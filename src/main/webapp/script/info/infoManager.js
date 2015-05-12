var infoManager = (function(){
	var infoList = $.ajaxlist({
		url: contextPath + "/manager/info/" + part + "/query",
		list: "#infos",
		listTemplate: "infoTemplate",
		pager: "#infoPager",
		pagerTemplate: "pagerTemplate"
	});
	
	this.init = function() {
		this.query();
		$("#search").click(function(){
			infoManager.query();
		});
	}
	
	this.query = function() {
		infoList.reset({
			params: {
				keyword: $.trim($("#keyword").val())
			}
		});
		infoList.query(1);
	}
	
	this.del = function(id) {
		if (confirm("您确定要删除此资讯？")) {
			$.ajax({
				url: contextPath + "/manager/info/" + part + "/" + id,
				type: "DELETE",
				dataType: "json",
				success: function(data) {
					AJAXUtils.success(data, function(){
						infoList.query();
					});
				},
				error: function() {
					AJAXUtils.error();
				}
			});
		}
	}
	
	return this;
})();