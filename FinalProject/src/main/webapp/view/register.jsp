<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			<div class="header_text">Register page</div>
		</div>
	</div>
	<div class="content">
		<br>
		<h1 class="center_header">Register</h1>
		<br>
		<div class="login_wrapper">
			<h3 class="white_ceter_header">To enter the system pass the register
				form</h3>
			<br>
			<form method="post" action="/FinalProject/register"
				class="center_form">
				<label>Login </label><input type="text" value="client" name="login"
					required><br> <label>Password </label><input
					type="password" value="client" name="pass" required><br>
				<label>First name </label><input type="text" name="fname" required><br>
				<label>Surname </label><input type="text" name="surname" required><br>
				<label>Last name </label><input type="text" name="lname" required><br>
				<label>Phone number </label><input type="text" name="phone_num"
					required pattern="\+[3][8][0][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" placeholder="+38xxxxxxxxxx"><br> <label>Contact email </label><input
					type="text" name="email" required pattern=".*@gmail\.com" placeholder="example@gmail.com"><br> <br>
				<input type="submit" value="Register" class="ref_button"><br>
				<br> <a href="/FinalProject/main" class="ref_button">Home</a>
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