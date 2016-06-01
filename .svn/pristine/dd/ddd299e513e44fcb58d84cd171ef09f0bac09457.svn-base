<#setting classic_compatible=true>
<@insert template="login/right">
    <#if isDraft == false >
        <#-- <div class="head_top_">
            <div class="rect_block">
                <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
                <div class="rect_bottom">打印预览</div>
            </div>
        </div> -->
    </#if>
    <div class="search_section">
        <#if isDraft == false >
            <form action="<@url value='/readyreceipt/getlist'/>" method="post">
        <#else>
            <form action="<@url value='/readyreceipt/getdraftlist'/>" method="post">
        </#if>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" value = "${(form.keywords)!}" class="input_text_common my_radius" data-click-tip="输入单据编号" placeholder="单据编号"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(从)</div>
                <div class="common_text_val"><input name="startDate" value = "${(form.startDate)!}" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(至)</div>
                <div class="common_text_val"><input name="endDate" value = "${(form.endDate)!}"  class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">状态</div>
                <div class="common_text_val">
                    <select name="status" class="my_radius grid_page_text">
                        <option <#if form.status==100>selected</#if> value = "100"></option>
                        <option <#if form.status==0>selected</#if> value="0">未引用</option>
                        <option <#if form.status==1>selected</#if> value="1">部分引用</option>
                        <option <#if form.status==2>selected</#if> value="2">已完成</option>
                    </select>
                </div>
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
                    <td>预计收货日期</td>
                    <td>单据日期</td>
                    <td>入库仓库</td>
                    <td>供应商编码</td>
                    <td>供应商名称</td>
                    <td>联系人</td>
                    <td>移动电话</td>
                    <td>状态</td>
                    <td>单据摘要</td>
                </tr>
            </thead>
            <tbody>
                <#if (pageModel.totalRecords > 0)>
                    <#list pageModel.records as record>
                    <tr>
                        <td><a href="<@url value='/readyreceipt/get?orderNumber=${(record.orderNumber)!}' />" data-hover-tip="查看详情">${(record.orderNumber)! }</a></td>
                        <td>${(record.receiptDate)!}</td>
                        <td>${(record.billDate)!}</td>
                        <td>${(record.inBoundStorageName)!}</td>
                        <td>${(record.partnerCode)!}</td>
                        <td>${(record.partnerName)!}</td>
                        <td>${(record.contactsFirstName)!}.${(record.contactsLastName)!}</td>
                        <td>${(record.mobilePhone)!}</td>
                        <td><#if record.readyStatus ==0>未引用</#if><#if record.readyStatus ==1>部分引用</#if><#if record.readyStatus ==2>已完成</#if></td>
                        <td>${(record.summary)!}</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <#if isDraft == false >
            <@pageByParam url="/readyreceipt/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        <#else>
            <@pageByParam url="/readyreceipt/getdraftlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
    </#if>
</@insert>