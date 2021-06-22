<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}" />
	<fmt:setBundle basename="locale" />
<c:if test="${receiptsCount == 0}">
	<h2>Looks like there are no work for you</h2>
</c:if>
<c:if test="${receiptsCount != 0 }">
	<table class="table">
		<th class="th"><fmt:message
						key="receipt_no" /></th>
		<th class="th"><fmt:message
						key="date" /></th>
		<th class="th"><fmt:message
						key="total_sum" /></th>
		<th class="th"><fmt:message
						key="action" /></th>
		
		<c:forEach items="${masterReceipts}" var="masterReceipt">
			<tr>
				<td class="td">${masterReceipt.id}</td>
				<td class="td"><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
								type="date" value="${masterReceipt.date}"/></td>
				<td class="td">${masterReceipt.totalSum} UAH</td>
				<td class="td">
					<form action="/FinalProject/master-receipt-details">
						<button class="button" name="idreceipt" value="${masterReceipt.id}"><fmt:message
						key="details" /></button>
					</form>
				</td>				
			</tr>
	</c:forEach>
	</table>
	
</c:if>