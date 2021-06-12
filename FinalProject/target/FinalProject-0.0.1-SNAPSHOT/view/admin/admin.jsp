<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>${sessionScope.user.firstName}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Profile</h1>
	Hello, ${sessionScope.user.firstName}
	<a href="/FinalProject/receipts">View Receipts</a>
	<a href="/FinalProject/logout">Logout</a>
</body>
</html>