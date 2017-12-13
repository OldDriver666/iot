package com.fise.iot.controller;

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
import com.fise.iot.service.DeviceInfoService;

/**
 *设备基本信息controller
 */
@Controller
@RequestMapping("/admin/device/")
public class DeviceInfoController {
	/**日志*/
	public static Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);
	
	@Autowired
	private DeviceInfoService deviceService;
	
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
