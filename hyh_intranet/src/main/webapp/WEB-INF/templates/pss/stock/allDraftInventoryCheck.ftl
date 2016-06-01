<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、所有人"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">单据日期(从)</div>
            <div class="common_text_val"><input id="" class="input_text_common my_radius" data-type="date"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">单据日期(至)</div>
            <div class="common_text_val"><input id="" class="input_text_common my_radius" data-type="date"></div>
        </div>
        <div class="search_btn common_text_div">
            <button class="btn_common my_radius btn_search">搜索</button>
        </div>
        <div style="clear:both"></div>
    </div>
    <table>
        <thead>
            <tr>
                <td width="10%"><a class="td_head_a" onclick="deleteDraftInventoryCheck();" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="14%">单据编号</td>
                <td width="14%">单据日期</td>
                <td width="14%">盘点仓库</td>
                <td width="10%">单据数量</td>
                <td width="12%">单据金额</td>
                <td width="12%">所有人</td>
                <td width="14%">单据摘要</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
            <#list inventorycheckList.records as i>
                <tr>
                    <td width="10%">
                        <input id="${i.checkNumber}" type="checkbox" name="check_box_inventory_check">
                        <a class="td_inner_a" href="<@url value='edit?checkNumber=${i.checkNumber}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                    </td>
                    <td width="14%"><a href="<@url value='get?checkNumber=${i.checkNumber}' />" data-hover-tip="查看详情">${i.checkNumber}</a></td>
                    <td width="14%">${i.billsDate!''}</td>
                    <td width="14%">${i.checkStorageName!''}</td>
                    <td width="10%">${i.totalNumber!''}</td>
                    <td width="12%">${i.totalPrice!''}</td>
                    <td width="12%">${i.ownerName!''}</td>
                    <td width="14%">${i.summary!''}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if inventorycheckList.totalRecords??>
        <@pageByParam url="/inventorycheck/getdraftlist" pageNo="${inventorycheckList.pageNo}"  pageSize="${inventorycheckList.pageSize}" totalRecords="${inventorycheckList.totalRecords}"/>
    </#if>
</@insert>