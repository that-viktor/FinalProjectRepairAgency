<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Success</title>
</head>
<body>
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message
						key="title" /></a>
			</div>
			<a href="/FinalProject/client-profile" class="a"><fmt:message
					key="profile" /></a> <a href="/FinalProject/client-comments" class="a"><fmt:message
					key="comments" /></a>
			<fmt:message key="balance" />
			${account.balance} UAH
		</div>
	</div>
	<br>
	<h1 class="center_content">
		<fmt:message key="receipt_success" />
	</h1>
	<br>
	<div class="content">
		<div class="wrapper">
			<b><fmt:message
					key="well_done" /></b><fmt:message
					key="success_s2" /> <br><fmt:message
					key="success_s3" /><b><fmt:message
					key="your_profile" /></b> <fmt:message
					key="success_s4" /><br> <b><fmt:message
					key="success_thx" /></b>
		</div>
	</div>
	<br>
	<form action="/FinalProject/client" class="center_form">
		<button class="ref_button"><fmt:message key="main" /></button>
	</form>
</body>
</html>