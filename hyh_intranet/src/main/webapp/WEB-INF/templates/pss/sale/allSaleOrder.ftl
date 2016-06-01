<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#-- <div class="rect_block">
            <div class="rect_top"><a href="<@url value='/sellorder/getdraftlist'/>"><img src="<@static value='/icons/copy_to.png' />"></a></div>
            <div class="rect_bottom">草稿箱</div>
        </div>
        <div class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
        <div class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/copy_to.png' />"></a></div>
            <div class="rect_bottom">复制到</div>
        </div> -->
    </div>
    <div class="search_section">
    	<form action="<@url value='/sellorder/getlist'/>" method="post">
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">关键字</div>
	            <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、客户、所有人关键字" placeholder="单据编号、商品、客户、所有人"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">单据日期(从)</div>
	            <div class="common_text_val"><input name="startDate" class="input_text_common my_radius" data-type="date"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">单据日期(至)</div>
	            <div class="common_text_val"><input name="endDate" class="input_text_common my_radius" data-type="date"></div>
	        </div>
	        <div class="search_btn common_text_div">
	            <button type="submit" class="btn_common my_radius btn_search">搜索</button>
	        </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <table>
        <thead>
            <tr>
                <td width="4%"></td>
                <td width="10%">单据编号</td>
                <td width="10%">原始订单号</td>
                <td width="10%">单据日期</td>
                <td width="10%">客户编号</td>
                <td width="10%">客户名称</td>
                <td width="16%">单据摘要</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#if (pageModel.records)?? >
                	<#list pageModel.records as p>
    	                <tr>
    	                    <td width="4%">
    	                        <!--<input id="" type="radio" name="check_box">-->
    	                    </td>
    	                    <td width="10%"><a href="<@url value='/sellorder/get?ordernumber=${p.orderNumber}' />" data-hover-tip="查看详情">${p.orderNumber}</a></td>
    	                    <td width="10%">${p.fromBillsNo!}</td>
    	                    <td width="10%">${p.billsDate!}</td>
    	                    <td width="10%">${p.partnerCode!}</td>
    	                    <td width="10%">${p.partnerName!}</td>
    	                    <td width="16%">${p.summary!}</td>
    	                </tr>
                    </#list>
                  </#if>
            </tbody>
        </table>
    </div>
    <#if (pageModel.records)?? >
        <@pageByParam url="/sellorder/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>