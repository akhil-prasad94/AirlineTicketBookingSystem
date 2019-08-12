<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/resources/static/head.jsp"%>
<%@ include file="/resources/static/navbar.jsp"%>
<title>Register User</title>
</head>
<body>
<script type="text/javascript">
function registerUser(){
	var isValid = true;
	var node = document.getElementById("error");
	
	var txtContent = node.textContent;
	
	
	if(txtContent=="Username already exists")
		{
		isValid = false;
		document.getElementById("duplicateuser").innerHTML= "";
		alert("Please enter unique username");
		}
	return isValid;
	
}
//AJAX

var xmlHttp;
xmlHttp = GetXmlHttpObject();
function checkUser() {
	if (xmlHttp == null)
    {
        alert("Your browser does not support AJAX!");
        return;
    }
    var username = document.getElementById("userName").value;
    var query = "action=ajaxCheck&userName="+username;
	
    xmlHttp.onreadystatechange = function stateChanged()
    {
        if (xmlHttp.readyState == 4)
        {
            //alert(xmlHttp.responseText);
            var json = JSON.parse(xmlHttp.responseText);
            document.getElementById("error").innerHTML="";
            document.getElementById("error").innerHTML = json.message;
            
        }
    };
    xmlHttp.open("POST", "register.htm", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(query);
   return false;
}

 function GetXmlHttpObject()
{
    var xmlHttp = null;
    try
    {
        // Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    } catch (e)
    {
        // Internet Explorer
        try
        {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e)
        {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
}

</script>
	
	<div class="container">
		<div
			class="col-md-4 col-md-offset-4 col-xs-12 col-lg-4 col-lg-offset-4 col-sm-12 well well-sm">
			 <form:form action="register.htm?action=register" onSubmit =" return registerUser()" commandName="user" method="post">

				<div class="form-group">
					<label>First Name:</label>
					<form:input path="firstName" name="firstName" required="required"
						class="form-control"></form:input>
					<form:errors path="firstName"></form:errors>
				</div>

				<div class="form-group">
					<label>Last Name: </label>
					<form:input path="lastName" name="lastName" class="form-control"></form:input>
					<form:errors path="lastName"></form:errors>
				</div>

				<div class="form-group">
					<label>User Name: </label>
					<form:input path="userName" name="userName" onblur="return checkUser()" class="form-control"></form:input>
					<form:errors path="userName"></form:errors>
				</div>
				<div id="error" style="color:red"></div>
				<div class="form-group">
					<label>Password</label>
					<form:input path="password" name="password" type="password"
						class="form-control" required="required"></form:input>
					<form:errors path="password"></form:errors>
				</div>

				 <div id="duplicateuser"></div>
				<input type="submit" name="submit" value="submit"
					class="btn-success btn" />
				<form:errors path="password" />
				<br>
			</form:form>
			
		</div>
	</div>
</body>
</html>