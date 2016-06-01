<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">编码</span></div>
            <div class="common_text_val"><span class="readonly_text_common">${businessPartner.partnerCode}</span></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">类型</span></div>
            <div class="common_text_val">
                     <#if businessPartner.partnerType==1>
                        <input id="partnerType" type="hidden" value="1">
                        <span class="readonly_text_common">客户</span>
                    <#else>
                        <input id="partnerType" type="hidden" value="2">
                        <span class="readonly_text_common">供应商</span>
                    </#if>
            </div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">名称</span></div>
            <div class="common_text_val"><input id="partnerName" value="${businessPartner.partnerName}" class="input_text_common my_radius"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">外文名称</div>
            <div class="common_text_val"><input id="foreignName" value="${businessPartner.foreignName}" class="input_text_common my_radius"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">组</div>
            <div class="common_text_val">
                <select id="partnerGroup" class="my_radius">
                        <#list partnerGroups as p>
                        	<#if businessPartner.partnerType == p.partnerType>
                            	<option <#if businessPartner.partnerGroup==p.id>selected</#if> value="${p.id}">${p.groupName}</option>
                            </#if>
                        </#list>
                </select>
            </div>
        </div>
        <div class="partner_text_div common_text_div" style="width: 100%;margin:30px 0 30px 0;">
            <div id="partner_title_1" class="section_title section_part_2">常规</div>
            <div id="partner_title_2" class="section_title section_part_2">联系人</div>
        </div>
        <div id="partner_section_1">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">电话</div>
                <div class="common_text_val"><input id="phone" value="${businessPartner.phone}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">移动电话</div>
                <div class="common_text_val"><input id="mobilephone" value="${businessPartner.mobilephone}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">传真</div>
                <div class="common_text_val"><input id="fax" value="${businessPartner.fax}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">电子邮件</div>
                <div class="common_text_val"><input id="email" value="${businessPartner.email}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">网址</div>
                <div class="common_text_val"><input id="website" value="${businessPartner.website}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">是否可用</div>
                <div id="isEnable" class="common_text_val">
                    <input value="true" <#if businessPartner.isEnable==true>checked="checked"</#if> type="radio" name="activity">可用
                    <input value="false" <#if businessPartner.isEnable==false>checked="checked"</#if> type="radio" name="activity">不可用
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">地址</div>
                <div class="common_text_val"><input id="address" value="${businessPartner.address}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">备注</div>
                <div class="common_text_val"><input id="description" value="${businessPartner.description}" class="input_text_common my_radius"></div>
            </div>
        </div>
        <div id="partner_section_2">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">名</div>
                <div class="common_text_val"><input id="contactsLastName" value="${businessPartner.contactsLastName}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">姓</div>
                <div class="common_text_val"><input id="contactsFirstName" value="${businessPartner.contactsFirstName}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">性别</div>
                <div class="common_text_val">
                    <div class="common_text_val">
                        <select id="contactsGender" class="my_radius">
                            <option <#if businessPartner.contactsGender==2>selected</#if> value="2"></option>
                            <option <#if businessPartner.contactsGender==0>selected</#if> value="0">男</option>
                            <option <#if businessPartner.contactsGender==1>selected</#if> value="1">女</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">电话</div>
                <div class="common_text_val"><input id="contactsPhone" value="${businessPartner.contactsPhone}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">移动电话</div>
                <div class="common_text_val"><input id="contactsMobilephone" value="${businessPartner.contactsMobilephone}" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">备注</div>
                <div class="common_text_val"><input id="contactsDescription" value="${businessPartner.contactsDescription}" class="input_text_common my_radius"></div>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <input type="hidden" id="partner_smt_edit_id" value="${businessPartner.id}">
        <button id="partner_smt_edit" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>