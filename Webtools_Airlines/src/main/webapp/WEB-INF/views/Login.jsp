<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/resources/static/head.jsp"%>
</head>
<body>
	<%@ include file="/resources/static/navbar.jsp"%>
	
		<div class="container">
		<div
			class="col-md-4 col-md-offset-4 col-xs-12 col-lg-4 col-lg-offset-4 col-sm-12 well well-sm">


			<form:form action="login.htm" method="POST" commandName="user">
				<div class="form-group">
					<label for="userName">User name:</label>

					<form:input path="userName" name="userName" required = "required" class="form-control" />
					<strong> <form:errors path="userName" />
					</strong>
				</div>
				<div class="form-group">
					<label for="password">Password:</label>
					<form:input path="password" name="password" required = "required" type="password"
						class="form-control" />
					<strong> <form:errors path="password" />
					</strong>
				</div>
				<input type="submit" name="submit" value="Login"
					class="btn btn-primary" />

				<br>
			</form:form>
			
			 <c:if test = "${validUserCreds ==  false}">
         <p style = "color: red;">Invalids creds, please try again!<p>
      </c:if>
		</div>
	</div>
</body>
</html>