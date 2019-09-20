<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!DOCTYPE html>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
<title>旅游网站后台管理系统</title>
<%@include file="common/common.jsp"%>
<script type="text/javascript" src="plugins/easyui/js/outlook.js"></script>
</head>
<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
		<div class="wu-header-left">
			<h1>旅游网站后台管理系统</h1>
		</div>
		<div class="wu-header-right">
			<p style="margin-top: 10px;">
				<strong class="easyui-tooltip">${sessionUser.userName}</strong>，欢迎您！|<a href="/m/logout">安全退出</a>
			</p>
		</div>
	</div>
	<!-- end of header -->
	<!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'">
		<div class="easyui-accordion" data-options="border:false,fit:true">
			<c:if test="${sessionUser.role==1}">
				<div title="功能管理" data-options="iconCls:'icon-application-cascade'" style="padding: 5px;">
					<ul class="easyui-tree wu-side-tree">
						<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="m/user/list" iframe="0">注册用户管理</a></li>
						<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="m/tHotel/list">酒店管理</a></li>
						<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="m/tTouristspots/list">景点管理</a></li>
						<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="m/tLines/list">线路管理</a></li>
						<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="m/tMessage/list">留言管理</a></li>
						<li iconCls="icon-chart-organisation"><a href="javascript:void(0)" data-icon="icon-chart-organisation" data-link="m/tNews/list">新闻管理</a></li>
						
					</ul>
				</div>				
			</c:if>
		</div>
	</div>
	<!-- end of sidebar -->
	<!-- begin of main -->
	<div class="wu-main" id="mainPanle" data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" data-options="border:false,fit:true">
			<div title="首页" data-options="closable:false,iconCls:'icon-tip',cls:'pd3'">
				<div style="width: 100%; height: 100%;text-align: center; margin-top: 100px;font-size: 50px;">欢迎您使用旅游网站管理系统</div>
			</div>
		</div>
	</div>

	<!-- end of main -->
	<!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:true">&copy; 2019 XXX All Rights Reserved</div>
	<!-- end of footer -->
	<!-- begin of tab -->
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<!-- end of tab -->

</body>
</html>