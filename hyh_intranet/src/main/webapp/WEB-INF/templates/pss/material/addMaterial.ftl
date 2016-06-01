<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page">
        <#--<div class="partner_text_div_5 common_text_div" style="width: 100%;">
            <div class="section_title">基础</div>
        </div>-->
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span data-required="true">商品货号</span></div>
            <div class="common_text_val"><input id="materialNo"  class="input_text_common_small my_radius" data-click-tip="数据唯一标识，不可重复"></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span data-required="true">商品名称</span></div>
            <div class="common_text_val"><input id="materialName" class="input_text_common_small my_radius"></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span data-required="true">所属商品类型</span></div>
            <div class="common_text_val">
                <input type="hidden" id="father_id" value="0">
                <!--<input type="hidden" id="materialTypeFlag" value="0">
                <input type="hidden" id="materialTypeShowFlag" value="0">-->
                <input id="fatherType" data-type="materialType" name="materialType" value="全部" readonly class="input_text_common_small my_radius">
                <div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" hidden="hidden"></div>
            </div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">外文名称</div>
            <div class="common_text_val"><input id="foreignName" class="input_text_common_small my_radius"></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">批次管理</div>
            <div class="common_text_val">
                <input type="radio" name="batch" value="1" onclick="useBatchManage()" checked="checked">是
                <!--<input type="radio" name="batch" value="0" onclick="unUseBatchManage()">否-->
            </div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span data-required="true">保质期</div>
            <div class="common_text_val"><input id="expirationDate" class="input_text_common_small my_radius">天</div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span data-required="true">国际编码</span></div>
            <div class="common_text_val"><input id="barcode" class="input_text_common_small my_radius"></div>
        </div>
       <!-- <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">法定单位</div>
            <div class="common_text_val">
                <select id="legalUnit" class="input_text_common_small my_radius">
                    <option value="">请选择</option>
                    <#list materialUnits as m>
                        <option value="${m.id}">${m.unitName}</option>>
                    </#list>
                </select>
            </div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">法定折算数量</div>
            <div class="common_text_val"><input id="legalTranslationQuantity" class="input_text_common_small my_radius"></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">入区单位</div>
            <div class="common_text_val">
                <select id="entryUnit" class="input_text_common_small my_radius">
                    <option value="">请选择</option>
                    <#list materialUnits as m>
                        <option value="${m.id}">${m.unitName}</option>>
                    </#list>
                </select>
            </div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">入区折算数量</div>
            <div class="common_text_val"><input id="entryTranslationQuantity" class="input_text_common_small my_radius"></div>
        </div>-->
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">规格型号</div>
            <div class="common_text_val"><input id="specificationsModel" class="input_text_common_small my_radius"></div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span data-required="true">重量(克)</div>
            <div class="common_text_val"><input id="weight" data-type="number" class="input_text_common_small my_radius"></div>
        </div>
         <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title"><span data-required="true">计量单位</span></div>
            <div class="common_text_val common_text_val_table" style="position:relative;margin-left:10px;width:60%" id="materialUnitDiv">
            <input id="unitName" onchange="fuzzyQueryUnit()" class="input_choose_text input_choose_short_text my_radius grid_text" style="height:24px;width:80%">
            <div class="more" onclick="openWinGetUnit()" style="height:24px;width:15%"></div><!--这里的几个style是微调，没有对其新建css类-->
            <input id="unitId"  type="hidden" >
            <div id="materialUnitHiddenDiv"></div>
            </div>
            <!--<div class="common_text_val" style="position:relative" id="materialUnitDiv">
            <input id="unitName" class="input_text_common_small my_radius" onchange="fuzzyQueryUnit()">
            <input id="unitId"  type="hidden" ><div id="materialUnitHiddenDiv"></div>-->
                <!--<select id="unitId" class="input_text_common_small my_radius">
                <option value="">请选择</option>
                <#list materialUnits as m>
                    <option value="${m.id}">${m.unitName}</option>>
                </#list>
                </select>-->
           <!-- </div>-->
        </div>
        <div class="partner_text_div_3 common_text_div">
        </div>
        <!--<div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">采购商品</div>
            <div class="common_text_val">
                <input type="hidden" type="radio" name="purchaseMaterial" checked="checked" value="1" disabled="disabled">是
                <input type="hidden" type="radio" name="purchaseMaterial" value="0" disabled="disabled">否
            </div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">销售商品</div>
            <div class="common_text_val">
                <input type="hidden" type="radio" name="purchaseMaterial" checked="checked" value="1" disabled="disabled">是
                <input type="hidden" type="radio" name="purchaseMaterial" value="0" disabled="disabled">否
            </div>
        </div>
        <div class="partner_text_div_3 common_text_div">
            <div class="common_text_title">库存商品</div>
            <div class="common_text_val">
                <input type="hidden" type="radio" name="storeMaterial" value="1" checked="checked" disabled="disabled">是
                <input type="hidden" type="radio" name="storeMaterial" value="0" disabled="disabled">否
            </div>
        </div>
         <input type="hidden" type="radio" name="purchaseMaterial" checked="checked" value="1" disabled="disabled">
         <input type="hidden" type="radio" name="saleMaterial" checked="checked" value="1" disabled="disabled">
         <input type="hidden" type="radio" name="storeMaterial" value="1" checked="checked" disabled="disabled">-->
        <div class="partner_text_div common_text_div" style="width: 100%;margin:30px 0 30px 0;">
            <div id="material_title_1" class="section_title section_part_3" style="border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: rgb(96, 167, 255);">常规</div>
            <div id="material_title_2" class="section_title section_part_3" style="border-bottom-width: 0px;">仓库预警</div>
            <div id="material_title_3" class="section_title section_part_3" style="border-bottom-width: 0px;">其他</div>
        </div>
        <div id="material_section_1" style="display: block;">
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">品牌</div>
                <div class="common_text_val"><input id="brand" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">季节性</div>
                <div class="common_text_val"><input id="season" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">供应商</div>
                <div class="common_text_val">
                    <input id="partnerName" class="input_text_common_small my_radius" onclick="openWinGetPartner(2)"><span id="id" style="display:none;"></span>
                </div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">生产商</div>
                <div class="common_text_val">
                    <input id="manufacturer" class="input_text_common_small my_radius">
                </div>
            </div>
             <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">所属商家</div>
                <div class="common_text_val">
                    <input id="shopName" class="input_text_common_small my_radius" onclick="openWinGetShop()"><input id="shops" type="hidden">
                </div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">起订量</div>
                <div class="common_text_val"><input id="minOrder" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">原产地</div>
                <div class="common_text_val"><input id="provenance" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">备注</div>
                <div class="common_text_val"><input id="description" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">是否可用</div>
                <div class="common_text_val">
                    <input type="radio" checked="checked" name="activity" value="1">可用
                    <input type="radio" name="activity" value="0">不可用
                </div>
        </div>
        </div>
        <div id="material_section_2" style="display: none;">
        <#--<div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">最大库存</div>
                <div class="common_text_val"><input id="maxInventory" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">最小库存</div>
                <div class="common_text_val"><input id="minInventory" class="input_text_common_small my_radius"></div>
            </div> -->
            <div class="data_add_grid" style="height:250px">
                <table>
                    <thead>
                        <tr>
                            <td width="25%">仓库编码</td>
                            <td width="25%">仓库名称</td>
                            <td width="25%">最小库存</td>
                            <td width="25%">最大库存</td>
                        </tr>
                    </thead>
                    <tbody id="dataSource" style="height: 230px;overflow: auto;">
                        <#list storages as s>
                        <tr id="${s_index}" class="grid_row">
                            <td><span id="storageNo${s_index}" class="grid_text grid_readonly_text my_radius">${s.storageNo!''}</span><input id="storageId${s_index}" value="${s.id}" type="hidden"></td>
                            <td><span id="storageName${s_index}" class="grid_text grid_readonly_text my_radius">${s.storageName!''}</span></td>
                            <td><input id="minInventory${s_index}" value="" data-type="number" class="input_text_common my_radius grid_text"></td>
                            <td><input id="maxInventory${s_index}" value="" data-type="number" class="input_text_common my_radius grid_text"></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
        <div id="material_section_3" style="display: none;">
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">电商企业代码</div>
                <div class="common_text_val"><input id="ebec" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">电商企业名称</div>
                <div class="common_text_val"><input id="eben" class="input_text_common_small my_radius"></div>
            </div>
             <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">HS编码</div>
                <div class="common_text_val"><input id="hscode" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">行邮税税号</div>
                <div class="common_text_val"><input id="postTaxNumber" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">行邮税税率</div>
                <div class="common_text_val"><input id="postTaxRate" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">项号</div>
                <div class="common_text_val"><input id="itemNo" class="input_text_common_small my_radius"></div>
            </div>
             <!--<div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">自定义1</div>
                <div class="common_text_val"><input id="custom1" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">自定义2</div>
                <div class="common_text_val"><input id="custom2" class="input_text_common_small my_radius"></div>
            </div>
            <div class="partner_text_div_5 common_text_div">
                <div class="common_text_title">自定义3</div>
                <div class="common_text_val"><input id="custom3" class="input_text_common_small my_radius"></div>
            </div>-->            
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="material_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>