<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
    	<form action="<@url value='/operationLog/delete'/>" method="post">
	        
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">操作日期(从)</div>
	            <div class="common_text_val"><input name="startDate" class="input_text_common my_radius" data-type="date"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">操作日期(至)</div>
	            <div class="common_text_val"><input name="endDate" class="input_text_common my_radius" data-type="date"></div>
	        </div>
	        <div class="search_btn common_text_div">
	            <button type="submit" class="btn_common my_radius btn_search">清除日志</button>
	        </div>
        </form>
        <div style="clear:both"></div>
    </div>
</@insert>