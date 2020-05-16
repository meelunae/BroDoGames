<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Iscriviti</title>
</head>
<body>
	<%@include file="Header.jsp" %>
	<form action="Registrazione" method="post">
	
		<fieldset>
			<legend>Dati anagrafici</legend>
			<input type="text" name="nome" required placeholder="Nome">
			<input type="text" name="cognome" required placeholder="Cognome">
			<input type="date" name="dataNascita" required>
		</fieldset>
		<fieldset>
			<legend>Dati di registrazione</legend>
			<input type="email" name="email" required placeholder="Email">
			<input type="text" name="username" required placeholder="Username">
			<input type="password" name="password" required placeholder="Password">
		</fieldset>
		<input type="submit" value="Invia">
		
	</form>
	<%@include file="Footer.jsp" %>

</body>
</html>