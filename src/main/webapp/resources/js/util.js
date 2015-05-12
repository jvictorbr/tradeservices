function handleErrorResponse($response) { 
	var errorMessage = "";
	if ($response.error && $response.error != "") { 
		errorMessage += "<strong>Error:&nbsp;</strong>" + $response.error;
	}
	if ($response.detailMessage && $response.detailMessage != "") {
		errorMessage += "<br/><strong>Detail:&nbsp;</strong>" + $response.detailMessage;
	}
	if ($response.url && $response.url != "") { 
		errorMessage += "<br/><strong>URI:&nbsp;</strong>" + $response.url;
	}
	return errorMessage;
}

function showErrorMessage(errorMessage) { 
	$("#errorAlert").find('span').last().html(errorMessage);
	$("#errorAlert").fadeIn("slow");
	setTimeout(function(){$("#errorAlert").fadeOut("slow")}, 10000);
}

function showGlass() { 
	$("#glassModal")			
	.modal({
		backdrop: 'static',
		keyboard: false
	});
}

function hideGlass() { 
	 $("#glassModal").modal('hide');
}

function centerModal() {
    $(this).css('display', 'block');
    var $dialog = $(this).find(".modal-dialog");	    
    var verticalOffset = ($(window).height() - $dialog.height()) / 2;	    
    var horizontalOffset = ($(window).width() - $dialog.width()) / 2;
    $dialog.css("margin-top", verticalOffset);	    
}

function getURI(url) { 
	
	var contextRoot = "/tlmonitor";
	var beginIndex = url.indexOf(contextRoot) + contextRoot.length;
	var uri = url.substring(beginIndex);
	return uri;
	
}





Array.prototype.findLink = function(linkName) { 
	var arr = this;
	for (i = 0; i < arr.length; i++) { 
		var link = arr[i];
		if (link.rel == linkName) { 
			return link;
		}
	}
	return undefined;
}