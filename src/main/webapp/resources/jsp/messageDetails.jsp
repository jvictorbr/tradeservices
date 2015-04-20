<div class="row">
	<div class="col-md-1">
		<button type="button" class="btn btn-default btn-sm minHeight" ng-click="goBack()">
			<span class="glyphicon glyphicon-step-backward"></span>
		</button>
	</div>		
	<div class="col-md-11"></div>
</div>
<br/>
<div class="row">
	<div class="col-md-12">		
		<div id="messageDetailsGrid" ui-grid="gridOptions" ui-grid-resize-columns ui-i18n="en"></div>
	</div>
</div>

<div id="messageXmlModal" class="modal fade" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
  			<div  class="modal-content" style="max-height: 600px; overflow-y: scroll;" >
  				<pre>
  				{{messageXml}}
  				</pre>
    		</div>
	</div>
</div>