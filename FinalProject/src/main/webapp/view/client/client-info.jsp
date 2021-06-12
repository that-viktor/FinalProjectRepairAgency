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
	<h1>Profile ${accountClient.firstName}
		${accountClient.lastName}</h1><br>
		<h2>Personal data</h2> 
		First name:
	<b>${accountClient.firstName}</b>
	<br> Surname:
	<b>${accountClient.surname}</b>
	<br> Last name:
	<b>${accountClient.lastName}</b>
	<br> <br>
		<h2>Account data</h2>
		Account no:
	<b>${sessionScope.account.accountId}</b>
	<br> Balance:
	<b>${sessionScope.account.balance} UAH</b>
	<br><br>
	<h2>Add funds</h2>
	<form action="/FinalProject/add-funds">
		<label>Enter positive sum:</label><br> <input type="text" name="sum" required><br><br>
		<button class="button">Add funds</button>
	</form>
	<br>
	<form action="/FinalProject/clients-list">
		<button class="button">Back</button>
	</form>
</body>
</html>