<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <div class="head_top_">
    </div>
    <div class="table_title">列表</div>
    <table>
        <thead>
            <tr>
                <td width="10%"><a class="td_head_a" onclick="deleteCountryRegion()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="15%">国家/地区</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#list countryRegion as i>
                    <tr>
                        <td width="10%">
                            <input id="${i.id}" type="radio" name="check_box">
                        </td>
                        <td width="15%">${i.countryRegionName}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>
</@insert>
<script type="text/javascript">
    function deleteCountryRegion(){
        var ids = [];
        var checkbox = $("input[name='check_box']:checked");
        for (var i = 0; i < checkbox.length; i++) {
            ids = checkbox[i].id;
        }
        if(ids!=""){
            siping.confirm('确定删除该国家/地区？', function() {
                siping.ajax({
                    method : 'post',
                    url : '/countryregion/delete?id=' + ids,
                    async : true,
                    success : function(data) {
                        if (data.success) {
                            siping.alert(1, data.msg);
                        } else {
                            siping.alert(0, data.msg);
                        }
                    }
                });
            });
        }else{
            siping.alert(0, "请选择要删除的数据行！");
        }
    }
</script>