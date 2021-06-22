<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			<a href="/FinalProject/receipts" class="a">View Receipts</a> <a
				href="/FinalProject/clients-list" class="a">Add funds to user</a> <a
				href="/FinalProject/admin-comments" class="a">Check the comments</a>
		</div>
	</div>
	<br>
	<h1 class="center_content">Profile ${accountClient.firstName}
		${accountClient.lastName}</h1>
	<br>
	<div class="content">
		<div class="masters_wrapper">
			<div class="center_text">
				<h2>Personal data</h2>
				First name: <b>${accountClient.firstName}</b> <br> Surname: <b>${accountClient.surname}</b>
				<br> Last name: <b>${accountClient.lastName}</b> <br> <br>
				<h2>Account data</h2>
				Account no: <b>${sessionScope.account.accountId}</b> <br>
				Balance: <b>${sessionScope.account.balance} UAH</b> <br> <br>
				<h2>Add funds</h2><br>
				<form action="/FinalProject/add-funds">
					<label>Enter positive sum:</label><br> <input type="number"
						min="1" name="sum" step="any" required><br> <br>
					<button class="ref_button">Add funds</button>
				</form>
				<br>
			</div>
		</div>

	</div>
	<br>
	<br>
	<form action="/FinalProject/clients-list" class="center_form">
		<button class="ref_button">Back</button>
	</form>
</body>
</html>