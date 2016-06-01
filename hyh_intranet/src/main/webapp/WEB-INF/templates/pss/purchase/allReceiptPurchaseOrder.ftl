<#setting classic_compatible=true>
<@insert template="login/right">
    <#if isDraft == false >
        <div class="head_top_">
            <#-- <div class="rect_block">
                <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
                <div class="rect_bottom">打印预览</div>
            </div> -->
            <#-- <div class="rect_block">
                <div class="rect_top"><a href="<@url value='/purchaseorderreceipt/getdraftlist' />"><img src="<@static value='/icons/draft.png' />"></a></div>
                <div class="rect_bottom">草稿箱</div>
            </div> -->
        </div>
    </#if>
    <div class="search_section">
        <#if isDraft == false >
            <form action="<@url value='/purchaseorderreceipt/getlist'/>" method="post">
        <#else>
            <form action="<@url value='/purchaseorderreceipt/getdraftlist'/>" method="post">
        </#if>
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
    <div class="data_grid">
        <table style="table-layout: fixed;text-align:center">
            <thead>
                <tr>
                    <td>单据编号</td>
                    <td>单据日期</td>
                    <td>入库仓库</td>
                    <td>供应商编码</td>
                    <td>供应商名称</td>
                    <td>联系人</td>
                    <td>移动电话</td>
                    <td>单据总金额</td>
                    <td>赠品金额</td>
                    <td>优惠金额</td>
                    <td>付款金额</td>
                    <td>单据摘要</td>
                </tr>
            </thead>
            <tbody>
                <#if (pageModel.totalRecords > 0)>
                    <#list pageModel.records as record>
                    <tr>
                        <td><a href="<@url value='/purchaseorderreceipt/get?receiptNumber=${record.orderNumber}' />" data-hover-tip="查看详情">${ record.orderNumber!'' }</a></td>
                        <td>${ record.billDate!'' }</td>
                        <td>${ record.inBoundStorageName!'' }</td>
                        <td>${ record.supplierCode!'' }</td>
                        <td>${ record.supplierName!'' }</td>
                        <td>${ record.contactsFirstName!'' }.${ record.contactsLastName!'' }</td>
                        <td>${ record.mobile!'' }</td>
                        <td>${ record.totalAmount!'' }</td>
                        <td>${ record.giftAmount!'' }</td>
                        <td>${ record.discountAmount!'' }</td>
                        <td>${ record.payAmount!'' }</td>
                        <td>${ record.summary!'' }</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
    <#if (pageModel.totalRecords)!>
        <#if isDraft == false >
            <@pageByParam url="/purchaseorderreceipt/getlist" pageNo="${pageModel.pageNo!}"  pageSize="${pageModel.pageSize!}" totalRecords="${pageModel.totalRecords!}"/>
        <#else>
            <@pageByParam url="/purchaseorderreceipt/getdraftlist" pageNo="${pageModel.pageNo!}"  pageSize="${pageModel.pageSize!}" totalRecords="${pageModel.totalRecords!}"/>
        </#if>
    </#if>
</@insert>