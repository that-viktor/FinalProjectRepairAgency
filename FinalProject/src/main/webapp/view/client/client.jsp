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
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			Hello, ${sessionScope.client.login}! <a
				href="/FinalProject/client-profile" class="a">Profile</a> <a
				href="/FinalProject/client-comments" class="a">Leave a comment</a>
			Balance ${account.balance} UAH
		</div>
	</div>


	<form action="/FinalProject/logout">
		<button name="user_role" value="2" class="logout_button">Logout</button>
	</form>

	<c:if test="${servicesCount > 0 }">
		<div class="checkout_bar">
			<br> You have ${servicesCount} services chosen
			<form action="/FinalProject/add-receipt">
				<button class="ref_button">Checkout</button>
			</form>
		</div>
	</c:if>


	<h1 class="center_content">List of services</h1>
	<br>
	<br>
	<div class="content">
		<div class="wrapper">
			<c:forEach items="${requestScope.services}" var="service">
				<p class="service_header">${service.name}</p>
				<p class="service_text">${service.price}UAH</p>
				<c:if test="${selectedService != service.id}">
					<br>
					<form action="/FinalProject/add-service" class="center_form">
						<button name="idservice" value="${service.id}" class="ref_button">Add
							to receipt</button>
					</form>
				</c:if>
				<br>
				<hr>
				<br>
			</c:forEach>
		</div>
	</div>
	<br>
	<form action="/FinalProject/client" class="pagination_block">
		<c:forEach items="${pages}" var="page">
			<button name="page" value="${page}" style="display: inline;"
				class="pagination_button">${page}</button>
		</c:forEach>
	</form>
	<div class="footer">
		<div class="footer_left_part">
			<h3>Our contacts</h3>
			<br>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<br>
		</div>
		<div class="footer_right_part">
			<h3>Our locations</h3>
			<br>
			<p>Kharkiv, Example1 st</p>
			<p>Kharkiv, Example2 st</p>
			<p>Kharkiv, Example3 st</p>
			<p>Kharkiv, Example3 st</p>
			<br>
		</div>
	</div>
</body>
</html>