/**
 * 
 */

function addPOrder(user_id,package_id,price){
	if(confirm("确定要购买该套餐吗？")){
		$.ajax({
			url : 'w/order/create',// 跳转到 action  
			type : 'post',
			cache : false,
			data : {userId:user_id,type:1,packageId:package_id,amount:price},
			dataType : "json",
			success : function(data) {
				if(data.code == 1){
					alert('购买成功！');
				}else{
					alert(data.message);
				}
			},
			error : function() {
				//TODO:请求错误
			}
		});
	}
}



function recharge(user_id){
	var amount=$("input[name='amount']:checked").val();
	if(confirm("确定要充值"+amount+"积分吗？")){
		$.ajax({
			url : 'w/order/create',// 跳转到 action  
			type : 'post',
			cache : false,
			data : {userId:user_id,type:3,amount:amount},
			dataType : "json",
			success : function(data) {
				if(data.code == 1){
					$.ajax({
						url : 'w/user/update',// 跳转到 action  
						type : 'post',
						cache : false,
						data : {integral:amount},
						dataType : "json",
						success : function(data) {
							if(data.code == 1){
								alert('充值成功！');
								location.reload(); 
							}else{
								alert(data.message);
							}
						},
						error : function() {
							//TODO:请求错误
						}
					});
				}else{
					alert(data.message);
				}
			},
			error : function() {
				//TODO:请求错误
			}
		});
	}
	
}

function leaveMsg(user_id){
	if(confirm("确定要发送留言吗？")){
		var msg=$("#leaveMsg").val();
		$.ajax({
			url : 'w/leave/create',// 跳转到 action  
			type : 'post',
			cache : false,
			data : {userId:user_id,msg:msg},
			dataType : "json",
			success : function(data) {
				if(data.code == 1){
					alert('新增成功！');
				}else{
					alert(data.message);
				}
			},
			error : function() {
				//TODO:请求错误
			}
		});
	}
}