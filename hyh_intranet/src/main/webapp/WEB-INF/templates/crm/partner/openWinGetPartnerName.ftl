<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="list_page">
    <div class="search_section">
        <form action="<@url value='/businesspartner/openwin?partnerType=2'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入编码、名称关键字" placeholder="编码、名称"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">组</div>
                <div class="common_text_val">
                    <select name="partnerGroup" class="my_radius">
                        <option></option>
                        <#list partnerGroups as p>
                            <option value="${p.id}">${p.groupName}</option>
                        </#list>
                    </select>
                </div>
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
                <td width="48%">编码</td>
                <td width="48%">名称</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid_forwin">
        <table>
            <tbody>
                <#list pageModel.records as p>
                    <tr id="tr${p_index+1}">
                        <td width="4%">
                            <input id="${p_index+1}" type="radio" name="check_box">
                            <span style="display:none;" id="id${p_index+1}">${p.id}</span>
                            <span style="display:none;" id="contactsLastName${p_index+1}">${p.contactsLastName}</span>
                            <span style="display:none;" id="contactsFirstName${p_index+1}">${p.contactsFirstName}</span>
                            <span style="display:none;" id="contactsMobilephone${p_index+1}">${p.contactsMobilephone}</span>
                        </td>
                        <td id="partnerCode${p_index+1}" width="48%">${p.partnerCode}</td>
                        <td id="partnerName${p_index+1}" width="48%">${p.partnerName}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/businesspartner/openwin?partnerType=2" pageNo="${pageModel.pageNo}" pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
    <div class="order_btns_l common_text_div">
        <button onclick="choosePartnerForName();" class="btn_common my_radius">确定</button>
    </div>
</div>
</@insert>