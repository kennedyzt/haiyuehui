<@insert template="layout/layer">
    <div class="back_page"><!--<a onclick="window.history.go(-1)">返回</a>--></div>
    <div class="add_page" style="height:300px">
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">库位编号</span></div>
            <div class="common_text_val"><input id="locationNo" value="${storageLocation.locationNo!''}" class="input_text_common my_radius"><input id="storageId" type="hidden" value="${storageLocation.storageId!''}"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">库位名称</span></div>
            <div class="common_text_val"><input id="locationName" value="${storageLocation.locationName!''}" class="input_text_common my_radius"></div>
        </div>
        <#--<div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">库位条码</span></div>
            <div class="common_text_val"><input id="locationBarcode" value="${storageLocation.locationBarcode!''}" class="input_text_common my_radius"></div>
        </div>-->
        <div class="bank_text_div common_text_div">
            <div class="common_text_title">拣配编号</div>
            <div class="common_text_val"><input id="pickOrder" value="${storageLocation.pickOrder!''}" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">所属库区</span></div>
            <div class="common_text_val">
            <select id="storageAreaId" class="my_radius">
                <option></option>
                <#list storageareas as s>
                    <option <#if storageLocation.storageAreaId?exists><#if storageLocation.storageAreaId?c == s.id>selected</#if></#if> value="${s.id}">${s.areaName!''}</option>
                </#list>
            </select>
            </div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title">备注</div>
            <div class="common_text_val"><input id="description" value="${storageLocation.description!''}" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div"><#--占位-->
            <div class="common_text_title"></div>
            <div class="common_text_val"></div>
        </div>
    <div class="user_btns_l common_text_div">
        <input type="hidden" id="id" value="${storageLocation.id!''}">
        <button id="storage_location_smt_edit" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>