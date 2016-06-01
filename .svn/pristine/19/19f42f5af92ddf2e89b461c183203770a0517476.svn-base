$(function() {
    $("#return_po_smt").click(function() {
        var ids = new Array("billsDate","inboundStorageId","partnerCode");
        var resultMsg = validateNotNull(ids);
        if(resultMsg.success != true){
            siping.tips("必填项",resultMsg.id);
            return;
        }
        //验证表体信息
        var names = new Array("counts","purchasePrice","batchNumber");
        var tableMsg = validateTable(names);
        if(tableMsg.success != true){
            siping.alert(0, tableMsg.msg);
            return;
        }
        if($("#payPrice").text()=="NaN"){
            siping.alert(0, "应付金额数据类型错误,请重新检查数据");
            return;
        }
        var request = getPurchaseReturnsData();
        if(request.purchaseReturnsItemRequests.length==0){
            siping.alert(0,"没有表体数据，不能提交采购退货单");
            return;
        }
        // 验证退货数量不能小于该批次剩余数量
        var resultMsg = {};
        resultMsg.success=true;
        $("#dataSource tr").each(function(){
            if($(this).find("input[name='batchCounts']").val()-$(this).find("input[name='counts']").val()<0){
                resultMsg.success=false;
                resultMsg.msg = "第"+$(this).attr("id")+"行商品"+$(this).find("span[name = 'materialName']").text() + ",超过该批次剩余数量"+$(this).find("input[name='batchCounts']").val();
                return resultMsg
            }
        });
        if(resultMsg.success==false){
            siping.alert(0,resultMsg.msg);
            return;
        }
        request.isDraft = false;
        siping.ajax({
            method : "post",
            url : 'purchasereturns/add',
            data : JSON.stringify(request),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg+"单据编号为："+data.billNumber);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#return_po_edit_smt").click(function() {
        var ids = new Array("billsDate","inboundStorageId","partnerCode");
        var resultMsg = validateNotNull(ids);
        if(resultMsg.success != true){
            siping.tips("必填项",resultMsg.id);
            return;
        }
        //验证表体信息
        var names = new Array("counts","purchasePrice","batchNumber");
        var tableMsg = validateTable(names);
        if(tableMsg.success != true){
            siping.alert(0, tableMsg.msg);
            return;
        }
        var request = getPurchaseReturnsData();
        request.isDraft = false;
        siping.ajax({
            method : "post",
            url : 'purchasereturns/edit',
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
    $("#return_po_draft_edit_smt").click(function() {
        var request = getPurchaseReturnsData();
        request.isDraft = true;
        siping.ajax({
            method : "post",
            url : 'purchasereturns/edit',
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
    $("#return_draft_po_smt").click(function() {
        var request = getPurchaseReturnsData();
        request.isDraft = true;
        siping.ajax({
            method : "post",
            url : 'purchasereturns/add',
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
    $("#apply_po_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td>' + 
                '<td><span style="display:none" id="id' + id + '"></span><input id="apply_po_materialNo' + id+ '" class="input_text_common my_radius grid_text" onkeyup="getProListInPurchase(this)"></td>' +
                '<td><span id="materialName' + id+ '"class="grid_text grid_readonly_text my_radius"></span></td>' + 
                '<td><span id="specificationsModel' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'+
                '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                '<td><span id="season' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                '<td><span id="barcode' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                '<input type="hidden" id="unitId' + id + '">' + 
                '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                '<td><input id="apply_po_counts' + id + '" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                '<td><input id="apply_po_last_date' + id+ '" data-type="date" class="input_text_common my_radius grid_text"></td>' + 
                '<td><input id="apply_po_remark' + id+ '" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    $("#po_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td>' + '<td><span style="display:none" id="id' + id + '"></span><input id="po_materialNo' + id
                        + '" class="input_text_common my_radius grid_text" onkeyup="getProListInPurchase(this)"></td>' + '<td><span id="materialName' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specifications_model' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="season' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="barcode' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<input type="hidden" id="unitId' + id + '">' + '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><input id="counts'+ id + '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="purchasePrice' + id+ '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="discount' + id+ '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="afterDiscount' + id + '" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="taxRate' + id+ '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><span id="tax' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><span id="total' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                        '<td><input id="isGift' + id + '" onclick="resetPrice(this);" type="checkbox"></td>' + 
                        '<td><input id="remark' + id + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                $.tableCalc("_counts" + id, "keyup");
                $.tableCalc("_price" + id), "keyup";
                $.tableCalc("_discountRate" + id, "keyup");
                $.tableCalc("_taxRate" + id, "keyup");
                $.tableCalc("_discountAmount" + id, "keyup");
                $.tableCalc("_isGift" + id, "change");
                siping.eventBinding($("#" + id + " *"));
            });
    $("#receive_purchase_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td>' + 
                        '<td><span style="display:none" id="id' + id + '"></span><input id="receipt_materialNo' + id + '" class="input_text_common my_radius grid_text" onkeyup="getProListInPurchase(this)"></td>' + 
                        '<td><span id="materialName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><span id="season' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><span id="barcode' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><input id="batchNumber' + id + '" class="input_text_common my_radius grid_text"></input></td>' + 
                        '<td><input id="productDate' + id + '" data-type="date" class="input_text_common my_radius grid_page_text"></input></td>' + 
                        '<input type="hidden" id="unitId' + id + '">' + '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><input id="counts' + id + '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="purchasePrice' + id + '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="discount' + id + '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="afterDiscount' + id + '" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="taxRate' + id + '" onkeyup="getTotalPrice(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><span id="tax' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><span id="total' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                        '<td><input id="isGift' + id + '" onclick="resetPrice(this);" type="checkbox"></td>' + 
                        '<td><input id="remark' + id + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    $("#return_purchase_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td><td><input id="materialId' + id
                        + '" class="input_text_common my_radius grid_text" onkeyup="getProListInPurchase(this)"><span id="id' + id + '" style="display:none;"></span></td>'
                        + '<td><span id="materialName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="season' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="barcode' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="batchNumber' + id
                        + '" class="input_text_common my_radius grid_text" onclick="getBatchNumber();"></td>' + '<td><input id="productionDate' + id
                        + '" data-type="date" class="input_text_common my_radius grid_text"></td>' + '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                        '<td><input id="counts'+id+'" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>'+
                        '<td><input id="purchasePrice'+id+'" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>'+
                        '<td><input id="discount'+id+'" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>'+
                        '<td><input id="afterDiscount' + id + '" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="taxRate'+id+'" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>'+
                        '<td><span id="tax'+id+'" onkeyup="getTotalPrice(this);" class="grid_text grid_readonly_text my_radius"></span></td>'+
                        '<td><span id="total' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                        '<td><input id="isGift'+id+'" onclick="resetPrice(this);" type="checkbox"></td>'+
                        '<td><input id="remark'+id+'" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });

    // 采购申请单提交
    $(" #apply_po_smt, #apply_po_draft_smt").click(function() {
        if(this.id == "apply_po_smt"){
            // 判断数据合法性
            var ids = new Array("billsDate","nickName");
            var resultMsg = validateNotNull(ids);
            if(resultMsg.success != true){
                siping.tips("必填项",resultMsg.id);
                return;
            }
            //验证表体信息
            var names = new Array("counts");
            var tableMsg = validateTable(names);
            if(tableMsg.success != true){
                siping.alert(0, tableMsg.msg);
                return;
            }
        }
        // 获取数据
        var datas = getApplyPODate(this);
        var url = '/purchaseapplyorder/add';
        siping.ajax({
            method : 'post',
            url : url,
            async : true,
            data : JSON.stringify(datas),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg+"单据编号为："+data.billNumber);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    
    $(" #apply_po_edit, #apply_po_draft_edit").click(function() {
        if(this.id == "apply_po_edit"){
            // 判断数据合法性
            var ids = new Array("applypo_expireDate","applypo_lastDate","userName");
            var resultMsg = validateNotNull(ids);
            if(resultMsg.success != true){
                siping.tips("必填项",resultMsg.id);
                return;
            }
            //验证表体信息
            var names = new Array("counts");
            var tableMsg = validateTable(names);
            if(tableMsg.success != true){
                siping.alert(0, tableMsg.msg);
                return;
            }
        }
        // 获取数据
        var datas = getApplyPODate(this);
        var url = '/purchaseapplyorder/edit';
        siping.ajax({
            method : 'post',
            url : url,
            async : true,
            data : JSON.stringify(datas),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);//+"单据编号为："+data.billNumber
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    // 采购订单编辑修改提交
    $("#po_smt, #po_draft_smt").click(function() {
        // 获取数据
        if(this.id == "po_smt"){
            // 判断数据合法性
            var ids = new Array("billsDate","partnerCode");
            var resultMsg = validateNotNull(ids);
            if(resultMsg.success != true){
                siping.tips("必填项",resultMsg.id);
                return;
            }
            //验证表体信息
            var names = new Array("counts","minOrder","purchasePrice","currencyPrice");
            var tableMsg = validateTable(names);
            if(tableMsg.success != true){
                siping.alert(0, tableMsg.msg);
                return;
            }
        }
        if($("#payPrice").text()=="NaN"){
            siping.alert(0, "应付金额数据类型错误,请重新检查数据");
            return;
        }
        var datas = getPODate(this);
        var url = '/purchaseorder/add';
        siping.ajax({
            method : 'post',
            url : url,
            async : true,
            data : JSON.stringify(datas),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg+"单据编号为："+data.billNumber,"purchaseorder/add");
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#po_edit, #po_draft_edit").click(function() {
        if(this.id == "po_edit"){
            // 判断数据合法性
            var ids = new Array("billsDate","partnerCode");
            var resultMsg = validateNotNull(ids);
            if(resultMsg.success != true){
                siping.tips("必填项",resultMsg.id);
                return;
            }
            //验证表体信息
            var names = new Array("counts","minOrder","purchasePrice");
            var tableMsg = validateTable(names);
            if(tableMsg.success != true){
                siping.alert(0, tableMsg.msg);
                return;
            }
        }
        // 获取数据
        var datas = getPODate(this);
        var url = '/purchaseorder/edit';
        siping.ajax({
            method : 'post',
            url : url,
            async : true,
            data : JSON.stringify(datas),
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
    // 采购收货单编辑修改提交 
    $("#receipt_edit, #receipt_draft_edit").click(function() {
        // 判断数据合法性
        if (!validReceipt()) {
            // 提示消息

            return;
        }
        // 获取数据
        var datas = getReceiptDate(this.id);
        var url = '/purchasereceipt/edit';
        siping.ajax({
            method : 'post',
            url : url,
            async : true,
            data : JSON.stringify(datas),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
            	siping.alert(1, data.msg);
            },
            error : function(data) {
                siping.alert(0, data.msg);
            }
        });
    });
    
    $("#receipt_smt, #receipt_draft_smt").click(function() {
        if(this.id == "receipt_smt"){
            // 判断数据合法性
            var ids = new Array("inboundStorageId","billsDate","partnerCode");
            var resultMsg = validateNotNull(ids);
            if(resultMsg.success != true){
                siping.tips("必填项",resultMsg.id);
                return;
            }
            //验证表体信息
            var names = new Array("counts","referencedAmount","purchasePrice","batchNumber","productDate","dueDate");
            var tableMsg = validateTable(names);
            if(tableMsg.success != true){
                siping.alert(0, tableMsg.msg);
                return;
            }
        }
        if($("#payPrice").text()=="NaN"){
            siping.alert(0, "应付金额数据类型错误,请重新检查数据");
            return;
        }
        // 验证仓库最大库存
        var nodeId = this.id;
        var request = getMaterialCounts();
        siping.ajax({
            method : 'post',
            url : '/inventory/ismorethan',
            async : true,
            data : JSON.stringify(request),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if(data.isMoreThan){
                    siping.confirm(data.materialName+"超过库存警戒,是否继续提交",function(){
                        smtPurchaseReceipt(nodeId);
                    });
                }
                else{
                    smtPurchaseReceipt(nodeId);
                }
            },
            error : function(data) {
                siping.alert(data.success, data.msg);
            }
        });
    });
    function smtPurchaseReceipt(nodeId){
     // 获取数据
        var datas = getReceiptDate(nodeId);
        datas.receiptNumber = $("#receiptNumber").text();//新增时，提供单据编号
        var url = '/purchasereceipt/add'; 
        siping.ajax({
            method : 'post',
            url : url,
            async : true,
            data : JSON.stringify(datas),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if(data.success){
                    siping.alert(data.success, data.msg+"单据编号为："+data.billNumber,'purchaseorderreceipt/add');//新增成功
                }
                else{
                    siping.alert(data.success, data.msg); 
                }
            },
            error : function(data) {
                siping.alert(data.success, data.msg);
            }
        });
    }
    function getMaterialCounts() {
        var dom = $("#dataSource");
        var request = [];
        $(dom).children("tr").each(function(){
            if($(this).find("input[name='materialId']").val() != ""){
                var item = {};
                item.materialId = $(this).find("input[name='materialId']").val();
                item.counts = $(this).find("input[name='counts']").val();
                item.storageId = $("#inboundStorageId").val();
                request.push(item);
            };
        });
        return request;
    }
    // 采购订单供应商和仓库选择控制事件
    jQuery.focusAble = function(suffix, event) {
        $("[id$='" + suffix + "']").bind(event, function() {
            if (!hasValue($("#partnerName").text())) {
                alert("请先选择供应商");

                if ($('#partnerName').length > 0) {
                    setTimeout(function() {
                        try {
                            $('#partnerCode')[0].focus();
                        } catch (e) {
                        }
                    }, 0);
                }

                return;
            }
            if (!hasValue($("#inboundStorageId").val())) {
                alert("请先选择仓库");
                if ($("#inboundStorageId").length > 0) {
                    setTimeout(function() {
                        try {
                            $("#inboundStorageId").focus();
                        } catch (e) {
                        }
                    }, 0);
                }
                return;
            }
        });
    }
});

// 获得物料数据
function getProListInPurchase(dom) {
    var idArray = [ "id", "materialNo", "materialName", "specificationsModel", "barcode", "brand", "season", "unitName" ];
    var rowId = $(dom).parent().parent().attr("id");
    siping.getSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
}

// 获得供应商信息
function getSupListInPurchase(dom) {
    var idArray = [ "id", "partnerCode", "partnerName", "contactsLastName", "contactsFirstName", "contactsMobilephone" ];
    siping.getSelect(dom, 'partnerCode', 'partnerName', "/businesspartner/getlistbykeyword?type=2", idArray, '');
}

/** 采购订单begin * */
// 采购订单数据验证
function validPO() {
    var dom = parent.$("#dataSource");
    // 验证物料起订量，提示信息：“xxx订单数量小于起订单！”

    // 验证库存最大量，提示信息：“xxx物料单据数量xx+当前库存数量xx大于最大库存，是否继续”，选择是继续过账，选择否过账事务回滚。

    // 验证订单数量和单价是否为0，为0则给出相应的提示信息“xxx数量不能为零”，“xxx单价不能为零”。
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            if($(this).find("input[name='counts']").val() == 0){
                var materialName = $(this).find("span[name='materialName']").text();
                alert(materialName+"数量不能为零");
                return false;
            }
            if($(this).find("input[name='purchasePrice']").val() == 0){
                var materialName = $(this).find("span[name='materialName']").text();
                alert(materialName+"单价不能为零");
                return false;
            }
        };
    });
    return true;
}

// 获取数据
function getPODate(node) {
    var dom = $("#dataSource");
    var request = {};
    // 表头表尾数据
    request.purchaseOrderNumber = $("#orderNumber").text();
    request.fromBillsNo = $("#fromBillsNo").val();
    request.purchaseFlowId = $("#po_flowId").val();
    request.supplierId = $("#id").text();
    request.billDate = $("#billsDate").val();
    request.receiptDate = $("#po_receiptDate").val();
    request.inBoundStorage = $("#inboundStorageId").val();
    request.summary = $("#po_summary").val();
    request.totalAmount = $("#totalPrice").text();
    request.giftAmount = $("#giftPrice").text();
    request.discountAmount = $("#favorablePrice").val();
    request.payAmount = $("#payPrice").text();
    request.createBy = $("#owner").val();
    request.auditor = $("#po_auditor").val();
    request.owner = $("#loginId").val();
    request.updateBy = $("#owner").val();
    request.currency = $("#currency").val();
    request.exchangeRate = $("#exchangeRate").val();
    if (node.id == "po_smt" || node.id == "po_edit") {
        request.isDraft = false;
    } else if (node.id == "po_draft_smt" || node.id == "po_draft_edit") {
        request.isDraft = true;
    }
    // 商品信息列表
    var items = [];
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            var item = {};
            item.purchaseOrderNumber = $("#purchaseOrderNumber").text();
            item.materialId = $(this).find("input[name='materialId']").val();
            item.counts = $(this).find("input[name='counts']").val();
            item.purchasePrice = $(this).find("input[name='purchasePrice']").val();
            item.discountRate = $(this).find("input[name='discount']").val();
            item.afterDiscount = $(this).find("input[name='afterDiscount']").val();
            item.taxRate = $(this).find("input[name='taxRate']").val();
            item.tax = $(this).find("span[name='tax']").text();
            item.amount = $(this).find("span[name='total']").text();
            item.isGift = $(this).find("input[name='isGift']").is(':checked');
            item.remark = $(this).find("input[name='remark']").val();
            item.currencyPrice = $(this).find("input[name='currencyPrice']").val();
            item.rowNumber = $(this).attr("id");
            items.push(item);
        };
    });
    request.itemList = items;
    return request;
}

/** 采购订单end * */

/** 采购申请单begin * */
// 数据验证
function validApplyPO() {
    if($("#applypo_expireDate").val()==""){
        return false;
    }
    if($("#applypo_lastDate").val()==""){
        return false;
    }
    return true;
}

// 获取数据
function getApplyPODate(node) {
    var dom = $("#dataSource");
    var request = {};
    // 表头表尾数据
    request.purchaseOrderNumber = $("#purchaseOrderNumber").text();
    if (hasValue($("#applypo_flowId").val())) {
        request.purchaseFlowId = $("#applypo_flowId").val();
    }
    request.applierId = $("#userId").val();
    request.summary = $("#applypo_summary").val();
    request.billDate = $("#billsDate").val();
    request.orderExpireDate = $("#applypo_expireDate").val();
    request.lastDate = $("#applypo_lastDate").val();
    if (node.id == "apply_po_smt" || node.id == "apply_po_edit") {
        request.isDraft = false;
        request.purchaseOrderNumber = $("#purchaseOrderNumber").text();
    } else if (node.id == "apply_po_draft_smt" || node.id == "apply_po_draft_edit") {
        request.isDraft = true;
    }
    // 商品信息列表
    var items = [];
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            var item = {};
            item.purchaseOrderNumber = $("#purchaseOrderNumber").text();
            item.materialId = $(this).find("input[name='materialId']").val();
            item.counts = $(this).find("input[name='counts']").val();
            item.lastDate = $(this).find("input[name='lastDate']").val();
            item.remark = $(this).find("input[name='remark']").val();
            items.push(item);
        };
    });
    request.items = items;
    return request;
}
/** 采购申请单end * */

/** 采购收货单begin * */
// 数据验证
function validReceipt() {

    return true;
}

// 获取数据
function getReceiptDate(nodeId) {
    var dom = $("#dataSource");
    var request = {};
    // 表头表尾数据
    request.fromBillsNo = $("#fromBillsNo").val();
    request.receiptNumber = $("#receipt_number").text();
    request.partnerId = $("#id").text();
    request.summary = $("#receipt_summary").val();
    request.billDate = $("#billsDate").val();
    request.inboundStorageId = $("#inboundStorageId").val();
    request.totalAmount = $("#totalPrice").text();
    request.giftAmount = $("#giftPrice").text();
    request.discountAmount = $("#favorablePrice").val();
    request.payAmount = $("#payPrice").text();
    request.owner = $("#loginId").val();
    if (nodeId == "receipt_smt" || nodeId == "receipt_edit") {
        request.isDraft = false;
    } else if (nodeId == "receipt_draft_smt" || nodeId == "receipt_draft_edit") {
        request.isDraft = true;
    }
    // 商品信息列表
    var items = [];
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            var item = {};
            item.materialId = $(this).find("input[name='materialId']").val();
            item.batchNumber = $(this).find("input[name='batchNumber']").val();
            item.productionDate = $(this).find("input[name='productDate']").val();
            item.dueDate = $(this).find("input[name='dueDate']").val();
            item.counts = $(this).find("input[name='counts']").val();
            item.purchasePrice = $(this).find("input[name='purchasePrice']").val();
            item.discountRate = $(this).find("input[name='discount']").val();
            item.afterDiscount = $(this).find("input[name='afterDiscount']").val();
            item.taxRate = $(this).find("input[name='taxRate']").val();
            item.tax = $(this).find("span[name='tax']").text();
            item.amount = $(this).find("span[name='total']").text();
            item.isGift = $(this).find("input[name='isGift']").is(':checked');
            item.remark = $(this).find("input[name='remark']").val();
            item.rowNumber = $(this).find("input[name='rowNumber']").val();
            items.push(item);
        };
    });
    request.items = items;
    return request;
}
/** 采购收货单end * */

function getRetProList(dom) {
    var idArray = [ "id", "materialNo", "materialName", "specificationsModel", "barcode", "brand", "season", "shops", "batchNumber", "productionDate", "unitName" ];
    var rowId = $(dom).parent().parent().attr("id");
    siping.getSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
}
function getRetSupList(dom) {
    var idArray = [ "id", "partnerCode", "partnerName", "contactsLastName", "contactsFirstName", "contactsMobilephone" ];
    siping.getSelect(dom, 'partnerCode', 'partnerName', "/businesspartner/getlistbykeyword?type=2", idArray, '');
}
function getUserList(dom) {
    siping.getSelect(dom, "id", "username", "", "", "");
}
function getBatchNumber() {
    var storageId = $("#inboundStorageId").val();
    var materialId = $("#id").val();
    siping.ajax({
        method : "get",
        url : "inventorylog/getlist",
        data : {
            materialId : materialId,
            storageId : storageId
        },
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            // TODO 显示批次号
        }
    });
}
function getPurchaseReturnsData() {
    var dom = $("#dataSource");
    var request = {};
    request.returnsNumber = $("#returnsNumber").text();
    request.billsDate = $("#billsDate").val();
    request.partnerId = $("#id").text();
    request.outboundStorageId = $("#inboundStorageId").val();
    request.summary = $("#summary").val();
    request.totalPrice = $("#totalPrice").text();
    request.giftPrice = $("#giftPrice").text();
    request.auditor = $("#auditor").val();
    request.owner = $("#loginId").val();
    request.favorablePrice = $("#favorablePrice").val();
    request.payPrice = $("#payPrice").text();
    // 商品信息列表
    var items = [];
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            var item = {};
            item.materialId = $(this).find("input[name='materialId']").val();
            item.batchNumber = $(this).find("input[name='batchNumber']").val();
            item.productionDate = $(this).find("span[name='productDate']").text();
            item.counts = $(this).find("input[name='counts']").val();
            item.purchasePrice = $(this).find("input[name='purchasePrice']").val();
            item.discount = $(this).find("input[name='discount']").val();
            item.afterDiscount = $(this).find("input[name='afterDiscount']").val();
            item.taxRate = $(this).find("input[name='taxRate']").val();
            item.tax = $(this).find("span[name='tax']").text();
            item.total = $(this).find("span[name='total']").text();
            item.isGift = $(this).find("input[name='isGift']").is(':checked');
            item.remark = $(this).find("input[name='remark']").val();
            items.push(item);
        };
    });
    request.purchaseReturnsItemRequests = items;
    return request;
}

/** 表体计算begin * */
// 税额计算=数量*单价*（1-折扣）*税率 
function calTax(price, counts, discountRate, taxRate) {
    return (parseFloat(price) * parseFloat(counts) * parseFloat(1- discountRate/100) * parseFloat(taxRate)).toFixed(3);
}
//税额计算=总价*税率 
function calTax2(amount, taxRate) {
    return (parseFloat(amount)*parseFloat(taxRate)/(1+parseFloat(taxRate)));
}

// 总计结算=数量*单价+税额-折扣%*单价*数量
function calAmount(price, counts, tax, discountRate) {
    return (parseFloat(price) * parseFloat(counts) + parseFloat(tax) - parseFloat(discountRate) / 100 * parseFloat(price) * parseFloat(counts)).toFixed(3);
}
// 折扣=100*(1-总计/(数量*单价*(1+税率)))
function calDiscount(price, counts, taxRate, amount) {
    return 100*(1-(parseFloat(amount)/(parseFloat(counts)*parseFloat(price)*(1+parseFloat(taxRate)))));
}
/** 表体计算end * */

function hasValue(value) {
    if (value == "" || value == undefined || value == null) {
        return false;
    }
    return true;
}
function numberReturn(value) {
    return (value == "") ? parseFloat("0") : (parseFloat(value)).toFixed(3);
}
function deleteDraftReturnPO() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/purchasereturns/delete?returnsNumber=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/purchaseorderreturn/getdraftlist");
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

function deleteDraftReceiptPO() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/purchasereceipt/delete?orderNumbers=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/purchaseorderreceipt/getdraftlist");
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

function deleteDraftPO() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/purchaseorder/delete?orderNumbers=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/purchaseorder/getdraftlist");
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

function deleteDraftApplyPO() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/purchaseapplyorder/delete?ids=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/purchaseapplyorder/getdraftlist");
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

function addReceiptPurchaseOrderRow(dom){
    var rowInfo = getMaterialsHtml(dom,'addReceiptPurchaseOrderRow','chooseReceiptPurchaseOrderMaterial');
    var row = rowInfo.materialsHtml;
    var rowId = rowInfo.rowId;
    row += '<td><input name="batchNumber" class="input_text_common my_radius grid_text"></input></td>' +
    '<td><input name="productDate" onclick = "laydate({choose: function(date){getDueDate(date,'+rowId+')}})" class="input_text_common my_radius grid_text"></input></td>' +
    '<td><input name="dueDate" onclick = "laydate({choose: function(date){getProductDate(date,'+rowId+')}})" data-type="date" class="input_text_common my_radius grid_text"></input><input name = "expirationDate" value = "" type = "hidden"></input></td>' +
    '<td><input name="counts" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="purchasePrice" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="discount" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="afterDiscount" data-type="number" onblur = "getDiscount(this);" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="taxRate" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><span name="tax" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="total" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><input name="isGift" onclick="reset(this);" type="checkbox"></td>' +
    '<td><input name="remark" class="input_text_common my_radius grid_text"></td></tr>';
    $(dom).parent().parent().parent().append(row);
    siping.eventBinding($("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
    parent.siping.eventBinding(parent.$("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
}

function addApplyPurchaseOrderRow(dom){
    var rowInfo = getMaterialsHtml(dom,'addApplyPurchaseOrderRow','chooseApplyPurchaseOrderMaterial');
    var row = rowInfo.materialsHtml;
    var rowId = rowInfo.rowId;
    row +=
    '<td><input name="counts" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="remark" class="input_text_common my_radius grid_text"></td>';
    $(dom).parent().parent().parent().append(row);
    siping.eventBinding($("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
    parent.siping.eventBinding(parent.$("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
}

function chooseReceiptPurchaseOrderMaterial(rowId){
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
        var needAddRow = getNeedAddRows(materialList, dom,rowId);
        // 行不够添加行
        if(needAddRow > 0){
            for(var i=0; i < needAddRow ; i++){
                addReceiptPurchaseOrderRow(dom.children().children().children());
            }
        }
        var materialIndex = 0; //物料下标
        // 循环填充表格
        for(var i = 0; i < materialList.length; i++){
            $(dom).children("tr").each(function(){
                if(parseInt($(this).attr("id")) >= rowId){
                    // 选择商品填充当前行，之后找空白行添加
                    if($(this).find("input[name = 'materialId']").val() == "" || (parseInt($(this).attr("id")) == rowId && materialIndex ==0)){
                        var material = materialList[materialIndex];
                        fillMaterialData(this,material); //填充商品信息
                        if(material.isBatch == "false"){
                            $(this).find("input[name='batchNumber']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                            $(this).find("input[name='productDate']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                            $(this).find("input[name='dueDate']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                        }else {
                            $(this).find("input[name='batchNumber']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                            $(this).find("input[name='productDate']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                            $(this).find("input[name='dueDate']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                        }
                        $(this).find("input[name='expirationDate']").val(material.expirationDate);
                        materialIndex ++;
                        return false; //中断循环
                    }
                }
            });
        }
        parent.materialList = [];
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择商品");
    }
}

function chooseApplyPurchaseOrderMaterial(rowId){
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
        var needAddRow = getNeedAddRows(materialList, dom,rowId);
        // 行不够添加行
        if(needAddRow > 0){
            for(var i=0; i < needAddRow ; i++){
                addApplyPurchaseOrderRow(dom.children().children().children());
            }
        }
        var materialIndex = 0; //物料下标
        // 循环填充表格
        for(var i = 0; i < materialList.length; i++){
            $(dom).children("tr").each(function(){
                if(parseInt($(this).attr("id")) >= rowId){
                    // 选择商品填充当前行，之后找空白行添加
                    if($(this).find("input[name = 'materialId']").val() == "" || (parseInt($(this).attr("id")) == rowId && materialIndex ==0)){
                        var material = materialList[materialIndex];
                        fillMaterialData(this,material); //填充商品信息
                        $(this).find("input[name = 'lastDate']").val(parent.$("#applypo_lastDate").val())
                        materialIndex ++;
                        return false; //中断循环
                    }
                }
            });
        }
        parent.materialList = [];
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择商品");
    }
}
function addPurchaseOrderRow(dom){
    var rowInfo = getMaterialsHtml(dom,'addPurchaseOrderRow','choosePurchaseOrderMaterial');
    var row = rowInfo.materialsHtml;
    var rowId = rowInfo.rowId;
    row += 
    '<td><input name="currencyPrice" onkeyup="getRMBPriceAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="counts" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="purchasePrice" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="discount" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="afterDiscount" data-type="number" onblur = "getDiscount(this);" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="taxRate" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><span name="tax" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="total" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><input name="isGift" onclick="reset(this);" type="checkbox"></td>' +
    '<td><input name="remark" class="input_text_common my_radius grid_text"></td>';
    $(dom).parent().parent().parent().append(row);
    siping.eventBinding($("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
    parent.siping.eventBinding(parent.$("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
}
function addReturnPurchaseOrderRow(dom){
    var rowInfo = getMaterialsHtml(dom,'addReturnPurchaseOrderRow','chooseReturnPurchaseMaterial');
    var row = rowInfo.materialsHtml;
    var rowId = rowInfo.rowId;
    row += 
    '<td><input name="batchNumber" class="input_text_common my_radius grid_text"></input></td>' +
    '<td><span name="productDate" disabled = "disabled" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><input name="batchCounts" type="hidden"><input name="counts" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="purchasePrice" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="discount" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="afterDiscount" data-type="number" onblur = "getDiscount(this);" class="input_text_common my_radius grid_text"></td>' +
    '<td><input name="taxRate" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>' +
    '<td><span name="tax" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="total" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><input name="isGift" onclick="reset(this);" type="checkbox"></td>' +
    '<td><input name="remark" class="input_text_common my_radius grid_text"></td>';
    $(dom).parent().parent().parent().append(row);
    siping.eventBinding($("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
    parent.siping.eventBinding(parent.$("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
}
function chooseReturnPurchaseMaterial(rowId){
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) { 
        var needAddRow = getNeedAddRows(materialList, dom,rowId);
        // 行不够添加行
        if(needAddRow > 0){
            for(var i=0; i < needAddRow ; i++){
                addReturnPurchaseOrderRow(dom.children().children().children());
            }
        }
        var materialIndex = 0; //物料下标
        // 循环填充表格
        for(var i = 0; i < materialList.length; i++){
            $(dom).children("tr").each(function(){
                if(parseInt($(this).attr("id")) >= rowId){
                    // 选择商品填充当前行，之后找空白行添加
                    if($(this).find("input[name = 'materialId']").val() == "" || (parseInt($(this).attr("id")) == rowId && materialIndex ==0)){
                        var material = materialList[materialIndex];
                        fillMaterialData(this,material); //填充商品信息
                        if(material.isBatch == "false"){
                            $(this).find("input[name='batchNumber']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                            $(this).find("input[name='productDate']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                        }else {
                            $(this).find("input[name='batchNumber']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                            $(this).find("input[name='productDate']").attr({"class":"input_text_common my_radius grid_text","disabled":true});
                        }
                        materialIndex ++;
                        return false; //中断循环
                    }
                }
            });
        }
        parent.materialList = [];
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择商品");
    }
}

function choosePurchaseOrderMaterial(rowId){
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
        var needAddRow = getNeedAddRows(materialList, dom,rowId);
        // 行不够添加行
        if(needAddRow > 0){
            for(var i=0; i < needAddRow ; i++){
                addPurchaseOrderRow(dom.children().children().children());
            }
        }
        var materialIndex = 0; //物料下标
        // 循环填充表格
        for(var i = 0; i < materialList.length; i++){
            $(dom).children("tr").each(function(){
                if(parseInt($(this).attr("id")) >= rowId){
                    // 选择商品填充当前行，之后找空白行添加
                    if($(this).find("input[name = 'materialId']").val() == "" || (parseInt($(this).attr("id")) == rowId && materialIndex ==0)){
                        var material = materialList[materialIndex];
                        fillMaterialData(this,material); //填充商品信息
                        materialIndex ++;
                        return false; //中断循环
                    }
                }
            });
        }
        parent.materialList = [];
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择商品");
    }
}
function changeApplyItemLastDate(date){
    if(validateIsChooseItem()){
        siping.confirm("是否同步所有商品的必须日期?",function(){
            $("#dataSource tr").each(function(){
                if($(this).find("input[name='materialId']").val() != ""){
                    $(this).find("input[name='lastDate']").val(date);
                }
            });
        });
    }
}

function getRMBPriceAmount(dom){
    var rowDom = $(dom).parent().parent();
    if($("#exchangeRate").val()==""){
        siping.alert(0,"请输入正确的外币汇率");
        $("#exchangeRate").focus();
    }
    else{
        var RMBPrice = ($(rowDom).find("input[name='currencyPrice']").val()*$("#exchangeRate").val()).toFixed(3);
    }
    $(rowDom).find("input[name='purchasePrice']").val(RMBPrice); // 采购价
    getTotalAmount($(rowDom).find("input[name='purchasePrice']"));
}
