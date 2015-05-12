angular.module('TradeServices.services', ['ngResource'])


.factory('tlMonitorService', function($http, $resource) {

    var tlMonitorAPI = {};    
    var messagesResource = $resource("/tradeservices/ws/tlmonitor/messages/:id", {}, {
        	query: { method: "GET", isArray: false }
    	});
    var messageDetailsResource = $resource("/tradeservices/ws/tlmonitor/messageDetails/:requestId");
    

    tlMonitorAPI.getMessages = function(isError, from, to, success, error) {
    	
    	messagesResource.query({isError:isError, from:from, to:to}, 
    		function(response) { 
    			success(response);
    	}, 	function(response) { 
    			error(response.data);    		 
    	});

    };
    
    tlMonitorAPI.getMessageDetails = function(requestId, success, error) {
    	
    	messageDetailsResource.get({requestId: requestId},
    		function(response) {
    			success(response);
    	},	function(response) {
    			error(response.data);
    	});

    };

    return tlMonitorAPI;
    
})

.factory('callbackService', function($http, $resource) {

	var callbackAPI = {};
	var callbackResource = $resource("/tradeservices/ws/callback");
	
	callbackAPI.getCallback = function(cnpj, account, success, error) {
		
		callbackResource.get({cnpj: cnpj, account: account},
				function(successResponse) { 
					success(successResponse);
				},
				function(errorResponse) { 
					error(errorResponse.data);
				}		
		);

	};
	
	return callbackAPI;

});