<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
var a,b,c;
function validatecredit() {
	  var creditcard = document.forms["myForm"]["creditCardNumber"].value;
	  if (creditcard.length != 16) {
		  a=false;
		  document.getElementById("carderror").style.color = "red";
	    document.getElementById('carderror').innerHTML = 'Invalid card number, enter again';
	  }else{
		  a=true;
		  document.getElementById("carderror").style.color = "green";
		  document.getElementById('carderror').innerHTML = 'Correct';
	  }
}
function validateexpiration_month() {
	 
	  var expirymonth = document.forms["myForm"]["expiration_month"].value;
	  if(!(expirymonth > 0 && expirymonth <=12)){
		  b=false;
		  document.getElementById("expiration_montherror").style.color = "red";
		  document.getElementById('expiration_montherror').innerHTML = 'Invalid month';
	  }else{
		  
		  b=true;
		  document.getElementById("expiration_montherror").style.color = "green";
		  document.getElementById('expiration_montherror').innerHTML = 'Correct';
	  }
}
function validateexpiration_year() {
	 
	  var expiryyear = document.forms["myForm"]["expiration_year"].value;
	  if(!(expiryyear >= 2019)){
		  c=false;
		  document.getElementById("expiration_yearerror").style.color = "red";
		  document.getElementById('expiration_yearerror').innerHTML = 'Invalid year, min should be 2019';
	  }else{
		  c=true;
		  document.getElementById("expiration_yearerror").style.color = "green";
		  document.getElementById('expiration_yearerror').innerHTML = 'Correct';
	  }
}

function validateForm(){
	if(a == true && b== true && c==true){
		return true;
	}
	else{
		return false;
	}
}
</script>
<title>Enter Payment</title>

<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>

	<div class="container bg-light" style="margin: 30px">
		<div class="col-sm-8">
			<h5>Enter payment details</h5>
			<form action="payment.htm" method="post" name="myForm"
				onsubmit="return validateForm()">
				<div class="form-group">
					<label for="creditCardNumber">Credit Card Number</label> <input onblur="validatecredit()" 
						class="form-control" id="creditCardNumber" type="text"
						name="creditCardNumber"
						value='${cookie.ccn.value}' required="required" />
					<p id="carderror"></p>
				</div>
				<div class="form-group">
					<label for="bankName">Bank Name</label> <input type="text"
						class="form-control" name="bankName" required="required" />

				</div>
				<div class="form-group">
					<label for="fullName">Name on card</label> <input type="text"
						class="form-control" name="fullName" required="required" />

				</div>
				<div class="form-group">
					<label for="expiration_month">Expiry month</label> <input onblur="validateexpiration_month()" 
						class="form-control" id="expiration_month" type="number"
						name="expiration_month" required="required"
						/>
					<p id="expiration_montherror"></p>
				</div>

				<div class="form-group">
					<label for="expiration_year">Expiry year</label> <input onblur="validateexpiration_year()"
						class="form-control" type="number" name="expiration_year"
						required="required" />
					<p id="expiration_yearerror"></p>
				</div>

				<input type="submit" class="btn btn-primary" value="Proceed" />
			</form>
		</div>
	</div>
</body>
</html>