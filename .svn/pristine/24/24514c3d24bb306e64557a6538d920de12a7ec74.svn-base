package com.siping.smartone.ec.response;

import java.io.Serializable;

public class EcResponse<T> implements Serializable {
    private static final long serialVersionUID = -5931083439107278282L;
    private String status;
    private T result;
    private Integer errorCode;
    private String errorMessage;

    public static <T> EcResponse<T> success(T result) {
        EcResponse<T> response = new EcResponse<T>();
        response.setStatus("success");
        response.setResult(result);
        response.setErrorCode(0);
        return response;
    }

    public static <T> EcResponse<T> fail(Integer errorCode, String errorMessage) {
        EcResponse<T> response = new EcResponse<T>();
        response.setStatus("fail");
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
