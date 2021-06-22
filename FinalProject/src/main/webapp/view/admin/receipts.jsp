<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receipts</title>
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
	<h1 class="page_header">Receipts</h1>
	<div class="receipts_wrapper">
		<div class="filter">
			<a href="/FinalProject/admin" class="ref_button">Home</a><br>
			<br>
			<br>
			<h2>Status</h2>
			<form action="/FinalProject/status-filter">
				<input type="radio" value="0" name="idstatus" required> all<br>
				<c:forEach items="${statuses}" var="status">
					<input type="radio" value="${status.id}" name="idstatus"> ${status.name} <br>
				</c:forEach>
				<br>
				<button class="ref_button">Show</button>

			</form>
			<br>
			<form action="/FinalProject/date-filter">
				<h2>Date</h2>
				<input type="radio" value="ascending" name="rec_date" required>
				ascending<br> <input type="radio" value="descending"
					name="rec_date"> descending <br> <br>
				<button class="ref_button">Show</button>
				<br>
			</form>
			<br>
			<form action="/FinalProject/sum-filter">
				<h2>Receipt sum</h2>
				<input type="radio" value="ascending" name="total_sum" required>
				ascending <br> <input type="radio" value="descending"
					name="total_sum"> descending <br> <br>
				<button class="ref_button">Show</button>
				<br>
			</form>
			<br>
			<form action="/FinalProject/master-filter">
				<h2>Master</h2>
				<c:forEach items="${masters}" var="master">
					<input type="radio" value="${master.id}" name="idmaster" required> ${master.firstName} ${master.surname} ${master.lastName}<br>
				</c:forEach>
				<br>
				<button class="ref_button">Show</button>
				<br>
			</form>
		</div>

		<div class="content">
			<table class="receipts_table">
				<th class="th">Receipt no</th>
				<th class="th">Date</th>
				<th class="th">Total sum</th>
				<th class="th">Status</th>
				<th class="th">Action</th>
				<c:forEach items="${receipts}" var="receipt">
					<tr style="border: 1px solid;">
						<td class="td">${receipt.id}</td>
						<td class="td"><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
								type="date" value="${receipt.date}" /></td>
						<td class="td">${receipt.totalSum}UAH</td>
						<c:if test="${receipt.status == 1}">
							<td class="td">waiting for payment</td>
						</c:if>
						<c:if test="${receipt.status == 2}">
							<td class="td">payed</td>
						</c:if>
						<c:if test="${receipt.status == 3}">
							<td class="td">cancelled</td>
						</c:if>
						<c:if test="${receipt.status == 4}">
							<td class="td">processing</td>
						</c:if>
						<c:if test="${receipt.status == 5}">
							<td class="td">processed</td>
						</c:if>
						<td><form action="/FinalProject/details" class="table_button">
								<button value="${receipt.id}" name="idreceipt" class="button">Details</button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br>
</body>
</html>