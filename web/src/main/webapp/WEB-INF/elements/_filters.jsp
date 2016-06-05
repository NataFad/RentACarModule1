<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<div>
    <s:form name="Form" action="/search_cars" modelAttribute="filter" autocomplete="true" method="post">
        <input type="hidden" name="command" value="search_cars"/>
        <fieldset>
            <legend>Фильтры поиска</legend>
            <label>С:&nbsp;
                <input type="date" name="dateFrom" id="dateFrom" value="${dateFrom}"
                       min="2000-01-01" required AUTOFOCUS/>&nbsp;&nbsp;&nbsp;

            </label>
            <label>По:&nbsp;
                <input type="date" name="dateBy" id="dateBy" value="${dateBy}" width="7%" min="2000-01-01"
                       required AUTOFOCUS/>&nbsp;&nbsp;&nbsp;
            </label>

            <label>Transmission:&nbsp;
                <select name="transmission" id="transmission">
                    <option value="">Все виды</option>
                    <c:forEach var="trans" items="${transList}">
                        <option value="${trans.name}" ${trans.name == filter.transmission ? 'selected="selected"' : ''}>
                            <c:out
                                    value="${trans}"/></option>
                    </c:forEach>
                </select>&nbsp;&nbsp;
            </label>

            <label>Fuel:&nbsp;
                <select name="fuelId" id="fuelId">
                    <option value="0">Все виды</option>
                    <c:forEach var="fuel" items="${fuelsList}">
                        <option value="${fuel.id}" ${fuel.id == filter.fuelId ? 'selected="selected"' : ''}><c:out
                                value="${fuel.name}"/></option>
                    </c:forEach>
                </select>&nbsp;&nbsp;
            </label>

            <label>Type:&nbsp;
                <select name="typeId" id="typeId">
                    <option value="0">Все типы</option>
                    <c:forEach var="type" items="${typeList}">
                        <option value="${type.id}" ${type.id == filter.typeId ? 'selected="selected"' : ''}><c:out
                                value="${type.name}"/></option>
                    </c:forEach>
                </select>&nbsp;&nbsp;
            </label>

            <label>Rating:&nbsp;
                <select name="ratingId" id="ratingId">
                    <option value="0">Все виды</option>
                    <c:forEach var="rating" items="${ratingList}">
                        <option value="${rating.id}" ${rating.id == filter.ratingId ? 'selected="selected"' : ''}><c:out
                                value="${rating.name}"/></option>
                    </c:forEach>
                </select>&nbsp;&nbsp;
            </label>

            <label>По:&nbsp;
                <select name="recordPerPage" id="recordPerPage">
                    <c:forEach var="perPage" items="${perPageList}">

                        <option value="${perPage.value}" ${perPage.value == filter.recordPerPage ? 'selected="selected"' : ''}>
                            <c:out value="${perPage.value}"/></option>
                    </c:forEach>
                </select>&nbsp;&nbsp;
            </label>

            <input type="submit" width="8%" size="12%" value="Найти"/>
        </fieldset>

        <br/>
        -&nbsp;
        <c:forEach begin="1" end="${requestScope.maxPages}" varStatus="i">
            <c:choose>
                <c:when test="${i.index == requestScope.currentPage}">
                    <b>Page ${i.index}</b>&nbsp;&nbsp;-&nbsp;
                </c:when>
                <c:otherwise>
                    -&nbsp;<a href="/search_cars?page=${i.index}">Page ${i.index}</a>-&nbsp;
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <br/>

        <c:if test="${!empty errorFilterCarMassager}">
            <p>${errorFilterCarMassager}</p>
            <p> Filter: ${filterVO} </p>
        </c:if>
    </s:form>

</div>