<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EVENT REGISTRATION</title>
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
				href="${contextPath}/event/viewallevents.htm?name=${name}"
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
		<br> <input type="hidden" value="${name}" name="userText"></input>
	</div>
	<div>
		<br>
		<br>
	</div>

	<div align="center">
		<h1>User ${name.fname}'s, Application</h1>
		<br>
		<h3>Please upload your latest id:</h3>
		<br>
		<br>
		<form method="post"
			action="${contextPath}/event/apply.htm?jobID=${jobID}&name=${name}"
			enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td><b>ID</b></td>
					<td><input type="file" name="fileUpload" size="50" required /></td>
				</tr>
				<tr></tr>

				<tr></tr>
			</table>
			<br>
			<br> <input type="submit" class="btn" value="Apply" />

		</form>
	</div>
</body>
</html>