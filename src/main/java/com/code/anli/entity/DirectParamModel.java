package com.code.anli.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @ClassName DirectParamModel
 * @Description TODO
 * @Author Administrator
 * @Date 2019/12/4 13:26
 * @Version 1.0
 **/
public class DirectParamModel {

    private JSONObject json;
    private List<TOrder> orders;


    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public List<TOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<TOrder> orders) {
        this.orders = orders;
    }
}
