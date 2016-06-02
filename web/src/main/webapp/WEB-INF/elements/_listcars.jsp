<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<div>
    <c:choose>
        <c:when test="${empty search_result}">
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

                <c:forEach var="car" items="${search_result}">
                    <tr>
                        <td align="center"><c:out value="${car.registrationNumber}"/></td>
                        <td>
                            <c:out value="${car.model}"/>,
                            <c:out value="${car.marka}"/>,
                            <c:out value="${car.transmission}"/>,
                            <c:out value="${car.fuel}"/>,
                            <c:out value="${car.type}"/>,
                            <c:out value="${car.rating}"/>
                        </td>
                        <td valign="top"><c:out value="${car.description}"/></td>
                        <td style="text-align: right"><c:out value="${car.cost}"/></td>
                        <td align="center">
                            <form action="action" method="post">
                                <input type="hidden" name="command" value="inDeveloping"/>
                                <input type="hidden" name="car_id" value="${car.id}"/>
                                <input type="submit" name="submit" size="90%" value="Арендовать"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</div>	