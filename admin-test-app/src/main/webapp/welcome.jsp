<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${application_name} - Добро пожаловать</title>
</head>
<body>

<h1>${application_name} - Добро пожаловать</h1>

<form method="POST" action="hello-page">
    <label>Представьтесь: </label>
    <input type="text" name="name" value="" placeholder="Ваше имя">
    <input type="submit" value="Отправить">
</form>
</body>
</html>