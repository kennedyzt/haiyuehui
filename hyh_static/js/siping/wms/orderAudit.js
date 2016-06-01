$(function(){
    $("#orderNumber").focus();
});
var materialInfoList = [];
var confirmAuditFlag = 0;
var msgIndex;
function changeOrderNumber(){
     siping.ajax({
        method : 'get',
        url : 'readyshipments/getbyaudit',
        async : true,
        dataType : "json",
        data : {
           ordernumber : $("#orderNumber").val()
        },
        success : function(data) {
            if(data==null){
                alert(0,"该订单在系统中不存在");//
                $("#orderNumber").val("");
                materialInfoList = [];//清空信息
                $("#auditTableTbody").html('');
                $("#orderNumber").focus();
            }else{
                if(data.status==2||data.status==3){
                    loadDataToPage(data);//如果需要可以在这里判断下单据的状态
                    $("#materialInfo").focus();
                }else if(data.status==0){
                    $("#orderNumber").focus();
                    alert("单据‘"+ $("#orderNumber").val() +"’还未生成拣货单!");
                     $("#orderNumber").val("")
                     materialInfoList = [];//清空信息
                     $("#auditTableTbody").html('');
                }else if(data.status==4){
                    $("#orderNumber").focus();
                    //siping.alert(0,"单据‘"+ $("#orderNumber").val() +"’已经复核过了!");
                    alert("单据‘"+ $("#orderNumber").val() +"’已经复核过了!");
                    $("#orderNumber").val("")
                    materialInfoList = [];//清空信息
                    $("#auditTableTbody").html('');
                    $("#orderNumber").focus();
                }else{
                    $("#orderNumber").focus();
                    alert("单据‘"+ $("#orderNumber").val() +"’不应被复核!");
                    $("#orderNumber").val("")
                    materialInfoList = [];//清空信息
                    $("#auditTableTbody").html('');
                    $("#orderNumber").focus();
                }
            }
        },
        error : function(data){
            alert("该订单在系统中不存在，或者获取数据出现错误");
            materialInfoList = [];//清空信息
            $("#auditTableTbody").html('');
            $("#orderNumber").val("");
            $("#orderNumber").focus();
        }
    });
}
function getOrderDataByTempAudit(){
    if($("#hiddenGetOrderDataByTempAudit").val()!=""){
        siping.ajax({
            method : 'get',
            url : '/readyshipments/getbyTempAudit',
            async : true,
            dataType : "json",
            data : {
               ordernumber : $("#hiddenGetOrderDataByTempAudit").val()
            },
            success : function(data) {
                if(data==null){
                    alert("该订单在系统中不存在");//
                    materialInfoList = [];//清空信息
                    $("#auditTableTbody").html('');
                    $("#orderNumber").focus();
                }
                else{
                    if(data.status==2||data.status==3){
                        loadDataToPageByTempAudit(data);
                    }
                    else if(data.status==0){
                        alert("单据‘"+ $("#orderNumber").val() +"’还未生成拣货单!");
                         $("#orderNumber").val("")
                         materialInfoList = [];//清空信息
                         $("#auditTableTbody").html('');
                    }
                    else if(data.status==4){
                        alert("单据‘"+ $("#orderNumber").val() +"’已经复核过了!");
                        $("#orderNumber").val("")
                        materialInfoList = [];//清空信息
                        $("#auditTableTbody").html('');
                    }
                    $("#materialInfo").focus();
                }
            },
            error : function(data){
                alert("该订单在系统中不存在，或者获取数据出现错误");
                materialInfoList = [];//清空信息
                $("#auditTableTbody").html('');
                $("#orderNumber").focus();
            }
        });
    }
}

function loadDataToPageByTempAudit(data){//读取暂存的录入,在前端和后台都是通过不同的方式
    $("#orderNumber").val(data.orderNumber);
    $("#fromBillsNo").text(data.fromBillsNo);
    $("#consigneeName").text(data.consigneeName);
    $("#consignessPhone").text(data.consignessPhone);
    $("#summary").text(data.summary);
    $("#consigneeAddress").text(data.consigneeAddress);
    var tbodyHtml ='';
    var materialInfo = {};
    var noAuditNumber = 0;
    materialInfoList = [];//清空信息
    for(var i=0;i<data.items.length;i++){
        tbodyHtml +='<tr><td width="12.5%">'+ parseInt(i+1) +'<input type="hidden" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_itemId" value="'+ data.items[i].id+'"></td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].barcode+'">'+ data.items[i].barcode+'</td>';
        tbodyHtml +='<td width="12.5%">'+ data.items[i].materialName+'</td>';
        tbodyHtml +='<td width="12.5%">'+ data.items[i].batchNumber+'</td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_hopeNumber">'+data.items[i].counts+'</td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_confirmNumber">0</td>';
        tbodyHtml +='<td width="12.5%">'+ data.items[i].unitName+'</td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_flagIcon"><img src="'+staticPath+'/icons/2w.png"></td></tr>';
        noAuditNumber = parseFloat(noAuditNumber) + parseFloat(data.items[i].counts);
        materialInfo = {};
        materialInfo.id = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_itemId';
        materialInfo.materialId = data.items[i].materialId;
        materialInfo.batchNumber = data.items[i].batchNumber;
        materialInfo.rowId = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_confirmNumber';
        materialInfo.hopeRowId = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_hopeNumber';
        materialInfo.flagIcon = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_flagIcon';
        materialInfoList.push(materialInfo);
    }
    $("#auditNumber").text(0);
    $("#noAuditNumber").text(noAuditNumber);//此处数据不再有暂存的数据
    $("#auditTableTbody").html(tbodyHtml);
}

function loadDataToPage(data){
    //页面扫描的录入
    $("#orderNumber").val(data.orderNumber);
    $("#fromBillsNo").text(data.fromBillsNo);
    $("#consigneeName").text(data.consigneeName);
    $("#consignessPhone").text(data.consignessPhone);
    $("#summary").text(data.summary);
    $("#consigneeAddress").text(data.consigneeAddress);
    var tbodyHtml ='';
    var noAuditNumber =0;
    var materialInfo = {};
    materialInfoList = [];//清空信息
    for(var i=0;i<data.items.length;i++){
        tbodyHtml +='<tr><td width="12.5%">'+ parseInt(i+1) +'<input type="hidden" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_itemId" value="'+ data.items[i].id+'"></td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].barcode+'">'+ data.items[i].barcode+'</td>';
        tbodyHtml +='<td width="12.5%">'+ data.items[i].materialName+'</td>';
        tbodyHtml +='<td width="12.5%">'+ data.items[i].batchNumber+'</td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_hopeNumber">'+ data.items[i].counts+'</td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_confirmNumber">0</td>';
        tbodyHtml +='<td width="12.5%">'+ data.items[i].unitName+'</td>';
        tbodyHtml +='<td width="12.5%" id="'+data.items[i].materialId+'_'+ data.items[i].batchNumber +'_flagIcon"><img src="'+staticPath+'/icons/2w.png"></td></tr>';
        noAuditNumber = parseFloat(noAuditNumber + data.items[i].counts);
        materialInfo = {};
        materialInfo.id = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_itemId';
        materialInfo.materialId = data.items[i].materialId;
        materialInfo.barcode = data.items[i].barcode;
        materialInfo.batchNumber = data.items[i].batchNumber;
        materialInfo.rowId = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_confirmNumber';
        materialInfo.hopeRowId = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_hopeNumber';
        materialInfo.flagIcon = data.items[i].materialId + '_'+ data.items[i].batchNumber +'_flagIcon';
        materialInfoList.push(materialInfo);
    }
    $("#auditNumber").text(0);
    $("#noAuditNumber").text(noAuditNumber);
    $("#auditTableTbody").html(tbodyHtml);
}
function scanMaterial(){
    var code = "";
    var codeArr = [];
    for(var i in materialInfoList){
        var bcode = materialInfoList[i].barcode;
        if(bcode == code){
            codeArr.push(bcode);
        }
        code = bcode;
    }
    var scanMaterialInfo = $("#materialInfo").val();
    var materialInfoArray = new Array();
    materialInfoArray = scanMaterialInfo.split("&");
    if(materialInfoArray.length == 1){
        for(var i=0;i<materialInfoList.length;i++){
            if(materialInfoArray[0] == materialInfoList[i].barcode){
                if(codeArr.contains(materialInfoList[i].barcode)){
                    $("#"+materialInfoList[i].flagIcon).html('<img src="'+staticPath+'/icons/1r.png">');
                }else{
                    if(parseFloat($("#"+materialInfoList[i].rowId).text()) == parseFloat($("#"+materialInfoList[i].hopeRowId).text())-1){
                        $("#"+materialInfoList[i].flagIcon).html('<img src="'+staticPath+'/icons/1r.png">');
                    }
                    if(parseFloat($("#"+materialInfoList[i].rowId).text()) == parseFloat($("#"+materialInfoList[i].hopeRowId).text())){
                        layer.msg("该商品批次数量已经满足要求!");
                        $("#materialInfo").val("");
                        return false;
                    }
                }

                $("#"+materialInfoList[i].rowId).text(parseFloat($("#"+materialInfoList[i].rowId).text())+1);
                 $("#auditNumber").text(parseFloat($("#auditNumber").text())+1);
                 $("#noAuditNumber").text(parseFloat($("#noAuditNumber").text())-1);
                 if($("#noAuditNumber").text()==0){
                    confirmAuditFlag = 1;
                    setInterval("confirmAudit()",5000);//设置好定时器
                    msgIndex = layer.open({
                        type: 1,
                        skin: 'layui-layer-rim', //样式类名
                        closeBtn: false, //不显示关闭按钮
                        shift: 2,
                        shadeClose: true, //开启遮罩关闭
                        time:5000,
                        area: ['420px', '240px'], //宽高
                        content: '<div style="height:140px;width:400px;"><span id="auditSpanInfo" style="color:green;font-size:30px;display:block;margin-left:30%;margin-top:50px;"><strong id="second_show">5秒</strong>后自动提交!!</span><button onclick="shutdownConfirmAudit()" class="btn_common my_radius" style="margin-left:41%;margin-top:60px;">取消</button></div>'
                    });
                    timer(intDiff);
                 }
                 $("#materialInfo").val("");
                 return true;
            }else if(i == materialInfoList.length-1){
                layer.msg("该订单中不包含该商品");
                $("#materialInfo").val("");
                return false;
            }
        }
    }else if(materialInfoArray.length == 3){
        for(var i=0;i<materialInfoList.length;i++){
            if(materialInfoArray[1]==materialInfoList[i].materialId){
                for(var j=0;j<materialInfoList.length;j++){
                    if(materialInfoArray[2]==materialInfoList[j].batchNumber&&materialInfoArray[1]==materialInfoList[j].materialId){
                     if(parseFloat($("#"+materialInfoList[j].rowId).text())==parseFloat($("#"+materialInfoList[j].hopeRowId).text())-1){
                            $("#"+materialInfoList[j].flagIcon).html('<img src="'+staticPath+'/icons/1r.png">');
                        }
                         if(parseFloat($("#"+materialInfoList[j].rowId).text())==parseFloat($("#"+materialInfoList[j].hopeRowId).text())){
                             layer.msg("该商品批次数量已经满足要求!");
                            $("#materialInfo").val("");
                            return false;
                        }
                        $("#"+materialInfoList[j].rowId).text(parseFloat($("#"+materialInfoList[j].rowId).text())+1);
                         $("#auditNumber").text(parseFloat($("#auditNumber").text())+1);
                         $("#noAuditNumber").text(parseFloat($("#noAuditNumber").text())-1);
                         if($("#noAuditNumber").text()==0){
                            confirmAuditFlag = 1;
                            setInterval("confirmAudit()",5000);//设置好定时器
                            msgIndex = layer.open({
                                type: 1,
                                skin: 'layui-layer-rim', //样式类名
                                closeBtn: false, //不显示关闭按钮
                                shift: 2,
                                shadeClose: true, //开启遮罩关闭
                                time:5000,
                                area: ['420px', '240px'], //宽高
                                content: '<div style="height:140px;width:400px;"><span id="auditSpanInfo" style="color:green;font-size:30px;display:block;margin-left:30%;margin-top:50px;"><strong id="second_show">5秒</strong>后自动提交!!</span><button onclick="shutdownConfirmAudit()" class="btn_common my_radius" style="margin-left:41%;margin-top:60px;">取消</button></div>'
                            });
                            timer(intDiff);
                         }
                         $("#materialInfo").val("");
                         return true;
                    }else if(j==materialInfoList.length-1){
                        layer.msg("该订单中不包含该商品的该批次");
                        $("#materialInfo").val("");
                        return false;
                    }
                }
            }else if(i==materialInfoList.length-1){
                layer.msg("该订单中不包含该商品");
                $("#materialInfo").val("");
                return false;
            }
        }
    }else{
        layer.msg("扫描的二维码信息有误,请联系管理员");
        $("#materialInfo").val("");
        return false;
    }
}
function shutdownConfirmAudit(){
    layer.close(msgIndex);
    confirmAuditFlag = 0;//手动维护标志位
}

function confirmAudit(){
    if(confirmAuditFlag){
        confirmAuditFlag = 0;//此处flag为定时器的flag,与业务逻辑无关
        confirmAuditWithoutFlag();
    }
}

function confirmAuditWithoutFlag(){//有没有flag主要是要实现暂存和直接保存的功能
    if($("#noAuditNumber").text()==0&&$("#noAuditNumber").text()!=""){
        siping.ajax({
            method : 'get',
            url : '/readyshipments/confirmAudit',
            async : true,
            data : {
               ordernumber : $("#orderNumber").val()
            },
            success : function(data) {
                if (data.success) {
                    //siping.alert(1, data.msg);
                    layer.msg(data.msg, {icon: 1});
                    //window.location.reload(true);
                    setTimeout("window.location.reload(true);",2000);
                } else {
                    siping.alert(0, data.msg);
                }
            },
            error : function(){
                 siping.alert(0,"网络连接出现错误");
            }
        });
    }
    else{
        siping.alert(0,"待审核数不为零,无法提交");
    }
}

function getAuditDataAndTemSave(){
    //先取得数据
    if($("#orderNumber").val()==""){
        siping.alert(0,"没有需要暂存的订单");
        return false;
    }
    var trNode = document.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    if(materialInfoList.length!=trNode.length){
        alert("该订单条目数量出现错误");
        return false;
    }
    var auditNumbetList = [];
    for(var i=0;i<materialInfoList.length;i++){
        var auditNumberInfo = {};
        auditNumberInfo.id = $("#"+materialInfoList[i].id).val();
        auditNumberInfo.confirmAmount = $("#"+materialInfoList[i].rowId).text();
        auditNumbetList.push(auditNumberInfo);
    }
    var request = {};
    request.orderNumber = $("#orderNumber").val();
    request.items = auditNumbetList;
    siping.ajax({
            method : 'post',
            url : '/readyshipments/tempSaveAudit',
            data : JSON.stringify(request),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            },
            error : function(){
                 siping.alert(0,"网络连接出现错误");
            }
        });
}
function getAllTempAuditOrder(){
    var title = "读取暂存复核单";
    siping.openWindow('/readyshipments/getAllTempAuditOrder','70%', '70%', title);
}

var intDiff = parseInt(4);//倒计时总秒数量
function timer(intDiff){
    window.setInterval(function(){
        var day=0,
            hour=0,
            minute=0,
            second=0;//时间默认值
        if(intDiff > 0){
            day = Math.floor(intDiff / (60 * 60 * 24));
            hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
            minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        }
        //if (minute <= 9) minute = '0' + minute;
        //if (second <= 9) second = '0' + second;
        //$('#day_show').html(day+"天");
        //$('#hour_show').html('<s id="h"></s>'+hour+'时');
        //$('#minute_show').html('<s></s>'+minute+'分');
        $('#second_show').html('<s></s>'+second+'秒');
        intDiff--;
    }, 1000);
}