<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<title>Title</title>
</head>
<body>
	<form:form method="post" enctype="multipart/form-data" modelAttribute="uploadedFile" action="uploadFile">
		<table>
			<tr>
				<td>Upload File:</td>
				<td><form:input type="file" name="file" path="file"/>
				<td><form:input path="name"/>
				<td style="color: red; font-style: italic;">
				<form:errors path="file" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Upload" /></td>
				<td></td>
			</tr>
		</table>
	</form:form>

</body>
</html>