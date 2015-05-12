var bannerManager = (function(){
	var bannerList = $.ajaxlist({
		url: contextPath + "/manager/banner/query",
		list: "#banners",
		listTemplate: "bannerTemplate",
		pager: "#bannerPager",
		pagerTemplate: "pagerTemplate"
	});
	
	this.init = function() {
		this.query();
		$("#search").click(function(){
			bannerManager.query();
		});
	}
	
	this.query = function() {
		bannerList.reset({
			params: {
				keyword: $.trim($("#keyword").val())
			}
		});
		bannerList.query(1);
	}
	
	this.del = function(id) {
		if (confirm("您确定要删除此轮播图？")) {
			$.ajax({
				url: contextPath + "/manager/banner/" + id,
				type: "DELETE",
				dataType: "json",
				success: function(data) {
					AJAXUtils.success(data, function(){
						bannerList.query();
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