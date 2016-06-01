<div class="menu_level_1" id="administrator_menus" style="display: none;">
    <div class="menu_click s_menu_name menu_1_style">
        <div class="s_menu_l">
            <img class="s_menu_icon" src="<@static value='/icons/sys.png'/>"><span>系统管理</span>
        </div>
        <div class="s_menu_r">
            <img class="s_tree_hide" src="<@static value='/icons/tree_show.png'/>">
        </div>
    </div>
    <div class="menu_level_2 menu_level" style="display: block;">
        <div class="menu_click s_menu_name menu_2_style">
            <div class="s_menu_l">
                <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>系统设置</span>
            </div>
            <div class="s_menu_r">
                <img class="s_tree_hide" src="<@static value='/icons/tree_hide.png'/>">
            </div>
        </div>
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/materialunit/getlist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"><span>计量单位</span>
                    </div> </a>
                <div class="s_menu3_r" style="display: none;">
                    <a data-type="url" data-url="<@url value='/materialunit/add'/>">新增</a>
                </div>
            </div>
        </div>
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/materialtype/getalllist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>商品分类</span>
                    </div></a>
                <div class="s_menu3_r" style="display: none;">
                    <a data-type="url" data-url="<@url value='/materialtype/add'/>">新增</a>
                </div>
            </div>
        </div>
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/partnergroup/getlist?partnerType=2'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>供应商组</span>
                    </div></a>
                <div class="s_menu3_r" style="display: none;">
                    <a data-type="url" data-url="<@url value='/partnergroup/add?partnerType=2'/>">新增</a>
                </div>
            </div>
        </div>
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/partnergroup/getlist?partnerType=1'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"><span>客户组</span>
                    </div> </a>
                <div class="s_menu3_r" style="display: none;">
                    <a data-type="url" data-url="<@url value='/partnergroup/add?partnerType=1'/>">新增</a>
                </div>
            </div>
        </div>
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/countryregion/getlist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"><span>国家/地区</span>
                    </div> </a>
                <div class="s_menu3_r" style="display: none;">
                    <a data-type="url" data-url="<@url value='/countryregion/add'/>">新增</a>
                </div>
            </div>
        </div>
    </div>
    <div class="menu_level_2 menu_level" style="display: block;">
        <div class="menu_click s_menu_name menu_2_style">
            <div class="s_menu_l">
                <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"><span>系统初始化</span>
            </div>
            <div class="s_menu_r">
                <img class="s_tree_hide" src="<@static value='/icons/tree_hide.png'/>">
            </div>
        </div>
        <#-- <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/postperiod/getlist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>过帐期间</span>
                    </div></a>
                <div class="s_menu3_r">
                    <a data-type="url" data-url="<@url value='/postperiod/add'/>">新增</a>
                </div>
            </div>
        </div> -->
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/menunode/getlist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>系统菜单</span>
                    </div></a>
                <div class="s_menu3_r">
                    <a data-type="url" data-url="<@url value='/menunode/add'/>">新增</a>
                </div>
            </div>
        </div>
    </div>
    <div class="menu_level_2 menu_level" style="display: block;">
        <div class="menu_click s_menu_name menu_2_style">
            <div class="s_menu_l">
                <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>用户管理</span>
            </div>
            <div class="s_menu_r">
                <img class="s_tree_hide" src="<@static value='/icons/tree_hide.png'/>">
            </div>
        </div>
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/usr/getlist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>用户</span>
                    </div></a>
                <div class="s_menu3_r">
                    <a data-type="url" data-url="<@url value='/usr/add'/>">新增</a>
                </div>
            </div>
        </div>
        <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/group/getlist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>用户组</span>
                    </div></a>
                <div class="s_menu3_r">
                    <a data-type="url" data-url="<@url value='/group/add'/>">新增</a>
                </div>
            </div>
        </div>
         <div class="menu_level_3 menu_level" style="display: none;">
            <div class="s_menu_name menu_3_style">
                <a data-type="url" data-url="<@url value='/operationLog/getlist'/>"><div class="s_menu3_l">
                        <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"> <span>系统日志</span>
                    </div></a>
            </div>
        </div>
    </div>
</div>
<div class="menu_level_1" id="busi_partner_menus" style="display: none;">
    <div class="menu_click s_menu_name menu_1_style">
        <div class="s_menu_l">
            <img class="s_menu_icon" src="<@static value='/icons/partner.png'/>"><span>Product</span>
        </div>
        <div class="s_menu_r">
            <img class="s_tree_hide" src="<@static value='/icons/tree_show.png'/>">
        </div>
    </div>
    <div class="menu_level_3 menu_level" style="display: block;">
        <div class="s_menu_name menu_3_style">
            <a data-type="url" data-url="<@url value='material/supplier/getlist'/>"><div class="s_menu3_l" style="width: 98%;">
                    <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"><span>Product Stocks</span>
                </div> </a>
            <div class="s_menu3_r" style="display: none;"></div>
        </div>
    </div>
    <div class="menu_level_3 menu_level" style="display: block;">
        <div class="s_menu_name menu_3_style">
            <a data-type="url" data-url="<@url value='report/expiration/getlist'/>"><div class="s_menu3_l" style="width: 98%;">
                    <img class="s_menu_icon" src="<@static value='/icons/tree_3.png'/>"><span>Expiration Period</span>
                </div> </a>
            <div class="s_menu3_r" style="display: none;"></div>
        </div>
    </div>
</div>