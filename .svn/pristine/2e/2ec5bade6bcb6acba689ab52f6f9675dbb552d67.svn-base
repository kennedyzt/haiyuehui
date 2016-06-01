<#setting classic_compatible=true>
<@insert template="login/right">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <script>
        $(function(){
            saleAnalysisGrid(1,10);
        });
        function saleAnalysisGrid(pageNo,pageSize){
            siping.fillGrid({
                content : "sale_analysis_grid",
                width : "100%",
                height : "430px",
                ajax : {
                    type : "post",
                    url : "report/saleanalysisreport/getlist",
                    async : true, 
                    data: {pageNo:pageNo, pageSize:pageSize,startDate:$("#startDate").text(),endDate:$("#endDate").text(),materialName:$("#materialName").text(),materialNo:$("#materialNo").val(),isPaging:$("#isPaging").val(),shopNo:$("#shopNo").val(),partnerNo:$("#partnerNo").val()},
                    contentType : 'application/json'
                },
                header : [
                      {width : "8.3%",name : "单据编号"},
                      {width : "8.3%",name : "单据日期"},
                      {width : "8.3%",name : "客户姓名"},
                      {width : "8.3%",name : "加盟商"},
                      {width : "8.3%",name : "商家"},
                      {width : "8.3%",name : "商品货号"},
                      {width : "8.3%",name : "国际编码"},
                      {width : "8.3%",name : "商品名称"},
                      {width : "8.3%",name : "计量单位"},
                      {width : "8.3%",name : "数量"},
                      {width : "8.3%",name : "单价"},
                      {width : "8.3%",name : "金额"}
                ],
                column : ["shipmentsNumber","billDate","partnerName","franchiseeName","shopName","materialNo","barcode","materialName","unitName","saleNumber","salePrice","saleTotal"],
                page : {options : [10,20,30,50,80], fn : "saleAnalysisGrid"},
                rowNum : true,
                totalRow : [{text : "数量合计", id : "totalCounts"},{text : "金额合计(元)", id : "totalPrice"}]
            });
        }
        
        function preViewSaleAnalysis() {
            var request = {};
            request.keywords = $("#keywords").val();
            request.startDate = $("#startDate").val();
            request.endDate = $("#endDate").val();
            request.materialName = $("#materialName").text();
            request.materialNo = $("#materialNo").val();
            request.shopNo = $("#shopNo").val();
            request.isPaging = false;
            siping.ajax({
                method : "post",
                url : 'report/saleanalysisreport/getlist',
                data : JSON.stringify(request),
                dataType : "json",
                contentType : "application/json",
                success : function(data) {
                    $("#sale_analysis_grid_print").empty();
                    createDataSourceHtml(data);
                    LODOP = getLodop();
                    LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
                    LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); //设置纸张
                    var linkHref = staticPath + "/css/print/common.css";
                    var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
                    var strFormHtml = strStyleCSS + "<body>" + document.getElementById("sale_analysis_grid_print").innerHTML + "</body>";
                    LODOP.PRINT_INIT("打印销售明细");
                    LODOP.ADD_PRINT_TABLE("10mm", "10mm", "190mm", "277mm", strFormHtml); //四个数值分别表示Top,Left,Width,Height
                    LODOP.SET_PRINT_STYLEA(0,"TableHeightScope",1); // 0,1,2,3 不包含,包含头尾，头，尾
                    LODOP.PREVIEW();
                }
            });
        }
        function createDataSourceHtml(data) {
            var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">销售明细</font></b></caption><thead><tr><th>单据编号</th><th>单据日期</th><th>客户姓名</th><th>加盟商</th><th>商家</th><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>计量单位</th><th>数量</th><th>单价</th><th>金额</th></tr></thead><tbody>';
            for (i = 0; i < data.records.length; i++) {
                dataSourceHtml += "<tr><td>"+data.records[i].shipmentsNumber+"</td><td>"+data.records[i].billDate+"</td><td>"+data.records[i].partnerName+"</td><td>"+data.records[i].franchiseeName+"</td><td>"+data.records[i].shopName+"</td><td>"+data.records[i].materialNo+"</td><td>"+data.records[i].barcode+"</td><td>"+data.records[i].materialName+"</td><td>"+data.records[i].unitName+"</td><td>"+data.records[i].saleNumber+"</td><td>"+data.records[i].salePrice+"</td><td>"+data.records[i].saleTotal+"</td></tr>";
            }
            dataSourceHtml += '<tr class="tfoot"><td colspan="12" style="text-align:right;font-size: 14px;">数量合计:'+Number(data.stats.totalCounts).toFixed(2)+'金额合计(元):'+Number(data.stats.totalPrice).toFixed(2)+'</td></tr>';
            dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="12" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
            $("#sale_analysis_grid_print").append(dataSourceHtml);
        }
    </script>
</head>
<body>
<div class="list_page">
    <div class="search_section">
        <div class="report_search_text common_text_div" style="width:33%">
            <div class="common_text_title">日期</div>
            <div class="common_text_val">
                <span id="startDate" class="readonly_text_common grid_page_tex" data-type="date" style="width:85px;">${startDate}</span>至
                <span id="endDate" class="readonly_text_common grid_page_tex" data-type="date" style="width:85px;">${endDate}</span>
            </div>
        </div>
        <div class="report_search_text common_text_div" style="width:33%">
            <div class="common_text_title">客户</div>
            <div class="common_text_val"><span id="partnerName" class="readonly_text_common grid_page_tex">${partnerName}</span><input type="hidden" value="${partnerNo}" id="partnerNo"></div>
        </div>
        <div class="report_search_text common_text_div" style="width:33%">
            <div class="common_text_title">商家</div>
            <div class="common_text_val"><span id="shopName" class="readonly_text_common grid_page_tex">${shopName}</span><input type="hidden" value="${shopNo}" id="shopNo""></div>
        </div>
        <div class="report_search_text common_text_div" style="width:33%">
            <div class="common_text_title">加盟商</div>
            <div class="common_text_val"><span id="franchiseeName" class="readonly_text_common grid_page_tex">${franchiseeName}</span><input type="hidden" value="${franchiseeNo}" id="franchiseeNo"></div>
        </div>
        <div class="report_search_text common_text_div" style="width:33%">
            <div class="common_text_title">商品</div>
            <div class="common_text_val"><span id="materialName" class="readonly_text_common grid_page_tex">${materialName}</span><input type="hidden" value="${materialNo}" id="materialNo"><input id="isPaging" type="hidden" value="true"></div>
        </div>
       <div class="report_search_text common_text_div" style="text-align:right;width:33%">
            <button onclick="preViewSaleAnalysis();" class="btn_common my_radius btn_search">打印</button>
        </div>
        <div style="clear:both"></div>
        <div style="clear:both"></div>
    </div>
    <div id="sale_analysis_grid"></div>
    <div style="display:none;" id="sale_analysis_grid_print"></div>
</div>
</body>
</html>
</@insert>
