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
	<h1>List of masters</h1>
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
	<form action="/FinalProject/details">
		<button name="idreceipt" value="${requestScope.idreceipt}" class="button">Back</button>
	</form>
</body>
</html>