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
<title>Receipt ${requestScope.receipt.id} details</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message
						key="title" /></a>
			</div>
			<a href="/FinalProject/receipts" class="a"><fmt:message
					key="view_receipts" /></a> <a href="/FinalProject/clients-list"
				class="a"><fmt:message key="add_funds_user" /></a> <a
				href="/FinalProject/admin-comments" class="a"><fmt:message
					key="check_comments" /></a>
		</div>
	</div>
	<div class="content">
		<br> <br>
		<div class="masters_wrapper">
			<div class="center_text">

				<h1>
					<fmt:message key="receipt_details" />
				</h1>
				<hr>
				<fmt:message key="receipt_no" />
				: ${requestScope.receipt.id} <br>
				<fmt:message key="approved_by" />
				:
				<c:if test="${requestScope.receipt.adminId == 0}">
					<fmt:message key="none" />
				</c:if>
				<c:if test="${requestScope.receipt.adminId != 0}">
	${requestScope.admin.firstName} ${requestScope.admin.lastName}<br>
					<fmt:message key="admin_phone" />: ${requestScope.admin.phoneNum} <br>
				</c:if>
				<br>
				<fmt:message key="client" />
				: ${client.firstName} ${client.surname} ${client.lastName} <br>
				<fmt:message key="client_phone" />
				: ${client.phoneNum} <br> <br>
				<fmt:message key="status" />
				:
				<c:if test="${requestScope.receipt.status == 1}">
		waiting for payment
	</c:if>
				<c:if test="${requestScope.receipt.status == 2}">
		payed
	</c:if>
				<c:if test="${requestScope.receipt.status == 3}">
		cancelled
	</c:if>
				<c:if test="${requestScope.receipt.status == 4}">
		processing
	</c:if>
				<c:if test="${requestScope.receipt.status == 5}">
		processed
	</c:if>
				<br>
				<fmt:message key="master_filter" />
				:
				<c:if test="${requestScope.receipt.masterId == 0}">
		<fmt:message
					key="none" />
	</c:if>
				<c:if test="${requestScope.receipt.masterId != 0}">
		${requestScope.master.firstName} ${requestScope.master.lastName} 
	</c:if>
				<br> <br>
				<c:if test="${requestScope.receipt.masterId == 0}">
					<form action="/FinalProject/masterList" method="GET">
						<button class="ref_button" value="${requestScope.receipt.id}"
							name="idreceipt"><fmt:message
					key="add_master" /></button>
					</form>
				</c:if>
				<c:if test="${requestScope.receipt.masterId != 0}">
					<form action="/FinalProject/remove-master" method="GET">
						<button class="remove_button" name="idreceipt"
							value="${requestScope.receipt.id}">
							<fmt:message key="remove_master" />
						</button>
					</form>
				</c:if>
				<br> <br>
				<table class="service_table">
					<th class="th"><fmt:message key="service_name" /></th>
					<th class="th"><fmt:message key="service_price" /></th>
					<c:forEach items="${requestScope.services}" var="service">
						<tr>
							<td class="td">${service.name}</td>
							<td class="td">${service.price}UAH</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="td"><fmt:message key="total_sum" />:</td>
						<td class="td">${requestScope.receipt.totalSum}UAH</td>
					</tr>
				</table>
				<br>
				<c:if test="${requestScope.receipt.totalSum == 0.0}">
					<form action="/FinalProject/count" method="get">
						<button class="ref_button" name="idreceipt"
							value="${requestScope.receipt.id}"><fmt:message
					key="count_total_sum" /></button>
					</form>
					<br>
				</c:if>
				<div>
					<c:if
						test="${requestScope.receipt.adminId == 0 && receipt.totalSum != 0.0}">
						<form action="/FinalProject/approve">
							<button class="ref_button" value="${requestScope.receipt.id}"
								name="idreceipt">Approve</button>
						</form>
						<br>
					</c:if>
					<c:if test="${requestScope.receipt.status != 3 }">
						<form action="/FinalProject/remove" method="POST">
							<button value="${requestScope.receipt.id}" name="idreceipt"
								class="cancel_button">
								<fmt:message key="cancel" />
							</button>
						</form>
						<br>
					</c:if>
				</div>


			</div>
		</div>
		<br>
		<div class="center_form">
			<a href="/FinalProject/receipts" class="ref_button"><fmt:message
					key="back" /></a>
		</div>
	</div>

</body>
</html>