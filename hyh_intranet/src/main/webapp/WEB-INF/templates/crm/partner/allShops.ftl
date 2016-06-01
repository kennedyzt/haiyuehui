<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <div class="search_section">
        <form action="<@url value='getlist' />" method="post" >
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入商家编码、名称关键字" placeholder="商家编码、名称"></div>
            </div>
            <div class="search_btn common_text_div">
                <button id="" class="btn_common my_radius btn_submit">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <div class="table_title">商家列表</div>
    <table style="table-layout: fixed;">
        <thead>
            <tr>
                <td>编码</td>
                <td>名称</td>
                <td>联系人</td>
                <td>电话</td>
                <td>地址</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table style="table-layout: fixed;">
            <tbody>
                <#list pageModel.records as s>
                    <tr>
                        <td>${s.shopNo!''}</td>
                        <td>${s.shopName!''}</td>
                        <td>${s.contact!''}</td>
                        <td>${s.telephone!''}</td>
                        <td>${s.address!''}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
            <@pageByParam url="/shop/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</div>
</@insert>