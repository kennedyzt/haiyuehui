$(function(){
    supplierPurchaseGrid(1,10);
    siping.fillSelect({
        id : "country",
        type : "post",
        url : "countryregion/getalllist",
        param : null,
        optionValue : "id",
        optionText : "countryRegionName"
    });
});
function supplierPurchaseGrid(pageNo,pageSize){
    var param = {fromPage:{dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val()},fromRow:{supplierNo:"supplierNo",supplierName:"supplierName"}};
    var urlColumn = {name:"supplierNo",openType:"window",url:"report/purchaseanalysis",param:param,width:"90%",height:"90%",title:"采购明细"};
    siping.fillGrid({
        content : "supplier_purchase_grid",
        width : "100%",
        height : "450px",
        ajax : {
            type : "post",
            url : "report/supplierpurchase/getdata",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,keyword:$("#keyword").val(),country:$("#country").val(),dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "24%",name : "供应商编码"},
              {width : "25%",name : "供应商名称"},
              {width : "20%",name : "国家/地区"},
              {width : "25%",name : "金额(元)"}
        ],
        column : [urlColumn,"supplierName","country","total"],
        page : {options : [10,20,30,50,80], fn : "supplierPurchaseGrid"},
        rowNum : true,
        totalRow : [{text : "金额合计(元)", id : "totalMoney"}]
    });
}

function preViewSupplierPurchase() {
    var request = {};
    request.keyword = $("#keyword").val();
    request.country= $("#country").val(),
    request.dateFrom = $("#dateFrom").val();
    request.dateTo = $("#dateTo").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/supplierpurchase/getdata',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#supplier_purchase_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("supplier_purchase_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印供应商采购统计");
            LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">供应商采购统计</font></b></caption><thead><tr><th>供应商编码</th><th>供应商名称</th><th>国家/地区</th><th>金额(元)</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>" + data.records[i].supplierNo + "</td><td>" + data.records[i].supplierName + "</td><td>" + data.records[i].country + "</td><td>" + data.records[i].total + "</td></tr>";
    }
    dataSourceHtml += '<tr class="tfoot"><td colspan="4" style="text-align:right;font-size: 14px;">金额合计(元):'+Number(data.stats.totalMoney).toFixed(2)+'</td></tr>';
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="10" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#supplier_purchase_grid_print").append(dataSourceHtml);
}
