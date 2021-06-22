<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${receiptsCount == 0}">
	<h2>Looks like there are no work for you</h2>
</c:if>
<c:if test="${receiptsCount != 0 }">
	<table class="table">
		<th class="th">Receipt no</th>
		<th class="th">Receipt date</th>
		<th class="th">Receipt sum</th>
		<th class="th">Action</th>
		
		<c:forEach items="${masterReceipts}" var="masterReceipt">
			<tr>
				<td class="td">${masterReceipt.id}</td>
				<td class="td"><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
								type="date" value="${masterReceipt.date}"/></td>
				<td class="td">${masterReceipt.totalSum} UAH</td>
				<td class="td">
					<form action="/FinalProject/master-receipt-details">
						<button class="button" name="idreceipt" value="${masterReceipt.id}">Details</button>
					</form>
				</td>				
			</tr>
	</c:forEach>
	</table>
	
</c:if>