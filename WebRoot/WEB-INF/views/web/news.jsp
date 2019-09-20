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
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/superslide.2.1.js"></script>
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
jQuery(".fullSlide").hover(function() {
    jQuery(this).find(".prev,.next").stop(true, true).fadeTo("show", 0.5)
},
function() {
    jQuery(this).find(".prev,.next").fadeOut()
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
<div id="container">
  <div class="column">
    <div class="title">
      <h1>旅游新闻</h1>
    </div>
    <ul class="list" style="margin-bottom:30px">
      <c:forEach var="act" items="${newsList}" varStatus="status" end="4">
		<li class="li1" style="width: 1000px;margin-left: 10px;padding: 15px;height: auto;border-radius: 6px;box-shadow: 0px 0px 11px 5px #dcd3c0;"> 
			<span class="jdpic" style="float: left;"><a href="#"><img src="${act.image}" width="224" height="148" /></a></span></span>
			<div style=" margin-left: 240px;width: 600px;">
			
			<div class="jdinfo" style="width: 730px;height: 30px;font-size: 18px;font-weight: 600;"><h3>${act.title}</h3></div>
	        <div class="jdinfo" style="width:730px;">
	        	${act.createTime}
	        </div>
	        <div class="jdinfo" style="width:730px">
	        	<span>摘要：${act.abstracts}</span><a href="w/newsDetail/${act.id}"><span class="price">详情</span></a></div>
			</div>
			
			
	      </li>
	</c:forEach>
	<c:if test="${empty  newsList}"><div style="text-align: center;padding-top: 50px;font-size: 14px;">暂无新闻</div></c:if>
    </ul>
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
</body>
</html>