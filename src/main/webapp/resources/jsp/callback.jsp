<div id="filters">
	<div class="panel panel-default">
		<div class="panel-heading">Filters</div>
		<div class="panel-body">
			<div class="row">	
				<div class="col-md-2"><span>CNPJ</span></div>
				<div class="col-md-1"><input ng-model="cnpj" type="text" placeholder="CNPJ..."></div>
			</div>		
			<br/>		
			<div class="row">
				<div class="col-md-2"><span>Account Number</span></div>
				<div class="col-md-1"><input ng-model="account" type="text" placeholder="Account Number..."></div>
			</div>		
			<br/>		
			<div class="row">	
				<div class="col-md-3">&nbsp;</div>	
				<div class="col-md-1">
					<div class="text-right">	
						<button type="submit" class="btn btn-default btn-sm" ng-click="getCallback()">Get Info</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<br/>

<div id="results" class="hidden">
	<div class="panel panel-default">
		<div class="panel-heading">Results</div>
		<div class="panel-body">
		
		
			<div id="basicInfo" class="panel panel-default">
				<div class="panel-heading">Basic Information</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2"><span>Request#</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.id" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>Customer CNPJ</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.customerId" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>Customer Account#</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.account" disabled="disabled" /></div>
					</div>				
					<div class="row">
						<div class="col-md-2"><span>Last Order Date</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.lastOrderDate" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>Order Count</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.orderCount" disabled="disabled" /></div>
					</div>
				</div>
			</div>  
			
			
			<div id="intermediaryInstitution" class="panel panel-default">
				<div class="panel-heading">Intermediary Institution</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2"><span>BIC Account#</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.intermediaryInstitution.bicAccount" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>BIC Code</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.intermediaryInstitution.bicCode" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>CC Clear</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.intermediaryInstitution.ccClear" disabled="disabled" /></div>
					</div>					
					<div class="row">
						<div class="col-md-2"><span>Bank Name</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.intermediaryInstitution.bankName" disabled="disabled" /></div>
					</div>					
				</div>
			</div>
			
			
			<div id="accountInstitution" class="panel panel-default">
				<div class="panel-heading">Account Institution</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2"><span>BIC Account#</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.accountInstitution.bicAccount" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>BIC Code</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.accountInstitution.bicCode" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>CC Clear</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.accountInstitution.ccClear" disabled="disabled" /></div>
					</div>					
					<div class="row">
						<div class="col-md-2"><span>Bank Name</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.accountInstitution.bankName" disabled="disabled" /></div>
					</div>					
				</div>
			</div>
			
			
			<div id="beneficiaryCustomer" class="panel panel-default">
				<div class="panel-heading">Beneficiary Customer</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2"><span>BIC Account#</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.beneficiaryCustomer.bicAccount" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>BIC Code</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.beneficiaryCustomer.bicCode" disabled="disabled" /></div>
					</div>
					<div class="row">
						<div class="col-md-2"><span>CC Clear</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.beneficiaryCustomer.ccClear" disabled="disabled" /></div>
					</div>					
					<div class="row">
						<div class="col-md-2"><span>Bank Name</span></div>
						<div class="col-md-6"><input ng-model="callbackResponse.beneficiaryCustomer.bankName" disabled="disabled" /></div>
					</div>					
				</div>
			</div>				
			
						  				  				  				  				  				  				  				
		</div>
	</div>
</div>