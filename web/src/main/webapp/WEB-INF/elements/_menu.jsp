<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<div>
	<table style="font-size: 14px; align: center">
	  <tr><td height="5"><br/></td></tr>
		<tr>
		  <td height="30">
				<form action="main" method="post">
				  <input type="submit" name="submit" size="15" value="На главную"/>
				</form>
			</td>
		</tr>
		<tr>
			<td height="30">
				<form action="get_all_cars" method="post">
					 <!--input type="hidden" name="command" value="get_all_cars"/-->
					 <input type="submit" name="submit" size="15" value="Страница поиска авто"/>
				</form>
			</td>
		</tr>
		<%-- Providing some administrator access --%>
		<c:if test="${(not empty userVO.access) and (userVO.access == 2)}">
			<tr height="15">
				<td>-Меню администратора-</td>
			</tr>
			<tr>
				<td height="30">
					<form action="addCar" method="post">
					  	<!--input type="hidden" name="command" value="setInfoCar"/-->
						<input type="submit" name="submit" size="15" value="Добавить авто"/>
					</form>
				</td>
			</tr>
			<tr>
				<td height="30">
					<form action="inDeveloping" method="post">
						<!--input type="hidden" name="command" value="inDeveloping"/-->
						<input type="submit" name="submit" size="15" value="Зaявки и счета"/>
					</form>
				</td>
			</tr>
			<tr>
				<td height="30">
					<form action="inDeveloping" method="post">
						<!--input type="hidden" name="command" value="inDeveloping"/-->
						<input type="submit" name="submit" size="15" value="Пользователи"/>
					</form>
				</td>
			</tr>
		</c:if>
	</table>
</div>
<div style="margin: 0; min-height: 100px"></div>