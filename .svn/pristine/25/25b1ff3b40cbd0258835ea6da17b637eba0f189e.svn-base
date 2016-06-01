<@insert template="layout/layer">
    <div class="list_page">
        <div class="table_title">复核单列表</div>
        <table>
            <thead>
                <tr>
                    <td style="width:10%"></td>
                    <td style="width:15%">发货订单</td>
                    <td style="width:15%">收货人</td>
                    <td style="width:15%">联系电话</td>
                    <td style="width:15%">收货地址</td>
                    <td style="width:15%">通过数</td>
                    <td style="width:15%">待核数</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid" style="height:70%;overflow-y:scroll">
            <table>
                <tbody>
                    <#list orders as s>
                        <tr>
                            <td style="width:10%"><input id="${s.orderNumber}" type="radio" name="check_radio" value="${s.orderNumber!''}"></td>
                            <td style="width:15%">${s.orderNumber!''}</td>
                            <td style="width:15%">${s.consigneeName!''}</td>
                            <td style="width:15%">${s.consignessPhone!''}</td>
                            <td style="width:15%">${s.consigneeAddress!''}</td>
                            <td style="width:15%">${s.auditNumber!''}</td>
                            <td style="width:15%">${s.noAuditNumber!''}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="layer_sm_btn" style="height:10%;margin-top:2%;">
            <button class="btn_common my_radius btn_submit" onclick="confirmAuditCheckOrder()">确定</button>
        </div>
    </div>
</@insert>
<script>
function confirmAuditCheckOrder(){
    parent.$("#hiddenGetOrderDataByTempAudit").val($("input[name='check_radio']:checked").val())
    parent.document.getElementById('hiddenGetOrderDataByTempAudit').onclick();
    var partnerIndex = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.siping.close(partnerIndex);
}
</script>