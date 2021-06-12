<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.master.lastName}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<form action="/FinalProject/logout">
		<button name="user_role" value="3">Logout</button>
	</form>
</body>
</html>