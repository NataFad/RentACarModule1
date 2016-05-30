<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<div align="center">
  <p style="font-size: 18px">Hello, <b>${sessionScope.user.name}!</b></p>
	<c:choose>
		<c:when test="${sessionScope.access == 0}">
			 <form action="action" method="post" autocomplete="off" style="padding-left:12px;overflow:hidden;" >
          <fieldset><legend>Авторизация</legend>
            <input type="hidden" name="command" value="login" />
            Логин и пароль должны быть не менее 5 символов<br />
            <input id="loginAuthForm" name="login" type="text" value="" size="15" maxlength="10" placeholder="Логин" pattern="[A-Za-z]{5,10}" required /> <br />
            <input id="passAuthForm" name="password" type="password" value="" size="15" maxlength="20" placeholder="Пароль" pattern="[A-Za-z0-9]{5,20}" autocomplete="off" required/> <br />
            ${errorLoginPassMessage} <br />
            <input type="submit" value="Log in"/><br />
            <a href="../pages/registration.jsp" style="font-size:15px;color:#0000ff;font-weight:600;">Регистрация</a>
          </fieldset>
      </form>
		</c:when>
		<c:otherwise>
			<div>
				<c:choose>
					<c:when test="${sessionScope.access > 0}">
						<a href="action?command=inDeveloping"> 
						  <img src="../../images/shop-cart.gif" style="position: relative; top: 12px; font-weight:600;" />Текущая заявка: <b>${not empty sessionScope.car ? sessionScope.car : ' не выбран автомобиль'}</b>
						</a>
						<br />
						<a href="action?command=inDeveloping" style="font-size: 14px; font-weight:600;">Ваши заявки</a>
					</c:when>
				</c:choose>
				<br />
				<br /> <a href="action?command=exit" style="font-size: 15px; font-weight:600;">Выход</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>