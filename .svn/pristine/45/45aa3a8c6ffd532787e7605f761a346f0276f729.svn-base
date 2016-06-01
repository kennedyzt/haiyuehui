<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section" style="overflow: visible;">
        <form action="<@url value='/reportinventorystatus/getlist'/>" method="post">
            <div class="grid_page_text_div common_text_div">
                <div class="common_text_title">商品名称</div>
                <div class="common_text_val"><input id="materialName" name="materialName" class="input_text_common my_radius grid_page_text" value="${original.materialName!}"></div>
            </div>
            <div class="grid_page_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">所属商品类型</span></div>
                <div class="common_text_val"><input type="hidden" id="father_id"><input id="fatherType" data-type="materialType" name="materialType" readonly class="input_text_common my_radius grid_page_text"><div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" disabled="disabled" ></div></div>
            </div>
            <div class="grid_page_text_div common_text_div">
                <div class="common_text_title">仓库</div>
                <div class="common_text_val">
                    <select name="storageId" class="my_radius grid_page_text">
                        <option value=""></option>
                        <#list storages as s>
                             <#if (original.storageId??) && (original.storageId==s.storageNo)>
                                <option value="${s.storageNo}" selected="selected">${s.storageName}</option>
                            <#else>
                                <option value="${s.storageNo}" >${s.storageName}</option>
                            </#if>
                        </#list>>
                    </select>
                </div>
            </div>
            <div class="grid_page_text_div common_text_div">
                <div class="common_text_title"><input type="checkbox" id="maxInventory" name="isMaxInventory" <#if original.isMaxInventory> checked="checked" </#if>></div>
                <div class="common_text_val">大于最大库存</div>
            </div>
            <div class="grid_page_text_div common_text_div">
                <div class="common_text_title"><input type="checkbox" id="minInventory" name="isMinInventory"  <#if original.isMinInventory> checked="checked" </#if>></div>
                <div class="common_text_val">小于最小库存</div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <div class="data_grid">
        <table style="table-layout: fixed;">
            <thead>
                <tr>
                    <td>商品货号</td>
                    <td>商品名称</td>
                    <td>计量单位</td>
                    <td>存货量</td>
                    <td>已承诺</td>
                    <td>已订购</td>
                    <td>可用量</td>
                    <td>最大库存量</td>
                    <td>最小库存量</td>
                </tr>
            </thead>
            <tbody>
                <#if pageModel??>
                    <#list pageModel.records as r>
                    <tr>
                        <td><a href="<@url value='/reportinventorystatus/detail?materialno=${ r.materialNo }' />">${ r.materialNo }</a></td>
                        <td>${ r.materialName }</td>
                        <td>${ r.materialUnitName}</td>
                        <td>${ r.inventoryAmount}</td>
                        <td>${ r.promisedAmount}</td>
                        <td>${ r.orderedAmount}</td>
                        <td>${ r.availableAmount }</td>
                        <td>${ r.maxInventory }</td>
                        <td>${ r.minInventory }</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
            <tfoot>
                <tr>
                    <td><strong>合计</strong></td>
                    <td></td>
                    <td></td>
                    <td><strong>${addInventoryAmount}</strong></td>
                    <td><strong>${addPromisedAmount}</strong></td>
                    <td><strong>${addOrderedAmount}</strong></td>
                    <td><strong>${addAvailableAmount}</strong></td>
                    <td><strong></strong></td>
                    <td><strong></strong></td>
                </tr>
            </tfoot> 
        </table>
    </div>
    <#if pageModel?? && (pageModel.records)??>
        <@pageByParam url="/reportinventorystatus/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>