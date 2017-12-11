<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<form id="submitForm" class="form-horizontal" enctype="multipart/form-data" method="post">
	<input name="id" value="${device.id}" type="text" hidden="hidden">
	
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="savepath">产品名称</label>
		<div class="col-sm-8">
			<input type="text" id="savepath" name="savepath" value="${device.savepath}" class="form-control" readOnly/>
		    <span style="color: black;">支持中文、英文字母、数字和下划线，长度限制4~30，中文算2位</span>
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="filetype">产品描述</label>
		<div class="col-sm-5">
			<input type="text" id="filetype" name="filetype" value="${device.filetype}" class="form-control" readOnly/>
		</div>
	</div>

</form>
