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
	<form action="Registrazione" method="post" onsubmit="event.preventDefault(); validateSignUp(this)">
	
		<fieldset>
			<legend>Dati anagrafici</legend>
			<input type="text" name="nome" required placeholder="Nome" id="name">
			<span id="errNome"></span><br>
			<input type="text" name="cognome" required placeholder="Cognome" id="surname">
			<span id="errCognome"></span><br>
			<input type="date" name="dataNascita" required id="date">
			<span id="errData"></span><br>
		</fieldset>
		<fieldset>
			<legend>Dati di registrazione</legend>
			<input type="email" name="email" required placeholder="Email" id="email">
			<span id="errEmail"></span><br>
			<input type="text" name="username" required placeholder="Username" id="username">
			<span id="errUsername"></span><br>
			<input type="password" name="password" required placeholder="Password" id="password">
			<span id="errPw"></span><br>
		</fieldset>
		<input type="submit" value="Invia">
		
	</form>
	<%@include file="Footer.jsp" %>
	<script src="jquery.js"></script>
	<script src="script.js"></script>

</body>
</html>