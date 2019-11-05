<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${application_name} - Добавить нового пользователя</title>
</head>
<body>

<h1>${application_name} - Добавить нового пользователя</h1>

<form action = "${pageContext.request.contextPath}/users" method="post">
    <input required type="text" name="name" placeholder="Имя">
    <input required type="text" name="age" placeholder="Возраст">
    <input type="submit" value="Сохранить">
</form>
</body>
</html>