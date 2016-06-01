<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">商品分类</div>
            <div class="common_text_val"><input id="materialType" data-type="materialType" name="materialType" class="input_choose_text my_radius" placeholder="选择商品分类"><div class="more"></div></div>
        </div>
        <div style="clear:both"></div>
    </div>
    <div class="data_grid">
        <table style="table-layout: fixed;">
            <thead>
                <tr>
                    <td>商品名称</td>
                    <td>商品类型描述</td>
                    <td>所属分类</td>
                    <td>层级</td>
                </tr>
            </thead>
            <tbody>
                <#if materialTypeList??>
                    <#list materialTypeList as m>
                    <tr>
                        <td><a class="td_inner_a" href="#" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a></td>
                        <td>${ m.typeName!'' }</td>
                        <td>${ m.description!'' }</td>
                        <td>${ m.parentTypeName!'' }</td>
                        <td>${ m.level!'' }</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
        </table>
        <#if pageModel?? && (pageModel.records)??>
            <@pageByParam url="/inventoryaudit/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
    </div>
<!-- 可删除 -->
</@insert>