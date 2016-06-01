<@insert template="login/right">
    <div class="search_section">
        <form action="<@url value='/reportauditlist/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">商品货号</div>
                <div class="common_text_val"><input id="materialNo" name="materialNo" class="input_text_common my_radius grid_page_text" value="${original.materialNo!''}"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">商品名称</div>
                <div class="common_text_val"><input id="materialName" name="materialName" class="input_text_common my_radius grid_page_text" value="${original.materialName!''}"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title"><span data-required="true">所属商品类型</span></div>
                <div class="common_text_val"><input type="hidden" id="father_id"><input id="fatherType" data-type="materialType" name="materialType" readonly class="input_text_common my_radius grid_page_text"><div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style"></div></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">仓库</div>
                <div class="common_text_val">
                    <select id="outboundStorageId" name="storageNo" class="my_radius grid_page_text">
                        <option value=""></option>
                        <#list storages as s>
                            <#if (original.storageNo??) && (original.storageNo==s.storageNo)>
                                <option value="${s.storageNo}" selected="selected">${s.storageName}</option>
                            <#else>
                                <option value="${s.storageNo}" >${s.storageName}</option>
                            </#if>
                        </#list>>
                    </select>
                </div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
            <div class="search_btn common_text_div">
                <button type="button" class="btn_common my_radius btn_search" onclick="preDoPrint('.data_grid');">打印预览</button>
            </div>
            <div class="search_btn common_text_div">
                <button type="button" class="btn_common my_radius btn_search" onclick="doPrint('.data_grid');">打印</button>
            </div>
            <div class="search_btn common_text_div">
                <button type="button" class="btn_common my_radius btn_search" onclick="printmore('.data_grid');">连打</button>
            </div>
            <div class="search_btn common_text_div">
                <a href="<@url value='/print' />" >套打 </a>
            </div>
            <div class="search_btn common_text_div">
                <a href="<@url value='/qrcode' />" >显示二维码 </a>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <div class="data_grid">
        <table id="tab" style="table-layout: fixed;">
            <thead>
                <tr>
                    <td>商品货号</td>
                    <td>商品名称</td>
                    <td>计量单位</td>
                    <td>数量</td>
                    <td>成本</td>
                    <td>金额</td>
                </tr>
            </thead>
            <tbody>
                <#if pageModel??>
                    <#list pageModel.records as r>
                        <tr>
                            <td><a href="<@url value='/reportauditlist/detail?materialno=${r.materialNo}&tag=detail' />" >${r.materialNo} </a></td>
                            <td>${r.materialName }</td>
                            <td>${r.materialUnitName}</td>
                            <td>${r.counts }</td>
                            <td>
                                <#if (r.isBatch??) && r.isBatch><#else>${r.cost}</#if>
                            </td>
                            <td>${r.inventoryTotalPrice}</td>
                        </tr>
                    </#list>
                </#if>
            </tbody>
            <tfoot>
                <tr>
                    <td><strong>合计</strong></td>
                    <td></td>
                    <td></td>
                    <td><strong>${totalCounts}</strong></td>
                    <td><strong>${totalCost}</strong></td>
                    <td><strong>${totalPrice}</strong></td>
                </tr>
            </tfoot> 
        </table>
    </div>
    <#if pageModel?? && (pageModel.records)??>
        <@pageByParam url="/reportauditlist/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>
