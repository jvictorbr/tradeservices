app.controller("callbackController", function($scope, $routeParams, $location, callbackService) {

	$scope.getCallback = function() { 
		
		showGlass();
		$("#results").removeClass("visible").addClass("hidden");		
		
		callbackService
			.getCallback($scope.cnpj, $scope.account)
			.success(function($response) {			
				$scope.callbackResponse = $response;
				$("#results").removeClass("hidden").addClass("visible");
				hideGlass();
			})
			.error(function($response){				
				showErrorMessage(handleErrorResponse($response));
				hideGlass();
			});	
		
	}
	
	
});