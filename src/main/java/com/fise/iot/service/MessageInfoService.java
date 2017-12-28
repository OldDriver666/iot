package com.fise.iot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.common.annotation.ServiceLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.common.utils.AppUtil;
import com.fise.iot.common.utils.DateUtil;
import com.fise.iot.common.utils.StringUtil;
import com.fise.iot.mapper.ProductMapper;
import com.fise.iot.mapper.TopicMapper;
import com.fise.iot.model.Product;
import com.fise.iot.model.ProductExample;
import com.fise.iot.model.Topic;
import com.fise.iot.model.TopicExample;
import com.github.pagehelper.page.PageMethod;

@Service
public class MessageInfoService extends AbstratService<Topic>{
	
	@Autowired
	private TopicMapper topicMapper;
	
	@Autowired
	private ProductMapper productMapper;

	@ServiceLog("消息通信列表")
	public PageAjax<Topic> queryMessagePage(PageAjax<Topic> page, Topic topic) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		
		TopicExample example=new TopicExample();
		TopicExample.Criteria criteria=example.createCriteria();
		if(!StringUtil.isEmpty(topic.getProductId())){
			criteria.andProductIdLike(topic.getProductId());
		}
		
		List<Topic> list = topicMapper.selectByExample(example);
		return AppUtil.returnPage(list);
	}
	
	public Topic queryMessageByID(int id) {
		return topicMapper.selectByPrimaryKey(id);
	}	
	
//	public String modifyTopicUrl(String productId,String topicUrl){
//		ProductExample example=new ProductExample();
//		ProductExample.Criteria criteria=example.createCriteria();
//		criteria.andProductIdEqualTo(productId);
//		List<Product> products = productMapper.selectByExample(example);
//		Product product = products.get(0);
//	    String  productKey=product.getProductKey();
//	    String topicUrlNew="/"+productKey+"/${deviceName}/";
//		return productKey;
//	}

	@ServiceLog("删除消息")
	public AjaxResult delMessage(int id) {
		Topic topic = queryMessageByID(id);
		if(null != topic){
			topicMapper.deleteByPrimaryKey(id);
		}
		return AppUtil.returnObj(null);
	}
	
	@ServiceLog("更新消息")
	public AjaxResult updateMessage(Topic topic) {
		//topic.setUpdateTime(DateUtil.getCurDateTime());
		topic.setUpdator("admin");
		topic.setUpdateTime(DateUtil.getCurDateTime());
		topicMapper.updateByPrimaryKeySelective(topic);
		return AppUtil.returnObj(null);
	}
	
	public AjaxResult addMessage(Topic topic) {
	     topicMapper.insert(topic);
        return AppUtil.returnObj(null);
    }
	
}
