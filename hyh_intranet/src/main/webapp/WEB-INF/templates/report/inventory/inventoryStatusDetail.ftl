<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="search_section" style="overflow: visible;">
        <div class="grid_page_text_div common_text_div">
            <input type="hidden" id="material_id">
            <div class="common_text_title">商品货号</div>
            <div class="common_text_val"><input id="material_name" class="input_text_common my_radius grid_page_text"  disabled="disabled" value="${original.materialNo}"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <input type="hidden" id="material_id">
            <div class="common_text_title">商品名称</div>
            <div class="common_text_val"><input id="material_name" class="input_text_common my_radius grid_page_text"  disabled="disabled" value="${original.materialName}"></div>
        </div>
        <div style="clear:both"></div>
    </div>
    <div class="data_grid">
        <table style="table-layout: fixed;">
            <thead>
                <tr>
                    <td>单据</td>
                    <td>业务伙伴</td>
                    <td>订单日期</td>
                    <td>交货日期</td>
                    <td>仓库编码</td>
                    <td>仓库名称</td>
                    <td>计量单位</td>
                    <td>已承诺</td>
                    <td>已订购</td>
                </tr>
            </thead>
            <tbody>
                <#if (statusList??)>
                    <#list statusList as s>
                    <tr>
                        <td>${ s.billsNo }</td>
                        <td>${ s.businessPartnerName }</td>
                        <td>${ s.billsDate }</td>
                        <td>${ s.receiptDate}</td>
                        <td>${ s.storageNo } </td>
                        <td>${ s.stroageName }</td>
                        <td>${ s.unitName }</td>
                        <td>${ s.promisedAmount}</td>
                        <td>${ s.orderedAmount}</td>
                    </tr>
                    </#list>
                </#if>
            </tbody>
             <tfoot>
                <tr>
                    <td><strong>合计</strong></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><strong>${addPromisedAmount}</strong></td>
                    <td><strong>${addOrderedAmount}</strong></td>
                </tr>
             <tfoot> 
        </table>
    </div>
</@insert>