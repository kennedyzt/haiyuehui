<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section" style="overflow: visible;">
        <form action="<@url value='/reportinventorybatch/getlist'/>" method="post">
            <div class="grid_page_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">单据日期从</span></div>
                <div class="common_text_val"><input  name="startDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${original.startDate!}"></div>
            </div>
             <div class="grid_page_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">到</span></div>
                <div class="common_text_val"><input id="billsDate" name="endDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${original.endDate!}"></div>
            </div>
             <div class="grid_page_text_div common_text_div">
                <div class="common_text_title">仓库</div>
                <div class="common_text_val">
                    <select id="outboundStorageId" name="storageId" class="my_radius grid_page_text">
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
                <div class="common_text_val"><input id="materialName" name="materialName" class="input_text_common my_radius grid_page_text" value="${original.materialName!}"></div>
            </div>
            <div class="grid_page_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">商品类型</span></div>
                <div class="common_text_val"><input type="hidden" id="father_id"><input id="fatherType" data-type="materialType" name="materialType" readonly class="input_text_common my_radius"><div id="materialTypeTree" data-type="tree" class="tree_div" style="position: relative;top: -7px;left: 10px;width: 180px; max-height: 150px;" disabled="disabled" ></div></div>
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
                    <td>批次</td>
                    <td>仓库</td>
                    <td>数量</td>
                    <td>生产日期</td>
                    <td>有效日期</td>
                </tr>
            </thead>
            <tbody>
                <#if pageModel??>
                    <#list pageModel.records as r>
                    <tr>
                        <td><a href="<@url value='/reportinventorybatch/detail?materialno=${ r.materialNo }' />">${ r.materialNo }</a></td>
                        <td>${ r.materialName }</td>
                        <td>${ r.batchNo}</td>
                        <td>${ r.stroageName}</td>
                        <td>${ r.counts}</td>
                        <td>${ r.productionDate}</td>
                        <td>${ r.expiryDate }</td>
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
                    <td><strong>${addCounts}</strong></td>
                    <td></td>
                    <td></td>
                  
                </tr>
            </tfoot> 
        </table>
        <#if pageModel?? && (pageModel.records)??>
            <@pageByParam url="/reportinventorybatch/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
    </div>
</@insert>