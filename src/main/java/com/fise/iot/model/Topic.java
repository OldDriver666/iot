package com.fise.iot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 
 */
public class Topic implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品id
     */
    private String productId;

    /**
     * topic类
     */
    private String topicUrl;

    /**
     * 操作权限(1-订阅,2-发布,3-订阅和发布)
     */
    private Integer operAuth;

    /**
     * 描述
     */
    private String topicDesc;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String updator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public Integer getOperAuth() {
        return operAuth;
    }

    public void setOperAuth(Integer operAuth) {
        this.operAuth = operAuth;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "Topic [id=" + id + ", productId=" + productId + ", topicUrl=" + topicUrl + ", operAuth=" + operAuth
				+ ", topicDesc=" + topicDesc + ", creator=" + creator + ", updator=" + updator + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}