<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="list_page">
        <div class="search_section">
        <form action="<@url value='/material/supplier/getlist'/>" method="post">
            <div class="partner_text_div common_text_div" style="width: 50%;">
                <div class="common_text_title">Keywords</div>
                <div class="common_text_val"><input style="width: 70%;" name="key" class="input_text_common my_radius" data-click-tip="enter product number,product name,or product barcode" placeholder="product number,product name,or product barcode" <#if searchCondition.key!=null> value="${searchCondition.key!}" </#if>></div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">Search</button>
            </div>
        <div style="clear:both"></div>
        </form>
        <div style="clear:both"></div>
        </div>
        <div class="table_title">Product List</div>
        <table>
            <thead>
                <tr>
                    <td width="1%"></td>
                    <td width="10%">Product Number</td>
                    <td width="17%">Product Name</td>
                    <td width="12%">Category</td>
                    <td width="11%">Unit of Measurement</td>
                    <td width="15%">Packing Specifications</td>
                    <td width="10%">Barcode</td>
                    <td width="15%">Provider</td>
                    <td width="8%">Quantity in Stock</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid">
            <table>
                <tbody>
                    <#list pageModel.records as m>
                    <tr>
                        <td width="1%"></td>
                        <td width="10%">${m.materialNo!''}</td>
                        <td width="17%">${m.foreignName!''}</td>
                        <td width="12%">${m.materialTypeName!''}</td>
                        <td width="11%">${m.unitName!''}</td>
                        <td width="15%">${m.specificationsModel!''}</td>
                        <td width="10%">${m.barcode!''}</td>
                        <td width="15%">${m.partnerName!''}</td>
                        <td width="8%"><font style="color:blue;">${m.stockQuantity}</font></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <#if pageModel.totalRecords??>
            <@pageByParam url="/material/supplier/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
    </div>
</@insert>