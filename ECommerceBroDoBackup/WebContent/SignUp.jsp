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
	<div class="login-form">
	<form action="Registrazione" method="post" onsubmit="event.preventDefault(); validateSignUp(this)">
	
		<fieldset>
			<legend align="center">Dati anagrafici</legend>
			<input type="text" name="nome" required placeholder="Nome" id="name">
			<div id="errNome"></div>
			<input type="text" name="cognome" required placeholder="Cognome" id="surname">
			<div id="errCognome"></div>
			<input type="date" name="dataNascita" required id="date">
			<div id="errData"></div>
		</fieldset>
		<fieldset>
			<legend align="center">Dati di registrazione</legend>
			<input type="email" name="email" required placeholder="Email" id="email">
			<div id="errEmail"></div>
			<input type="text" name="username" required placeholder="Username" id="username">
			<div id="errUsername"></div>
			<input type="password" name="password" required placeholder="Password" id="password">
			<div id="errPw"></div>
		</fieldset>
		<input type="submit" value="Registrati" class="button">
		
	</form>
	</div>
	<%if(session.getAttribute("failed") != null){
		
		session.removeAttribute("failed"); %>
		
		<p class="incorrect" align="center">Registrazione fallita!</p>
		
	<% }
	%>
	<%@include file="Footer.jsp" %>
	<script src="jquery.js"></script>
	<script src="script.js"></script>

</body>
</html>