<#setting classic_compatible=true>
<@insert template="layout/layer">
    <div class="layer_sm_content">
        <div class="layer_1_text">
            <span data-required="true">盘点仓库</span><input type="hidden" id="inventoryCheckId" value="0">
            <select id="selected_storage_for_inventory_check" class="my_radius" onchange="getStorageArea()">
                <option value=""></option>
                <#list storages as s>
                <option value="${s.id}">${s.storageName}</option>
                </#list>
            </select>
        </div>
        <div class="layer_1_text">
            <span>&nbsp;&nbsp;盘点库区</span>
            <select id="layerChooseStorageArea" class="my_radius" disabled="disabled">
                <option value=""></option>
            </select>
        </div>
    </div>
    <div class="layer_sm_btn">
        <button id="layer_choose_storage" class="btn_common my_radius btn_submit">确定</button>
    </div>
</@insert>
<script>
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
</script>
