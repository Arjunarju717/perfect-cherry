GET: $(document).ready(
		function() {

			// GET REQUEST
			$("#accessportal").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					 headers: {
						    "Authorization": "bearer " + "b0a5e93f-cba0-4ab4-94cf-aa3c9663c1f3",
						  },
					url : "http://perfectcherryrsrds-env.eba-pmmdzzeg.us-east-2.elasticbeanstalk.com/userAccount/getUserDataById/2040537900",
					success : function(result) {
						$("#apiResponse").html(result.emailAddress);
					}
				});
			}
		})