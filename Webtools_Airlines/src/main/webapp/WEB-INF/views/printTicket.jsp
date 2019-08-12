<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticket Printout</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>
	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">
				<h2 >Congratulations, your ticket has been succesfully booked. You can choose to get ticket by email or pdf!</h2>
				<h3>
					<a href="downloadTicket.pdf">Download Ticket PDF</a>
				</h3>
				<h3>
					<a href="emailTicket.htm"> Send Email Confirmation</a>
				</h3>
				<h3>
					<a href="deleteTicket.htm">Delete Ticket</a>
				</h3>
		
		</div>
		</br></br></br></br></br>
		<h4><a href="bookingCart.htm">Go home</a></h4>
	</div>

</body>
</html>