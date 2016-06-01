package com.siping.wms.pda.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.SpringController;
import org.stroma.framework.core.platform.web.StromaWebserviceController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.setting.DeploymentSettings;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.material.service.MaterialService;
import com.siping.intranet.crm.storage.service.StorageLocationService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.response.GetInventoryCheckResponseNew;
import com.siping.smartone.inventory.response.InventoryReceiptItemResponse;
import com.siping.smartone.inventory.response.InventoryReceiptResponse;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.wms.pda.service.PdaService;

@Controller
public class PdaController extends StromaWebserviceController implements SpringController {

    @Autowired
    private PdaService pdaService;
    @Autowired
    private StorageLocationService storageLocationService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private MaterialService materialService;
    @Value("${site.staticdir}")
    private String staticPath;
    @Autowired
    private DeploymentSettings deploymentSettings;
    

    @RequestMapping(value = "/pda/materialbatchinfo/get", method = RequestMethod.POST)
    // 得到有关批次的信息
    @ResponseBody
    public GetMaterialBatchResponse getMaterialBatchInfoByPda(@RequestParam(value = "materialId", required = true) String materialId,
                                                              @RequestParam(value = "batchNumber", required = true) String batchNumber) {
        GetMaterialBatchResponse response = null;
        try {
            response = materialService.getMaterialBatch(materialId, batchNumber);
            if (response.getBatchNumber() == null && response.getCounts() == null) {
                response = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/pda/material/get", method = RequestMethod.POST)
    // 得到商品的信息
    @ResponseBody
    public GetMaterialResponse getMaterialByPda(@RequestParam(value = "materialNo", required = true) String materialNo, @RequestParam(value = "batchNumber", required = true) String batchNumber) {
        GetMaterialResponse response = null;
        try {
            response = materialService.get(null, materialNo, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/pda/login", method = RequestMethod.POST)
    @ResponseBody
    public UserLoginResponse login(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password) {
        UserLoginResponse response = null;
        UserLoginRequest user = new UserLoginRequest();
        user.setUsername(username);
        user.setPassword(password);
        try {
            response = pdaService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/pda/inventoryreceipt/getlist", method = RequestMethod.POST)
    @ResponseBody
    public PagingResponse<InventoryReceiptResponse> getReceiptOrderList(@RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                                        @RequestParam(value = "pageNo", required = true) Integer pageNo,
                                                                        @RequestParam(value = "userid", required = true) Integer userid, @RequestParam(value = "status", required = true) Integer status) {
        PagingResponse<InventoryReceiptResponse> response = null;
        try {
            response = pdaService.getReceiptOrderList(pageNo, pageSize, userid, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/pda/inventoryreceipt/get", method = RequestMethod.GET)
    @ResponseBody
    public InventoryReceiptResponse get(@RequestParam("receiptNumber") String receiptNumber) {
        InventoryReceiptResponse response = null;
        try {
            response = pdaService.get(receiptNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    // @RequestMapping(value = "/pda/inventoryreceipt/add", method =
    // RequestMethod.POST)
    // @ResponseBody
    // public ResultMsg add(@RequestBody InventoryReceiptRequest request) {
    // ResultMsg resultMsg = null;
    // try {
    // Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
    // request.setCreateBy(loginId);
    // if (pdaService.add(request)) {
    // resultMsg = new ResultMsg(Boolean.TRUE,
    // i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
    // } else {
    // resultMsg = new ResultMsg(Boolean.TRUE,
    // i18nUtil.getMessage(ErrorCode.ADD_ERROR));
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // resultMsg = new ResultMsg(Boolean.FALSE,
    // i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" +
    // e.getMessage() }));
    // }
    // return resultMsg;
    // }

    // @RequestMapping(value = "/pda/inventoryreceipt/delete", method =
    // RequestMethod.GET)
    // @ResponseBody
    // public ResultMsg delete(@RequestParam(value = "receiptNumbers", required
    // = true) List<String> receiptNumbers) {
    // ResultMsg resultMsg = null;
    // try {
    // if (pdaService.delete(receiptNumbers)) {
    // resultMsg = new ResultMsg(Boolean.TRUE,
    // i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
    // } else {
    // resultMsg = new ResultMsg(Boolean.TRUE,
    // i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // resultMsg = new ResultMsg(Boolean.TRUE,
    // i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
    // }
    // return resultMsg;
    // }

    @RequestMapping(value = "/pda/inventoryreceiptitem/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(@RequestBody InventoryReceiptItemResponse request) {
        ResultMsg resultMsg = null;
        try {
            if (pdaService.edit(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/pda/inventoryreceiptitem/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody InventoryReceiptItemResponse request) {
        ResultMsg resultMsg = null;
        try {
            if (pdaService.addItem(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/pda/inventoryreceiptitem/getrownumber", method = RequestMethod.GET)
    @ResponseBody
    public String getRowNumber(@RequestParam(value = "batchNumber", required = true) String batchNumber,
                              @RequestParam(value = "materialId", required = true) String materialId) {
        String rowNumber = null;
        try {
            rowNumber = pdaService.getRowNumber(batchNumber, Integer.parseInt(materialId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowNumber;
    }

    @RequestMapping(value = "/pda/getMaterialBatchByStorageLocation/getlist", method = RequestMethod.GET)
    @ResponseBody
    public PagingResponse<GetMaterialBatchResponse> getMaterialBatchByStorageLocation(@RequestParam(value = "locationNo", required = true) String locationNo) {
        List<GetMaterialBatchResponse> materialBatchs = null;
        try {
            materialBatchs = pdaService.getMaterialBatchByStorageLocation(locationNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PagingResponse<GetMaterialBatchResponse> res = new PagingResponse<GetMaterialBatchResponse>();
        if (CollectionUtils.isNotEmpty(materialBatchs)) {
            res.setRecords(materialBatchs);
        }
        return res;
    }

    @RequestMapping(value = "/pda/getStorageLocationWithMaterialBatchByMaterial/getlist", method = RequestMethod.GET)
    @ResponseBody
    public PagingResponse<GetMaterialBatchResponse> getStorageLocationWithMaterialBatchByMaterial(@RequestParam(value = "materialNo", required = true) String materialNo) {
        List<GetMaterialBatchResponse> materialBatchs = null;
        try {
            materialBatchs = pdaService.getStorageLocationWithMaterialBatchByMaterial(materialNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PagingResponse<GetMaterialBatchResponse> res = new PagingResponse<GetMaterialBatchResponse>();
        if (CollectionUtils.isNotEmpty(materialBatchs)) {
            res.setRecords(materialBatchs);
        }
        return res;
    }

    @RequestMapping(value = "/pda/storageLocation/getbypda", method = RequestMethod.POST)
    @ResponseBody
    public StorageLocationResponese delete(@RequestParam(value = "locationId", required = true) String locationId) {
        StorageLocationResponese storageLocation = null;
        try {
            storageLocation = storageLocationService.get(locationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageLocation;
    }

    @RequestMapping(value = "/pda/checkbatchno", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkBatchNo(@RequestParam(value = "batchNo", required = true) String batchNo, @RequestParam(value = "materialId", required = true) String materialId) {
        Boolean rs = null;
        try {
            rs = pdaService.checkBatchNo(batchNo, materialId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @RequestMapping(value = "/pda/inventorycheck/getlist", method = RequestMethod.POST)
    @ResponseBody
    public PagingResponse<GetInventoryCheckResponseNew> getInventoryCheckList(@RequestParam(value = "pageSize", required = true) Integer pageSize,
                                                                              @RequestParam(value = "pageNo", required = true) Integer pageNo,
                                                                              @RequestParam(value = "userid", required = true) Integer userid,
                                                                              @RequestParam(value = "status", required = true) Integer status) {
        PagingResponse<GetInventoryCheckResponseNew> response = null;
        try {
            response = pdaService.getInventoryCheckList(pageNo, pageSize, userid, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/pda/inventorycheck/get", method = RequestMethod.GET)
    @ResponseBody
    public GetInventoryCheckResponseNew getInventoryCheck(@RequestParam("checkNumber") String checkNumber) {
        GetInventoryCheckResponseNew response = null;
        try {
            response = pdaService.getInventoryCheck(checkNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    @RequestMapping(value = "/pda/pdadownload/get", method = RequestMethod.GET)
    @ResponseBody
    public HttpServletResponse downloadPda(HttpServletResponse response, HttpServletRequest request) {
        String separator = File.separator;
        String contextPath = request.getSession().getServletContext().getRealPath("") ;
        
        String appPath = contextPath + separator + "resources";
        String fileName = new String("WMS-release.apk");
        File file = new File(appPath+separator+fileName);
        if (!file.exists()) {
            return null;
        }
        FileInputStream in = null;
        OutputStream out = null;
        try {
            //读取要下载的文件，保存到文件输入流
            in = new FileInputStream(appPath+separator+fileName);
            //创建输出流
            out = response.getOutputStream();
            response.setHeader("Content-Length", file.length()+"");
            //创建缓冲区
            byte buffer[] = new byte[4*1024];
            int len = 0;
            //循环将输入流中的内容读取到缓冲区
            while((len=in.read(buffer))>0) {
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0 , len);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return response;
    }

    @RequestMapping(value = "/pda/inventorycheck/commit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg commitInventoryCheck(@RequestBody GetInventoryCheckResponseNew request) {
        ResultMsg resultMsg = null;
        try {
            if (pdaService.commitInventoryCheck(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }

        return resultMsg;
    }

    @RequestMapping(value = "/pda/checkallocatenumber", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg checkAllocateNumber(@RequestBody AddInventoryAllocateRequest request) {
        ResultMsg resultMsg = null;
        try {
            Boolean isEnough = pdaService.checkAllocateNumber(request);
            resultMsg = new ResultMsg(isEnough, isEnough ? i18nUtil.getMessage(ErrorCode.ADD_SUCCESS) : i18nUtil.getMessage(ErrorCode.ADD_ERROR_NO_PARAM));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
}
