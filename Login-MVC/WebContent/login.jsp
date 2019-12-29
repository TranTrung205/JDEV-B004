<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>LOGIN</title>
</head>
<body>
	<h2>LOGIN</h2>
	<form action="LoginServlet" method="post">
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="txtUsername" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="txtPassword" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="LOGIN" /></td>
			</tr>
		</table>
	</form>
</body>
</html>