<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Home Page</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>
	<div class="container">
		<h1>Welcome ${sessionScope.username}</h1>
		<c:choose>
			<c:when test="${!empty sessionScope.bookingSession}">
				<h3>Your cart contents</h3>
				<table class="table table-hover" border="1">
					<thead class="thead-dark">
						<tr>
							<th>Flight Number</th>
							<th>Airplane Id</th>
							<th>Departure time</th>
							<th>Destination arrival time</th>
							<th>Remove from session</th>
						</tr>
						</thead>
						<c:forEach var="flight" items="${sessionScope.bookingSession}">
							<tr>
								<td>${flight.flightID}</td>
								<td>${flight.airLineId}</td>
								<td>${flight.departureTime}</td>
								<td>${flight.arrivalTime}</td>
								<td><a
									href="removeFromCart.htm?action=remove&id=${flight.flightID}">
										[Remove from booking session]</a></td>
							</tr>
						</c:forEach>
						
					
				</table>
				<br>
				<br>
				<a href="customerDetails.htm"> Proceed to enter details and
					Payment>></a>
			</c:when>
			<c:otherwise>
				<h5>Nothing in cart..</h5>
				
					<a href="index.htm">Book a ticket</a>

			</c:otherwise>
		</c:choose>
		</br></br></br>
		
	</div>
</body>
</html>