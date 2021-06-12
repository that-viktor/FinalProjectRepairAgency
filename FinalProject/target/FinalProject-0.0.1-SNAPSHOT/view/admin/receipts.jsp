<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h1>Receipts</h1>
	<a href="/FinalProject/admin">Home</a>
	<div class="wrapper">
		<div class="filter">
			<h2>Filter by:</h2>
			<form action="/FinalProject/filter">
				<select name="filter">
					<option disabled>Choose filter</option>
					<c:forEach items="${statuses}" var="status">
						<option value="${status.id}">${status.name}</option>
					</c:forEach>
				</select> <br>
			</form>
		</div>

		<div class="content">
			<table class="table">
				<th class="th">Receipt no</th>
				<th class="th">Date</th>
				<th class="th">Total sum</th>
				<th class="th">Status</th>
				<th class="th">Action</th>
				<c:forEach items="${receipts}" var="receipt">
					<tr style="border: 1px solid;">
						<td style="border: 1px solid;" class="td">${receipt.id}</td>
						<td style="border: 1px solid;" class="td">${receipt.date}</td>
						<td style="border: 1px solid;" class="td">${receipt.totalSum}</td>
						<td style="border: 1px solid;" class="td">${receipt.status}</td>
						<td style="border: 1px solid;"><form
								action="/FinalProject/details">
								<button value="${receipt.id}" name="idreceipt" class="button">Details</button>
							</form></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>


	<br>
</body>
</html>