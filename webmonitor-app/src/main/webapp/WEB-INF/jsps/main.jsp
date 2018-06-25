<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="refresh" content="300">

<link href="/css/main.css" rel="stylesheet"></link>

<title>ShipServ Web Monitoring Application</title>
</head>
<body>
	<h1>Welcome to ShipServ Web Monitoring Application</h1>
	<h3>Here are the list of websites being monitored:</h3>
	<table border=1 style="width:50%;align:left" cellpadding="0">
		<tr>
			<th>Name</th>
			<th>URL</th>
			<th>Status</th>
		</tr>
		<tr>
		<c:forEach items="${websiteListForm.websites}" var="website" varStatus="status">
			<tr>
				<td>${website.name}</td>
				<td>${website.url}</td>
				<td>${website.status}</td>
			</tr>
		</c:forEach>
	</table>	
<p>Click this link to add new web url: <a href="/addweburlpage" >Add New Web URL</a> </p>
<p>Click this to refresh the page manually: <a href="/" >Refresh page</a> </p>

<p><i>This page will be refreshed every 5 minutes automatically</i><p>
</body>
</html>