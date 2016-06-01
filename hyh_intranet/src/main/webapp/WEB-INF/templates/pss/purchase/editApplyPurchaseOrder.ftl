<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="purchaseOrderNumber">${ purchaseApplyOrder.orderNumber!''}</span></div>
        </div>
        <input type="hidden" id="applypo_flowId" value="${ purchaseApplyOrder.purchaseFlowId }">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${ purchaseApplyOrder.billDate!'' }"></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">有效期至</span></div>
            <div class="common_text_val"><input id="applypo_expireDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${ purchaseApplyOrder.orderExpireDate!'' }"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">必需日期</span></div>
            <div class="common_text_val"><input id="applypo_lastDate" onclick = "laydate({choose: function(date){changeApplyItemLastDate(date);}})" class="input_text_common my_radius grid_page_text" value="${ purchaseApplyOrder.lastDate!'' }"></div>
        </div>-->
        
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">申请者</span></div>
            <div class="common_text_val"><input id="userName" name="userName" class="input_text_common my_radius grid_page_text" onclick="openWinGetUser();" value="${ purchaseApplyOrder.applierName!'' }"><input id="userId" type = "hidden" value="${ purchaseApplyOrder.applierId }"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="applypo_summary" class="input_text_common my_radius grid_page_text" value="${ purchaseApplyOrder.summary }"></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 1520px;">
                <thead>
                    <tr>
                        <td width="20px"></td>
                        <td width="40px"></td>
                        <td width="150px">商品货号</td>
                        <td width="150px">商品名称</td>
                        <td width="150px">规格及型号</td>
                        <td width="150px">品牌</td>
                        <td width="150px">季节</td>
                        <td width="150px">国际编码</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">数量</td>
                        <#--<td width="150px">必需日期</td>-->
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="dataSource">
                    <#list purchaseApplyOrder.items as item>
                        <tr id= "${item_index+1}">
                            <td width="20px">${item_index+1}</td>
                            <td><img onclick="addApplyPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                            <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type="chooseApplyPurchaseOrderMaterial" name="materialNo" value = "${ item.materialNo }" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" value = "${item.materialId }" name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseApplyPurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                            <td><span name="materialName" class="grid_text grid_readonly_text my_radius">${ item.materialName }</span></td>
                            <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius">${ item.specificationsModel }</span></td>
                            <td><span name="brand" class="grid_text grid_readonly_text my_radius">${ item.brand }</span></td>
                            <td><span name="season" class="grid_text grid_readonly_text my_radius">${ item.season }</span></td>
                            <td><span name="barcode" class="grid_text grid_readonly_text my_radius">${ item.barcode }</span></td>
                            <td><span name="unitName" class="grid_text grid_readonly_text my_radius">${ item.unitName }</span><input type = "hidden" value = "${item.minOrder}" name = "minOrder"></input></td>
                            <td><input name="counts" data-type="number" class="input_text_common my_radius grid_text" value="${ item.counts!'' }"></td>
                            <#--<td><input name="lastDate" data-type="date" class="input_text_common my_radius grid_text" value="${ item.lastDate!'' }"></td>-->
                            <td><input name="remark" value = "${ item.remark }" class="input_text_common my_radius grid_text"></td>
                        </tr>
                    </#list>
                    <#list 0..(9-purchaseApplyOrder.items?size) as i>
                        <tr id= "${i+1+purchaseApplyOrder.items?size}">
                            <td width="20px">${i+1+purchaseApplyOrder.items?size}</td>
                            <td><img onclick="addApplyPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                            <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type="chooseApplyPurchaseOrderMaterial" name="materialNo"  class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden"  name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseApplyPurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                            <td><span name="materialName" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="brand" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="season" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="barcode" class="grid_text grid_readonly_text my_radius"></span></td>
                            <input type="hidden" name="unitId"><td><span name="unitName" class="grid_text grid_readonly_text my_radius"></span><input type = "hidden" name = "minOrder"></input></td>
                            <td><input name="counts" data-type="number" class="input_text_common my_radius grid_text"></td>
                            <#--<td><input name="lastDate" data-type="date" class="input_text_common my_radius grid_text"></td>-->
                            <td><input name="remark" class="input_text_common my_radius grid_text"></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val">
                <select id="applypo_auditor" class="my_radius grid_page_text" value="{purchaseApplyOrder.auditorName}">
                    <option value=""></option>
                    <option value="1">李四</option>
                    <option value="2">李六</option>
                </select>
            </div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <input type="hidden" id="applypo_ownerId" value=" ">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="applypo_owner">${purchaseApplyOrder.ownerName!''} </span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="apply_po_edit" class="btn_common my_radius btn_submit">保存</button>
        <#-- <button id="apply_po_draft_edit" class="btn_common my_radius">存为草稿</button> -->
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>