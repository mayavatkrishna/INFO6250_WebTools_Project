<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration List</title>
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
	margin-top: 100px
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div>
		<header>
		<h1>EVENT HUB</h1>
		<div>
			<nav> <a href="${contextPath}/admin/postevent.htm?name=${name}"
				style="color: white; margin-left: 20px">Post an Event</a> <a
				href="${contextPath}/admin/myeventposts.htm"
				style="color: white; margin-left: 20px">View Events posted</a> </nav>
		</div>
		<div class="nav">
			<a href="${contextPath}/logout.htm"
				style="color: white; margin-left: 50px">Logout</a> <a
				style="color: white; margin-left: 50px">Hello , ${name.fname}</a>
		</div>
		</header>
		<div>
			<center>
				<h2>Registrations received</h2>
			</center>
			<c:if test="${listuser.size() > 0}">
				<table class="table">
					<c:forEach var="a" items="${listuser}">
						<tr>
							<td><b>First Name</b></td>
							<td>${a.fname}</td>
						</tr>
						<tr>
							<td><b>Last Name</b></td>
							<td>${a.lname}</td>
						</tr>
						<tr>
							<td><b>Email Address</b></td>
							<td>${a.email}</td>
						</tr>
					</c:forEach>
					<c:forEach var="b" items="${listapp}">
						<tr>
							<td><b>${b.fileName}</b></td>
							<td>${b.data}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${listuser.size() == 0}">
				<h3 style="color: red">No Participants so far!!</h3>
			</c:if>

		</div>
	</div>
</body>
</html>