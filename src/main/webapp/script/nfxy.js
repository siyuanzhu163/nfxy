/**
 * ajax请求相关
 */
var AJAXUtils = {
	_AJAX_SUCCESS: 200,
	_AJAX_FAIL: 500,
	/**
	 * 请求成功的处理方法
	 */
	success: function(data, contentProcessor) {
		if (data.status == this._AJAX_SUCCESS) {
			contentProcessor(data.content);
		} else if (data.status == this._AJAX_FAIL) {
			alert(data.msg);
		} else {
			alert("操作失败");
		}
	},
	/**
	 * 请求失败的处理方法
	 */	
	error: function() {
		alert("操作失败");
	}
};