<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Home Page</title>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>


	<div class="container" style="margin-top: 30px">
		<div class="col-sm-8">

			<h3>ADD AIRLINER</h3>
			<h5>Please enter below details</h5>
			<form:form action="addAirLiners.htm" commandName="AirLiners"
				method="post">
				  <div class="form-group">
				      <label for="airLineName">Airliner Name</label>
				      <form:input type="text" class="form-control" id="airLineName" path="airLineName"
					name="airLineName" size="10" required="required" placeholder="Airliner name"/>
  				  </div>

				<div class="form-group">
				      <label for="twolettercode">2 Letter Code</label>
				      <form:input type="text" class="form-control" id="twolettercode" path="airLineShortName"
					name="airLineShortName" size="2" required="required" />
  				  </div>
				<div class="form-group">
				      <label for="owningcompany">Owning Company</label>
				      <form:input type="text" class="form-control" id="owningcompany" path="owningCompany"
					name="owningCompany" size="10" required="required" placeholder="Owning Company"/>
  				  </div>



				<input type="submit" class="btn btn-primary" value="Add Airplane" />

			</form:form>
		</div>


	</div>



</body>
</html>