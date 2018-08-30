package com.gwg.shiro.web.vo;

import lombok.Data;

import java.io.Serializable;
public class CusMapVo implements Serializable{

    //key
    private String cusKey;

    //value
    private String cusValue;

    public String getCusKey() {
        return cusKey;
    }

    public void setCusKey(String cusKey) {
        this.cusKey = cusKey;
    }

    public String getCusValue() {
        return cusValue;
    }

    public void setCusValue(String cusValue) {
        this.cusValue = cusValue;
    }
}
