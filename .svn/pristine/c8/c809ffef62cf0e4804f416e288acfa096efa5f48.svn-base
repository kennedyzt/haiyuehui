<#setting classic_compatible=true>
<@insert template="login/right">
    <#if isDraft == false >
        <div class="head_top_">
            <#-- <div class="rect_block"> 
                <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
                <div class="rect_bottom">打印预览</div>
            </div> -->
            <#-- <div class="rect_block">
                <div class="rect_top"><a href="<@url value='/purchaseapplyorder/getdraftlist ' />"><img src="<@static value='/icons/draft.png' />"></a></div>
                <div class="rect_bottom">草稿箱</div>
            </div> -->
        </div>
    </#if>
    <div class="search_section">
        <#if isDraft == false >
            <form action="<@url value='/purchaseapplyorder/getlist'/>" method="post">
        <#else>
            <form action="<@url value='/purchaseapplyorder/getdraftlist'/>" method="post">
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
        <table style="table-layout: fixed;">
            <thead>
                <tr>
                    <td>单据编号</td>
                    <td>单据日期</td>
                    <td>申请者</td>
                    <td>单据摘要</td>
                    <td>单据数量</td>
                    <td>所有人</td>
                </tr>
            </thead>
            <tbody>
                <#if (pageModel.totalRecords > 0)>
                    <#list pageModel.records as record>
                    <tr>
                        <td><a href="<@url value='/purchaseapplyorder/get?orderNumber=${record.orderNumber}' />" data-hover-tip="查看详情">${ record.orderNumber!'' }</a></td>
                        <td>${ record.billDate!'' }</td>
                        <td>${ record.nickName!'' }</td>
                        <td>${ record.summary!'' }</td>
                        <td>${ record.counts!'' }</td>
                        <td>${ record.ownerName!'' }</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <#if isDraft == false >
            <@pageByParam url="/purchaseapplyorder/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        <#else>
            <@pageByParam url="/purchaseapplyorder/getdraftlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
    </#if>
</@insert>