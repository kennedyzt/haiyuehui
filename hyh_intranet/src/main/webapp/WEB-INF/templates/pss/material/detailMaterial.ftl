<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page">
        <#--<div class="partner_text_div common_text_div" style="width: 100%;">
            <div class="section_title_0 section_part_1">基础</div>
        </div> -->
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">商品货号</div>
            <div class="common_text_val"><span class="text_val">${material.materialNo}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">商品名称</div>
            <div class="common_text_val"><span class="text_val">${material.materialName}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">所属分类</div>
            <div class="common_text_val"><span class="text_val">${material.materialTypeName}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">外文名称</div>
            <div class="common_text_val"><span class="text_val">${material.foreignName}</span></div>
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
            <div class="common_text_title">国际编码</div>
            <div class="common_text_val"><span class="text_val">${material.barcode}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">计量单位</div>
            <div class="common_text_val"><span class="text_val">${material.unitName}</span></div>
        </div>
        <!--<div class="partner_text_div common_text_div">
            <div class="common_text_title">法定单位</div>
            <div class="common_text_val">
                <span id="legalUnit" class="text_val">${material.legalName}</span>
            </div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">法定折算数量</div>
            <div class="common_text_val">
                <span id="legalTranslationQuantity" class="text_val">${material.legalTranslationQuantity}</span>
            </div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">入区单位</div>
            <div class="common_text_val">
                <span id="entryUnit" class="text_val">${material.entryName}</span>
            </div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">入区折算数量</div>
            <div class="common_text_val">
                <span id="entryTranslationQuantity" class="text_val">${material.entryTranslationQuantity}</span>
            </div>
        </div>-->
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">规格型号</div>
            <div class="common_text_val"><span class="text_val">${material.specificationsModel}</span></div>
        </div>
            <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">重量(克)</div>
            <div class="common_text_val"><span class="text_val">${material.weight}</span></div>
        </div>
        <!--<div class="partner_text_div common_text_div">
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
        </div>-->
        <#--<div class="partner_text_div common_text_div" style="width: 100%;">
            <div class="section_title_0 section_part_1">常规</div>
        </div>-->
        <div class="partner_text_div common_text_div" style="width: 100%;margin:30px 0 30px 0;">
            <div id="material_title_1" class="section_title section_part_3" style="border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: rgb(96, 167, 255);">常规</div>
            <div id="material_title_2" class="section_title section_part_3" style="border-bottom-width: 0px;">仓库预警</div>
            <div id="material_title_3" class="section_title section_part_3" style="border-bottom-width: 0px;">其他</div>
        </div>
        <div id="material_section_1" style="display: block;">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">品牌</div>
                <div class="common_text_val"><span class="text_val">${material.brand}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">季节性</div>
                <div class="common_text_val"><span class="text_val">${material.season}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">供应商</div>
                <div class="common_text_val"><span class="text_val">${material.partnerName}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">生产商</div>
                <div class="common_text_val"><span class="text_val">${material.manufacturer}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">所属商家</div>
                <div class="common_text_val"><span class="text_val">${material.shopName}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">起订量</div>
                <div class="common_text_val"><span class="text_val">${material.minOrder}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">原产地</div>
                <div class="common_text_val"><span class="text_val">${material.provenance}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">备注</div>
                <div class="common_text_val"><span class="text_val">${material.description}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">是否可用</div>
                <div class="common_text_val"><span class="text_val"><#if material.isEnable==true>可用<#else>不可用</#if></span></div>
            </div>
        </div>
        <div id="material_section_2" style="display: none;">
            <div class="data_add_grid" style="height:auto">
                <table>
                    <thead>
                        <tr>
                            <td width="25%">仓库编码</td>
                            <td width="25%">仓库名称</td>
                            <td width="25%">最小库存</td>
                            <td width="25%">最大库存</td>
                        </tr>
                    </thead>
                    <tbody id="dataSource">
                        <#list material.inventoryWarnings as s>
                        <tr id="${s_index}" class="grid_row">
                            <td><span id="storageNo${s_index}" class="text_val">${s.storageNo!''}</span><input id="storageId${s_index}" value="${s.storageId}" type="hidden"></td>
                            <td><span id="storageName${s_index}" class="text_val">${s.storageName!''}</span></td>
                            <td><span id="minInventory${s_index}"  class="text_val">${s.minInventory!''}</span></td>
                            <td><span id="maxInventory${s_index}"  class="text_val">${s.maxInventory!''}</span></td> 
                        </tr>
                        </#list>
                    </tbody>
                </table>
             </div>
         </div>
        <#-- <div class="partner_text_div common_text_div" style="width: 100%;">
            <div class="section_title_0 section_part_1">其他</div>
        </div>-->
        <div id="material_section_3" style="display: none;">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">电商企业代码</div>
                <div class="common_text_val"><span class="text_val">${material.ebec}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">电商企业名称</div>
                <div class="common_text_val"><span class="text_val">${material.eben}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">HS编码</div>
                <div class="common_text_val"><span class="text_val">${material.hscode}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">行邮税税号</div>
                <div class="common_text_val"><span class="text_val">${material.postTaxNumber}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">行邮税税率</div>
                <div class="common_text_val"><span class="text_val">${material.postTaxRate}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">项号</div>
                <div class="common_text_val"><span class="text_val">${material.itemNo}</span></div>
            </div>
            <!--<div class="partner_text_div common_text_div">
                <div class="common_text_title">自定义1</div>
                <div class="common_text_val"><span class="text_val">${material.custom1}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">自定义2</div>
                <div class="common_text_val"><span class="text_val">${material.custom2}</span></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">自定义3</div>
                <div class="common_text_val"><span class="text_val">${material.custom3}</span></div>
            </div>-->
        </div>
    </div>
</@insert>