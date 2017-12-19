package com.fise.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fise.iot.common.annotation.Authority;

@Controller
@RequestMapping("/admin/log/")
public class DeviceLogController {
	
	@Authority(opCode = "0405", opName = "设备日志管理界面")
	@RequestMapping("deviceLogPage")
	public String deviceLogPage(){
		return "log/device_log";
	}
	
	
	//设备行为分析。

}
