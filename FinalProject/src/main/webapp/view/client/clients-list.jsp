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
<link rel="stylesheet" href="css/style.css">
<title>Clients list</title>
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
		<fmt:message key="choose_user_title" />
	</h1>
	<div class="content">
		<div class="wrapper">
			<h1 class="center_content">
				<fmt:message key="users" />
			</h1>
			<hr>
			<br>
			<table class="user_table">
				<th class="th"><fmt:message key="name" /></th>
				<th class="th"><fmt:message key="action" /></th>
				<c:forEach items="${requestScope.clients}" var="client">
					<tr>
						<td class="td">${client.firstName} ${client.surname}
							${client.lastName}</td>
						<td class="td"><form action="/FinalProject/account-info">
								<button class="button" value="${client.id}" name="iduser">
									<fmt:message key="details" />
								</button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br>
	<div class="center_form">
		<a href="/FinalProject/admin" class="ref_button"><fmt:message
					key="back" /></a>
	</div>
</body>
</html>