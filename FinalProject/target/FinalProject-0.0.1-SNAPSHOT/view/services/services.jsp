<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<c:forEach items="${services}" var="service">
		${service} <br>
	</c:forEach>
	<a href="/FinalProject/client">Home</a>
</body>
</html>