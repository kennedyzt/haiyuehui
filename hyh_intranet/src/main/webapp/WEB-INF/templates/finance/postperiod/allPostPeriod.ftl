<@insert template="login/right">
    <div class="list_page">
        <div class="table_title">过账期间列表</div>
        <table>
            <thead>
                <tr>
                    <td width="10%"><!--<a class="td_head_a" onclick="deletePostPeriod()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a>--></td>
                    <td width="18%">期间代码</td>
                    <td width="18%">期间名称</td>
                    <td width="18%">过账期间(从)</td>
                    <td width="18%">过账期间(止)</td>
                    <td width="18%">期间状态</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid">
            <table>
                <tbody>
                <#list pageModel.records as p>
                    <tr>
                        <td width="10%">
                            <!--<input id="${p.id}" type="checkbox" name="check_box">-->
                            <a class="td_inner_a" href="<@url value='/postperiod/edit?id=${p.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="18%">${p.postPeriodNo}</td>
                        <td width="18%">${p.postPeriodName}</td>
                        <td width="18%">${p.postPeriodStartTime}</td>
                        <td width="18%">${p.postPeriodEndTime}</td>
                        <td width="18%"><#if p.postPeriodFlag==true>激活<#else>锁定</#if></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</@insert>