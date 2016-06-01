function preDoPrintOrderPick(orderNumber) {
    var LODOP = getLodop();
    LODOP.SET_PRINT_STYLEA(0, "Horient", 2); // 设置居中
    LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4");
    var linkHref = staticPath + "/css/print/detail-order-pick.css";
    var strStyleCSS = "<link href='" + linkHref + "' type='text/css' rel='stylesheet'>";
    var strFormHtml = strStyleCSS + "<body>" + document.getElementById("detail_order_pick").innerHTML + "</body>";
    LODOP.PRINT_INIT("打印拣货单");
    LODOP.ADD_PRINT_HTM(50, 50, 700, 1000, strFormHtml);
    var readyShipmentsSize = $("#ready_shipments_size").val();
    for (var i = 0; i < readyShipmentsSize; i++) {
        LODOP.NewPageA();
        LODOP.SET_PRINT_PAGESIZE(0, 0, 0, "A4");
        LODOP.ADD_PRINT_BARCODE(27, 511, 208, 61, "Code39", document.getElementById("orderNumber_" + i).innerHTML);
        LODOP.ADD_PRINT_HTM(63, 288, 193, 39, "销售单");
        var readyShipmentsHtml = strStyleCSS + "<body>" + document.getElementById("ready_shipments" + i).innerHTML + "</body>";
        LODOP.ADD_PRINT_HTM(105, 52, 700, 1000, readyShipmentsHtml);
    }
    LODOP.SET_PRINT_MODE("CATCH_PRINT_STATUS",true);
    var resultMsg = LODOP.PREVIEW();
    // LODOP.PRINT_DESIGN();
    // 修改打印状态
    /*if (LODOP.GET_VALUE("PRINT_STATUS_OK",resultMsg) == 0||LODOP.GET_VALUE("PRINT_STATUS_OK",resultMsg)==1) {
        $.get(createUrl("orderpick/updatestatus?orderNumber=" + orderNumber + "&status=1"), function(data) {
            if (!data.success) {
                siping.alert("打印状态修改失败,请重新打印");
            }
        });
    }*/
    $.get(createUrl("orderpick/updatestatus?orderNumber=" + orderNumber + "&status=1"), function(data) {
        if (!data.success) {
            siping.alert("打印状态修改失败,请重新打印");
        }
    });
}