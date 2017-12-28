<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="left-col col-md-3">
	<div class="list-group">
			<a class="list-group-item",
				href="javascript:goPage('admin/product/productinfoPage')"><h5 class="list-group-item-heading">基本信息</h5></a> 
			<a class="list-group-item",
				href="javascript:goPage('admin/device/deviceinfoPage/${id}')"><h5 class="list-group-item-heading">设备管理</h5></a> 
			<a class="list-group-item",
				href="javascript:goPage('admin/message/messageinfoPage')"><h5 class="list-group-item-heading">消息通信</h5></a> 
			<a  class="list-group-item",
				href="javascript:goPage('admin/log/deviceLogPage')"><h5 class="list-group-item-heading">日志服务</h5></a>
		</ul>
	</div>
</div>