<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>

<!DOCTYPE html>
<div>
    <table style="font-size: 14px; align: center">
        <tr>
            <td height="5"><br/></td>
        </tr>
        <tr>
            <td height="30">
                <s:form name="Form" action="/main" method="post">
                    <input type="submit" size="15" value="На главную"/>
                </s:form>
            </td>
        </tr>
        <tr>
            <td height="30">
                <s:form name="Form" action="/allCars" method="post">
                    <input type="submit" size="15" value="Страница поиска авто"/>
                </s:form>
            </td>
        </tr>
        <%-- Providing some administrator access --%>
        <c:if test="${(not empty userVO.access) and (userVO.access == 2)}">
            <tr height="15">
                <td>-Меню администратора-</td>
            </tr>
            <tr>
                <td height="30">
                    <s:form name="Form" action="/inDeveloping" method="post">
                        <input type="submit" size="15" value="Добавить авто"/>
                    </s:form>
                </td>
            </tr>
            <tr>
                <td height="30">
                    <s:form name="Form" action="/inDeveloping" method="post">
                        <input type="submit" size="15" value="Зaявки и счета"/>
                    </s:form>
                </td>
            </tr>
            <tr>
                <td height="30">
                    <s:form name="Form" action="/inDeveloping" method="post">
                        <input type="submit" size="15" value="Пользователи"/>
                    </s:form>
                </td>
            </tr>
        </c:if>
    </table>
</div>
<div style="margin: 0; min-height: 100px"></div>