package com.fise.iot.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.Constant;
import com.fise.iot.common.annotation.Authority;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.model.DeviceLog;
import com.fise.iot.model.MQTTDto;
import com.fise.iot.model.Product;
import com.fise.iot.model.Topic;
import com.fise.iot.model.TopicSave;
import com.fise.iot.service.ProductInfoService;
import com.fise.iot.service.DeviceLogService;
import com.fise.iot.service.MessageInfoService;

@Controller
@RequestMapping("/admin/message/")
public class MessageInfoController {
	
	/**日志*/
	public static Logger logger = LoggerFactory.getLogger(MessageInfoController.class);
	
	@Value("${mqtt.publish.topic}") 
	private String publish;
	
	@Autowired
	MqttPahoMessageHandler messageHandler;
	
	@Autowired
	DeviceLogService deviceLogService;
	
	@Autowired
	private MessageInfoService messageService;
	
	@Autowired
    private ProductInfoService baseInfoService;
	
	@Authority(opCode = "0403", opName = "查询消息通信界面")
	@RequestMapping("messageinfoPage/{productId}")
	public String messagePage(@PathVariable("productId") String productId,Map<String, Object> map) {
		map.put("productId", productId);
		return "message/message_info";
	}
	
	@ControllerLog("消息通信列表")
	@RequestMapping("queryMessagePage")
	@ResponseBody
	@Authority(opCode = "0403", opName = "消息通信列表")
	public PageAjax<Topic> queryMessagePage(PageAjax<Topic> page, Topic topic) {
		return messageService.queryMessagePage(page,topic);
	}
	
	@Authority(opCode = "040302", opName = "更新消息页面")
	@RequestMapping("updateMessagePage/{id}")
	public String updateMessagePage(@PathVariable("id") int id, Map<String, Object> map) {
		//根据productId获取productKey
		Topic topic = messageService.queryMessageByID(id);
		String topicUrl=topic.getTopicUrl();
		
		String prefix=topicUrl.substring(0,topicUrl.lastIndexOf("}")+2);
		String suffix=topicUrl.substring(topicUrl.lastIndexOf("}")+2, topicUrl.length());
		
		map.put("topic", topic);
		map.put("prefix", prefix);
		map.put("suffix", suffix);
		return "message/message_update";
	}

	@ControllerLog("修改消息")
	@RequestMapping("updateMessage")
	@ResponseBody
	@Authority(opCode = "040302", opName = "修改消息")
	public AjaxResult updateMessage(Topic topic,HttpServletRequest request) {
		String prefix=request.getParameter("prefix");
		String suffix=request.getParameter("suffix");
		String topicUrl=prefix+suffix;
		topic.setTopicUrl(topicUrl);
		return messageService.updateMessage(topic);
	}
	
	@ControllerLog("删除消息")
	@RequestMapping("delMessage/{id}")
	@ResponseBody
	@Authority(opCode = "040303", opName = "删除消息")
	public AjaxResult delMessage(@PathVariable("id") int id) {
		return messageService.delMessage(id);
	}
	
	@Authority(opCode = "040304", opName = "添加topic页面")
    @RequestMapping("addMessagePage")
    public String addMessagePage(Map<String, Object> map) {
        List<Product> productlist = baseInfoService.queryAll();
        map.put("productlist", productlist);
        return "message/message_add";
    }
	
	@Authority(opCode = "040305", opName = "保存topic")
	@RequestMapping("addTopic")
	public AjaxResult addTopic(TopicSave save){
	    Topic topic = new Topic();
	    topic.setProductId(save.getProductId());
	    topic.setTopicUrl("/"+save.getProductKey()+"/${deviceName}/"+save.getTopic_suffix());
	    topic.setOperAuth(save.getOper_auth());
	    
	    return  messageService.save(topic);
	}
	
	@ControllerLog("发布消息")
	@Authority(opCode = "040306", opName = "发布消息")
	@RequestMapping("publishMessage")
	public String publishMsg(MQTTDto mqtt){
	    String topicUrl = mqtt.getTopicUrl();
	    String content = mqtt.getContent();
	    Integer qos = mqtt.getQos();
	    try {
		    messageHandler.setDefaultTopic(publish + topicUrl);
		    messageHandler.setDefaultQos(qos);
		    Message<String> message = MessageBuilder.withPayload(content).build();  
		    messageHandler.handleMessage(message);  
		    
	    	DeviceLog deviceLog = new DeviceLog();
	    	deviceLog.setProductId(mqtt.getProductId());
	    	deviceLog.setDeviceName(mqtt.getDeviceName());
	    	deviceLog.setDetail(content);
	    	deviceLog.setType(Constant.TOPIC_TYPE_UP);
	    	deviceLogService.save(deviceLog);
	    	
		} catch (Exception e) {
			return "failed";
		}
	    return "success";
	}
	
	
}
