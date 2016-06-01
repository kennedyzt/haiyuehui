<#setting classic_compatible=true>
<@insert template="login/right">
    <div>
        <div style = "text-align:center;">拣配清单（纸质打印单）</div>
    </div>
    <div>
        <div>
            <div class="head_section">
                <div class="grid_page_text_div common_text_div">
                    <div class="common_text_title"><span>波次号</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
                </div>
                <div class="grid_page_text_div common_text_div">
                    <div class="common_text_title"><span>操作日期</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
                </div>
                <div class="grid_page_text_div common_text_div">
                    <div class="common_text_title"><span>操作人</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
                </div>
                <div class="grid_page_text_div common_text_div">
                </div>
                <div class="grid_page_text_div common_text_div">
                    <div class="common_text_title"><span>仓库</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
                </div>
                <div class="grid_page_text_div common_text_div">
                    <div class="common_text_title"><span>SKU总数()</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
                </div>
                <div class="grid_page_text_div common_text_div">
                    <div class="common_text_title"><span>商品总数()</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
                </div>
            </div>
            <table>
                <tbody>
                    <tr>
                        <td width="3%">序号</td>
                        <td width="12%">库位号</td>
                        <td width="85%">
                            <table>
                                <tr>
                                    <td width="6%">商品序号</td>
                                    <td width="14%">商品条码</td>
                                    <td width="14%">商品名</td>
                                    <td width="10%">数量</td>
                                    <td width="14%">单位</td>
                                    <td width="14%">原始订单号</td>
                                    <td width="14%">发货订单号</td>
                                    <td width="14%">拣货状态</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                        <#list orderPicks.storageLocationList as s>
                            <tr>
                                <td width="3%">${s_index+1}</td>
                                <td width="12%">${s.storageLocationName}</td>
                                <td width="85%">
                                    <table>
                                        <#list s.items as i>
                                            <tr>
                                                <td width="6%">${i_index+1}</td>
                                                <td width="14%">${i.materialNo}</td>
                                                <td width="14%">${i.materialName}</td>
                                                <td width="10%">${i.counts}</td>
                                                <td width="14%">${i.unitName}</td>
                                                <td width="14%">${i.orderNumber}</td>
                                                <td width="14%">发货订单号</td>
                                                <td width="14%">拣货状态</td>
                                            </tr>
                                        </#list>
                                    </table>
                                </td>
                            </tr>
                        </#list>
                </tbody>
            </table>
            <table>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td>操作人签字</td><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td><td></td><td></td><td></td><td>完成时间</td><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td><td></td><td>接单时间</td><td></td><td></td><td></td><td></td><td></td></tr>
                <tr><td></td><td></td><td></td><td></td><td>总体用时</td><td></td><td></td><td></td><td></td><td></td></tr>
            </table>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button class="btn_common my_radius">返回</button>
    </div>
</@insert>