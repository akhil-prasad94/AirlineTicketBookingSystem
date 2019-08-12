<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Flight</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
		$("#txtFromDate").datepicker({
			numberOfMonths : 2,
			minDate : 0,
			onSelect : function(selected) {
				$("#txtToDate").datepicker("option", "minDate", selected)
			}
		});
		$("#txtToDate").datepicker({
			numberOfMonths : 2,
			onSelect : function(selected) {
				$("#txtFromDate").datepicker("option", "maxDate", selected)
			}
		});
	});
</script>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>


	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">
			<form:form action="updateFlight.htm" commandName="fd" method="post">
				<table>
					<tr>
						<td>Flight ID</td>
						<td><form:input type="text" path="flightID"
								value="${requestScope.flight.flightID}" size="30" /><font
							color="red"><form:errors path="flightID" /></font></td>
					</tr>
					<tr>
						<td>Airplane Id</td>
						<td><form:input type="text" path="airLineId"
								value="${requestScope.flight.airLineId}" size="30" /><font
							color="red"><form:errors path="airLineId" /></font></td>
					</tr>
					<tr>
						<td>Departure</td>
						<td><form:input type="text" path="departureAirport"
								value="${requestScope.flight.departureAirport}" size="3" /><font
							color="red"><form:errors path="departureAirport" /></font></td>
					</tr>
					<tr>
						<td>Arrival</td>
						<td><form:input type="text" path="arrivalAirport"
								value="${requestScope.flight.arrivalAirport}" size="3" /><font
							color="red"><form:errors path="arrivalAirport" /></font></td>
					</tr>
					<tr>
						<td>Departure Time</td>
						<td><form:input type="text" path="departureTime"
								value="${requestScope.flight.departureTime}" size="6" /><font
							color="red"><form:errors path="departureTime" /></font></td>
					</tr>
					<tr>
						<td>Arrival Time</td>
						<td><form:input type="text" path="arrivalTime"
								value="${requestScope.flight.arrivalTime}" size="6" /><font
							color="red"><form:errors path="arrivalTime" /></font></td>
					</tr>

					<tr>
						<td>Total Seats</td>
						<td><form:input type="text" path="numberOfSeats"
								value="${requestScope.flight.numberOfSeats}" size="30"
								readonly="true" /><font color="red"><form:errors
									path="numberOfSeats" /></font></td>
					</tr>
					<tr>
						<td>Available Seats</td>
						<td><form:input type="text" path="availableSeats" value="0"
								size="30" /><font color="red"><form:errors
									path="availableSeats" /></font></td>
					</tr>

					<tr>
						<td>Departure Date</td>
						<td><form:input type="text" path="departureDate"
								id="txtFromDate" value="${requestScope.flight.departureDate}"
								size="30" /><font color="red"><form:errors
									path="departureDate" /></font></td>
					</tr>
					<tr>
						<td>Arrival Date</td>
						<td><form:input type="text" path="arrivalDate" id="txtToDate"
								value="${requestScope.flight.arrivalDate}" size="30" /><font
							color="red"><form:errors path="arrivalDate" /></font></td>

					</tr>

					<tr>
						<td colspan="2"><input type="submit"
							value="Update flight to database" /></td>
					</tr>

				</table>



			</form:form>
			<a href="AdminHome.htm">Go Back to Menu Page</a>
		</div>
	</div>
</body>
</html>