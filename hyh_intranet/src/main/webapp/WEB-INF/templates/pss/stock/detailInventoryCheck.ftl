<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="checkNumber">${i.checkNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据日期</span></div>
            <div class="common_text_val"><span id="billsDate" data-type="date" class="readonly_text_common grid_page_text">${i.billsDate}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">操作员</span></div>
            <div class="common_text_val"><span id="nickName" name="nickName" class="readonly_text_common grid_page_text">${i.operatorName}</span></div>
        </div>
          <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"></div><!-- 占位-->
            <div class="common_text_val"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">盘点仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="storage_name">${i.storageName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">盘点库区</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="storage_area_name">${i.storageAreaName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span id="summary" class="readonly_text_common grid_page_text">${i.summary}</span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 1850px;">
                <thead>
                    <tr>
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
                <tbody id="dataSource">
                    <#if i?? && (i.items)??>
                        <#list i.items as item>
                            <tr>
                                <td width="150px">${item.materialNo}</td>
                                <td width="150px">${item.barcode}</td>
                                <td width="150px">${item.materialName}</td>
                                <td width="150px">${item.materialTypeName}</td>
                                <td width="150px">${item.unitName}</td>
                                <td width="150px">${item.locationNo}</td>
                                <td width="150px">${item.batchNumber}</td>
                                <td width="150px">${item.productionDate}</td>
                                <td width="150px">${item.expirationDate}</td>
                                <td width="150px">${item.inventoryNumber}</td>
                                <td width="150px">${item.actualNumber}</td>
                                <td width="150px">${item.diffNumber}</td>
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
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${i.ownerName}</span></div><!--这里使用模拟数据-->
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>
<script>

</script>