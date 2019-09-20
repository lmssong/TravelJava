<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="utf-8">
<title>线路管理</title>
<%@include file="../common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="css/manage.css" />
<script type="text/javascript" src="plugins/other/ajaxfileupload.js"></script>
<script type="text/javascript" src="plugins/other/upload.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
</head>
<body class="easyui-layout" style="overflow: auto">
	<div class="easyui-panel" title="编辑线路管理信息">
		<form id="tLinesForm" method="post">
			<table cellpadding="10" align="center"
				style="width: 60%; margin-top: 30px;">
				<input type="hidden" id="id" name="id" value="${id}" />
				<input type="hidden" name="beginLongitude" id="beginLongitude" value="${tLines.beginLongitude}">
				<input type="hidden" name="beginLatitude" id="beginLatitude" value="${tLines.beginLatitude}"></input>
				<input type="hidden" name="eginLongitude" id="eginLongitude" value="${tLines.eginLongitude}"></input>
				<input type="hidden" name="eginLatitude" id="eginLatitude" value="${tLines.eginLatitude}"></input>
				
				<tr>
					<td class="td_title">线路名称:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						id="name" value="${tLines.name}" style="width: 330px;"></input></td>
				<tr>
				<tr>
					<td class="td_title">起点地址:</td>
					<td><input class="easyui-textbox" type="text"
						name="beginAddress" id="beginAddress"
						value="${tLines.beginAddress}" style="width: 330px;"></input></td>
				<tr>
				<tr>
					<td class="td_title">终点地址:</td>
					<td><input  class="easyui-textbox" type="text" name="endAddress" id="endAddress"
						value="${tLines.endAddress}" style="width: 330px;"></input></td>
				<tr>
				<tr>
					<td class="td_title">线路介绍:</td>
					<td><input class="easyui-textbox" type="text" name="introduce"
						data-options="multiline:true" id="introduce"
						value="${tLines.introduce}" style="width: 330px; height: 50px"></input>
					</td>
				<tr>
				
				<tr>
					<td colspan="2"><div style="width:640px;height:330px;border:1px solid gray" id="container"></div></td>
				</tr>

				<tr>
					<td colspan="4" style="padding: 20px 0px 0px 120px;"><a
						href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'" id="btn_sub"> &nbsp;保 存
							&nbsp;</a>&nbsp;&nbsp; <a href="javascript:void(0);"
						class="easyui-linkbutton" data-options="iconCls:'icon-back'"
						id="btn_back"> &nbsp;返 回 &nbsp;</a>&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	var map = new BMap.Map("container");
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


	//右键单击map出现右键菜单事件
	function RightClick(){
	    var setBeginMarker = function(map){//右键更新站名
	        if (confirm("要设置为起点吗？")){
	            if(true){
	            	$("#beginLongitude").val(s);
					$("#beginLatitude").val(w);
	                myP1 = new BMap.Point(s,w); 
					run();
	            }
	        }    
	    };
		var setEndMarker = function(map){//右键更新站名
	        if (confirm("要设置为终点吗？")){
	            if(true){
	            	myP2 = new BMap.Point(s,w); 
	            	$("#eginLongitude").val(s);
					$("#eginLatitude").val(w);
					run();
	            }
	        }    
	    };
	    var markerMenu=new BMap.ContextMenu();
	    markerMenu.addItem(new BMap.MenuItem('设为起点',setBeginMarker.bind(map)));
		markerMenu.addItem(new BMap.MenuItem('设为终点',setEndMarker.bind(map)));
	    map.addContextMenu(markerMenu);//给标记添加右键菜单
	}

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
	
	
	
	
		$(function() {
			window.top.document.title = "新增线路管理信息";
			$("#tbtn_sub").bind("click", function() {
				tLines.addtLineserList();
			});
			if ($("#id").val()) {
				$("#btn_sub").bind("click", function() {
					tLines.editTeach();
				});
			} else {
				$("#btn_sub").bind("click", function() {
					tLines.addTeach();
				});
			}
			;
			$("#btn_back").bind("click", function() {
				window.location.href = "m/tLines/list";
			});
			
			run();
		});
		var tLines = {};
		tLines.addTeach = function() {
			if ($("#tLinesForm").form("validate")) {
				$.ajax({
					url : 'm/tLines/create',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tLinesForm').serialize(),
					dataType : "json",
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '新增成功！');
							window.location.href = "m/tLines/list";//跳转到列表页
						} else {
							$.messager.alert('操作提示', data.message);
						}
					},
					error : function() {
						//TODO:请求错误
					}
				});
			} else {
				$.messager.alert("操作提示", "表单验证不通过");
			}
		};

		tLines.uploadCover = function(fileId, img, picId) {
			$.ajaxFileUpload({
				url : "m/tLines/upload", //需要链接到服务器地址 
				secureuri : false,
				fileElementId : fileId,//文件选择框的id属性
				dataType : 'json', //json
				success : function(obj) {
					$("#" + img).attr("src", obj.convertFileName).show();
					$("#" + picId).val(obj.newFileName);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert('操作提示', '上传失败！');
				}
			});
		};

		tLines.editTeach = function() {
			if ($("#tLinesForm").form("validate")) {
				$.ajax({
					url : 'm/tLines/update',// 跳转到 action  
					type : 'post',
					cache : false,
					data : $('#tLinesForm').serialize(),
					dataType : 'json',
					success : function(data) {
						if (data.code == 1) {
							$.messager.alert('操作提示', '修改成功！');
							window.location.href = "m/tLines/list";//跳转到列表页
						} else {
							$.messager.alert('操作提示', data.message);
						}
					},
					error : function() {
						//TODO:请求错误
					}
				});
			} else {
				$.messager.alert("操作提示", "表单验证不通过");
			}
		};
	</script>
</body>
</html>