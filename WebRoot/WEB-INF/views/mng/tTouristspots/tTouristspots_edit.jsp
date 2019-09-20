<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8">
<title>景点管理</title>
<%@include file="../common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="css/manage.css" />
<script type="text/javascript" src="plugins/other/ajaxfileupload.js"></script>
<script type="text/javascript" src="plugins/other/upload.js"></script>
</head>
<body class="easyui-layout" style="overflow: auto">
	<div class="easyui-panel" title="编辑景点管理信息">
		<form id="tTouristspotsForm" method="post">
			<table cellpadding="10" align="center"
				style="width: 60%; margin-top: 30px;">
				<input type="hidden" id="id" name="id" value="${id}" />
				<tr>
					<td class="td_title">活动图片:</td>
					<td><c:choose>
							<c:when test="${empty tTouristspots.image}">
								<input type="hidden" id="image" name="image" value="" />
								<img id="imgIcon" src="images/default.png"
									onclick="$('#fileIcon').click();"
									style="width: 150px; height: 150px;">
							</c:when>
							<c:otherwise>
								<input type="hidden" id="image" name="image"
									value="${tTouristspots.image}" />
								<img id="imgIcon"
									src="http://127.0.0.1:8080/tTouristspots/${tTouristspots.image}"
									onclick="$('#fileIcon').click();"
									style="width: 150px; height: 150px;">
							</c:otherwise>
						</c:choose> <a href="javascript:void(0);" onclick="$('#fileIcon').click();">点击上传</a>
						<input type="file"
						onchange="tTouristspots.uploadCover('fileIcon','imgIcon','image');"
						id="fileIcon" style="display: none;" name="fileInput" /></td>
				</tr>
				<tr>
					<td class="td_title">景点名称:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						id="name" value="${tTouristspots.name}" style="width: 330px;"></input>
					</td>
				<tr>
				<tr>
					<td class="td_title">景点位置:</td>
					<td><input class="easyui-textbox" type="text" name="address"
						id="address" value="${tTouristspots.address}"
						style="width: 330px;"></input></td>
				<tr>
				<tr>
					<td class="td_title">预定电话:</td>
					<td><input class="easyui-textbox" type="text" name="tel"
						id="tel" value="${tTouristspots.tel}" style="width: 330px;"></input>
					</td>
				<tr>
				<tr>
					<td class="td_title">景点介绍:</td>
					<td><input class="easyui-textbox" type="text" name="introduce"  data-options="multiline:true"
						id="introduce" value="${tTouristspots.introduce}"
						style="width: 330px;height:150px"></input></td>
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
			window.top.document.title = "新增景点管理信息";
			$("#tbtn_sub").bind("click", function() {
				tTouristspots.addtTouristspotserList();
			});
			if ($("#id").val()) {
				$("#btn_sub").bind("click", function() {
					tTouristspots.editTeach();
				});
			} else {
				$("#btn_sub").bind("click", function() {
					tTouristspots.addTeach();
				});
			}
			;
			$("#btn_back").bind("click", function() {
				window.location.href = "m/tTouristspots/list";
			});
		});
		var tTouristspots = {};
		tTouristspots.addTeach = function() {
			if ($("#tTouristspotsForm").form("validate")) {
				$.ajax({
					url : 'm/tTouristspots/create',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tTouristspotsForm').serialize(),
					dataType : "json",
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '新增成功！');
							window.location.href = "m/tTouristspots/list";//跳转到列表页
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

		tTouristspots.uploadCover = function(fileId, img, picId) {
			$.ajaxFileUpload({
				url : "m/tTouristspots/upload", //需要链接到服务器地址 
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

		tTouristspots.editTeach = function() {
			if ($("#tTouristspotsForm").form("validate")) {
				$.ajax({
					url : 'm/tTouristspots/update',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tTouristspotsForm').serialize(),
					dataType : 'json',
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '修改成功！');
							window.location.href = "m/tTouristspots/list";//跳转到列表页
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