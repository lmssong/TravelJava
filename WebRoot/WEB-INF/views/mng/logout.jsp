<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
	<script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
	<title>旅游网站管理平台</title>
	<script type="text/javascript">
		function afterLoad(msg){
			if(msg!=''&&msg!=null){
				alert(msg);
			}
			window.top.location='m/index';
		};
	</script>
	</head>
	<body onload="afterLoad('${msg}');">
	</body>
</html>
