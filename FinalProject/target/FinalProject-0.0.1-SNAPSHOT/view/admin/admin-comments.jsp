<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comment" uri="http://tags/cmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comments</title>
</head>
<body>
	<c:forEach items="${adminComments}" var="adminComment">
		<comment:admin-comment comment="${adminComment}" />
	</c:forEach>
</body>
</html>