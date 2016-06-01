<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">商品货号</div>
            <div class="common_text_val"><input id="materialNo" name="materialNo" class="input_text_common my_radius grid_page_text" disabled="disabled" value="${materialno}"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">商品名称</div>
            <div class="common_text_val"><input id="materialName" name="materialName" class="input_text_common my_radius grid_page_text" disabled="disabled" value="${audit.materialName}"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">所属商品类型</span></div>
            <div class="common_text_val"><input type="hidden" id="father_id"><input id="fatherType" data-type="materialType" name="materialType" readonly class="input_text_common my_radius grid_page_text"><div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" disabled="disabled" ></div></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">仓库</div>
            <div class="common_text_val">
                <select id="outboundStorageId" class="my_radius grid_page_text" disabled="disabled">
                    <option value=""></option>
                    <#list storages as s>
                        <#if request?? && (request.storageNo==s.storageNo)>
                            <option value="${s.storageNo}" selected="selected">${s.storageName}</option>
                        <#else>    
                            <option value="${s.storageNo}">${s.storageName}</option>
                        </#if>
                    </#list>>
                </select>
            </div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"></div>
            <div class="common_text_val"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"></div>
            <div class="common_text_val"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"></div>
            <div class="common_text_val"></div>
        </div>
        <input type="hidden" value="${materialno}"/>
        <div style="clear:both"></div>
    </div>
    <div class="data_grid">
        <table style="table-layout: fixed;">
            <thead>
                <tr>
                    <td>商品货号</td>
                    <td>商品名称</td>
                    <td>单据日期</td>
                    <td>单据编号</td>
                    <td>仓库编码</td>
                    <td>仓库名称</td>
                    <td>计量单位</td>
                    <td>数量</td>
                    <td>成本</td>
                    <td>金额</td>
                    <td>累计数量</td>
                    <td>累计成本</td>
                    <td>累计金额</td>
                </tr>
            </thead>
            <tbody>
                <#if audit?? && (audit.detailList)??>
                    <#list audit.detailList as d>
                    <tr>
                        <td>${d.materialNo}</td>
                        <td>${d.materialName}</td>
                        <td>${d.billsDate}</td>
                        <td>${d.fromBillsId}</td>
                        <td>${d.storageNo} </td>
                        <td>${d.storageName}</td>
                        <td>${d.materialUnitName}</td>
                        <td><#if d.isInbound> ${d.counts} <#else>${-d.counts}</#if></td>
                        <td>${d.cost}</td>
                        <td><#if d.isInbound>${d.inventoryTotalPrice} <#else>${-d.inventoryTotalPrice} </#if></td>
                        <td>${d.addCounts}</td>
                        <td> <#if (audit.isBatch??) && (audit.isBatch)> <#else>${d.addCost}</#if></td>
                        <td>${d.addInventoryTotalPrice}</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>
</@insert>