<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			<a href="/FinalProject/receipts" class="a">View Receipts</a> <a
				href="/FinalProject/clients-list" class="a">Add funds to user</a> <a
				href="/FinalProject/admin-comments" class="a">Check the comments</a>
		</div>
	</div>
	<form action="/FinalProject/logout">
		<button name="user_role" value="1" class="logout_button">Logout</button>
	</form>
	<div class="content">
		<div class="wrapper">
			<br>
			<h1 class="page_header">Admin profile</h1>
			<div class="center_text">
				Hello, ${sessionScope.admin.firstName}
				<p>
					1. To see all the receipts choose <a href="/FinalProject/receipts"
						class="big_url">this option</a>.
				</p>
				<p>
					2. To add funds to users choose <a
						href="/FinalProject/clients-list" class="big_url">this option</a>.
				</p>
				<p>
					3. To check the user's comments choose <a
						href="/FinalProject/admin-comments" class="big_url">this
						option</a>.
				</p>
			</div>
		</div>
		<br> <br>
		<div class="wrapper">
			<h2 class="page_header">Instruction</h2>
			<div class="center_text">
				<p>1. Client will form the receipt depending on its wishes.</p>
				<br>
				<p>
					2. Formed receipt will appear on the <b>receipts</b> page.
				</p>
				<br>
				<p>3. You should choose a master for this receipt first.</p>
				<br>
				<p>4. Then you should count receipt total sum by pressing the
					corresponding button.</p>
				<br>
				<p>
					5. After doing the previous steps you will be allowed to press the
					<b>Approve</b> button.
				</p>
				<br>
				<p>6. Clients are also allowed to leave a comment after their
					receipt was processed.</p>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="footer_left_part">
			<h3>Our contacts</h3>
			<br>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<p>+38 067 624 07 23</p>
			<br>
		</div>
		<div class="footer_right_part">
			<h3>Our locations</h3>
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