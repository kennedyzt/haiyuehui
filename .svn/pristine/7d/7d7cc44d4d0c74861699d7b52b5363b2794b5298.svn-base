<@insert template="login/right">
<#setting classic_compatible=true>
    <div class="add_page">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">编码</span></div>
            <div class="common_text_val"><input id="partnerCode" class="input_text_common my_radius" data-click-tip="数据唯一标识，不可重复"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">类型</span></div>
            <div class="common_text_val">
                <#if partnerType==1>
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
            <div class="common_text_val"><input id="partnerName" class="input_text_common my_radius"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">外文名称</div>
            <div class="common_text_val"><input id="foreignName" class="input_text_common my_radius"></div>
        </div>
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">组</div>
            <div class="common_text_val">
                <select id="partnerGroup" class="my_radius">
                    <#list partnerGroups as p>
                        <option value="${p.id}">${p.groupName}</option>
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
                <div class="common_text_val"><input id="phone" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">移动电话</div>
                <div class="common_text_val"><input id="mobilephone" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">传真</div>
                <div class="common_text_val"><input id="fax" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">电子邮件</div>
                <div class="common_text_val"><input id="email" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">网址</div>
                <div class="common_text_val"><input id="website" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">是否可用</div>
                <div id="isEnable" class="common_text_val">
                    <input value="true" type="radio" checked="checked" name="activity">可用
                    <input value="false" type="radio" name="activity">不可用
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">企业地址(中文)</div>
                <div class="common_text_val"><input id="address" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">企业地址（英文）</div>
                <div class="common_text_val"><input id="addressEn" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">国家/地区</div>
                <div class="common_text_val">
                    <select id="countryRegionId" class="my_radius">
                        <option value=""></option>
                        <#list countryRegions as country>
                            <option value="${country.id}">${country.countryRegionName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">企业类型</div>
                <div class="common_text_val">
                    <select id="businessType" class="my_radius">
                        <option></option>
                        <option value="1">出口商</option>
                        <option value="2">代理商</option>
                        <option value="3">出口商/代理商</option>
                    </select>
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">邮政编码</div>
                <div class="common_text_val"><input id="postalCode" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">备注</div>
                <div class="common_text_val"><input id="description" class="input_text_common my_radius"></div>
            </div>
        </div>
        <div id="partner_section_2">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">名</div>
                <div class="common_text_val"><input id="contactsLastName" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">姓</div>
                <div class="common_text_val"><input id="contactsFirstName" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">性别</div>
                <div class="common_text_val">
                    <div class="common_text_val">
                        <select id="contactsGender" class="my_radius">
                            <option value="2"></option>
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">电话</div>
                <div class="common_text_val"><input id="contactsPhone" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">移动电话</div>
                <div class="common_text_val"><input id="contactsMobilephone" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">传真</div>
                <div class="common_text_val"><input id="contactsFax" class="input_text_common my_radius"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">邮箱</div>
                <div class="common_text_val"><input id="contactsEmail" class="input_text_common my_radius" data-click-tip="请不要填写hotmail和gmail邮箱" placeholder="请不要填写hotmail和gmail邮箱"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">备注</div>
                <div class="common_text_val"><input id="contactsDescription" class="input_text_common my_radius"></div>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="supplier_smt_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>