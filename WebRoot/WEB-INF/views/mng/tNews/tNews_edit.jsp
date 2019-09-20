<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8">
<title>新闻管理</title>
<%@include file="../common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="css/manage.css" />
<script type="text/javascript" src="plugins/other/ajaxfileupload.js"></script>
<script type="text/javascript" src="plugins/other/upload.js"></script>
</head>
<body class="easyui-layout" style="overflow: auto">
	<div class="easyui-panel" title="编辑新闻管理信息">
		<form id="tNewsForm" method="post">
			<table cellpadding="10" align="center"
				style="width: 60%; margin-top: 30px;">
				<input type="hidden" id="id" name="id" value="${id}" />
				<tr>
					<td class="td_title">新闻照片:</td>
					<td><c:choose>
							<c:when test="${empty tNews.image}">
								<input type="hidden" id="image" name="image" value="" />
								<img id="imgIcon" src="images/default.png"
									onclick="$('#fileIcon').click();"
									style="width: 150px; height: 150px;">
							</c:when>
							<c:otherwise>
								<input type="hidden" id="image" name="image"
									value="${tNews.image}" />
								<img id="imgIcon"
									src="http://127.0.0.1:8080/tNews/${tNews.image}"
									onclick="$('#fileIcon').click();"
									style="width: 150px; height: 150px;">
							</c:otherwise>
						</c:choose> <a href="javascript:void(0);" onclick="$('#fileIcon').click();">点击上传</a>
						<input type="file"
						onchange="tNews.uploadCover('fileIcon','imgIcon','image');"
						id="fileIcon" style="display: none;" name="fileInput" /></td>
				</tr>
				<tr>
					<td class="td_title">新闻标题:</td>
					<td><input class="easyui-textbox" type="text" name="title"
						id="title" value="${tNews.title}" style="width: 330px;"></input></td>
				<tr>
				<tr>
					<td class="td_title">新闻摘要:</td>
					<td><input class="easyui-textbox" type="text" name="abstracts"
						id="abstracts" value="${tNews.abstracts}" style="width: 330px;"></input>
					</td>
				<tr>
				<tr>
					<td class="td_title">新闻内容:</td>
					<td><input class="easyui-textbox" type="text" name="content" data-options="multiline:true"
						id="content" value="${tNews.content}" style="width: 330px;height:200px"></input>
					</td>
				<tr>
				<tr>
					<td></td>
					<td id="attach_td"></td>
				</tr>

				<tr>
					<td colspan="4" style="padding: 20px 0px 0px 120px;"><a
						href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" id="btn_sub"> &nbsp;保 存
							&nbsp;</a>&nbsp;&nbsp; <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-back'"
						id="btn_back"> &nbsp;返 回 &nbsp;</a>&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			window.top.document.title = "新增新闻管理信息";
			$("#tbtn_sub").bind("click", function() {
				tNews.addtNewserList();
			});
			if ($("#id").val()) {
				$("#btn_sub").bind("click", function() {
					tNews.editTeach();
				});
			} else {
				$("#btn_sub").bind("click", function() {
					tNews.addTeach();
				});
			}
			;
			$("#btn_back").bind("click", function() {
				window.location.href = "m/tNews/list";
			});
		});
		var tNews = {};
		tNews.addTeach = function() {
			if ($("#tNewsForm").form("validate")) {
				$.ajax({
					url : 'm/tNews/create',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tNewsForm').serialize(),
					dataType : "json",
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '新增成功！');
							window.location.href = "m/tNews/list";//跳转到列表页
						} else {
							$.messager.alert('操作提示', data.message);
						}
					},
					error : function() {
						//TODO:请求错误
					}
				});
			} else {
				$.messager.alert("操作提示", "表单验证不通过");
			}
		};

		tNews.uploadCover = function(fileId, img, picId) {
			$.ajaxFileUpload({
				url : "m/tNews/upload", //需要链接到服务器地址 
				secureuri : false,
				fileElementId : fileId,//文件选择框的id属性
				dataType : 'json', //json
				success : function(obj) {
					$("#" + img).attr("src", obj.convertFileName).show();
					$("#" + picId).val(obj.newFileName);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert('操作提示', '上传失败！');
				}
			});
		};

		tNews.editTeach = function() {
			if ($("#tNewsForm").form("validate")) {
				$.ajax({
					url : 'm/tNews/update',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tNewsForm').serialize(),
					dataType : 'json',
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '修改成功！');
							window.location.href = "m/tNews/list";//跳转到列表页
						} else {
							$.messager.alert('操作提示', data.message);
						}
					},
					error : function() {
						//TODO:请求错误
					}
				});
			} else {
				$.messager.alert("操作提示", "表单验证不通过");
			}
		};
	</script>
</body>
</html>