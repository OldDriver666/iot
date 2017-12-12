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
import com.fise.iot.model.IFile;
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
	public PageAjax<Product> queryProductPage(PageAjax<Product> page, Product product) {
		return baseInfoService.queryProductPage(page, product);
	}
	
	@Authority(opCode = "040102", opName = "更新产品页面")
	@RequestMapping("updateInfoPage/{id}")
	public String updateInfoPage(@PathVariable("id") int id, Map<String, Object> map) {
		Product product = baseInfoService.queryProductByID(id);
		map.put("product", product);
		return "product/info_update";
	}

	@ControllerLog("修改产品")
	@RequestMapping("updateProduct")
	@ResponseBody
	@Authority(opCode = "040102", opName = "修改文件")
	public AjaxResult updateProduct(Product product) {
		return baseInfoService.updateProduct(product);
	}
	
	@ControllerLog("删除产品")
	@RequestMapping("delProduct/{id}")
	@ResponseBody
	@Authority(opCode = "040103", opName = "删除产品")
	public AjaxResult delProduct(@PathVariable("id") int id) {
		return baseInfoService.delProduct(id);
	}

}
