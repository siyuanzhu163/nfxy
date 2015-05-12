(function($){
	$.extend({
		ajaxlist: function(options) {
			
			var _options_default = {
				url: "",
				method: "POST",
				params: {},
				list: "",
				listTemplate: "",
				pager: "",
				pagerTemplate: "",
				pageSizeList: [5, 10, 20],
				pageSize: 10,
				pageNumber: 1,
				pageCount: 0,
				totalCount: 0,
				itemResp: "items",
				totalCountResp: "totalCount"
			};
			
			var _options = $.extend(_options_default, options);
			
			// 页脚事件初始化
			var _initPagerEvent = function() {
				// 每页条数
				$(_options.pager).find("#pageSize").change(function(){
					_reset({
						pageSize: $(this).val()
					});
					_query(1);
				});
				
				// 首页
				$(_options.pager).find("#head").click(function(){
					if (_options.pageNumber > 1) {
						_query(1);
					}
				});
				
				// 上一页
				$(_options.pager).find("#pre").click(function(){
					if (_options.pageNumber > 1) {
						_query(_options.pageNumber-1);
					}
				});
				
				// 页码
				$(_options.pager).find("#pageNumber").keyup(function(){
					var pageNumber = $(this).val();
					pageNumber = pageNumber.replace(/[^0-9]/g, "");
					$(this).val(pageNumber);
				});
				$(_options.pager).find("#pageNumber").blur(function(){
					var pageNumber = $(this).val();
					if ("" == pageNumber) {
						$(this).val(_options.pageNumber);
						return;
					}
					pageNumber = parseInt(pageNumber, 10);
					if (pageNumber < 1 || pageNumber > _options.pageCount) {
						pageNumber = _options.pageNumber;
						$(this).val(pageNumber);
					}
					if (pageNumber != _options.pageNumber) {
						_query(pageNumber);
					}
				});
				
				// 下一页
				$(_options.pager).find("#next").click(function(){
					if (_options.pageNumber < _options.pageCount) {
						_query(_options.pageNumber+1);
					}
				});
				
				// 尾页
				$(_options.pager).find("#tail").click(function(){
					if (_options.pageNumber < _options.pageCount) {
						_query(_options.pageCount);
					}
				});
			};
			
			// 重置
			var _reset = function(options) {
				_options = $.extend(_options, options);
			}
			
			// 查询
			var _query = function(pageNumber) {
				if (pageNumber) {
					_options.pageNumber = pageNumber;
				}
				$.ajax({
					url: _options.url,
					type: _options.method,
					data: $.extend(_options.params, {
						pageSize: _options.pageSize,
						pageNumber: _options.pageNumber
					}),
					dataType: "json",
					success: function(data) {
						AJAXUtils.success(data, function(content){
							// 显示内容
							var items = content[_options.itemResp];
							// 本页记录为空时，跳转至上一页直至首页
							if (items.length == 0 && _options.pageNumber > 1) {
								_query(_options.pageNumber - 1);
								return;
							}
							$(_options.list).setTemplateElement(_options.listTemplate);
							$(_options.list).processTemplate(items);
							
							var totalCount = content[_options.totalCountResp];
							// 计算页数
							var pageCount = Math.floor(totalCount / _options.pageSize);
							pageCount = (totalCount % _options.pageSize == 0) ? pageCount : pageCount + 1;
							$.extend(_options, {
								pageNumber: pageNumber,
								pageCount: pageCount,
								totalCount: totalCount
							});
							
							// 显示页脚
							$(_options.pager).setTemplateElement(_options.pagerTemplate);
							$(_options.pager).processTemplate({
								totalCount: _options.totalCount,
								pageSizeList: _options.pageSizeList,
								pageSize: _options.pageSize,
								pageNumber: _options.pageNumber,
								pageCount: _options.pageCount
							});
							
							// 绑定事件
							_initPagerEvent();
						});
					},
					error: function() {
						AJAXUtils.error();
					}
				});
			};
			
			return {
				query: _query,
				reset: _reset
			};
		}
	});
})(jQuery);