<@insert template="login/right">
    <div class="add_page" style="height:50%;">
        <div class="user_text_div common_text_div">
            <div class="common_text_title">国家/地区</div>
            <div class="common_text_val"><input id="countryRegionName" class="input_text_common my_radius"></div>
        </div>
    </div>
    <div class=" user_btns_l common_text_div">
            <button id="country_region_smt_add" class="btn_common my_radius btn_submit">保存</button>
            <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>
<script>
    $("#country_region_smt_add").click(function() {
        var ids = new Array("countryRegionName");
        var resultMsg = validateNotNull(ids);
        if(resultMsg.success != true){
            siping.tips("必填项",resultMsg.id);
            return;
        }
        siping.ajax({
            method : 'post',
            url : '/countryregion/add',
            async : true,
            data : {
                countryRegionName : $("#countryRegionName").val()
            },
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                    siping.redirect("/countryregion/getlist");
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
</script>