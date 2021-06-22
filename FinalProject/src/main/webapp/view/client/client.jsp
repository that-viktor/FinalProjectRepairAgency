<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
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
				<a href="/FinalProject/main" class="main_title"><fmt:message
						key="title" /></a>
			</div>
			<a href="/FinalProject/client-profile" class="a"><fmt:message
					key="profile" /></a> <a href="/FinalProject/client-comments" class="a"><fmt:message
					key="comments" /></a>
			<fmt:message key="balance" />
			${account.balance} UAH
		</div>
	</div>


	<form action="/FinalProject/logout">
		<button name="user_role" value="2" class="logout_button">
			<fmt:message key="logout" />
		</button>
	</form>

	<c:if test="${servicesCount > 0 }">
		<div class="checkout_bar">
			<br>
			<form action="/FinalProject/add-receipt">
				<label><fmt:message key="services_chosen" />${servicesCount}</label>
				<button class="ref_button">
					<fmt:message key="checkout" />
				</button>
			</form>
		</div>
	</c:if>


	<h1 class="center_content">
		<fmt:message key="list_of_services" />
	</h1>
	<br>
	<br>
	<div class="content">
		<div class="wrapper">
			<c:forEach items="${requestScope.services}" var="service">
				<h3>${service.name}</h3>
				<p class="service_text">${service.price}UAH</p>
				<c:if test="${selectedService != service.id}">
					<br>
					<form action="/FinalProject/add-service" class="center_form">
						<button name="idservice" value="${service.id}" class="ref_button">
							<fmt:message key="add_to_receipt" />
						</button>
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
			<h3>
				<fmt:message key="contacts" />
			</h3>
			<br>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<br>
		</div>
		<div class="footer_right_part">
			<h3>
				<fmt:message key="locations" />
			</h3>
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