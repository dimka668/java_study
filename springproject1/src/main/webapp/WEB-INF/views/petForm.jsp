<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<title>Title</title>
</head>
<body>
	<form:form method="POST" modelAttribute="pet" action="/owners/1/pets/22/edit">
		<table>
			<tr>
				<td>petId:      <form:input name="petId" path="petId"/>
				<td>ownerId:    <form:input name="ownerId" path="ownerId"/>
			</tr>
						<tr>
            				<td></td>
            				<td><input type="submit" value="create" /></td>
            				<td></td>
            			</tr>
		</table>
	</form:form>

</body>
</html>