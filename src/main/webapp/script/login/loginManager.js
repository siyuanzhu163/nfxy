var loginManager = {
	init: function() {
		$("#login").validate({
			submitHandler: function(form) {
				$.ajax({
					url: contextPath + "/manager/login",
					type: "POST",
					cache: false,
					data: {
						name: $.trim($("#login :input[name='name']").val()),
						password: $.trim($("#login :input[name='password']").val()),
						saveName: $("#login :checkbox[name='saveName']").is(":checked")
					}, 
					dataType: "json",
					success: function(data) {
						AJAXUtils.success(data, function(content){
							window.location.replace(contextPath + "/manager/index");
						});
					}, 
					error: function() {
						AJAXUtils.error();
					}
				});
			}
		});
	}
};
