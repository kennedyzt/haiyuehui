<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#if form.status==0>
            <div class="rect_block">
                <div class="rect_top"> <#if form.status==0><a onclick="addOrderPick();"><img src="<@static value='/icons/copy_to.png' />"></a></#if></div>
                <div class="rect_bottom">生成拣货单</div>
            </div>
        </#if> 
         <#if form.status==5>
             <div class="rect_block">
                <div class="rect_top"><a onclick="addSaleShipments();"><img src="<@static value='/icons/copy_to.png' />"></a></div>
                <div class="rect_bottom">生成销售发货单</div>
             </div>
         </#if>
    </div>
    <div class="search_section">
    	<form action="<@url value='/readyshipments/getlist'/>" method="post">
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
                        <option <#if form.status==0>selected</#if> value="0">未生成拣货单</option>
                        <option <#if form.status==2>selected</#if> value="2">已生成拣货单</option>
                        <option <#if form.status==3>selected</#if> value="3">复核暂存</option>
                        <option <#if form.status==4>selected</#if> value="4">复核通过</option>
                        <option <#if form.status==5>selected</#if> value="5">待生成发货单</option>
                        <option <#if form.status==6>selected</#if> value="6">已生成发货单</option>
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
                <td width="9%">原始订单号</td>
                <td width="9%">单据日期</td>
                <td width="9%">收货人姓名</td>
                <td width="9%">收货人电话</td>
                <td width="9%">收货地址</td>
                <td width="9%">邮编</td>
                <td width="5%">重量</td>
                <td width="9%">仓库</td>
                <td width="9%">备注</td>
                <td width="4%">状态</td>
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
    	                        <input id="${p.orderNumber}" value="${p.storageId}" type="checkBox" name="check_box" >
    	                    </td>
    	                    <td width="9%"><a href="<@url value='/readyshipments/get?ordernumber=${p.orderNumber}' />" data-hover-tip="查看详情">${p.orderNumber}</a></td>
    	                    <td width="9%">${p.fromBillsNo}</td>
    	                    <td width="9%">${p.billsDate}</td>
    	                    <td width="9%">${p.consigneeName}</td>
    	                    <td width="9%">${p.consignessPhone}</td>
    	                    <td width="9%">${p.consigneeAddress}</td>
    	                    <td width="9%">${p.consignessPostcode}</td>
    	                    <td width="5%">${p.weight}</td>
    	                    <td width="9%">${p.storageName}<input name="storageId" type="hidden" value="${p.storageId}"></td>
    	                    <td width="9%">${p.summary}</td>
    	                    <td width="4%"><#if p.status == 0>未生成拣货单<#elseif  p.status == 2>已生成拣货单<#elseif  p.status == 3>复核暂存<#elseif  p.status == 4>复核通过<#elseif  p.status == 5>待生成发货单<#elseif  p.status == 6>已生成发货单</#if></td>
    	                </tr>
                    </#list>
                  </#if>
            </tbody>
        </table>
    </div>
    <#if (pageModel.records)?? >
        <@pageByParam url="/readyshipments/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>
<script>
    function selectAll(){  
        if ($("#selectAll").prop("checked")) {  
            $("[name='isAdd']").prop("checked", true);  
        } else {  
            $("[name='isAdd']").prop("checked", false);  
        }  
    }
    function addOrderPick() {
        var orderNumbers = [];
        var storageId="";
        var checkbox = $("input[name='check_box']:checked");
        for (var i = 0; i < checkbox.length; i++) {
            orderNumbers[i] = checkbox[i].id;
        }
        if (orderNumbers != "") {
            siping.ajax({
                method : 'get',
                url : '/orderpick/add?orderNumbers=' + orderNumbers+'&storageId='+storageId,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        } else {
            siping.alert(0, "请选择单据");
        }
    }
    
    function addSaleShipments(){
        var orderNumbers = [];
        var checkbox = $("input[name='check_box']:checked");
        for (var i = 0; i < checkbox.length; i++) {
            orderNumbers[i] = checkbox[i].id;
        }
        if (orderNumbers != "") {
            siping.ajax({
                method : 'get',
                url : 'sellshipments/adds?orderNumbers=' + orderNumbers,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        } else {
            siping.alert(0, "请选择单据");
        }
    }
</script>