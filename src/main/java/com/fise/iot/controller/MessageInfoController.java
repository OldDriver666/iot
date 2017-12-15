package com.fise.iot.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.annotation.Authority;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.common.pojo.AjaxResult;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.model.Topic;
import com.fise.iot.service.MessageInfoService;

@Controller
@RequestMapping("/admin/message/")
public class MessageInfoController {
	/**日志*/
	public static Logger logger = LoggerFactory.getLogger(MessageInfoController.class);
	
	@Autowired
	private MessageInfoService messageService;
	
	@Authority(opCode = "0403", opName = "查询消息通信界面")
	@RequestMapping("messageinfoPage")
	public String messagePage() {
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
	
}
