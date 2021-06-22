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
<title>Profile ${sessionScope.client.login}</title>
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
			<fmt:message
						key="balance" /> ${account.balance} UAH
		</div>
	</div>
	<br>

	<div class="profile_container">
		<div class="profile_wrapper">
			<h1 class="center_content"><fmt:message
						key="profile" /> ${client.firstName}</h1><br>
			<div class="profile_info">
				<fmt:message
						key="profile_text_beg" /> ${client.firstName}. <br>
				<p><fmt:message
						key="profile_text_p2" /></p>
				<br>
				<h4><fmt:message
						key="login_in_system" /></h4>
				${client.login}<br> <br>
				<h4><fmt:message
						key="fname" /></h4>
				${client.firstName}<br> <br>
				<h4><fmt:message
						key="sname" /></h4>
				${client.surname}<br> <br>
				<h4><fmt:message
						key="lname" /></h4>
				${client.lastName}<br> <br>
				<h4><fmt:message
						key="account_no" /></h4>
				${account.accountId}<br> <br>
				<h4><fmt:message
						key="balance" /></h4>
				${account.balance} UAH<br> <br>

			</div>
			
			
			<form action="/FinalProject/client" class="center_form">
				<button class="ref_button"><fmt:message
						key="back" /></button>
			</form>
		</div>

		<div class="client_receipts">

			<div class="profile_receipts_wrapper">
				<table class="table">
					<th class="th"><fmt:message
						key="receipt_no" /></th>
					<th class="th"><fmt:message
						key="date" /></th>
					<th class="th"><fmt:message
						key="total_sum" /></th>
					<th class="th"><fmt:message
						key="status" /></th>
					<th class="th"><fmt:message
						key="payment" /></th>
					<c:forEach items="${clientReceipts}" var="clientReceipt">
						<tr>
							<td class="td">${clientReceipt.id}</td>
							<td class="td"><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
									type="date" value="${clientReceipt.date}" /></td>
							<td class="td">${clientReceipt.totalSum} UAH</td>
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
											value="${clientReceipt.id}"><fmt:message
						key="pay_button" /></button>
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