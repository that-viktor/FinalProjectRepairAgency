<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="comments" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>Comments</title>
</head>
<body>
<h1 class="page_header">Comments</h1>
	<form action="/FinalProject/client">
		<button class="button">Back</button>
	</form>

	<comments:commentTag/>
</body>
</html>