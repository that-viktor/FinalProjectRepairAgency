<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>${accountClient.login}profile</title>
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
	<br>
	<h1 class="center_content">
		<fmt:message key="profile" />
		${accountClient.firstName} ${accountClient.lastName}
	</h1>
	<br>
	<div class="content">
		<div class="masters_wrapper">
			<div class="center_text">
				<h2>
					<fmt:message key="personal_data" />
				</h2>
				<fmt:message key="fname" />
				: <b>${accountClient.firstName}</b> <br> <fmt:message
					key="sname" />: <b>${accountClient.surname}</b>
				<br> <fmt:message
					key="lname" />: <b>${accountClient.lastName}</b> <br> <br>
				<h2><fmt:message
					key="account_data" /></h2>
				<fmt:message
					key="account_no" />: <b>${sessionScope.account.accountId}</b> <br>
				<fmt:message
					key="balance" />: <b>${sessionScope.account.balance} UAH</b> <br> <br>
				<h2><fmt:message
					key="add_funds_form" /></h2>
				<br>
				<form action="/FinalProject/add-funds">
					<label><fmt:message
					key="enter_positive_sum" />:</label><br> <input type="number"
						min="1" name="sum" step="any" required><br> <br>
					<button class="ref_button"><fmt:message
					key="add_funds_form" /></button>
				</form>
				<br>
			</div>
		</div>

	</div>
	<br>
	<br>
	<form action="/FinalProject/clients-list" class="center_form">
		<button class="ref_button"><fmt:message
					key="back" /></button>
	</form>
</body>
</html>