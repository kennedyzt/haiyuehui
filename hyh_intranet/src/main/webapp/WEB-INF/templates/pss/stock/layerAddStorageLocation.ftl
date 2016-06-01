<@insert template="layout/layer">
    <div class="add_page">
        <div class="bank_text_div common_text_div"></div>
        <div class="bank_text_div common_text_div"></div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">库位编码</span></div>
            <div class="common_text_val"><input id="locationNo"  class="input_text_common my_radius" data-click-tip="数据唯一标识，不可重复"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">库位名称</span></div>
            <div class="common_text_val"><input id="locationName"  class="input_text_common my_radius"></div>
        </div>
        <#--<div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">库位条码</span></div>
            <div class="common_text_val"><input id="locationBarcode"  class="input_text_common my_radius"></div>
        </div>-->
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span>拣配编号</span></div>
            <div class="common_text_val"><input id="pickOrder" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title">所属库区</span></div>
            <div class="common_text_val">
            <select id="storageAreaId" class="my_radius">
                <option></option>
                <#list storageareas as s>
                    <option value="${s.id}">${s.areaName}</option>
                </#list>
            </select>
            </div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span>备注</span></div>
            <div class="common_text_val"><input id="description" class="input_text_common my_radius"></div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <input type="hidden" id="storageId" value="${s.id}">
        <button id="storage_location_smt_add" class="btn_common my_radius btn_submit">保存</button>
    </div>
</@insert>