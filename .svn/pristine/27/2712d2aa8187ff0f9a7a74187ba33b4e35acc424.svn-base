<#setting classic_compatible=true>
<@insert template="layout/layer">
    <div class="list_page">
        <div class="search_section">
            <form action="/hyh_intranet/materialunit/openwin" method="post">
                <div class="partner_text_div common_text_div">
                    <div class="common_text_title">关键字</div>
                    <div class="common_text_val"><input id="key" name="key" class="input_text_common my_radius" data-click-tip="输入单位名称,编号" placeholder="单位名称,编号"></div>
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
                    <td width="10%"></td>
                    <td width="30%">单位编号</td>
                    <td width="30%">单位名称</td>
                    <td width="30%">单位描述</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid" style="height:310px;">
            <table>
                <tbody>
                   <#list pageModel.records as m>
                    <tr>
                        <td width="10%">
                            <input id="${m.id}" type="radio" name="material_unit_check_box">
                        </td>
                        <td width="30%">${m.unitNo}</td>
                        <td width="30%">${m.unitName}</td>
                        <td width="30%">${m.description}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <#if pageModel.totalRecords??>
            <@pageByParam url="/materialunit/openwin" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
         <div class="order_btns_l common_text_div">
            <button onclick="chooseMaterialUnit();" class="btn_common my_radius">确定</button>
        </div>
    </div>
</@insert>