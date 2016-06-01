<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="list_page">
        <#-- <div class="head_top_">
            <div class="rect_block">
                <div class="rect_top"><a href="#"><img src="<@static value='/icons/export.png' />"></a></div>
                <div class="rect_bottom">批量导出</div>
            </div>
        </div> -->
        <div class="search_section">
        <form action="<@url value='/material/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="key" class="input_text_common my_radius" data-click-tip="输入编码、名称、规格、国际编码关键字" placeholder="编码、规格、国际编码" <#if searchCondition.key!=null> value="${searchCondition.key!}" </#if>></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">类别</div>
                <div class="common_text_val">
                    <input type="hidden" id="father_id" name="materialTypeId" <#if searchCondition.materialTypeId!=null> value="${searchCondition.materialTypeId!}" </#if>>
                    <input id="fatherType"  data-type="materialType" name="materialType" readonly class="input_text_common_small my_radius" <#if searchCondition.materialType!=null> value="${searchCondition.materialType!}" </#if>>
                    <div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" hidden="hidden"></div>
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">供应商</div>
                <div class="common_text_val">
                   <input id="supplierName" name="supplierName" class="input_text_common_small my_radius" onclick="openWinGetSupplierWithIdConfict()" <#if searchCondition.supplierName!=null> value="${searchCondition.supplierName!}" </#if>><input id="supplierId" name="supplierId" type="hidden"></span>
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">是否可用</div>
                <div class="common_text_val">
                    <select name="isEnable" class="my_radius">
                        <option <#if searchCondition.isEnable==true>selected</#if> value="1">可用</option>
                        <option <#if searchCondition.isEnable==false>selected</#if> value="0">不可用</option>
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
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        <div style="clear:both"></div>
        </form>
        <div style="clear:both"></div>
        </div>
        <div class="table_title">商品列表</div>
        <table>
            <thead>
                <tr>
                    <td width="10%"><a class="td_head_a" onclick="deleteMaterial()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                    <td width="15%">编码</td>
                    <td width="15%">名称</td>
                    <td width="15%">类别</td>
                    <td width="15%">规格型号</td>
                    <td width="15%">国际编码</td>
                    <td width="15%">供应商</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid">
            <table>
                <tbody>
                    <#list pageModel.records as m>
                    <tr>
                        <td width="10%">
                            <input id="${m.id}" type="radio" name="check_box">
                            <a class="td_inner_a" href="<@url value='/material/edit?id=${m.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="15%"><a href="<@url value='/material/get?id=${m.id}' />" data-hover-tip="查看详情">${m.materialNo!''}</a></td>
                        <td width="15%">${m.materialName!''}</td>
                        <td width="15%">${m.materialTypeName!''}</td>
                        <td width="15%">${m.specificationsModel!''}</td>
                        <td width="15%">${m.barcode!''}</td>
                        <td width="15%">${m.partnerName!''}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <#if pageModel.totalRecords??>
            <@pageByParam url="/material/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
    </div>
</@insert>