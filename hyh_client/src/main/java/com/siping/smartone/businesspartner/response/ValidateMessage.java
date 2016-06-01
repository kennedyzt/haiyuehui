package com.siping.smartone.businesspartner.response;

public class ValidateMessage {
    public boolean isPass;
    public Integer rowIndex;
    public Integer cellIndex;
    public String message;
    public boolean isRequiredError;

    public ValidateMessage() {
        isPass = true;
    }

    public ValidateMessage(Integer rowIndex, Integer cellIndex, String message) {
        isPass = false;
        this.rowIndex = rowIndex;
        this.cellIndex = cellIndex;
        this.message = message;
    }

    public ValidateMessage(Integer rowIndex, Integer cellIndex, String message, boolean isRequiredError) {
        isPass = false;
        this.rowIndex = rowIndex;
        this.cellIndex = cellIndex;
        this.message = message;
        this.isRequiredError = isRequiredError;
    }

    public boolean isPass() {
        return isPass;
    }

    public boolean isRequiredError() {
        return isRequiredError;
    }
}
