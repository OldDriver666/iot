package com.fise.iot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.annotation.Authority;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.model.Product;
import com.fise.iot.service.BaseInfoService;

/**
 *设备基本信息controller
 */
@Controller
@RequestMapping("/admin/product/")
public class BaseInfoController {
	/**日志*/
	public static Logger logger = LoggerFactory.getLogger(BaseInfoController.class);
	
	@Autowired
	private BaseInfoService baseInfoService;
	
	@Authority(opCode = "0401", opName = "产品基本信息界面")
	@RequestMapping("baseinfoPage")
	public String filePage() {
		return "product/base_info";
	}
	
	@ControllerLog("查询产品列表")
	@RequestMapping("queryProductPage")
	@ResponseBody
	@Authority(opCode = "0401", opName = "查询产品列表")
	public PageAjax<Product> queryFilePage(PageAjax<Product> page, Product product) {
		return baseInfoService.queryProductPage(page, product);
	}

}
