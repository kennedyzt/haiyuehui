<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、审核人、所有人"></div>
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
                <td width="10%"><a class="td_head_a" onclick="deleteDraftInventoryReceipt()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="14%">单据编号</td>
                <td width="14%">单据日期</td>
                <td width="12%">入库仓库</td>
                <td width="10%">单据数量</td>
                <td width="12%">单据金额</td>
                <td width="12%">所有人</td>
                <td width="16%">单据摘要</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                    <#list pageModel.records as p>
                        <tr>
                        <td width="10%">
                            <input id="" type="radio" name="check_box">
                            <a class="td_inner_a" href="<@url value='/inventoryshipments/edit?shipmentsNumber=${p.shipmentsNumber}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="14%"><a href="<@url value='/inventoryshipments/get?shipmentsnumber=${p.shipmentsNumber}' />" data-hover-tip="查看详情">${p.shipmentsNumber}</a></td>
                        <td width="14%">${p.billsDate!}</td>
                        <td width="14%">${p.storageName!}</td>
                        <td width="10%">${p.counts!}</td>
                        <td width="14%">${p.totalPrice!}</td>
                        <td width="14%">${p.nickname!}</td>
                        <td width="16%">${p.summary!}</td>
                    </tr>
                       </#list>
            </tbody>
        </table>
    </div>
</@insert>