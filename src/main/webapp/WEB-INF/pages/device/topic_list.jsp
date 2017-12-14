<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <input type="hidden" value="${device.deviceId}" id="deviceId"/>
  <div id="searchForm">
	  <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="backBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/device/deviceinfoPage');">返回</button>
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
                {field:"topicUrl", text:"设备的Topic"},
                {field:"operAuth", text:"设备具有的权限",formatter:function(index, content, data){
                	if(content == 1)
                	return "<font color='blue'>订阅</font>";
                	if(content == 2)
                    return "<font color='blue'>发布</font>";
                	if(content == 3)
                    return "<font color='blue'>订阅和发布</font>";
                }},
                {field:"messageNum", text:"发布消息数"},
                {field:"deviceId", text:"操作", style:"text-align:center", formatter:function(index, content, data){
                	var publishUrl = "admin/device/topicList/" + content;
                    return "<a href='javascript:showModal(\"发布消息\", \""+publishUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>发布消息</i></a>";
                }}
            ],	
            cls: "",
            url: _urlPath + "admin/device/topicListPage/${device[0].deviceId}",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>