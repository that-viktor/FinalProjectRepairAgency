<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<form action="/FinalProject/homepage" method="post">
	<input type="text" value="admin" name="login" required><br>
	<input type="password" value="admin" name="pass" required><br>
	<input type="submit" value="Login"><br>
</form>
<a href="/FinalProject/main">Home</a><br>
</body>
</html>