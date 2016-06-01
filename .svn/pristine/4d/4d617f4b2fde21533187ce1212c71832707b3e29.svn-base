<#setting classic_compatible=true>
<@insert template="layout/layer">
    <div class="list_page">
        <div class="table_title">银行账户</div>
        <table>
            <thead>
                <tr>
                    <td width="10%"><a class="td_head_a" onclick="deleteBank(${partnerId})" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                    <td width="16%">银行名称</td>
                    <td width="16%">银行国际代码</td>
                    <td width="16%">开户行</td>
                    <td width="10%">户名</td>
                    <td width="16%">银行账号</td>
                    <td width="16%">备注</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid">
            <table>
                <tbody>
                    <#list bankAccounts as b>
                        <tr>
                            <td width="10%">
                                <input id="${b.id}" type="checkbox" name="check_box">
                                <a class="td_inner_a" href="<@url value='/bankaccount/edit?id=${b.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                            </td>
                            <td width="16%">${b.bankName}</td>
                            <td width="16%">${b.swiftCode}</td>
                            <td width="16%">${b.bankAddress}</td>
                            <td width="10%">${b.accountName}</td>
                            <td width="16%">${b.accountNo}</td>
                            <td width="16%">${b.remark}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</@insert>