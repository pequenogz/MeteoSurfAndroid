package com.meteosurf.meteosurf.msw;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import okhttp3.ResponseBody;

public class MSWError {

    @SerializedName("errorResponse")
    @Expose
    private ErrorResponse errorResponse;

    public MSWError() {
    }

    public MSWError(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public static MSWError fromResponseBody(ResponseBody responseBody) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(responseBody.string(), MSWError.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public MSWError withErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        return this;
    }

}
