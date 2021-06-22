<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Profile ${sessionScope.client.login}</title>
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
	<br>

	<div class="profile_container">
		<div class="profile_wrapper">
			<h1 class="center_content">Profile ${client.firstName}</h1>
			<div class="profile_info">
				This is your profile, ${client.firstName}. <br>
				<p>Here you can see all the personal information. Also, you can
					see a history of your receipts on the right part of the screen</p>
				<br>
				<h4>Login in system</h4>
				${client.login}<br> <br>
				<h4>First name</h4>
				${client.firstName}<br> <br>
				<h4>Surname</h4>
				${client.surname}<br> <br>
				<h4>Last name</h4>
				${client.lastName}<br> <br>
				<h4>Account number</h4>
				${account.accountId}<br> <br>
				<h4>Account balance</h4>
				${account.balance} UAH<br> <br>

			</div>
			<form action="/FinalProject/client" class="center_form">
				<button class="ref_button">Back</button>
			</form>
		</div>

		<div class="client_receipts">

			<div class="profile_receipts_wrapper">
				<table class="table">
					<th class="th">Receipt no</th>
					<th class="th">Date</th>
					<th class="th">Total sum</th>
					<th class="th">Status</th>
					<th class="th">Payment</th>
					<c:forEach items="${clientReceipts}" var="clientReceipt">
						<tr>
							<td class="td">${clientReceipt.id}</td>
							<td class="td"><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
									type="date" value="${clientReceipt.date}" /></td>
							<td class="td">${clientReceipt.totalSum}UAH</td>
							<td class="td"><c:if test="${clientReceipt.status == 1}">
		waiting for payment
	</c:if> <c:if test="${clientReceipt.status == 2}">
		payed
	</c:if> <c:if test="${clientReceipt.status == 3}">
		cancelled
	</c:if> <c:if test="${clientReceipt.status == 4}">
		processing
	</c:if> <c:if test="${clientReceipt.status == 5}">
		processed
	</c:if></td>
							<td class="td"><c:if
									test="${clientReceipt.status == 1 && clientReceipt.adminId != 0}">
									<form action="/FinalProject/pay">
										<button class="pay_button" name="idreceipt"
											value="${clientReceipt.id}">Pay</button>
									</form>
								</c:if> <c:if test="${clientReceipt.status != 1 }">
								Payed
							</c:if> <c:if
									test="${clientReceipt.status == 1 && clientReceipt.adminId == 0}">
								None
							</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
</body>
</html>