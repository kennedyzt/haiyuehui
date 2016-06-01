package com.siping.smartone.inventory.request;

import java.io.Serializable;

public class ItemReceiptCountsRequest implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Double receiptCounts;
    private Integer rowNumber;
    private String batchNumber;
    private String productionDate;

    public Double getReceiptCounts() {
        return receiptCounts;
    }

    public void setReceiptCounts(Double receiptCounts) {
        this.receiptCounts = receiptCounts;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

}
