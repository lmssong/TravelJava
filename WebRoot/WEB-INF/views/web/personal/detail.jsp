<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<base href="http://127.0.0.1:8080/tourism/" />
<%
    request.setAttribute("basePath", request.getContextPath());
%>
<script type="text/javascript">
	var basePath = '${basePath}';
</script>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>旅游网站</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/AdminLTE.min.css" />
<link rel="stylesheet" type="text/css" href="plugins/adminlte/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="css/web.css" />
<link rel="stylesheet" type="text/css" href="plugins/bootstrapvalidator/css/bootstrapValidator.css" />


<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/web/common/manage.js"></script>
<script type="text/javascript" src="js/web/index.js"></script>
<script type="text/javascript" src="plugins/bootstrapvalidator/js/bootstrapValidator.js"></script>
<style>
.modal-body .form-control {
	width: 300px
}

.pet-list {
	height: 230px;
	overflow-y: auto;
	text-align: center;
	margin-left: 124px !important;
	width: 390px;
}

.petItem {
	vertical-align: middle;
	line-height: 100px;
	float: left;
	margin: 10px 30px;
}

.petItem img {
	width: 100px;
}

.petItem input {
	margin-top: 50px;
	margin-right: 10px;
}

.description {
	width: 200px !important;
	color: #666 !important;
	display: -webkit-box !important;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 4;
	overflow: hidden;
}

.mass-info {
	padding: 20px 5px;
	font-size: 14px;
	color: #666;
	line-height: 24px;
}

.mass-info p {
	text-indent: 26px;
}

.teach-info .rows {
	font-size: 14px;
	font-weight: 600;
	padding: 20px 60px;
	padding-bottom: 36px;
	margin-top: 30px;
	box-shadow: 1px 1px 1px 1px #e4e4e4;
}
</style>
<script type="text/javascript">
	$(function() {

		
	});

</script>
</head>
<body>
	<div class="global-content">
		<!--页头-->
		<div class="header">
			<div class="inner">
				<a href="w/index">
					<span style="font-size: 26px; font-family: '微软雅黑'; margin-top: 20px; display: inline-block;">旅游网站</span>
				</a>
			</div>
			<c:if test="${!empty sessionScope.webUser}">
				<div style="position: absolute; top: 5px; right: 10px">
					欢迎 ${sessionScope.webUser.userName} 登录 | <a href="w/logout">注销</a>
				</div>
			</c:if>

		</div>
		<div class="clearfix"></div>
		<!--首页菜单-->
		<div class="index-menu">
			<div class="menu inner">
				<ul>
					<li><a href="w/index">首页</a></li>
					<li><a href="w/teach/index">个人信息</a></li>
					<c:choose>
						<c:when test="${empty webUser}">
							<li><a href="javascript:alert('请先登录网站');">我的账户</a></li>
						</c:when>
						<c:otherwise>
							<li><a class="cur" href="w/user/info">我的账户</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix" style="font-size: 1px"></div>
		<!--首页banner-->
		<img alt="" src="images/timg.jpg" width="100%" height="100">
		<div class="main-content inner" style="margin-top: 34px">
			<div class="clearfix"></div>
			<div class="pets">
				<div class="title">
					<span style="width: 160px">个人信息</span>
				</div>
				<div class="teach-info">
					<div class="rows">
						<div class="col-sm-12">
							<div class="col-sm-3">姓名：${webUser.name}</div>
							<div class="col-sm-5">性别：${webUser.sex==1?'男':'女'}</div>
							<div class="col-sm-4">积分：${webUser.integral}</div>
							<div class="col-sm-8" style="margin-top: 20px;">
								<input type="radio" name="amount" value="100" checked>100积分
								<input type="radio" name="amount" value="500" style="margin-left:20px">500积分
								<input type="radio" name="amount" value="1000" style="margin-left:20px">1000积分
							</div>
							<div class="col-sm-2" style="margin-top: 16px;">
								<a href="javascript:void(0);" class="btn btn-primary" style="padding: 2px 26px;" onclick="recharge(${webUser.id})">充值</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
					<div class="box" style="margin-top: 40px;">
						<div class="box-header">
							<h3 class="box-title">我的订单</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<table class="table table-striped" style="text-align: center;">
								<tbody>
									<tr>
										<th style="text-align: center;width: 90px">订单号</th>
										<th style="text-align: center;">订单类型</th>
										<th style="text-align: center;">套餐</th>
										<th style="text-align: center;">技能师</th>
										<th style="text-align: center;">充值金额</th>
									</tr>
									<c:forEach var="order" items="${orderList}" varStatus="status">
										<tr>
											<td>${order.id}</td>
											<td>
												<c:if test="${order.type==1}">购买套餐</c:if>
												<c:if test="${order.type==2}">购买项目</c:if>
												<c:if test="${order.type==3}">充值积分</c:if>
											</td>
											<td>${order.packageName}</td>
											<td>${order.teachName}</td>
											<td>${order.amount}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					
					
					<div class="title">
						<span style="width: 160px">我的留言</span>
					</div>
					<div style="font-size:14px;    padding: 10px 20px;">
						<c:forEach var="leave" items="${leaveList}" varStatus="status">
							<div>留言：${ leave.msg}</div>
							<div>管理员回复：${ leave.reply}</div>
						</c:forEach>
					
					</div>
					<div class="box box-info" style="margin-top:40px">
			            <div class="box-header with-border">
			              <h3 class="box-title">留言</h3>
			            </div>
			            <!-- /.box-header -->
			            <!-- form start -->
			            <form class="form-horizontal">
			              <div class="box-body">
			                <div class="form-group">
			                  <div class="col-sm-12">
			                    <textarea id="leaveMsg" class="form-control" rows="5" placeholder="进行留言 ..."></textarea>
			                  </div>
			                </div>
			              </div>
			              <!-- /.box-body -->
			              <div class="box-footer">
			                <a href="javascript:void(0);" class="btn btn-info pull-right" onclick="leaveMsg('${webUser.id}')">留言</a>
			              </div>
			              <!-- /.box-footer -->
			            </form>
			          </div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<!--页尾-->
		<div class="footer">
			<div class="inner">
				<div class="pull-left">
					<p>
						<a href="#">客户服务</a> <a href="#">关于我们</a> <a href="#">友情连接</a>
					</p>
					<p>版权所有 2016 xxxxxxxxxxxx 京ICP备08102525号 京公网安备110102004606号</p>
					<p>电话：(010)xxxxxxxx, xxxxxxxx 传真：(010)xxxxxxxx, xxxxxxxx</p>
				</div>
				<div class="pull-right">
					<img class="qr-code" src="images/qrcode.png" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>