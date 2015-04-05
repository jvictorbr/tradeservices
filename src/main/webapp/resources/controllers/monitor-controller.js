app.controller("monitorController", function($scope, $http, $location) {
	
	$scope.viewDetails = function($event, reqId) {
		$location.path('/messageDetails/'+reqId);
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
			{name: 'View Details', field: 'requestId', cellTemplate: '<div class="text-center"><button type="button" class="btn-xs" ng-click="grid.appScope.viewDetails($event, row.entity.requestId);">Details</button></div>'}
		],
		paginationPageSizes: [25, 50, 75],
		paginationPageSize: 25
	};
	

	
	$scope.fetchServerInfo = function() {
		
		 showGlass();
		
		var url = "http://localhost:8080/tradeservices/ws/tlmonitor/messages";
		var params = {}; 
		
		if ($scope.isError != undefined && $scope.isError != "") {
			params.isError = $scope.isError;
		}
		if ($scope.from) { 
			params.from = $scope.from;			
		}
		if ($scope.to) { 
			params.to = $scope.to;			
		}		
		
		if (!$.isEmptyObject(params)) {
			url = url + '?' + $.param(params);
		}	
		
		$http
			.get(url)
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