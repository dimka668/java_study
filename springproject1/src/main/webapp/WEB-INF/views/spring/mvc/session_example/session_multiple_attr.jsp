<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>@SessionAttributes</title>
</head>
<body>
	<h1>@SessionAttributes</h1>

	<P>${person.greeting}</P>
	<P>${sheldon.greeting}</P>
</body>
</html>
