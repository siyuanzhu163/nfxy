var managerManager = {
	initSelfManage: function() {
		$("#selfManage").validate({
			messages: {
				newPwdConfirm: {
					equalTo: "新密码和确认密码不一致"
				}
			},
			submitHandler: function(form) {
				$.ajax({
					url: contextPath + "/manager/manager/self",
					type: "POST",
					cache: false,
					data: {
						name: $.trim($(":input[name='name']").val()),
						oldPwd: $.trim($(":input[name='oldPwd']").val()),
						newPwd: $(":input[name='newPwd']").val()
					}, 
					dataType: "json",
					success: function(data) {
						AJAXUtils.success(data, function(content){
							alert("管理员信息修改成功");
						});
					}, 
					error: function() {
						AJAXUtils.error();
					}
				});
			}
		});
	},
	init: function() {
		var managerList = $.ajaxlist({
			url: contextPath + "/manager/manager/query",
			list: "#managers",
			listTemplate: "managerTemplate",
			pager: "#infoPager",
			pagerTemplate: "pagerTemplate"
		});
		
		$("#search").click(function(){
			managerList.reset({
				params: {
					keyword: $.trim($("#keyword").val())
				}
			});
			managerList.query(1);
		});
		
		$("#search").click();
	},
	enable: function(id) {
		if (confirm("您确定要启用此系统用户？")) {
			$.ajax({
				url: contextPath + "/manager/manager/" + id + "/enable",
				type: "POST",
				dataType: "json",
				success: function(data) {
					AJAXUtils.success(data, function(){
						$("#search").click();
					});
				},
				error: function() {
					AJAXUtils.error();
				}
			});
		}
	},
	disable: function(id) {
		if (confirm("您确定要停用此系统用户？")) {
			$.ajax({
				url: contextPath + "/manager/manager/" + id + "/disable",
				type: "POST",
				dataType: "json",
				success: function(data) {
					AJAXUtils.success(data, function(){
						$("#search").click();
					});
				},
				error: function() {
					AJAXUtils.error();
				}
			});
		}
	}
};