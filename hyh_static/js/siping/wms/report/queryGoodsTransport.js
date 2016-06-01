$(function() {
    queryGoodsTransportGrid(1, 10);
});
function queryGoodsTransportGrid(pageNo, pageSize) {
    siping.fillGrid({
        content : "query_goods_transport_grid",
        width : "100%",
        height : "500px",
        ajax : {
            type : "post",
            url : "report/querygoodstransport/getlist",
            async : true,
            data : {
                pageNo : pageNo,
                pageSize : pageSize,
                keywords : $("#keywords").val(),
                materialType : $("#father_id").val(),
                startDate : $("#startDate").val(),
                endDate : $("#endDate").val()
            },
            contentType : 'application/json'
        },
        header : [ {
            width : "10%",
            name : "商品货号"
        }, {
            width : "10%",
            name : "国际编码"
        }, {
            width : "10%",
            name : "商品名称"
        }, {
            width : "10%",
            name : "商品类型"
        }, {
            width : "10%",
            name : "批次号"
        }, {
            width : "10%",
            name : "起运港"
        }, {
            width : "10%",
            name : "目的港"
        }, {
            width : "10%",
            name : "运输方式"
        }, {
            width : "10%",
            name : "单位"
        }, {
            width : "10%",
            name : "数量"
        } ],
        column : [ "materialNo", "barcode", "materialName", "typeName", "batchNumber", "startStation", "endStation", "transportationType", "unitName", "receiptCounts" ],
        page : {
            options : [ 10, 20, 30, 50, 80 ],
            fn : "queryGoodsTransportGrid"
        },
        rowNum : true
    });
}
function preViewQueryGoodsTransport() {
    var request = {};
    request.keywords = $("#keywords").val();
    request.materialType= $("#father_id").val(),
    request.startDate = $("#startDate").val();
    request.endDate = $("#endDate").val();
    request.isPaging = false;
    siping.ajax({
        method : "post",
        url : 'report/querygoodstransport/getlist',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            $("#query_goods_transport_grid_print").empty();
            createDataSourceHtml(data);
            LODOP = getLodop();
            LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
            LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4"); // 设置纸张
            var linkHref = staticPath + "/css/print/common.css";
            var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
            var strFormHtml = strStyleCSS + "<body>" + document.getElementById("query_goods_transport_grid_print").innerHTML + "</body>";
            LODOP.PRINT_INIT("打印商品运输查询");
            LODOP.SET_PRINT_STYLEA(0, "TableHeightScope", 1); // 0,1,2,3   不包含,包含头尾，头，尾
            LODOP.ADD_PRINT_TABLE("10mm", "10mm", "90%", "90%", strFormHtml); // 四个数值分别表示Top,Left,Width,Height
            LODOP.PREVIEW();
        }
    });
}
function createDataSourceHtml(data) {
    var dataSourceHtml = '<table class = "print_table"><caption><b><font face="黑体" size="4">商品运输查询</font></b></caption><thead><tr><th>商品货号</th><th>国际编码</th><th>商品名称</th><th>商品类型</th><th>批次号</th><th>起运港</th><th>目的港</th><th>运输方式</th><th>单位</th><th>数量</th></tr></thead><tbody>';
    for (i = 0; i < data.records.length; i++) {
        dataSourceHtml += "<tr><td>" + data.records[i].materialNo + "</td><td>" + data.records[i].barcode + "</td><td>" + data.records[i].materialName + "</td><td>" + data.records[i].typeName + "</td><td>" + data.records[i].batchNumber + "</td><td>" + data.records[i].startStation + "</td><td>" + data.records[i].endStation + "</td><td>" + data.records[i].transportationType + "</td><td>" + data.records[i].unitName + "</td><td>" + data.records[i].receiptCounts + "</td></tr>";
    }
    dataSourceHtml += '</tbody><tfoot><tr class="tfoot"><td colspan="10" style="text-align:right;">第<font color="blue" tdata="PageNO">#</font>页/共<font tdata="PageCount" color="blue">#</font>页</td></tr></tfoot></table>';
    $("#query_goods_transport_grid_print").append(dataSourceHtml);
}