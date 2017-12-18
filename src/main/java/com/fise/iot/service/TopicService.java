package com.fise.iot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.mapper.TopicMapper;
import com.fise.iot.model.Topic;
import com.fise.iot.model.TopicExample;

@Service
public class TopicService extends AbstratService<Topic> {
	@Autowired
	private TopicMapper topicMapper;
	
	public Topic queryTopicByID(int id) {
		return topicMapper.selectByPrimaryKey(id);
	}
	
	public List<Topic> queryTopicByDeviceId(String deviceId){
		TopicExample  example =new TopicExample();
		TopicExample.Criteria criteria=example.createCriteria();
		//criteria.andDeviceIdEqualTo(deviceId);
		return topicMapper.selectByExample(example);
	}
	
	public List<Topic> queryTopicPage(String productId,String deviceName){
		TopicExample  example =new TopicExample();
		TopicExample.Criteria criteria=example.createCriteria();
		criteria.andProductIdEqualTo(productId);
		List<Topic> topics=topicMapper.selectByExample(example);
		List<Topic> topicList=new ArrayList<Topic>();
		for(Topic topic:topics){
			 Topic top=new Topic();
		     String url=topic.getTopicUrl().trim();
		     StringBuffer buff=new StringBuffer(url);
		     buff.replace(url.indexOf("$"), url.lastIndexOf("}")+1, deviceName);
		     top.setTopicUrl(buff.toString());
		     top.setOperAuth(topic.getOperAuth());
		     top.setMessageNum( topic.getMessageNum());
		     topicList.add(top);
		}
		
		return topicList;
	}
}
