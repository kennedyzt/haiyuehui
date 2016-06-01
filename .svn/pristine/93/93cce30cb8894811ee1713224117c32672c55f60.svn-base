$(function(){
    purchaseDetailGrid(1,10);
});
function purchaseDetailGrid(pageNo, pageSize){
    siping.fillGrid({
        content : "purchase_detail_grid",
        width : "100%",
        height : "450px",
        ajax : {
            type : "post",
            url : "report/purchaseanalysis/getdata",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,supplierNo:$("#supplier_no").val(),productNo:$("#product_no").val(),dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "8%",name : "单据编号"},
              {width : "8%",name : "单据日期"},
              {width : "14%",name : "供应商"},
              {width : "12%",name : "商品货号"},
              {width : "12%",name : "国际编码"},
              {width : "14%",name : "商品名称"},
              {width : "9%",name : "商品类型"},
              {width : "8%",name : "计量单位"},
              {width : "6%",name : "数量"},
              {width : "6%",name : "金额(元)"}
        ],
        column : ["billsNo","billsDate","supplierName","productNo","barcode","prodcutName","typeName","unitName","quantity","money"],
        page : {options : [10,20,30,50,80], fn : "purchaseDetailGrid"},
        rowNum : true,
        totalRow : [{text : "数量合计", id : "counts"},{text : "金额合计(元)", id : "money"}]
    });
}

function preViewPurchaseDetail() {
    var request = {};
    request.supplierNo = $("#supplier_no").val();
    request.productNo = $("#product_no").val();
    request.dateFrom = $("#dateFrom").val();
    request.dateTo = $("#dateTo").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/purchaseanalysis/getdata',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#purchase_detail_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); //设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("purchase_detail_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印采购明细");
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "190mm", "277mm", strFormHtml); //四个数值分别表示Top,Left,Width,Height
            LODOP.SET_PRINT_STYLEA(0,"TableHeightScope",1); // 0,1,2,3 不包含,包含头尾，头，尾
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">采购明细</font></b></caption><thead><tr><th>单据编号</th><th>单据日期</th><th>供应商</th><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>计量单位</th><th>数量</th><th>金额</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>"+data.records[i].billsNo+"</td><td>"+data.records[i].billsDate+"</td><td>"+data.records[i].supplierName+"</td><td>"+data.records[i].productNo+"</td><td>"+data.records[i].barcode+"</td><td>"+data.records[i].prodcutName+"</td><td>"+data.records[i].typeName+"</td><td>"+data.records[i].unitName+"</td><td>"+data.records[i].quantity+"</td><td>"+data.records[i].money+"</td></tr>";
    }
    dataSourceHtml += '<tr class="tfoot"><td colspan="10" style="text-align:right;font-size: 14px;">数量合计:'+Number(data.stats.counts).toFixed(2)+'金额合计(元):'+Number(data.stats.money).toFixed(2)+'</td></tr>';
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="10" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#purchase_detail_grid_print").append(dataSourceHtml);
}