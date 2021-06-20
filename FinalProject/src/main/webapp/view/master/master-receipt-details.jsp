<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Receipt details</title>
</head>
<body>
	<h1 class="page_header">Receipt ${masterReceiptDetails.id} details</h1>

	<div class="content">
		<div class="center_content">
			<h2 class="center_header">Receipt info</h2><br><br>
			<h3 class="center_header">Receipt total sum</h3>
			${masterReceiptDetails.totalSum} UAH<br><br>
			<h3 class="center_header">Receipt forming date</h3>
			<fmt:formatDate pattern="dd-MM-yyyy HH:mm" type="date"
				value="${masterReceiptDetails.date}" /><br><br>
			<h3 class="center_header">Receipt status</h3>
			${masterReceiptStatus}<br><br>
		</div>
		<h2 class="center_header">Receipt services</h2>
		<div class="center_content">
			<table class="table">
				<th class="th">Service no</th>
				<th class="th">Service name</th>
				<th class="th">Service price</th>
				<c:forEach items="${masterReceiptServices}"
					var="masterReceiptService">
					<tr>
						<td class="td">${masterReceiptService.id }</td>
						<td class="td">${masterReceiptService.name}</td>
						<td class="td">${masterReceiptService.price} UAH</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div><br>
	<form action="/FinalProject/process-receipt" class="center_header">
		<c:if test="${masterReceiptDetails.status == 2 }">
			<button name="action" value="processing" class="button">Accept to process</button>
		</c:if>
		<c:if test="${masterReceiptDetails.status == 4 }">
			<button name="action" value="processed" class="button">Finish processing</button>
		</c:if>
	</form><br>
	<form action="/FinalProject/master" class="center_header">
		<button class="button">Back</button>
	</form>
</body>
</html>