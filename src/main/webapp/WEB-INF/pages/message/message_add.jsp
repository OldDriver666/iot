<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
	   <div class="form-group" >
        <label class="col-sm-3 control-label" for="productId"><font color="red">*</font>ProductId：</label>
        <div class="col-sm-8">
            <select class="form-control" id="productId" name="productId" onchange="func()" style="display: inline-block;width: 50%">
            	<option>==productId==</option>
            	<c:forEach items="${productlist}" var="productlist">
					<option value="${productlist.productKey}">${productlist.productId}</option>
				</c:forEach>>
            </select>
            <input type="text" class="form-control" id="productKey" name="productKey" style="display: inline-block;width: 40%" readonly/>
        </div>
       </div> 
	  <div class="form-group">
        <label class="col-sm-3 control-label" for="productId"><font color="red">*</font>Topic后缀： </label>
        <div class="col-sm-5">
            <input class="form-control" type="text" id="topic_suffix" name="topic_suffix" placeholder=""/>
            <div id="validation-productId" class="validate-error help-block"></div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="productName"><font color="red">*</font>设备操作权限：</label>
        <div class="col-sm-5">
            <select class="form-control" name="oper_auth">
            	<option>==操作权限==</option>
            	<option value="1">订阅</option>
            	<option value="2">发布</option>
            	<option value="3">订阅和发布</option>
            </select>
        </div>
    </div>
    
    <div class="form-group">
        <div class="col-sm-12">
        	<label class="col-sm-3 control-label" for="productName"></label>
            <div style="font-size: 14px" class="validation-productKey col-sm-8">1.Topic格式必须以“/”进行分层，区分每个类目。其中前两个类目已经规定好，第一个代表产品标识ProductKey，第二个类目${deviceName}通配deviceName。简单来说，Topic类：/pk/${deviceName}/update是具体Topic:/pk/mydevice/update或者/pk/yourdevice/update的集合</br>
            	2.只能包含字母，数字和下划线（_）命名每级类目，每级类目不能为空</br>
            	3.输入的Topic类长度不能超过64字节
            </div>
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-sm-3 control-label" for="productDesc">描述：</label>
        <div class="col-sm-8">
            <textarea rows="3" cols="20" class="form-control" id="productDesc" name="productDesc"></textarea>
        </div>
    </div>
    
</form>
<script type="text/javascript">
	 submit = function(){
		frmValidate();
	    var data = $("#submitForm").serialize();
		ajaxRequest("admin/message/addTopic", data);
	}
	 function func() {
		var val = $("#productId option:selected").val();
		if(null != val | "==productId=="==val){
			$("#productKey").val(val);
			$("#productId option:selected").attr("value",$("#productId option:selected").text());
		}
	}
</script>