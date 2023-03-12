<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event Post</title>

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
			<nav> <a href="${contextPath}/admin/postevent.htm?name=${name}"
				style="color: white; margin-left: 20px">Post an Event</a> <a
				href="${contextPath}/admin/myeventposts.htm"
				style="color: white; margin-left: 20px">View my Events</a> </nav>
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
			<h1>Listed Events</h1>
		</center>
		<table class="table">
			<tr>
				<th><b>Event ID</b></th>
				<th><b>Title</b></th>
				<th><b>Host Name</b></th>
				<th><b>Event Type</b></th>
				<th><b>Location</b></th>
				<th><b>Link</b></th>
				<th><b>Description</b></th>
				<th><b>Posted On</b></th>
				<th><b>Update Post</b></th>
				<th><b>View Registrations</b></th>
				<th><b>Delete</b></th>
			</tr>
			<form action="${contextPath}/admin/deleteMyEventposts.htm"
				method="get">
				<c:forEach var="j" items="${eventPost}">

					<input type="hidden" name="eventID" value="${j.id}" />
					<tr>
						<td>${j.id}</td>
						<td>${j.eventTitle}</td>
						<td>${j.hostName}</td>
						<td>${j.eventType}</td>
						<td>${j.venue},${j.city}</td>
						<c:if test="${empty j.link || j.link == 'null'}">
							<td>No link available. Please check the company's website</td>
						</c:if>
						<c:if test="${not empty j.link}">
							<td>${j.link}</td>
						</c:if>
						<c:if test="${empty j.description}">
							<td>No description provided</td>
						</c:if>
						<c:if test="${not empty j.description}">
							<td>${j.description}</td>
						</c:if>
						<td>${j.postedOn}</td>
						<td><a
							href="${contextPath}/admin/updateEventDetails.htm?eventID=${j.id}&name=${name.fname}"
							role="button" aria-pressed="true">Update Post</a></td>
						<td><a
							href="${contextPath}/admin/viewCandidatesApplied?eventID=${j.id}&name=${name.fname}"
							role="button" aria-pressed="true">View Registration</a></td>
						<td><button class="btn" type="submit">Delete Post</button></td>
					</tr>
					<br>
					<br>
				</c:forEach>
			</form>
		</table>
</body>
</html>