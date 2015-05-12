var infoEditor = (function(){
	var coverUploader = new pictureUploader({
	    container: "container",
	    button: "upload",
	    url: contextPath + "/manager/picture/INFO_COVER",
	    picture: "cover",
	    loading: "loading"
	});
	coverUploader.init();

	var contentEditor = UE.getEditor('content',{
	    serverUrl: contextPath + "/manager/ueditor",
	    initialFrameWidth: 900,
	    initialFrameHeight: 300
	});
	
	$("#info").validate({
		submitHandler: function(form) {
			var cover = $("#cover").attr("src").replace(contextPath + "/img/blank.jpg", "");
			if (cover == "") {
				alert("请上传封面图片");
				return;
			}
			var content = contentEditor.getContentTxt();
			if (content == "") {
				alert("请录入正文内容");
				return;
			}
			
			var info = {
				id: $("#id").val(),
				part: $("#part").val(),
				type: $("#type").val(),
				title: $("#title").val(),
				author: $("#author").val(),
				cover: cover,
				summary: $("#summary").val(),
				content: contentEditor.getContent()
			};
			
			var url = (info.id == "") ? contextPath + "/manager/info/" + info.part + "/add"
					: contextPath + "/manager/info/" + info.part + "/" + info.id;
			$.ajax({
				url: url,
				type: "POST",
				cache: false,
				data: info, 
				dataType: "json",
				success: function(data) {
					AJAXUtils.success(data, function(content){
						alert("资讯编辑成功");
					});
				}, 
				error: function() {
					AJAXUtils.error();
				}
			});
		}
	});
})();