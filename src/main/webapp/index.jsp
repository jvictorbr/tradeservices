<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
	
	<!--  JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/jquery-ui.min.js"></script>
	
	<!-- AngularJS -->
	<script	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular-touch.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular-animate.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular-route.min.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
    <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
	
	<!-- Bootstrap -->	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.css">	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	 
	 <!-- UI GRID -->
	 <script src="resources/ui-grid/ui-grid.min.js"></script>
	 <link rel="stylesheet" href="resources/ui-grid/ui-grid.min.css" />
	 <script type="text/javascript" src="http://angular-ui.github.com/ng-grid/lib/ng-grid.debug.js"></script>
	 
	 <!-- Citi CSS Theme -->	 
	 <link rel="stylesheet" href="resources/css/tradeservices-bootstrap.css">
	 
	 <!-- JQuery XML Pretty Print -->
	 <script type="text/javascript" src="resources/js/vkbeautify.0.99.00.beta.js"></script> 

	<!-- Util -->
	<script type="text/javascript" src="resources/js/util.js"></script>
	
</head>

<body ng-app="tradelayerMonitor" style="font-size: 13px">

<div class="container">
		
	<div ng-include="'resources/jsp/header.jsp'"></div>
	
	<div id="errorAlert" class="alert alert-danger alert-dismissible" style="display:none; margin-top: 10px; margin-bottom: 10px" role="alert">			
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<span>Put whatever error message here!</span>
	</div>	
	
	<br/>
	
	<ng-view></ng-view>
	
	<div id="glassModal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
 			<div class="modal-dialog modal-sm modal-vertical-centered">
	   			<div class="modal-content" style="padding: 20px">
	     				<img src="resources/img/ajax-loader.gif" class="img-responsive center-block" />
	   			</div>
 			</div>
	</div>
	
	<div ng-include="'resources/jsp/footer.jsp'"></div>	
  
</div>

</body>

<script type="text/javascript">

	var app = angular.module("tradelayerMonitor", ['ngRoute', 'ngTouch','ui.grid', 'ui.grid.resizeColumns', 'ui.grid.pagination'])
		.config(['$routeProvider', function($routeProvider) {
  			$routeProvider
				.when("/messages", {templateUrl: "resources/jsp/messages.jsp", controller: "monitorController"})
				.when("/messageDetails/:requestId", {templateUrl: "resources/jsp/messageDetails.jsp", controller: "messageDetailsController"})
				.otherwise({redirectTo: '/messages'});
		}]);
	
</script>



<script>

	$(document).ready(function() {
	
		$('.modal').on('show.bs.modal', centerModal);
		$(window).on("resize", function () {
		    $('.modal:visible').each(centerModal);
		});
		
	});
	
</script>

<script src="resources/directives/datepicker.js"></script>
<script src="resources/directives/combobox.js"></script>
<script src="resources/controllers/monitor-controller.js"></script>
<script src="resources/controllers/message-details-controller.js"></script>


</html>