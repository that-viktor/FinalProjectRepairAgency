<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Payment</title>
</head>
<body>
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message
						key="title" /></a>
			</div>
			<a href="/FinalProject/client" class="a"><fmt:message
						key="services" /></a> <a
				href="/FinalProject/client-profile" class="a"><fmt:message
					key="profile" /></a> <a
				href="/FinalProject/client-comments" class="a"><fmt:message
					key="comments" /></a>
			<fmt:message key="balance" /> ${account.balance} UAH
		</div>
	</div>
	<h1 class="page_header"><fmt:message
						key="pay_info" /></h1>
	<div class="content">
		<div class="pay_receipts_wrapper">
			<div class="payment_block">
				<h2><fmt:message key="receipt_no" />:</h2>
				${receiptToPay.id } <br> <br>
				<h3><fmt:message key="total_sum" />:</h3>
				${receiptToPay.totalSum } UAH<br> <br>
				<h3><fmt:message key="services" /></h3><br>
				<table class="table">
					<th class="th"><fmt:message key="service_no" /></th>
					<th class="th"><fmt:message key="service_name" /></th>
					<th class="th"><fmt:message key="service_price" /></th>
					<c:forEach items="${services}" var="service">
						<tr>
							<td class="td">${service.id}</td>
							<td class="td">${service.name}</td>
							<td class="td">${service.price}UAH</td>
						</tr>
					</c:forEach>
				</table>
				<br><br>
				<form action="/FinalProject/pay" method="post">
					<button class="ref_button" name="idreceipt"
						value="${receiptToPay.id}"><fmt:message key="submit_payment" /></button>
				</form>
				<br>

			</div>
		</div>
		<br>
		<form action="/FinalProject/client-profile" class="center_form">
			<button class="ref_button"><fmt:message key="back" /></button>
		</form>
	</div>
</body>
</html>