<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NUBooking</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#txtFromDate").datepicker({
			numberOfMonths : 2,
			minDate : 0,
			onSelect : function(selected) {
				$("#txtToDate").datepicker("option", "minDate", selected)
			}
		});

	});
	//AJAX

	var xmlHttp;
	xmlHttp = GetXmlHttpObject();
	function names1() {
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		var destCities = document.getElementById("arrivalAirport").value;
		var query = "fromCities=" + destCities;

		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {

				var dataList = document.getElementById('json-datalist1');
				var json = JSON.parse(xmlHttp.responseText);
				document.getElementById("json-datalist1").innerHTML = "";
				for (var i = 0; i < json.list.length; i++) {

					var option = document.createElement("option");
					option.setAttribute("value", json.list[i].cityname);
					//json.list[i].cityname  option.textContent =  json.list[i].cityname;
					dataList.appendChild(option);

				}

			}
		};

		xmlHttp.open("POST", "fromCitieslist.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;
	}

	function names() {
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		var fromCities = document.getElementById("departureAirport").value;
		var query = "fromCities=" + fromCities;

		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {

				var dataList = document.getElementById('json-datalist');
				var json = JSON.parse(xmlHttp.responseText);
				document.getElementById("json-datalist").innerHTML = "";
				for (var i = 0; i < json.list.length; i++) {

					var option = document.createElement("option");
					option.setAttribute("value", json.list[i].cityname);
					//  option.textContent =  json.list[i].cityname;
					dataList.appendChild(option);

				}

			}
		};
		xmlHttp.open("POST", "fromCitieslist.htm", true);
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
	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">

			<h1>Search for flights</h1>

			<form:form action="listflights.htm" commandName="flightDetails"
				method="post">
				<div class="form-group">
					<label for="departureAirport">Departure Airport:</label>
					<form:input class="form-control" type="text"
						path="departureAirport" list="json-datalist"
						onkeyup="return names()" placeholder="Departure Airport"
						id="departureAirport" name="departureAirport" size="10"
						required="required" />
					<font color="red"><form:errors path="departureAirport" /></font>
					<datalist id="json-datalist"></datalist>
				</div>

				<div class="form-group">
					<label for="arrivalAirport">Arrival Airport:</label>
					<form:input class="form-control" type="text" path="arrivalAirport"
						list="json-datalist1" onkeyup="return names1()"
						placeholder="Arrival Airport" id="arrivalAirport"
						name="arrivalAirport" size="10" required="required" />
					<font color="red"><form:errors path="arrivalAirport" /></font>
					<datalist id="json-datalist1"></datalist>
				</div>

				<div class="form-group">
					<label for="departureDate">Departure Date:</label>
					<form:input class="form-control" type="date" path="departureDate"
						name="departureDate" size="10" required="required" />
					<font color="red"><form:errors path="departureDate" /></font>
				</div>
				<div class="form-group">
					<label for="numberOfSeats">Number of seats:</label>
					<form:input class="form-control" type="number" path="numberOfSeats"
						name="numberOfSeats" size="2" min="1" max="10" required="required" />
					<font color="red"><form:errors path="numberOfSeats" /></font>
				</div>
				<input type="submit" class="btn btn-primary" value="Search" />

			</form:form>
		</div>
	</div>

</body>
</html>