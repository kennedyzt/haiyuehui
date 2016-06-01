<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <#--<div class="head_top_">
        <div class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/export.png' />"></a></div>
            <div class="rect_bottom">批量导出</div>
        </div>
    </div>-->
    <div class="search_section">
        <form action="<@url value='/businesspartner/getlist?partnerType=${partnerType!}'/>" method="post">
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
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">是否可用</div>
                <div class="common_text_val">
                    <select name="isEnable" class="my_radius">
                        <option ></option>
                        <option value="true">可用</option>
                        <option value="false">不可用</option>
                    </select>
                </div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <div class="table_title">列表</div>
    <table>
        <thead>
            <tr>
                <td width="10%"><a class="td_head_a" onclick="deletePartner()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="15%">编码</td>
                <td width="15%">名称</td>
                <td width="15%">电话</td>
                <td width="15%">传真</td>
                <td width="15%">电子邮件</td>
                <td width="15%">银行账户</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <input type="hidden" id="partnerType" value="${partnerType}">
        <table>
            <tbody>
                <#list pageModel.records as t>
                    <tr>
                        <td width="10%">
                            <input id="${t.id}" type="radio" name="check_box">
                            <a class="td_inner_a" href="<@url value='/businesspartner/editsupplier?id=${t.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="15%"><a href="<@url value='/businesspartner/getsupplier?id=${t.id}' />" data-hover-tip="查看详情">${t.partnerCode}</a></td>
                        <td width="15%">${t.partnerName}</td>
                        <td width="15%">${t.phone}</td>
                        <td width="15%">${t.fax}</td>
                        <td width="15%">${t.email}</td>
                        <td width="15%">
                            <a onclick="openWinBank(${t.id})" >查看</a>
                            <a onclick="openWinAddBank(${t.id})" >添加</a>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/businesspartner/getlist?partnerType=${partnerType}" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</div>
</@insert>