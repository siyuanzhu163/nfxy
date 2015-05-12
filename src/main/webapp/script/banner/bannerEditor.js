var bannerEditor = (function(){
	var coverUploader = new pictureUploader({
	    container: "container",
	    button: "upload",
	    url: contextPath + "/manager/picture/BANNER",
	    picture: "picture",
	    loading: "loading"
	});
	coverUploader.init();
	
	var typeahead = jQuery.fn.typeahead.noConflict();
	jQuery.fn.typeahead = typeahead;
	var _selected = false;
	$("#infoTitle").typeahead({
	    highlight: true
	},{
	    source: function(keyword, process) {
	        _selected = false;
	        var items = [];
	        $.ajax({
	            url: contextPath + "/manager/search/info",
	            type: "POST",
	            async: false,
	            data: {
	                pageSize: 5,
	                pageNumber: 1,
	                keyword: keyword
	            },
	            dataType: "json",
	            success: function(data) {
	                AJAXUtils.success(data, function(content){
	                    for (var i in content) {
	                        items = content;
	                    }
	                });
	            },
	            error: function() {
	                AJAXUtils.error();
	            }
	        });
	        return process(items);
	    },
	    displayKey: "title"
	}).on("typeahead:selected", function(event, item, name){
	    _selected = true;
	    $("#infoId").val(item.id);
	});

	$("#infoTitle").blur(function(){
	    if (!_selected) {
	        $("#infoId").val("");
	        $(this).val("");
	    }
	});
	
	$("#banner").validate({
		submitHandler: function(form) {
			var picture = $("#picture").attr("src").replace(contextPath + "/img/blank.jpg", "");
			if (picture == "") {
				alert("请上传轮播图");
				return;
			}
			
			var banner = {
				id: $("#id").val(),
				part: $("#part").val(),
				title: $("#title").val(),
				picture: picture,
				summary: $("#summary").val(),
				"info.id": $("#infoId").val(),
				"info.title": $("#infoTitle").val(),
				url: $("#url").val(),
				weight: $("#weight").val()	
			};
			
			var url = (banner.id == "") ? contextPath + "/manager/banner/add"
					: contextPath + "/manager/banner/" + banner.id;
			$.ajax({
				url: url,
				type: "POST",
				cache: false,
				data: banner, 
				dataType: "json",
				success: function(data) {
					AJAXUtils.success(data, function(content){
						alert("轮播图编辑成功");
					});
				}, 
				error: function() {
					AJAXUtils.error();
				}
			});
		}
	});
	
})();