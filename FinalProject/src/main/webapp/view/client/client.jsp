<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.client.lastName}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<p>Hello, ${sessionScope.client.login}!</p>
	<a href="/FinalProject/client-profile">Profile</a>
	<form action="/FinalProject/logout">
		<button name="user_role" value="2" class="button">Logout</button>
	</form>
	<h1>List of services</h1>
	<br>
	<br>
	<c:forEach items="${requestScope.services}" var="service">
		<p>Service no ${service.id}</p>
		<p>${service.name}</p>
		<p>${service.price}UAH</p>
		<form action="/add-to-receipt">
			<button name="idservice" value="${service.id}" class="button">Add
				to receipt</button>
		</form>
		<hr>
		<br>
	</c:forEach>
	<form action="/FinalProject/client">
		<c:forEach items="${pages}" var="page">
			<button name="page" value="${page}" style="display:inline;">${page}</button>
		</c:forEach>
	</form>
</body>
</html>