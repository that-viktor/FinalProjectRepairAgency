<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
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
	<h1 class="page_header">
		<fmt:message key="receipts" />
	</h1>
	<div class="receipts_wrapper">
		<div class="filter">
			<a href="/FinalProject/admin" class="ref_button"><fmt:message
					key="home_button" /></a><br> <br> <br>
			<h2><fmt:message
						key="status" /></h2>
			<form action="/FinalProject/status-filter">
				<input type="radio" value="0" name="idstatus" required>
				<fmt:message key="all" />
				<br>
				<c:forEach items="${statuses}" var="status">
					<input type="radio" value="${status.id}" name="idstatus"> ${status.name} <br>
				</c:forEach>
				<br>
				<button class="ref_button">
					<fmt:message key="show" />
				</button>

			</form>
			<br>
			<form action="/FinalProject/date-filter">
				<h2><fmt:message
						key="date" /></h2>
				<input type="radio" value="ascending" name="rec_date" required>
				<fmt:message key="ascending" />
				<br> <input type="radio" value="descending" name="rec_date">
				<fmt:message key="descending" />
				<br> <br>
				<button class="ref_button">
					<fmt:message key="show" />
				</button>
				<br>
			</form>
			<br>
			<form action="/FinalProject/sum-filter">
				<h2><fmt:message
						key="total_sum" /></h2>
				<input type="radio" value="ascending" name="total_sum" required>
				<fmt:message key="ascending" />
				<br> <input type="radio" value="descending" name="total_sum">
				<fmt:message key="descending" />
				<br> <br>
				<button class="ref_button">
					<fmt:message key="show" />
				</button>
				<br>
			</form>
			<br>
			<form action="/FinalProject/master-filter">
				<h2><fmt:message
						key="master_filter" /></h2>
				<c:forEach items="${masters}" var="master">
					<input type="radio" value="${master.id}" name="idmaster" required> ${master.firstName} ${master.surname} ${master.lastName}<br>
				</c:forEach>
				<br>
				<button class="ref_button">
					<fmt:message key="show" />
				</button>
				<br>
			</form>
		</div>

		<div class="content">
			<table class="receipts_table">
				<th class="th"><fmt:message
						key="receipt_no" /></th>
				<th class="th"><fmt:message
						key="date" /></th>
				<th class="th"><fmt:message
						key="total_sum" /></th>
				<th class="th"><fmt:message
						key="status" /></th>
				<th class="th"><fmt:message
						key="action" /></th>
				<c:forEach items="${receipts}" var="receipt">
					<tr style="border: 1px solid;">
						<td class="td">${receipt.id}</td>
						<td class="td"><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
								type="date" value="${receipt.date}" /></td>
						<td class="td">${receipt.totalSum} UAH</td>
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
								<button value="${receipt.id}" name="idreceipt" class="button"><fmt:message
						key="details" /></button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br>
</body>
</html>