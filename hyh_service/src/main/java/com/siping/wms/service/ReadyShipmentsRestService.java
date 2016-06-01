package com.siping.wms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.inventory.repository.InventoryShipmentsRestRepository;
import com.siping.service.material.repository.MaterialRestRepository;
import com.siping.service.report.repository.InventoryReportRestRepository;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.inventory.request.AddInventoryShipmentsItemRequest;
import com.siping.smartone.inventory.request.AddInventoryShipmentsRequest;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsItemRequest;
import com.siping.smartone.invoicing.sell.shipments.request.AddSellShipmentsRequest;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.report.response.SingleMaterialCostResponse;
import com.siping.smartone.wms.request.AddPackItemRequest;
import com.siping.smartone.wms.request.AddPackRequest;
import com.siping.smartone.wms.request.ESaleOrderRequest;
import com.siping.smartone.wms.request.OrderPickQueryParam;
import com.siping.smartone.wms.request.ReadyShipmentsRequest;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsItemResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.wms.repository.PdaRestRepository;
import com.siping.wms.repository.ReadyShipmentsRestRepository;

@Service
public class ReadyShipmentsRestService extends DBSwitch {
    @Autowired
    private ReadyShipmentsRestRepository readyShipmentsRestRepository;
    @Autowired
    private InventoryShipmentsRestRepository inventoryShipmentsRestRepository;
    @Autowired
    private PdaRestRepository pdaRestRepository;
    @Autowired
    private MaterialRestRepository materialRestRepository;
    @Autowired
    private InventoryReportRestRepository inventoryReportRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(ESaleOrderRequest request) {
        return readyShipmentsRestRepository.add(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ReadyShipmentsResponse get(String orderNumber) {
        return readyShipmentsRestRepository.get(orderNumber);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<ReadyShipmentsResponse> getList(CommonBillsRequest request) {
        return readyShipmentsRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public OrderPickResponse get(OrderPickQueryParam request) {
        return readyShipmentsRestRepository.get(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean confirmAuditByOrderNumber(String ordernumber) {
        return readyShipmentsRestRepository.confirmAuditByOrderNumber(ordernumber);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean tempSaveAudit(ReadyShipmentsRequest request) {
        return readyShipmentsRestRepository.tempSaveAudit(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<ReadyShipmentsResponse> getAllTempAuditOrder() {
        return readyShipmentsRestRepository.getAllTempAuditOrder();
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public ReadyShipmentsResponse getbyTempAudit(String orderNumber) {
        return readyShipmentsRestRepository.getbyTempAudit(orderNumber);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean savePack(AddPackRequest request) {
        AddInventoryShipmentsRequest inventoryShipmentsRequest = assembleInventoryShipmentsRequest(request);//生成库存发货单
        AddSellShipmentsRequest sellShipmentsRequest = assembleAddSellShipmentsRequest(request);//生成销售发货单
        checkSavePackWeight(sellShipmentsRequest,request);//检查重量差异,如果出现异常，无法执行到下面;
        inventoryShipmentsRestRepository.add(inventoryShipmentsRequest);
        try {
            readyShipmentsRestRepository.changeReadyShipmentsStatus(5, request.getOrderNumber());
            readyShipmentsRestRepository.changeReadyShipmentsWeight(Double.valueOf(request.getPackWeight()),request.getOrderNumber());
        } catch (Exception e) { 
            throw new BusinessProcessException("包装成功后修改预备发货单失败!");
        }
        //sellShipmentsRestRepository.add(sellShipmentsRequest);不用生成销售发货单了,同时也不会改变库存了
        return Boolean.TRUE;
    }
    
    private void checkSavePackWeight(AddSellShipmentsRequest sellShipmentsRequest,AddPackRequest packRequest){
        Double totalMaterialWeight = 0.0;//包含包装在内的总质量
        Double minMaterialWeight = 0.0;
        Double tempMaterialWeight = 0.0;
        for(AddPackItemRequest packItem: packRequest.getPackList()){
            try {
                totalMaterialWeight = totalMaterialWeight + Double.valueOf(packItem.getNumber())*materialRestRepository.get(packItem.getMaterialId(), null, null).getWeight();//计算包装重量
            } catch (Exception e) {
                throw new BusinessProcessException("计算包装重量时出错");
            }
            
        }
        for(AddSellShipmentsItemRequest sellShipmentsItem:sellShipmentsRequest.getShipmentsItemList()){
            try {
                tempMaterialWeight = materialRestRepository.get(sellShipmentsItem.getMaterialId(), null, null).getWeight();
            } catch (Exception e) {
                throw new BusinessProcessException("取得单据商品重量时出错");
            }
            if(minMaterialWeight == 0.0){
                minMaterialWeight = tempMaterialWeight;
            }
            else if(minMaterialWeight>tempMaterialWeight){
                minMaterialWeight = tempMaterialWeight;
            }
            totalMaterialWeight = totalMaterialWeight + sellShipmentsItem.getCounts()*tempMaterialWeight;//之前算好的包装总质量加上销售商品的总质量
        }
        if(!StringUtils.hasText(packRequest.getPackWeight())){
            throw new BusinessProcessException("请输入包装的称重质量");
        }
        if(Double.valueOf(packRequest.getPackWeight())-totalMaterialWeight>=minMaterialWeight){
            throw new BusinessProcessException("包装过重");
        }
        if(totalMaterialWeight - Double.valueOf(packRequest.getPackWeight())>=minMaterialWeight){
            throw new BusinessProcessException("包装过轻");
        }
    }

    private AddInventoryShipmentsRequest assembleInventoryShipmentsRequest(AddPackRequest request) {
        List<AddInventoryShipmentsItemRequest> inventoryShipmentitems = new ArrayList<AddInventoryShipmentsItemRequest>();
        Double totalPrice = 0.0;//整张库存发货单的价格
        for (AddPackItemRequest packItem : request.getPackList()) {
            List<GetMaterialBatchResponse> materialBatchResponses = pdaRestRepository.getStorageLocationWithMaterialBatchByMaterial(packItem.getMaterialId());
            Double sumCounts = 0.0;
            Double needCounts = Double.valueOf(packItem.getNumber());
            Double packNumber = Double.valueOf(packItem.getNumber());
            for (GetMaterialBatchResponse tempMaterialBatchResponse : materialBatchResponses) {
                if ("0".equals(tempMaterialBatchResponse.getStorageId())) {// 所有耗材只能从虚拟仓库出货
                    Double counts = Double.valueOf(tempMaterialBatchResponse.getCounts());
                    if (needCounts <= counts) {
                        AddInventoryShipmentsItemRequest inventoryShipmentitem = new AddInventoryShipmentsItemRequest();
                        inventoryShipmentitem.setPurchasePrice(Double.valueOf(tempMaterialBatchResponse.getPrice()));
                        inventoryShipmentitem.setCounts(needCounts);
                        inventoryShipmentitem.setMaterialId(Integer.valueOf(tempMaterialBatchResponse.getMaterialId()));
                        inventoryShipmentitem.setProductionDate(tempMaterialBatchResponse.getProductionDate());
                        inventoryShipmentitem.setSellPrice(getCostOfMaterial(tempMaterialBatchResponse.getMaterialId(),"0"));//耗材的成本是单独取的，并且所有耗材只能从虚拟仓库发货
                        inventoryShipmentitem.setBatchNumber(tempMaterialBatchResponse.getBatchNumber());
                        //inventoryShipmentitem.setRemark("通过单号为‘" + request.getOrderNumber() + "’预发货单生成的耗材的库存发货单");//明细无需记录
                        inventoryShipmentitem.setTotal(inventoryShipmentitem.getSellPrice()*needCounts);
                        inventoryShipmentitems.add(inventoryShipmentitem);
                        totalPrice = totalPrice + inventoryShipmentitem.getTotal();//得到库存发货单总价
                        needCounts = 0.0;
                        break;
                    } else if(counts > 0.0){//查询出来的库存量可以是负数,所以这里一定要加上验证
                        sumCounts = sumCounts + counts;
                        AddInventoryShipmentsItemRequest inventoryShipmentitem = new AddInventoryShipmentsItemRequest();
                        inventoryShipmentitem.setPurchasePrice(Double.valueOf(tempMaterialBatchResponse.getPrice()));
                        inventoryShipmentitem.setCounts(counts);
                        inventoryShipmentitem.setMaterialId(Integer.valueOf(tempMaterialBatchResponse.getMaterialId()));
                        inventoryShipmentitem.setProductionDate(tempMaterialBatchResponse.getProductionDate());
                        inventoryShipmentitem.setSellPrice(getCostOfMaterial(tempMaterialBatchResponse.getMaterialId(),"0"));//耗材的成本是单独取的，并且所有耗材只能从虚拟仓库发货
                        inventoryShipmentitem.setBatchNumber(tempMaterialBatchResponse.getBatchNumber());
                        //inventoryShipmentitem.setRemark("通过单号为‘" + request.getOrderNumber() + "’预发货单生成的耗材的库存发货单");
                        inventoryShipmentitem.setTotal(inventoryShipmentitem.getSellPrice()*inventoryShipmentitem.getCounts());
                        inventoryShipmentitems.add(inventoryShipmentitem);
                        totalPrice = totalPrice + inventoryShipmentitem.getTotal();//得到库存发货单总价
                        needCounts = packNumber - sumCounts;
                    }
                }
            }
            if (needCounts != 0.0) {
                throw new BusinessProcessException("系统中记录的耗材'" + packItem.getMaterialName() + "'数量不足,无法生成耗材发货单,还需要'" + needCounts + "'");
            }
        }
        AddInventoryShipmentsRequest inventoryShipment = new AddInventoryShipmentsRequest();
        inventoryShipment.setOutboundStorage("0");//耗材的出库仓库应该设置为虚拟仓库，这个仓库的值好像和销售出库的仓库是一样的，所以这里需要修改
        inventoryShipment.setIsDraft(false);
        inventoryShipment.setShipmentsItemList(inventoryShipmentitems);// 需要哪些参数可以在这里计算
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        inventoryShipment.setBillsDate(sdf.format(new Date()));
        inventoryShipment.setCreateBy(Integer.valueOf(request.getLoginId()));
        inventoryShipment.setGiftPrice(0.0);
        inventoryShipment.setOwner(request.getLoginId());
        inventoryShipment.setSummary("通过单号为‘" + request.getOrderNumber() + "’预发货单生成的耗材的库存发货单");
        inventoryShipment.setTotalPrice(totalPrice);
        return inventoryShipment;
    }

    private Double getCostOfMaterial(String materialId,String storageId){
        List<SingleMaterialCostResponse> allCostOfEachStorage= inventoryReportRestRepository.getCost(materialId);
        for(SingleMaterialCostResponse tempResponse: allCostOfEachStorage){
            if(String.valueOf(tempResponse.getStorageId()).equals(storageId)){
                return tempResponse.getCost();
            }
        }
        throw new BusinessProcessException("未查询到耗材的成本,请联系管理员!");
    }
    
    private AddSellShipmentsRequest assembleAddSellShipmentsRequest(AddPackRequest request) {
        AddSellShipmentsRequest sellShipmentsRequest = new AddSellShipmentsRequest();
        List<AddSellShipmentsItemRequest> sellShipmentsItems = new ArrayList<AddSellShipmentsItemRequest>();
        ReadyShipmentsResponse response = readyShipmentsRestRepository.get(request.getOrderNumber());
        sellShipmentsRequest.setFromBillsNo(response.getOrderNumber());
        sellShipmentsRequest.setOutboundStorage(response.getStorageId().toString());
        sellShipmentsRequest.setIsDraft(false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sellShipmentsRequest.setBillsDate(sdf.format(new Date()));
        sellShipmentsRequest.setCreateBy(request.getLoginId());
        sellShipmentsRequest.setFavorablePrice(response.getFavorablePrice());
        sellShipmentsRequest.setPartnerId(response.getPartenerId()!=null?response.getPartenerId().toString():null);
        sellShipmentsRequest.setSummary("通过单号为‘" + request.getOrderNumber() + "’预发货单生成的耗材的销售发货单");
        sellShipmentsRequest.setGiftPrice(response.getGiftPrice());
        sellShipmentsRequest.setOwner(request.getLoginId());
        sellShipmentsRequest.setTotalPrice(response.getTotalPrice());
        for (ReadyShipmentsItemResponse readyShipmentsItem : response.getItems()) {
            AddSellShipmentsItemRequest sellShipmentsItem = new AddSellShipmentsItemRequest();
            sellShipmentsItem.setBatchNumber(readyShipmentsItem.getBatchNumber());
            sellShipmentsItem.setMaterialId(readyShipmentsItem.getMaterialId().toString());
            if(null==readyShipmentsItem.getConfirmAmount()){
                throw new BusinessProcessException("此预发货单存在数量为空的条目,请修改！");
            }
            sellShipmentsItem.setCounts(Double.valueOf(readyShipmentsItem.getConfirmAmount()));
            sellShipmentsItem.setProductionDate(readyShipmentsItem.getProductionDate());
            sellShipmentsItem.setPurchasePrice(readyShipmentsItem.getPurchasePrice());
            sellShipmentsItem.setSellPrice(readyShipmentsItem.getSellPrice());
            sellShipmentsItem.setStorageLocationId(readyShipmentsItem.getStorageLocationId());
            //sellShipmentsItem.setRemark("通过单号为‘" + request.getOrderNumber() + "’预发货单生成的销售发货单");
            sellShipmentsItem.setDiscount(readyShipmentsItem.getDiscount());
            sellShipmentsItem.setTax(readyShipmentsItem.getTax());
            sellShipmentsItem.setTaxRate(readyShipmentsItem.getTaxRate());
            sellShipmentsItem.setTotal(readyShipmentsItem.getTotal());
            sellShipmentsItem.setAfterDiscount(readyShipmentsItem.getAfterDiscount());
            sellShipmentsItems.add(sellShipmentsItem);
        }
        sellShipmentsRequest.setShipmentsItemList(sellShipmentsItems);
        return sellShipmentsRequest;
    }

    public PagingResponse<ReadyShipmentsResponse> printEMS(CommonBillsRequest request) {
        return readyShipmentsRestRepository.printEMS(request);
    }

    public Boolean updateStatus(String id, Integer status, Integer userId) {
        return readyShipmentsRestRepository.updateStatus(id,status,userId);
    }
}
