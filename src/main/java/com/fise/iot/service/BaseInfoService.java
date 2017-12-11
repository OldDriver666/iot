package com.fise.iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.common.annotation.ServiceLog;
import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.common.utils.AppUtil;
import com.fise.iot.mapper.ProductMapper;
import com.fise.iot.model.Product;
import com.github.pagehelper.page.PageMethod;

@Service
public class BaseInfoService extends AbstratService<Product> {
	
	@Autowired
	private ProductMapper productMapper;

	@ServiceLog("查询产品列表")
	public PageAjax<Product> queryProductPage(PageAjax<Product> page, Product product) {
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<Product> list = productMapper.selectAll();
		return AppUtil.returnPage(list);
	}


}
