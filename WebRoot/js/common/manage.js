/**
 * Created by hqsunc on 2016/7/21.
 */
$.fn.serializeObject = function(){  
   var o = {};  
   var a = this.serializeArray();  
   $.each(a, function() {  
       if (o[this.name]) {  
           if (!o[this.name].push) {  
               o[this.name] = [o[this.name]];  
           }  
           o[this.name].push(this.value || '');  
       } else {  
           o[this.name] = this.value || '';  
       }  
   });  
   return o;  
};

var DateUtil = {
	/**
	 * 字符串转时间戳
	 */
	time2Stamp:function(strTime){
		var timestamp = Date.parse(new Date(strTime));
		return timestamp;
	},
	stamp2Time:function(timestamp){
		if(timestamp!=0){
			var newDate = new Date();
			newDate.setTime(timestamp);
			return newDate.format('yyyy-MM-dd');
		}else{
			return '';	
		}
	}
};

Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}


Array.prototype.indexOf = function(el) {
	for (var i = 0, n = this.length; i < n; i++) {
		if (this[i] === el) {
			return i;
		}
	}
	return -1;
}

/****************扩展easyui表单的验证********************/
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉字
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字.'
    },
    //移动手机号码验证
    Mobile: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '手机号码格式错误.'
    },
    //国内邮编验证
    ZipCode: {
        validator: function (value) {
            var reg = /^[0-9]\d{5}$/;
            return reg.test(value);
        },
        message: '请输入邮政编码.'
    },
  //数字
    Number: {
        validator: function (value) {
            var reg =/^[0-9]*$/;
            return reg.test(value);
        },
        message: '请输入数字.'
    },
  //数字
    Capacity: {
        validator: function (value) {
        	if(value!='无限次'){
                var reg =/^[0-9]*$/;
                return reg.test(value);
        	}else{
        		return true;
        	}
        },
        message: '请输入数字.'
    },
    //金额
    Price: {
        validator: function (value) {
            var reg =/^\d+(?:\.\d{1,2})?$/;
            return reg.test(value);
        },
        message: '请输入金额.'
    },
    //身份证 
    IDCard: { 
    	validator:function (value){
    	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
    		var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
    		return  reg.test(value);  
       },
       message: '请输入合法的身份证号码.'
    },
    EqualTo: { 
    	validator: function (value, param) 
    	{ return $(param[0]).val() == value; }, 
    	message: '字段不匹配' 
    },
    LoginName: {  
        validator: function (value, param) {  
            return /^[a-zA-Z\d]\w{3,11}[a-zA-Z\d]$/.test(value);  
        },  
        message: '登录名称只允许汉字、英文字母、数字及下划线。'  
    },  
    Safepass: {  
        validator: function (value, param) {  
            return safePassword(value);  
        },  
        message: '密码由字母和数字组成，至少6位'  
    }
});

var safePassword = function (value) {  
    return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));  
};

/**
 * 扩展combox验证，easyui原始只验证select text的值，不支持value验证
 */
$.extend($.fn.validatebox.defaults.rules, {
    comboxValidate : {
        validator : function(value, param) {
            if(value && value.indexOf('请选择')==-1){
                return true;
            }
            return false;
        },
        message : "{1}"
    }
});
