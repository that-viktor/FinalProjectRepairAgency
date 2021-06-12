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
	<c:forEach items="${requestScope.masters}" var="master">
	${master}
	<br>
	</c:forEach>
	<br>
	<form action="/FinalProject/details">
		<button name="idreceipt" value="${requestScope.idreceipt}">Back</button>
	</form>
</body>
</html>