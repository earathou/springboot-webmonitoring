<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/css/main.css" rel="stylesheet"></link>

<title>Welcome to Web Monitoring Application</title>
</head>
<body>
	<h1>Welcome to Web Monitoring Application</h1>
	<h3>Please add a web url:</h3>
	<form:form method="POST" modelAttribute="websiteForm" action="/addwebsite">
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>URL:</td>
				<td><form:input path="url" /></td>
				<td><form:errors path="url" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form:form>
<p>Click this link to go back to the list: <a href="/" >Web Monitoring List</a> </p>

</body>
</html>