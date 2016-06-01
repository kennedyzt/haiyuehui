<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="search_section">
    <form action="<@url value='/purchaseorder/copyfrom'/>" method="post">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="keywords" name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、所有人"></div>
        </div>
        <div class="search_btn common_text_div">
            <button type="submit" class="btn_common my_radius btn_search">搜索</button>
        </div>
        <div class="search_btn common_text_div">
        </div>
    </form>
    <div style="clear:both"></div>
</div>
<table style="table-layout: fixed;">
    <thead>
        <tr>
            <td width="10%"></td>
            <td width="12%">单据编号</td>
            <td width="12%">单据日期</td>
            <td width="12%">申请者</td>
            <td width="12%">申请数量</td>
            <td width="12%">单据摘要</td>
        </tr>
    </thead>
</table>
<div class="data_grid_forwin">
    <table>
        <tbody>
            <#list pageModel.records as record>
                <tr>
                    <td width="10%"><input id="${ record.orderNumber }" type="radio" name="check_box"></td>
                    <td width="12%">${ record.orderNumber }</td>
                    <td width="12%">${ record.billDate }</td>
                    <td width="12%">${ record.applierName }</td>
                    <td width="12%">${ record.counts }</td>
                    <td width="12%">${ record.summary }</td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
<#if (pageModel.totalRecords)! >
    <@pageByParam url="/purchaseapplyorder/copyfrom" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
</#if>
<div class="order_btns_l common_text_div">
    <button onclick="copyApplyPO();" class="btn_common my_radius">确定</button>
</div>
</@insert>