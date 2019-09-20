<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8">
<title>留言管理</title>
<%@include file="../common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="css/manage.css" />
<script type="text/javascript" src="plugins/other/ajaxfileupload.js"></script>
<script type="text/javascript" src="plugins/other/upload.js"></script>
</head>
<body class="easyui-layout" style="overflow: auto">
	<div class="easyui-panel" title="编辑留言管理信息">
		<form id="tMessageForm" method="post">
			<table cellpadding="10" align="center"
				style="width: 60%; margin-top: 30px;">
				<input type="hidden" id="id" name="id" value="${id}" />
				<tr>
					<td class="td_title">留言人:</td>
					<td><input class="easyui-textbox" type="text" name="person"
						id="person" value="${tMessage.person}" style="width: 330px;"></input>
					</td>
				<tr>
				<tr>
					<td class="td_title">留言内容:</td>
					<td><input class="easyui-textbox" type="text" name="content"
						id="content" value="${tMessage.content}" style="width: 330px;"></input>
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
			window.top.document.title = "新增留言管理信息";
			$("#tbtn_sub").bind("click", function() {
				tMessage.addtMessageerList();
			});
			if ($("#id").val()) {
				$("#btn_sub").bind("click", function() {
					tMessage.editTeach();
				});
			} else {
				$("#btn_sub").bind("click", function() {
					tMessage.addTeach();
				});
			}
			;
			$("#btn_back").bind("click", function() {
				window.location.href = "m/tMessage/list";
			});
		});
		var tMessage = {};
		tMessage.addTeach = function() {
			if ($("#tMessageForm").form("validate")) {
				$.ajax({
					url : 'm/tMessage/create',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tMessageForm').serialize(),
					dataType : "json",
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '新增成功！');
							window.location.href = "m/tMessage/list";//跳转到列表页
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

		tMessage.uploadCover = function(fileId, img, picId) {
			$.ajaxFileUpload({
				url : "m/tMessage/upload", //需要链接到服务器地址 
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

		tMessage.editTeach = function() {
			if ($("#tMessageForm").form("validate")) {
				$.ajax({
					url : 'm/tMessage/update',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tMessageForm').serialize(),
					dataType : 'json',
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '修改成功！');
							window.location.href = "m/tMessage/list";//跳转到列表页
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