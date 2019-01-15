<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Здравствуйте!</title>
</head>
<body>
Если вы хотите начать работу с базой данных пользователей - <br>
нажмите кнопку ниже:

<form action = "${pageContext.request.contextPath}/users" method="get">
    <input type="submit" value="Начать работу с базой данных">
</form>
</body>
</html>