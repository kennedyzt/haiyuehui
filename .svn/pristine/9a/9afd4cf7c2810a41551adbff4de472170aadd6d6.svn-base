<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="list_page" style="overflow: auto;">
    <div class="search_section">
        <form action="<@url value='/usr/openwin'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入账号、昵称" placeholder="账号、昵称"></div>
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
                            <input id="${p_index+1}" type="radio" name="check_box">
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
        <@pageByParam url="/usr/openwin" pageNo="${pageModel.pageNo}" pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
    <div class="order_btns_l common_text_div">
        <button onclick="chooseUser();" class="btn_common my_radius">确定</button>
    </div>
</div>
</@insert>