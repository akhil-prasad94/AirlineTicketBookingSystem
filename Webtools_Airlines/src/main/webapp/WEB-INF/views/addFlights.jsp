<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Route</title>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#date").datepicker({
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
	
	function validateForm() {
		  var x = document.forms["myForm"]["departureAirport"].value;
		  var y = document.forms["myForm"]["arrivalAirport"].value;
		  arrivalAirport
		  if (x == y) {
		    alert("From and To cannot be same");
		    return false;
		  }
		}
</script>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>
	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">
			<h3>Add a new Route</h3>


			<form:form action="addflights.htm" commandName="fd" method="post" onsubmit="return validateForm()" name="myForm">
				<div class="form-group">
					<label for="airLineName">AirLiner Name :</label>

					<form:select path="airLineId" id="airLineName" required="required"
						name="airLineName">
						<c:forEach var="item" items="${airLiner}">
							<form:option value="${item.airLineId}">${item}</form:option>
						</c:forEach>

					</form:select>
					<font color="red"><form:errors path="airLineId" /></font>
				</div>
				<div class="form-group">
					<label for="departureAirport">Departure Airport:</label>
					<form:input type="text" class="form-control"
						path="departureAirport" list="json-datalist"
						onkeyup="return names()" id="departureAirport"
						name="departureAirport" size="30" required="required" />
					<font color="red"><form:errors path="departureAirport" /></font>
					<datalist id="json-datalist"></datalist>

				</div>

				<div class="form-group">
					<label for="arrivalAirport">Arrival Airport:</label>
					<form:input type="text" class="form-control" path="arrivalAirport"
						list="json-datalist1" onkeyup="return names1()"
						id="arrivalAirport" name="arrivalAirport" size="30"
						required="required" />
					<font color="red"><form:errors path="arrivalAirport" /></font>
					<datalist id="json-datalist1"></datalist>

				</div>


				<div class="form-group">
					<label for="departureTime">Departure Time :</label>
					<form:input type="text" class="form-control" path="departureTime" id="departureTime"
						size="5" required="required" />
					<font color="red"><form:errors path="departureTime" /></font>

				</div>

				<div class="form-group">
					<label for="arrivalTime">Arrival Time :</label>
					<form:input type="text" class="form-control" path="arrivalTime" id="arrivalTime"
						size="5" required="required" />
					<font color="red"><form:errors path="arrivalTime" /></font>

				</div>

				<div class="form-group">
					<label for="seats">Number of Seats :</label>
					<form:input type="text" class="form-control" path="numberOfSeats" id="seats" min="1"
						max="300" required="required" />
					<font color="red"><form:errors path="numberOfSeats" /></font>

				</div>


				<div class="form-group">
					<label for="depdate">Departure date :</label>
					<form:input type="date" class="form-control" path="departureDate" id="depdate"
						required="required" />
					<font color="red"><form:errors path="departureDate" /></font>

				</div>
				<div class="form-group">
					<label for="arrdate">Arrival Date :</label>
					<form:input type="date" class="form-control" path="arrivalDate" id="arrdate"
						required="required" />
					<font color="red"><form:errors path="arrivalDate" /></font>

				</div>


				<input class="btn btn-primary" type="submit"
					value="Add flight to database" />
			</form:form>
		</div>
	</div>

</body>
</html>