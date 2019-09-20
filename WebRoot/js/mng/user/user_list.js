var fmt = (function() {
	var fmt = {};
	fmt.createTime = function(_val, _row, _index) {
		return DateUtil.stamp2Time(_val);
	};

	return fmt
})();

$(function() {
	var user_columns = [ [ {
		field : 'id',
		title : 'ID',
		width : 60,
		hidden : true
	}, {
		field : 'userName',
		title : '用户名',
		width : 100
	}, {
		field : 'name',
		title : '姓名',
		width : 100
	},{
		field : 'sex',
		title : '性别',
		width : 100
	},{
		field : 'integral',
		title : '积分',
		width : 100
	}, {
		field : 'createTime',
		title : '注册时间',
		width : 100,
		formatter : fmt.createTime
	} ] ];

	var user_toolbar = [{
		text : '刷新',
		iconCls : 'icon-reload',
		handler : function() {
			$("#user_datagrid").datagrid("options").url = 'm/user/findPage?role=2';
			$('#user_datagrid').datagrid('reload');
		}
	} ];

	window.top.document.title = "注册用户管理";
	$("#user_datagrid").datagrid({
		fit: true,
		striped : true,
		resizable : true,
		rownumbers : true,
		fitColumns : true,
		collapsible : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		url : 'm/user/findPage?role=2',
		method : 'post',
		columns : user_columns,
		toolbar : user_toolbar,
		onBeforeLoad : function(_post) {
			_post.currentPage = _post.page;
			_post.pageSize = _post.rows;

			delete _post.page;
			delete _post.rows;
		}
	});

});