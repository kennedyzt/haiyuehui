<#setting classic_compatible=true>
<@insert template="login/right">
    <#if isDraft == false >
        <div class="head_top_">
            <#--<div class="rect_block">
                <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
                <div class="rect_bottom">打印预览</div>
            </div> -->
            <div class="rect_block">
                <div class="rect_top"><a href="<@url value='/purchaseorder/getdraftlist ' />"><img src="<@static value='/icons/draft.png' />"></a></div>
                <div class="rect_bottom">草稿箱</div>
            </div>
        </div>
    </#if>
    <div class="search_section">
        <#if isDraft == false >
            <form action="<@url value='/purchaseorder/getlist'/>" method="post">
        <#else>
            <form action="<@url value='/purchaseorder/getdraftlist'/>" method="post">
        </#if>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input id="keywords" name="keywords" value = "${(form.keywords)!}" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、所有人"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(从)</div>
                <div class="common_text_val"><input id="start_date" name="startDate" value = "${(form.startDate)!}" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(至)</div>
                <div class="common_text_val"><input id="end_date" name="endDate" value = "${(form.endDate)!}" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">状态</div>
                <div class="common_text_val">
                    <select name="status" class="my_radius grid_page_text">
                        <option <#if (form.status)! == 100>selected</#if> value = "100"></option>
                        <option <#if (form.status)! == 0>selected</#if> value="0">未引用</option>
                        <option <#if (form.status)! == 1>selected</#if> value="1">部分引用</option>
                        <option <#if (form.status)! == 2>selected</#if> value="2">已完成</option>
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
                     <#if isDraft != false >
                        <td width="10%"><a class="td_head_a" onclick="deleteDraftPO()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                    <#else>
                    </#if>
                    <td>单据编号</td>
                    <td>预计收货日期</td>
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
                    <td>状态</td>
                    <td>单据摘要</td>
                </tr>
            </thead>
            <tbody>
                <#if (pageModel.totalRecords > 0)>
                    <#list pageModel.records as record>
                    <tr>
                        <#if isDraft != false >
                            <td>
                                <input id="${ record.orderNumber!'' }" type="radio" name="check_box">
                                <a class="td_inner_a" href="<@url value='/purchaseorder/edit?orderNumber=${ record.orderNumber }' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                            </td>
                        <#else>
                        </#if>
                        <td><a href="<@url value='/purchaseorder/get?orderNumber=${record.orderNumber}' />" data-hover-tip="查看详情">${ record.orderNumber!'' }</a></td>
                        <td>${ record.receiptDate!'' }</td>
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
                        <td><#if record.status ==0>未引用</#if><#if record.status ==1>部分引用</#if><#if record.status ==2>已完成</#if></td>
                        <td>${ record.summary!'' }</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <#if isDraft == false >
            <@pageByParam url="/purchaseorder/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        <#else>
            <@pageByParam url="/purchaseorder/getdraftlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
            <div class="order_btns_l common_text_div">
                <button id="goback_btn" class="btn_common my_radius">返回</button>
            </div>
        </#if>
    </#if>
</@insert>