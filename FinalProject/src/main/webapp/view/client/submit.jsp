<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Submit receipt</title>
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
	<br>
	<h1 class="center_content"><fmt:message key="receipt_validation" /></h1>
	<br>
	<div class="content">
		<div class="wrapper">
			<div class="center_content">
				<table class="table">
					<th class="th"><fmt:message key="service_no" /></th>
					<th class="th"><fmt:message key="service_name" /></th>
					<th class="th"><fmt:message key="service_price" /></th>
					<th class="th"><fmt:message key="action" /></th>
					<c:forEach items="${services}" var="service">
						<tr>
							<td class="td">${service.id}</td>
							<td class="td">${service.name}</td>
							<td class="td">${service.price} UAH</td>
							<td class="td"><form action="/FinalProject/remove-service">
									<button class="button" name="idservice" value="${service.id}"><fmt:message key="remove" /></button>
								</form></td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<form action="/FinalProject/submit" class="center_form">
					<button class="ref_button"><fmt:message key="submit" /></button>
				</form>
			</div>
		</div>
	</div>
	<br>
	<form action="/FinalProject/client" class="center_form">
		<button class="ref_button"><fmt:message key="back" /></button>
	</form>

</body>
</html>