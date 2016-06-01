<#setting classic_compatible=true>
<@insert template="login/right">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <script>
        $(function(){
            materialWarningGrid(1,10);
        });
        function materialWarningGrid(pageNo,pageSize){
            siping.fillGrid({
                content : "material_warning_grid",
                width : "100%",
                height : "500px",
                ajax : {
                    type : "post",
                    url : "report/materialwarningreport/getlist",
                    async : true, 
                    data: {pageNo:pageNo, pageSize:pageSize,isPaging:$("#is_paging").val(),warningType:$("input[name='material_type']:checked").val(),keyword:$("#keyword").val()},
                    contentType : 'application/json'
                },
                header : [
                      {width : "10%",name : "商品货号"},
                      {width : "10%",name : "国际编码"},
                      {width : "10%",name : "商品名称"},
                      {width : "10%",name : "商品类型"},
                      {width : "10%",name : "仓库编码"},
                      {width : "10%",name : "仓库名称"},
                      {width : "10%",name : "计量单位"},
                      {width : "10%",name : "库存数量"},
                      {width : "10%",name : "预警数量"},
                      {width : "10%",name : "差异数量"}
                ],
                column : ["materialNo","barcode","materialName","materialTypeName","storageNo","storageName","unitName","inventoryNumber","warningNumber","diffNumber"],
                page : {options : [10,20,30,50,80], fn : "materialWarningGrid"},
                rowNum : true,
                totalRow : [{text : "库存合计", id : "totalCounts"},{text : "差异合计", id : "totalDiff"}]
            });
        }
        function preViewMaterialWarning () {
            var request = {};
            request.warningType = $("input[name='material_type']:checked").val();
            request.keyword= $("#keyword").val();
            request.isPaging = false;
            siping.ajax({
                method : "post",
                url : 'report/materialwarningreport/getlist',
                data : JSON.stringify(request),
                dataType : "json",
                contentType : "application/json",
                success : function(data) {
                    $("#material_warning_grid_print").empty();
                    createDataSourceHtml(data);
                    LODOP = getLodop();
                    LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
                    LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
                    var linkHref = staticPath + "/css/print/common.css";
                    var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
                    var strFormHtml = strStyleCSS + "<body>" + document.getElementById("material_warning_grid_print").innerHTML + "</body>";
                    LODOP.PRINT_INIT("打印仓库预警");
                    LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
                    LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
                    LODOP.PREVIEW();
                }
            });
        }
        function createDataSourceHtml(data) {
            var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">仓库预警</font></b></caption><thead><tr><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>仓库编码</th><th>仓库名称</th><th>计量单位</th><th>库存数量</th><th>预警数量</th><th>差异数量</th></tr></thead><tbody>';
            for (i = 0; i < data.records.length; i++) { "materialNo","barcode","materialName","materialTypeName","storageNo","storageName","unitName","inventoryNumber","warningNumber","diffNumber"
                dataSourceHtml += "<tr><td>" + data.records[i].materialNo + "</td><td>" + data.records[i].barcode + "</td><td>" + data.records[i].materialName + "</td><td>" + data.records[i].materialTypeName + "</td><td>" + data.records[i].storageNo + "</td><td>" + data.records[i].storageName + "</td><td>" + data.records[i].unitName + "</td><td>" + Number(data.records[i].inventoryNumber).toFixed(2) + "</td><td>" + Number(data.records[i].warningNumber).toFixed(2) + "</td><td>" + Number(data.records[i].diffNumber).toFixed(2) + "</td></tr>";
            }
            dataSourceHtml += '<tr class="tfoot"><td colspan="10" style="text-align:right;font-size: 14px;">库存合计:'+Number(data.stats.totalCounts).toFixed(2)+'    差异合计:'+Number(data.stats.totalDiff).toFixed(2)+'</td></tr>';
            dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="10" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
            $("#material_warning_grid_print").append(dataSourceHtml);
        }
    </script>
</head>
<body>
<div class="list_page">
    <div class="head_top_">
        <div class="rect_block" onclick="preViewMaterialWarning();"> 
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
    </div>
    <div class="search_section">
        <div class="report_search_text common_text_div" style="width:25%">
            <div class="common_text_title"></div>
            <div class="common_text_val">
               <span>下限预警</span><input type="radio" name="material_type" value="0" checked="checked"><!-- 下限预警-->
               <span>上限预警</span><input type="radio" name="material_type" value="1"><!-- 上限预警-->
               <input type="hidden" id="is_paging" value="true">
            </div>
        </div>
        <div class="report_search_text common_text_div" style="width:33%">
            <div class="common_text_title">仓库</div>
            <div class="common_text_val">
                <input name="keyword" id="keyword" class="input_text_common my_radius" data-click-tip="输入仓库编号,仓库名称关键字" placeholder="仓库编号，仓库名称">
            </div>
        </div>
        <div class="report_search_text common_text_div" style="text-align:right;width:33%">
            <button onclick="materialWarningGrid(1,10);" class="btn_common my_radius btn_search">查询</button>
        </div>
        <div style="clear:both"></div>
    </div>
    <div id="material_warning_grid"></div>
    <div style="display:none;" id="material_warning_grid_print"></div>
</div>
</body>
</html>
</@insert>
