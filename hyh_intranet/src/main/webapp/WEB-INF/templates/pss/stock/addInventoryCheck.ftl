<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="checkNumber">${checkNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">盘点人</span></div>
            <div class="common_text_val"><input id="nickName" name="nickName" class="input_text_common my_radius grid_page_text" onclick="openWinGetUser();"><input id="userId" type = "hidden"></div><#-- 为了公用选择联系人的方法，这里的userId赋值是给的operatorId -->
        </div>
          <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"></div><!-- 占位-->
            <div class="common_text_val"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">盘点仓库</span></div>
            <div class="common_text_val"><input type="hidden" id="storage_id"><span class="readonly_text_common grid_page_text" id="storage_name"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">盘点库区</span></div>
            <div class="common_text_val"><input type="hidden" id="storage_area_id"><span class="readonly_text_common grid_page_text" id="storage_area_name"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="summary" class="input_text_common my_radius grid_page_text"></div>
        </div>
    </div>
    <div class="order_add_prowin">
        <!--<input class="input_text_common my_radius" data-type="inventoryMaterial">-->
        <button class="my_radius" onclick = "checkOpenWinGetMaterial()" type="button">添加</button>
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
                <tbody id="dataSource">
                  <#-- 表体信息每一行含有一个name为materialId隐藏input,各项检验是通过这个materialId来的-->
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
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${loginUser.nickname}</span><input id="ownerId" type="hidden" value="${loginUser.id}"></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="inventory_check_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>
<script>

</script>