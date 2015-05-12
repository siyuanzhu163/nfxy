var infobannerStatistic = {
	init: function(){
		var bannerList = $.ajaxlist({
		    url: contextPath + "/manager/statistic/infobanner/banner/new",
		    list: "#banners",
		    listTemplate: "bannerTemplate",
		    pager: "#bannerPager",
		    pagerTemplate: "pagerTemplate",
		    pageSize: 5
		});

		bannerList.query(1);

		var infoList = $.ajaxlist({
		    url: contextPath + "/manager/statistic/infobanner/info/new",
		    list: "#infos",
		    listTemplate: "infoTemplate",
		    pager: "#infoPager",
		    pagerTemplate: "pagerTemplate",
		    pageSize: 5
		});

		infoList.query(1);

		$(function(){
		    $("#parts li").click(function(){
		        var curPart = $("#parts li.active").attr("part");
		        var part = $(this).attr("part");
		        if (curPart != part) {
		            $("#parts li.active").removeClass("active");
		            $(this).addClass("active");
		            bannerList.reset({
		                url: contextPath + "/manager/statistic/infobanner/banner/" + part,
		                pageSize: 5
		            });
		            bannerList.query(1);
		            infoList.reset({
		                url: contextPath + "/manager/statistic/infobanner/info/" + part,
		                pageSize: 5
		            });
		            infoList.query(1);
		        }
		    });
		});
	}
};