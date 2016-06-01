<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span>发货订单号</span></div>
            <div class="common_text_val"><input id="orderNumber" onchange="changePackingOrderNumber()" class="input_text_common my_radius"><input type="hidden" id="storageId"></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span>原始订单号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="fromBillsNo"></span></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span>包裹重量</span></div>
            <div class="common_text_val"><input id="packWeight" class="input_text_common my_radius" onchange="changeWeight1000(this)"></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span>耗材条码</span></div>
            <div class="common_text_val"><input id="barcode" onchange="changePack()" class="input_text_common my_radius"></div>
        </div>
    </div>
    <div class="grid_section" style="height:410px;overflow:auto;">
        <div class="data_add_grid" style="height:auto">
            <table style="">
                <thead>
                    <tr>
                        <td>耗材编号</td>
                        <td>耗材名称</td>
                        <td>耗材数量</td>
                    </tr>
                </thead>
                <tbody id="packTableTbody">
                    
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
    </div>
     <div class="order_btns_l common_text_div">
        <button id="submitPackingId" class="btn_common my_radius" onclick="submitPacking()">提交</button>
    </div>
</@insert>
<script>
   document.getElementById("orderNumber").focus();
   var submitFlag = 0;
   function submitPacking(){
        if($("#packWeight").val()==""){
            siping.tips("必须项","packWeight");
            return false;
        }
        loadingIndex = layer.load(2, {
            time : 5 * 1000
        });
        if(submitFlag){
             var request = loadPackPageData();
             $("#submitPackingId").attr('disabled',true);
                siping.ajax({
                    method : 'post',
                    url : '/readyshipments/savePack',
                    data : JSON.stringify(request),
                    dataType : "json",
                    contentType : "application/json",
                    success : function(data) {
                        if (data.success) {
                            layer.msg(data.msg, {icon: 1});
                            setTimeout("window.location.reload(true);",2000);
                        } else {
                            siping.alert(0, data.msg);
                            $("#submitPackingId").attr('disabled',false);
                        }
                    },
                    error : function(){
                         siping.alert(0,"网络连接出现错误");
                         $("#submitPackingId").attr('disabled',false);
                    }
                 });
        }
   }
   function changePack(){
        if($("#barcode").val()=='submit'){
            if(submitFlag){
                var request = loadPackPageData();
                siping.ajax({
                    method : 'post',
                    url : '/readyshipments/savePack',
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
            else{
                siping.alert(0,"单据为非法单据，无法提交");
            }
        }else{
            siping.ajax({
                method : 'get',
                url : 'material/getByAsync',
                async : true,
                dataType : "json",
                data : {
                   barcode : $("#barcode").val()
                },
                success : function(data) {
                    if(data==null){
                        alert("不存在该耗材");
                        $("#barcode").val("");
                        document.getElementById("barcode").focus();
                    }
                    else{
                        loadMaterialDataToPage(data);
                        document.getElementById("barcode").focus();
                    }
                },
                error : function(data){
                    alert("不存在该耗材,或者与服务器连接错误");
                    $("#barcode").val("");
                    document.getElementById("barcode").focus();
                }
            });
        }
         
   }
   function loadMaterialDataToPage(data){
        var materialAddHtml ='';
        var dom = document.getElementById("packTableTbody");
        var rowLength = dom.rows.length ;
        var rowCount = 0;
        $(dom).children("tr").each(function(){
            if($(this).find("span[name='materialNo']").text()==data.materialNo){
               $(this).find("span[name='number']").text(parseFloat($(this).find("span[name='number']").text())+1);
               $("#barcode").val("");
               return false;
            }
            else{
                rowCount = rowCount+1;
            }
        });
        if(rowCount==rowLength){
            materialAddHtml = '<tr><td><span style="display:none" name="materialId">'+ data.id +'</span><span name="materialNo">'+ data.materialNo +'</span></td>';
            materialAddHtml += '<td><span name="materialName">'+ data.materialName +'</span></td>';
            materialAddHtml += '<td><span name="number">1</span></td></tr>';
            
        $("#packTableTbody").append(materialAddHtml);
        $("#barcode").val("");
        }
   }
   function changePackingOrderNumber(){
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
                    alert("该订单在系统中不存在");
                    $("#orderNumber").val("");
                    document.getElementById("orderNumber").focus();
                    submitFlag = 0;
                }
                else{
                    if(data.status == 5){
                        alert("该订单已经包装过，同一订单不可包装两次");
                        submitFlag = 0;
                        document.getElementById("orderNumber").focus();
                        $("#orderNumber").val("");
                    }
                    else if(data.status == 4){
                        $("#fromBillsNo").text(data.fromBillsNo);
                        $("#storageId").val(data.storageId);
                        document.getElementById("barcode").focus();
                        submitFlag = 1;
                    }
                    else{
                        document.getElementById("orderNumber").focus();
                        alert("该订单不满足包装的条件,无法包装");//维护光标位置，维护订单扫描号,维护提交标志位
                        $("#orderNumber").val("");
                        submitFlag = 0;
                    }
                }
            },
            error : function(data){
                alert("该订单在系统中不存在，或者获取数据出现错误");
                $("#orderNumber").val("");
                document.getElementById("orderNumber").focus();
                submitFlag = 0;
            }
        });
   }
   
   function loadPackPageData(){
        var request = {};
        request.orderNumber = $("#orderNumber").val();
        request.packWeight = $("#packWeight").val();
        request.storageId = $("#storageId").val();
        var packList = [];
        var dom = document.getElementById("packTableTbody");
        $(dom).children("tr").each(function(){
            var packItem = {};
            packItem.materialId = $(this).find("span[name='materialId']").text();
            packItem.number = $(this).find("span[name='number']").text();
            packItem.materialName = $(this).find("span[name='materialName']").text();
            packList.push(packItem);
        });
        request.packList = packList;
        return request;
   }
   
   function changeWeight1000(dom){
       $(dom).val((parseFloat($(dom).val())*1000).toFixed(3));
   }
   
</script>