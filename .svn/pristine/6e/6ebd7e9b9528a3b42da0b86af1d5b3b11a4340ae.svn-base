<#setting classic_compatible=true>
<@insert template="login/right">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <script>
        $(function(){
            materialSaleGrid(1,10);
        });
        function materialSaleGrid(pageNo,pageSize){
            var param = {fromPage:{startDate:$("#startDate").val(),endDate:$("#endDate").val()},fromRow:{materialNo:"materialNo",materialName:"materialName"}};
            var urlColumn = {name:"materialNo",openType:"window",url:"report/saleanalysisreport",param:param,width:"90%",height:"90%",title:"销售明细"};
            siping.fillGrid({
                content : "material_sale_grid",
                width : "100%",
                height : "500px",
                ajax : {
                    type : "post",
                    url : "report/materialsalereport/getlist",
                    async : true, 
                    data: {pageNo:pageNo, pageSize:pageSize, keyword:$("#keyword").val(),startDate:$("#startDate").val(),endDate:$("#endDate").val(),materialType:$("#father_id").val(),isPaging:$("#isPaging").val()},
                    contentType : 'application/json'
                },
                header : [
                      {width : "14%",name : "商品货号"},
                      {width : "14%",name : "国际编码"},
                      {width : "14%",name : "商品名称"},
                      {width : "14%",name : "商品类型"},
                      {width : "14%",name : "单位"},
                      {width : "14%",name : "数量"},
                      {width : "14%",name : "金额"}
                ],
                column : [urlColumn,"barcode","materialName","materialTypeName","unitName","saleNumber","saleTotal"],
                page : {options : [10,20,30,50,80], fn : "materialSaleGrid"},
                rowNum : true,
                totalRow : [{text : "数量合计", id : "totalCounts"},{text : "金额合计(元)", id : "totalPrice"}]
            });
        }
        
        function preViewMaterialSale() {
            var request = {};
            request.keyword = $("#keyword").val();
            request.startDate= $("#startDate").val(),
            request.endDate = $("#endDate").val();
            request.materialType = $("#father_id").val();
            request.isPaging = false;
            siping.ajax({
                method : "post",
                url : 'report/materialsalereport/getlist',
                data : JSON.stringify(request),
                dataType : "json",
                contentType : "application/json",
                success : function(data) {
                    $("#material_sale_grid_print").empty();
                    createDataSourceHtml(data);
                    LODOP = getLodop();
                    LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
                    LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
                    var linkHref = staticPath + "/css/print/common.css";
                    var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
                    var strFormHtml = strStyleCSS + "<body>" + document.getElementById("material_sale_grid_print").innerHTML + "</body>";
                    LODOP.PRINT_INIT("打印商品销售统计");
                    LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
                    LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
                    LODOP.PREVIEW();
                }
            });
        }
        function createDataSourceHtml(data) {
            var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">商品销售统计</font></b></caption><thead><tr><th>商品货号</th><th>国家编码</th><th>商品名称</th><th>商品类型</th><th>单位</th><th>数量</th><th>金额</th></tr></thead><tbody>';
            for (i = 0; i < data.records.length; i++) {
                dataSourceHtml += "<tr><td>" + data.records[i].materialNo + "</td><td>" + data.records[i].barcode + "</td><td>" + data.records[i].materialName + "</td><td>" + data.records[i].materialTypeName + "</td><td>" + data.records[i].unitName + "</td><td>" + data.records[i].saleNumber + "</td><td>" + data.records[i].saleTotal + "</td></tr>";
            }
            dataSourceHtml += '<tr class="tfoot"><td colspan="7" style="text-align:right;font-size: 14px;">数量合计:'+data.stats.totalCounts+'    金额合计(元):'+data.stats.totalPrice+'</td></tr>';
            dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="7" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
            $("#material_sale_grid_print").append(dataSourceHtml);
        }
    </script>
</head>
<body>
<div class="list_page">
    <div class="head_top_">
        <div class="rect_block" onclick="preViewMaterialSale();"> 
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
    </div>
    <div class="search_section">
        <div class="report_search_text common_text_div" style="width:29%">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="keyword" class="input_text_common my_radius" placeholder="输入商品编码、名称"><input id="isPaging" type="hidden" value="true"></div>
        </div>
        <div class="report_search_text common_text_div" style="width:29%">
            <div class="common_text_title"><span data-required="true">所属商品类型</span></div>
            <div class="common_text_val">
                <input type="hidden" id="father_id" value="0">
                <input id="fatherType" data-type="materialType" name="materialType" value="全部" readonly class="input_text_common_small my_radius">
                <div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" hidden="hidden"></div>
            </div>
        </div>
        <div class="report_search_text common_text_div" style="width:29%">
            <div class="common_text_title">日期</div>
            <div class="common_text_val">
                <input id="startDate" class="input_text_common my_radius" data-type = "firstday" placeholder="开始日期" style="width:85px;">至
                <input id="endDate" class="input_text_common my_radius" data-type = "lastday" placeholder="结束日期" style="width:85px;">
            </div>
        </div>
        <div class="report_button_text common_text_div">
            <button onclick="materialSaleGrid(1,10);" class="btn_common my_radius btn_search">查询</button>
        </div>
        <div style="clear:both"></div>
        <div style="clear:both"></div>
    </div>
    <div id="material_sale_grid"></div>
    <div style="display:none;" id="material_sale_grid_print"></div>
</div>
</body>
</html>
</@insert>
