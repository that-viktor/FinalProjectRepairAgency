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
	<h1 class="page_header">Choose a user to add funds</h1>
	<table class="user_table">
		<th class="th">Name</th>
		<th class="th">Action</th>
		<c:forEach items="${requestScope.clients}" var="client">
			<tr>
				<td class="td">${client.firstName} ${client.surname}
					${client.lastName}</td>
				<td class="td"><form action="/FinalProject/account-info">
						<button class="button" value="${client.id}" name="iduser">Details</button>
					</form></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/FinalProject/admin">Back</a>
</body>
</html>