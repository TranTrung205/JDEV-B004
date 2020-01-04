<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<h2>Xin chúc mừng bạn <%= request.getParameter("txtUsername") %> đã đăng nhập thành công!</h2>
	<h3>Số điện thoại của bạn là: <%= request.getAttribute("phone") %></h3>
	<hr/>
	<h2>Xin chúc mừng bạn ${param.txtUsername} đã đăng nhập thành công!</h2>
	<h3>Số điện thoại của bạn là: ${requestScope.phone}</h3>
</body>
</html>