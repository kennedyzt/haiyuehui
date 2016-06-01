<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="list_page" style="width:90%; height:90%;">
    <div class="search_section">
        <form action="<@url value='openwin' />" method="post" >
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入商家编码、名称关键字" placeholder="商家编码、名称"></div>
            </div>
            <div class="search_btn common_text_div">
                <button id="" class="btn_common my_radius btn_submit">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <table style="table-layout: fixed;">
        <thead>
            <tr>
                <td width="4%"></td>
                <td width="19%">编码</td>
                <td width="19%">名称</td>
                <td width="19%">联系人</td>
                <td width="19%">电话</td>
                <td width="19%">地址</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid" style="height:75%">
        <table>
            <tbody>
                    <!--<tr>
                        <td width="4%"><input id="0" type="radio" name="check_box"><span id="shops0" style="display: none;">0</span></td>
                        <td width="19%" id="shopNo0">自营</td>
                        <td width="19%" id="shopName0">自营</td>
                        <td width="19%" id="contact0"></td>
                        <td width="19%" id="telephone0"></td>
                        <td width="19%" id="address0"></td>
                    </tr>-->
                <#list pageModel.records as s>
                    <tr>
                        <td width="4%"><input id="${s_index+1}" type="radio" name="check_box"><span id="shops${s_index+1}" style="display: none;">${s.id}</span></td>
                        <td width="19%" id="shopNo${s_index+1}">${s.shopNo!''}</td>
                        <td width="19%" id="shopName${s_index+1}">${s.shopName!''}</td>
                        <td width="19%" id="contact${s_index+1}">${s.contact!''}</td>
                        <td width="19%" id="telephone${s_index+1}">${s.telephone!''}</td>
                        <td width="19%" id="address${s_index+1}">${s.address!''}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
            <@pageByParam url="/shop/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</div>
    <div class="order_btns_l common_text_div">
        <button onclick="chooseShop();" class="btn_common my_radius">确定</button>
    </div>
</@insert>