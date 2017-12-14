package com.fise.iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.mapper.TopicMapper;
import com.fise.iot.model.Topic;
import com.fise.iot.model.TopicExample;

@Service
public class TopicService extends AbstratService<Topic> {
	@Autowired
	private TopicMapper topicMapper;
	
//	public Topic queryTopicByID(int id) {
//		return topicMapper.selectByPrimaryKey(id);
//	}
	
	public List<Topic> queryTopicByDeviceId(String deviceId){
		TopicExample  example =new TopicExample();
		TopicExample.Criteria criteria=example.createCriteria();
		//criteria.andDeviceIdEqualTo(deviceId);
		return topicMapper.selectByExample(example);
	}

}
