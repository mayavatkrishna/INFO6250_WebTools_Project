<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User home page</title>
<style>
header {
	display: flex;
	align-items: center;
	background-color: #D41B2C;
	height: 94px;
	color: white;
	margin-left: 2;
	position: fixed;
	width: 100%;
	margin-top: 0px;
}

header>h1 {
	color: white;
	margin-left: 25px;
}

nav {
	display: flex;
	justify-items: center;
	align-content: center;
	margin-left: 300px;
}

nav>a {
	color: white;
}

.nav {
	display: flex;
	justify-items: center;
	align-content: center;
	margin-left: 500px;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
	padding-top: 171px;
	margin-top: 80px
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

.btn {
	color: white;
	background-color: #D41B2C;
	border: none;
	height: 35px;
	width: 88px;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<div>
		<header>
		<h1>EVENT HUB</h1>
		<div>
			<nav> <a
				href="${contextPath}/student/viewalljobs.htm?name=${name}"
				style="color: white; margin-left: 20px; text-decoration: none">View
				all Events</a> <a
				href="${contextPath}/student/viewMyJobs.htm?name=${name}"
				style="color: white; margin-left: 20px; text-decoration: none">View
				my registered events</a> </nav>
		</div>
		<div class="nav">
			<a href="${contextPath}/logout.htm"
				style="color: white; margin-left: 50px">Logout</a> <a
				style="color: white; margin-left: 50px">Hello , ${name.fname}</a>
		</div>
		</header>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<center>
			<h1>
				<strong>${successMessage}</strong>
			</h1>
			<center>
				<center>
					<strong>${errorMessage}</strong>
				</center>
</body>
</html>