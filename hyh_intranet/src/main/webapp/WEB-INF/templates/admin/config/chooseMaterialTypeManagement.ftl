<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="list_page">
    <div id="main" class="openWinGetPartner">
    <div class="search_section">
        <form action="<@url value='/materialtype/show'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入名称、描述关键字" placeholder="名称、描述"></div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <div class="table_title">商品类型列表</div>
    <table>
        <thead>
            <tr>
                <td width="4%"></td>
                <td width="48%">名称</td>
                <td width="48%">描述</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid_for_layzer">
        <table>
            <tbody>
                <#list pageModel.records as p>
                    <tr id="tr${p_index+1}">
                        <td width="4%">
                            <input id="${p_index+1}" type="radio" name="check_box">
                            <span style="display:none;" id="id${p_index+1}">${p.id}</span>
                        </td>
                        <td id="typeName${p_index+1}" width="48%">${p.typeName}</td>
                        <td id="description${p_index+1}" width="48%">${p.description}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/materialtype/show" pageNo="${pageModel.pageNo}" pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
    <div class="order_btns_l common_text_div">
        <button onclick="chooseMaterialType();" class="btn_common my_radius">确定</button>
    </div>
    </div>
</div>
</@insert>