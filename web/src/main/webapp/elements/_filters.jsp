<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<div>
	<form id="filterforsearch" name="filterforsearch" method="post" action="action" title="Фильтр для поиска">
		<input type="hidden" name="command" value="search_cars" />
		<%-- <fieldset><legend>Фильтры поиска</legend>
		<table>
		  <tr align="left">
		    <td width="20%"> Период </td>
		    <td width="15%"> Transmission </td>
		    <td width="15%"> Fuel </td>
		    <td width="20%"> Type </td>
		    <td width="20%"> Rating </td>
		    <td width="10%"></td>
		  </tr>
		  <tr valign="top">
        <td>
          С:&nbsp;&nbsp;&nbsp;<input type="date" name="fromDate" id="fromDate" size="10" value="${requestScope.fromDate}" min="${requestScope.fromDate}" required AUTOFOCUS /><br />
          По:&nbsp;<input type="date" name="byDate" id="byDate" size="10" value="${requestScope.byDate}" min="${requestScope.fromDate}" required AUTOFOCUS />
        </td>
        <td> 
          <select name="Transmission" id="Transmission" size="${requestScope.transList.size()}" multiple>
            <c:forEach var="trans" items="${requestScope.transList}">
              <option value="${trans}"><c:out value="${trans}" /></option>
            </c:forEach>
          </select>  
        </td>
        <td>
          <select name="Fuel" id="Fuel" size="${requestScope.fuelsList.size()}" multiple>
            <c:forEach var="fuel" items="${requestScope.fuelsList}">
              <option value="${fuel.id}"><c:out value="${fuel.name}" /></option>
            </c:forEach>
          </select> 
        </td>
        <td>
          <select name="Type" id="Type" size="${requestScope.typeList.size()}" multiple>
            <c:forEach var="type" items="${requestScope.typeList}">
              <option value="${type.id}"><c:out value="${type.name}" /></option>
            </c:forEach>
          </select> 
        </td>
        <td>
         <select name="Rating" id="Rating" size="${requestScope.ratingList.size()}" multiple>
          <c:forEach var="rating" items="${requestScope.ratingList}">
            <option value="${rating.id}"><c:out value="${rating.name}" /></option>
          </c:forEach>
         </select> 
        </td>
        <td valign="bottom">
          <input type="submit" size="100%" value="Найти" />
        </td>
      </tr>
		</table>
		</fieldset> --%>
		<fieldset>
			<legend>Фильтры поиска</legend>
			<label>С:&nbsp;&nbsp;
			 <input type="date" name="fromDate" id="fromDate" value="${requestScope.fromDate}" width="7%" min="2000-01-01" required AUTOFOCUS />&nbsp;&nbsp;&nbsp;
			</label> 
			<label>По:&nbsp;
			 <input type="date" name="byDate" id="byDate" value="${requestScope.byDate}" width="7%" min="2000-01-01" required AUTOFOCUS />&nbsp;&nbsp;&nbsp;
			</label> 
			
			<label>Transmission:&nbsp; 
			<select name="transmission" id="transmission">
			  <option value="0" selected>Все виды</option>
				<c:forEach var="trans" items="${requestScope.transList}">
					<option value="${trans}"><c:out value="${trans}" /></option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;
			</label> 
			
			<label>Fuel:&nbsp; 
			<select name="fuelId" id="fuelId">
			  <option value="0" selected>Все виды</option>
				<c:forEach var="fuel" items="${requestScope.fuelsList}">
					<option value="${fuel.id}"><c:out value="${fuel.name}" /></option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;
			</label> 
			
			<label>Type:&nbsp; 
			<select name="typeId" id="typeId">
			  <option value="0" selected>Все типы</option>
				<c:forEach var="type" items="${requestScope.typeList}">
					<option value="${type.id}"><c:out value="${type.name}" /></option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;
			</label> 
			
			<label>Rating:&nbsp; 
			<select name="ratingId" id="ratingId">
			  <option value="0" selected>Все виды рейтинга</option>
				<c:forEach var="rating" items="${requestScope.ratingList}">
					<option value="${rating.id}"><c:out value="${rating.name}" /></option>
				</c:forEach>
			</select>&nbsp;&nbsp;&nbsp;
			</label>
			
			<input type="submit" width="15%" size="12%" value="Найти" />
		</fieldset>
	</form>
	<c:if test="${!empty errorFilterCarMassager}">
	 <p>${errorFilterCarMassager}</p>
	</c:if>
</div>