<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="master" tagdir="/WEB-INF/tags"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sessionScope.master.lastName}</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1 class="page_header">Welcome, ${master.firstName} ${master.lastName}</h1>
	<form action="/FinalProject/logout">
		<button name="user_role" value="3" class="button">Logout</button>
	</form>
	
	<h2 class="center_header">Here are your receipts</h2><hr><br>
	<master:masterTable></master:masterTable>
</body>
</html>