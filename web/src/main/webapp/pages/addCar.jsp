<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Registration</title>
</head>

<body>
  <%-- начало общей таблицы всей страницы  №1--%>
  <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
    <tr>
      <td valign="top">
      
        <%-- начало таблицы "шапка"  №2--%>
        <table width="100%" height="150" bgcolor="#5dcec6" cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td valign="top" width="15%"><img src="../images/Avtologo.png" width="100%" height="100%"></td>
            <td width="85%" style="font-size: 24px; text-align: center"><i>Проект 3-го модуля Фадеевой Натальи: Rent a car</i></td>
          </tr>
        </table>
        <%-- конец таблицы "шапка"  №2--%> 
        
        <%-- начало средней таблицы  №3--%>
        <table width="100%" height="500" cellpadding="0" cellspacing="0" border="0">
          <tr>
            <!-- начало левой колонки вложенной средней таблицы  №3-->
            <td valign="top" width="15%" bgcolor="#5dc8cd">
              <div><%@include file="../elements/_auth.jsp"%></div><%-- auth --%>
              <div><%@include file="../elements/_menu.jsp"%></div><%-- menu --%>
            </td>
            <%-- конец левой колонки вложенной средней таблицы  №3--%>

            <%-- начало правой колонки вложенной средней таблицы  №3--%>
            <td valign="top" width="85%">
              <c:choose>
                <c:when test="${requestScope.addCar == 0}">
                  <div><%@include file="../elements/_addcar.jsp"%></div><%-- addcar --%>
                </c:when>
                <c:otherwise>
                  <p>${addCarMessage}</p>
                  <form action="addCar" method="post">
                    <input type="hidden" name="command" value="setInfoCar"/>
                    <input type="submit" name="submit" size="15" value="Добавить авто"/>
                  </form>
                </c:otherwise>
              </c:choose>          
            </td>
            <%-- конец левой колонки вложенной средней таблицы  №3--%>
            
          </tr>
        </table> 
        <%-- конец средней таблицы №3 --%> 
        
        <%-- начало "подвала"  №4--%>
        <table width="100%" height="90" bgcolor="#38b2ce" cellpadding="0" cellspacing="0" border="0">
          <tr>
            <td><%@include file="../elements/_footer.jsp"%></td>
          </tr>
        </table> 
        <%-- конец "подвала"  №4--%>
      </td>
    </tr>
  </table>
  <%-- конец общей таблицы всей страницы  №1--%>
</body>
</html>