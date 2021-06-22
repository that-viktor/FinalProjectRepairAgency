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
	<div class="header">
		<div class="bar">
			<div class="website_header">
				<a href="/FinalProject/main" class="main_title">Electronic
					repair agency website</a>
			</div>
			<a href="/FinalProject/client" class="a">Services</a>
			 <a	href="/FinalProject/client-profile" class="a">Profile</a> 
			 <a href="/FinalProject/client-comments" class="a">Leave a comment</a>
			Balance ${account.balance} UAH
		</div>
	</div>
	<h1 class="page_header">Comments</h1>
	<div class="content">
		<comments:commentTag />

		<br>

		<form action="/FinalProject/add-comment" class="center_form"
			method="POST">
			<textarea rows="15" cols="70" required name="commentText"
				placeholder="Type your comment here"></textarea>
			<button class="ref_button">Leave a comment</button>
		</form>

		<form action="/FinalProject/client" class="page_header">
			<button class="ref_button">Back</button>
		</form>

	</div>

</body>
</html>