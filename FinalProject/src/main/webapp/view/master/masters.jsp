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
<title>Receipt ${requestScope.idreceipt}</title>
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
		<div class="wrapper">
			<h1 class="center_text">
				<fmt:message key="list_of_masters" />
			</h1>
			<hr>
			<table class="table">
				<th class="th"><fmt:message key="master_no" /></th>
				<th class="th"><fmt:message key="master_name" /></th>
				<th class="th"><fmt:message key="action" /></th>
				<c:forEach items="${requestScope.masters}" var="master">
					<tr>
						<td class="td">${master.id}</td>
						<td class="td">${master.firstName} ${master.surname}
							${master.lastName}</td>
						<td class="td"><form action="/FinalProject/setmaster">
								<button class="button" name="idmaster" value="${master.id}">
									<fmt:message key="set_master" />
								</button>
							</form></td>
					</tr>
					<br>
				</c:forEach>
			</table>

			<br>

		</div><br>
		<form action="/FinalProject/details" class="center_form">
			<button name="idreceipt" value="${requestScope.idreceipt}"
				class="ref_button"><fmt:message key="back" /></button>
		</form>
	</div>

</body>
</html>