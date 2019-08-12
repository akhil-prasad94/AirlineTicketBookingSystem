<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airplane added Successfully</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>

	<div class="container" style ="padding: 100px;">
	
	<h4 style="margin: 50px; text-align: center;">New Flight Created Successfully: ${fd.departureAirport} - ${fd.arrivalAirport}</h4>
	
	<h5 style="margin: 50px; text-align: center;"><a href="AdminHome.htm">Go Back to Menu Page</a></h5>
	</div>
		
</body>
</html>