<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error page</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>${requestScope.error_message} </h1>
	<a href="/FinalProject/login">Back to login page</a>
</body>
</html>