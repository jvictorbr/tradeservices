<style type="text/css">	 	
 	.grid {
 		height: 550px; 
 	}	
	 	
 	.minHeight {
 		min-height: 32px;
 	}
</style>

<div class="row">

	<div class="col-md-2">
		<input id="date-picker-from" datepicker ng-model="from" placeholder="From..." type="text" class="date-picker form-control input-block-level minHeight" />
	</div>
	
	<div class="col-md-2">
		<input id="date-picker-to" datepicker ng-model="to" placeholder="To..." type="text" class="date-picker form-control input-block-level minHeight" />
	</div>
  		
  	<div class="col-md-2">
  		<select class="form-control" ng-model="isError" id="sel1">
        	<option value="">All</option>        	
        	<option value="false">Success</option>
        	<option value="true">Error</option>
      	</select>
	</div>
	
	<div class="col-md-1">
		<button type="button" class="btn btn-default btn-sm minHeight" ng-click="fetchServerInfo()">
			<span class="glyphicon glyphicon-refresh"></span>
		</button>
	</div>		
  			   		
</div>


		
<div class="row" style="margin-top: 15px">
	<div class="col-md-12">
		
		<div id="grid1" ui-grid="gridOptions" class="grid" ui-grid-resize-columns ui-grid-pagination ui-i18n="en"></div>
		
	</div>		
</div>	

<script type="text/javascript">
	
	$(document).ready(function() {
		$('.date-picker').datepicker();		
		$(".dropdown-menu li a").click(function(){
			  var selText = $(this).text();
			  $(this).parents('.dropdown').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
		});		
	});

</script>