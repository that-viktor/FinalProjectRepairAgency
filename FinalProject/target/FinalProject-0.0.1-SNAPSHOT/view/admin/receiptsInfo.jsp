<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receipt ${requestScope.receipt.id} details</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Receipt services</h1>
	Receipt no: ${requestScope.receipt.id}
	<br> Approved by:
	<c:if test="${requestScope.receipt.adminId == 0}">
		none
	</c:if>
	<c:if test="${requestScope.receipt.adminId != 0}">
	${requestScope.admin.firstName} ${requestScope.admin.lastName}<br> 
	Admin phone: ${requestScope.admin.phoneNum} <br>
	</c:if>
	<br> Client: ${client.firstName} ${client.surname}
	${client.lastName}
	<br> Client phone: ${client.phoneNum}
	<br>
	<br> Status:
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
	<br> Master:
	<c:if test="${requestScope.receipt.masterId == 0}">
		none
	</c:if>
	<c:if test="${requestScope.receipt.masterId != 0}">
		${requestScope.master.firstName} ${requestScope.master.lastName} 
	</c:if>
	<br>
	<br>
	<c:if test="${requestScope.receipt.masterId == 0}">
		<form action="/FinalProject/masterList" method="GET">
			<button class="button" value="${requestScope.receipt.id}"
				name="idreceipt">Add master</button>
		</form>
	</c:if>
	<c:if test="${requestScope.receipt.masterId != 0}">
		<form action="/FinalProject/remove-master" method="GET" class="form">
			<button class="button" name="idreceipt" value="${requestScope.receipt.id}">Remove master</button>
		</form>
	</c:if>
	<br>
	<br>
	<table class="service_table">
		<th class="th">Service name</th>
		<th class="th">Service price</th>
		<c:forEach items="${requestScope.services}" var="service">
			<tr>
				<td class="td">${service.name}</td>
				<td class="td">${service.price} UAH</td>
			</tr>
		</c:forEach>
		<tr>
			<td class="td">Total sum:</td>
			<td class="td">${requestScope.receipt.totalSum} UAH</td>
		</tr>
	</table>
	<br>
	<c:if test="${requestScope.receipt.totalSum == 0.0}">
		<form action="/FinalProject/count" method="get">
			<button class="button" name="idreceipt"
				value="${requestScope.receipt.id}">Count total sum</button>
		</form>
		<br>
	</c:if>
	<div class="buttons">
		<c:if test="${requestScope.receipt.adminId == 0 && receipt.totalSum != 0.0}">
			<form action="/FinalProject/approve" class="form">
				<button class="button" value="${requestScope.receipt.id}"
					name="idreceipt">Approve</button>
			</form>	<br>
		</c:if>

		<form action="/FinalProject/remove" method="POST" class="form">
			<button value="${requestScope.receipt.id}" name="idreceipt"
				class="button">Cancel receipt</button>
		</form>
		<br>
	</div>

	<a href="/FinalProject/receipts">Back</a>
</body>
</html>