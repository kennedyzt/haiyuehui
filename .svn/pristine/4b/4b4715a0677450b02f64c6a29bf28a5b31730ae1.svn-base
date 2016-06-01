<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
            <form action="<@url value='/orderpick/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据编号</div>
                <div class="common_text_val"><input id="keywords" name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品名称,商品货号关键字" placeholder="单据编号，商品名称，商品货号"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(从)</div>
                <div class="common_text_val"><input id="start_date" name="startDate" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(至)</div>
                <div class="common_text_val"><input id="end_date" name="endDate" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">状态</div>
                <div class="common_text_val">
                    <select name="status" class="my_radius grid_page_text">
                        <option <#if form.status==100>selected</#if> value = "100"></option>
                        <option <#if form.status==0>selected</#if> value="0">未打印拣货单</option>
                        <option <#if form.status==1>selected</#if> value="1">已打印拣货单</option>
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
    <table style="table-layout: fixed;width: 99%;margin: auto;">
        <thead>
            <tr>
                <td width="5%" style="text-align:center;">序号</td>
                <td width="10%">单据编号</td>
                <td width="40%">来源单据</td>
                <td width="10%">单据日期</td>
                <td width="10%">仓库名称</td>
                <td width="10%">SKU总数</td>
                <td width="10%">商品总数</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table style="table-layout: fixed;">
            <tbody>
                <#if (pageModel.totalRecords > 0)>
                    <#list pageModel.records as record>
                    <tr>
                        <td width="5%" style="color:blue;text-align:center;">${record_index+1}</td>
                        <td width="10%"><a href="<@url value='/orderpick/get?orderNumber=${ record.orderNumber }' />" data-hover-tip="查看详情">${ record.orderNumber }</a></td>
                        <td width="40%">${ record.fromBillsNumbers }</td>
                        <td width="10%">${ record.billsDate }</td>
                        <td width="10%">${ record.storageName }</td>
                        <td width="10%">${ record.skuCounts }</td>
                        <td width="10%">${ record.materialCounts }</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/orderpick/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>