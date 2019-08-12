<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete AirLiner</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>

	<div class="container bg-light" style="margin: 30px">
		<div class="col-sm-8">
			<form:form  action="deleteAirLiners.htm" commandName= "alf" method="post">
				<div class="form-group">
					<label for="airLineId">AirLiner Name</label> 
					<form:select path="airLineId" id="airLineName" required="required"
						name="airLineName">
						<c:forEach var="item" items="${airLiner}">
							<form:option value="${item.airLineId}">${item}</form:option>
						</c:forEach>

					</form:select>
					
					
					<!-- <input
						class="form-control" id="creditCardNumber" type="number"
						name="airplane_id" required="required" />  -->
						
				</div>
				<input type="submit" class="btn btn-primary" value="Proceed" />
			</form:form>
		</div>
	</div>






</body>
</html>