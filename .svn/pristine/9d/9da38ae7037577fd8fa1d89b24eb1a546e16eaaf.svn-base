package com.siping.intranet.crm.material.web;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.material.service.MaterialService;
import com.siping.smartone.common.DataConvertUtil;
import com.siping.smartone.common.ExportExcel;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfo;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfoRequest;
import com.siping.smartone.material.request.AddMaterialRequest;
import com.siping.smartone.material.request.MaterialExportParamRequest;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class MaterialAjaxController extends StromaController {
    @Autowired
    private MaterialService materialService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/material/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<String> ids) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (materialService.delete(ids, updateBy.toString()))
                ;
            {
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/material/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody AddMaterialRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            materialService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/material/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(@RequestBody AddMaterialRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            materialService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/material/export", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg export(MaterialExportParamRequest param, HttpServletRequest request, HttpServletResponse response) {
        // 返回消息
        ResultMsg msg = null;
        // 物料数据列表
        List<Map<String, Object>> listMap = new LinkedList<Map<String, Object>>();
        try {
            List<GetMaterialResponse> materials = materialService.getAllMaterials(param);
            Iterator<GetMaterialResponse> it = materials.iterator();
            while (it.hasNext()) {
                GetMaterialResponse material = it.next();
                @SuppressWarnings("unchecked")
                Map<String, Object> map = DataConvertUtil.convertBean(material);
                listMap.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 定义导出模板显示字段名
        String[] colDisplayName = { "物料编码", "物料名称", "外文名称", "所属分类", "品牌", "规格及型号", "季节", "计量单位", "国际编码", "所属商家编号", "商家名称", "保质期(天)", "供应商编码", "供应商名称", "起订量", "最小库存", "最大库存", "备注" };
        // 导出模板字段名
        String[] colName = { "materialNo", "materialName", "foreignName", "materialType", "brand", "specificationsModel", "season", "unitId", "barcode", "shops", "shopName", "expirationDate",
                "partnerId", "partnerName", "minOrder", "minInventory", "maxInventory", "description" };
        ExportExcel.exportToExcel("物料信息", "物料信息", "表1", listMap, response, colName, colDisplayName);
        return msg;
    }

    @RequestMapping(value = "/material/getlistbykey", method = RequestMethod.GET)
    @ResponseBody
    public List<GetMaterialResponse> getStorageList(@RequestParam(value = "keyword", required = true) String keyword, @RequestParam(value = "isPurchase", required = false) Boolean isPurchase,
                                                    @RequestParam(value = "isSell", required = false) Boolean isSell, @RequestParam(value = "isInventory", required = false) Boolean isInventory,
                                                    Map<String, Object> model) {
        List<GetMaterialResponse> response = null;
        if(null==isPurchase){ //TODO 旧版本没有判断商品类型，以后删掉
            isPurchase = true;
            isSell = true;
            isInventory =true;
        }
        try {
            response = materialService.getList(keyword,isPurchase,isSell,isInventory);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }

    @RequestMapping(value = "/material/getlistbykeyandstorageid", method = RequestMethod.GET)
    @ResponseBody
    public List<GetMaterialResponse> getList(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "storageid", required = false) String storageid,
                                             Map<String, Object> model) {
        List<GetMaterialResponse> response = null;
        try {
            response = materialService.getList(keyword, storageid);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }
    
    @RequestMapping(value = "/material/getByAsync", method = RequestMethod.GET)
    @ResponseBody
    public GetMaterialResponse get(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "materialNo", required = false) String materialNo,
                      @RequestParam(value = "barcode", required = false) String barcode, @RequestParam(value = "description", required = false) String description) {
        GetMaterialResponse response = null; // 查询单个物料可以单独用id查询，单独用物料编号查询，单独用国际编码查询均可，当然可以三个条件都提供来查询
        try {
            response = materialService.get(id, materialNo, barcode, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    @RequestMapping(value = "/material/getInventoryCheckInfoWithStorageIdWithStorageArea", method = RequestMethod.POST)
    @ResponseBody
    public List<GetInventoryCheckWithMaterialAndLocationInfo> getInventoryCheckInfoWithStorageIdWithStorageArea(@RequestBody GetInventoryCheckWithMaterialAndLocationInfoRequest request) {
        List<GetInventoryCheckWithMaterialAndLocationInfo> response = null;
        try {
            response = materialService.getInventoryCheckInfoWithStorageIdWithStorageArea(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessProcessException("所盘点区域不含有任何库位，或者获取数据出现错误");
        }
        return response;
    }
}
