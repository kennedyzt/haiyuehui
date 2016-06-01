<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section">
            <form action="<@url value='/inventoryallocate/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据编号</div>
                <div class="common_text_val"><input id="keywords" name="keywords" class="input_text_common my_radius" data-click-tip="输入单据编号、商品名称,商品货号关键字" placeholder="单据编号，商品名称，商品货号"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(从)</div>
                <div class="common_text_val"><input id="start_date" name="startDate" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">单据日期(至)</div>
                <div class="common_text_val"><input id="end_date" name="endDate" class="input_text_common my_radius" data-type="date"></div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">搜索</button>
            </div>
            <div class="search_btn common_text_div">
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2000px;">
                <thead>
                    <tr>
                        <td>单据编号</td>
                        <td>单据日期</td>
                        <td>商品名称</td>
                        <td>商品货号</td>
                        <td>商品国际编码</td>
                        <td>规格型号</td>
                        <td>单位</td>
                        <td>批次号</td>
                        <td>生产日期</td>
                        <td>到期日期</td>
                        <td>源仓库</td>
                        <td>源库位</td>
                        <td>目标仓库</td>
                        <td>目标库位</td>
                        <td>数量</td>
                        <td>所属人</td>
                    </tr>
                </thead>
                <tbody>
                    <#if pageModel??>
                        <#if (pageModel.totalRecords > 0)>
                            <#list pageModel.records as record>
                            <tr>
                                <td>${ record.allocateNumber!'' }</td>
                                <td>${ record.billsDate!'' }</td>
                                <td>${ record.materialName!'' }</td>
                                <td>${ record.materialNo!'' }</td>
                                <td>${ record.barcode!'' }</td>
                                <td>${ record.specifictionsModel!'' }</td>
                                <td>${ record.unitName!'' }</td>
                                <td>${ record.batchNumber!''}</td>
                                <td>${ record.productionDate!'' }</td>
                                <td>${ record.expirationDate!'' }</td>
                                <td>${ record.fromStroageName!'' }</td>
                                <td>${ record.fromLocationName!'' }</td>
                                <td>${ record.toStroageName!'' }</td>
                                <td>${ record.toLocationName!'' }</td>
                                <td>${ (record.counts?number!'')?string('#.00') }</td>
                                <td>${ record.createName!'' }</td>
                            </tr>
                            </#list>
                        </#if>
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
    <#if pageModel?? && pageModel.totalRecords??>
        <@pageByParam url="/inventoryallocate/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>