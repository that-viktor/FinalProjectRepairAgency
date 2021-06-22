<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>Clients list</title>
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
	<h1 class="page_header">Choose a user to add funds</h1>
	<div class="content">
		<div class="wrapper">
			<h1 class="center_content">Users</h1><hr> <br>
			<table class="user_table">
				<th class="th">Name</th>
				<th class="th">Action</th>
				<c:forEach items="${requestScope.clients}" var="client">
					<tr>
						<td class="td">${client.firstName}${client.surname}
							${client.lastName}</td>
						<td class="td"><form action="/FinalProject/account-info">
								<button class="button" value="${client.id}" name="iduser">Details</button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br>
	<div class="center_form">
		<a href="/FinalProject/admin" class="ref_button">Back</a>
	</div>
</body>
</html>