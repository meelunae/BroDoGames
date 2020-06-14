<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log In</title>
<link href="css/catalogo.css" rel="stylesheet">
</head>
<body>
	<%@include file="Header.jsp" %>
	<form action="Accesso" method="post" onsubmit="event.preventDefault(); validateLogIn(this)">
		<fieldset>
			<legend>Email e password</legend>
			Email: <input type="email" name="email" required placeholder="mariorossi@example.com" id="email">
			<span id="errEmail"></span><br>
			Password: <input type="password" name="password" required placeholder="Password" id="pw">
			<span id="errPw"></span>
		</fieldset>
		<input type="submit">
	</form>
	<%if(session.getAttribute("incorrect") != null){ 
		session.removeAttribute("incorrect"); %>
			<p class="incorrect">I dati inseriti non sono corretti!</p>
		
	<% } %>
	<p>Non sei ancora registrato?<a href="SignUp.jsp">Registrati ora!</a></p>
	<%@include file="Footer.jsp" %>
	<script src="./jquery.js"></script>
	<script src="./script.js"></script>
</body>
</html>