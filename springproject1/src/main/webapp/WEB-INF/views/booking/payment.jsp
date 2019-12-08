<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Payment Page</title>
</head>
<body>
    <form:form action="payment" modelAttribute="ticketForm">
        CreditCard Number : <form:input path="creditCardNumber"/>
        <input type="submit"/>
    </form:form>
    Movie ID : ${ticketForm.movieId}</br>
    Last Name : ${ticketForm.lastName}</br>
</body>
</html>