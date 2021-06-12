<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Receipt services</h1>
	Receipt no: ${receipt.id}
	<br> Approved by:
	<c:if test="${receipt.adminId == 0}">
		none
	</c:if>
	<c:if test="${receipt.adminId != 0}">
	${admin.firstName} ${admin.lastName}<br> 
	Admin phone: ${admin.phoneNum} <br>
	</c:if>
	<br> Client: ${requestScope.client.firstName} ${requestScope.client.surname}
	${requestScope.client.lastName}
	<br> Client phone: ${requestScope.client.phoneNum}
	<br>
	<br> Status:
	<c:if test="${receipt.status == 1}">
		waiting for payment
	</c:if>
	<c:if test="${receipt.status == 2}">
		payed
	</c:if>
	<c:if test="${receipt.status == 3}">
		processing
	</c:if>
	<c:if test="${receipt.status == 4}">
		ready
	</c:if>
	<br> Master:
	<c:if test="${receipt.masterId == 0}">
		none
	</c:if>
	<c:if test="${receipt.masterId != 0}">
		${master.firstName} ${master.lastName} 
	</c:if>
	<br>
	<form action="/FinalProject/masterList" method="GET">
		<button class="button" value="${requestScope.receipt.id}" name="idreceipt">Add master</button>
	</form>
	<form action="/removeMaster" method="GET" class="form">
		<button class="button">Remove master</button>
	</form>

	<br>
	<br>
	<table class="service_table">
		<th class="th">Service name</th>
		<th class="th">Service price</th>
		<c:forEach items="${services}" var="service">
			<tr>
				<td class="td">${service.name}</td>
				<td class="td">${service.price}</td>
			</tr>
		</c:forEach>
		<tr>
			<td class="td">Total sum:</td>
			<td class="td">${receipt.totalSum}</td>
		</tr>
	</table>
	<br>
	<c:if test="${receipt.totalSum == 0.0}">
		<form action="/FinalProject/count" method="get" >
			<button class="button" name="idreceipt" value="${receipt.id}">Count
				total sum</button>
		</form>
		<br>
	</c:if>
	<div class="buttons">
		<form action="/FinalProject/approve" class="form">
			<button class="button">Approve</button>
		</form>
		<form action="/FinalProject/remove" method="POST" class="form">
			<button value="${receipt.id}" name="idreceipt" class="button">Deny</button>
		</form>
		<br>
	</div>

	<a href="/FinalProject/receipts">Back</a>
</body>
</html>