/**
 * Created by hqsunc on 2016/7/21.
 */

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
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
	time2Stamp : function(strTime) {
		var timestamp = Date.parse(new Date(strTime));
		return timestamp;
	},
	second2Time : function(second){
		if(second == null){
			return "00:00:00";
		}
		var hour = parseInt(second/3600);
		var minute = parseInt((second%3600)/60);
		var seconds = (second%3600)%60;
		if(hour < 10){
			hour = "0"+hour;
		}
		if(minute < 10){
			minute = "0"+minute;
		}
		if(seconds < 10){
			seconds = "0"+seconds;
		}
			return hour + ":" + minute + ":" + seconds;
		
	},
	stamp2Time : function(timestamp) {
		if (timestamp != 0) {
			var newDate = new Date();
			newDate.setTime(timestamp);
			return newDate.format('yyyy-MM-dd hh:mm:ss');
		} else {
			return '';
		}
	},
	stamp2Date : function(timestamp) {
		if (timestamp != 0) {
			var newDate = new Date();
			newDate.setTime(timestamp);
			return newDate;
		} else {
			return '';
		}
	},
	getDateDiff:function(dateTimeStamp) {
		var minute = 1000 * 60;
		var hour = minute * 60;
		var day = hour * 24;
		var halfamonth = day * 15;
		var month = day * 30;
		var now = new Date().getTime();
		var diffValue = now - dateTimeStamp;
		if (diffValue < 0) {
			return;
		}
		var monthC = diffValue / month;
		var weekC = diffValue / (7 * day);
		var dayC = diffValue / day;
		var hourC = diffValue / hour;
		var minC = diffValue / minute;
		if (monthC >= 1) {
			result = "" + parseInt(monthC) + "月前";
		} else if (weekC >= 1) {
			result = "" + parseInt(weekC) + "周前";
		} else if (dayC >= 1) {
			result = "" + parseInt(dayC) + "天前";
		} else if (hourC >= 1) {
			result = "" + parseInt(hourC) + "小时前";
		} else if (minC >= 1) {
			result = "" + parseInt(minC) + "分钟前";
		} else
			result = "刚刚";
		return result;
	},

	getDateTimeStamp:function(dateStr) {
		return Date.parse(dateStr.replace(/-/gi, "/"));
	}
};

Date.prototype.format = function(format) {
	var date = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S+" : this.getMilliseconds()
	};
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '')
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in date) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k]
					: ("00" + date[k]).substr(("" + date[k]).length));
		}
	}
	return format;
}

var config = {
	/**
	 * 格式化数据
	 */
	fmtConfig : function(val, type) {
		if (type == '2') {
			if (val == '101') {
				return '难';
			} else if (val == '102') {
				return '较难';
			} else if (val == '103') {
				return '中';
			} else if (val == '104') {
				return '较容易';
			} else if (val == '105') {
				return '容易';
			}
		} else if (type == '1') {
			if (val == '201') {
				return '单选题';
			} else if (val == '202') {
				return '多选题';
			} else if (val == '203') {
				return '简答题';
			} else if (val == '204') {
				return '填空题';
			} else if (val == '205') {
				return '论述题';
			} else if (val == '206') {
				return '判断题';
			}
		}
	}
};

Array.prototype.indexOf = function(el) {
	for (var i = 0, n = this.length; i < n; i++) {
		if (this[i] === el) {
			return i;
		}
	}
	return -1;
}

var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/
			.test(value));
};


// 身份证号合法性验证
// 支持15位和18位身份证号
// 支持地址编码、出生日期、校验位验证
function IdentityCodeValid(code) {
	var city = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江 ",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北 ",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏 ",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外 "
	};
	var tip = "";
	var pass = true;

	if (!code
			|| !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i
					.test(code)) {
		tip = "身份证号格式错误";
		pass = false;
	}

	else if (!city[code.substr(0, 2)]) {
		tip = "地址编码错误";
		pass = false;
	} else {
		// 18位身份证需要验证最后一位校验位
		if (code.length == 18) {
			code = code.split('');
			// ∑(ai×Wi)(mod 11)
			// 加权因子
			var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
			// 校验位
			var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
			var sum = 0;
			var ai = 0;
			var wi = 0;
			for (var i = 0; i < 17; i++) {
				ai = code[i];
				wi = factor[i];
				sum += ai * wi;
			}
			var last = parity[sum % 11];
			if (parity[sum % 11] != code[17]) {
				tip = "校验位错误";
				pass = false;
			}
		}
	}
	if (!pass)
		alert(tip);
	return pass;
}

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);