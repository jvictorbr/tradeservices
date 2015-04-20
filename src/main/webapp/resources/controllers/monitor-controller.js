app.controller("monitorController", function($scope, $location, tlMonitorService) {
	
	$scope.viewDetails = function($event, reqId) {
		$location.path('/messageDetails/'+reqId);
	}

	$scope.shouldShow = function(reqId) {
		return reqId.indexOf("ManCallback_") != 0;
	}
	
	$scope.gridOptions = {
		enableFiltering: true,
		columnDefs: [
			{name: 'Date', field: 'messageDate'},
			{name: 'Code', field: 'messageCode'},
			{name: 'Error', field: 'isError'},
			{name: 'Request#', field: 'requestId'},
			{name: 'Deal#', field: 'dealId'},
			{name: 'Bacen#', field: 'bacenContractId'},
			{name: 'Year', field: 'bacenContractYear'},
			{name: 'Contract#', field: 'fxContractId'},
			{name: 'Message', field: 'messageDescription'},
			{name: 'View Details', field: 'requestId', cellTemplate: '<div class="text-center"><button type="button" ng-show="grid.appScope.shouldShow(\'{{COL_FIELD}}\')" class="btn-xs" ng-click="grid.appScope.viewDetails($event, row.entity.requestId);">Details</button></div>'}
		],
		paginationPageSizes: [25, 50, 75],
		paginationPageSize: 25
	};
	

	
	$scope.fetchServerInfo = function() {
		
		 showGlass();
		 
		 tlMonitorService
		 	.getMessages($scope.isError, $scope.from, $scope.to)
		 	.success(function($response) {				
				$scope.gridOptions.data = $response.messages;
				hideGlass();
			})
			.error(function($response){				
				showErrorMessage(handleErrorResponse($response));
				hideGlass();
			});
		 
	}
	
	$scope.fetchServerInfo();
	
});