<#setting classic_compatible=true>
<#setting boolean_format="true,false">
<@insert template="layout/layer">
<script type="text/javascript">
    $(function(){
        var materials = parent.materialList;
        var materialIds = [];
        for(var i = 0 ; i < materials.length ; i ++){
            material = materials[i];
            materialIds.push(material.id);
        }
        $("#materialDataSource tr").each(function(){
            var thisMaterialId = $(this).find("span[name = 'materialId']").text();
            if(contains(materialIds,thisMaterialId)){
                $(this).find("input[type='checkbox']").attr("checked","checked");
            }
        });
    });
</script>
<div class="list_page" style="overflow: auto;">
    <div class="search_section">
        <form action="<@url value='/material/openwin?rowId=${rowId}&functionName=${functionName}&isPurchase=${isPurchase?string}&isSell=${isSell?string}&isInventory=${isInventory?string}'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keyword" class="input_text_common my_radius" data-click-tip="输入编号、名称" placeholder="编号、名称"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">国际编码</div>
                <div class="common_text_val"><input name="barcode" class="input_text_common my_radius" data-click-tip="输国际编码" placeholder="国际编码"></div>
            </div>
            <div class="partner_text_div_3 common_text_div">
                <div class="common_text_title">商品类型</div>
                <div class="common_text_val"><input type="hidden" id="father_id" name="materialTypeId"><input id="fatherType"  data-type="materialType" name="materialType" readonly class="input_text_common_small my_radius"><div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" hidden="hidden";></div></div>
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
        <table id = "materialDataSource">
            <tbody>
                <#list pageModel.records as p>
                    <tr id="${p_index+1}">
                        <td width="4%">
                            <input id="_${p.id}" onclick="addChooseMaterialItem(this.id);" type="checkbox" name="check_box">
                            <span style="display:none;" name = "materialId" id="materialId_${p.id}">${p.id}</span>
                            <span style="display:none;" id="brand_${p.id}">${p.brand}</span>
                            <span style="display:none;" id="season_${p.id}">${p.season}</span>
                            <span style="display:none;" id="isBatch_${p.id}"><#if p.isBatch==true>true<#else>false</#if></span>
                            <span style="display:none;" id="expirationDate_${p.id}">${p.expirationDate}</span>
                            <span style="display:none;" id="minOrder_${p.id}">${p.minOrder}</span>
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
        <@pageByParam url="/material/openwin?rowId=${rowId}&functionName=${functionName}&isPurchase=${isPurchase?string}&isSell=${isSell?string}&isInventory=${isInventory?string}" pageNo="${pageModel.pageNo}" pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
    <div class="order_btns_l common_text_div">
        <button onclick="chooseMaterials('${functionName}', ${rowId});" class="btn_common my_radius">确定</button>
    </div>
</div>
</@insert>