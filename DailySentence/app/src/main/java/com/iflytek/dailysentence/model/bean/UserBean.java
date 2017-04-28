package com.iflytek.dailysentence.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * @author ghcui
 * @time 2017/1/11
 */
public class UserBean extends RealmObject implements Serializable{
    public String id;
    public String account;
    public String password;
    public String user_code;
    public String real_name;//
    public String mobile;
    public String position = "";
    public String role_code;
    public String area_code="";
    public String name="";


}
