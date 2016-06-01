<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="list_page" style="overflow: auto;">
    <div class="search_section">
        <form action="<@url value='/readyreceipt/openconsigneewin'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">起运港</span></div>
                <div class="common_text_val"><input id="startStation" name="startStation" value = "${form.startStation}"  class="input_text_common my_radius" ></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">目的港</span></div>
                <div class="common_text_val"><input id="endStation" name="endStation" value = "${form.endStation}"  class="input_text_common my_radius" ></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">运输方式</span></div>
                <div class="common_text_val"><input id="transportationType" name="transportationType" value = "${form.transportationType}"  class="input_text_common my_radius" ></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">入库仓库</div>
                <div class="common_text_val">
                    <select id="inboundStorageId" name="inboundStorageId" class="my_radius grid_page_text">
                        <#list storages as s>
                            <option <#if s.id == 0>selected</#if> value="${s.id}">${s.storageName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" value = "${form.keywords}" class="input_text_common my_radius" data-click-tip="输入账号、昵称" placeholder="账号、昵称"></div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <table>
        <thead>
            <tr>
                <td width="4%"></td>
                <td width="48%">账号</td>
                <td width="48%">昵称</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid_forwin">
        <table>
            <tbody>
                <#list pageModel.records as p>
                    <tr id="${p_index+1}">
                        <td width="4%">
                            <input id="${p_index+1}" type="radio" name="radio">
                            <input id = "userId${p_index+1}" value="${p.id}" type = "hidden">
                        </td>
                        <td id="userName${p_index+1}" width="48%">${p.userName}</td>
                        <td id="nickName${p_index+1}" width="48%">${p.nickName}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/readyreceipt/openconsigneewin" pageNo="${pageModel.pageNo}" pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
    <div class="order_btns_l common_text_div">
        <button onclick="chooseConsignee();" class="btn_common my_radius">确定</button>
    </div>
</div>
</@insert>
<script>
function chooseConsignee() {
    var ids = new Array("inboundStorageId","startStation","endStation","transportationType");
    var resultMsg = validateNotNull(ids);
    if(resultMsg.success != true){
        siping.tips("必填项",resultMsg.id);
        return;
    }
    var id = "";
    var checkbox = $("input[name='radio']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        id = checkbox[i].id;
    }
    if (id != "") {
        parent.generateReceipt($("#userId" + id).val(),$("#inboundStorageId").val(),$("#startStation").val(),$("#endStation").val(),$("#transportationType").val());
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择收货人");
    }
}
</script>