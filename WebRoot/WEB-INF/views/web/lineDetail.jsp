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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
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
      <h1>旅游线路-${lines.name}</h1>
    </div>
    <input type="hidden" name="beginLongitude" id="beginLongitude" value="${tLines.beginLongitude}">
	<input type="hidden" name="beginLatitude" id="beginLatitude" value="${tLines.beginLatitude}"></input>
	<input type="hidden" name="eginLongitude" id="eginLongitude" value="${tLines.eginLongitude}"></input>
	<input type="hidden" name="eginLatitude" id="eginLatitude" value="${tLines.eginLatitude}"></input>
    <div style="margin: 10px;padding: 15px;margin-top: 20px;border-radius: 6px;box-shadow: 1px 3px 11px 5px #dcd3c0;">
      	<div class="jdinfo" style="width: 1000px;height: 30px;font-size: 18px;font-weight: 600;"><h3>线路名称：${lines.name}</h3></div>
	    <div class="jdinfo" style="width:1000px;color:red">
	      	起点位置：${lines.beginAddress}
	      	-
	      	终点位置：${lines.endAddress}
	      </div>
	      <div class="jdinfo" style="width:1000px;display: initial;">
	    	<span>线路说明：${lines.introduce}</span>
	    </div>
	    
	    <div style="width:1140px;height:500px;margin-top: 50px;border:1px solid gray" id="mapDiv"></div
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


var map = new BMap.Map("mapDiv");
var s;//经度
var w;//纬度
map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);
map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件

var myP1 = new BMap.Point(106.521436,29.532288);    //起点-重庆
var myP2 = new BMap.Point(108.983569,34.285675);    //终点-西安
if(''!=$("#beginLongitude").val()){
	myP1 = new BMap.Point(Number($("#beginLongitude").val()),Number($("#beginLatitude").val()));
}

if(''!=$("#eginLongitude").val()){
	myP2 = new BMap.Point(Number($("#eginLongitude").val()),Number($("#eginLatitude").val()));
}

map.addEventListener("rightclick",function(e){
	if(e.overlay){//判断右键单击的是否是marker
		
	}else{
		s = e.point.lng;//经度
		w = e.point.lat;//维度
		RightClick();//右键单击map出现右键菜单事件
	}
});

window.run = function (){
    map.clearOverlays();                        //清除地图上所有的覆盖物
    var driving = new BMap.DrivingRoute(map);    //创建驾车实例
    driving.search(myP1, myP2);                 //第一个驾车搜索
    driving.setSearchCompleteCallback(function(){
        var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组        
		var polyline = new BMap.Polyline(pts);             
		map.addOverlay(polyline);                
		var m1 = new BMap.Marker(myP1);         //创建3个marker        
		var m2 = new BMap.Marker(myP2);        
		map.addOverlay(m1);        
		map.addOverlay(m2);                 
		var lab1 = new BMap.Label("起点",{position:myP1});      
		var lab3 = new BMap.Label("终点",{position:myP2});           
		map.addOverlay(lab1);         
		map.addOverlay(lab3);                
		setTimeout(function(){            
			map.setViewport([myP1,myP2]);          //调整到最佳视野        
			},1000);            
	});
}

run();
</script>
</body>
</html>