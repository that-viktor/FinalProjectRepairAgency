<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message
						key="title" /></a>
			</div>
			<div class="header_text">
				<fmt:message key="register_page" />
			</div>
		</div>
	</div>
	<div class="content">
		<br>
		<h1 class="form_header">
			<fmt:message key="register_page" />
		</h1>
		<br>
		<div class="login_wrapper">
			<h3 class="form_header">
				<fmt:message key="register_message" />
			</h3>
			<br>
			<form method="post" action="/FinalProject/register"
				class="center_form">
				<label><fmt:message key="form_login" /> </label><input type="text"
					value="client" name="login" required pattern="[a-z,0-9]*"
					maxlength="10"><br> <label><fmt:message
						key="form_password" /> </label><input type="password" value="client"
					name="pass" required maxlength="10" pattern="[a-z,0-9]*"><br>
				<label><fmt:message key="fname" /></label><input type="text"
					name="fname" required><br> <label><fmt:message
						key="sname" /> </label><input type="text" name="surname" required><br>
				<label><fmt:message key="lname" /></label><input type="text"
					name="lname" required><br> <label><fmt:message
						key="phone_num" /></label><input type="text" name="phone_num" required
					pattern="\+[3][8][0][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"
					placeholder="+38xxxxxxxxxx"><br> <label><fmt:message
						key="email" /></label><input type="text" name="email" required
					pattern=".*@gmail\.com" placeholder="example@gmail.com"><br>
				<br> <input type="submit"
					value="<fmt:message
						key="Register" />" class="ref_button"><br>
				<br> <a href="/FinalProject/main" class="ref_button"><fmt:message
						key="home_button" /></a>
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
			<h3>
				<fmt:message key="contacts" />
			</h3>
			<br>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<br>
		</div>
		<div class="footer_right_part">
			<h3>
				<fmt:message key="locations" />
			</h3>
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