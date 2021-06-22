<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Submit receipt</title>
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
	<h1 class="center_content">Receipt validation</h1>
	<br>
	<div class="content">
		<div class="wrapper">
			<div class="center_content">
				<table class="table">
					<th class="th">Service no</th>
					<th class="th">Service name</th>
					<th class="th">Service price</th>
					<th class="th">Action</th>
					<c:forEach items="${services}" var="service">
						<tr>
							<td class="td">${service.id}</td>
							<td class="td">${service.name}</td>
							<td class="td">${service.price}UAH</td>
							<td class="td"><form action="/FinalProject/remove-service">
									<button class="button" name="idservice" value="${service.id}">Remove</button>
								</form></td>
						</tr>
					</c:forEach>
				</table><br>
				<form action="/FinalProject/submit" class="center_form">
					<button class="ref_button">Submit</button>
				</form>
			</div>
		</div>
	</div>
<br>
	<form action="/FinalProject/client" class="center_form">
		<button class="ref_button">Back</button>
	</form>
	
</body>
</html>