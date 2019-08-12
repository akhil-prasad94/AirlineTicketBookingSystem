<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Home Page</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>

	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">
			<h2>ADMIN PAGE</h2>
			<h5>Please select from below options</h5>

			<a href="addAirLiners.htm"> Add AirLines</a><br /><!-- 
			<br /> <a href="deleteAirLiners.htm">Delete AirLines</a><br /> -->
			<br /> <a href="ListFlights.htm">View and Edit Flights</a><br />
		
		</div>


	</div>
</body>
</html>