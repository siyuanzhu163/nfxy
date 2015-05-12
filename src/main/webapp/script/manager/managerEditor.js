var managerEditor = (function(){
	this.init = function() {
		$("#manager").validate({
			messages: {
				passwordConfirm: {
					equalTo: "密码和确认密码不一致"
				}
			},
			submitHandler: function(form) {
				var manager = {};
				manager.id = $("#id").val();
				manager.name = $.trim($("#name").val());
				manager.password = $.trim($("#password").val());
				$(":input[name='menus']:checked").each(function(index){
					manager["menus[" + index + "].id"] = $(this).val();
				});
				
				var url = "";
				if (manager.id == "") {
					var exist = false;
					$.ajax({
						url: contextPath + "/manager/manager/exist",
						type: "POST",
						cache: false,
						async: false,
						data: {
							name: manager.name
						}, 
						dataType: "json",
						success: function(data) {
							AJAXUtils.success(data, function(content){
								exist = content;
							});
						}, 
						error: function() {
							AJAXUtils.error();
						}
					});
					if (exist) {
						alert("用户名已存在");
						return;
					}
					url = contextPath + "/manager/manager/add";
				} else {
					url = contextPath + "/manager/manager/" + manager.id + "/edit";
				}
				
				$.ajax({
					url: url,
					type: "POST",
					cache: false,
					data: manager, 
					dataType: "json",
					success: function(data) {
						AJAXUtils.success(data, function(content){
							alert("系统用户编辑成功");
						});
					}, 
					error: function() {
						AJAXUtils.error();
					}
				});
			}
		});
	}
	
	return this;
})();