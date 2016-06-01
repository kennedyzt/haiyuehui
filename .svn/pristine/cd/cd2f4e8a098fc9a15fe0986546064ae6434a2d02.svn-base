package com.siping.wms.readyreceipt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.common.CommonBillsForm;
import com.siping.smartone.wms.request.AddPackRequest;
import com.siping.smartone.wms.request.ESaleOrderRequest;
import com.siping.smartone.wms.request.ReadyShipmentsRequest;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.wms.readyreceipt.repository.ReadyShipmentsRepository;

@Service
public class ReadyShipmentsService {
    @Autowired
    private ReadyShipmentsRepository readyShipmentsRepository;

    public Boolean add(ESaleOrderRequest request) {
        return readyShipmentsRepository.add(request);
    }
//
//    public Boolean edit(AddSellOrderRequest request) {
//        return readyShipmentsRepository.edit(request);
//    }
//
    public ReadyShipmentsResponse get(String orderNumber) {
        return readyShipmentsRepository.get(orderNumber);
    }

    public PagingResponse<ReadyShipmentsResponse> getList(Integer pageNo, Integer pageSize, CommonBillsForm form) {
        return readyShipmentsRepository.getList(pageNo, pageSize, form);
    }
    public OrderPickResponse get(List<String> orderNumbers) {
        return readyShipmentsRepository.get(orderNumbers);
    }
    public Boolean confirmAuditByOrderNumber(String orderNumber) {
        return readyShipmentsRepository.confirmAuditByOrderNumber(orderNumber);
    }
    public Boolean tempSaveAudit(ReadyShipmentsRequest request) {
        return readyShipmentsRepository.tempSaveAudit(request);
    }
    public List<ReadyShipmentsResponse> getAllTempAuditOrder(){
        return readyShipmentsRepository.getAllTempAuditOrder();
    }
//    public Boolean delete(final List<String> orderNumberList) {
//        return readyShipmentsRepository.delete(orderNumberList);
//    }
    public ReadyShipmentsResponse getbyTempAudit(String orderNumber) {
        return readyShipmentsRepository.getbyTempAudit(orderNumber);
    }
    public boolean savePack(AddPackRequest request) {
        return readyShipmentsRepository.savePack(request);
    }
    public PagingResponse<ReadyShipmentsResponse> printEMS(int i, int j, CommonBillsForm form) {
        return readyShipmentsRepository.printEMS(i, j, form);
    }
    public Boolean updateStatus(String id, int status, Integer userId) {
        return readyShipmentsRepository.updateStatus(id,status,userId);
    }
}
