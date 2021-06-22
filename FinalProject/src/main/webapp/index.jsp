<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css" />
<title>Main</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="locale" />

	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title"><fmt:message key="title"/></a>
			</div>
			<a href="/FinalProject/registerForm" class="a"><fmt:message
					key="Register" /></a> <a href="/FinalProject/login" class="a"><fmt:message key="Login"/></a>
		</div>
	</div>
	<br>
	<h1 class="center_header">
		<div><fmt:message key="main_text"/></div>
	</h1>
	<br>
	<div class="content">
		<div class="icon">
			<img alt="logo" src="img/logo.jpg" height="200px" width="200px">
		</div>
		<div class="ceter_text">
			<fmt:message key="warning"/> <a href="/FinalProject/login"
				class="big_url"><fmt:message key="login"/></a> <fmt:message key="or"/> <a href="/FinalProject/registerForm"
				class="big_url"><fmt:message key="register"/></a>
		</div>
	</div>
	<br>
	<br>
	<c:if test="${lang == 'en'}">
		<form action="/FinalProject/locale" class="center_form">
		<button name="lang" value="ru" class="ref_button">Russian</button>
	</form>
	</c:if>
	<c:if test="${lang == 'ru'}">
		<form action="/FinalProject/locale" class="center_form">
		<button name="lang" value="en" class="ref_button">Английский</button>
	</form>
	</c:if>
		
		<br><br><br><br><br><br><br>
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
