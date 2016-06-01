<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#-- <div onclick="" class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div> -->
        <div onclick="changeInventoryReceiptStatus();" class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">关闭</div>
        </div>
    </div>
    <div class="search_section">
    	<form action="<@url value='/inventoryreceipt/getlist'/>" method="post">
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">关键字</div>
	            <div class="common_text_val"><input name="keywords" value = "${form.keywords}" class="input_text_common my_radius" data-click-tip="输入单据编号、商品、所有人关键字" placeholder="单据编号、商品、所有人"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">单据日期(从)</div>
	            <div class="common_text_val"><input name="startDate" value = "${form.startDate}" class="input_text_common my_radius" data-type="date"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">单据日期(至)</div>
	            <div class="common_text_val"><input name="endDate" value = "${form.endDate}" class="input_text_common my_radius" data-type="date"></div>
	        </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">状态</div>
                <div class="common_text_val">
                    <select name="status" class="my_radius grid_page_text">
                        <option <#if form.status==100>selected</#if> value = "100"></option>
                        <option <#if form.status==0>selected</#if> value="0">待收货</option>
                        <option <#if form.status==1>selected</#if> value="1">部分收货</option>
                        <option <#if form.status==2>selected</#if> value="2">已完成</option>
                    </select>
                </div>
            </div>
	        <div class="search_btn common_text_div">
	            <button type="submit" class="btn_common my_radius btn_search">搜索</button>
	        </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <div id="inventoryReceiptData"> 
	    <table>
	        <thead>
	            <tr>
	                <td width="4%"></td>
	                <td width="9%">单据编号</td>
	                <td width="9%">单据日期</td>
	                <td width="9%">入库仓库</td>
	                <td width="9%">起运港</td>
	                <td width="9%">目的港</td>
	                <td width="9%">运输方式</td>
	                <td width="9%">应收数量</td>
	                <td width="9%">所有人</td>
	                <td width="9%">状态</td>
	            </tr>
	        </thead>
	    </table>
	    <div class="data_grid">
	        <table>
	            <tbody>
	                <#if pageModel?? && (pageModel.records)??>
    	                <#list pageModel.records as p>
    		                <tr>
    		                    <td width="4%"><input id="${p.receiptNumber}" type="radio" name="check_box"></td>
    		                    <td width="9%"><a href="<@url value='/inventoryreceipt/get?receiptNumber=${p.receiptNumber}' />" data-hover-tip="查看详情">${p.receiptNumber}</a></td>
    		                    <td width="9%">${p.billsDate}</td>
    		                    <td width="9%">${p.storageName}</td>
    		                    <td width="9%">${p.startStation}</td>
    		                    <td width="9%">${p.endStation}</td>
    		                    <td width="9%">${p.transportationType}</td>
    		                    <td width="9%">${p.receiptCounts}/${p.counts}</td>
    		                    <td width="9%">${p.consigneeName}</td>
    		                    <td width="9%"><#if p.status=0>待收货<#elseif p.status=1>部分收货<#else>已完成</#if></td>
    		                </tr>
    	                </#list>
    	           </#if>
	            </tbody>
	        </table>
	    </div>
    </div>
    <#if pageModel?? && pageModel.totalRecords??>
        <@pageByParam url="/inventoryreceipt/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>
<script>
    function changeInventoryReceiptStatus(){
        var orderNumber = [];
        var checkbox = $("input[name='check_box']:checked");
        for (var i = 0; i < checkbox.length; i++) {
            orderNumber[i] = $(checkbox[i]).attr("id");
        }
        if(orderNumber!=""){
            siping.confirm("是否确认关闭?", function(){
                siping.ajax({
                    method : 'get',
                    url : '/inventoryreceipt/changestatus?orderNumber=' + orderNumber,
                    async : true,
                    success : function(data) {
                        if (data.success) {
                            siping.alert(1, data.msg);
                        } else {
                            siping.alert(0, data.msg);
                        }
                    }
                });
            });
        } else {
            siping.alert(0, "请选择行");
        }
    }
</script>