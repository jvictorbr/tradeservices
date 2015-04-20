angular.module('TradeServices.services', [])


.factory('tlMonitorService', function($http) {

    var tlMonitorAPI = {};

    tlMonitorAPI.getMessages = function(isError, from, to) {
    	
    	var url = "http://localhost:8080/tradeservices/ws/tlmonitor/messages";
    	var params = {}; 
		
		if (isError != undefined && isError != "") {
			params.isError = isError;
		}
		if (from) { 
			params.from = from;			
		}
		if (to) { 
			params.to = to;			
		}		
		
		if (!$.isEmptyObject(params)) {
			url = url + '?' + $.param(params);
		}	
    	
		return $http.get(url);
    };
    
    tlMonitorAPI.getMessageDetails = function(requestId) {
    	var url = "http://localhost:8080/tradeservices/ws/tlmonitor/messageDetails?reqId="+requestId;
    	return $http.get(url);
    };

    return tlMonitorAPI;
    
})

.factory('callbackService', function($http) {

	var callbackAPI = {};
	
	callbackAPI.getCallback = function(cnpj, account) { 
		
		var url = "http://localhost:8080/tradeservices/ws/callback?cnpj="+cnpj+"&account="+account;
		return $http.get(url);
		
	};
	
	return callbackAPI;

});