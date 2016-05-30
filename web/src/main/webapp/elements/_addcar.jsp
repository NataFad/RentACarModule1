<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<div align="center">
	<%-- Сведения об автомобиле --%>
	<form id="addCar" name="addCar" method="post" action="addCar" title="Сведения об автомобиле">
		<input type="hidden" name="command" value="addCarAction" />
		<fieldset>
			<legend>Сведения о новом автомобиле</legend>
			
			<label>Registration number:&nbsp; 
			  <input type="text" name="registrationNumber" id="registrationNumber" size="25"
          placeholder="Регистрационный номер" pattern="[0-9A-Za-z\s\-]{5,15}" required>
			</label><br /><br />
			
			<label>Model and mark:&nbsp; 
        <select name="modelId" id="modelId" required>
          <c:forEach var="modelMark" items="${requestScope.modelList}">
            <c:choose>
              <c:when test="${modelMark.id == requestScope.modelId}">
                <option value="${modelMark.id}" selected>
                  <c:out value="${modelMark.mark}" />, <c:out value="${modelMark.model}" />
                </option>
              </c:when>
              <c:otherwise>
                <option value="${modelMark.id}">
                  <c:out value="${modelMark.mark}" />, <c:out value="${modelMark.model}" />
                </option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </label><br /><br /> 
      
			<label>Transmission:&nbsp; 
			  <select name="transmission" id="transmission" required>
					<c:forEach var="trans" items="${requestScope.transList}">
						<c:choose>
							<c:when test="${trans == requestScope.transmission}">
								<option value="${trans}" selected><c:out value="${trans}" /></option>
							</c:when>
							<c:otherwise>
								<option value="${trans}"><c:out value="${trans}" /></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			  </select>
			</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     
			
			<label>Fuel:&nbsp; 
			  <select name="fuelId" id="fuelId" required>
					<c:forEach var="fuel" items="${requestScope.fuelsList}">
						<c:choose>
							<c:when test="${fuel.id == requestScope.fuelId}">
								<option value="${fuel.id}" selected><c:out value="${fuel.name}" /></option>
							</c:when>
							<c:otherwise>
								<option value="${fuel.id}"><c:out value="${fuel.name}" /></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			  </select>
			</label><br /><br /> 
			
			<label>Type:&nbsp; 
        <select name="typeId" id="typeId" required>
          <c:forEach var="type" items="${requestScope.typeList}">
            <c:choose>
              <c:when test="${type.id == requestScope.typeId}">
                <option value="${type.id}" selected><c:out value="${type.name}" /></option>
              </c:when>
              <c:otherwise>
                <option value="${type.id}"><c:out value="${type.name}" /></option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
      
      <label>Rating:&nbsp; 
        <select name="ratingId" id="ratingId" required>
          <c:forEach var="rating" items="${requestScope.ratingList}">
            <c:choose>
              <c:when test="${rating.id == requestScope.ratingId}">
                <option value="${rating.id}" selected><c:out value="${rating.name}" /></option>
              </c:when>
              <c:otherwise>
                <option value="${rating.id}"><c:out value="${rating.name}" /></option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </select>
      </label><br /><br /> 
      
      <label>Description:&nbsp;
        <textarea name="description" cols="40" rows="3"></textarea>
      </label><br /> 
      
			<input type="submit" width="15%" size="12%" value="Записать автомобиль" /> 
		</fieldset>
	</form>
	
	<c:if test="${!empty errorFilterCarMassager}">
   <p>${errorFilterCarMassager}</p>
  </c:if>
</div>