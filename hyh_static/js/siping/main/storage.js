$(function() {
    $("#storage_area_smt_add").click(function() {
        if(checkNotNullByLableId("areaNo")&&checkNotNullByLableId("areaName"))
        {
            siping.ajax({
                method : 'post',
                url : '/storagearea/add',
                async : true,
                data : {
                    storageId :$("#storageId").val(),
                    areaNo : $("#areaNo").val(),
                    areaName : $("#areaName").val(),
                    remark : $("#remark").val()
                },
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        }
        
    });
    $("#storage_area_smt_edit").click(function(){
        if(checkNotNullByLableId("areaNo")&&checkNotNullByLableId("areaName")){
            siping.ajax({
                method : 'post',
                url : '/storagearea/edit',
                async : true,
                data : {
                    id : $("#id").val(),
                    areaNo : $("#areaNo").text(),
                    areaName : $("#areaName").val(),
                    remark : $("#remark").val()
                },
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        }
    });
    $("#storage_location_smt_edit").click(function() {
        if(checkNotNullByLableId("locationNo")&&checkNotNullByLableId("locationName")){
            siping.ajax({
                method : 'post',
                url : '/storagelocation/edit',
                async : true,
                data : {
                    id : $("#id").val(),
                    locationNo : $("#locationNo").val(),
                    locationName : $("#locationName").val(),
                    storageId : $("#storageId").val(),
                    storageAreaId :$("#storageAreaId").val(),
                    description : $("#description").val(),
                    pickOrder : $("#pickOrder").val()
                },
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        }
    });
    $("#storage_location_smt_add").click(function() {
        if(checkNotNullByLableId("locationNo")&&checkNotNullByLableId("locationName")){
            siping.ajax({
                method : 'post',
                url : '/storagelocation/add',
                async : true,
                data : {
                    storageId : $("#storageId").val(),
                    locationNo : $("#locationNo").val(),
                    locationName : $("#locationName").val(),
                    description : $("#description").val(),
                    storageAreaId :$("#storageAreaId").val(),
                    pickOrder : $("#pickOrder").val()
                },
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        }
    });
    $("#storage_smt_add").click(function() {
        siping.ajax({
            method : 'post',
            url : '/storage/add',
            async : true,
            data : {
                storageNo : $("#storageNo").val(),
                storageName : $("#storageName").val(),
                isEnableLocation : $("#isEnableLocation").val(),
                description : $("#description").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#selected_storage_for_inventory_check").change(function(){
        if($("#selected_storage_for_inventory_check").val()==''){
            siping.tips("必需项", "selected_storage_for_inventory_check");
            return false;
        }
        siping.ajax({
            method : 'get',
            url : '/inventorycheck/getinventorycheckbystorageidwithdrft',
            async : true,
            data : {
                storageId : $("#selected_storage_for_inventory_check").val()
            },
            success : function(data) {
                if (data.success) {
                   $("#layer_choose_storage").text("确定");
                   $("#layerChooseMaterialType").attr("disabled",false);
                } else {
                   $("#layer_choose_storage").text("编辑");//当时是为了一个仓库只能有一个盘点单，所以会出现这个验证
                   //$("#layerChooseMaterialType").attr("visibility","hidden");
                   $("#layerChooseMaterialType").attr("disabled","disabled");
                   //$("#layerChooseMaterialType").attr("'class", "grid_text grid_readonly_text my_radius")
                   $("#inventoryCheckId").val(data.msg);
                }
            }
        });
    });
 
    $("#storage_smt_edit").click(function() {
        siping.ajax({
            method : 'post',
            url : '/storage/edit',
            async : true,
            data : {
                id : $("#id").val(),
                storageNo : $("#storageNo").text(),
                storageName : $("#storageName").val(),
                description : $("#description").val(),
                isEnableLocation :$("#isEnableLocation").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#inventory_shipment_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td><td><input class="input_text_common my_radius grid_text" onkeyup="getSaleOrderSurProList(this)"><input id="id' + id
                        + '" type="hidden"></td>' + '<td><span id="materialName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></td>'
                        + '<td><span id="season' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="shops' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="batchNumber' + id + '" class="input_text_common my_radius grid_text"></td>'
                        + '<td><input id="productionDate' + id + '" data-type="date" class="input_text_common my_radius grid_text"></td>' + '<td><span id="unitName' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="counts' + id
                        + '" onkeyup="getInventoryTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + '<td><input id="purchasePrice' + id
                        + '" onkeyup="getInventoryTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + '<td><span id="totalPrice' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="isGift' + id + '" onclick="resetInventoryReceiptTotal(this);" type="checkbox"></td>'
                        + '<td><input id="remark' + id + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    $("#storage_receipt_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td><td><input class="input_text_common my_radius grid_text" onkeyup="getInventorySurProList(this)"><input id="id' + id
                        + '" type="hidden"></td>' + '<td><span id="materialName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></td>'
                        + '<td><span id="season' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="shops' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="batchNumber' + id + '" class="input_text_common my_radius grid_text"></td>'
                        + '<td><input id="productionDate' + id + '" data-type="date" class="input_text_common my_radius grid_text"></td>' + '<td><span id="unitName' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="counts' + id
                        + '" onkeyup="getInventoryTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + '<td><input id="purchasePrice' + id
                        + '" onkeyup="getInventoryTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + '<td><span id="totalPrice' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="isGift' + id + '" onclick="resetInventoryReceiptTotal(this);" type="checkbox"></td>'
                        + '<td><input id="remark' + id + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    $("#inventory_check_add_row").click(
            function() {
                var id = getInventoryMaxTrId();
                var img = '<img onclick="removeCheckGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '<input id="materialId' + id + '" type="hidden"></td><td><input id="materialNo' + id
                        + '" class="input_text_common my_radius grid_text" onkeyup="getInventoryCheckProList(this)"></td>' + '<td><span id="materialName' + id
                        + '"class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="barcode' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="batchNumber' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="productionDate' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="counts' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="checkCounts' + id
                        + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="calculatorInventoryCheckDifAndTotalPrice(this)"></td>' + '<td><span id="difference' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="price' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="totalAmount' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="remark' + id
                        + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    // 添加库存收货单
    $("#storage_receipt_smt").click(function() {
        var request = getInventoryReceiptData();
        request.isDraft = false;
        siping.ajax({
            method : "post",
            url : 'inventoryreceipt/add',
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
    });
    // 存为草稿
    $("#storage_receipt_draft_smt").click(function() {
        var request = getInventoryReceiptData();
        request.isDraft = true;
        siping.ajax({
            method : "post",
            url : 'inventoryreceipt/add',
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
    });
    if (window.location.href.indexOf('/inventorycheck/add') != -1) {
        siping.openWindow("/storage/layerallstorage", "300px", "200px", "");
    }
    $("#layer_choose_storage").click(function() {
        var id = $("#selected_storage_for_inventory_check").val();
        if (id == "") {
            siping.tips("必需项", "selected_storage_for_inventory_check");
            return;
        }
        if($("#layer_choose_storage").text()=="确定"){
            var storageName = $("#selected_storage_for_inventory_check option[value='" + id + "']").text();
            var storageAreaId = $("#layerChooseStorageArea").val();
            parent.$("#storage_id").val(id);
            parent.$("#storage_name").text(storageName);
            parent.$("#storage_area_id").val(storageAreaId);
            parent.$("#storage_area_name").text($("#layerChooseStorageArea option[value='" + storageAreaId + "']").text());
            parent.siping.close(layerIndex);
        }
        else if($("#layer_choose_storage").text()=="编辑"){
            parent.window.location.href = "/hyh_intranet/inventorycheck/edit?checkNumber="+$("#inventoryCheckId").val();
        }
    });
    // 添加库存盘点单
    $("#inventory_check_smt").click(function() {
        var request = getInventoryCheckData(this);
        if(request.items.length==0){
            siping.tips("没有盘点条目", "dataSource");
            return;
        }
        if (request.billsDate == "" || request.billsDate == null){
            siping.tips("必需项", "billsDate");
            return;
        }
        if(request.storageId == ""||request.storageId == null){
            siping.tips("必需项","storage_name");
            return;
        }
        if(request.operatorId == ""||request.operatorId == null){
            siping.tips("必需项","nickName");
            return;
        }
        loadingIndex = layer.load(2, {
            time : 5 * 1000
        });
        siping.ajax({
            method : "post",
            url : 'inventorycheck/addnew',
            data : JSON.stringify(request),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    layer.close(loadingIndex);
                    siping.alert(1, data.msg);
                } else {
                    layer.close(loadingIndex);
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#pda_inventory_check_smt_add").click(function() {
        var request = getPDAInventoryCheckData(this);
        if(request.storageId == ""||request.storageId == null){
            siping.tips("必需项","selected_storage_for_inventory_check");
            return;
        }
        if(request.operatorId == ""||request.operatorId == null){
            siping.tips("必需项","nickName");
            return;
        }
        loadingIndex = layer.load(2, {
            time : 5 * 1000
        });
        siping.ajax({
            method : "post",
            url : 'pdainventorycheck/addnew',
            data : JSON.stringify(request),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    layer.close(loadingIndex);
                    siping.alert(1, data.msg);
                } else {
                    layer.close(loadingIndex);
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    // 库存盘点单存为草稿
//    $("#inventory_check_draft_smt").click(function() {
//        var request = getInventoryCheckData(this);
//        if(true){//存为草稿无需验证
//            if (request.billsDate == "" || request.billsDate == null) {
//                siping.tips("必需项", "billsDate");
//                return;
//            }
//            if(request.checkStorage == ""||request.checkStorage ==null){
//                siping.tips("必需项","storage_name");
//                return;
//            }
//            request.isDraft = true;
//            siping.ajax({
//                method : "post",
//                url : 'inventorycheck/add',
//                data : JSON.stringify(request),
//                dataType : "json",
//                contentType : "application/json",
//                success : function(data) {
//                    if (data.success) {
//                        siping.alert(1, data.msg);
//                    } else {
//                        siping.alert(0, data.msg);
//                    }
//                }
//            });
//        }
//    });
    $("#inventory_check_edit_smt").click(function() {
        loadingIndex = layer.load(2, {
            time : 5 * 1000
        });
        var request = getInventoryCheckData(this);
        siping.confirm('确定提交吗？提交后不可修改!', function() {
            siping.ajax({
                method : "post",
                url : 'inventorycheck/edit',
                data : JSON.stringify(request),
                dataType : "json",
                contentType : "application/json",
                success : function(data) {
                    if (data.success) {
                        layer.close(loadingIndex);
                        siping.alert(1, data.msg);
                        setTimeout(function() {
                            window.location.href="/hyh_intranet/inventorycheck/getlist";},2000);
                    } else {
                        layer.close(loadingIndex);
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    });
//    $("#inventory_check_edit_draft_smt").click(function() {
//        var request = getInventoryCheckData(this);
//        request.isDraft = true;
//        siping.ajax({
//            method : "post",
//            url : 'inventorycheck/edit',
//            data : JSON.stringify(request),
//            dataType : "json",
//            contentType : "application/json",
//            success : function(data) {
//                if (data.success) {
//                    siping.alert(1, data.msg);
//                } else {
//                    siping.alert(0, data.msg);
//                }
//            }
//        });
//    });

    $("#storage_delivery_smt").click(function() {
        var request = getInventoryShipmentsData();
        request.isDraft = false;
        siping.ajax({
            method : "post",
            url : 'inventoryshipments/add',
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
    });
    $("#storage_delivery_edit_smt").click(function() {
        var request = getInventoryShipmentsData();
        request.isDraft = false;
        siping.ajax({
            method : "post",
            url : 'inventoryshipments/edit',
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
    });
    $("#storage_delivery_draft_smt").click(function() {
        var request = getInventoryShipmentsData();
        request.isDraft = true;
        siping.ajax({
            method : "post",
            url : 'inventoryshipments/add',
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
    });
});
function checkAddInventoryCheckRequestData(data) {
    for (var i = 0; i < data.length; i++) {
        if (data[i].materialId != "" && data[i].materialId != null && (data[i].actualAmount == "" || data[i].actualAmount == null)) {
//            confirm('您的商品货号为' + getValueByIdForDifLabel("materialNo" + data[i].id) + '的商品未输入盘点数量,需要把盘点数量置为零然后保存盘点单吗？', function() {
//                alert("我是里面的");
//            });
            siping.tips("此处请输入盘点数量", "checkCounts" + data[i].id);
            $("#checkCounts" + data[i].id).focus();
            return false;
            break;
        }
        if(i== data.length-1){
            return true;
        }
    }
}
function setCheckCountsToZero(id){
    
}
function addInventoryCheckItemRow() {
    var id = getInventoryMaxTrId();
    id++;
    var row = '<tr id="' + id + '" class="grid_row">' + '<td><img onclick="removeCheckGridRowData(this)" src="'+staticPath+'/icons/tree_show.png"></td>'
            + '<td><input id="materialId' + id + '" type="hidden"><input id="materialNo' + id + '" class="input_text_common my_radius grid_text" onkeyup="getInventoryCheckProList(this)"></td>'
            + '<td><span id="materialName' + id + '"class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id
            + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="barcode' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
            + '<td><span id="batchNumber' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="productionDate' + id
            + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
            + '<td><span id="counts' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="checkCounts' + id
            + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="calculatorInventoryCheckDifAndTotalPrice(this)"></td>' + '<td><span id="difference' + id
            + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="price' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
            + '<td><span id="totalAmount' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="remark' + id
            + '" class="input_text_common my_radius grid_text"></td></tr>';
    if(id==1){
        $("#0").before(row);//列表中只有这一行的时候需要单独的处理，因为找不到prev();
    }
    else{
        $("#0").prev().after(row);
    }
    $("#materialNo" + id).focus();
    siping.eventBinding($("#" + id + " *"));
}
function deleteStorage() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    siping.confirm('确定删除吗？', function() {
        siping.ajax({
            method : 'get',
            url : '/storage/delete?id=' + ids,
            async : true,
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
}
function deleteInventoryCheck() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    if (checkbox.length != 0) {
        for (var i = 0; i < checkbox.length; i++) {
            ids[i] = checkbox[i].id;
        }
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/inventorycheck/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    } else {
        siping.alert(0, "请选择需要删除的盘点单");
    }

}
function getInventoryCheckProList(dom) {
    var idArray = [ "materialId", "materialNo", "materialName", "specificationsModel", "barcode", "batchNumber", "productionDate", "unitName", "counts", "price" ];
    var rowId = $(dom).parent().parent().attr("id");
    if (rowId == 0) {
        rowId = getInventoryMaxTrId();
    }
    var storageId = $("#storage_id").val();
    getInventoryCheckSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId, [ storageId, "inventorycheck/getmaterialbatchnumberlist" ]);
}
function getInventorySurProList(dom) {
    var idArray = [ "id", "materialNo", "materialName", "specificationsModel", "barcode", "brand", "season", "shops", "batchNumber", "productionDate", "unitName" ];
    var rowId = $(dom).parent().parent().attr("id");
    siping.getSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
}
function getInventoryShipmentsProList(dom) {
    var idArray = [ "materialId", "materialNo", "materialName", "specificationsModel", "brand", "season", "shops", "batchNumber", "productionDate", "unitName", "counts", "price", "totalPrice",
            "isGift", "remark" ];
    var rowId = $(dom).parent().parent().attr("id");
    if (rowId == 0) {
        var trs = $("#inventoryCheckItemDataSource").find("tr");
        var max = 0;
        for (var i = 0; i < trs.length; i++) {
            var m = $(trs[i]).attr("id");
            if (parseInt(m) > parseInt(max)) {
                max = m;
            }
        }
        rowId = max;
    }
    var storageId = $("#inboundStorageId").val();
    getInventoryShipmentsSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId, [ storageId, "inventorycheck/getmaterialbatchnumberlist" ]);
}
function getInventoryShipmentsSelect(dom, code, name, url, idArray, rowid, checkBatch) {
    var v = dom.value;
    siping.ajax({
        method : 'get',
        url : url,
        async : true,
        dataType : "json",
        data : {
            keyword : v
        },
        success : function(data) {
            var _left = $(dom).offset().left;
            var _top = $(dom).offset().top;
            var _width = $(dom).height();
            var list = '<div class="li_div my_radius">';
            for (var i = 0; i < data.length; i++) {
                list += '<div class="li_span"><span class="li_code">' + data[i][code] + '</span>-<span>' + data[i][name] + '</span></div>'
            }
            list += '</div>';
            $(".li_div").remove();
            $(dom).after(list);
            $(".li_div").css({
                "left" : _left,
                "top" : _top + _width + 3
            });
            $(".li_div").delegate(
                    ".li_span",
                    "click",
                    function(e) {
                        var licode = $(this).children(".li_code").text();
                        var rowData = null;
                        $(dom).val(licode);
                        $(".li_div").remove();
                        $("tr[data-parentid=" + rowid + "]").remove();
                        for (var i = 0; i < data.length; i++) {
                            if (data[i][code] == licode) {
                                rowData = data[i];
                                setCurrentInventoryShipmentsValue(rowData, idArray, rowid)
                                break;
                            }
                        }
                        siping.ajax({
                            method : "GET",
                            url : "inventorycheck/getmaterialbatchnumberlist",
                            data : {
                                materialId : rowData.id,
                                storageId : $("#inboundStorageId").val()
                            },
                            dataType : "json",
                            success : function(data) {
                                if (data.length <= 1) {
                                    if (data.length == 1) {
                                        $("#batchNumber" + rowid).val = data.batchNumber;
                                        $("#productionDate" + rowid).val = data.productionDate;
                                    }
                                } else {
                                    var row = '<table id="dso_batch_number">';
                                    for (var i = 0; i < data.length; i++) {
                                        row += '<tr ondblclick="chooseSaleDate(this);"><td width="16%">' + data[i].batchNumber + '</td><td width="16%">' + data[i].productionDate
                                                + '</td><td width="16%">' + data[i].expirationDate + '</td></tr>';
                                    }
                                    row += '</table>';
                                    $("#hide_delivery_sale_order").html(row);
                                    siping.openWindow('/material/getmaterialbatchnumberlist?rowId=' + rowid, '50%', '350px', "选择批次号(双击选择)");
                                }
                            }
                        });
                    });
        }
    });
}

function setCurrentInventoryShipmentsValue(data, idArray, rowid) {
    $("#materialId" + rowid).val(data.id);
    $("#materialNo" + rowid).val(data.materialNo);
    $("#materialName" + rowid).text(data.materialName);
    $("#specificationsModel" + rowid).text(data.specificationsModel);
    $("#brand" + rowid).text(data.brand);
    $("#season" + rowid).text(data.season);
    $("#shops" + rowid).text(data.shops);
    $("#unitName" + rowid).text(data.unitName);
    $("#counts" + rowid).val(data.counts);
    $("#price" + rowid).val(data.price);
}
function getInventoryMaxTrId() {
    var trs = $("#inventoryCheckItemDataSource").find("tr");
    var max = 0;
    for (var i = 0; i < trs.length; i++) {
        var m = $(trs[i]).attr("id");
        if (parseInt(m) > parseInt(max)) {
            max = m;
        }
    }
    return max;
}

function priceEmpty(dom) {
    var rowId = $(dom).parent().parent().attr("id");
    $("#price" + rowId).val(0);
    $("#totalPrice" + rowId).html(0);
    getInventoryTotal(dom);
}
function getInventoryReceiptData() {
    var request = {};
    request.billsDate = $("#billsDate").val();
    request.inboundStorageId = $("#inboundStorageId").val();
    request.summary = $("#summary").val();
    request.totalPrice = $("#totalPrice").text();
    request.giftPrice = $("#totalGiftPrice").text();
    request.auditor = $("#auditor").val();
    request.owner = $("#loginId").val();
    // 表体数据
    var trs = $("#dataSource").find("tr");
    var items = [];
    for (var i = 0; i < trs.length; i++) {
        var id = $(trs[i]).attr("id");
        if (id != 0) {
            var item = {};
            item.materialId = $("#id" + id).text(); // TODO隐藏框获取
            item.batchNumber = $("#batchNumber" + id).val();
            item.productionDate = $("#productionDate" + id).val();
            item.counts = $("#counts" + id).val();
            item.purchasePrice = $("#purchasePrice" + id).val();
            item.remark = $("#remark" + id).val();
            item.total = $("#total" + id).text();
            items.push(item);
        }
    }
    request.inventoryReceiptItemArraysRequest = items;
    return request;
}
function getInventoryCheckData(buttonDom) {
    var request = {};
    var addOrEditFlag;
    if($(buttonDom).attr("id")=="inventory_check_edit_smt"){//按钮不同，调用不同的收集数据的页面
        addOrEditFlag = "edit";
    }
    else if($(buttonDom).attr("id")=="inventory_check_smt"){
        addOrEditFlag = "add";
    }
    else{siping.alert(0,"出现错误");return false;}
    if ($("#checkNumber").text() != '') {
        request.checkNumber = $("#checkNumber").text();
    }
    request.storageId = $("#storage_id").val();
    request.storageAreaId = $("#storage_area_id").val();
    request.billsDate = getValueByIdForDifLabel("billsDate")//$("#billsDate").val();val和text公共的处理
    request.operatorId = $("#userId").val();
    request.summary = $("#summary").val();
    request.owner = $("#ownerId").val();
    // 循环注入商品信息
    var dom = $("#dataSource");
    var items = [];
    if(addOrEditFlag=="add"){
        $(dom).children("tr").each(function(){
            if($(this).find("input[name='materialId']").val() != ""){
                var item = {};
                item.materialId = $(this).find("input[name='materialId']").val();
                item.materialNo = $(this).find("span[name='materialNo']").text();
                item.barcode = $(this).find("span[name='barcode']").text();
                item.materialName = $(this).find("span[name='materialName']").text();
                item.materialTypeName = $(this).find("span[name='materialTypeName']").text();
                item.unitName = $(this).find("span[name='unitName']").text();
                item.batchNumber = $(this).find("span[name='batchNumber']").text();
                item.locationNo = $(this).find("span[name='locationNo']").text();
                item.inventoryNumber = $(this).find("span[name='inventoryNumber']").text();//这里会有val()和text()的处理
                item.diffNumber = $(this).find("span[name='diffNumber']").text();
                item.actualNumber = $(this).find("span[name='actualNumber']").text();
                item.productionDate = $(this).find("span[name='productionDate']").text();
                item.expirationDate = $(this).find("span[name='expirationDate']").text();
                items.push(item);
            }
        });
    }
    else if(addOrEditFlag=="edit"){
        $(dom).children("tr").each(function(){
            if($(this).find("input[name='materialId']").val() != ""){
                var item = {};
                item.materialId = $(this).find("input[name='materialId']").val();
                item.materialNo = $(this).find("span[name='materialNo']").text();
                item.barcode = $(this).find("span[name='barcode']").text();
                item.materialName = $(this).find("span[name='materialName']").text();
                item.materialTypeName = $(this).find("span[name='materialTypeName']").text();
                item.unitName = $(this).find("span[name='unitName']").text();
                item.batchNumber = $(this).find("span[name='batchNumber']").text();
                item.locationNo = $(this).find("span[name='locationNo']").text();
                item.inventoryNumber = $(this).find("span[name='inventoryNumber']").text();//这里会有val()和text()的处理
                item.diffNumber = $(this).find("span[name='diffNumber']").text();
                item.actualNumber = $(this).find("input[name='actualNumber']").val();
                item.productionDate = $(this).find("span[name='productionDate']").text();
                item.expirationDate = $(this).find("span[name='expirationDate']").text();
                items.push(item);
            }
        });
    }
    else{siping.alert(0,"出现错误");return false}
    request.items = items;
    return request;
}
// 打印库存收货单
function printInventoryReceiptData() {
    $(".inventoryReceiptData").printArea();
}
function getInventoryCheckSelect(dom, code, name, url, idArray, rowid, checkBatch) {
    var v = dom.value;
    siping.ajax({
        method : 'get',
        url : url,
        async : true,
        dataType : "json",
        data : {
            keyword : v
        },
        success : function(data) {
            var _left = $(dom).offset().left;
            var _top = $(dom).offset().top;
            var _width = $(dom).height();
            var list = '<div class="li_div my_radius">';
            for (var i = 0; i < data.length; i++) {
                list += '<div class="li_span"><span class="li_code">' + data[i][code] + '</span>-<span>' + data[i][name] + '</span></div>'
            }
            list += '</div>';
            $(".li_div").remove();
            $(dom).after(list);
            $(".li_div").css({
                "left" : _left,
                "top" : _top + _width + 3
            });
            $(".li_div").delegate(".li_span", "click", function(e) {
                var licode = $(this).children(".li_code").text();
                var rowData = null;
                $(dom).val(licode);
                $(".li_div").remove();
                $("tr[data-parentid=" + rowid + "]").remove();
                for (var i = 0; i < data.length; i++) {
                    if (data[i][code] == licode) {
                        rowData = data[i];
                        break;
                    }
                }
                checkMaterialIdRepeat(rowData.id, checkBatch, idArray, rowid, rowData);
            });
        }
    });
}
function checkMaterialIdRepeat(materialId, checkBatch, idArray, rowid, rowData) {
    var trs = $("#inventoryCheckItemDataSource").find("tr");
    var i = 0;
    for (i; i < trs.length; i++) {
        if (materialId == $("#materialId" + $(trs[i]).attr("id")).val()) {
            siping.confirm('该物料已经添加过盘点单中,是否继续添加', function() {
                if (checkBatch != undefined && typeof checkBatch == 'object') {
                    getInventoryCheckBatch(checkBatch, rowid, idArray, rowData);
                }
            });
            break;
        }
        if (i == trs.length - 1) {
            if (checkBatch != undefined && typeof checkBatch == 'object') {
                getInventoryCheckBatch(checkBatch, rowid, idArray, rowData);
            }
        }
    }
}
function getInventoryCheckBatch(checkBatch, rowid, idArray, rowData) {
    var storageId = checkBatch[0];
    var url = checkBatch[1];
    var lastRowId = getInventoryMaxTrId();
    if (storageId && url && lastRowId) {
        siping.ajax({
            method : 'get',
            url : url,
            async : true,
            dataType : "json",
            data : {
                materialId : rowData.id,
                storageId : storageId
            },
            success : function(data) {
                var row = "";
                var id = parseInt(lastRowId) + 1;
                for (var i = 0; i < data.length; i++) {
                    if (i == 0) {
                        for (var j = 0; j < idArray.length; j++) {
                            var idname = idArray[j];
                            var tagName = $("#" + idname + rowid)[0].tagName;
                            if (tagName == "INPUT") {
                                $("#" + idname + rowid).val(data[i][idname]);
                            } else {
                                $("#" + idname + rowid).text(data[i][idname]);
                            }
                        }
                    } else {
                        var img = '<img onclick="removeCheckGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                        row = '<tr id="' + id + '" data-parentid="' + rowid + '" class="grid_row"><td>' + img + '<input id="materialId' + id + '" type="hidden" value="' + data[i].materialId
                                + '"></td>' + '<td><span style="text-indent: 5px;" id="materialNo' + id + '"class="grid_text grid_readonly_text my_radius">' + data[i].materialNo + '</span></td>'
                                + '<td><span id="materialName' + id + '"class="grid_text grid_readonly_text my_radius">' + data[i].materialName + '</span></td>' + '<td><span id="specificationsModel'
                                + id + '" class="grid_text grid_readonly_text my_radius">' + data[i].specificationsModel + '</span></td>' + '<td><span id="barcode' + id
                                + '" class="grid_text grid_readonly_text my_radius">' + data[i].barcode + '</span></td>' + '<td><span id="batchNumber' + id
                                + '" class="grid_text grid_readonly_text my_radius">' + data[i].batchNumber + '</span></td>' + '<td><span id="productionDate' + id
                                + '" class="grid_text grid_readonly_text my_radius">' + data[i].productionDate + '</span></td>' + '<td><span id="unitName' + id
                                + '" class="grid_text grid_readonly_text my_radius">' + data[i].unitName + '</span></td>' + '<td><span id="counts' + id
                                + '" class="grid_text grid_readonly_text my_radius">' + data[i].counts + '</span></td>' + '<td><input id="checkCounts' + id
                                + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="calculatorInventoryCheckDifAndTotalPrice(this)"></td>' + '<td><span id="difference'
                                + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="price' + id + '" class="grid_text grid_readonly_text my_radius">'
                                + data[i].price + '</span></td>' + '<td><span id="totalAmount' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="remark' + id
                                + '" class="input_text_common my_radius grid_text"></td></tr>';
                        $("#" + rowid).after(row);
                        id++;
                        siping.eventBinding($("#" + id + " *"));
                    }
                }
            }
        });
    }
}
function removeCheckGridRowData(dom) {
    $(dom).parent().parent().remove();
    $("tr[data-parentid=" + $(dom).parent().parent().attr("id") + "]").remove();
}

function deleteDraftInventoryReceipt() {
    var receiptNumbers = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        receiptNumbers[i] = checkbox[i].value;
    }
    if (receiptNumbers != "") {
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/inventoryreceipt/delete?receiptNumbers=' + receiptNumbers,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/inventoryreceipt/getdraftlist");
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    } else {
        siping.alert(0, "请选择要删除的列！");
    }
}
function calculatorInventoryCheckDifAndTotalPrice(dom) {
    var counts = $(dom).parent().prev().text();//仓库数量
    var price = $(dom).parent().next().next().children().text();//价格
    var checkCounts = $(dom).val();//盘点数量
    if ((/^\d+(\.\d+)?$/.test(checkCounts)) && (/^\d+(\.\d+)?$/.test(counts)) && (/^\d+(\.\d+)?$/.test(price))) {
        var totalPrice = (checkCounts - counts) * price
        $(dom).parent().next().children().text(checkCounts - counts);
        $(dom).parent().next().next().next().children().text(totalPrice);
    } else {
        alert("请确保输入的数据格式正确");
    }

}

function getInventoryShipmentsData() {
    var request = {};
    request.outboundStorage = $("#inboundStorageId").val();
    request.billsDate = $("#billsDate").val();
    request.summary = $("#summary").val();
    request.owner = $("#loginId").val();
    request.totalPrice = $("#totalPrice").text();
    request.giftPrice = $("#giftPrice").text();
    // 表体数据
    var trs = $("#dataSource").find("tr");
    var items = [];
    for (var i = 0; i < trs.length; i++) {
        var id = $(trs[i]).attr("id");
        if (id != 0) {
            var item = {};
            item.materialId = getValueByIdForDifLabel("id" + id);
            item.batchNumber = getValueByIdForDifLabel("batchNumber" + id);
            item.productionDate = getValueByIdForDifLabel("productionDate" + id);
            item.counts = getValueByIdForDifLabel("counts" + id);
            item.sellPrice = getValueByIdForDifLabel("sellPrice" + id);
            var counts = getValueByIdForDifLabel("counts" + id);
            var price = getValueByIdForDifLabel("sellPrice" + id);
            if (!isNaN(counts) && !isNaN(price)) {
                var totalPrice = counts * price;
                item.total = totalPrice;
            }
            item.isGift = $("#isGift" + id)[0].checked;
            item.remark = getValueByIdForDifLabel("remark" + id);
            items.push(item);
        }
    }
    request.shipmentsItemList = items;
    return request;
}
var deliveryIndex = parent.layer.getFrameIndex(window.name);
function chooseSaleDate(dom) {
    var batchNumber = dom.firstChild.innerText;
    var productionDate = dom.firstChild.nextSibling.innerText;
    var rowId = $("#rowId").val();
    parent.$('#batchNumber' + rowId).text(batchNumber);
    parent.$('#productionDate' + rowId).text(productionDate);
    parent.siping.close(deliveryIndex);
}
// 添加库存收货单项
function chooseStorageReceiptMaterial(){
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
            for(var i=0;i<materialList.length;i++){
                var material = materialList[i];
                var id = dom.find("tr:last-child").attr("id");
                if(typeof(id)=='undefined'){
                    id =0;
                }
                id++;
                var materialHtml = getMaterialHtml(material,id);
                var row = materialHtml;
                if(material.isBatch == "true"){
                    row +=  '<td><input id="batchNumber' + id + '" class="input_text_common my_radius grid_text"></td>' + 
                            '<td><input id="productionDate' + id + '" data-type="date" class="input_text_common my_radius grid_text"></td>';
                }else{
                    row +=  '<td><input id="batchNumber' + id + '" readonly class="grid_text grid_readonly_text my_radius"></td>' + 
                    '<td><input id="productionDate' + id + '" readonly class="grid_text grid_readonly_text my_radius"></td>';
                } 
                row +=
                '<td><input id="counts' + id + '" onkeyup="getInventoryTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                '<td><input id="purchasePrice' + id + '" onkeyup="getInventoryTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                '<td><span id="total' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                '<td><input id="isGift' + id + '" onclick="resetInventoryTotal(this);" type="checkbox"></td>' + 
                '<td><input id="remark' + id + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(dom).append(row);
                parent.siping.eventBinding(parent.$("#"+id + " *"));
            }
            // 清除保存在全局变量中的materialList
            parent.materialList = [];
            parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择物料");
    }
}
function chooseInventoryDeliveryMaterial(){
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
            for(var i=0;i<materialList.length;i++){
                var material = materialList[i];
                var id = dom.find("tr:last-child").attr("id");
                if(typeof(id)=='undefined'){
                    id =0;
                }
                id++;
                var materialHtml = getMaterialHtml(material,id);
                var row = materialHtml;
                if(material.isBatch == "true"){
                    row +=  '<td><input id="batchNumber' + id + '" onclick = "getBatchList(this);" class="input_text_common my_radius grid_text"></td>' + 
                            '<td><span id="productionDate' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>';
                }else{
                    row +=  '<td><input id="batchNumber' + id + '" readonly class="grid_text grid_readonly_text my_radius"></td>' + 
                    '<td><span id="productionDate' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>';
                } 
                row +=
                    '<td><input id="counts' + id + '" onkeyup="getInventoryTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                    '<td><input id="sellPrice' + id+ '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getInventoryTotal(this)"></td>' + 
                    '<td><span id="total' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                    '<td><input id="isGift' + id + '" onclick="resetInventoryTotal(this);" type="checkbox"></td>'+ 
                    '<td><input id="remark' + id + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(dom).append(row);
                parent.siping.eventBinding(parent.$("#"+id + " *"));
            }
            // 清除保存在全局变量中的materialList
            parent.materialList = [];
            parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择物料");
    }
}
function openWinAddStorageLocation(id){
    siping.openWindow('/storagelocation/add?id='+id,'900px','450px',"添加库位");
}
function openWinAllStorageLocation(id){
    siping.openWindow('/storagelocation/getlist?id='+id,'900px','500px',"库位列表");
}
function openWinAddStorageArea(id){
    siping.openWindow('/storagearea/add?id='+id,'900px','450px',"添加库区");
}
function openWinAllStorageArea(id){
    siping.openWindow('/storagearea/getlist?id='+id,'900px','450px',"库区列表");
}
function deleteStorageLocation(locationId){
    var ids = [];
    ids.push(locationId);
    if(ids!=""){
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/storageLocation/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        //siping.redirect("/businesspartner/bank/getlist?id="+partnerId);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择要删除的列！");
    }
}
function deleteStorageArea(){
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if(ids!=""){
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/storagearea/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择要删除的列！");
    }
}

function getInveotryReciptList(){
//    siping.ajax({
//        method : 'post',
//        url : '/pda/inventoryreceipt/getlist',
//        async : true,
//        data : {
//            pageNo : 1,
//            pageSize : 10,
//            userid : 22,
//            status : 0
//        },
//        success : function(data) {
//            if (data.success) {
//                siping.alert(1, data.msg);
//            } else {
//                siping.alert(0, data.msg);
//            }
//        }
//    });
    siping.ajax({
      method : 'get',
      url : '/pda/inventoryreceipt/get?receiptNumber=MI1',
      async : true,
      success : function(data) {
          if (data.success) {
              siping.alert(1, data.msg);
          } else {
              siping.alert(0, data.msg);
          }
      }
  });
}

function checkOpenWinGetMaterial(){
    if($("#storage_id").val()==''){
        siping.openWindow("/storage/layerallstorage", "300px", "200px", "");
    }
    else{
        openWinGetMaterials('chooseInventoryCheckMaterial',true,true,true,this);
    }
}

function chooseInventoryCheckMaterial(rowId){
    var materialList = parent.materialList;
    var ids = [];
    for(var i=0;i<materialList.length;i++){
        ids[i] = materialList[i].id;
    }
    var request = {};
    request.ids = ids;
    request.storageId = parent.document.getElementById("storage_id").value;
    request.storageAreaId = parent.document.getElementById("storage_area_id").value;
    var materialIds = [];
    parent.$("#dataSource tr").each(function(){
        var materialId = $(this).find("input[name = 'materialId']").val();
        if(materialId != ""){
            materialIds.push(materialId);
        }
    });
    //uniqueArray(materialIds);
    if (ids != "") {
        //siping.confirm('确定盘点这些商品吗？', function() {
        var flag = true;
        if(ids.length==1){
            for(var i=0;i<materialIds.length;i++){
                if(ids[0]==materialIds[i]){
                    flag = false;
                    break;
                }
            }
            if(flag==false){
                siping.alert(0,"请勿重复选择商品盘点");
            }
        }
        else if(ids.length>1){
            var newIds = [];
            for(var j=0;j<ids.length;j++){//找出不重复的
                var arrayFlag = true;
                for(var i=0;i<materialIds.length;i++){
                    if(ids[j]==materialIds[i]){
                        arrayFlag = false;
                        break;
                    }
                }
                if(arrayFlag == true){
                    newIds.push(ids[j]);
                }
            }
            if(newIds.length == 0){//验证
                siping.alert(0,"所选商品全部已经选择过");
                flag = false;
            }
            else{
                request.ids = newIds;//重复不全，直接过滤
            }
        }
        if(flag){
            loadingIndex = layer.load(2, {
                time : 5 * 1000
            });
            siping.ajax({
                method : "post",
                url : '/material/getInventoryCheckInfoWithStorageIdWithStorageArea',
                data : JSON.stringify(request),
                dataType : "json",
                contentType : "application/json",
                success : function(data) {
                    if(!data){
                        siping.alert(0,"所选商品无盘点内容,或者获取盘点内容错误");
                    }
                    else{
                        var checkItemHtml = '';
                        for(var i=0;i<data.length;i++){
//                            checkItemHtml += '<tr><td><img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png"></td>';//注释的和下面的样式不一样
//                            checkItemHtml += '<td name="materialNo"><input name="materialId" type="hidden" value="'+ data[i].materialId +'"><span name="materialName" class="grid_text grid_readonly_text my_radius">'+data[i].materialNo+'</span></td>';
//                            checkItemHtml += '<td name="barcode">'+data[i].barcode+'</span></td>';
//                            checkItemHtml += '<td name="materialName">'+data[i].materialName+'</span></td>';
//                            checkItemHtml += '<td name="materialTypeName">'+data[i].materialType+'</span></td>';
//                            checkItemHtml += '<td name="unitrName">'+data[i].unitName+'</span></td>';
//                            checkItemHtml += '<td name="locationNo">'+data[i].locationNo+'</span></td>';
//                            checkItemHtml += '<td name="batchNumber">'+data[i].batchNumber+'</span></td>';
//                            checkItemHtml += '<td name="productionDate">'+data[i].productionDate+'</span></td>';
//                            checkItemHtml += '<td name="expirationDate">'+data[i].expirationDate+'</span></td>';
//                            checkItemHtml += '<td name="inventoryNumber">'+data[i].inventoryNumber+'</span></td>';
//                            checkItemHtml += '<td name="actualNumber">'+data[i].actualNumber+'</td>';
//                            checkItemHtml += '<td name="actualNumber">'+data[i].diffNumber+'</td></tr>';
                            checkItemHtml += '<tr><td><img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png"></td>';
                            checkItemHtml += '<td><input name="materialId" type="hidden" value="'+ data[i].materialId +'"><span name="materialNo" class="grid_text grid_readonly_text my_radius">'+data[i].materialNo+'</span></td>';
                            checkItemHtml += '<td><span name="barcode" class="grid_text grid_readonly_text my_radius">'+data[i].barcode+'</span></td>';
                            checkItemHtml += '<td><span name="materialName" class="grid_text grid_readonly_text my_radius">'+data[i].materialName+'</span></td>';
                            checkItemHtml += '<td><span name="materialTypeName" class="grid_text grid_readonly_text my_radius">'+data[i].materialType+'</span></td>';
                            checkItemHtml += '<td><span name="unitName" class="grid_text grid_readonly_text my_radius">'+data[i].unitName+'</span></td>';
                            checkItemHtml += '<td><span name="locationNo" class="grid_text grid_readonly_text my_radius">'+data[i].locationNo+'</span></td>';
                            checkItemHtml += '<td><span name="batchNumber" class="grid_text grid_readonly_text my_radius">'+data[i].batchNumber+'</span></td>';
                            checkItemHtml += '<td><span name="productionDate" class="grid_text grid_readonly_text my_radius">'+data[i].productionDate+'</span></td>';
                            checkItemHtml += '<td><span name="expirationDate" class="grid_text grid_readonly_text my_radius">'+data[i].expirationDate+'</span></td>';
                            checkItemHtml += '<td><span name="inventoryNumber" class="grid_text grid_readonly_text my_radius">'+data[i].inventoryNumber.toFixed(2)+'</span></td>';
                            checkItemHtml += '<td><span name="actualNumber"  class="grid_text grid_readonly_text my_radius">'+data[i].inventoryNumber.toFixed(2)+'</span></td>';
                            checkItemHtml += '<td><span name="diffNumber" class="grid_text grid_readonly_text my_radius">0.00</td></tr>';
                        }
                        parent.$("#dataSource").append(checkItemHtml);
                        layer.close(loadingIndex);
                        parent.materialList = [];
                        parent.siping.close(partnerIndex);
                     }
                        
                }
            });
        }
       // });
    } else {
        siping.alert(0, "请选择要盘点的商品！");
    }
    //parent.siping.close(partnerIndex);
}
function getStorageArea(){
    if($("#selected_storage_for_inventory_check").val()!=""&&$("#selected_storage_for_inventory_check").val()!="0"){
    siping.ajax({
                    method : 'get',
                    url : '/storagearea/getlistbyasync',
                    async : true,
                    data :{
                    storageId : $("#selected_storage_for_inventory_check").val()
                    },
                    success : function(data) {
                        var html = '<option value=""></option>';
                        for(var i=0;i<data.length;i++){
                            html = html + '<option value="'+data[i].id+'">'+ data[i].areaName+'</option>';
                        }
                        $("#layerChooseStorageArea").html(html);
                        $("#layerChooseStorageArea").attr("disabled",false);
                    },
                    error : function(){
                         siping.alert(0,"获取数据出现错误");
                    }
                 });
    }
    else{
        $("#layerChooseStorageArea").html('<option value=""></option>');
        $("#layerChooseStorageArea").val("");
        $("#layerChooseStorageArea").attr("disabled","disabled");
    }
}
function getPDAInventoryCheckData(dom){
    var request = {};
    request.checkNumber = $("#checkNumber").text();
    request.storageId = $("#selected_storage_for_inventory_check").val();
    request.storageAreaId = $("#layerChooseStorageArea").val()
    request.operatorId =  $("#userId").val();
    return request;
}
//function getSelectMaterialByInventoryCheck(dom,code,name,url,idArray,rowid){
//var v = dom.value;
//var rowDom = $(dom).parent().parent().parent(); //tr
////var singleTr = '<tr><td><img onclick="addSaleOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>';
////singleTr = singleTr + '<td><span name="materialNo">商品货号</span></td>';
////singleTr = singleTr + '<td><span name="barcode">国际编码</span></td>';
////singleTr = singleTr + '<td><span name="materialName">商品名称</span></td>';
////singleTr = singleTr + '<td><span name="unitName">计量单位</span></td>';
////singleTr = singleTr + '<td><span name="materialNo">库存编码</span></td>';
////singleTr = singleTr + '<td><span name="batchNumber">批次号</span></td>';
////singleTr = singleTr + '<td><span name="productionDate">生产日期</span></td>';
////singleTr = singleTr + '<td><span name="expirationDate">到期日期</span></td>';
////singleTr = singleTr + '<td><span name="inventoryNumber">库存数量</span></td>';
////singleTr = singleTr + '<td><input name="checkNumber" value="盘点数量"></td>';
////singleTr = singleTr + '<td><span name="differNumber" value="差异数量"></td>';
////$("#dataSource").append();
//siping.ajax({
//  method : 'get',
//  url : url,
//  async : true,
//  dataType : "json",
//  data : {
//      keyword : v,
//      isPurchase : true,
//      isSell : true,
//      isInventory : true
//  },
//  success : function(data) {
//      var _left = $(dom).offset().left;
//      var _top = $(dom).offset().top;
//      var _width = $(dom).height();
//      var list = '<div class="li_div my_radius">';
//      for(var i=0;i<data.length;i++){
//          list += '<div class="li_span"><span class="li_code">'+data[i][code]+'</span>-<span class="li_name">'+data[i][name]+'</span></div>'
//      }
//      list += '</div>';
//      $(".li_div").remove();
//      $(dom).after(list);
//      $(".li_div").css({"left":_left,"top":_top+_width+3});
//      $(".li_div").delegate(".li_span", "click", function(e){
//          var licode = $(this).children(".li_code").text();
//          //清空当前行所有input和span数据
//          $(".li_div").remove();
//          $(dom).val($(this).children(".li_name").text());
//          for(var i=0;i<data.length;i++){
//              if(data[i][code] == licode){
//                   //var resultMsg = validateMaterialId(data[i]['id']);
//                   //if(resultMsg.contains){ // 下一行和这一行是实现相同的功能，为了后期修改方便，进行了保留,但是这个模糊查询的功能没有使用了
//                   if(false){
//                       siping.alert(0,resultMsg.materialName+",在第"+resultMsg.row+"行已存在");
//                       break;
//                   }
//                   else{
//                       siping.alert(0,"取得数据成功Id为"+data[i]['id']);
//                   }
//              }
//          }
//      });
//  }
//});
//}