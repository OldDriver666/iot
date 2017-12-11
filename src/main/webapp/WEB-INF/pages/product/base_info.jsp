<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
  <div id="searchForm">
  	<div class="col-md-2" style="width: 200px">
		<input type="text" class="form-control search-query" name="username" placeholder="产品名称">
	</div>
	 <div class="col-md-1" style="width: 105px;">
        <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
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
                {field:"id",text:"ID"},
                {field:"productId",text:"产品ID"},
                {field:"productName", text:"产品名称"},
                {field:"productKey", text:"productKey"},
                {field:"productDesc", text:"产品描述"},
                {field:"creator", text:"创建者"},
                {field:"updator", text:"修改者"},
                {field:"createTime", text:"创建时间"},
                {field:"updateTime", text:"更新时间"},
                {field:"id", text:"操作", style:"text-align:center", formatter:function(index, content, data){
                    var editUrl = "admin/tech/updateFilePage/" + content;
                    //可直接通过downUrl=data.url获取文件访问路径直接下载,但是那种方式会直接在浏览器打开文件
                    var downUrl = _urlPath + "upload/downFile.do?id=" + content;
                    var delUrl = "admin/tech/delFile/" + content;
                    return "<a href='javascript:showModal(\"修改文件\", \""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
                        + "&nbsp;<a href='javascript:showCfm(\"确定删除该记录\", \""+delUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>删除</i></a>";
                }}
            ],	
            cls: "",
            url: _urlPath + "admin/product/queryProductPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
</script>