function generateReceipt(consignee, inboundStorageId, startStation, endStation, transportationType) {
    var rowNumbers = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        rowNumbers[i] = checkbox[i].id;
    }
    var request = {};
    request.orderNumber = $("#orderNumber").text();
    request.rowNumbers = rowNumbers;
    request.consignee = consignee;
    request.inboundStorageId = inboundStorageId;
    request.startStation = startStation;
    request.endStation = endStation;
    request.transportationType = transportationType;
    siping.ajax({
        method : "post",
        url : 'readyreceipt/generateReceipt',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            if (data.success) {
                siping.alert(1, data.msg);
            } else {
                siping.alert(0, data.msg);
            }
        }
    });
}

// 弹出user选择框
function openWinGetConsignee() {
    var rowNumbers = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        rowNumbers[i] = checkbox[i].id;
    }
    if (rowNumbers != "") {
        siping.openWindow('/readyreceipt/openconsigneewin', '70%', '70%', '选择收货人');
    } else {
        siping.alert(0, "请选择商品");
    }
}
function selectAll() {
    if ($("#selectAll").prop("checked")) {
        $("[name='check_box']").not(':disabled').prop("checked", true);
    } else {
        $("[name='check_box']").prop("checked", false);
    }
}