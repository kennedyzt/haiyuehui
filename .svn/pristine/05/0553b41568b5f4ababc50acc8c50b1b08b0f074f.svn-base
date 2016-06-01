$(function(){
    supplierExpGrid(1,10);
});
function supplierExpGrid(pageNo,pageSize){
    var header = null;
    var language = $("#user_language").val();
    if(language == "en"){
        header = [{width : "7%",name : "Product Number"},
              {width : "7%",name : "Product Name"},
              {width : "7%",name : "Barcode"},
              {width : "6%",name : "Category"},
              {width : "4%",name : "Unit of Measurement"},
              {width : "7%",name : "Packing Specifications"},
              {width : "6%",name : "Batch Number"},
              {width : "6%",name : "Production Date"},
              {width : "6%",name : "Expiration Date"},
              {width : "6%",name : "Warehouse Number"},
              {width : "7%",name : "Warehouse Name"},
              {width : "6%",name : "Location Number"},
              {width : "7%",name : "Location Name"},
              {width : "6%",name : "Provider"},
              {width : "6%",name : "Quantity in Stock"},
              {width : "7%",name : "Remaining Days of Expiration"}];
    }else{
        header = [{width : "6%",name : "商品货号"},
                  {width : "7%",name : "商品名称"},
                  {width : "5%",name : "国际编码"},
                  {width : "6%",name : "类别"},
                  {width : "4%",name : "计量单位"},
                  {width : "7%",name : "规格型号"},
                  {width : "6%",name : "批次号"},
                  {width : "6%",name : "生产日期"},
                  {width : "6%",name : "过期日期"},
                  {width : "6%",name : "仓库编号"},
                  {width : "7%",name : "仓库名称"},
                  {width : "6%",name : "库位编号"},
                  {width : "7%",name : "库位名称"},
                  {width : "6%",name : "供应商"},
                  {width : "6%",name : "库存数量"},
                  {width : "8%",name : "剩余过期天数"}];
    }
    siping.fillGrid({
        content : "product_exp_grid",
        width : "2550px",
        height : "500px",
        ajax : {
            type : "post",
            url : "report/productexp/getdata",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,keyword:$("#keyword").val(),remainDays:$("#exp_days_").val()},
            contentType : 'application/json'
        },
        header : header,
        column : ["materialNo","materialName","barcode","materialTypeName","unitName","specificationsModel","batchCode","productDate","expireDate","storageNo","storageName","locationNo","locationName","partnerName","stockQuantity","remainDays"],
        page : {options : [10,20,30,50,80], fn : "supplierExpGrid"},
        rowNum : true,
        totalRow : null
    });
}
function checkNumForExpDays(obj){
    var val = obj.value;
    if(isNaN(val)){
        siping.tips("Not a Number!","exp_days_");
        $(obj).val("");
    }
}

function preViewProductExpiration() {
    var request = {};
    request.keywords = $("#keywords").val();
    request.remainDays = $("#exp_days_").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/productexp/getdata',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#product_exp_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(2, 0, 0, "A4"); //设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("product_exp_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印商品有效期");
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "277mm", "190mm", strFormHtml); //四个数值分别表示Top,Left,Width,Height
            LODOP.SET_PRINT_STYLEA(0,"TableHeightScope",1); // 0,1,2,3 不包含,包含头尾，头，尾
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">商品有效期</font></b></caption><thead><tr><th>商品货号</th><th>商品名称</th><th>国际编码</th><th>类别</th><th>计量单位</th><th>规格型号</th><th>批次号</th><th>生成日期</th><th>过期日期</th><th>仓库编号</th><th>仓库名称</th><th>库位编号</th><th>库位名称</th><th>供应商</th><th>库存数量</th><th>剩余过期天数</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>"+data.records[i].materialNo+"</td><td>"+data.records[i].materialName+"</td><td>"+data.records[i].barcode+"</td><td>"+data.records[i].materialTypeName+"</td><td>"+data.records[i].unitName+"</td><td>"+data.records[i].specificationsModel+"</td><td>"+data.records[i].batchCode+"</td><td>"+data.records[i].productDate+"</td><td>"+data.records[i].expireDate+"</td><td>"+data.records[i].storageNo+"</td><td>"+data.records[i].storageName+"</td><td>"+data.records[i].locationNo+"</td><td>"+data.records[i].locationName+"</td><td>"+data.records[i].partnerName+"</td><td>"+data.records[i].stockQuantity+"</td><td>"+data.records[i].remainDays+"</td></tr>";
    }
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="16" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#product_exp_grid_print").append(dataSourceHtml);
}