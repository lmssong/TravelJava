<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8">
<title>留言管理</title>
<%@include file="../common/common.jsp"%>
</head>
<body>
	<form name="searchform" method="post" action="" id="searchform">
		<table>
			<tr>
				<td><strong>留言人：</strong></td>
				<td><input type="text" name="person" id="person" size=20></td>
				<td><a class="btn" id="submit_search">搜索</a></td>
			</tr>
		</table>
	</form>
	<table class="easyui-datagrid" id="tMessage_datagrid">
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
			window.location.href = "m/tMessage/edit/" + id;
		};

		fmt.remove = function(id) {
			if (confirm('是否确认删除该数据')) {
				$.ajax({
					url : 'm/tMessage/delete',// 跳转到 action
					type : 'post',
					cache : false,
					data : {
						id : id
					},
					dataType : 'json',
					success : function(data) {
						$.messager.alert('操作提示', "删除成功！");
						$("#tMessage_datagrid").datagrid("reload");
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
		var tMessage_columns = [ [ {
			field : 'id',
			title : '唯一标识',
			hidden : true
		}, {
			field : 'person',
			title : '留言人',
			width : 100,
		}, {
			field : 'content',
			title : '留言内容',
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

		var tMessage_toolbar = [
				'-',
				{
					text : '新增',
					iconCls : 'icon-add',
					handler : function() {
						window.location.href = 'm/tMessage/add';
					}
				},
				'-',
				{
					text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						$("#tMessage_datagrid").datagrid("options").url = 'm/tMessage/findPage';
						$('#tMessage_datagrid').datagrid('reload');
					}
				} ];

		window.top.document.title = "留言管理";
		$("#tMessage_datagrid").datagrid({
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
			url : 'm/tMessage/findPage',
			method : 'post',
			queryParams : form2Json("searchform"),
			columns : tMessage_columns,
			toolbar : tMessage_toolbar,
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
			$('#tMessage_datagrid').datagrid({
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