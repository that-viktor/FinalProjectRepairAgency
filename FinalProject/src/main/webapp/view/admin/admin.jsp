<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html>
<head>
<title>${sessionScope.admin.firstName}</title>
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
	<form action="/FinalProject/logout">
		<button name="user_role" value="1" class="logout_button">
			<fmt:message key="logout" />
		</button>
	</form>
	<div class="content">
		<div class="wrapper">
			<br>
			<h1 class="page_header">
				<fmt:message key="admin_profile" />
			</h1>
			<div class="center_text">
				<p>
					1.
					<fmt:message key="todo_p1" />
					<a href="/FinalProject/receipts" class="big_url"><fmt:message
							key="this_option" /></a>.
				</p>
				<p>
					2.
					<fmt:message key="todo_p2" />
					<a href="/FinalProject/clients-list" class="big_url"><fmt:message
							key="this_option" /></a>.
				</p>
				<p>
					3.
					<fmt:message key="todo_p3" />
					<a href="/FinalProject/admin-comments" class="big_url"><fmt:message
							key="this_option" /></a>.
				</p>
			</div>
		</div>
		<br> <br>
		<div class="wrapper">
			<h2 class="page_header"><fmt:message
							key="instruction" /></h2>
			<div class="center_text">
				<p>1.
					<fmt:message key="inst_p1" />
				</p>
				<br>
				<p>
					2. <fmt:message key="inst_p2" />.
				</p>
				<br>
				<p>3. <fmt:message key="inst_p3" /></p>
				<br>
				<p>4. <fmt:message key="inst_p4" /></p>
				<br>
				<p>
					5. <fmt:message key="inst_p5" />
				</p>
				<br>
				<p>6. <fmt:message key="inst_p6" /></p>
			</div>
		</div>
	</div>
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