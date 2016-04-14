<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Registration</title>
</head>

<body>
	<!-- начало общей таблицы всей страницы  №1-->
	<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td valign="top">
			
				<!-- начало таблицы "шапка"  №2-->
				<table width="100%" height="150" bgcolor="#5dcec6" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td valign="top" width="15%"><img src="../images/Avtologo.png" width="100%" height="100%"></td>
						<td width="85%" style="font-size: 24px; text-align: center"><i>Проект 3-го модуля Фадеевой Натальи: Rent a car</i></td>
					</tr>
				</table>
        <!-- конец таблицы "шапка"  №2--> 
				
				<!-- начало средней таблицы  №3-->
				<table width="100%" height="500" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<!-- начало левой колонки вложенной средней таблицы  №3-->
						<td valign="top" width="15%" bgcolor="#5dc8cd">
							<div><%@include file="../elements/_auth.jsp"%></div><!-- auth -->
							<div><%@include file="../elements/_menu.jsp"%></div><!-- menu -->
						</td>
						<!-- конец левой колонки вложенной средней таблицы  №3-->

						<!-- начало правой колонки вложенной средней таблицы  №3-->
						<td valign="top" width="85%">
							<!-- Личные данные -->
							<form id="PersonalData" name="PersonalData" method="post" action="action" title="Личные данные">
								<input type="hidden" name="command" value="registration" />
								<fieldset>
									<legend>Личные данные</legend>
									<label>Фамилия:&nbsp;
									<input type="text" name="surname" id="surname" size="25" maxlength="25" placeholder="Фамилия"
										pattern="^[А-ЯЁA-Z][а-яёa-z]{1,25}" required /></label><br /> 
									<label>Имя:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="text" name="name" id="name" size="25" placeholder="Имя" pattern="^[А-ЯЁA-Z][а-яёa-z]{1,20}" required /></label><br />
  
									<!-- Паспортные данные -->
									<fieldset>
										<legend>Паспортные данные</legend>
										<label>Серия и номер:&nbsp;&nbsp;&nbsp;
										<input type="text" name="passportNumber" id="passportNumber" size="20" maxlength="9" placeholder="Серия и номер паспорта"
											pattern="^[A-Z]{2}[0-9]{7}"></label><br />
										<label>Дата выдачи:&nbsp;&nbsp;&nbsp;
										<input type="date" name="passportIssue" min="2000-01-01"></label><br /> 
										<label>Срок действия:&nbsp;
										<input type="date" name="passportExpire" max="2075-01-01"></label><br /> 
										<label>Кем выдан:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="text" name="passportAuthority" id="passportAuthority" size="50" minlength="1" maxlength="45" placeholder="Кем выдан"></label>
									</fieldset>

									<!-- Дата рождения -->
									<fieldset>
										<legend>Дата рождения</legend>
										<label>Дата рождения:&nbsp;<input type="date" name="birthday" min="1950-01-01" required></label>
									</fieldset>

									<!-- Данные для входа и восстановления пароля -->
									<fieldset>
										<legend>Данные для входа и восстановления пароля</legend>
										<label>Логин:&nbsp;&nbsp;&nbsp;
										<input type="text" name="login" id="login" size="30" maxlength="20" placeholder="Логин" pattern="[A-Za-z]{5,20}" value="" required></label><br> 
										<label>Пароль:&nbsp;&nbsp;
										<input type="Password" name="password" id="password" size="30" maxlength="20" placeholder="Пароль" pattern="[A-Za-z]{5,20}"
											value="" autocomplete="off" required></label><br>
										${errorLoginPassMessage}<br />
									</fieldset>

									<!-- Координаты -->
									<fieldset>
										<legend>E-mail</legend>
										<label>E-mail:&nbsp;
										<input type="email" name="email" id="email" placeholder="Введите адрес почты" autocomplete="on" required></label><br>
										<label>Телефонный номер в формате +ХХХ&ndash;ХХ&ndash;ХХХ&ndash;ХХ&ndash;ХХ:&nbsp; 
										<input type="text" name="phone" size="20" maxlength="17" pattern="\+[0-9]{3}\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}"
											placeholder="+375&ndash;29&ndash;123&ndash;45&ndash;67">
										</label><br>
									</fieldset>
									<br /> 
									<input type="submit" value="Зарегистрироваться">
								</fieldset>
							</form>
						</td>
						<!-- конец левой колонки вложенной средней таблицы  №3-->
						
					</tr>
				</table> 
				<!-- конец средней таблицы №3 --> 
				
				<!-- начало "подвала"  №4-->
				<table width="100%" height="90" bgcolor="#38b2ce" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td><%@include file="../elements/_footer.jsp"%></td>
					</tr>
				</table> 
				<!-- конец "подвала"  №4-->
			</td>
		</tr>
	</table>
	<!-- конец общей таблицы всей страницы  №1-->
</body>
</html>