app.controller("messageDetailsController", function($scope, $routeParams, $location, tlMonitorService) {
	
	showGlass();
	
	tlMonitorService.getMessageDetails($routeParams.requestId)
		.success(function($response) {
			$scope.gridOptions.data = $response.messageDetails;
			hideGlass();
		}).error(function($response) {
			showErrorMessage(handleErrorResponse($response));
			hideGlass();
		});
	
	$scope.viewXml = function($event, messageDetails) { 
		
		$scope.messageXml = $.trim(vkbeautify.xml(messageDetails.messageContent));
		$("#messageXmlModal").modal('show');
		
	}
	
	$scope.gridOptions = {
			columnDefs: [
			             {name: 'Date', field: 'messageDate'},
			             {name: 'Request#', field: 'requestId'},
			             {name: 'Message Type', field: 'messageType'},
			             {name: 'View Message', cellTemplate: '<div class="text-center"><button type="button" class="btn-xs" ng-click="grid.appScope.viewXml($event, row.entity);">XML</button></div>'}
			             ]
	}
	
	$scope.goBack = function() { 
		$location.path("/messages");
	}
	
});