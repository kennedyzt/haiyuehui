$(function(){
    inventoryBalanceDetailGrid(1,10);
});

function inventoryBalanceDetailGrid(pageNo,pageSize){
    siping.fillGrid({
        content : "inventory_balance_detail_grid",
        width : "2500px",
        height : "450px",
        ajax : {
            type : "post",
            url : "/inventorybalance/getDetaillist",
            async : true, 
            data: {pageNo:pageNo, pageSize:pageSize,billsDate:$("#billsDate").val(),material:$("#material").val(),materialType:$("#father_id").val(),stroage:$("#stroage").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "5%",name : "单据号"},
              {width : "5%",name : "单据日期"},
              {width : "5%",name : "业务伙伴"},
              {width : "5%",name : "仓库名称"},
			  {width : "5%",name : "商品货号"},
			  {width : "5%",name : "国际编码"},
			  {width : "5%",name : "商品名称"},
			  {width : "5%",name : "批次号"},
			  {width : "5%",name : "商品类型"},
			  {width : "5%",name : "计量单位"},
			  {width : "5%",name : "入库数量"},
			  {width : "5%",name : "入库成本"},
			  {width : "5%",name : "入库金额"},
			  {width : "5%",name : "出库数量"},
			  {width : "5%",name : "出库成本"},
			  {width : "5%",name : "出库金额"}
	//		  {width : "5%",name : "结余数量"}, 隐藏结余
	//		  {width : "5%",name : "结余成本"},
	//		  {width : "5%",name : "结余金额"}
        ],
        column : ["billsNo","billsDate","businessPartnerName","stroageName","materialNo","barcode","materialName","batchNumber","materialTypeName","materialUnitName","inboundCount","inboundCost","inboundTotalPrice","outBoundCount","outBoundCost","outBoundTotalPrice"],
		// "balanceCount","balanceCost","balanceTotalPrice"],
        page : {options : [10,20,30,50,80], fn : "inventoryBalanceDetailGrid"},
        rowNum : true,
		totalRow : [{text : "入库数量合计", id : "inboundCount"},{text : "入库金额合计（元）", id : "inboundTotalPrice" },{text : "出库数量合计", id : "outboundCount"},{text : "出库金额合计（元）", id : "outboundTotalPrice" }]
    });
}

function preInventoryBalanceDetail() {
    var request = {};
    request.billsDate = $("#billsDate").val();
    request.material = $("#material").val();
    request.materialType = $("#father_id").val();
    request.stroage = $("#stroage").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : '/inventorybalance/getDetaillist',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#inventory_balance_detail_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(2, 0, 0, "A4"); //设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("inventory_balance_detail_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印出入库明细");
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "277mm", "190mm", strFormHtml); //四个数值分别表示Top,Left,Width,Height
            LODOP.SET_PRINT_STYLEA(0,"TableHeightScope",1); // 0,1,2,3 不包含,包含头尾，头，尾
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">出入库明细</font></b></caption><thead><tr><th>单据编号</th><th>单据日期</th><th>业务伙伴</th><th>仓库名称</th><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>计量单位</th><th>入库数量</th><th>入库成本</th><th>入库金额</th><th>出库数量</th><th>出库成本</th><th>出库金额</th><th>结余数量</th><th>结余成本</th><th>结余金额</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {  column : ["billsNo","billsDate","businessPartnerName","stroageName","materialNo","barcode","materialName","materialTypeName","materialUnitName",                                                                                                                                                                                                                                                                                                                                                                                                                   "inboundCount","inboundCost","inboundTotalPrice","outBoundCount","outBoundCost","outBoundTotalPrice","balanceCount","balanceCost","balanceTotalPrice"],
        dataSourceHtml += "<tr><td>"+data.records[i].billsNo+"</td><td>"+data.records[i].billsDate+"</td><td>"+data.records[i].businessPartnerName+"</td><td>"+data.records[i].stroageName+"</td><td>"+data.records[i].materialNo+"</td><td>"+data.records[i].barcode+"</td><td>"+data.records[i].materialName+"</td><td>"+data.records[i].materialTypeName+"</td><td>"+data.records[i].materialUnitName+"</td><td>"+data.records[i].inboundCount+"</td><td>"+data.records[i].inboundCost+"</td><td>"+data.records[i].inboundTotalPrice+"</td><td>"+data.records[i].outBoundCount+"</td><td>"+data.records[i].outBoundCost+"</td><td>"+data.records[i].outBoundTotalPrice+"</td><td>"+data.records[i].balanceCount+"</td><td>"+data.records[i].balanceCost+"</td><td>"+data.records[i].balanceTotalPrice+"</td></tr>";
    }
    dataSourceHtml += '<tr class="tfoot"><td colspan="18" style="text-align:right;font-size: 14px;">入库数量合计:'+Number(data.stats.inboundCount).toFixed(2)+'    入库金额合计(元):'+Number(data.stats.inboundTotalPrice).toFixed(2)+'    出库数量合计:'+Number(data.stats.outboundCount).toFixed(2)+'    出库金额合计(元):'+Number(data.stats.outboundTotalPrice).toFixed(2)+'</td></tr>';
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="18" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#inventory_balance_detail_grid_print").append(dataSourceHtml);
}