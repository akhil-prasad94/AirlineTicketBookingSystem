<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight List</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	var xmlHttp;
	xmlHttp = GetXmlHttpObject();

	function checkSeats() {
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		//alert("hi");
		var check = document.getElementById("link").href;
		var flightId = check.split('=');
		//alert(flightId[1]);
		var query = "flid=" + flightId[1];

		xmlHttp.onreadystatechange = function stateChanged() {

			if (xmlHttp.readyState == 4) {
				document.getElementById("error").innerHTML = xmlHttp.responseText;
				var node = document.getElementById("error");

				var txtContent = node.textContent;

				//alert(txtContent);

				if (txtContent == "Seats are available") {
					location.href = ("viewCart.htm");
				}
			}
		};
		xmlHttp.open("POST", "addToCart.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;

	}

	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			// Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}
</script>


<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>


	<c:choose>

		<c:when test="${sessionScope.flightlist.size() > 0}">
			<div id="container">

				<h2>Search result</h2>

				<table class="table table-hover" border="1">
					<thead class="thead-dark">
						<tr>
							<th>Flight Number</th>
							<th>From</th>
							<th>To</th>
							<th>Airliner ID</th>
							<th>Departure time</th>
							<th>Arrival time</th>
							<th>Available Seats</th>
							<th>Book</th>
						</tr>
					</thead>
					<c:forEach var="flight" items="${sessionScope.flightlist}">
						<tr>
							<td>${flight.flightID}</td>
							<td>${flight.departureAirport}</td>
							<td>${flight.arrivalAirport}</td>
							<td>${flight.airLineId}</td>
							<td>${flight.departureTime}</td>
							<td>${flight.arrivalTime}</td>
							<td>${flight.availableSeats}</td>
							<td><a
								href="addtoBookingCart.htm?flightID=${flight.flightID}"
								id="link">Book Ticket</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:when>
		<c:when test="${sessionScope.flightlist.size() == 0}">
			<div class="container" style="padding: 100px;text-align: center;">

				<h2>No results found. Please search again!</h2>

				<h3 style="text-align: center;">
					<a href="index.htm">Home</a>
				</h3>
			</div>
		</c:when>

	</c:choose>

	<div id="error" style="color: red"></div>

</body>
</html>