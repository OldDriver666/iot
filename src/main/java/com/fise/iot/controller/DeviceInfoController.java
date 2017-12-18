package com.fise.iot.controller;

import java.util.List;
import java.util.Map;

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
import com.fise.iot.model.Device;
import com.fise.iot.model.Topic;
import com.fise.iot.service.DeviceInfoService;
import com.fise.iot.service.TopicService;

/**
 * 设备基本信息controller
 */
@Controller
@RequestMapping("/admin/device/")
public class DeviceInfoController {
	/** 日志 */
	public static Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

	@Autowired
	private DeviceInfoService deviceService;

	@Autowired
	private TopicService topicService;

	@Authority(opCode = "0402", opName = "设备基本信息界面")
	@RequestMapping("deviceinfoPage")
	public String devicePage() {
		return "device/device_info";
	}

	@ControllerLog("查询设备列表")
	@RequestMapping("queryDevicePage")
	@ResponseBody
	@Authority(opCode = "0402", opName = "查询设备列表")
	public PageAjax<Device> queryDevicePage(PageAjax<Device> page, Device device) {
		return deviceService.queryDevicePage(page, device);
	}

	@Authority(opCode = "040201", opName = "更新设备页面")
	@RequestMapping("updateDevicePage/{id}")
	public String updateDevicePage(@PathVariable("id") int id, Map<String, Object> map) {
		Device device = deviceService.queryDeviceByID(id);
		map.put("device", device);
		return "device/device_update";
	}

	@ControllerLog("修改设备")
	@RequestMapping("updateDevice")
	@ResponseBody
	@Authority(opCode = "040201", opName = "修改设备")
	public AjaxResult updateDevice(Device device) {
		return deviceService.updateDevice(device);
	}

	@Authority(opCode = "040202", opName = "Topic列表页面")
	@RequestMapping("topicListPage/{id}")
	public String topicListPage(@PathVariable("id") int id, Map<String, Object> map) {
		map.put("id", id);
		return "device/topic_list";
	}

	@ControllerLog("Topic列表页面")
	@RequestMapping("topicResultPage/{id}")
	@ResponseBody
	@Authority(opCode = "040202", opName = "Topic列表页面")
	public PageAjax<Topic> topicResultPage(@PathVariable("id") int id) {
		// 根据id查出该设备的信息
		Device device = deviceService.queryDeviceByID(id);
		String deviceName = device.getDeviceName();
		// 根据其中的productId查出所有的Topic，替换其中的devicename
		String productId = device.getProductId();
		List<Topic> topicList = topicService.queryTopicPage(productId, deviceName);
		
		return new PageAjax<Topic>(topicList);
		
	}

	@ControllerLog("Topic列表")
	@RequestMapping("topicList")
	@ResponseBody
	@Authority(opCode = "040202", opName = "Topic列表")
	public AjaxResult topicList(Topic topic) {
		return null;
		// return deviceService.updateDevice(device);
	}

	@ControllerLog("删除设备")
	@RequestMapping("delDevice/{id}")
	@ResponseBody
	@Authority(opCode = "040203", opName = "删除设备")
	public AjaxResult delDevice(@PathVariable("id") int id) {
		return deviceService.delDevice(id);
	}

	@Authority(opCode = "040204", opName = "添加设备页面")
	@RequestMapping("addDevicePage")
	public String addDevicePage(Map<String, Object> map) {
		return "device/device_add";
	}

	@ControllerLog("添加设备")
	@RequestMapping("addDevice")
	@ResponseBody
	@Authority(opCode = "040204", opName = "添加设备")
	public AjaxResult addDevice(Device device) {
		return deviceService.addDevice(device);
	}
}
