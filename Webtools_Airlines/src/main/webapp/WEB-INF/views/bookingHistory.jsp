<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>
	<div class="container">
		<h1>Welcome ${sessionScope.username}</h1>
		
		</br></br></br>
		<h3>Booking History</h3>
		
				<table class="table table-hover" border="1">
					<thead class="thead-dark">
						<tr>
							<th>Ticket ID</th>
							<th>Departure Airport</th>
							<th>Departure time</th>
							<th>Passenger</th>
							
						</tr>
						</thead>
						<c:forEach var="ticket" items="${ticketList}">
							<tr>
								<td>${ticket.ticket_id}</td>
								<td>${ticket.flightDetails.departureAirport}</td>
								<td>${ticket.flightDetails.arrivalAirport}</td>
								<td>${ticket.passengerDetails}</td>
								
						</c:forEach>
						
					
				</table>
	</div>
</body>
</html>