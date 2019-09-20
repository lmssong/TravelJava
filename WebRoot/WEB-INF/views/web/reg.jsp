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
<link href="css/whir_css.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="plugins/adminlte/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="css/web.css" />
<link rel="stylesheet" type="text/css" href="plugins/bootstrapvalidator/css/bootstrapValidator.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/superslide.2.1.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/web/common/manage.js"></script>
<script type="text/javascript" src="js/web/index.js"></script>
<script type="text/javascript" src="plugins/bootstrapvalidator/js/bootstrapValidator.js"></script>
</head>
<body>
<div id="header">
  <div class="top">
    <div class="welcome">
      <div class="welleft">欢迎访问旅游网站！</div>
      <c:if test="${!empty sessionScope.webUser}">
			<div class="zhuce">欢迎 ${sessionScope.webUser.userName} 登录 | <a href="w/logout">注销</a></div>
		</c:if>
		<c:if test="${empty sessionScope.webUser}">
			<div class="zhuce"><a href="w/tologin">登录</a> / <a href="w/toreg">注册</a></div>
		</c:if>
    </div>
    <div class="searchbox">
      <div class="logo" style="height: 70px;font-size: 50px;padding-top: 20px;"><h1><a href="#" title="旅游网站">旅游网站</a></h1></div>
      <div class="search">
        <input type="text" class="input1" value="请输入查询关键字"  onFocus="this.value='';" onBlur="if(this.value==''){this.value='请输入查询关键字';}"name="" /><input type="image" src="images/btn.jpg" class="btn" />
      </div>
      <div class="tel"><img src="images/tel.jpg" /></div>
    </div>
  </div>
  <div class="nav">
    <ul class="navmenu">
      <li><a href="w/index">网站首页</a></li>
      <li><a href="w/touristspots">旅游景点</a></li>
      <li><a href="w/lines">旅游线路</a></li>
      <li><a href="w/hotels">酒店宾馆</a></li>
      <li><a href="w/messages">我要留言</a></li>
      <li><a href="w/news">旅游新闻</a></li>
      <li class="login"><a href="w/tologin">立即登录</a></li>
    </ul>
  </div>
</div>
<div class="fullSlide">
  <div class="bd">
    <ul>
      <li _src="url(images/banner.jpg)" style="background:#E2025E center 0 no-repeat;"><a target="_blank" href="#"></a></li>
      <li _src="url(images/banner.jpg)" style="background:#DED5A1 center 0 no-repeat;"><a target="_blank" href="#"></a></li>
      <li _src="url(images/banner.jpg)" style="background:#B8CED1 center 0 no-repeat;"><a target="_blank" href="#"></a></li>
      <li _src="url(images/banner.jpg)" style="background:#98918E center 0 no-repeat;"><a target="_blank" href="#"></a></li>
      <li _src="url(images/banner.jpg)" style="background:#FEFF19 center 0 no-repeat;"><a target="_blank" href="#"></a></li>
    </ul>
  </div>
  <div class="hd">
    <ul>
    </ul>
  </div>
  <span class="prev"></span> <span class="next"></span> </div>
<script type="text/javascript">

</script>
<div id="container">
  <div class="column">
    <div class="title">
      <h1>我要注册</h1>
    </div>
    <div style="width:800px;padding:20px;margin: 50px auto;border-radius: 6px;box-shadow: 1px 3px 11px 5px #dcd3c0;">
      	<form id="regForm">
			<div class="form-group">
				<label for="userName" style="font-size: 20px;margin: 10px;">用户名：</label>
				<input type="text" class="form-control" id="userName" name="userName" placeholder="用户名为6～20字符">
			</div>
			<div class="form-group">
				<label for="password" style="font-size: 20px;margin: 10px;">密 码：</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="密码为6～12位">
			</div>
			<div class="form-group">
				<label for="confirmPwd" style="font-size: 20px;margin: 10px;">确认密码：</label>
				<input type="password" class="form-control" id="confirmPwd" name="confirmPwd" placeholder="确认密码">
			</div>
			<div class="form-group">
				<label for="name" style="font-size: 20px;margin: 10px;">姓名：</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="姓名">
			</div>
			<div class="form-group">
				<label for="sex" style="font-size: 20px;margin: 10px;">性 别：</label>
				<div style="padding-left: 6px;">
					<label class="radio-inline"> <input type="radio" name="sex" value="1" checked> 男
					</label> <label class="radio-inline"> <input type="radio" name="sex" value="0"> 女
					</label>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="form-group" style="margin-top: 14px;    margin-bottom: 60px;">
				<a class="btn btn-primary" style="width: 760px;" id="regBtn">注册</a>
			</div>
		</form>
    </div>
  </div>
  
</div>
<!--底部-->
<div id="footer">
  <div class="foot">
    <div class="fnav">
      <ul>
        <li><a href="#">关于我们</a></li>
        <li><a href="#">免责条款</a></li>
        <li><a href="#">加入收藏</a></li>
        <li class="nobg"><a href="#">设为首页</a></li>
      </ul>
    </div>
    <div class="copyright">版权所有：旅游网站 电话：0550-66258789 地址：XXXXXXXX 邮编：XXXXXXXX 邮箱：XXXXXX@163.com<br />
      皖ICP备07501184号 Designed by <a href="#" target="_blank">samxinnet</a></div>
  </div>
</div>

<script type="text/javascript">
jQuery(".fullSlide").hover(function() {
    jQuery(this).find(".prev,.next").stop(true, true).fadeTo("show", 0.5)
},
function() {
    jQuery(this).find(".prev,.next").fadeOut();
    
    $('#regForm').bootstrapValidator({
					feedbackIcons : {
						valid : 'glyphicon glyphicon-ok',
						invalid : 'glyphicon glyphicon-remove',
						validating : 'glyphicon glyphicon-refresh'
					},
					fields : {
						userName : {
							validators : {
								notEmpty : {
									message : '用户名不能为空'
								},
								stringLength : {
									min : 6,
									max : 20,
									message : '用户名长度为6-20个字符'
								},
								remote : {
									type : 'POST',
									url : 'w/user/checkAccount',
									message : '用户名已被注册'
								}
							}
						},
						password : {
							validators : {
								notEmpty : {
									message : '密码不能为空'
								},
								regexp : {
									enabled : true,
									regexp : /^(?![\d]+$)(?![a-zA-Z]+$)(?![!@#$%^&*,.\/<>?]+$)[\da-zA-Z!@#$%^&*,.\/<>?]{6,12}$/,
									message : '请输入6-12位密码，密码包含字母、数字及特殊符号'
								},
								identical : {
									field : 'confirmPwd',
									message : '两次密码不一样'
								}
							}
						},
						confirmPwd : {
							validators : {
								notEmpty : {
									message : '确认密码不能为空'
								},
								regexp : {
									enabled : true,
									regexp : /^(?![\d]+$)(?![a-zA-Z]+$)(?![!@#$%^&*,.\/<>?]+$)[\da-zA-Z!@#$%^&*,.\/<>?]{6,12}$/,
									message : '请输入6-12双组合密码'
								},
								identical : {
									field : 'password',
									message : '两次密码不一样'
								}
							}
						}
					}
				});
	
	
	$("#regBtn").bind("click", function() {
		$("#regForm").data('bootstrapValidator').validate();
		if (!$("#regForm").data('bootstrapValidator').isValid()) {
			return;
		}
		jQuery.ajax({
			type : "post",
			url : "w/user/create",
			dataType : "json",
			data : $("#regForm").serializeObject(),
			success : function(obj) {
				alert("注册成功，请登录网站！");
				window.location.href = "w/index";
				$("#regForm").find("input").val("");
			}
		});
	});
});
jQuery(".fullSlide").slide({
    titCell: ".hd ul",
    mainCell: ".bd ul",
    effect: "fold",
    autoPlay: true,
    autoPage: true,
    trigger: "click",
    startFun: function(i) {
        var curLi = jQuery(".fullSlide .bd li").eq(i);
        if ( !! curLi.attr("_src")) {
            curLi.css("background-image", curLi.attr("_src")).removeAttr("_src")
        }
    }
});

</script>
</body>
</html>