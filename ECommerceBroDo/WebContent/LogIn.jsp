<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log In</title>
</head>
<body>
	<%@include file="Header.jsp" %>
	<form action="Accesso" method="post">
		<fieldset>
			<legend>Email e password</legend>
			<input type="email" name="email" required placeholder="Email">
			<input type="password" name="password" required placeholder="Password">
		</fieldset>
		<input type="submit">
	</form>
	<p>Non sei ancora registrato?<a href="SignUp.jsp">Registrati ora!</a></p>
	<%@include file="Footer.jsp" %>
</body>
</html>