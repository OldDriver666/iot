package com.fise.iot.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.iot.common.pojo.PageAjax;
import com.fise.iot.common.utils.AppUtil;
import com.fise.iot.common.utils.DateUtil;
import com.fise.iot.common.utils.StringUtil;
import com.fise.iot.mapper.DeviceLogMapper;
import com.fise.iot.mapper.ProductMapper;
import com.fise.iot.model.AuthUser;
import com.fise.iot.model.DeviceLog;
import com.fise.iot.model.DeviceLogExample;
import com.fise.iot.model.Product;
import com.fise.iot.model.ProductExample;
import com.github.pagehelper.page.PageMethod;

@Service
public class DeviceLogService extends AbstratService<DeviceLog> {

	@Autowired
	private DeviceLogMapper logMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	public PageAjax<DeviceLog> queryDeviceLogPage(PageAjax<DeviceLog> page, String productId, DeviceLog deviceLog, String time) {
		
		ProductExample example1=new ProductExample();
		ProductExample.Criteria criteria1=example1.createCriteria();
		criteria1.andProductIdEqualTo(productId);
		List<Product> listProduct = productMapper.selectByExample(example1);
		deviceLog.setProductKey(listProduct.get(0).getProductKey());
		
		PageMethod.startPage(page.getPageNo(), page.getPageSize());
		List<DeviceLog> list = logMapper.queryList(deviceLog);
		return AppUtil.returnPage(list);
	}

}
