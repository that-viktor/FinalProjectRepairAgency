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
	<h1>Receipt validation</h1>
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
	</table>
	<form action="/FinalProject/client">
		<button class="button" >Back</button>
	</form>
	<form action="/FinalProject/submit">
		<button class="button">Submit</button>
	</form>
</body>
</html>