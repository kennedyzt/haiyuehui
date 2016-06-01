<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <div onclick ="preViewInventoryCheck();" class="rect_block">
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
        <div onclick ="exportExcel();" class="rect_block">
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">导出Excel</div>
        </div>
    </div>
    <div class="search_section">
    <form action="<@url value='/inventorycheck/getlist'/>" method="post">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="searchKey" name="searchKey" class="input_text_common my_radius" data-click-tip="输入单据编号、商品货号、名称、国际编码" placeholder="输入单据编号、商品货号、名称、国际编码"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">单据日期(从)</div>
            <div class="common_text_val"><input id="startTime" name="startTime" class="input_text_common my_radius" data-type="date"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">单据日期(至)</div>
            <div class="common_text_val"><input id="endTime" name="endTime" class="input_text_common my_radius" data-type="date"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">盘点状态</div>
            <div class="common_text_val">
                <select id="status" name="status" class="input_text_common my_radius">
                    <option value="2">所有</option>
                    <option <#if status==0>selected</#if> value="0">未盘点</option>
                    <option <#if status==1>selected</#if> value="1">已盘点</option>
                </select>
            </div>
        </div>
        <div class="search_btn common_text_div">
            <button class="btn_common my_radius btn_search" type="submit">搜索</button>
        </div>
        <div style="clear:both"></div>
    </form>
    </div>
    <table>
        <thead>
            <tr>
                <td width="8%"><a class="td_head_a" onclick="deleteInventoryCheck()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="14%">单据编号</td>
                <td width="14%">单据日期</td>
                <td width="14%">修改日期</td>
                <td width="10%">所有人</td>
                <td width="12%">盘点人</td>
                <td width="14%">盘点状态</td>
                <td width="28%">单据摘要</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
             <#list inventorycheckList.records as i>
                <tr>
                    <td width="8%"><input id="${i.checkNumber}" type="radio" name="check_box"><#if i.status==false><a class="td_inner_a" href="<@url value='/inventorycheck/edit?checkNumber=${i.checkNumber}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a></#if></td>
                    <td width="14%"><a href="<@url value='get?checkNumber=${i.checkNumber}' />" data-hover-tip="查看详情">${i.checkNumber}</a></td>
                    <td width="14%">${i.billsDate!''}</td>
                    <td width="14%">${i.billsEditDate!''}</td>
                    <td width="10%">${i.ownerName!''}</td>
                    <td width="12%">${i.operatorName!''}</td>
                    <td width="12%">${i.statusString!''}</td>
                    <td width="28%">${i.summary!''}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
     <#if inventorycheckList.totalRecords??>
        <@pageByParam url="/inventorycheck/getlist" pageNo="${inventorycheckList.pageNo}"  pageSize="${inventorycheckList.pageSize}" totalRecords="${inventorycheckList.totalRecords}"/>
    </#if>
    <div style="display:none;" id="inventory_check_print"></div>
</@insert>
<@js src="js/siping/report/inventoryCheck.js" />