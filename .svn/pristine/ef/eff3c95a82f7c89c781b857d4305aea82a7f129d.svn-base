package com.siping.service.ec.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.ec.repository.EcApiRestRepository;
import com.siping.service.material.repository.MaterialRestRepository;
import com.siping.service.sell.order.repository.SellOrderRestRepository;
import com.siping.smartone.ec.request.AddVendorRequest;
import com.siping.smartone.ec.request.PromisedProduct;
import com.siping.smartone.ec.response.ProductCustomsResponse;
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderItemRequest;
import com.siping.smartone.invoicing.sell.order.request.AddSellOrderRequest;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.wms.request.ESaleOrderItemRequest;
import com.siping.smartone.wms.request.ESaleOrderRequest;
import com.siping.wms.repository.ReadyShipmentsRestRepository;

@Service
public class EcApiRestService extends DBSwitch {
    @Autowired
    private EcApiRestRepository ecApiRestRepository;
    @Autowired
    private ReadyShipmentsRestRepository readyShipmentsRestRepository;
    @Autowired
    private SellOrderRestRepository sellOrderRestRepository;
    @Autowired
    private MaterialRestRepository materialRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addShop(AddVendorRequest request) {
        return ecApiRestRepository.addShop(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ProductCustomsResponse checkMaterialNo(String materialno) {
        return ecApiRestRepository.checkMaterialNo(materialno);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public String getOnhandBalance(String productno) {
        String num = ecApiRestRepository.getOnhandBalance(productno);
        if (num == null) {
            num = "0";
        }
        return num;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addEcSalesorder(ESaleOrderRequest request) {
        AddSellOrderRequest orderRequest = new AddSellOrderRequest();
        orderRequest.setBillsDate(request.getBillsDate());
        orderRequest.setFromBillsNo(request.getEcOrderNumber());
        orderRequest.setGatheringPrice(request.getGatheringPrice());
        orderRequest.setGiftPrice(request.getGiftPrice());
        orderRequest.setIsDraft(false);
        request.setPartnerId(0);
        orderRequest.setPartnerId("0");
        orderRequest.setSummary(request.getSummary());
        orderRequest.setTotalPrice(request.getTotalPrice());
        orderRequest.setCustomsCode(request.getCustomsCode());
        orderRequest.setTrackingNo(request.getTrackingNo());
        orderRequest.setFavorablePrice(request.getFavorablePrice());
        List<ESaleOrderItemRequest> requestItem = request.getItems();
        List<AddSellOrderItemRequest> requestOrderItem = new ArrayList<AddSellOrderItemRequest>();
        for (ESaleOrderItemRequest es : requestItem) {
            AddSellOrderItemRequest as = new AddSellOrderItemRequest();
            GetMaterialResponse m = materialRestRepository.get(null, es.getProductNo(), null);
            es.setMaterialId(m.getId().toString());
            as.setMaterialId(es.getMaterialId());
            as.setCounts(es.getCounts());
            as.setSellPrice(es.getSellPrice());
            as.setAfterDiscount(es.getTotal() == null ? 0 : es.getTotal());
            as.setTax(es.getTax());
            Double discount = es.getDiscount() == null ? 100 : es.getDiscount();
            as.setDiscount(discount);
            Double total = es.getTotal() == null ? 0.0 : es.getTotal();
            Double tax = es.getTax() == null ? 0.0 : es.getTax();
            if(0.0 != total){
                BigDecimal b = new BigDecimal(tax / total * 100);
                Double taxRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                as.setTaxRate(taxRate);
            }else{
                as.setTaxRate(0.0);
            }
            as.setTotal(total + tax);
            as.setIsGift(es.getIsGift());
            requestOrderItem.add(as);
        }
        orderRequest.setOrderItemList(requestOrderItem);
        boolean rs = readyShipmentsRestRepository.add(request);
        boolean so = sellOrderRestRepository.add(orderRequest);
        if (rs && so) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean subtractQuantity(PromisedProduct request) {
        return ecApiRestRepository.subtractQuantity(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addQuantity(PromisedProduct request) {
        return ecApiRestRepository.addQuantity(request);
    }
}
