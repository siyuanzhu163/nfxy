var pictureUploader = function(options) {
	return new plupload.Uploader({
	    runtimes : 'html5,flash,silverlight,html4',
	    container: options.container,
	    browse_button : options.button,
	    url : options.url,
	    flash_swf_url : contextPath + '/script/plupload/Moxie.swf',
	    silverlight_xap_url : contextPath + '/script/plupload/Moxie.xap',
	    
	    filters : {
	        max_file_size : '10mb',
	        mime_types : [
	          { title : "Image files", extensions : "jpg,jpeg,gif,png" }
	        ]
	    },
	
	    init: {
	        FilesAdded: function(uploader, files) {
	        	if (files.length > 1) {
	        		alert("一次只能上传一个文件");
	        		return;
	        	}
	        	$("#" + options.button).attr("disabled", true);
	        	$("#" + options.picture).hide();
	        	$("#" + options.loading).show();
	        	uploader.start();
	        },
	        FileUploaded: function(up, file, info) {
	        	var data = eval("("+info.response+")");
	        	AJAXUtils.success(data, function(content){
	        		$("#" + options.picture).attr("src", content);
	        		$("#" + options.picture).show();
	        		$("#" + options.loading).hide();
	        		$("#" + options.button).attr("disabled", false);
	        	});
	        },
	        Error: function(up, err) {
	        	alert(err.code + ": " + err.message);
	        }
	    }
	});
}