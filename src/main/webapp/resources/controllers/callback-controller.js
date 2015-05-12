app.controller("callbackController", function($scope, $routeParams, $location, callbackService) {

	$scope.getCallback = function() { 
		
		showGlass();
		$("#results").removeClass("visible").addClass("hidden");
		
		callbackService.getCallback($scope.cnpj, $scope.account,
				function(response) { 
					$scope.callbackResponse = response;
					$("#results").removeClass("hidden").addClass("visible");
					hideGlass();
				},
				function(error) { 
					showErrorMessage(handleErrorResponse(error));
					hideGlass();
				}
		
		);

	}
	
	
});