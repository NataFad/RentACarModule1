<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<div align="center">
    <p style="font-size: 18px">Hello, <b>${userVO.firstname}!</b></p>
    <c:choose>
        <c:when test="${userVO.access == 0}">
            <form method="post" modelAttribute="userVO" action="login" autocomplete="off"
                  style="padding-left:12px;overflow:hidden;">
                <fieldset>
                    <legend>Авторизация</legend>
                    <input type="hidden" name="command" value="login"/>
                    Логин и пароль должны быть не менее 5 символов<br/>
                    <input id="loginAuthForm" name="login" type="text" value="" size="15" maxlength="10"
                           placeholder="Логин" pattern="[A-Za-z]{5,10}" required/> <br/>
                    <input id="passAuthForm" name="password" type="password" value="" size="15" maxlength="20"
                           placeholder="Пароль" pattern="[A-Za-z0-9]{5,20}" autocomplete="off" required/> <br/>
                        ${errorLoginPassMessage} <br/>
                    <input type="submit" value="Log in"/><br/>
                    <a href="${pageContext.request.contextPath}/WEB-INF/pages/registration" style="font-size:15px;color:#0000ff;font-weight:600;">Регистрация</a>
                </fieldset>
            </form>
        </c:when>
        <c:otherwise>
            <div>
                <c:choose>

                    <c:when test="${(userVO.access == 1) or (userVO.access == 2)}">
                        <form method="post" action="inDeveloping">
                            <!--a href="action?command=inDeveloping"-->
                            <a><img src="${pageContext.request.contextPath}/images/shop-cart.gif"
                                    style="position: relative; top: 12px; font-weight:600;"/>
                                Текущая заявка: <b>${not empty carVO ? carVO : ' не выбран автомобиль'}</b>
                            </a>
                        </form>
                        <br/>
                        <form method="post" action="inDeveloping">
                            <!-- href="action?command=inDeveloping"  -->
                            <a style="font-size: 14px; font-weight:600;">Ваши заявки</a>
                        </form>
                    </c:when>
                </c:choose>
                <br/>
                <form method="post" action="exit">
                    <br/>
                    <c:when test="${(userVO.access == 1) or (userVO.access == 2)}">
                        <a style="font-size: 15px; font-weight:600;">Выход</a>
                    </c:when>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</div>