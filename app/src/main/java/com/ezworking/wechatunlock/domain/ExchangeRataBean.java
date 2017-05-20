package com.ezworking.wechatunlock.domain;

/**
 * Created by sxj on 2017/5/20.
 */
public class ExchangeRataBean extends ResultBean {

    public String qqRate;
    public String cashRate;
    public String qqNum;
    public String cashNum;

    public String getQqRate() {
        return qqRate;
    }

    public void setQqRate(String qqRate) {
        this.qqRate = qqRate;
    }

    public String getCashRate() {
        return cashRate;
    }

    public void setCashRate(String cashRate) {
        this.cashRate = cashRate;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public String getCashNum() {
        return cashNum;
    }

    public void setCashNum(String cashNum) {
        this.cashNum = cashNum;
    }


}
