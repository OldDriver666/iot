package com.fise.iot.model;

import java.io.Serializable;

public class TopicSave implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private String productId;
    private String productKey;
    private String topic_suffix;
    private Integer oper_auth;
    private String productDesc;
    
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductKey() {
        return productKey;
    }
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }
    public String getTopic_suffix() {
        return topic_suffix;
    }
    public void setTopic_suffix(String topic_suffix) {
        this.topic_suffix = topic_suffix;
    }
    public Integer getOper_auth() {
        return oper_auth;
    }
    public void setOper_auth(Integer oper_auth) {
        this.oper_auth = oper_auth;
    }
    public String getProductDesc() {
        return productDesc;
    }
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

}
