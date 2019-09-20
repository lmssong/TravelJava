var _menus = {
		"menus" : [ {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "系统管理",
			"menus" : [ {
				"menuname" : "菜单管理",
				"icon" : "icon-nav",
				"url" : "http://www.16sucai.com"
			}, {
				"menuname" : "添加用户",
				"icon" : "icon-add",
				"url" : "demo.html"
			}, {
				"menuname" : "用户管理",
				"icon" : "icon-users",
				"url" : "demo2.html"
			}, {
				"menuname" : "角色管理",
				"icon" : "icon-role",
				"url" : "demo2.html"
			}, {
				"menuname" : "权限设置",
				"icon" : "icon-set",
				"url" : "demo.html"
			}, {
				"menuname" : "系统日志",
				"icon" : "icon-log",
				"url" : "demo.html"
			} ]
		}, {
			"menuid" : "8",
			"icon" : "icon-sys",
			"menuname" : "员工管理",
			"menus" : [ {
				"menuname" : "员工列表",
				"icon" : "icon-nav",
				"url" : "demo.html"
			}, {
				"menuname" : "视频监控",
				"icon" : "icon-nav",
				"url" : "demo1.html"
			} ]
		}, {
			"menuid" : "56",
			"icon" : "icon-sys",
			"menuname" : "部门管理",
			"menus" : [ {
				"menuname" : "添加部门",
				"icon" : "icon-nav",
				"url" : "demo1.html"
			}, {
				"menuname" : "部门列表",
				"icon" : "icon-nav",
				"url" : "demo2.html"
			} ]
		}, {
			"menuid" : "28",
			"icon" : "icon-sys",
			"menuname" : "财务管理",
			"menus" : [ {
				"menuname" : "收支分类",
				"icon" : "icon-nav",
				"url" : "demo.html"
			}, {
				"menuname" : "报表统计",
				"icon" : "icon-nav",
				"url" : "demo1.html"
			}, {
				"menuname" : "添加支出",
				"icon" : "icon-nav",
				"url" : "demo.html"
			} ]
		}, {
			"menuid" : "39",
			"icon" : "icon-sys",
			"menuname" : "商城管理",
			"menus" : [ {
				"menuname" : "商品分",
				"icon" : "icon-nav",
				"url" : "/shop/productcatagory.aspx"
			}, {
				"menuname" : "商品列表",
				"icon" : "icon-nav",
				"url" : "/shop/product.aspx"
			}, {
				"menuname" : "商品订单",
				"icon" : "icon-nav",
				"url" : "/shop/orders.aspx"
			} ]
		} ]
	};
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登录窗口
	function close() {
		$('#w').window('close');
	}

	//修改密码
	function updatePwd() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		})

	}

	$(function() {
		openPwd();
		//弹出密码修改框
		$('#editpass').click(function() {
			$('#w').window('open');
		});

		//保存密码
		$('#btnEp').click(function() {
			updatePwd();
		});
		
		//关闭密码修改框
		$('#btnCancel').click(function() {
			close();
		});

		//退出登录
		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = '/ajax/loginout.ashx';
				}
			});

		})

	});