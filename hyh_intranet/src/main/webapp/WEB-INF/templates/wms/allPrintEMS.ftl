<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <div class="rect_block" onclick="printEMS();"> 
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印快递单</div>
        </div>
    </div>
    <div class="search_section">
    	<form action="<@url value='/readyshipments/printems'/>" method="post">
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">关键字</div>
	            <div class="common_text_val"><input name="keywords" value = "${form.keywords}" class="input_text_common my_radius" data-click-tip="输入单据编号" placeholder="单据编号"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">单据日期(从)</div>
	            <div class="common_text_val"><input name="startDate" value = "${form.startDate}" class="input_text_common my_radius" data-type="date"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">单据日期(至)</div>
	            <div class="common_text_val"><input name="endDate" value = "${form.endDate}"  class="input_text_common my_radius" data-type="date"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
                <div class="common_text_title">状态</div>
                <div class="common_text_val">
                    <select name="status" class="my_radius grid_page_text">
                        <option <#if form.status==100>selected</#if> value = "100"></option>
                        <option <#if form.status==0>selected</#if> value="0">未打印快递单</option>
                        <option <#if form.status==1>selected</#if> value="1">已打印快递单</option>
                    </select>
                </div>
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
                <td width="9%">单据编号</td>
                <td width="9%">单据日期</td>
                <td width="9%">发货日期</td>
                <td width="9%">收货人姓名</td>
                <td width="9%">收货人电话</td>
                <td width="9%">收货地址</td>
                <td width="9%">邮编</td>
                <td width="9%">仓库</td>
                <td width="9%">备注</td>
                <td width="9%">状态</td>
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
    	                        <input id="${p.orderNumber}" value="${p.storageId}" type="checkBox" name="check_box">
    	                    </td>
    	                    <td width="9%"><a href="<@url value='/readyshipments/get?ordernumber=${p.orderNumber}' />" data-hover-tip="查看详情">${p.orderNumber}</a></td>
    	                    <td width="9%">${p.billsDate}</td>
    	                    <td width="9%">${p.shipmentsDate}</td>
    	                    <td width="9%">${p.consigneeName}</td>
    	                    <td width="9%">${p.consignessPhone}</td>
    	                    <td width="9%">${p.consigneeAddress}</td>
    	                    <td width="9%">${p.consignessPostcode}</td>
    	                    <td width="9%">${p.storageName}<input name="storageId" type="hidden" value="${p.storageId}"></td>
    	                    <td width="9%">${p.summary}</td>
    	                    <td width="9%"><#if p.status == 0>未打印快递单<#elseif p.status==1>已打印快递单</#if></td>
    	                </tr>
                    </#list>
                  </#if>
            </tbody>
        </table>
    </div>
    <#if (pageModel.records)?? >
        <@pageByParam url="/readyshipments/printems" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>
 <@js src="js/siping/wms/allPrintEMS.js" />