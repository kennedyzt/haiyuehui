$(function(){
    inventoryBalanceGrid(1,10);
});
function inventoryBalanceGrid(pageNo,pageSize){
    var param = {fromPage:{billsDate:$("#billsDate").val()},fromRow:{material:"materialNo",materialName:"materialName",stroage:"stroageNo",stroageName:"stroageName",materialType:"materialTypeId",materialTypeName:"materialTypeName"}};
    var urlColumn = {name:"materialNo",openType:"window",url:"inventorybalance/toinoutpage",param:param,width:"90%",height:"90%",title:"出入库明细"};
    siping.fillGrid({
        content : "inventory_balance_grid",
        width : "100%",
        height : "450px",
        ajax : {
            type : "post",
            url : "inventorybalance/getlist",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,billsDate:$("#billsDate").val(),material:$("#material").val(),materialType:$("#father_id").val(),stroage:$("#stroage").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "9%",name : "商品货号"},
              {width : "10%",name : "国际编码"},
              {width : "10%",name : "商品名称"},
              {width : "10%",name : "商品类型"},
			  {width : "9%",name : "计量单位"},
			  {width : "10%",name : "仓库编码"},
			  {width : "10%",name : "仓库名称"},
			  {width : "10%",name : "数量"},
			  {width : "10%",name : "成本"},
			  {width : "10%",name : "金额"}
        ],
        column : [urlColumn,"barcode","materialName","materialTypeName","materialUnitName","stroageNo","stroageName","count","cost","totalPrice"],
        page : {options : [10,20,30,50,80], fn : "inventoryBalanceGrid"},
        rowNum : true,
		totalRow : [{text : "数量合计", id : "count"},{text : "金额合计（元）", id : "totalPrice" }]
    });
}

function preViewInventoryBalance() {
    var request = {};
    request.billsDate = $("#billsDate").val();
    request.material = $("#material").val();
    request.materialType = $("#materialType").val();
    request.stroage = $("#stroage").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : '/inventorybalance/getlist',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#inventory_balance_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); //设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("inventory_balance_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印库存余额");
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "190mm", "277mm", strFormHtml); //四个数值分别表示Top,Left,Width,Height
            LODOP.SET_PRINT_STYLEA(0,"TableHeightScope",1); // 0,1,2,3 不包含,包含头尾，头，尾
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">库存余额</font></b></caption><thead><tr><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>计量单位</th><th>仓库编码</th><th>仓库名称</th><th>数量</th><th>成本</th><th>金额</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>"+data.records[i].materialNo+"</td><td>"+data.records[i].barcode+"</td><td>"+data.records[i].materialName+"</td><td>"+data.records[i].materialTypeName+"</td><td>"+data.records[i].materialUnitName+"</td><td>"+data.records[i].stroageNo+"</td><td>"+data.records[i].stroageName+"</td><td>"+data.records[i].count+"</td><td>"+data.records[i].cost+"</td><td>"+data.records[i].totalPrice+"</td></tr>";
    }
    dataSourceHtml += '<tr class="tfoot"><td colspan="10" style="text-align:right;font-size: 14px;">数量合计:'+Number(data.stats.count).toFixed(2)+'    金额合计(元):'+Number(data.stats.totalPrice).toFixed(2)+'</td></tr>';
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="10" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#inventory_balance_grid_print").append(dataSourceHtml);
}