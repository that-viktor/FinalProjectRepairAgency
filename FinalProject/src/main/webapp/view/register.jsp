<%@ page language="java" contentType="text/html"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<form method="post" action="/FinalProject/register">
		<label>Login </label><input type="text" value="client" name="login" required><br> 
		<label>Password </label><input type="password" value="client" name="pass" required><br> 
		<label>First name </label><input type="text" name="fname" required><br>
		<label>Surname </label><input type="text" name="surname" required><br>
		<label>Last name </label><input type="text" name="lname" required><br>
		<label>Phone number </label><input type="text" name="phone_num" required><br>
		<label>Contact email </label><input type="text" name="email" required><br>
		<input type="submit" value="Register now"><br>
	</form>
	<a href="/FinalProject/main">Home</a>
</body>
</html>