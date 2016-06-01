package com.siping.smartone.common;

import java.io.Serializable;

public final class PagePath implements Serializable {

    private static final long serialVersionUID = 8957070956245738627L;

    /** 登录界面 */
    public static final String LOGIN_LOGIN = "login/login";
    /** 首页 **/
    public static final String INDEX_PAGE = "login/homePage";
    /** 主页 */
    public static final String WELCOME = "login/welcome";
    /** 后台主页 */
    public static final String ADMIN_WELCOME = "admin/adminWelcome";
    /** 用户列表 */
    public static final String ALL_USER = "admin/user/allUser";
    /** 用户组列表 */
    public static final String ALL_GROUP = "admin/group/allGroup";
    /** 弹出用户选择框 **/
    public static final String OPEN_WIN_USER = "admin/user/openWinGetUser";
    /** 弹出用户选择框 **/
    public static final String OPEN_WIN_CONSIGNEE = "wms/openWinGetConsignee";
    /** 菜单资源列表 */
    public static final String ALL_MENUNODE = "admin/menunode/allMenuNode";
    /** 权限组用户弹出层 */
    public static final String LAYER_GROUP_USER = "admin/user/layerGroupUser";
    /** 权限组编辑 */
    public static final String ADD_PERMISSIONGROUP = "admin/user/addPermissionGroup";
    /** 权限组列表 */
    public static final String ALL_PERMISSIONGROUP = "admin/user/allPermissionGroup";
    /** 添加用户 */
    public static final String ADD_USER = "admin/user/addUser";
    /** 用户编辑 */
    public static final String EDIT_USER = "admin/user/editUser";
    /** 用户编辑 */
    public static final String VIEW_USER = "admin/user/viewUser";
    /** 修改密码 */
    public static final String USER_RESET_PASS = "admin/user/resetPass";
    /** 添加用户组 */
    public static final String ADD_GROUP = "admin/group/addGroup";
    /** 编辑用户组 */
    public static final String EDIT_GROUP = "admin/group/editGroup";
    /** 编辑用户组 */
    public static final String VIEW_GROUP = "admin/group/viewGroup";
    /** 查看用户组权限 */
    public static final String VIEW_GROUP_PERMISSIONS = "admin/group/viewGroupPermissions";
    /** 编辑用户组权限 */
    public static final String EDIT_GROUP_PERMISSIONS = "admin/group/editGroupPermissions";
    /** 菜单资源列表 */
    public static final String ADD_MENUNODE = "admin/menunode/addMenuNode";
    /** 菜单资源编辑 */
    public static final String EDIT_MENUNODE = "admin/menunode/editMenuNode";
    /** 查看菜单资源 */
    public static final String VIEW_MENUNODE = "admin/menunode/viewMenuNode";
    /** 选择上级菜单 */
    public static final String OPEN_WIN_MENUNODE = "admin/menunode/openWinGetMenu";
    /** 添加供应商 */
    public static final String ADD_SUPPLIER = "crm/partner/addSupplier";
    /** 添加客户 */
    public static final String ADD_CUSTOMER = "crm/partner/addCustomer";
    /** 业务伙伴信息修改 */
    public static final String EDIT_PARTNER = "crm/partner/editPartner";
    /** 供应商信息修改 */
    public static final String EDIT_SUPPLIER = "crm/partner/editSupplier";
    /** 所有客户信息 */
    public static final String ALL_CUSTOMER = "crm/partner/allCustomer";
    /** 所有供应商信息 */
    public static final String ALL_SUPPLIER = "crm/partner/allSupplier";
    /** 业务伙伴详情 */
    public static final String DETAIL_PARTNER = "crm/partner/detailPartner";
    /** 供应商详情 */
    public static final String DETAIL_SUPPLIER = "crm/partner/detailSupplier";
    /** 商品添加 */
    public static final String ADD_MATERIAL = "pss/material/addMaterial";
    /** 商品修改 */
    public static final String EDIT_MATERIAL = "pss/material/editMaterial";
    /** 所有商品信息 */
    public static final String ALL_MATERIAL = "pss/material/allMaterial";
    /** 供应商查看所有商品信息 */
    public static final String ALL_SUPPLIER_MATERIAL = "pss/material/allSupplierMaterial";
    /** 供应商查看所有商品期限报表 */
    public static final String ALL_MATERIAL_EXP = "pss/material/allExpMaterial";
    /** 商品详情信息 */
    public static final String DETAIL_MATERIAL = "pss/material/detailMaterial";
    /** 业务伙伴银行账户列表 */
    public static final String ALL_PARTNER_BANK = "crm/partner/layerAllPartnerBank";
    /** 修改业务伙伴银行 */
    public static final String EDIT_PARTNER_BANK = "crm/partner/layerEditPartnerBank";
    /** 添加业务伙伴银行 */
    public static final String ADD_PARTNER_BANK = "crm/partner/layerAddPartnerBank";
    /** 新增仓库 */
    public static final String ADD_STORAGE = "pss/stock/addStorage";
    /** 编辑仓库 */
    public static final String EDIT_STORAGE = "pss/stock/editStorage";
    /** 仓库列表 */
    public static final String ALL_STORAGE = "pss/stock/allStorage";
    /** 新增过账期间 */
    public static final String ADD_POST_PERIOD = "finance/postperiod/addPostPeriod";
    /** 编辑过账期间 */
    public static final String EDIT_POST_PERIOD = "finance/postperiod/editPostPeriod";
    /** 过账期间列表 */
    public static final String ALL_POST_PERIOD = "finance/postperiod/allPostPeriod";
    /** 添加采购申请单 */
    public static final String ADD_APPLY_PO = "pss/purchase/addApplyPurchaseOrder";
    /** 添加采购单 */
    public static final String ADD_PO = "pss/purchase/addPurchaseOrder";
    /** 添加采购收货单 */
    public static final String ADD_RECEIPT_PO = "pss/purchase/addReceiptPurchaseOrder";
    /** 添加采购退货单 */
    public static final String ADD_RETURN_PO = "pss/purchase/addReturnPurchaseOrder";
    /** 采购申请单列表 */
    public static final String ALL_APPLY_PO = "pss/purchase/allApplyPurchaseOrder";
    /** 采购申请单草稿箱列表 */
    public static final String ALL_DRAFT_APPLY_PO = "pss/purchase/allDraftApplyPurchaseOrder";
    /** 采购单草稿箱列表 */
    public static final String ALL_DRAFT_PO = "pss/purchase/allDraftPurchaseOrder";
    /** 采购收货单草稿箱列表 */
    public static final String ALL_DRAFT_RECEIPT_PO = "pss/purchase/allDraftReceiptPurchaseOrder";
    /** 采购退货单草稿箱列表 */
    public static final String ALL_DRAFT_RETURN_PO = "pss/purchase/allDraftReturnPurchaseOrder";
    /** 采购单列表 */
    public static final String ALL_PO = "pss/purchase/allPurchaseOrder";
    /** 采购收货单列表 */
    public static final String ALL_RECEIPT_PO = "pss/purchase/allReceiptPurchaseOrder";
    /** 采购退货单列表 */
    public static final String ALL_RETURN_PO = "pss/purchase/allReturnPurchaseOrder";
    /** 采购申请单详情 */
    public static final String DETAIL_APPLY_PO = "pss/purchase/detailApplyPurchaseOrder";
    /** 采购单详情 */
    public static final String DETAIL_PO = "pss/purchase/detailPurchaseOrder";
    /** 采购收货单详情 */
    public static final String DETAIL_RECEIPT_PO = "pss/purchase/detailReceiptPurchaseOrder";
    /** 采购退货单详情 */
    public static final String DETAIL_RETURN_PO = "pss/purchase/detailReturnPurchaseOrder";
    /** 采购申请单编辑 */
    public static final String EDIT_APPLY_PO = "pss/purchase/editApplyPurchaseOrder";
    /** 采购单编辑 */
    public static final String EDIT_PO = "pss/purchase/editPurchaseOrder";
    /** 采购收货单编辑 */
    public static final String EDIT_RECEIPT_PO = "pss/purchase/editReceiptPurchaseOrder";
    /** 采购退货单编辑 */
    public static final String EDIT_RETURN_PO = "pss/purchase/editReturnPurchaseOrder";
    /** 添加库存收货单 */
    public static final String ADD_INVENTORY_RECEIPT = "pss/stock/addInventoryReceipt";
    /** 添加库存发货单 */
    public static final String ADD_INVENTORY_DELIVERY = "pss/stock/addInventoryDelivery";
    /** 添加库存盘点单 */
    public static final String ADD_INVENTORY_CHECK = "pss/stock/addInventoryCheck";
    /** 添加PDA库存盘点单 */
    public static final String ADD_PDA_INVENTORY_CHECK = "pss/stock/addPDAInventoryCheck";
    /** 弹出层查询所有仓库和物料类型 */
    public static final String LAYER_CHOOSE_STORAGE = "pss/stock/layerChooseStorage";
    /** 打印采购申请单 */
    public static final String PRINT_APPLY_PO = "pss/print/printApplyPurchaseOrder";
    /** 打印销售发货单 */
    public static final String PRINT_DELIVERY_SO = "pss/print/printDeliverySaleOrder";
    /** 打印库存盘点单 */
    public static final String PRINT_INVENTORY_CHECK = "pss/print/printInventoryCheck";
    /** 打印库存发货单 */
    public static final String PRINT_INVENTORY_DELIVERY = "pss/print/printInventoryDelivery";
    /** 打印库存收货单 */
    public static final String PRINT_INVENTORY_RECEIPT = "pss/print/printInventoryReceipt";
    /** 打印采购订单 */
    public static final String PRINT_PO = "pss/print/printPurchaseOrder";
    /** 打印采购收货单 */
    public static final String PRINT_RECEIPT_PO = "pss/print/printReceiptPurchaseOrder";
    /** 打印采购退货单 */
    public static final String PRINT_RETURN_PO = "pss/print/printReturnPurchaseOrder";
    /** 打印销售退货单 */
    public static final String PRINT_RETURN_SO = "pss/print/printReturnSaleOrder";
    /** 打印销售订单 */
    public static final String PRINT_SO = "pss/print/printSaleOrder";
    /** 库存盘点单列表 */
    public static final String ALL_INVENTORY_CHECK = "pss/stock/allInventoryCheck";
    /** PDA库存盘点单列表 */
    public static final String ALL_PDA_INVENTORY_CHECK = "pss/stock/allPdaInventoryCheck";
    /** 库存盘点单草稿箱列表 */
    public static final String ALL_DRAFT_INVENTORY_CHECK = "pss/stock/allDraftInventoryCheck";
    /** 库存发货单草稿箱列表 */
    public static final String ALL_DRAFT_INVENTORY_DELIVERY = "pss/stock/allDraftInventoryDelivery";
    /** 库存收货单草稿箱列表 */
    public static final String ALL_DRAFT_INVENTORY_RECEIPT = "pss/stock/allDraftInventoryReceipt";
    /** 库存发货单列表 */
    public static final String ALL_INVENTORY_DELIVERY = "pss/stock/allInventoryDelivery";
    /** 库存收货单列表 */
    public static final String ALL_INVENTORY_RECEIPT = "pss/stock/allInventoryReceipt";
    /** 库存盘点单详情 */
    public static final String DETAIL_INVENTORY_CHECK = "pss/stock/detailInventoryCheck";
    /** 库存发货单详情 */
    public static final String DETAIL_INVENTORY_DELIVERY = "pss/stock/detailInventoryDelivery";
    /** 库存收货单详情 */
    public static final String DETAIL_INVENTORY_RECEIPT = "pss/stock/detailInventoryReceipt";
    /** 库存盘点单编辑 */
    public static final String EDIT_INVENTORY_CHECK = "pss/stock/editInventoryCheck";
    /** 库存发货单编辑 */
    public static final String EDIT_INVENTORY_DELIVERY = "pss/stock/editInventoryDelivery";
    /** 库存收货单编辑 */
    public static final String EDIT_INVENTORY_RECEIPT = "pss/stock/editInventoryReceipt";
    /** 添加销售发货单 */
    public static final String ADD_DELIVERY_SO = "pss/sale/addDeliverySaleOrder";
    /** 添加销售退货单 */
    public static final String ADD_RETURN_SO = "pss/sale/addReturnSaleOrder";
    /** 添加销售订单 */
    public static final String ADD_SO = "pss/sale/addSaleOrder";
    /** 销售发货单列表 */
    public static final String ALL_DELIVERY_SO = "pss/sale/allDeliverySaleOrder";
    /** 销售发货单草稿箱列表 */
    public static final String ALL_DRAFT_DELIVERY_SO = "pss/sale/allDraftDeliverySaleOrder";
    /** 销售退货单草稿箱列表 */
    public static final String ALL_DRAFT_RETURN_SO = "pss/sale/allDraftReturnSaleOrder";
    /** 销售订单草稿箱列表 */
    public static final String ALL_DRAFT_SO = "pss/sale/allDraftSaleOrder";
    /** 销售退货单列表 */
    public static final String ALL_RETURN_SO = "pss/sale/allReturnSaleOrder";
    /** 销售订单列表 */
    public static final String ALL_SO = "pss/sale/allSaleOrder";
    /** 销售发货单详情 */
    public static final String DETAIL_DELIVERY_SO = "pss/sale/detailDeliverySaleOrder";
    /** 销售退货单详情 */
    public static final String DETAIL_RETURN_SO = "pss/sale/detailReturnSaleOrder";
    /** 销售订单详情 */
    public static final String DETAIL_SO = "pss/sale/detailSaleOrder";
    /** 销售发货单编辑 */
    public static final String EDIT_DELIVERY_SO = "pss/sale/editDeliverySaleOrder";
    /** 销售退货单编辑 */
    public static final String EDIT_RETURN_SO = "pss/sale/editReturnSaleOrder";
    /** 销售订单编辑 */
    public static final String EDIT_SO = "pss/sale/editSaleOrder";
    /** 弹层选择单据 */
    public static final String LAYER_CHOOSE_APPLAY = "pss/purchase/chooseApplyPurchaseOrder";
    public static final String LAYER_CHOOSE_ORDER = "pss/purchase/choosePurchaseOrder";
    public static final String LAYER_CHOOSE_RECEIPT = "pss/purchase/choosePurchaseReceiptOrder";
    /** 弹层选择批次号 */
    public static final String BATCH_NUMBER = "pss/material/batchNumber";
    /** 弹层选择供应商或者客户 **/
    public static final String OPEN_WIN_PARTNER = "crm/partner/openWinGetPartner";
    /** 弹层选择供应商或者客户 **/
    public static final String OPEN_WIN_PARTNER_FORNAME = "crm/partner/openWinGetPartnerName";
    /** 由于Id冲突来选择供应商或者客户 **/
    public static final String OPEN_WIN_PARTNER_WITH_ID_CONFLICT = "pss/material/openWinGetPartner";
    /** 弹层选择物料 **/
    public static final String OPEN_WIN_MATERIAL = "pss/material/openWinGetMaterial"; // TODO旧版物料选择框以后删掉

    public static final String OPEN_MATERIAL_WIN = "pss/material/openMaterialWin";
    /** 新增商品单位 **/
    public static final String ADD_MATERIAL_UNIT = "pss/materialunit/addMaterialUnit";
    /** 商品单位列表 **/
    public static final String ALL_MATERIAL_UNIT = "pss/materialunit/allMaterialUnit";
    /** 商品单位弹窗选择列表 **/
    public static final String ALL_WIN_MATERIAL_UNIT = "pss/materialunit/layerChooseMaterialUnit";
    /** 商品单位详情 **/
    public static final String DETAIL_MATERIAL_UNIT = "pss/materialunit/detailMaterialUnit";
    /** 编辑商品单位 **/
    public static final String EDIT_MATERIAL_UNIT = "pss/materialunit/editMaterialUnit";

    /** 库存余额报表 **/
    public static final String INVENTORY_BALANCE_LIST = "report/inventory/inventoryBalanceList";
    /** 库存余额报表明细表即出入库明细表 **/
    public static final String INVENTORY_BALANCE_DETAIL = "report/inventory/inventoryBalanceDetail";
    /** 库存状态列表报表 **/
    public static final String INVENTORY_STATUS_LIST = "report/inventory/inventoryStatusList";
    /** 库存状态明细报表 **/
    public static final String INVENTORY_STATUS_DETAIL = "report/inventory/inventoryStatusDetail";
    /** 供应商采购分析 **/
    public static final String SUPPLIER_PURCHASE_ANALYSIS = "report/purchase/supplierPurchase";
    /** 商品采购分析 **/
    public static final String PRODUCT_PURCHASE_ANALYSIS = "report/purchase/productPurchase";
    /** 采购明细统计 **/
    public static final String PURCHASE_ANALYSIS = "report/purchase/purchaseDetail";
    /** 采购订单查询 **/
    public static final String PURCHASE_ORDER_STATISTIC = "report/purchase/purchaseOrder";
    /** 管理界面 **/
    /** 计量单位新增 **/
    public static final String ADD_UNIT_MANAGEMENT = "admin/config/addUnitManagement";
    /** 计量单位列表 **/
    public static final String ALL_UNIT_MANAGEMENT = "admin/config/allUnitManagement";
    /** 计量单位编辑 **/
    public static final String EDIT_UNIT_MANAGEMENT = "admin/config/editUnitManagement";
    /** 商品分类新增 **/
    public static final String ADD_MATERIAL_TYPE_MANAGEMENT = "admin/config/addMaterialTypeManagement";
    /** 商品分类列表 **/
    public static final String ALL_MATERIAL_TYPE_MANAGEMENT = "admin/config/allMaterialTypeManagement";
    /** 商品分类编辑 **/
    public static final String EDIT_MATERIAL_TYPE_MANAGEMENT = "admin/config/editMaterialTypeManagement";
    /** 商品分类管理 **/
    public static final String MANAGE_MATERIAL_TYPE_MANAGEMENT = "admin/config/manageMaterialTypeManagement";
    /** 商品分类选择界面 **/
    public static final String SHOW_MATERIAL_TYPE_MANAGEMENT = "admin/config/chooseMaterialTypeManagement";
    /** 供应商分组新增 **/
    public static final String ADD_PARTNER_GROUP_MANAGEMENT = "crm/partner/addPartnerGroupManagement";
    /** 供应商分组列表 **/
    public static final String ALL_PARTNER_GROUP_MANAGEMENT = "crm/partner/allPartnerGroupManagement";
    /** 供应商分组编辑 **/
    public static final String EDIT_PARTNER_GROUP_MANAGEMENT = "crm/partner/editPartnerGroupManagement";
    /** 供应商分组管理 **/
    public static final String MANAGE_SUPPLIER_GROUP_MANAGEMENT = "crm/partner/managePartnerGroupManagement";
    /** 商家列表 **/
    public static final String ALL_SHOPS = "crm/partner/allShops";
    /** 商家选择列表 **/
    public static final String GET_SHOPS = "crm/partner/openWinGetShop";
    /** 批号交易报表 **/
    public static final String INVENTORY_BATCH_LIST = "report/inventory/inventoryBatchList";
    /** 批号交易明细报表 **/
    public static final String INVENTORY_BATCH_DETAIL = "report/inventory/inventoryBatchDetail";
    /** 添加库位 **/
    public static final String ADD_STORAGE_LOCATION = "pss/stock/layerAddStorageLocation";
    /** 添加库区 **/
    public static final String ADD_STORAGE_AREA = "pss/stock/layerAddStorageArea";
    /** 根据仓库显示库位 **/
    public static final String ALL_STORAGE_LOCATION = "pss/stock/layerAllStorageLocation";
    /** 根据仓库显示库区 **/
    public static final String ALL_STORAGE_AREA = "pss/stock/layerAllStorageArea";
    /** 根据库位Id显示编辑库位 **/
    public static final String EDIT_STORAGE_LOCATION = "pss/stock/layerEditStorageLocation";
    /** 根据库区Id显示编辑库区 **/
    public static final String EDIT_STORAGE_AREA = "pss/stock/layerEditStorageArea";
    /** 添加国家/地区 */
    public static final String ALL_COUNTRY_REGION = "admin/countryregion/getAllCountryRegion";
    /** 添加国家/地区 */
    public static final String ADD_COUNTRY_REGION = "admin/countryregion/addCountryRegion";
    /** 修改国家/地区 */
    public static final String EDIT_COUNTRY_REGION = "admin/countryregion/editCountryRegion";
    /** wms **/
    /** 预收货单 **/
    public static final String ALL_READY_RECEIPT = "wms/allReadyReceipt";
    /** 预收货单详情 **/
    public static final String DETAIL_READY_RECEIPT = "wms/detailReadyReceipt";
    /** 预发货单 **/
    public static final String ALL_READY_SHIPMENTS = "wms/allReadyShipments";
    /** 打印快递单 **/
    public static final String ALL_PRINT_EMS = "wms/allPrintEMS";
    /** 预发货单详情 **/
    public static final String DETAIL_RS = "wms/detailReadyShipments";
    /** 库存调拨单列表 **/
    public static final String ALL_INVENTORYALLOCATE = "wms/allInventoryAllocate";
    /** 预览拣货单 **/
    public static final String ORDER_PICK_PREVIEW = "wms/orderPickPreview";
    /** 拣货单列表 **/
    public static final String ALL_ORDER_PICK = "wms/allOrderPick";
    /** 拣货单详情 **/
    public static final String DETAIL_ORDER_PICK = "wms/detailOrderPick";
    /** 预发货单审核 **/
    public static final String ORDER_AUDIT = "wms/orderAudit";
    public static final String LAYER_ALL_TEMP_ORDER_AUDIT = "wms/layerAlltempOrderAudit";
    public static final String PACK = "wms/packing";
    public static final String TEST = "wms/test";
    public static final String ALL_OPERATION_LOG = "common/allOperationLog";
    public static final String DELETE_OPERATION_LOG = "common/deleteOperationLog";
    /** 商品有效期报表 */
    public static final String REPORT_PRODUCT_EXP = "report/purchase/productExpiration";
    public static final String REPORT_BUSINESS_SALES = "report/sale/businessSales";
    /** 商品运输查询 */
    public static final String QUERY_GOODS_TRANSPORT = "wms/report/queryGoodsTransport";
    /** 商品销售报表 */
    public static final String MATERIAL_SALE_REPORT = "report/sale/materialSaleReport";
    /** 销售分析报表 */
    public static final String SALE_ANALYSIS_REPORT = "report/sale/saleAnalysisReport";
    // 销售单量统计
    public static final String SELL_ORDER_COUNT_REPORT = "report/sale/sellOrderCountReport";
    // 销售日报，月报，季报，年报
    public static final String SALEROOM_REPORT = "report/sale/saleroomReport";
    // 客户销售统计
    public static final String CUSTOMER_SALE = "report/sale/customerSale";
    // 收货统计
    public static final String IN_WAREHOUSE = "report/inventory/inWarehouse";
    // 出货统计
    public static final String OUT_WAREHOUSE = "report/inventory/outWarehouse";
    /** 仓库预警报表 */
    public static final String MATERIAL_WARNING_REPORT = "wms/report/materialWarningReport";
}
