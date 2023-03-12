<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<style>
header {
	display: flex;
	align-items: center;
	background-color: #D41B2C;
	height: 94px;
	color: black;
	margin-left: 2;
	position: fixed;
	width: 100%;
	margin-top: 0px;
}

header>h1 {
	color: white;
	margin-left: 25px;
}

.login {
	margin-left: 550px;
	padding-top: 130px;
	align-items: center;
	text-align: center;
	padding: 40px;
	height: 385px;
	border-bottom: 4px solid #ec1d1d;
	box-sizing: border-box;
	background-color: orange;
	width: 400px;
}

.login>h1 {
	color: orange;
	padding-top: 50px;
	padding-bottom: 20px;
}

.input>input {
	background: none;
	background-color: white;
	border: none;
	border-bottom-color: currentcolor;
	border-bottom-style: none;
	border-bottom-width: medium;
	border-bottom: 2px solid #ec1d1d;
}

.btn {
	color: white;
	background-color: #D41B2C;
	border: none;
	height: 35px;
	width: 88px;
}

.sign-up {
	text-align: center;
	border: none;
	text-decoration: none;
}

h3 {
	color: black;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<header>
	<h1>EVENT HUB</h1>
	</header>
	<div class="homepage">
		<div class="login">

			<h1>Log In</h1>
			<form action="${contextPath}/login.htm" method="POST">
				<div></div>
				<div class="input">
					Username: <input type="text" name="uname" placeholder="username"
						maxlength="20" required="required" size="20"><br /> <br />
					Password: <input type="password" name="pwd" placeholder="password"
						minlength="6" required="required" size="20"><br /> <br />
				</div>
				<div class="submit-login">
					<input type="submit" name="submit" value="Login" class="btn">
				</div>
			</form>
		</div>
		<div class="sign-up">
			<a href="${contextPath}/signup.htm"><h3>Create a New
					Account?</h3></a>
		</div>
		<br> <br>
		<center>
			<strong>${successMessage}</strong>
		</center>
		<center>
			<strong>${errorMessage}</strong>
		</center>
	</div>
	<br>
	<br>
</body>
</html>