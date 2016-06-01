var partnerIndex = parent.layer.getFrameIndex(window.name); // 获取窗口索引
var materialList = []; // 全局变量保存所选商品信息
/** 全局变量保存批次号信息 * */
var batchNumberData = "";
batchNumberData = parent.document.getElementById("hide_batch_number_data");
/** 将批次号信息赋值到弹出框 * */
$("#show_batch_number").html(batchNumberData);
$(function(){
    /** 默认单据日期为当前日期 **/
    if($("#billsDate").val() == ""){
        var date = new Date();
        var timestamp = date.getTime();
        $("#billsDate").val(laydate.now(timestamp));
    }
});
// 弹出partner选择框
function openWinGetPartner(type) { // 客户为 1 供应商 为2
    var title;
    if (type == 1) {
        title = "选择客户";
    } else {
        title = "选择供应商";
    }
    siping.openWindow('/businesspartner/openwin?partnerType=' + type, '900px', '450px', title);
}
// 弹出partner选择框
function openWinGetShop() { // 客户为 1 供应商 为2
    siping.openWindow('/shop/openwin', '70%', '70%', "选择商家");
}
//弹出user选择框
function openWinGetUser() {
    siping.openWindow('/usr/openwin', '900px', '450px', '选择用户');
}
// 选择供应商或客户
function choosePartner() {
    var id;
    var checkbox = $("input[name='check_box']:checked");
    if(checkbox.length==0){
        siping.alert(0,"请选择供应商!");
        return;
    }
    for (var i = 0; i < checkbox.length; i++) {
        id = checkbox[i].id;
    }
    if (id != "") {
        var idArray = [ "id", "partnerCode", "partnerName", "contactsLastName", "contactsFirstName", "contactsMobilephone" ];
        for (var i = 0; i < idArray.length; i++) {
            var thisId = idArray[i];
            if (thisId == "partnerCode") {
                parent.$("#" + thisId).val($("#" + thisId + id).text());
            }
            if (thisId == "partnerName"){
                parent.$("#" + thisId).val($("#" + thisId + id).text());
                parent.$("#manufacturer").val($("#" + thisId + id).text());//生产商赋相同的值
            }
            parent.$("#" + thisId).text($("#" + thisId + id).text());
        }
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择");
    }
}

function chooseAllMaterialPartner() {//由于id号冲突，新增此方法，此方法不通用
    var id;
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        id = checkbox[i].id;
    }
    if (id != "") {
        var idArray = [ "id", "partnerName"];
        for (var i = 0; i < idArray.length; i++) {
            var thisId = idArray[i];
            if(i==0){
                parent.$("#supplierId").val($("#" + thisId + id).text());
            }
            else if(i==1){
                parent.$("#supplierName").val($("#" + thisId + id).text());
            }
        }
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择");
    }
}
function chooseShop() {
    var id;
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        id = checkbox[i].id;
    }
    if (id != "") {
        var idArray = [ "shopName", "shops"];
        for (var i = 0; i < idArray.length; i++) {
            var thisId = idArray[i];
                parent.$("#" + thisId).val($("#" + thisId + id).text());
        }
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择");
    }
}

//选择商品类型
function chooseMaterialType() {

}

// 弹出商品选择框
function openWinGetMaterial(functionName,isPurchase,isSell,isInventory) {
    siping.openWindow('/material/openwin?functionName=' + functionName +'&isPurchase='+isPurchase+'&isSell='+isSell+'&isInventory='+isInventory, '70%', '70%', "选择商品");
}
function openWinGetMaterials(functionName,isPurchase,isSell,isInventory,dom) {
    var rowId = $(dom).parent().parent().parent().attr("id");
    if(typeof(rowId)=='undefined'){
        rowId = 1;
    }
    siping.openWindow('/material/openwin?functionName=' + functionName +'&isPurchase='+isPurchase+'&isSell='+isSell+'&isInventory='+isInventory+'&rowId='+rowId, '900px', '450px', "选择商品");
}
// 保存商品对象
function addChooseMaterialItem(step) {
    if ($("#" + step).is(':checked')) {
        var item = {};
        item.id = $("#materialId" + step).text();
        item.materialNo = $("#materialNo" + step).text();
        item.materialName = $("#materialName" + step).text();
        item.barcode = $("#barcode" + step).text();
        item.specificationsModel = $("#specificationsModel" + step).text();
        item.unitName = $("#unitName" + step).text();
        item.brand = $("#brand" + step).text();
        item.season = $("#season" + step).text();
        item.isBatch = $("#isBatch" + step).text();
        item.expirationDate = $("#expirationDate" + step).text();
        item.minOrder = $("#minOrder" + step).text();
        parent.materialList.push(item);
    } else {
        for (var i = 0; i < parent.materialList.length; i++) {
            var material = parent.materialList[i];
            materialId = step.substr(1); // 去掉下划线得到商品id
            if (materialId == material.id) {
                parent.materialList.remove(i); // 调用Array.prototype.remove
                $("input[name = 'materialIds'][value = "+materialId+"]").remove();
            }
        }
    }
}
//选择上级菜单
function chooseParentMenu() {
    var id;
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        id = checkbox[i].id;
    }
    if (id != "") {
        var idArray = [ "menuId", "menuName" ];
        for (var i = 0; i < idArray.length; i++) {
            var thisId = idArray[i];
            if (thisId == "menuId") {
                parent.$("#parentMenuId").val($("#" + thisId +"_"+ id).text());
            }
            if (thisId == "menuName"){
                parent.$("#parentMenuName").val($("#" + thisId +"_"+ id).text());
            }
        }
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择");
    }
}

function chooseUser(step) {
    var id = "";
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        id = checkbox[i].id;
    }
    if (id != "") {
        parent.$("#nickName").val($("#nickName" + id).text());
        parent.$("#userId").val($("#userId" + id).val());
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择申请人");
    }
}
Array.prototype.remove = function(obj) {
    for (var i = 0; i < this.length; i++) {
        var temp = this[i];
        if (!isNaN(obj)) {
            temp = i;
        }
        if (temp == obj) {
            for (var j = i; j < this.length; j++) {
                this[j] = this[j + 1];
            }
            this.length = this.length - 1;
        }
    }
}
function selectThisTr(id) {
    var checkbox = $("input[name='check_box']:checked");
    checkbox.attr("checked", false);
    $("#" + id).attr("checked", "checked");
}
function doNothing() {

}

function getBatchNumList(dom) {
    // 获取被选择商品的所有批次号
    var rowDom = $(dom).parent().parent();
    var rowId = $(rowDom).attr("id");
    var storageId = $("#inboundStorageId").val();
    var materialId = $(rowDom).find("input[name = 'materialId']").val();
    $(rowDom).find("input[name = 'batchNumber']").attr('disabled','true');
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
                    $(rowDom).find("input[name = 'batchNumber']").val(data[0].batchNumber);
                    $(rowDom).find("span[name = 'productDate']").text(data[0].productionDate);
                    $(rowDom).find("input[name = 'batchCounts']").val(data[0].counts);
                }
            } else {
                var row = '<table id="dso_batch_number">';
                for (var i = 0; i < data.length; i++) {
                    row += '<tr ondblclick="chooseBatchNum(this);"><td width="16%">' + data[i].batchNumber + '</td><td width="16%">' + data[i].productionDate + '</td><td width="16%">'
                            + data[i].expirationDate + '<input name="batchCounts" type="hidden" value="'+data[i].counts+'"></td></tr>';
                }
                row += '</table>';
                $("#hide_batch_number_data").html(row); // 在添加页面需添加一个隐藏域
                siping.openWindow('/material/getmaterialbatchnumberlist?rowId=' + rowId, '50%', '350px', "选择批次号(双击选择)");
            }
            $(rowDom).find("input[name = 'batchNumber']").removeAttr("disabled"); 
        }
    });
}

function chooseBatchNum(dom) {
    var batchNumber = dom.firstChild.innerText;
    var productionDate = dom.firstChild.nextSibling.innerText;
    var rowId = $("#rowId").val();
    var rows = parent.$("#dataSource").children();
    parent.$(rows).eq(rowId-1).find("input[name = 'batchNumber']").val(batchNumber);
    parent.$(rows).eq(rowId-1).find("span[name = 'productDate']").text(productionDate);
    parent.$(rows).eq(rowId-1).find("input[name = 'batchCounts']").val($(dom).find("input[name='batchCounts']").val());
    parent.siping.close(deliveryIndex);
}
//TODO 旧版本的商品html 以后删掉
function getMaterialHtml(material,id){
    var img = '<img onclick="removeGridRowData(this)" src="'+staticPath+'/icons/tree_show.png">';
    var materialHtml = '<tr id="' + id + '" class="grid_row"><td>' + img + '</td><td><span id="materialNo'+id+'" class="grid_text grid_readonly_text my_radius">'+material.materialNo+'</span><span id="id'+id+'" style="display:none;">'+material.id+'</span></td>'+
    '<td><span id="materialName'+id+'" class="grid_text grid_readonly_text my_radius">'+material.materialName+'</span></td>'+
    '<td><span id="specificationsModel'+id+'" class="grid_text grid_readonly_text my_radius">'+material.specificationsModel+'</span></td>'+
    '<td><span id="brand'+id+'" class="grid_text grid_readonly_text my_radius">'+material.brand+'</span></td>'+
    '<td><span id="season'+id+'" class="grid_text grid_readonly_text my_radius">'+material.season+'</span></td>'+
    '<td><span id="barcode'+id+'" class="grid_text grid_readonly_text my_radius">'+material.barcode+'</span></td>' + 
    '<td><span id="unitName' + id + '" class="grid_text grid_readonly_text my_radius">'+material.unitName+'</span></td>';
    return materialHtml;
}
function getMaterialsHtml(dom,addRowFunction,chooseMaterialFcuntion){
    var row = {};
    var id = Number($(dom).parent().parent().parent().find("tr:last-child").attr("id"));
    var moreDiv = $(dom).parent().parent().find("div.more").eq(0).prop("outerHTML");
    var materialUse = $(dom).parent().parent().find("input[name = 'materialNo']").eq(0).attr("data-type");
    var imgDel = '<img onclick="removeGridRow(this)" src="' + staticPath + '/icons/tree_show.png">';
    var imgAdd = '<img onclick= "'+addRowFunction+'(this);" src="'+staticPath+'/icons/tree_hide.png">';
    id++;
    var materialsHtml = '<tr id= "'+id+'"><td width="20px">'+id+'</td>' +
    '<td>'+imgAdd+imgDel+'</td>' +
    '<td><div class="common_text_val common_text_val_table"><input data-type="'+materialUse+'" data-order-type = "'+chooseMaterialFcuntion+'" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" name = "materialId">'+moreDiv+'</div></td>' +
    '<td><span name="materialName" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="brand" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="season" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="barcode" class="grid_text grid_readonly_text my_radius"></span></td>' +
    '<td><span name="unitName" class="grid_text grid_readonly_text my_radius"></span><input type = "hidden" name = "minOrder"></input></td>';
    row.materialsHtml = materialsHtml;
    row.rowId =id;
    return row;
}
//分发选择商品后调用的方法
//TODO 旧版商品选择框以后删掉
function chooseMaterial(functionName) {
    if (functionName == "chooseReturnPurchaseMaterial") {
        chooseReturnPurchaseMaterial();
    } else if (functionName == 'chooseStorageReceiptMaterial') {
        chooseStorageReceiptMaterial();
    } else if (functionName == 'chooseSaleOrderMaterial') {
        chooseSaleOrderMaterial();
    } else if (functionName == 'chooseDeliverySaleOrderMaterial') {
        chooseDeliverySaleOrderMaterial();
    } else if (functionName == 'chooseApplyPurchaseOrderMaterial') {
        chooseApplyPurchaseOrderMaterial();
    } else if (functionName == 'choosePurchaseOrderMaterial') {
        choosePurchaseOrderMaterial();
    } else if (functionName == 'chooseReceiptPurchaseOrderMaterial') {
        chooseReceiptPurchaseOrderMaterial();
    } else if (functionName == 'chooseReturnSaleOrderMaterial'){
        chooseReturnSaleOrderMaterial();
    } else if (functionName == 'chooseInventoryDeliveryMaterial') {
        chooseInventoryDeliveryMaterial();
    } else if(functionName == 'chooseInventoryCheckMaterial'){
        chooseInventoryCheckMaterial();
    }
}



function chooseMaterials(functionName , rowId) {
//    不再验证重复
//    var resultMsg = validateMaterialIds();
//    if(resultMsg.contains){
//        siping.alert(0,resultMsg.materialName+",在第"+resultMsg.row+"行已存在");
//        return;
//    }
    switch(functionName)
    {
    case 'chooseReceiptPurchaseOrderMaterial':
        chooseReceiptPurchaseOrderMaterial(rowId);
        break;
    case 'chooseApplyPurchaseOrderMaterial':
        chooseApplyPurchaseOrderMaterial(rowId);
        break;
    case 'choosePurchaseOrderMaterial':
        choosePurchaseOrderMaterial(rowId);
        break;
    case 'chooseReturnPurchaseMaterial':
        chooseReturnPurchaseMaterial(rowId);
        break;
    case 'chooseSaleOrderMaterial':
        chooseSaleOrderMaterial(rowId);
        break;
    case 'chooseReturnSaleOrderMaterial':
        chooseReturnSaleOrderMaterial(rowId);
        break;
    case 'chooseDeliverySaleOrderMaterial':
        chooseDeliverySaleOrderMaterial(rowId);
        break;
    case 'chooseInventoryCheckMaterial':
        chooseInventoryCheckMaterial(1);
        break;
    }
}

/** 计算价格 **/
function getTotalAmount(dom){
    var tableDom = $("#dataSource");
    var rowDom = $(dom).parent().parent();
    if($(rowDom).find("input[name = 'materialId']").val() != ""){ //若商品id为空者此行不参与计算
        var counts = $(rowDom).find("input[name='counts']").val(); // 数量
        var price;
        if($(rowDom).find("input[name='sellPrice']").exist()){ // 判断是采购价or销售价
            price = $(rowDom).find("input[name='sellPrice']").val(); // 销售价格
        }else{
            price = $(rowDom).find("input[name='purchasePrice']").val(); // 采购价
        }
        var discount = $(rowDom).find("input[name='discount']").val() == "" ? 100 : $(rowDom).find("input[name='discount']").val(); // 折扣默认为100
        var afterDiscount; // 折后金额
        var taxRate = $(rowDom).find("input[name='taxRate']").val(); // 税率
        var tax =0; //税额默认为 0
        var total = $(rowDom).find("span[name='total']").text(); // 行总价
        var totalPrice = 0; // 总价
        var giftPrice= 0; // 赠品价
        var favorablePrice = $("#favorablePrice").val() == "" ? 0 : $("#favorablePrice").val(); // 优惠
        if(dom != "favorablePrice"){ // 若修改优惠价格，直接总价减去优惠价得到应付金额
            if(counts !="" && price !=""){
                afterDiscount = price * counts * discount/100; // 得到折后金额
                if(taxRate != ""){
                    tax = afterDiscount * taxRate/100;
                }
                $(rowDom).find("input[name='afterDiscount']").val(afterDiscount.toFixed(3));
                $(rowDom).find("span[name='tax']").text(tax.toFixed(3));
                $(rowDom).find("span[name='total']").text((afterDiscount + tax).toFixed(3));
                $(tableDom).children("tr").each(function(){
                    if($(this).find("input[name='materialId']").val() !=""){
                        if ($(this).find("input[name='isGift']").is(':checked')) {
                            giftPrice += $(this).find("span[name='total']").text() == "" ? 0 : parseFloat($(this).find("span[name='total']").text());
                        }else{
                            totalPrice += $(this).find("span[name='total']").text() == "" ? 0 : parseFloat($(this).find("span[name='total']").text());
                        }
                    }
                });
                $("#totalPrice").text(totalPrice.toFixed(3));
                $("#giftPrice").text(giftPrice.toFixed(3));
                if($('#gatheringPrice').exist()){
                    $("#gatheringPrice").text((totalPrice-favorablePrice).toFixed(3));
                }else{
                    $("#payPrice").text((totalPrice-favorablePrice).toFixed(3));
                }
            }else {
                $(rowDom).find("input[name='taxRate']").val("");
                $(rowDom).find("input[name='afterDiscount']").val("");
                $(rowDom).find("span[name='tax']").text("");
                $(rowDom).find("span[name='total']").text("");
                $("#totalPrice").text("");
                $("#giftPrice").text("");
                $("#payPrice").text("");
                $("#gatheringPrice").text("");
            }
        }else{
            if( Number($("#totalPrice").text()) - favorablePrice < 0){
                siping.tips("单据优惠金额必须小于总计金额","favorablePrice");
                $("#favorablePrice").val("0.00");
                favorablePrice = 0.00;
            }
            totalPrice = $("#totalPrice").text();
            if($('#gatheringPrice').exist()){
                $("#gatheringPrice").text((totalPrice-favorablePrice).toFixed(3));
            }else{
                $("#payPrice").text((totalPrice-favorablePrice).toFixed(3));
            }
        }
    }
}
function getTotalPrice(dom){
    var rowId = $(dom).parent().parent().attr("id");
    var counts = $("#counts" + rowId).val(); // 数量
    var price;
    if($('#sellPrice'+rowId).exist()){ // 判断是采购价or销售价
        price = $("#sellPrice" + rowId).val(); // 销售价格
    }else{
        price = $("#purchasePrice" + rowId).val(); // 采购价
    }
    var discount = $("#discount" + rowId).val() == "" ? 100 : $("#discount" + rowId).val(); // 折扣默认为100
    var afterDiscount; // 折后金额
    var taxRate = $("#taxRate" + rowId).val(); // 税率
    var tax =0; //税额默认为 0
    var total = $("#total" + rowId).val(); // 行总价
    var totalPrice = 0; // 总价
    var giftPrice= 0; // 赠品价
    var favorablePrice=$("#favorablePrice").val() == "" ? 0 : $("#favorablePrice").val(); // 优惠
    if(dom != "favorablePrice"){ // 若修改优惠价格，直接总价减去优惠价得到应付金额
        afterDiscount = price * counts * discount/100; // 得到折后金额
        if(taxRate != ""){
            tax = afterDiscount * taxRate/100;
        }
        $("#afterDiscount" + rowId).val(afterDiscount.toFixed(3));
        $("#tax" + rowId).text(tax.toFixed(3));
        $("#total" + rowId).text((afterDiscount + tax).toFixed(3));
        var items = $(dom).parent().parent().parent().children("tr").length;
        for (var i = 1; i <= items; i++) {
            // 排除赠品
            if ($('#isGift'+i).is(':checked')) {
                giftPrice += $("#total" + i).text() == "" ? 0 : parseFloat($("#total" + i).text());
            }else{
                totalPrice += $("#total" + i).text() == "" ? 0 : parseFloat($("#total" + i).text());
            }
        }
        $("#totalPrice").text(totalPrice.toFixed(3));
        $("#giftPrice").text(giftPrice.toFixed(3));
        if($('#gatheringPrice').exist()){
            $("#gatheringPrice").text((totalPrice-favorablePrice).toFixed(3));
        }else{
            $("#payPrice").text((totalPrice-favorablePrice).toFixed(3));
        }
    }else{
        totalPrice = $("#totalPrice").text();
        if($('#gatheringPrice').exist()){
            $("#gatheringPrice").text((totalPrice-favorablePrice).toFixed(3));
        }else{
            $("#payPrice").text((totalPrice-favorablePrice).toFixed(3));
        }
    }
}

//点击赠品重置总价
function resetPrice(dom) {
    var rowId = $(dom).parent().parent().attr("id");
    if ($('#isGift' + rowId).is(':checked')) {
        if($('#purchasePrice'+rowId).exist()){
            $("#purchasePrice" + rowId).val(0);
        }else{
            $("#sellPrice" + rowId).val(0);
        }
    }
    getTotalPrice(dom);
}
function reset(dom) {
    var rowDom = $(dom).parent().parent();
    if ($(rowDom).find("input[name='isGift']").is(':checked')) {
        if($(rowDom).find("input[name='purchasePrice']").exist()){
            $(rowDom).find("input[name='purchasePrice']").val(parseFloat("0.00").toFixed(3));
        }else{
            $(rowDom).find("input[name='sellPrice']").val(parseFloat("0.00").toFixed(3));
        }
        $(rowDom).find("input[name='discount']").val(parseFloat("100.00").toFixed(3));
        $(rowDom).find("input[name='taxRate']").val(parseFloat("0.00").toFixed(3));
    }
    getTotalAmount(dom);
}

// 通用折扣计算
function getDiscount(dom){
    var rowDom = $(dom).parent().parent();
    if($(rowDom).find("input[name = 'materialId']").val() != ""){ //若商品id为空者此行不参与计算
        var price;
        var counts = $(rowDom).find("input[name='counts']").val(); // 数量
        var afterDiscount = $(rowDom).find("input[name='afterDiscount']").val(); //折后金额
        if(afterDiscount != "" && afterDiscount != 0){ // 折后金额为空或者为0重新计算
            if($(rowDom).find("input[name='sellPrice']").exist()){ // 判断是采购价or销售价
                price = $(rowDom).find("input[name='sellPrice']").val(); // 销售价格
            }else{
                price = $(rowDom).find("input[name='purchasePrice']").val(); // 采购价
            }
            var discount = afterDiscount/(price * counts) * 100;
            $(rowDom).find("input[name='discount']").val(discount.toFixed(3));
        }else {
            $(rowDom).find("input[name='discount']").val(parseFloat("100.00").toFixed(3));
        }
        getTotalAmount(dom);
    }
}
function getInverseDiscount(dom){
    var rowId = $(dom).parent().parent().attr("id");
    var price;
    var counts = $("#counts" + rowId).val(); // 数量
    var afterDiscount = $("#afterDiscount" + rowId).val(); //折后金额
    if(afterDiscount != "" && afterDiscount != 0){ // 折后金额为空或者为0重新计算
        if($('#sellPrice'+rowId).exist()){ // 判断是采购价or销售价
            price = $("#sellPrice" + rowId).val(); // 销售价格
        }else{
            price = $("#purchasePrice" + rowId).val(); // 采购价
        }
        var discount = afterDiscount/(price * counts) * 100;
        
        $("#discount" + rowId).val(discount.toFixed(3) == 100.00 ? "" : discount.toFixed(3));
    }
    getTotalPrice(dom);
}

// 判断dom中是否存在某节点
(function($) {
    $.fn.exist = function(){ 
        if($(this).length>=1){
            return true;
        }
        return false;
    };
})(jQuery);

// 库存单通用计算总价
function getInventoryTotal(dom) {
    var rowId = $(dom).parent().parent().attr("id");
    var counts = $("#counts" + rowId).val();
    var price;
    if ($('#purchasePrice'+rowId).exist()){
        price = $("#purchasePrice" + rowId).val();
    }else{
        price = $("#sellPrice" + rowId).val();
    }
    if (counts != "" & price != "") {
        var total = counts * price;
        $("#total" + rowId).html(total);
        var count = $(dom).parent().parent().parent().children("tr").length;
        var totalPrice = 0;
        var totalGiftPrice = 0;
        for (var c = 1; c <= count; c++) {
            // 排除赠品
            if (!$('#isGift' + c).is(':checked')) {
                totalPrice += parseInt($("#total" + c).html());
            } else {
                totalGiftPrice += parseInt($("#total" + c).html());
            }
        }
        $("#totalPrice").html(totalPrice);
        $("#totalGiftPrice").html(totalGiftPrice);
    }
}

//点击赠品重置总价
function resetInventoryTotal(dom) {
    var rowId = $(dom).parent().parent().attr("id");
    if ($('#isGift' + rowId).is(':checked')) {
        if($('#purchasePrice'+rowId).exist()){ // 库存收货单是采购价 ,库存发货单是销售价
            $("#purchasePrice" + rowId).val(0);
        }else{
            $("#sellPrice" + rowId).val(0);
        }
    }
    getInventoryTotal(dom);
}
/** 计算价格结束 **/
/** 添加商品时先准备好所需的列 **/
function getNeedAddRows(materialList,dom,rowId){
    // 计算当前列表中空白行
    var spaceRows =0 ;
    $(dom).children("tr").each(function(){
        if(parseInt($(this).attr("id")) >= rowId ){ // 从rowId行开始计算
            if($(this).find("input[name = 'materialId']").val()==""){
                spaceRows ++;
            };
        }
    });
    var needAddRow = materialList.length - spaceRows;
    return needAddRow;

}
/** 结束 **/
/** 填充商品信息 **/
function fillMaterialData(dom , material) {
    $(dom).find("input[name='materialNo']").val(material.materialNo);
    $(dom).find("input[name='materialId']").val(material.id);
    $(dom).find("span[name='materialName']").text(material.materialName);
    $(dom).find("span[name='specificationsModel']").text(material.specificationsModel);
    $(dom).find("span[name='brand']").text(material.brand);
    $(dom).find("span[name='season']").text(material.season);
    $(dom).find("span[name='barcode']").text(material.barcode);
    $(dom).find("span[name='unitName']").text(material.unitName);
    $(dom).find("input[name='minOrder']").val(material.minOrder);
    $(dom).find("input[name='currencyPrice']").val(parseFloat("0.00").toFixed(3)); //外币单价
    $(dom).find("input[name='counts']").val(parseFloat("0.00").toFixed(3));
    $(dom).find("input[name='sellPrice']").val(parseFloat("0.00").toFixed(3));
    $(dom).find("input[name='purchasePrice']").val(parseFloat("0.00").toFixed(3));
    $(dom).find("input[name='discount']").val(parseFloat("100.00").toFixed(3));
    $(dom).find("input[name='afterDiscount']").val(parseFloat("0.00").toFixed(3));
    $(dom).find("input[name='taxRate']").val(parseFloat("0.00").toFixed(3));
    $(dom).find("span[name='tax']").text(parseFloat("0.00").toFixed(3));
    $(dom).find("span[name='total']").text(parseFloat("0.00").toFixed(3));
}
/** 结束 **/
/** 模糊查询商品信息 **/
function getSelectMaterial(dom,code,name,url,idArray,rowid,materialType){
    var v = dom.value;
    var rowDom = $(dom).parent().parent().parent(); //tr
    siping.ajax({
        method : 'get',
        url : url,
        async : true,
        dataType : "json",
        data : {
            keyword : v,
            isPurchase : materialType == "isPurchase" ? true : false,
            isSell : materialType == "isSell" ? true : false,
            isInventory : materialType == "isInventory" ? true : false
        },
        success : function(data) {
            var _left = $(dom).offset().left;
            var _top = $(dom).offset().top;
            var _width = $(dom).height();
            var list = '<div class="li_div my_radius">';
            for(var i=0;i<data.length;i++){
                list += '<div class="li_span"><span class="li_code">'+data[i][code]+'</span>-<span>'+data[i][name]+'</span></div>'
            }
            list += '</div>';
            $(".li_div").remove();
            $(dom).after(list);
            $(".li_div").css({"left":_left,"top":_top+_width+3});
            $(".li_div").delegate(".li_span", "click", function(e){
                var licode = $(this).children(".li_code").text();
                //清空当前行所有input和span数据
                $(rowDom).find("input").val("");
                $(rowDom).find("span").text("");
                $(".li_div").remove();
                for(var i=0;i<data.length;i++){
                    if(data[i][code] == licode){
                        // 不再验证是否有商品重复
                        // var resultMsg = validateMaterialId(data[i]['id']);
                        // if(resultMsg.contains){
                        // siping.alert(0,resultMsg.materialName+",在第"+resultMsg.row+"行已存在");
                        //    break;
                        // }
                        // 为当前行添加默认值
                        $(rowDom).find("input[name='counts']").val(parseFloat("0.00").toFixed(3));
                        $(rowDom).find("input[name='purchasePrice']").val(parseFloat("0.00").toFixed(3));
                        $(rowDom).find("input[name='sellPrice']").val(parseFloat("0.00").toFixed(3));
                        $(rowDom).find("input[name='discount']").val(parseFloat("100.00").toFixed(3));
                        $(rowDom).find("input[name='afterDiscount']").val(parseFloat("0.00").toFixed(3));
                        $(rowDom).find("input[name='taxRate']").val(parseFloat("0.00").toFixed(3));
                        $(rowDom).find("span[name='tax']").text(parseFloat("0.00").toFixed(3));
                        $(rowDom).find("span[name='total']").text(parseFloat("0.00").toFixed(3));
                        $(rowDom).find("input[name='isGift']").attr("checked",false);
                        $(dom).val(licode);
                        for(var j=0;j<idArray.length;j++){
                            var thisId = idArray[j];
                            if (data[i][thisId]!=null && data[i][thisId]!=undefined)
                                // 向表单中赋值
                                switch(thisId){
                                    case 'id' :
                                        $(rowDom).find("input[name='materialId']").val(data[i][thisId]);
                                        break;
                                    case 'minOrder' :
                                        $(rowDom).find("input[name='minOrder']").val(data[i][thisId]);
                                        break;
                                    case 'expirationDate' :
                                        $(rowDom).find("input[name='expirationDate']").val(data[i][thisId]);
                                        break;
                                    default :
                                        $(rowDom).find("span[name='"+thisId+"']").text(data[i][thisId]);
                                }
                        }
                        // 根据不同单据页面做相应处理
                        var orderType= $(dom).attr("data-order-type");
                        switch(orderType){
                            case 'chooseReceiptPurchaseOrderMaterial' :
                                if(data[i]['isBatch'] == false){
                                    $(rowDom).find("input[name='batchNumber']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                                    $(rowDom).find("input[name='productDate']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                                    $(rowDom).find("input[name='dueDate']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                                }else {
                                    $(rowDom).find("input[name='batchNumber']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                                    $(rowDom).find("input[name='productDate']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                                    $(rowDom).find("input[name='dueDate']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                                }
                                break;
                            case 'chooseReturnPurchaseMaterial' :
                                if(data[i]['isBatch'] == false){
                                    $(rowDom).find("input[name='batchNumber']").attr({"class":"grid_text grid_readonly_text my_radius","disabled":true});
                                }else {
                                    $(rowDom).find("input[name='batchNumber']").attr({"class":"input_text_common my_radius grid_text","disabled":false});
                                }
                                break;
                            case 'chooseApplyPurchaseOrderMaterial' :
                                $(rowDom).find("input[name = 'lastDate']").val($("#applypo_lastDate").val());
                                break;
                        }
                        break;
                    }
                }
            });
        }
    });
}
/** 结束 **/
/** 计算生产日期到期日期 **/
function getDueDate(date,rowId){
    var dom = $("#dataSource");
    var rowDom = $(dom).find("tr[id='"+rowId+"']");
    if($(rowDom).find("input[name = 'materialId']").val() != ""){
        var productDate = $(rowDom).find("input[name = 'productDate']").val();
        var expirationDate = $(rowDom).find("input[name = 'expirationDate']").val();
        var newDate = addDate(productDate,expirationDate);
        var timestamp = newDate.getTime();
        $(rowDom).find("input[name = 'dueDate']").val(laydate.now(timestamp));
    }
}
function getProductDate(date,rowId){
    var dom = $("#dataSource");
    var rowDom = $(dom).find("tr[id='"+rowId+"']");
    if($(rowDom).find("input[name = 'materialId']").val() != ""){
        var dueDate = $(rowDom).find("input[name = 'dueDate']").val();
        var expirationDate = $(rowDom).find("input[name = 'expirationDate']").val();
        var newDate = addDate(dueDate,-expirationDate);
        var timestamp = newDate.getTime();
        $(rowDom).find("input[name = 'productDate']").val(laydate.now(timestamp));
    }
}
function addDate(date,days){
    var newDate = new Date(date)
    newDate = newDate.valueOf()
    newDate = newDate + days * 24 * 60 * 60 * 1000
    newDate = new Date(newDate)
    return newDate;
}
/** 结束 **/
/** 复制从 **/
function getOrderCopyFrom(orderType){
    siping.openWindow(orderType+"/copyfrom","70%","70%","选择单据");
}
function copyApplyPO(){
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        parent.window.location.href=createUrl('/purchaseapplyorder/copyto?orderNumber=' + ids);
    } else {
        siping.alert(0, "请选择需要复制的列！");
    }
}
function copyPO(){
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        parent.window.location.href=createUrl('/purchaseorder/copyto?orderNumber=' + ids);
    } else {
        siping.alert(0, "请选择需要复制的列！");
    }
}
function copyPRO(){
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids[i] = checkbox[i].id;
    }
    if (ids != "") {
        parent.window.location.href=createUrl('/purchaseorderreceipt/copyto?orderNumber=' + ids);
    } else {
        siping.alert(0, "请选择需要复制的列！");
    }
}
/** 结束 **/
/** 数据验证 **/
/** 验证必填项不能为空 **/
function validateNotNull(ids){
    var resultMsg = {};
    resultMsg.success = true;
    for(var i=0;i<ids.length;i++){
        var id = ids[i];
        if($.trim($("#"+id).val())==""){
            resultMsg.id = id;
            resultMsg.success = false;
            return resultMsg;
        }
    }
    return resultMsg;
}
/** 验证表体数据（单价，数量等） **/
function validateTable(names){
    var resultMsg = {};
    resultMsg.success = true;
    var rows = $("#dataSource").children().length;
    for (var i = 1 ; i <= rows ; i ++) {
        var rowDom = $("#dataSource").find("tr[id = '"+i+"']");
        if($(rowDom).find("input[name = 'materialId']").val() != ""){
            for(var j = 0 ; j < names.length ; j ++){
                // 验证单价，数量不能为空或0
                var name = names[j]
                var tableVal = $(rowDom).find("input[name = '"+name+"']").val();
                if (name == "counts" ){
                    if($.trim(tableVal) == "" || Number(tableVal) == 0 ){
                        resultMsg.success = false;
                        resultMsg.msg = "第"+$(rowDom).attr("id")+"行商品"+$(rowDom).find("span[name = 'materialName']").text() + ",数量不能为空或0";
                        return resultMsg;
                    }
                    if(contains(names,"minOrder")){
                        var minOrder = $(rowDom).find("input[name = 'minOrder']").val();
                        if(Number(tableVal) < minOrder){
                            resultMsg.success = false;
                            resultMsg.msg = "第"+$(rowDom).attr("id")+"行商品"+$(rowDom).find("span[name = 'materialName']").text() + ",小于最小起订量"+minOrder;
                            return resultMsg;
                        }
                    }
                    if(contains(names,"referencedAmount")){
                        var notReferencedAmount = $(rowDom).find("input[name = 'notReferencedAmount']").val();
                        if(Number(tableVal) > notReferencedAmount){
                            resultMsg.success = false;
                            resultMsg.msg = "第"+$(rowDom).attr("id")+"行商品"+$(rowDom).find("span[name = 'materialName']").text() + ",不能超出未引用数量"+notReferencedAmount;
                            return resultMsg;
                        }
                    }
                }
                if (name == "purchasePrice"){
                    if(($.trim(tableVal) == "" || Number(tableVal) == 0) && $(rowDom).find("input[name='isGift']").is(':checked') == false){
                        resultMsg.success = false;
                        resultMsg.msg = "第"+$(rowDom).attr("id")+"行商品"+$(rowDom).find("span[name = 'materialName']").text() + ",单价不能为空或0";
                        return resultMsg;
                    }
                }
                //验证批次号
                if (name == "batchNumber") {
                    if($(rowDom).find("input[name = 'batchNumber']").attr("disabled")!="disabled"){
                        if($.trim(tableVal) == ""){
                            resultMsg.success = false;
                            resultMsg.msg = "第"+$(rowDom).attr("id")+"行商品"+$(rowDom).find("span[name = 'materialName']").text() + "，批次号不能为空";
                            return resultMsg;
                        }
                        if( contains(names,"productDate") && contains(names,"dueDate")){
                            if($(rowDom).find("input[name = 'productDate']").val() == ""){
                                resultMsg.success = false;
                                resultMsg.msg = "第"+$(rowDom).attr("id")+"行商品"+$(rowDom).find("span[name = 'materialName']").text() + "，生产日期不能为空";
                                return resultMsg;
                            }
                            if($(rowDom).find("input[name = 'dueDate']").val() == ""){
                                resultMsg.success = false;
                                resultMsg.msg = "第"+$(rowDom).attr("id")+"行商品"+$(rowDom).find("span[name = 'materialName']").text() + "，到期日期不能为空";
                                return resultMsg;
                            }
                        }
                    }
                }
            }
        }
    }
    return resultMsg;
}
function contains(arr, str) {
    var i = arr.length;
    while (i--) {
       if (arr[i] === str) {
       return true;
       }
    }
    return false;
}
// 验证模糊选择商品是否重复
function validateMaterialId(materialId){
    var resultMsg = {};
    resultMsg.contains = false;
    var materialIds = [];
    $("#dataSource tr").each(function(){
        var materialId = $(this).find("input[name = 'materialId']").val();
        if(materialId != ""){
            materialIds.push(materialId);
        }
    });
    var size = materialIds.length;
    for(var i = 1 ; i<=size ; i ++){
        if (materialIds[i-1] == materialId) {
            resultMsg.contains = true;
            resultMsg.row = $("#dataSource").find("input[name = 'materialId'][value = '"+materialId+"']").parent().parent().parent().attr("id");
            resultMsg.materialName = $("#dataSource").find("input[name = 'materialId'][value = '"+materialId+"']").parent().parent().parent().find("span[name = 'materialName']").text();
            return resultMsg;
        }
    }
    return resultMsg;
}
function validateMaterialIds(){
    var resultMsg = {};
    resultMsg.contains = false;
    var materialIds = [];
    parent.$("#dataSource tr").each(function(){
        var materialId = $(this).find("input[name = 'materialId']").val();
        if(materialId != ""){
            materialIds.push(materialId);
        }
    });
    for(var i = 0 ; i < parent.materialList.length ; i ++){
        var materialId = parent.materialList[i].id;
        var size = materialIds.length;
        for(var j = 1 ; j <= size ; j ++){
            if (materialIds[j-1] == materialId) {
                resultMsg.contains = true;
                resultMsg.row = parent.$("#dataSource").find("input[name = 'materialId'][value = '"+materialId+"']").parent().parent().parent().attr("id");
                resultMsg.materialName = parent.$("#dataSource").find("input[name = 'materialId'][value = '"+materialId+"']").parent().parent().parent().find("span[name = 'materialName']").text();
                return resultMsg;
            }
        }
    }
    return resultMsg;
}
/** 验证表体是否选择了商品 **/
function validateIsChooseItem(){
    var flag = false;
    $("#dataSource tr").each(function(){
        if($(this).find("input[name='materialId']").val() != ""){
            flag = true;
            return false;
        }
    });
    return flag;
}
/** 结束 **/
function checkNotNullByLableId(id){
    var value=getValueByIdForDifLabel(id);
    if(value==""){
        siping.tips("必需项", id);
        return false;
    }
    else{
        return true;
    }
}
function getValueByIdForDifLabel(labelId) {
    if ($("#" + labelId).get(0).tagName == 'SPAN') {
        return ($("#" + labelId).text());
    } else if ($("#" + labelId).get(0).tagName == 'INPUT') {
        return ($("#" + labelId).val());
    } else if ($("#" + labelId).get(0).tagName == 'SELECT') {
        return ($("#" + labelId).val());
    }
}
function choosePartnerForName(){
    var id = "";
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        id = checkbox[i].id;
    }
    if (id != "") {
        var idArray = [ "id", "partnerCode", "partnerName"];
        for (var i = 0; i < idArray.length; i++) {
            var thisId = idArray[i];
            if (thisId == "id") {
                parent.$("#objectId").val($("#" + thisId + id).text());
            }
            if (thisId == "partnerName"){
                parent.$("#objectName").val($("#" + thisId + id).text());
            }
        }
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择");
    }
}
function closePartnerWin(){
    parent.siping.close(partnerIndex);
}