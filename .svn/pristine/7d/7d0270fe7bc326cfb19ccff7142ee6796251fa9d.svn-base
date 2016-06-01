package com.siping.intranet.ec.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.database.redis.RedisAccess;
import org.stroma.framework.core.platform.web.SpringController;
import org.stroma.framework.core.platform.web.StromaWebserviceController;
import org.stroma.framework.core.util.StringUtils;
import org.stroma.framework.core.util.URLUtils;

import com.siping.intranet.ec.service.EcApiService;
import com.siping.smartone.ec.request.AddCustomerRequest;
import com.siping.smartone.ec.request.AddVendorRequest;
import com.siping.smartone.ec.response.EcResponse;
import com.siping.smartone.ec.response.ProductCustomsResponse;
import com.siping.smartone.wms.request.ESaleOrderRequest;

@Controller
public class EcApiController extends StromaWebserviceController implements SpringController { // 电商接口类,实现SpringController的目的在于规避未登录调用/ec/getdynamickey时出现异常

    @Autowired
    private EcApiService ecApiService;
    @Autowired
    private RedisAccess redisAccess;

    /*
     * 下文errorCode表示的意义： 0:成功 1000:token无效 1001:处理失败 2001:电商订单重复
     */
    @RequestMapping(value = "/ec/getdynamickey", method = { RequestMethod.GET })
    @ResponseBody
    public EcResponse<String> getDynamicKey() {
        String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(20);
        redisAccess.setEx(randomAlphanumeric, randomAlphanumeric, 30 * 60);
        return EcResponse.success(randomAlphanumeric);
    }

    @RequestMapping(value = "/ec/addvendor", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public synchronized EcResponse<Boolean> addVendor(@RequestBody AddVendorRequest request, HttpServletRequest httpServletRequest) { // 增加电商商家
        boolean flag = validateDynamicKey(request.getToken(), httpServletRequest);
        if (flag) {
            try {
                if (ecApiService.addShop(request)) {
                    return EcResponse.success(true);
                } else {
                    return EcResponse.fail(1001, "fail to save data");
                }
            } catch (Exception e) {
                return EcResponse.fail(1001, e.getMessage());
            }
        } else {
            return EcResponse.fail(1000, "invalid token");
        }
    }

    @RequestMapping(value = "/ec/addcustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public synchronized EcResponse<Boolean> addCustomer(@RequestBody AddCustomerRequest request, HttpServletRequest httpServletRequest) { // 增加电商平台客户
        boolean flag = validateDynamicKey(request.getToken(), httpServletRequest);
        if (flag) {
            try {
                if (ecApiService.addCustomer(request)) {
                    return EcResponse.success(true);
                } else {
                    return EcResponse.fail(1001, "fail to save data");
                }
            } catch (Exception e) {
                return EcResponse.fail(1001, e.getMessage());
            }
        } else {
            return EcResponse.fail(1000, "invalid token");
        }
    }

    @RequestMapping(value = "/ec/checkproductno", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public EcResponse<ProductCustomsResponse> checkMaterialNo(@RequestParam(value = "productno", required = true) String productNo, @RequestParam(value = "token", required = true) String token,
                                                              HttpServletRequest httpServletRequest) {
        boolean flag = validateDynamicKey(token, httpServletRequest);
        if (flag) {
            try {
                ProductCustomsResponse pc = ecApiService.checkMaterialNo(productNo);
                if (pc != null) {
                    return EcResponse.success(pc);
                } else {
                    return EcResponse.fail(1001, "invalid product code");
                }
            } catch (Exception e) {
                return EcResponse.fail(1001, e.getMessage());
            }
        } else {
            return EcResponse.fail(1000, "invalid token");
        }
    }

    private Boolean validateDynamicKey(String ecDynamicKey, HttpServletRequest request) {

        String remoteIp = URLUtils.getRemoteIp(request);
        Set<String> set = new HashSet<String>();
        set.add("114.112.104.179");
        set.add("114.112.104.178");
        if (StringUtils.hasText(ecDynamicKey)) {
            String key = redisAccess.get(ecDynamicKey);
            if (ecDynamicKey.equalsIgnoreCase(key) && set.contains(remoteIp))
                return Boolean.TRUE;
        }

        return Boolean.TRUE;
    }

    @RequestMapping(value = "/ec/getonhandbalance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public EcResponse<String> getOnhandBalance(@RequestParam(value = "productno", required = true) String productNo, @RequestParam(value = "token", required = true) String token,
                                               HttpServletRequest httpServletRequest) {
        boolean flag = validateDynamicKey(token, httpServletRequest);
        if (flag) {
            try {
                String balance = ecApiService.getOnhandBalance(productNo);
                return EcResponse.success(balance);
            } catch (Exception e) {
                return EcResponse.fail(1001, e.getMessage());
            }
        } else {
            return EcResponse.fail(1000, "invalid token");
        }
    }

    @RequestMapping(value = "/ec/addecsalesorder", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public synchronized EcResponse<Boolean> addEcSalesorder(@RequestBody ESaleOrderRequest request, HttpServletRequest httpServletRequest) {
        boolean flag = validateDynamicKey(request.getToken(), httpServletRequest);
        if (flag) {
            try {
                if (ecApiService.addEcSalesorder(request)) {
                    return EcResponse.success(true);
                } else {
                    return EcResponse.fail(1001, "fail to save data");
                }
            } catch (Exception e) {
                if (e.getMessage().equals("原始订单号重复")) {
                    return EcResponse.fail(2001, e.getMessage());
                }
                return EcResponse.fail(1001, e.getMessage());
            }
        } else {
            return EcResponse.fail(1000, "invalid token");
        }
    }

    @RequestMapping(value = "/ec/promisedquantity/subtract", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public synchronized EcResponse<Boolean> subtractQuantity(@RequestParam(value = "productno", required = true) String productNo, @RequestParam(value = "quantity", required = true) Float quantity,
                                                @RequestParam(value = "token", required = true) String token, HttpServletRequest httpServletRequest) {// 增加电商商家
        boolean flag = validateDynamicKey(token, httpServletRequest);
        if (flag) {
            try {
                if (quantity <= 0) {
                    throw new Exception("数量必须大于零");
                }
                if (ecApiService.subtractQuantity(productNo, quantity)) {
                    return EcResponse.success(true);
                } else {
                    return EcResponse.fail(1001, "productNo is maybe incorrect");
                }
            } catch (Exception e) {
                return EcResponse.fail(1001, e.getMessage());
            }
        } else {
            return EcResponse.fail(1000, "invalid token");
        }
    }

    @RequestMapping(value = "/ec/promisedquantity/add", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public synchronized EcResponse<Boolean> addQuantity(@RequestParam(value = "productno", required = true) String productNo, @RequestParam(value = "quantity", required = true) Float quantity,
                                           @RequestParam(value = "token", required = true) String token, HttpServletRequest httpServletRequest) {// 增加电商商家
        boolean flag = validateDynamicKey(token, httpServletRequest);
        if (flag) {
            try {
                if (quantity <= 0) {
                    throw new Exception("数量必须大于零");
                }
                if (ecApiService.addQuantity(productNo, quantity)) {
                    return EcResponse.success(true);
                } else {
                    return EcResponse.fail(1001, "productNo is maybe incorrect");
                }
            } catch (Exception e) {
                return EcResponse.fail(1001, e.getMessage());
            }
        } else {
            return EcResponse.fail(1000, "invalid token");
        }
    }
}
