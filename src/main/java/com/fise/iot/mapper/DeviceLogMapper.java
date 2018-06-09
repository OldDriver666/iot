package com.fise.iot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fise.iot.common.dao.MyMapper;
import com.fise.iot.model.DeviceLog;

public interface DeviceLogMapper  extends MyMapper<DeviceLog>{
	List<DeviceLog> queryList(@Param("deviceLog")DeviceLog deviceLog);
}