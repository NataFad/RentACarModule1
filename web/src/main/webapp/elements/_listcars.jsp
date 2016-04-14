<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<div>
	<c:choose>
		<c:when test="${empty requestScope.search_result}">
			<p>${errorSearchCarMessage}</p>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr style="text-align: center">
					<td width="10%">Number</td>
					<td width="40%">Car</td>
					<td width="35%">Description</td>
					<td width="5%">Cost</td>
					<td width="10%">Submit</td>
				</tr>

				<c:forEach var="car" items="${requestScope.search_result}">
					<tr>
						<td align="center"><c:out value="${car.registrationNumber}" /></td>
						<td>
						  <c:out value="${car.modelName}" />,
						  <c:out value="${car.transmission}" />, 
						  <c:out value="${car.fuelName}" />,
							<c:out value="${car.typeName}" />, 
							<c:out value="${car.ratingName}" />, 
							<c:out value="${car.costOfDay}" />,
							<c:out value="${car.discount}" />
						</td>
						<td valign="top"><c:out value="${car.description}" /></td>
						<td style="text-align: right"><c:out value="${car.cost}" /></td>
						<td align="center">
							<form action="action" method="post">
								<input type="hidden" name="command" value="inDeveloping" /> 
								<input type="hidden" name="car_id" value="${car.id}" /> 
								<input type="submit" name="submit" size="90%" value="Арендовать" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</div>	