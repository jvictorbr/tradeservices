app.directive('combobox', function() {
	return {
		restrict : 'A',
		require : 'ngModel',
		compile : function compile(element, attrs, transclude) {
			return {

				pre : function preLink($scope, element, attrs, controller) {
					$(element).parent().find("li").click(
							function(e) {
								var selectedText = $(element).text();
								controller.$setViewValue($(this).attr("combobox-value"));
							});
				}

			}
		}
	}
});

app.directive('comboboxOnchange', function() {
	return {
		restrict : 'EA',
		compile : function compile(element, attrs, transclude) {
			return {
				
				post : function postLink($scope, element, attrs, controller) {
					$(element).parent().find("li").click(function(e) {
						eval("$scope." + attrs.comboboxOnchange);
						e.preventDefault();
					});

				}
			}
		}

	}
});
