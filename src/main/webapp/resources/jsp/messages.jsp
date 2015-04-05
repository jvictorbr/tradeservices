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
  		
  	<div class="col-md-1">
  		<div class="dropdown">
  			<button combobox combobox-onchange="fetchServerInfo()" ng-model="isError" class="btn btn-default dropdown-toggle" id="statusDropdown" type="button" data-toggle="dropdown" aria-expanded="true">Status<span class="caret"></span></button>
			<ul class="dropdown-menu" role="menu" aria-labelledby="statusDropdown">
				<li combobox-value=""><a href="#">All</a></li>
				<li class="divider"></li>
	            <li combobox-value="false"><a href="#">Success</a></li>
	            <li combobox-value="true"><a href="#">Error</a></li>    
			</ul>	
        </div> 
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