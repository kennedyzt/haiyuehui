<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="checkNumber">${i.checkNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><span id="billsDate" data-type="date" class="readonly_text_common grid_page_text">${i.billsDate}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">操作员</span></div>
            <div class="common_text_val"><span id="nickName" name="nickName" class="readonly_text_common grid_page_text">${i.operatorName}</span><input id="userId" type = "hidden" value="${i.operatorId}"></div><#-- 为了公用选择联系人的方法，这里的userId赋值是给的operatorId -->
        </div>
          <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"></div><!-- 占位-->
            <div class="common_text_val"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">盘点仓库</span></div>
            <div class="common_text_val"><input type="hidden" id="storage_id" value="${i.storageId}"><span class="readonly_text_common grid_page_text" id="storage_name">${i.storageName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">盘点库区</span></div>
            <div class="common_text_val"><input type="hidden" id="storage_area_id" value="${i.storageAreaId}"><span class="readonly_text_common grid_page_text" id="storage_area_name">${i.storageAreaName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="summary" class="input_text_common my_radius grid_page_text" value="${i.summary}"></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 1890px;">
                <thead>
                    <tr>
                        <td width="40px"></td>
                        <td width="150px">商品货号</td><!-- 商品货号-->
                        <td width="150px">国际编码</td>
                        <td width="150px">商品名称</td>
                        <td width="150px">商品类型</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">库位编码</td>
                        <td width="150px">批次号</td>
                        <td width="150px">生产日期</td>
                        <td width="150px">到期日期</td>
                        <td width="150px">库存数量</td>
                        <td width="150px">盘点数量</td>
                        <td width="150px">差异数量</td>
                    </tr>
                </thead>
             </table>
             <table style="width: 1890px;">
                <tbody id="dataSource">
                   <#if i?? && (i.items)??>
                        <#list i.items as item>
                            <tr>
                                <td width="150px"><input name="materialId" type="hidden" value="${item.materialId}"><span name="materialNo" class="grid_text grid_readonly_text my_radius">${item.materialNo}</span></td>
                                <td width="150px"><span name="barcode" class="grid_text grid_readonly_text my_radius">${item.barcode}</span></td>
                                <td width="150px"><span name="materialName" class="grid_text grid_readonly_text my_radius">${item.materialName}</span></td>
                                <td width="150px"><span name="materialTypeName" class="grid_text grid_readonly_text my_radius">${item.materialTypeName}</span></td>
                                <td width="150px"><span name="unitName" class="grid_text grid_readonly_text my_radius">${item.unitName}</span></td>
                                <td width="150px"><span name="locationNo" class="grid_text grid_readonly_text my_radius">${item.locationNo}</span></td>
                                <td width="150px"><span name="batchNumber" class="grid_text grid_readonly_text my_radius">${item.batchNumber}</span></td>
                                <td width="150px"><span name="productionDate" class="grid_text grid_readonly_text my_radius">${item.productionDate}</span></td>
                                <td width="150px"><span name="expirationDate" class="grid_text grid_readonly_text my_radius">${item.expirationDate}</span></td>
                                <td width="150px"><span name="inventoryNumber" class="grid_text grid_readonly_text my_radius">${item.inventoryNumber}</span></td>
                                <td width="150px"><input name="actualNumber" data-type="number" class="input_text_common my_radius grid_text" value="${item.actualNumber}" onchange="calculatorDiffNumber(this)"></td>
                                <td width="150px"><span name="diffNumber" class="grid_text grid_readonly_text my_radius">${item.diffNumber}</span></td>
                            </tr>
                        </#list>    
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val">
                <select id="auditor" class="my_radius grid_page_text">
                    <option value=""></option>
                    <option value="1">李四</option>
                    <option value="2">李六</option>
                </select>
            </div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${i.ownerName}</span><input id="ownerId" type="hidden" value="${i.owner}"></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="inventory_check_edit_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>
<script>
    function calculatorDiffNumber(dom){
        var inventoryNumber = $(dom).parent().prev().find('span').text();
        var actualNumber = $(dom).val();
        if(actualNumber==""){
            actualNumber = 0.0;
        }
        $(dom).parent().next().find('span').text((parseFloat(actualNumber)-parseFloat(inventoryNumber)).toFixed(2));
    }
</script>