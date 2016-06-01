package com.siping.intranet.ec.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.ec.request.AddCustomerRequest;
import com.siping.smartone.ec.request.AddVendorRequest;
import com.siping.smartone.ec.request.PromisedProduct;
import com.siping.smartone.ec.response.ProductCustomsResponse;
import com.siping.smartone.wms.request.ESaleOrderRequest;

@Repository
public class EcApiRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean addShop(AddVendorRequest request) {
        String requestContent = JSONBinder.binder(AddVendorRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/rest/ec/addshop").body(requestContent));
    }

    public Boolean addCustomer(AddCustomerRequest request) {
        return null;
    }

    public ProductCustomsResponse checkMaterialNo(String materialno) {
        if (!StringUtils.hasText(materialno)) {
            throw new BusinessProcessException("商品货号为空！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(ProductCustomsResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/rest/ec/checkmaterialno/%s").arguments(materialno));
    }

    public String getOnhandBalance(String productno) {
        if (!StringUtils.hasText(productno)) {
            throw new BusinessProcessException("商品货号为空！");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(String.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/rest/ec/getonhandbalance/%s").arguments(productno));
    }

    public boolean addEcSalesorder(ESaleOrderRequest request) {
        String requestContent = JSONBinder.binder(ESaleOrderRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/rest/ec/addecsalesorder").body(requestContent));
    }

    public boolean subtractQuantity(String productNo, Float quantity) {
        PromisedProduct request = new PromisedProduct();
        request.setProductNo(productNo);
        request.setQuantity(quantity);
        String requestContent = JSONBinder.binder(PromisedProduct.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/ec/promisedquantity/subtract").body(requestContent));
    }

    public boolean addQuantity(String productNo, Float quantity) {
        PromisedProduct request = new PromisedProduct();
        request.setProductNo(productNo);
        request.setQuantity(quantity);
        String requestContent = JSONBinder.binder(PromisedProduct.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/ec/promisedquantity/add").body(requestContent));
    }
}