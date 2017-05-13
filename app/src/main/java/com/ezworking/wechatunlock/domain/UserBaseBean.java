package com.ezworking.wechatunlock.domain;

import java.io.Serializable;

/**
 * Created by dujiande on 2016/12/16.
 * 个人基本信息结构
 */
public class UserBaseBean implements Serializable{

    private String uAvatar;
    private String uID;
    private String uName;
    private String uEmail;
    private String uTel;

    public String getuTel() {
        return uTel;
    }

    public void setuTel(String uTel) {
        this.uTel = uTel;
    }

    public String getuAvatar() {
        return uAvatar;
    }

    public void setuAvatar(String uAvatar) {
        this.uAvatar = uAvatar;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}
