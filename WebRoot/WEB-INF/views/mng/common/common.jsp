<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<base href="http://127.0.0.1:8080/tourism/" />
<!-- <link rel="shortcut icon" href="image/favicon.ico" /> -->
<link rel="stylesheet" type="text/css" href="plugins/easyui/css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<link rel="stylesheet" type="text/css" href="css/wu.css" />

<script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="plugins/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="plugins/easyui/js/easyui-lang-zh_CN.js"></script>


<script type="text/javascript" src="js/common/manage.js"></script>
<% 
	request.setAttribute("basePath",request.getContextPath());
%>
<script type="text/javascript">
	var basePath = '${basePath}';
</script>
