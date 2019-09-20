var callback;
var upload = {
	fileSelected:function(url) {
	    var file = document.getElementById('fileToUpload').files[0];
        if (file) {
        	var fd = new FormData();
	 	    fd.append("fileToUpload", file);
	 	    var xhr = new XMLHttpRequest();
	 	    xhr.upload.addEventListener("progress", upload.uploadProgress, false);
	 	    xhr.addEventListener("load", upload.uploadComplete, false);
	 	    xhr.addEventListener("error", upload.uploadFailed, false);
	 	    xhr.addEventListener("abort", upload.uploadCanceled, false);
	 	    xhr.open("POST", url);
	 	    xhr.send(fd);
        }
	},

	uploadFile:function() {
		$("#fileToUpload").click();
	},

	uploadProgress:function(evt) {
	    if (evt.lengthComputable) {
	        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
	        $('#progressNumber').progressbar('setValue', percentComplete);
	    }
	    else {
	        $('#progressNumber').innerHTML = '无法计算';
	    }
	},

	uploadComplete:function(evt) {
	    /* 服务器返回数据*/
	    var obj = eval('(' + evt.target.responseText+ ')');
	    if(callback){
	    	callback(obj);
	    }
	},

	uploadFailed:function(evt) {
	    alert("上传出错.");
	},

	uploadCanceled:function(evt) {
	    alert("上传已由用户或浏览器取消删除连接.");
	},	
};
