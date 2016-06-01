<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
    	<form action="<@url value='/sellshipments/getdraftlist'/>" method="post">
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">关键字</div>
	            <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、所有人"></div>
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
                <td width="10%"><a class="td_head_a" onclick="deleteDeliverySaleOrder()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="12%">单据编号</td>
                <td width="12%">单据日期</td>
                <td width="10%">出库仓库</td>
                <td width="10%">单据数量</td>
                <td width="12%">单据金额</td>
                <td width="12%">客户名称</td>
                <td width="10%">所有人</td>
                <td width="12%">单据摘要</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
            	<#if (pageModel.records)?? >
	            	<#list pageModel.records as p>
		                <tr>
		                    <td width="10%">
		                        <input id="${p.shipmentsNumber!}" type="radio" name="check_box">
		                        <a class="td_inner_a" href="<@url value='/sellshipments/edit?shipmentsNumber=${p.shipmentsNumber!}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
		                    </td>
		                    <td width="12%"><a href="<@url value='/sellshipments/get?shipmentsnumber=${p.shipmentsNumber!}' />" data-hover-tip="查看详情">${p.shipmentsNumber!}</a></td>
		                    <td width="12%">${p.billsDate!}</td>
		                    <td width="10%">${p.storageName!}</td>
		                    <td width="10%">${p.counts!}</td>
		                    <td width="12%">${p.totalPrice!}</td>
		                    <td width="12%">${p.partnerName!}</td>
		                    <td width="10%">${p.owner!}</td>
		                    <td width="12%">${p.summary!}</td>
		                </tr>
	                </#list>
                </#if>
            </tbody>
        </table>
    </div>
</@insert>