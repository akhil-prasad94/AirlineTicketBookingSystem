<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights list</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>

	<%@ include file="/resources/static/navbar.jsp"%>
	<!-- /<div class="container"> -->
	
		<h2>Add new Route</h2>
		<a href="addflights.htm"> Add flight </a>
		</br>
		</br></br></br>
		<h3>Flights list</h3>
		<form action="updateFlights.htm" method="get">
			<table class="table table-hover" border="1">

				<thead class="thead-dark">
					<tr>
						<th>Flight Id</th>
						<th>Airliner ID	</th>
						<th>Source        </th>
						<th>Destination</th>
						<th>Departure Time</th>
						<th>Arrival Time</th>
						<th>Total Seats</th>
						<th>Available Seats</th>
						<th>Departure Date</th>
						<th>Arrival Date</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<c:forEach var="flight" items="${sessionScope.listOfFlights}">
					<tr>
						<td>${flight.flightID}</td>
						<td>${flight.airLineId}</td>
						<td>${flight.departureAirport}</td>
						<td>${flight.arrivalAirport}</td>
						<td>${flight.departureTime}</td>
						<td>${flight.arrivalTime}</td>
						<td>${flight.numberOfSeats}</td>
						<td>${flight.availableSeats}</td>
						<td>${flight.departureDate}</td>
						<td>${flight.arrivalDate}</td>
						<td><a
							href="updateFlights.htm?id=${flight.flightID}&action=update">Update
								Flight</a></td>
						<td><a href="deleteFlights.htm?id=${flight.flightID}">Delete
								Flight</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
<!-- 	</div> -->
</body>
</html>