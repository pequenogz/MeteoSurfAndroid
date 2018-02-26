package com.meteosurf.meteosurf.msw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("errorMsg")
    @Expose
    private String errorMsg;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ErrorResponse withCode(int code) {
        this.code = code;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ErrorResponse withErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

}
