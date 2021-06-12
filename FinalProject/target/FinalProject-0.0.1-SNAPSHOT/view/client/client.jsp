<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.user.lastName}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	Hello, ${sessionScope.user.login}!<br>
	<a href="/FinalProject/services">View Services</a>
	<a href="/FinalProject/logout">Logout</a><br>
	
</body>
</html>