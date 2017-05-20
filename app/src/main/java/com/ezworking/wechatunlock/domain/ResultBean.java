package com.ezworking.wechatunlock.domain;

import java.io.Serializable;

/**
 * Created by sxj on 2017/5/18.
 */
public class ResultBean implements Serializable{
    public boolean isSuccess;
    private String errorMsg ;
    private String errorCode;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
