<@insert template="layout/layer">
    <div class="list_page">
        <div class="search_section">
            <form action="/hyh_intranet/storagelocation/getlist" method="post">
                <div class="partner_text_div common_text_div">
                    <div class="common_text_title">库区编码</div>
                    <div class="common_text_val"><input type="hidden" value="${storageId}" name="id" id="id"><input id="storageAreaNo" name="storageAreaNo" class="input_text_common my_radius" data-click-tip="库区编码" placeholder="库区编码"></div>
                </div>
                <div class="partner_text_div common_text_div">
                    <div class="common_text_title">库位编码</div>
                    <div class="common_text_val"><input id="storageLocationNo" name="storageLocationNo" class="input_text_common my_radius" data-click-tip="库位编码" placeholder="库位编码"></div>
                </div>
                <div class="search_btn common_text_div">
                    <button type="submit" class="btn_common my_radius btn_search">搜索</button>
                </div>
            </form>
            <div style="clear:both"></div>
        </div>
        <table>
            <thead>
                <tr>
                    <td width="10%"><input id="select_all_checkbox" type="checkbox" name="check_box_all"></td>
                    <td width="15%">库位编号</td>
                    <td width="15%">库位名称</td>
                    <td width="15%">所属库区编号</td>
                    <td width="15%">所属库区名称</td>
                    <td width="15%">拣配编号</td>
                    <td width="15%">描述</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid" style="height:300px;">
            <table>
                <tbody id = "dataSource">
                    <#list storageLocation.records as s>
                        <tr>
                            <td width="13%">
                                <input id="${s.id}" type="checkbox" name="check_box">
                                <a class="td_inner_a" style="margin:0px 20px 0px 0px;" href="<@url value='/storagelocation/edit?id=${s.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                                <a class="td_inner_a" style="margin:0px 20px 0px 0px;" onclick="deleteStorageLocation(${s.id})" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a>
                            </td>
                            <td name = "locationNo" width="12%">${s.locationNo!''}</td>
                            <td name = "locationName" width="15%">${s.locationName!''}</td>
                            <td width="15%">${s.storageAreaNo!''}</td>
                            <td width="15%">${s.storageAreaName!''}</td>
                            <td name = "pickOrder" width="15%">${s.pickOrder!''}</td>
                            <td width="15%">${s.description!''}<input type = "hidden" name = "storageName" value = "${s.storageName}"><input type = "hidden" name = "storageNo" value = "${s.storageNo}"></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
         <#if storageLocation.totalRecords??>
            <@pageByParam url="/storagelocation/getlist" pageNo="${storageLocation.pageNo}"  pageSize="${storageLocation.pageSize}" totalRecords="${storageLocation.totalRecords}"/>
        </#if>
        <div class="table_title">
            <input type="button" value = "打印二维码" onclick = "printQRCode();">
        </div>
    </div>
</@insert>
<script>
$("#select_all_checkbox").click(function() {
    if($("#select_all_checkbox").prop("checked")){
        $(":checkbox").prop("checked",true);
    } else {
        $(":checkbox").prop("checked",false);
    }
});

function printQRCode() {
     LODOP = getLodop();
     LODOP.SET_PRINT_STYLE("FontSize", 28); //
     LODOP.SET_PRINT_STYLE("VOrient",2);
     LODOP.SET_PRINT_PAGESIZE(0,"72mm", "57mm", "A4");
     $("#dataSource tr").each(function(){
        if ($(this).find("input[name = 'check_box']").prop("checked")) {
            LODOP.NEWPAGE();
            LODOP.ADD_PRINT_BARCODE(0,0,"25mm","25mm","QRCode",$(this).find("input[name = 'check_box']").attr("id"));
            //LODOP.ADD_PRINT_TEXT(53,90,220,55,"99A-9901");
            LODOP.ADD_PRINT_TEXT("15mm","26mm","46mm","15mm",$(this).find("td[name = 'locationNo']").text());
            //LODOP.SET_PRINT_STYLEA(0,"FontSize",36);
            LODOP.ADD_PRINT_SHAPE(2,0,0,"72mm","40mm",0,1,"#FFFFFF");
            //LODOP.ADD_PRINT_RECT(0,0,"77mm","40mm",0,1);
        }
     });
     LODOP.PREVIEW();
     //LODOP.PRINT_DESIGN();
}
</script>