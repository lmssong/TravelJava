<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8">
<title>酒店管理</title>
<%@include file="../common/common.jsp"%>
</head>
<body>
	<form name="searchform" method="post" action="" id="searchform">
		<table>
			<tr>
				<td><strong>酒店名称：</strong></td>
				<td><input type="text" name="name" id="name" size=50></td>
				<td><strong>地址：</strong></td>
				<td><input type="text" name="address" id="address" size=50></td>
				<td><a class="btn" id="submit_search">搜索</a></td>
			</tr>
		</table>
	</form>
	<table class="easyui-datagrid" id="tHotel_datagrid">
	</table>
</body>
<script>
	var fmt = (function() {
		var fmt = {};
		fmt.createTime = function(_val, _row, _index) {
			return DateUtil.stamp2Time(_val);
		};

		fmt.createTime1 = function(_val, _row, _index) {

			return DateUtil.stamp2Time(DateUtil.time2Stamp(_val));
		};

		fmt.fmtImage = function(val, row, index) {
			if (val == 'image/default.png') {
				return "<img src='" + val
					+ "' style='width:80px; height:80px;padding:5px;'>";
			} else {
				return "<img src='" + val
					+ "' style='width:80px; height:80px;padding:5px;'>";
			}
		};
		fmt.fmtOpt = function(val, row, index) {
			return '<a href="javascript:void(0);" onclick="fmt.edit(' + row.id
					+ ');">编辑</a> '
					+ '| <a href="javascript:void(0);" onclick="fmt.remove('
					+ row.id + ');">删除</a>';
		};

		fmt.edit = function(id) {
			window.location.href = "m/tHotel/edit/" + id;
		};

		fmt.remove = function(id) {
			if (confirm('是否确认删除该数据')) {
				$.ajax({
					url : 'm/tHotel/delete',// 跳转到 action
					type : 'post',
					cache : false,
					data : {
						id : id
					},
					dataType : 'json',
					success : function(data) {
						$.messager.alert('操作提示', "删除成功！");
						$("#tHotel_datagrid").datagrid("reload");
					},
					error : function(data) {
						console.log("remove　error" + JSON.stringify(data));
					}
				});
			}
		};
		return fmt
	})();

	$(function() {
		var tHotel_columns = [ [ {
			field : 'id',
			title : '唯一标识',
			hidden : true
		}, {
			field : 'name',
			title : '酒店名称',
			width : 100,
		}, {
			field : 'address',
			title : '地址',
			width : 100,
		}, {
			field : 'tel',
			title : '预定电话',
			width : 100,
		}, {
			field : 'image',
			title : '酒店招聘',
			width : 120,
			formatter : fmt.fmtImage
		}, {
			field : 'introduce',
			title : '酒店介绍',
			width : 100,
		}, {
			field : 'createTime',
			title : '创建时间',
			width : 100,
			formatter : fmt.createTime
		}, {
			field : 'operate',
			title : '操作',
			width : 60,
			formatter : fmt.fmtOpt
		} ] ];

		var tHotel_toolbar = [
				'-',
				{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						window.location.href = 'm/tHotel/add';
					}
				},
				'-',
				{
					text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						$("#tHotel_datagrid").datagrid("options").url = 'm/tHotel/findPage';
						$('#tHotel_datagrid').datagrid('reload');
					}
				} ];

		window.top.document.title = "酒店管理";
		$("#tHotel_datagrid").datagrid({
			fit : true,
			striped : true,
			resizable : true,
			rownumbers : true,
			fitColumns : true,
			collapsible : true,
			singleSelect : true,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			url : 'm/tHotel/findPage',
			method : 'post',
			queryParams : form2Json("searchform"),
			columns : tHotel_columns,
			toolbar : tHotel_toolbar,
			onBeforeLoad : function(_post) {
				_post.currentPage = _post.page;
				_post.pageSize = _post.rows;

				delete _post.page;
				delete _post.rows;
			}
		});

		$("#submit_search").linkbutton({
			iconCls : 'icon-search',
			plain : true
		}).click(function() {
			$('#tHotel_datagrid').datagrid({
				queryParams : form2Json("searchform")
			}); //点击搜索
		});

		//将表单数据转为json
		function form2Json(id) {

			var arr = $("#" + id).serializeArray()
			var jsonStr = "";

			jsonStr += '{';
			for (var i = 0; i < arr.length; i++) {
				jsonStr += '"' + arr[i].name + '":"' + arr[i].value + '",'
			}
			jsonStr = jsonStr.substring(0, (jsonStr.length - 1));
			jsonStr += '}'

			var json = JSON.parse(jsonStr)
			return json
		}

	});
</script>
</html>