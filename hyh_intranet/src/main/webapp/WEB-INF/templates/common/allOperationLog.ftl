<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
    	<form action="<@url value='/operationLog/getlist'/>" method="post">
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">用户</div>
	            <div class="common_text_val"><input id="nickName" name="nickName" class="input_text_common my_radius grid_page_text" onclick="openWinGetUser();"><input id="userId" name="createBy" type = "hidden"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">操作日期(从)</div>
	            <div class="common_text_val"><input name="startDate" class="input_text_common my_radius" data-type="firstday"></div>
	        </div>
	        <div class="partner_text_div common_text_div">
	            <div class="common_text_title">操作日期(至)</div>
	            <div class="common_text_val"><input name="endDate" class="input_text_common my_radius" data-type="lastday"></div>
	        </div>
	        <div class="search_btn ">
	            <button id="submit" type="submit" class="btn_common my_radius btn_search">搜索</button>
	            <button type="button" onclick="deleteOperationLog();" class="btn_common my_radius btn_search">清除</button>
	        </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <table>
        <thead>
            <tr>
                <td width="10%">序号</td>
                <td width="15%">用户</td>
                <td width="15%">姓名</td>
                <td width="15%">操作类型</td>
                <td width="30%">日志</td>
                <td width="15%">时间</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#if (pageModel.records)! >
                	<#list pageModel.records as log>
    	                <tr>
    	                    <td width="10%">${log_index+1}</td>
    	                    <td width="15%"> ${log.createUserName!'admin'}</td>
    	                    <td width="15%">${log.nickName!'admin'}</td>
    	                    <td width="15%">${log.operationType!}</td>
    	                    <td width="30%">${log.operationLog!}</td>
    	                    <td width="15%">${log.createDate!}</td>
    	                </tr>
                    </#list>
                  </#if>
            </tbody>
        </table>
    </div>
    <#if (pageModel.records)! >
        <@pageByParam url="/operationLog/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>
<script>
    function deleteOperationLog(){
        siping.confirm("确认清除日志?",function(){
            $.post(createUrl("/operationLog/delete"),{createBy:$("#userId").val(),startDate:$("#startDate").val(),endDate:$("#endDate").val()},function(data){
                 if(data.success){
                    siping.alert(1,data.msg);
                    $("#submit").trigger("click");
                }else{
                    siping.alert(0,data.msg);
                }
                
            });
        });
    }
</script>