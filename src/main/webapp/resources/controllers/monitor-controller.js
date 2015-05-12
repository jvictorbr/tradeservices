app.factory('monitorControllerFilters', function() {
	
	var filters = {		
			isError: "",
			from: new Date().toLocaleDateString(),
			to: new Date().toLocaleDateString()
	};
	
    return {
        getFilters: function () {
            return filters;
        },
        setFilters: function (isError, from, to) {
        	filters.isError = isError;
            filters.from = from;
            filters.to = to;
        }
    };
	
});


app.controller("monitorController", function($scope, $location, tlMonitorService, monitorControllerFilters) {
		
	$scope.viewDetails = function($event, row) {
		
		$location.path('/messageDetails/'+row.requestId);
		
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
			{name: 'View Details', field: 'requestId', cellTemplate: '<div class="text-center"><button type="button" ng-show="grid.appScope.shouldShow(\'{{COL_FIELD}}\')" class="btn-xs" ng-click="grid.appScope.viewDetails($event, row.entity);">Details</button></div>'}
		],
		paginationPageSizes: [25, 50, 75],
		paginationPageSize: 25
	};
	

	
	$scope.fetchServerInfo = function() {
		
		 showGlass();
		 
		 monitorControllerFilters.setFilters($scope.isError, $scope.from, $scope.to);
		
		 tlMonitorService.getMessages($scope.isError, $scope.from, $scope.to, 
			function(response) { 
			 $scope.gridOptions.data = response.messages;
			 hideGlass();
		 }, function(response) { 
			 showErrorMessage(handleErrorResponse(response));
			 $scope.gridOptions.data = [];
			 hideGlass();
		 });

	}
	
	$scope.isError = monitorControllerFilters.getFilters().isError; 
	$scope.from = monitorControllerFilters.getFilters().from;
	$scope.to = monitorControllerFilters.getFilters().to;
	$scope.fetchServerInfo();
	
});