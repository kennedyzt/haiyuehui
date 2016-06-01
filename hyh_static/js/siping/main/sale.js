$(function() {
    var batchNumberData = "";
    batchNumberData = parent.document.getElementById("dso_batch_number");
    $("#show_batch_number").html(batchNumberData);
    $("#delivery_so_smt").click(function() {
        var request = getDeliverSOData();
        request.isDraft = false;
        siping.ajax({
            method : "post",
            url : 'sellshipments/add',
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
    $("#delivery_so_draft_smt").click(function() {
        var request = getDeliverSOData();
        request.isDraft = true;
        siping.ajax({
            method : "post",
            url : 'sellshipments/add',
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
    $("#saleorder_smt").click(function() {
        var request = getSaleOrderData();
        request.isDraft = false;
        siping.ajax({
            method : "post",
            url : 'sellorder/add',
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
    $("#saleorder_draft_smt").click(function() {
        var request = getSaleOrderData();
        request.isDraft = true;
        siping.ajax({
            method : "post",
            url : 'sellorder/add',
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
    $("#delivery_sale_order_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td><td><input id="materialId' + id
                        + '" class="input_text_common my_radius grid_text" onkeyup="getDeliverySaleOrderProList(this)"><span id="id' + id + '" style="display:none"></span></td>'
                        + '<td><span id="materialName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="season' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="barcode' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="unitName' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><input id="counts' + id + '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="sellPrice'+ id + '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="discount' + id+ '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="afterDiscount' + id + '" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="taxRate' + id+ '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><span id="tax' + id+ '" onkeyup="getTotalPrice(this);" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><span id="total' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                        '<td><input id="isGift' + id+ '" onclick="onclick="resetPrice(this);" type="checkbox"></td>' + 
                        '<td><input id="remark" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    $("#sale_order_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td><td><input id="materialId' + id
                        + '" class="input_text_common my_radius grid_text" onkeyup="getProListInPurchase(this)"><span id="id' + id + '" style="display:none"></span></td>'
                        + '<td><span id="materialName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="season' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="barcode' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                        '<td><input id="counts' + id + '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="sellPrice' + id+ '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="discount' + id + '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="afterDiscount' + id + '" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="taxRate' + id + '" data-type="number" onkeyup="getTotalPrice(this);" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><span id="tax' + id + '" onkeyup="getTotalPrice(this);" class="grid_text grid_readonly_text my_radius"></span></td>' + 
                        '<td><span id="total' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
                        '<td><input id="isGift' + id + '" onclick="resetPrice(this);" type="checkbox"></td>' + 
                        '<td><input id="shipmentsAmount' + id + '" class="input_text_common my_radius grid_text"></td>' + 
                        '<td><input id="remark" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    $("#sale_returnorder_add_row").click(
            function() {
                var id = $(this).parent().parent().parent().find("tr:last-child").attr("id");
                var img = '<img onclick="removeGridRowData(this)" src="' + staticPath + '/icons/tree_show.png">';
                id++;
                var row = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td>' + '<td><input id="materialNo' + id
                        + '" class="input_text_common my_radius grid_text" onkeyup="getReturnSOProList(this)"><input id="id' + id + '" type="hidden"></td>' + '<td><span id="materialName' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="specificationsModel' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><span id="brand' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="season' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><span id="barcode' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><input id="batchNumber' + id + '" class="input_text_common my_radius grid_text"></td>' + '<td><input id="productionDate' + id
                        + '" data-type="date" class="input_text_common my_radius grid_text"></td>' + '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius"></span></td>'
                        + '<td><input id="counts' + id + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + '<td><input id="sellPrice' + id
                        + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + '<td><input id="discount' + id
                        + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + '<td><input id="taxRate' + id
                        + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + '<td><span id="tax' + id
                        + '" class="grid_text grid_readonly_text my_radius"></span></td>' + '<td><input id="total' + id
                        + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getDiscount(this);"></td>' +
                        '<td><input id="isGift' + id + '" type="checkbox" onclick="resetReturnSaleOrderTotal(this);"></td>' + '<td><input id="remark' + id
                        + '" class="input_text_common my_radius grid_text"></td></tr>';
                $(this).parent().parent().parent().append(row);
                siping.eventBinding($("#" + id + " *"));
            });
    $("#return_so_smt,#return_so_draft_smt").click(function() {
        var requestData = getReturnSORequestData(this);
        siping.ajax({
            method : 'post',
            url : 'sellreturns/add',
            async : true,
            data : JSON.stringify(requestData),
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
    $("#return_so_edit_smt,#return_so_edit_draft_smt").click(function() {
        var requestData = getReturnSORequestData(this);
        alert("编辑后上传");
        siping.ajax({
            method : 'post',
            url : 'sellreturns/edit',
            async : true,
            data : JSON.stringify(requestData),
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
function getReturnSORequestData(node) {
    var dom = $("#dataSource");
    var request = {};
    if ($("#returnsNumber").text() != '') {
        request.returnsNumber = $("#returnsNumber").text();
    }
    request.billsDate = $("#billsDate").val();
    request.inboundStorage = $("#inboundStorageId").val();
    request.partnerId = $("#id").text();
    request.summary = $("#summary").val();
    request.owner = $("#ownerId").val();
    request.totalPrice = $("#totalPrice").text();
    request.giftPrice = $("#giftPrice").text();
    request.favorablePrice = $("#favorablePrice").val();
    request.payPrice = $("#payPrice").text();
    if (node.id == "return_so_smt" || node.id == "return_so_edit_smt") {
        request.isDraft = false;
    } else if (node.id == "return_so_draft_smt" || node.id == "return_so_edit_draft_smt") {
        request.isDraft = true;
    }
    
    var items = [];
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            var item = {};
            item.materialId = $(this).find("input[name='materialId']").val();
            item.batchNumber = $(this).find("input[name='batchNumber']").val();
            item.productionDate = $(this).find("input[name='productionDate']").val();
            item.counts = $(this).find("input[name='counts']").val();
            item.sellPrice = $(this).find("input[name='sellPrice']").val();
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
    request.returnsItemList = items;
    return request;
}
function getReturnSOProList(dom) {
    var idArray = [ "id", "materialName", "specificationsModel", "brand", "season", "barcode", "unitName" ];
    var rowId = $(dom).parent().parent().attr("id");
    siping.getSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
}
function getSOReturnCustList(dom) {
    var idArray = [ "id", "partnerCode", "partnerName", "contactsLastName", "contactsFirstName", "contactsMobilephone" ];
    siping.getSelect(dom, 'partnerCode', 'partnerName', "/businesspartner/getlistbykeyword?type=1", idArray, '');
}
function getSaleOrderSurProList(dom) {
    var idArray = [ "id", "materialName", "specificationsModel", "brand", "season", "barcode", "unitName", "shops" ];
    var rowId = $(dom).parent().parent().attr("id");
    siping.getSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
}
function getCustList(dom) {
    var idArray = [ "id", "partnerCode", "partnerName", "contactsLastName", "contactsFirstName", "contactsMobilephone" ];
    siping.getSelect(dom, 'partnerCode', 'partnerName', "/businesspartner/getlistbykeyword?type=1", idArray, '');
}
function getDeliverySaleOradrCustList(dom) {
    var idArray = [ "id", "partnerCode", "partnerName", "contactsLastName", "contactsFirstName", "contactsMobilephone" ];
    siping.getSelect(dom, 'partnerCode', 'partnerName', "/businesspartner/getlistbykeyword?type=1", idArray, '');
}
function getDeliverySaleProList(dom) {
    var idArray = [ "id", "materialName", "specificationsModel", "brand", "season", "barcode", "unitName" ];
    var rowId = $(dom).parent().parent().attr("id");
    siping.getSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
}
function getDeliverySaleOrderProList(dom) {
    var idArray = [ "id", "materialName", "specificationsModel", "brand", "season", "barcode", "unitName" ];
    var rowId = $(dom).parent().parent().attr("id");
    deliverySaleOrder.getSelect(dom, 'materialNo', 'materialName', "material/getlistbykey", idArray, rowId);
}
var deliveryIndex = parent.layer.getFrameIndex(window.name);
var deliverySaleOrder = {
    getSelect : function(dom, code, name, url, idArray, rowid) {
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
                            $(dom).val(licode);
                            $(".li_div").remove();
                            var rowData; // 保存选择的物料数据
                            for (var i = 0; i < data.length; i++) {
                                if (data[i][code] == licode) {
                                    rowData = data[i];
                                    for (var j = 0; j < idArray.length; j++) {
                                        var thisId = idArray[j];
                                        if (data[i][thisId] != null && data[i][thisId] != undefined)
                                            $("#" + thisId + rowid).text(data[i][thisId]);
                                    }
                                    // 获取被选择物料的所有批次号
                                    var storageId = $("#outboundStorageId").val();
                                    var materialId = rowData.id;
                                    siping.ajax({
                                        method : "GET",
                                        url : "inventorycheck/getmaterialbatchnumberlist",
                                        data : {
                                            materialId : materialId,
                                            storageId : storageId
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
                                    break;
                                }
                            }
                        });
            }
        });
    }
}
function getSaleCusList(dom) {
    var idArray = [ "id", "partnerCode", "partnerName", "contactsLastName", "contactsFirstName", "contactsMobilephone" ];
    siping.getSelect(dom, 'partnerCode', 'partnerName', "/businesspartner/getlistbykeyword?type=1", idArray, '');
}
function getSaleOrderData() {
    var dom = $("#dataSource");
    var request = {};
    request.orderNumber = $("#orderNumber").text();
    request.billsDate = $("#billsDate").val();
    request.outboundStorage = $("#outboundStorageId").val();
    request.partnerId = $("#id").text();
    request.summary = $("#summary").val();
    request.totalPrice = $("#totalPrice").text();
    request.giftPrice = $("#giftPrice").text();
    request.favorablePrice = $("#favorablePrice").val();
    request.gatheringPrice = $("#gatheringPrice").text();
    request.auditor = $("#auditor").val();
    request.owner = $("#loginId").val();
    request.shipmentsDate = $("#shipmentsDate").val();
    // 表体数据
    var items = [];
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            var item = {};
            item.materialId = $(this).find("input[name='materialId']").val();
            item.counts = $(this).find("input[name='counts']").val();
            item.sellPrice = $(this).find("input[name='sellPrice']").val();
            item.discount = $(this).find("input[name='discount']").val();
            item.afterDiscount = $(this).find("input[name='afterDiscount']").val();
            item.taxRate = $(this).find("input[name='taxRate']").val();
            item.tax = $(this).find("span[name='tax']").text();
            item.total = $(this).find("span[name='total']").text();
            item.isGift = $(this).find("input[name='isGift']").is(':checked');
            item.shipmentsAmount = $(this).find("input[name='shipmentsAmount']").val();
            item.remark = $(this).find("input[name='remark']").val();
            items.push(item);
        };
    });
    request.orderItemList = items;
    return request;
}

function deleteSaleOrder() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/sellorder/delete?orderNumberList=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/sellorder/getdraftlist");
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

function getDeliverSOData() {
    var dom = $("#dataSource");
    var request = {};
    request.shipmentsNumber = $("#shipmentsNumber").text();
    request.billsDate = $("#billsDate").val();
    request.outboundStorage = $("#outboundStorageId").val();
    request.partnerId = $("#id").text();
    request.summary = $("#summary").val();
    request.totalPrice = $("#totalPrice").text();
    request.giftPrice = $("#giftPrice").text();
    request.favorablePrice = $("#favorablePrice").val();
    request.gatheringPrice = $("#gatheringPrice").text();
    request.owner = $("#loginId").val();
    // 表体数据
    var items = [];
    $(dom).children("tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            var item = {};
            item.materialId = $(this).find("input[name='materialId']").val();
            item.counts = $(this).find("input[name='counts']").val();
            item.sellPrice = $(this).find("input[name='sellPrice']").val();
            item.discount = $(this).find("input[name='discount']").val();
            item.afterDiscount = $(this).find("input[name='afterDiscount']").val();
            item.taxRate = $(this).find("input[name='taxRate']").val();
            item.tax = $(this).find("span[name='tax']").text();
            item.total = $(this).find("span[name='total']").text();
            item.isGift = $(this).find("input[name='isGift']").is(':checked');
            item.shipmentsAmount = $(this).find("input[name='shipmentsAmount']").val();
            item.remark = $(this).find("input[name='remark']").val();
            items.push(item);
        };
    });
    request.shipmentsItemList = items;
    return request;
}
function chooseSaleDate(dom) {
    var batchNumber = dom.firstChild.innerText;
    var productionDate = dom.firstChild.nextSibling.innerText;
    var rowId = $("#rowId").val();
    parent.$('#batchNumber' + rowId).text(batchNumber);
    parent.$('#productionDate' + rowId).text(productionDate);
    parent.siping.close(deliveryIndex);
}
function deleteReturnSaleOrder() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        siping.confirm('确定删除吗？', function() {
            siping.ajax({
                method : 'get',
                url : '/sellreturns/delete?returnsNumberList=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                        siping.redirect("/sellreturns/getdraftlist");
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
function chooseSaleOrderMaterial(rowId) {
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
        var needAddRow = getNeedAddRows(materialList, dom,rowId);
        // 行不够添加行
        if(needAddRow > 0){
            for(var i=0; i < needAddRow ; i++){
                addSaleOrderRow(dom.children().children().children());
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

function chooseDeliverySaleOrderMaterial(rowId) {
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
        var needAddRow = getNeedAddRows(materialList, dom,rowId);
        // 行不够添加行
        if(needAddRow > 0){
            for(var i=0; i < needAddRow ; i++){
                addDeliverySaleOrderRow(dom.children().children().children());
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

function chooseReturnSaleOrderMaterial(rowId){
    var dom = parent.$("#dataSource");
    var materialList = parent.materialList;
    if (materialList.length != 0) {
        var needAddRow = getNeedAddRows(materialList, dom,rowId);
        // 行不够添加行
        if(needAddRow > 0){
            for(var i=0; i < needAddRow ; i++){
                addReturnSaleOrderRow(dom.children().children().children());
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
                            $(this).find("input[name='productDate']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
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

function addReturnSaleOrderRow(dom){
    var rowInfo = getMaterialsHtml(dom,'addReturnSaleOrderRow','chooseInventoryDeliveryMaterial');
    var row = rowInfo.materialsHtml;
    var rowId = rowInfo.rowId;
    row +=
        '<td><input name="batchNumber" class="input_text_common my_radius grid_text"></td>'+
        '<td><input name="productionDate" data-type="date" class="input_text_common my_radius grid_text"></td>'+
        '<td><input name="counts" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>'+
        '<td><input name="sellPrice" data-type="number" class="input_text_common my_radius grid_text"  onkeyup="getTotalAmount(this);"></td>'+
        '<td><input name="discount" data-type="number" class="input_text_common my_radius grid_text"  onkeyup="getTotalAmount(this);"></td>'+
        '<td><input name="afterDiscount"  onblur = "getInverseDiscount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>'+
        '<td><input name="taxRate" data-type="number" class="input_text_common my_radius grid_text"  onkeyup="getTotalAmount(this);"></td>'+
        '<td><span name="tax" onkeyup="getTotalAmount(this);" class="grid_text grid_readonly_text my_radius"></span></td>'+
        '<td><span name="total" class="grid_text grid_readonly_text my_radius"></span></td>'+
        '<td><input name="isGift" type="checkbox" onclick="reset(this);"></td>'+
    $(dom).parent().parent().parent().append(row);
    siping.eventBinding($("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
    parent.siping.eventBinding(parent.$("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
}

//function chooseReturnSaleOrderMaterial() {
//    var dom = parent.$("#dataSource");
//    var materialList = parent.materialList;
//    if (materialList.length != 0) {
//        for (var i = 0; i < materialList.length; i++) {
//            var material = materialList[i];
//            var id = dom.find("tr:last-child").attr("id");
//            if (typeof (id) == 'undefined') {
//                id = 0;
//            }
//            id++;
//            var materialHtml = getMaterialHtml(material, id);
//            var row = materialHtml;
//            row += '<td><input id="batchNumber' + id + '" class="input_text_common my_radius grid_text"></td>' + 
//            '<td><input id="productionDate' + id+ '" data-type="date" class="input_text_common my_radius grid_text"></td>' + 
//            '<td><input id="counts' + id + '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + 
//            '<td><input id="sellPrice' + id+ '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + 
//            '<td><input id="discount' + id+ '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + 
//            '<td><input id="afterDiscount' + id + '" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' + 
//            '<td><input id="taxRate' + id+ '" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getTotalPrice(this);"></td>' + 
//            '<td><span id="tax' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>' + 
//            '<td><span id="total' + id+ '" class="grid_text grid_readonly_text my_radius"></span></td>'+ 
//            '<td><input id="isGift' + id + '" type="checkbox" onclick="resetPrice(this);"></td>' + 
//            '<td><input id="remark' + id+ '" class="input_text_common my_radius grid_text"></td></tr>';
//            $(dom).append(row);
//            parent.siping.eventBinding(parent.$("#" + id + " *"));
//        }
//        // 清除保存在全局变量中的materialList
//        parent.materialList = [];
//        parent.siping.close(partnerIndex);
//    } else {
//        siping.alert(0, "请选择物料");
//    }
//}

function addSaleOrderRow(dom){
    var rowInfo = getMaterialsHtml(dom,'addSaleOrderRow','chooseSaleOrderMaterial');
    var row = rowInfo.materialsHtml;
    var rowId = rowInfo.rowId;
    row +=
        '<td><input name="counts" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="sellPrice" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="discount" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="afterDiscount" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="taxRate" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><span name="tax" onkeyup="getTotalAmount(this);" class="grid_text grid_readonly_text my_radius"></span></td>' +
        '<td><span name="total" class="grid_text grid_readonly_text my_radius"></span></td>' +
        '<td><input name="isGift" onclick="reset(this);" type="checkbox"></td>' +
        '<td><input name="shipmentsAmount" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="remark" class="input_text_common my_radius grid_text"></td></tr>';
    $(dom).parent().parent().parent().append(row);
    siping.eventBinding($("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
    parent.siping.eventBinding(parent.$("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
}

function addDeliverySaleOrderRow(dom){
    var rowInfo = getMaterialsHtml(dom,'addDeliverySaleOrderRow','chooseDeliverySaleOrderMaterial');
    var row = rowInfo.materialsHtml;
    var rowId = rowInfo.rowId;
    row +=
        '<td><input name="counts" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="sellPrice" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="discount" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="afterDiscount" onblur = "getInverseDiscount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><input name="taxRate" data-type="number" onkeyup="getTotalAmount(this);" class="input_text_common my_radius grid_text"></td>' +
        '<td><span name="tax" onkeyup="getTotalAmount(this);" class="grid_text grid_readonly_text my_radius"></span></td>' +
        '<td><span name="total" class="grid_text grid_readonly_text my_radius"></span></td>' +
        '<td><input name="isGift" onclick="reset(this);" type="checkbox"></td>' +
        '<td><input name="remark" class="input_text_common my_radius grid_text"></td></tr>';
    $(dom).parent().parent().parent().append(row);
    siping.eventBinding($("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
    parent.siping.eventBinding(parent.$("#dataSource").find("tr[id = '"+rowId+"']").find("input"));
}