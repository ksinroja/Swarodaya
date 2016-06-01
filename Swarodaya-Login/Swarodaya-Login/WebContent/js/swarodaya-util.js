function showNotification(message, type){
	$.notify(message, {
		placement: {
			align: "center"
		},
		type:type
	});
}

function showConfirmationDialog(text, okHandler, cancelHandler){
	bootbox.confirm(text, function(result) {
		if(result == true)
			okHandler();			
		else
			cancelHandler();
    });
}

function makeAjaxCall(url, type, contentType, data, dataType, successHandler, errorHandler, async) {
	$.ajax({
		url : url,
		type : type,
		contentType : contentType,
		data : data,
		async: async != null ? async : true,
		dataType : dataType,
		success : function(data, textStatus, jqXHR) {
			successHandler(data, textStatus, jqXHR);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			errorHandler(jqXHR, textStatus, errorThrown);
		}
	});
}
function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validatePhoneOrMobile(val){
	
	if (/^\d{10}$/.test(val)) {
		return true;
	} else {
	    return false
	}
}

function changeBorderColor(fields){

	for(var i=0;i<fields.length;++i){
		console.log(fields[i]);
		fields[i].css('border-color','red');
	}
	fields[0].focus();
}