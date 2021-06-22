<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="master" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.master.lastName}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message
						key="title" /></a>
			</div>
			<fmt:message
						key="you_logged_as" /> <b><fmt:message
						key="master" /></b>
		</div>
	</div>
	<form action="/FinalProject/logout">
		<button name="user_role" value="3" class="logout_button"><fmt:message
						key="logout" /></button>
	</form>
	<div class="content">
		<div class="wrapper">
			<h1 class="page_header"><fmt:message
						key="welcome" />, ${master.firstName}
				${master.lastName}</h1>
			<h2 class="center_header"><fmt:message
						key="greeting" /></h2>
			<br>
			<master:masterTable></master:masterTable>
		</div>
	</div>
	<br><br><br><br><br>
	<div class="footer">
		<div class="footer_left_part">
			<h3><fmt:message key="contacts"/></h3>
			<br>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<br>
		</div>
		<div class="footer_right_part">
			<h3><fmt:message key="locations"/></h3>
			<br>
			<p>Kharkiv, Example1 st</p>
			<p>Kharkiv, Example2 st</p>
			<p>Kharkiv, Example3 st</p>
			<p>Kharkiv, Example3 st</p>
			<br>
		</div>
	</div>
</body>
</html>