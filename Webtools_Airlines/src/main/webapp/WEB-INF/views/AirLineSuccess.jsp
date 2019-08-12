<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airplane added Successfully</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>

	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">
			<strong>${AirLiners.airLineName}</strong> has been added succesfully
			<br> <a href="AdminHome.htm">Go Back
				to Menu Page</a>
		</div>
	</div>
</body>
</html>