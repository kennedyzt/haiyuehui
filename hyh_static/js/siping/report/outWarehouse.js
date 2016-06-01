$(function(){
    outWarehouseGrid(1,10);
    siping.fillSelect({
        id : "storageId",
        type : "get",
        url : "storage/getlist/withnoparam",
        param : null,
        optionValue : "id",
        optionText : "storageName"
    });
});
function outWarehouseGrid(pageNo, pageSize){
    siping.fillGrid({
        content : "out_warehouse_grid",
        width : "100%",
        height : "500px",
        ajax : {
            type : "post",
            url : "report/outwarehouse/getdata",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,keyword:$("#keyword").val(),productType:$("#father_id").val(),
                dateFrom:$("#dateFrom").val(),dateTo:$("#dateTo").val(),storageId:$("#storageId").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "12%",name : "商品货号"},
              {width : "12%",name : "国际编码"},
              {width : "12%",name : "商品名称"},
              {width : "12%",name : "商品类型"},
              {width : "12%",name : "仓库编码"},
              {width : "12%",name : "仓库名称"},
              {width : "12%",name : "单位"},
              {width : "12%",name : "数量"}
        ],
        column : ["productNo","barcode","productName","productType","storageNo","storageName","unitName","counts"],
        page : {options : [10,20,30,50,80], fn : "outWarehouseGrid"},
        rowNum : true
    });
}

function preViewOutWarehouse() {
    var request = {};
    request.keyword = $("#keyword").val();
    request.productType= $("#father_id").val();
    request.dateFrom = $("#dateFrom").val();
    request.dateTo = $("#dateTo").val();
    request.storageId = $("#storageId").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/outwarehouse/getdata',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#out_warehouse_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("out_warehouse_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印出货统计");
            LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">出货统计</font></b></caption><thead><tr><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>仓库编码</th><th>仓库名称</th><th>单位</th><th>数量</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>" + data.records[i].productNo + "</td><td>" + data.records[i].barCode + "</td><td>" + data.records[i].productName + "</td><td>" + data.records[i].productType + "</td><td>" + data.records[i].storageNo + "</td><td>" + data.records[i].storageName + "</td><td>" + data.records[i].unitName + "</td><td>" + data.records[i].counts + "</td></tr>";
    }
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="8" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#out_warehouse_grid_print").append(dataSourceHtml);
}