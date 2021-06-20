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
	Hello, ${sessionScope.client.login}!
	<a href="/FinalProject/client-profile">Profile</a>
	<a href="/FinalProject/client-comments">Leave a comment</a>
	 Balance ${account.balance} UAH
	<c:if test="${servicesCount > 0 }">
		You have ${servicesCount} services chosen
		<form action="/FinalProject/add-receipt">
			<button class="button">Checkout</button>
		</form>
	</c:if>
	<form action="/FinalProject/logout">
		<button name="user_role" value="2" class="button">Logout</button>
	</form>
	<h1>List of services</h1>
	<br>
	<br>
	<c:forEach items="${requestScope.services}" var="service">
		<p class="service_header">${service.name}</p>
		<p class="service_text">${service.price}UAH</p>
		<c:if test="${selectedService != service.id}">
			<form action="/FinalProject/add-service">
				<button name="idservice" value="${service.id}" class="button">Add
					to receipt</button>
			</form>
		</c:if>
		<br>
		<hr>
		<br>
	</c:forEach>
	<form action="/FinalProject/client" class="pagination_block">
		<c:forEach items="${pages}" var="page">
			<button name="page" value="${page}" style="display: inline;"
				class="pagination_button">${page}</button>
		</c:forEach>
	</form>
</body>
</html>