$(function(){
    productPurchaseGrid(1,10);
});
function productPurchaseGrid(pageNo, pageSize){
    var param = {fromPage:{dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val()},fromRow:{productNo:"productNo",productName:"productName"}};
    var urlColumn = {name:"productNo",openType:"window",url:"report/purchaseanalysis",param:param,width:"90%",height:"90%",title:"采购明细"};
    siping.fillGrid({
        content : "product_purchase_grid",
        width : "100%",
        height : "500px",
        ajax : {
            type : "post",
            url : "report/productpurchase/getdata",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,keyword:$("#keyword").val(),productType:$("#father_id").val(),dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "15%",name : "商品货号"},
              {width : "16%",name : "国际编码"},
              {width : "16%",name : "商品名称"},
              {width : "16%",name : "商品类型"},
              {width : "16%",name : "单位"},
              {width : "10%",name : "数量"},
              {width : "10%",name : "金额(元)"}
        ],
        column : [urlColumn,"barCode","productName","productType","unitName","quantity","money"],
        page : {options : [10,20,30,50,80], fn : "productPurchaseGrid"},
        rowNum : true,
        totalRow : [{text : "数量合计", id : "counts"},{text : "金额合计(元)", id : "money"}]
    });
}

function preViewProductPurchase() {
    var request = {};
    request.keyword = $("#keyword").val();
    request.productType= $("#father_id").val();
    request.dateFrom = $("#dateFrom").val();
    request.dateTo = $("#dateTo").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/productpurchase/getdata',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#product_purchase_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("product_purchase_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印供应商采购统计");
            LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">商品采购统计</font></b></caption><thead><tr><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>单位</th><th>数量</th><th>金额(元)</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>" + data.records[i].productNo + "</td><td>" + data.records[i].barCode + "</td><td>" + data.records[i].productName + "</td><td>" + data.records[i].productType + "</td><td>" + data.records[i].unitName + "</td><td>" + data.records[i].quantity + "</td><td>" + data.records[i].money + "</td></tr>";
    }
    dataSourceHtml += '<tr class="tfoot"><td colspan="7" style="text-align:right;font-size: 14px;">数量合计:'+Number(data.stats.counts).toFixed(2)+'    金额合计(元):'+Number(data.stats.money).toFixed(2)+'</td></tr>';
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="7" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#product_purchase_grid_print").append(dataSourceHtml);
}