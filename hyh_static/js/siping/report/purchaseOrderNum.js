$(function(){
    purchaseOrderNumGrid(1,10);
});
function purchaseOrderNumGrid(pageNo, pageSize){
    siping.fillGrid({
        content : "purchase_ordernum_grid",
        width : "100%",
        height : "500px",
        ajax : {
            type : "post",
            url : "report/purchaseordernum/getdata",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,keyword:$("#keyword").val(),orderNo:$("#orderNo").val(),supplierId:$("#id").text(),productType:$("#father_id").val(),dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "8%",name : "订单号"},
              {width : "8%",name : "单据日期"},
              {width : "10%",name : "供应商"},
              {width : "9%",name : "商品货号"},
              {width : "10%",name : "国际编码"},
              {width : "10%",name : "商品名称"},
              {width : "10%",name : "商品类型"},
              {width : "8%",name : "单位"},
              {width : "8%",name : "应收数量"},
              {width : "8%",name : "应收金额(元)"},
              {width : "8%",name : "实收数量"}
        ],
        column : ["orderNo","billsDate","supplierName","productNo","barcode","productName","productType","unitName","counts","afterDiscount","receiptCounts"],
        page : {options : [10,20,30,50,80], fn : "purchaseOrderNumGrid"},
        rowNum : true,
        totalRow : [{text : "应收数量合计", id : "counts"},{text : "实收数量合计", id : "receiptCounts"}]
    });
}

function preViewPurchaseOrder() {
    var request = {};
    request.keyword = $("#keyword").val();
    request.orderNo= $("#orderNo").val(),
    request.supplierId = $("#id").text();
    request.dateFrom = $("#dateFrom").val();
    request.dateTo = $("#dateTo").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/purchaseordernum/getdata',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#purchase_ordernum_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("purchase_ordernum_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印采购订单统计");
            LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">采购订单统计</font></b></caption><thead><tr><th>订单号</th><th>单据日期</th><th>供应商</th><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>单位</th><th>应收数量</th><th>实收数量</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>" + data.records[i].orderNo + "</td><td>" + data.records[i].billsDate + "</td><td>" + data.records[i].supplierName + "</td><td>" + data.records[i].productNo + "</td><td>" + data.records[i].barcode + "</td><td>" + data.records[i].productName + "</td><td>" + data.records[i].productType + "</td><td>" + data.records[i].unitName + "</td><td>" + data.records[i].counts + "</td><td>" + data.records[i].receiptCounts + "</td></tr>";
    }
    dataSourceHtml += '<tr class="tfoot"><td colspan="10" style="text-align:right;font-size: 14px;">应收数量合计:'+Number(data.stats.counts).toFixed(2)+'    实收数量合计:'+Number(data.stats.receiptCounts).toFixed(2)+'</td></tr>';
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="10" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#purchase_ordernum_grid_print").append(dataSourceHtml);
}