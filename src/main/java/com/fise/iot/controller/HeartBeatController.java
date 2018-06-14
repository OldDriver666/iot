package com.fise.iot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 心跳监测Controller
 */
@RestController
@RequestMapping("/heartbeat/")
public class HeartBeatController {
	
	@RequestMapping("helloworld")
	public String Helloworld() {
		return "helloworld";
	}
}
