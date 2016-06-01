<@insert template="layout/layer">
    <div class="back_page"><!--<a onclick="window.history.go(-1)">返回</a>--></div>
    <div class="add_page" style="height:300px">
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">编号</span></div>
            <div class="common_text_val"><span id="areaNo" class="readonly_text_common">${storageArea.areaNo!''}</span></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">名称</span></div>
            <div class="common_text_val"><input id="areaName" value="${storageArea.areaName!''}" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">描述</span></div>
            <div class="common_text_val"><input id="remark" value="${storageArea.remark!''}" class="input_text_common my_radius"></div>
        </div>
         <div class="bank_text_div common_text_div">
        </div>
    <div class="user_btns_l common_text_div">
        <input type="hidden" id="id" value="${storageArea.id}">
        <button id="storage_area_smt_edit" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>