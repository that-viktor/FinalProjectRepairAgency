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
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			<a href="/FinalProject/receipts" class="a">View Receipts</a> <a
				href="/FinalProject/clients-list" class="a">Add funds to user</a> <a
				href="/FinalProject/admin-comments" class="a">Check the comments</a>
		</div>
	</div>
	<div class="content">
		<br> <br>
		<div class="masters_wrapper">
			<div class="center_text">

				<h1>Receipt services</h1><hr>
				Receipt no: ${requestScope.receipt.id} <br> Approved by:
				<c:if test="${requestScope.receipt.adminId == 0}">
		none
	</c:if>
				<c:if test="${requestScope.receipt.adminId != 0}">
	${requestScope.admin.firstName} ${requestScope.admin.lastName}<br> 
	Admin phone: ${requestScope.admin.phoneNum} <br>
				</c:if>
				<br> Client: ${client.firstName} ${client.surname}
				${client.lastName} <br> Client phone: ${client.phoneNum} <br>
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
				<br> <br>
				<c:if test="${requestScope.receipt.masterId == 0}">
					<form action="/FinalProject/masterList" method="GET">
						<button class="ref_button" value="${requestScope.receipt.id}"
							name="idreceipt">Add master</button>
					</form>
				</c:if>
				<c:if test="${requestScope.receipt.masterId != 0}">
					<form action="/FinalProject/remove-master" method="GET">
						<button class="remove_button" name="idreceipt"
							value="${requestScope.receipt.id}">Remove master</button>
					</form>
				</c:if>
				<br> <br>
				<table class="service_table">
					<th class="th">Service name</th>
					<th class="th">Service price</th>
					<c:forEach items="${requestScope.services}" var="service">
						<tr>
							<td class="td">${service.name}</td>
							<td class="td">${service.price}UAH</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="td">Total sum:</td>
						<td class="td">${requestScope.receipt.totalSum}UAH</td>
					</tr>
				</table>
				<br>
				<c:if test="${requestScope.receipt.totalSum == 0.0}">
					<form action="/FinalProject/count" method="get">
						<button class="ref_button" name="idreceipt"
							value="${requestScope.receipt.id}">Count total sum</button>
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
								class="cancel_button">Cancel</button>
						</form>
						<br>
					</c:if>
				</div>


			</div>
		</div>
		<br>
		<div class="center_form">
			<a href="/FinalProject/receipts" class="ref_button">Back</a>
		</div>
	</div>

</body>
</html>