<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passenger Details</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<%@ include file="/resources/static/head.jsp"%>
</head>
<body>

	<%@ include file="/resources/static/navbar.jsp"%>
	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">

			<h1>Please enter all details correctly</h1>
			<form:form action="passenger.htm" commandName="passenger"
				method="post">

				<div class="form-group">
					<label for="firstName">First Name:</label>
					<form:input type="text" path="firstName" size="30"
						required="required" />
					<font color="red"><form:errors path="firstName" /></font>
					</td>
				</div>

				<div class="form-group">
					<label for="lastName">Last Name:</label>
					<form:input type="text" path="lastName" size="30"
						required="required" />
					<font color="red"><form:errors path="lastName" /></font>
				</div>

				<div class="form-group">
					<label for="firstName">First Name:</label>
					<form:radiobutton required="required" value="M" path="gender"
						checked="checked" />
					Male
					<form:radiobutton value="F" path="gender" />
					Female
				</div>


				<input type="submit" class="btn btn-primary"
					value="Save details and enter payment details" />

			</form:form>
		</div>

	</div>
</body>
</html>