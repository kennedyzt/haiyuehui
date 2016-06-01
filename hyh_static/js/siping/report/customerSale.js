$(function(){
    customerSaleGrid(1,10);
});
function customerSaleGrid(pageNo, pageSize){
    var param = {fromPage:{startDate:$("#dateFrom").val(),endDate:$("#dateTo").val()},fromRow:{partnerNo:"customerNo",partnerName:"customerName"}};
    var urlColumn = {name:"customerNo",openType:"window",url:"report/saleanalysisreport",param:param,width:"90%",height:"90%",title:"销售明细"};
    siping.fillGrid({
        content : "customer_sale_grid",
        width : "100%",
        height : "500px",
        ajax : {
            type : "post",
            url : "report/customersale/getdata",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,keyword:$("#keyword").val(),dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "20%",name : "客户编号"},
              {width : "30%",name : "客户名称"},
              {width : "30%",name : "金额"}
        ],
        column : [urlColumn,"customerName","money"],
        page : {options : [10,20,30,50,80], fn : "customerSaleGrid"},
        rowNum : true,
        totalRow : [{text : "金额合计", id : "totalMoney"}]
    });
}

function preViewCustomerSale() {
    var request = {};
    request.keyword = $("#keyword").val();
    request.dateFrom = $("#dateFrom").val();
    request.dateTo = $("#dateTo").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/customersale/getdata',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#customer_sale_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("customer_sale_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印供应商采购统计");
            LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">客户销售统计</font></b></caption><thead><tr><th>客户编号</th><th>名称</th><th>金额</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>" + data.records[i].customerNo + "</td><td>" + data.records[i].customerName + "</td><td>" + data.records[i].money + "</td></tr>";
    }
    dataSourceHtml += '<tr class="tfoot"><td colspan="3" style="text-align:right;font-size: 14px;">金额合计(元):'+Number(data.stats.totalMoney).toFixed(2)+'</td></tr>';
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="3" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#customer_sale_grid_print").append(dataSourceHtml);
}
