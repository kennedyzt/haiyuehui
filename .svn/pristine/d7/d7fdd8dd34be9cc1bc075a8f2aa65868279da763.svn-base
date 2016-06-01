<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#-- <div class="rect_block">
            <div class="rect_top"><a href="<@url value="/sellreturns/getdraftlist" />"><img src="<@static value='/icons/draft.png' />"></a></div>
            <div class="rect_bottom">草稿箱</div>
        </div>
        <div class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div> -->
    </div>
    <div class="search_section">
        <form action="<@url value='/sellreturns/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input id="keywords" name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、客户、所有人关键字" placeholder="单据编号、商品、客户、所有人"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(从)</div>
                <div class="common_text_val"><input id="startDate" name="startDate" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(至)</div>
                <div class="common_text_val"><input id="endDate" name="endDate" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="search_btn common_text_div">
                <button class="btn_common my_radius btn_search" type="submit">搜索</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <table>
        <thead>
            <tr>
                <td width="4%"></td>
                <td width="12%">单据编号</td>
                <td width="12%">单据日期</td>
                <td width="12%">入库仓库</td>
                <td width="12%">单据数量</td>
                <td width="12%">单据金额</td>
                <td width="12%">客户名称</td>
                <td width="12%">所有人</td>
                <td width="12%">单据摘要</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#if pageModel?? && (pageModel.records)??>
                    <#list pageModel.records as p>
                        <tr>
                            <td width="4%">
                                <!--<input id="${p.returnsNumber}" type="radio" name="check_box">-->
                            </td>
                            <td width="12%"><a href="<@url value='/sellreturns/get?returnsnumber=${p.returnsNumber}' />" data-hover-tip="查看详情">${p.returnsNumber}</a></td>
                            <td width="12%">${p.billsDate}</td>
                            <td width="12%">${p.storageName}</td>
                            <td width="12%">${p.counts}</td>
                            <td width="12%">${p.totalPrice}</td>
                            <td width="12%">${p.partnerName}</td>
                            <td width="12%">${p.nickname}</td>
                            <td width="12%">${p.summary}</td>
                        </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
     <#if (pageModel.records)?? >
        <@pageByParam url="/sellreturns/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>