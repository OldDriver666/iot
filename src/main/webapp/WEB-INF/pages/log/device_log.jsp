<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
   <div id="titleForm">
	  <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="backBtn" class="btn btn-labeled btn-primary">日志服务</button>
        <div align="left"  style="width: 1800px; height:20px">
        
           <tr align="right" style="width: 180px; height: 80px">
              
            <td class="btn btn-labeled btn-primary" style="text-align:left;">
              <a href="pwd_modify.jsp">设备行为分析</a>
            </td>
            
            <td class="btn btn-labeled btn-primary" style="text-align:center;">
             <a href="pwd_modify.jsp">上行消息分析</a>
            </td>
            
            <td class="btn btn-labeled btn-primary" style="text-align:right;">
                <a href="pwd_modify.jsp">下行消息分析</a>
            </td>
    </tr>
   </div>
 </div>
</div>

</div>
<div class="openAppGrid">
	<div id="openAppGrid"></div>
</div>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
                {field:"id",text:"文件ID"},
                {field:"username", text:"操作人"},
                {field:"type", text:"日志类型", formatter:function(index, content, data){
                	return 1 == content ? "<font color='red'>异常日志</font>" : "<font color='blue'>操作日志</font>";
                }},
                {field:"url", text:"请求地址"},
                {field:"method", text:"方法"},
                {field:"params", text:"请求参数",limit:15,formatter:function(index, content, data){
                	content = content.replace(/<(?:.|\s)*?>/g,"");
                	if(content.length > 30){
                		content = content.substr(0, 30) + "...";
                	}
                	return content;
                }},
                {field:"requestip", text:"访问IP"},
                {field:"description", text:"操作描述"},
                {field:"detail", text:"异常详情"},
                {field:"operDate", text:"操作日期"}
            ],	
            cls: "",
            url: _urlPath + "admin/log/queryPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>