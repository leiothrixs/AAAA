package com.ezworking.wechatunlock.domain;

import java.io.Serializable;

public class ResultBase implements Serializable{

    /**
     * isSuccess	是否成功	boolean	@mock=true
     responseCode	响应返回码	number	@mock=100
     responseMsg*/

    public boolean isSuccess;
    private int responseCode = -1;
    private String responseMsg;
    private int numberLeft;
    private int isSigned;

    public int getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(int isSigned) {
        this.isSigned = isSigned;
    }

    /**
     * responseCode.
     *
     * @return the responseCode
     * @since JDK 1.6
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * responseCode.
     *
     * @param responseCode the responseCode to set
     * @since JDK 1.6
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * responseMsg.
     *
     * @return the responseMsg
     * @since JDK 1.6
     */
    public String getResponseMsg() {
        return responseMsg;
    }

    /**
     * responseMsg.
     *
     * @param responseMsg the responseMsg to set
     * @since JDK 1.6
     */
    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public int getNumberLeft() {
        return numberLeft;
    }

    public void setNumberLeft(int numberLeft) {
        this.numberLeft = numberLeft;
    }

}
