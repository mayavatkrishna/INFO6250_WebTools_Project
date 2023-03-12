<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
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

.register {
	margin-left: 520px;
	padding-top: 130px;
	align-items: center;
	text-align: center;
	padding: 40px;
	height: 521px;
	border-bottom: 4px solid #ec1d1d;
	box-sizing: border-box;
	background-color: #FDF6F5;
	width: 487px;
}

.register>h1 {
	color: #ec1d1d;
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
	color: red;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<header>
	<h1>EVENT HUB</h1>
	</header>
	<div class="homepage">
		<div class="register">
			<h1>Sign Up</h1>
			<div class="container">

				<form class="form-group" action="${contextPath}/register.htm"
					method="POST">
					<label>Select what best suits you:</label><br> <input
						type="radio" name="role" value="student"> Looking for
					Career Events &nbsp&nbsp <input type="radio" name="role"
						value="employer"> Looking for Volunteering<br> <br>
					<div class="input">
						<label for="email">First Name:</label><input type="text"
							name="fname" placeholder="firstname" maxlength="20"
							required="required" size="20"><br> <br> <label>Last
							Name:</label><input type="text" name="lname" placeholder="lastname"
							maxlength="20" required="required" size="20"><br> <br>

						<label>Email Address:</label><input type="email" name="email"
							placeholder="email" maxlength="50" required="required" size="20"><br>
						<br> <label>Username:</label><input type="text" name="uname"
							placeholder="username" maxlength="20" required="required"
							size="20"><br> <br> <label>Password:</label><input
							type="password" name="pwd" placeholder="password" minlength="6"
							required="required" size="20"><br> <br>


					</div>
					<div class="submit-registration">
						<input type="submit" name="submit" value="Register" class="btn">
					</div>
				</form>
			</div>

		</div>
	</div>
	<div class="sign-up">
		<a href="${contextPath}/login.htm"><h3>Already have a
				Account?</h3></a>
	</div>

</body>
</html>