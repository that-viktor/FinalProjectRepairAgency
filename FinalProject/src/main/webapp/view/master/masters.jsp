<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receipt ${requestScope.idreceipt}</title>
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
	<div class="content">
		<div class="wrapper">
		<h1 class="center_text">List of masters</h1><hr>
	<table class="table">
		<th class="th">Master no</th>
		<th class="th">Master name</th>
		<th class="th">Action</th>		
		<c:forEach items="${requestScope.masters}" var="master">
			<tr>
			<td class="td">${master.id}</td>
			<td class="td">${master.firstName} ${master.surname} ${master.lastName}</td>
			<td class="td"><form action="/FinalProject/setmaster"><button class="button" name="idmaster" value="${master.id}">Set master</button></form></td>
			</tr>
			<br>
		</c:forEach>
	</table>

	<br>
	<form action="/FinalProject/details" class="center_form">
		<button name="idreceipt" value="${requestScope.idreceipt}" class="ref_button">Back</button>
	</form>
		</div>
	</div>
	
</body>
</html>