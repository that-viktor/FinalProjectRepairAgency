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
	<h1>Profile ${client.firstName}</h1>
	<div class="profile_container">
		<div class="profile_info">
			This is your profile, ${client.firstName}. <br>
			<p>Here you can see all the personal information and update it,
				if you need. Also, you can see a history of your receipts on the
				right part of the screen</p>
			<br>
			<h2>Login in system</h2>
			${client.login}<br>
			<h2>First name</h2>
			${client.firstName}<br>
			<h2>Surname</h2>
			${client.surname}<br>
			<h2>Last name</h2>
			${client.lastName}<br>
			<h2>Account number</h2>
			${account.accountId}<br>
			<h2>Account balance</h2>
			${account.balance} UAH<br><br>
			<form action="/FinalProject/client">
				<button class="button">Back</button>
			</form>
		</div>
		<div class="client_receipts">
			<h2 class="page_header">Receipts history</h2>
			<%-- ${clientReceipts} --%>
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
						<td class="td"><c:if test="${clientReceipt.status == 1 && clientReceipt.adminId != 0}">
								<form action="/FinalProject/pay">
									<button class="pay_button" name="idreceipt" value="${clientReceipt.id}">Pay</button>
								</form>
							</c:if>
							<c:if test="${clientReceipt.status != 1 }">
								Payed
							</c:if>
							<c:if test="${clientReceipt.status == 1 && clientReceipt.adminId == 0}">
								Not approved
							</c:if>
							</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>