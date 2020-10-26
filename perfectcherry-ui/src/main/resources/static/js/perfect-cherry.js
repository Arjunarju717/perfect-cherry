$$(document).on('pageInit', function(e) {
	$$('#loginNow').on('click', function() {

		alert("From arjun");
		var developerData = {};
		developerData["email"] = $("#email").val();
		developerData["passcode"] = $("#passcode").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "rs/getUserDataById",
			data : JSON.stringify(developerData),
			dataType : 'json',
			success : function(data) {
				alert(data);
				// Code to display the response.
			}
		});
	});
});
