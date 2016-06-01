<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page">
        <div class="partner_text_div common_text_div" style="width: 100%;">
            <div class="section_title_0 section_part_1">基础</div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">编码</div>
            <div class="common_text_val"><span class="text_val">${material.materialNo}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">国际编码</div>
            <div class="common_text_val"><span class="text_val">${material.barcode}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">名称</div>
            <div class="common_text_val"><span class="text_val">${material.materialName}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">外文名称</div>
            <div class="common_text_val"><span class="text_val">${material.foreignName}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">所属类别</div>
            <div class="common_text_val"><span class="text_val">${material.materialType}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">品牌</div>
            <div class="common_text_val"><span class="text_val">${material.brand}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">计量单位</div>
            <div class="common_text_val"><span class="text_val">${material.unitName}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">规格型号</div>
            <div class="common_text_val"><span class="text_val">${material.specificationsModel}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">季节性</div>
            <div class="common_text_val"><span class="text_val">${material.season}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">采购商品</div>
            <div class="common_text_val"><span class="text_val"><#if material.isPurchase==true>是<#else>否</#if></span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">销售商品</div>
            <div class="common_text_val"><span class="text_val"><#if material.isSell==true>是<#else>否</#if></span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">库存商品</div>
            <div class="common_text_val"><span class="text_val"><#if material.isInventory==true>是<#else>否</#if></span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">法定单位</div>
            <div class="common_text_val">
                <select id="legalUnit" class="my_radius">
                    <option value="">请选择</option>
                    <#list materialUnits as m>
                        <option value="${m.id}">${m.unitName}</option>>
                    </#list>
                </select>
            </div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">法定折算数量</div>
            <div class="common_text_val"><input id="legalTranslationQuantity" class="input_text_common my_radius"></div>
        </div>
         <div class="partner_text_div common_text_div">
            <div class="common_text_title">入区单位</div>
            <div class="common_text_val">
                <select id="entryUnit" class="my_radius">
                    <option value="">请选择</option>
                    <#list materialUnits as m>
                        <option value="${m.id}">${m.unitName}</option>>
                    </#list>
                </select>
            </div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">入区折算数量</div>
            <div class="common_text_val"><input id="entryTranslationQuantity" class="input_text_common my_radius"></div>
        </div>
        <div class="partner_text_div common_text_div" style="width: 100%;">
            <div class="section_title_0 section_part_1">常规</div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">批次管理</div>
            <div class="common_text_val"><span class="text_val"><#if material.isBatch==true>是<#else>否</#if></span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">保质期</div>
            <div class="common_text_val"><span class="text_val">${material.expirationDate}</span>天</div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">供应商</div>
            <div class="common_text_val"><span class="text_val">${material.partnerId}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">起订量</div>
            <div class="common_text_val"><span class="text_val">${material.minOrder}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">最大库存</div>
            <div class="common_text_val"><span class="text_val">${material.maxInventory}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">最小库存</div>
            <div class="common_text_val"><span class="text_val">${material.minInventory}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">是否可用</div>
            <div class="common_text_val"><span class="text_val"><#if material.isEnable==true>可用<#else>不可用</#if></span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">备注</div>
            <div class="common_text_val"><span class="text_val">${material.description}</span></div>
        </div>
    </div>
</@insert>