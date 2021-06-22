<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			Hello, ${sessionScope.client.login}! <a
				href="/FinalProject/client-profile" class="a">Profile</a> <a
				href="/FinalProject/client-comments" class="a">Leave a comment</a>
			Balance ${account.balance} UAH
		</div>
	</div>
	<br>
	<h1 class="center_content">Receipt processed successfully</h1><br>
	<div class="content">
		<div class="wrapper">
			<b>Well done!</b> We will contact you in a few minutes. <br>As soon as our administrator approve your receipt,
			the info will be shown on <b>your profile!</b>. You can pay the receipt from your profile page.<br> <b>Thanks for your order!</b>
		</div>
	</div>
	<br>
	<form action="/FinalProject/client" class="center_form">
		<button class="ref_button">To profile</button>
	</form>
</body>
</html>