<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="locale" />

	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message key="title"/></a>
			</div>
			<div class="header_text"><fmt:message key="login_page"/></div>
		</div>
	</div>
	<div class="content">
		<br>
		<h1 class="form_header"><fmt:message key="Login"/></h1>
		<br>
		<div class="login_wrapper">
			<br> <br>
			<h3 class="form_header"><fmt:message key="login_message"/></h3>
			<br>
			<form action="/FinalProject/auth" method="post" class="center_form">
				<label><fmt:message key="form_login"/></label><br> <input type="text" value="admin"
					name="login" required><br> <br> <label><fmt:message key="form_password"/></label><br>
				<input type="password" value="admin" name="pass" required
					align="left"><br> <br> <input type="submit"
					value="<fmt:message key="Login"/>" class="ref_button"><br> <br> <a
					href="/FinalProject/main" class="ref_button"><fmt:message key="home_button"/></a>
			</form>

		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
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