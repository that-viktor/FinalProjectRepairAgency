<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>${sessionScope.admin.firstName}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Profile</h1>
	Hello, ${sessionScope.admin.firstName}
	<a href="/FinalProject/receipts">View Receipts</a>
	<a href="/FinalProject/clients-list">Add funds to user</a>
	<a href="/FinalProject/admin-comments">Check the comments</a>
	<br>
	<form action="/FinalProject/logout">
		<button name="user_role" value="1" class="button">Logout</button>
	</form>
</body>
</html>