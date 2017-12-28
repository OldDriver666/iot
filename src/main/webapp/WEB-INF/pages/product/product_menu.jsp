<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div>
	<div>
		<li class="mm-dropdown" opCode="04">
			<ul>
				<!-- <li opCode="0401"><a tabindex="-1"
					href="javascript:goPage('admin/product/baseinfoPage')"><span
						class="mm-text">基本信息</span></a></li> -->
				<li opCode="0402"><a tabindex="-1"
					href="javascript:goPage('admin/device/deviceinfoPage/${productId}')"><span
						class="mm-text">设备管理</span></a></li>
				<li opCode="0403"><a tabindex="-1"
					href="javascript:goPage('admin/message/messageinfoPage/${productId}')"><span
						class="mm-text">消息通信</span></a></li>
				<li opCode="0404"><a tabindex="-1"
					href="javascript:goPage('admin/tech/articlePage')"><span
						class="mm-text">服务端订阅</span></a></li>
				<li opCode="0405"><a tabindex="-1"
					href="javascript:goPage('admin/log/deviceLogPage/${productId}')"><span
						class="mm-text">日志服务</span></a></li>
			</ul>
		</li>
		</ul>
	</div>
</div>
