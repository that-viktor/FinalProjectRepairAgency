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
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			You logged as <b>master</b>
		</div>
	</div>
	<form action="/FinalProject/logout">
		<button name="user_role" value="3" class="logout_button">Logout</button>
	</form>
	<h1 class="form_header">Receipt ${masterReceiptDetails.id} details</h1>

	<div class="content">
		<div class="center_content">
			<div class="login_wrapper">
				<h3 class="form_header">Total sum</h3>
				${masterReceiptDetails.totalSum} UAH<br> <br>
				<h3 class="form_header">Forming date</h3>
				<fmt:formatDate pattern="dd-MM-yyyy HH:mm" type="date"
					value="${masterReceiptDetails.date}" />
				<br> <br>
				<h3 class="form_header">Actual status</h3>
				${masterReceiptStatus}
			</div>
			<br><br>
		</div>
		<div class="content">
			<div class="wrapper">
				<h2 class="form_header">Receipt services</h2>
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
								<td class="td">${masterReceiptService.price}UAH</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>
	<br>
	<form action="/FinalProject/process-receipt" class="center_header">
		<c:if test="${masterReceiptDetails.status == 2 }">
			<button name="action" value="processing" class="ref_button">Process</button>
		</c:if>
		<c:if test="${masterReceiptDetails.status == 4 }">
			<button name="action" value="processed" class="ref_button">Finish</button>
		</c:if>
	</form>
	<br>
	<form action="/FinalProject/master" class="center_header">
		<button class="ref_button">Back</button>
	</form>
</body>
</html>