<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section" style="overflow: visible;">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期从</span></div>
            <div class="common_text_val"><input name="startDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${original.startDate!}" disabled="disabled"></div>
        </div>
         <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">到</span></div>
            <div class="common_text_val"><input name="endDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${original.endDate!}" disabled="disabled"></div>
        </div>
         <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">仓库</div>
            <div class="common_text_val">
                <select id="outboundStorageId" name="storageId" class="my_radius grid_page_text" disabled="disabled">
                    <option value=""></option>
                    <#list storages as s>
                        <#if (original.storageId??) && (original.storageId==s.storageNo)>
                                <option value="${s.storageNo}" selected="selected">${s.storageName}</option>
                        <#else>
                                <option value="${s.storageNo}">${s.storageName}</option>
                        </#if>
                    </#list>>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">商品名称</div>
            <div class="common_text_val"><input id="materialName" name="materialName" class="input_text_common my_radius grid_page_text" value="${original.materialName!}" disabled="disabled"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">商品类型</span></div>
            <div class="common_text_val"><input type="hidden" id="father_id"><input id="fatherType" data-type="materialType" name="materialType" readonly class="input_text_common my_radius"><div id="materialTypeTree" data-type="tree" class="tree_div" style="position: relative;top: -7px;left: 10px;width: 180px; max-height: 150px;" disabled="disabled" ></div></div>
        </div>
        <div style="clear:both"></div>
    </div>
    <div class="data_grid">
        <table style="table-layout: fixed;">
            <thead>
                <tr>
                    <td>单据编号</td>
                    <td>单据日期</td>
                    <td>仓库编码</td>
                    <td>仓库名称</td>
                    <td>数量</td>
                    <td>方向</td>
                </tr>
            </thead>
            <tbody>
                <#if (detailList??)>
                    <#list detailList as d>
                    <tr>
                        <td>${ d.billsNo }</td>
                        <td>${ d.billsDate }</td>
                        <td>${ d.storageNo }</td>
                        <td>${ d.stroageName}</td>
                        <td>${ d.counts } </td>
                        <td>${ d.direction }</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
            <tfoot>
                <tr>
                    <td><strong>合计</strong></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><strong>${addCount}</strong></td>
                    <td></td>
                </tr>
            <tfoot> 
        </table>
    </div>
</@insert>