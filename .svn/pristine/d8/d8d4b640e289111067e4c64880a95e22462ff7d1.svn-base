<#setting classic_compatible=true>
<#setting boolean_format="true,false">
<@insert template="layout/layer">
<div class="list_page" style="overflow: auto;">
    <div class="search_section">
        <form action="<@url value='/material/openwin?functionName=${functionName}&isPurchase=${isPurchase?string}&isSell=${isSell?string}&isInventory=${isInventory?string}'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keyword" class="input_text_common my_radius" data-click-tip="输入编号、名称" placeholder="编号、名称"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">国际编码</div>
                <div class="common_text_val"><input name="barcode" class="input_text_common my_radius" data-click-tip="输国际编码" placeholder="国际编码"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">组</div>
                <div class="common_text_val">
                    <select name="materialGroupId" class="my_radius">
                        <option></option>
                        <#list materialGroups as m>
                            <option value="${m.id}">${m.groupName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div id="material_id_hidden_div" style="display:none;">
                <#list materialIds as id>
                    <input name = "materialIds" value = "${id}"></input>
                </#list>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <table>
        <thead>
            <tr>
                <td width="4%"></td>
                <td width="19%">编号</td>
                <td width="19%">名称</td>
                <td width="19%">国际编码</td>
                <td width="19%">规格及型号</td>
                <td width="19%">单位</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid_forwin">
        <table>
            <tbody>
                <#list pageModel.records as p>
                    <tr id="${p_index+1}">
                        <td width="4%">
                            <input id="_${p.id}" onclick="addChooseMaterialItem(this.id);" type="checkbox" <#if materialIds!?seq_contains(p.id)==true>checked</#if> name="check_box">
                            <span style="display:none;"  id="materialId_${p.id}">${p.id}</span>
                            <span style="display:none;" id="brand_${p.id}">${p.brand}</span>
                            <span style="display:none;" id="season_${p.id}">${p.season}</span>
                            <span style="display:none;" id="isBatch_${p.id}"><#if p.isBatch==true>true<#else>false</#if></span>
                        </td>
                        <td id="materialNo_${p.id}" width="19%">${p.materialNo}</td>
                        <td id="materialName_${p.id}" width="19%">${p.materialName}</td>
                        <td id="barcode_${p.id}" width="19%">${p.barcode}</td>
                        <td id="specificationsModel_${p.id}" width="19%">${p.specificationsModel}</td>
                        <td id="unitName_${p.id}" width="19%">${p.unitName}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/material/openwin?functionName=${functionName}&isPurchase=${isPurchase?string}&isSell=${isSell?string}&isInventory=${isInventory?string}" pageNo="${pageModel.pageNo}" pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
    <div class="order_btns_l common_text_div">
        <button onclick="chooseMaterial('${functionName}');" class="btn_common my_radius">确定</button>
    </div>
</div>
</@insert>