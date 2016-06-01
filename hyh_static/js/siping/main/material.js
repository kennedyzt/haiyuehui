$(function() {
    $("#material_unit_smt_edit").click(function(){
        siping.ajax({
            method : 'post',
            url : '/materialunit/edit',
            async : true,
            data : {
                id:$("#id").val(),
                unitNo:$("#unitNo").val(),
                unitName:$("#unitName").val(),
                description:$("#description").val(),
                isDelete:false
            },
            success : function(data) {
                if(data.success){
                    siping.alert(1,data.msg);
                }else{
                    siping.alert(0,data.msg);
                }
            }
        });
    });
    $("#material_unit_smt_add").click(function(){
        siping.ajax({
            method : 'post',
            url : '/materialunit/add',
            async : true,
            data : {
                unitName:$("#unitName").val(),
                unitNo:$("#unitNo").val(),
                description:$("#description").val(),
                isDelete:false
            },
            success : function(data) {
                if(data.success){
                    siping.alert(1,data.msg);
                }else{
                    siping.alert(0,data.msg);
                }
            }
        });
    });
    $("#material_type_smt_add").click(function(){
        siping.ajax({
            method : 'post',
            url : '/materialtype/add',
            async : true,
            data : {
                typeName:$("#typeName").val(),
                parentId:$("#father_id").val(),
                isLeafNode: true,
                description: $("#description").val()
            },
            success : function(data) {
                if(data.success){
                    siping.alert(1,data.msg);
                }else{
                    siping.alert(0,data.msg);
                }
            }
        });
    });
    $("#material_type_smt_edit").click(function(){
        siping.ajax({
            method : 'post',
            url : '/materialtype/edit',
            async : true,
            data : {
                id:$("#material_type_id").val(),
                typeName:$("#typeName").val(),
                parentId:$("#father_id").val(),
                oldParentId:$("#old_parent_id").val(),
                description: $("#description").val()
            },
            success : function(data) {
                if(data.success){
                    siping.alert(1,data.msg);
                }else{
                    siping.alert(0,data.msg);
                }
            }
        });
    });
    $("#material_smt").click(function() {
        if(checkNotNullByLableId("materialNo")&&checkNotNullByLableId("materialName")&&checkNotNullByLableId("barcode")&&checkNotNullByLableId("unitId")&&checkNotNullByLableId("expirationDate")&&checkNotNullByLableId("weight")){
            var request = getMaterialData();
            siping.ajax({
                method : "post",
                url : '/material/add',
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
    });
    $("#material_edit_smt").click(function() {
        if(checkNotNullByLableId("materialNo")&&checkNotNullByLableId("materialName")&&checkNotNullByLableId("barcode")&&checkNotNullByLableId("unitId")&&checkNotNullByLableId("expirationDate")&&checkNotNullByLableId("weight")){
            var request = getMaterialData();
            request.id = $("#materialId").val();
            siping.ajax({
                method : "post",
                url : '/material/edit',
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
    });
    $("#material_title_1").click(function() {
        $("#material_section_3").hide();
        $("#material_section_2").hide();
        $("#material_section_1").show();
        $(this).css({
            "border-bottom" : "2px solid #60A7FF"
        }).siblings().css({
            "border-bottom" : "0"
        });
    });
    $("#material_title_2").click(function() {
        $("#material_section_1").hide();
        $("#material_section_3").hide();
        $("#material_section_2").show();
        $(this).css({
            "border-bottom" : "2px solid #60A7FF"
        }).siblings().css({
            "border-bottom" : "0"
        });
    });
    $("#material_title_3").click(function() {
        $("#material_section_1").hide();
        $("#material_section_2").hide();
        $("#material_section_3").show();
        $(this).css({
            "border-bottom" : "2px solid #60A7FF"
        }).siblings().css({
            "border-bottom" : "0"
        });
    });
    
});
function deleteMaterial() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    if(checkbox.length==0){
        siping.alert(0,"请选择需要删除的商品!");
        return false
    }
    for(var i=0;i<checkbox.length;i++){
        ids[i]=checkbox[i].id;
    }
    siping.confirm('确定删除吗？', function() {
        siping.ajax({
            method : 'get',
            url : '/material/delete?id=' + ids,
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
function deleteMaterialUnit() {
    var ids = [];
    var checkbox = $("input[name='material_unit_check_box']:checked");
    if(checkbox.length==0){
        siping.alert(0,"请选择行需要删除的行！");
        return;
    }
    for(var i=0;i<checkbox.length;i++){
        ids[i]=checkbox[i].id;
    }
    siping.confirm('确定删除吗？', function() {
        siping.ajax({
            method : 'get',
            url : '/materialunit/delete?id=' + ids,
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
function deleteMaterialType() {
    var ids = [];
    var checkbox = $("input[name='material_type_check_box']:checked");
    if(checkbox.length==0){
        siping.alert(0,"请选择行需要删除的行！");
        return;
    }
    for(var i=0;i<checkbox.length;i++){
        ids[i]=checkbox[i].id;
    }
    siping.confirm('确定删除吗？', function() {
        siping.ajax({
            method : 'get',
            url : '/materialtype/delete?id=' + ids,
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
function useBatchManage(){
    $("#expirationDate").attr("disabled", false);
}
function unUseBatchManage(){
    $("#expirationDate").val("");
    $("#expirationDate").attr("disabled", true);
}
function getMaterialData(){
    var request = {};
    request.materialNo = $("#materialNo").val(),
    request.materialName = $("#materialName").val(),
    request.barcode = $("#barcode").val(),
    request.unitId = $("#unitId").val(),
    request.foreignName = $("#foreignName").val(),
    request.materialType = $("#father_id").val()==""?"0":$("#father_id").val(),
    request.brand = $("#brand").val(),
    request.specificationsModel = $("#specificationsModel").val(),
    request.season = $("#season").val(),
//    request.isPurchase = $('input[name="purchaseMaterial"]:checked').val()==1,
//    request.isSell = $('input[name="saleMaterial"]:checked').val()==1,
//    request.isInventory = $('input[name="storeMaterial"]:checked').val()==1,
    request.isPurchase =1,
    request.isSell =1,
    request.isInventory =1,
    request.shops = $("#shops").val(),
    request.expirationDate = $("#expirationDate").val(),
    request.partnerId = $("#id").text(),
    request.minOrder = $("#minOrder").val(),
    request.isEnable = $('input[name="activity"]:checked').val()==1,
    request.isBatch = $('input[name="batch"]:checked').val()==1,
    request.isDelete = false,
    request.description = $("#description").val(),
    //request.legalUnit = $("#legalUnit").val(),
    //request.legalTranslationQuantity = $("#legalTranslationQuantity").val(),
    //request.entryUnit = $("#entryUnit").val(),
    //request.entryTranslationQuantity = $("#entryTranslationQuantity").val(),
    request.manufacturer = $("#manufacturer").val(),
    request.provenance = $("#provenance").val(),
    request.ebec = $("#ebec").val(),
    request.eben = $("#eben").val(),
    request.hscode = $("#hscode").val(),
    request.postTaxNumber = $("#postTaxNumber").val(),
    request.custom1 = $("#custom1").val(),
    request.custom2 = $("#custom2").val(),
    request.custom3 = $("#custom3").val(),
    request.itemNo = $("#itemNo").val(),
    request.weight = $("#weight").val(),
    request.postTaxRate = $("#postTaxRate").val(),
    request.transactionId = 3,
    request.minInventory = 1,
    request.maxInventory = 2
    // 循环注入库存警戒
    var trs = $("#dataSource").find("tr");
    var addInventoryWarningRequests = [];
    for (var c = 0; c < trs.length; c++) {
        var inventoryWarning = {};
        inventoryWarning.storageId = getValueByIdForDifLabel("storageId"+c);
        inventoryWarning.maxInventory = getValueByIdForDifLabel("maxInventory"+c);
        inventoryWarning.minInventory = getValueByIdForDifLabel("minInventory"+c);
        addInventoryWarningRequests.push(inventoryWarning);
    }
    request.addInventoryWarningRequests = addInventoryWarningRequests;
    return request;
}
function getValueByIdForDifLabel(labelId) {
    if ($("#" + labelId).get(0).tagName == 'SPAN') {
        return ($("#" + labelId).text());
    } else if ($("#" + labelId).get(0).tagName == 'INPUT') {
        return ($("#" + labelId).val());
    }
}
function openWinGetSupplierWithIdConfict(){
    title = "选择供应商";
    siping.openWindow('/businesspartner/openwinwithidconfict?partnerType=' + '2','900px', '450px', title);
}

function fuzzyQueryUnit(){
    siping.ajax({
        method : 'get',
        url : '/materialunit/getlistwithcondition?keyword=' + $("#unitName").val(),
        async : true,
        success : function(data) {
            var fuzzyQueryDiv = '<div id="fuzzyQueryDiv" style="position:absolute;top:35px;left:0px;px;width:200px;height:auto;background-color: white">'
            fuzzyQueryDiv += '<div class="li_div my_radius">'
            for(var i=0;i<data.length;i++){
            fuzzyQueryDiv += '<div class="li_span" onclick="chooseUnit(this)"><span class="li_code">'+data[i].unitName+'</span><input type="hidden" name="untiId" value="'+ data[i].id +'"></div>'
            }
            fuzzyQueryDiv += '</div></div>'
            $("#materialUnitHiddenDiv").html(fuzzyQueryDiv);
        }
    });
}

function chooseUnit(dom){
    $("#unitName").val($(dom).children("span").text());
    $("#unitId").val($(dom).children("input").val());
    $("#materialUnitHiddenDiv").html('');
}

function openWinGetUnit(){
    title = "选择单位";
    siping.openWindow('/materialunit/openwin','900px', '500px', title);
}
function chooseMaterialUnit(){//供layerChooseMaterialUnit调用
    var ids = [];
    var names = [];
    var checkbox = $("input[name='material_unit_check_box']:checked");
    if(checkbox.length==0){
        siping.alert(0,"请选择需要的单位!");
        return false
    }
    for(var i=0;i<checkbox.length;i++){
        ids[i]=checkbox[i].id;
        names[i]=$(checkbox[i]).parent().next().next().text();
    }
    if(ids.length==1&&names.length==1){
        parent.$("#unitName").val(names[0]);
        parent.$("#unitId").val(ids[0]);
        parent.siping.close(layerIndex);
    }else{
        siping.alert(0,"获取数据出现错误！");
    }
   
}
function getMaterialTypeList(pageNo,pageSize){
    siping.treetable({
        content : "materailtype_list_grid",
        width : "100%",
        height : "450px",
        ajax : {
            type : "post",
            url : "materialtype/getlistbyparentid",
            async : true,
            data: {pageNo:pageNo, pageSize:pageSize, parentId:'0', key:$("#material_name_key").val()},
            contentType : 'application/json'
        },
        header : [
              {width : "25%",name : "类型名称"},
              {width : "20%",name : "父类型"},
              {width : "20%",name : "创建日期"},
              {width : "20%",name : "描述"}
        ],
        column : ["typeName","parentTypeName","createDate","description"],
        page : {options : [10,20,30,50,80], fn : "getMaterialTypeList"},
        rowNum : true,
        checkbox : {id: 'id'},
        handler : {
            del : {fn: 'deleteMaterialType', tips: '删除', width: '3%'},
            edit : {href: 'materialtype/edit',id: 'id',tips: '修改', width: '3%'}
        }
    });
}