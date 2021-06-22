<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			<a href="/FinalProject/client" class="a">Services</a> <a
				href="/FinalProject/client-profile" class="a">Profile</a> <a
				href="/FinalProject/client-comments" class="a">Leave a comment</a>
			Balance ${account.balance} UAH
		</div>
	</div>
	<h1 class="page_header">Payment info</h1>
	<div class="content">
		<div class="receipts_wrapper">
			<div class="payment_block">
				<h2>Receipt no</h2>
				${receiptToPay.id } <br> <br>
				<h3>Receipt price:</h3>
				${receiptToPay.totalSum } UAH<br> <br>
				<h3>Services</h3>
				<table class="table">
					<th class="th">Service no</th>
					<th class="th">Service name</th>
					<th class="th">Service price</th>
					<c:forEach items="${services}" var="service">
						<tr>
							<td class="td">${service.id}</td>
							<td class="td">${service.name}</td>
							<td class="td">${service.price}UAH</td>
						</tr>
					</c:forEach>
				</table>
				<br>
				<form action="/FinalProject/pay" method="post">
					<button class="ref_button" name="idreceipt"
						value="${receiptToPay.id}">Submit payment</button>
				</form>
				<br>

			</div>
		</div>
		<br>
		<form action="/FinalProject/client-profile" class="center_form">
			<button class="ref_button">Back</button>
		</form>
	</div>
</body>
</html>