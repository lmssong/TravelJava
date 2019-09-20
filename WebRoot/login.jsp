<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>后台管理登录</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

	<link rel="stylesheet" type="text/css" href="plugins/adminlte/css/bootstrap.min.css" />
	
	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" type="text/css" href="plugins/adminlte/css/AdminLTE.min.css"/>
	
	<script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="plugins/adminlte/js/bootstrap.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <script type="text/javascript">
	  $(function(){
			document.onkeydown = function(e){ 
				var ev = document.all ? window.event : e;
				if(ev.keyCode==13) {
					login();
				}
			};
		});
	  	function login(){
	  		if(!$("#userName").val()){
	  			$("#showMsg").text("用户名不能为空").fadeIn().fadeOut(5000);
	  			return;
	  		}else if(!$("#password").val()){
	  			$("#showMsg").text("密码不能为空").fadeIn().fadeOut(5000);
	  			return;
	  		}
	  		jQuery.ajax({
		    	type : "post" , 
		    	url : "m/login", 
		    	dataType : "json" , 
		    	data : $("#loginForm").serialize(),
		    	success : function(obj) {
		    		if(obj.data.code==1){
		    			if($("#redirect").val()){
		    				window.location.href = $("#redirect").val();
		    			}else{
		    				window.location.href = "m/index";
		    			}
		    		}else{
		    			$("#showMsg").text("用户名或密码错误").fadeIn().fadeOut(5000);
		    		}
		    	}
		    });
	  	}
  </script>
  <style type="text/css">
  	.login-box, .register-box{
  		margin: 14% auto;
  	}
  </style>
</head>
<body class="hold-transition login-page" style="overflow-y:hidden;background-image:url(images/login-bg2.jpg);background-repeat:repeat;">
	<div class="login-box">
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">旅游网站系统登录</p>
			<div style="height: 30px;padding-bottom: 15px;text-align: center;color: red;" id="showMsg"></div>

			<form id="loginForm" action="" method="post">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="userName" id="userName"
						placeholder="用户名" /> <span
						class="glyphicon glyphicon-user form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password" id="password"
						placeholder="密码"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<!-- /.col -->
					<div class="col-sm-offset-4 col-sm-4">
						<input type="hidden" name="redirect" id="redirect" value="${redirect}" />
          				<button type="button" class="btn btn-primary btn-block btn-flat" onclick="login();">登录</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
		</div>
		<!-- /.login-box-body -->
	</div>
</body>
</html>
