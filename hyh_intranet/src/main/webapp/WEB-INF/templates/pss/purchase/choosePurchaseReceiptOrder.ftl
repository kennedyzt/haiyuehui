<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="search_section">
    <form action="<@url value='/purchaseorderreturn/copyfrom'/>" method="post">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="keywords" name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、所有人"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">必需日期(从)</div>
            <div class="common_text_val"><input id="start_date" name="startDate" class="input_text_common my_radius" data-type="date"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">必需日期(至)</div>
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
<table>
    <thead>
        <tr>
            <td width="10%"></td>
            <td>单据编号</td>
            <td>单据日期</td>
            <td>入库仓库</td>
            <td>供应商编码</td>
            <td>供应商名称</td>
            <td>单据数量</td>
            <td>单据金额</td>
            <td>摘要</td>
        </tr>
    </thead>
</table>
<div class="data_grid_forwin">
    <table>
        <tbody>
            <#list pageModel.records as record>
                <tr>
                    <td><input id="${ record.orderNumber }" type="radio" name="check_box"></td>
                    <td>${ record.orderNumber }</a></td>
                    <td>${ record.billDate }</td>
                    <td>${ record.inBoundStorageName }</td>
                    <td>${ record.supplierCode }</td>
                    <td>${ record.supplierName }</td>
                    <td>${ record.counts }</td>
                    <td>${ record.totalAmount }</td>
                    <td>${ record.summary }</td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
<#if pageModel.totalRecords??>
    <@pageByParam url="/purchaseorderreturn/copyfrom" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
</#if>
<div class="order_btns_l common_text_div">
    <button onclick="copyPRO();" class="btn_common my_radius">确定</button>
</div>
</@insert>