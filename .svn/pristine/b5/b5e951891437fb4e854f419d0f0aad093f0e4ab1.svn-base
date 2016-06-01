<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="search_section">
    <form action="<@url value='/purchaseorderreceipt/copyfrom'/>" method="post">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="keywords" name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、所有人"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">单据日期(从)</div>
            <div class="common_text_val"><input id="start_date" name="startDate" class="input_text_common my_radius" data-type="date"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">单据日期(至)</div>
            <div class="common_text_val"><input id="end_date" name="endDate"  class="input_text_common my_radius" data-type="date"></div>
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
            <td width="10%">单据编号</td>
            <td width="10%">单据日期</td>
            <td width="10%">预计收货日期</td>
            <td width="10%">入库仓库</td>
            <td width="10%">供应商编码</td>
            <td width="10%">供应商名称</td>
            <td width="10%">单据数量</td>
            <td width="10%">单据金额</td>
            <td width="10%">摘要</td>
        </tr>
    </thead>
</table>
<div class="data_grid_forwin">
    <table>
        <tbody>
            <#list pageModel.records as record>
                <tr>
                    <td width="10%"><input id="${ record.orderNumber }" type="radio" name="check_box"></td>
                    <td width="10%">${ record.orderNumber }</td>
                    <td width="10%">${ record.billDate }</td>
                    <td width="10%">${ record.receiptDate }</td>
                    <td width="10%">${ record.inBoundStorageName }</td>
                    <td width="10%">${ record.supplierCode }</td>
                    <td width="10%">${ record.supplierName }</td>
                    <td width="10%">${ record.counts }</td>
                    <td width="10%">${ record.totalAmount }</td>
                    <td width="10%">${ record.summary }</td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
<#if pageModel.totalRecords??>
    <@pageByParam url="/purchaseorderreceipt/copyfrom" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
</#if>
<div class="order_btns_l common_text_div">
    <button onclick="copyPO();" class="btn_common my_radius">确定</button>
</div>
</@insert>