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
	<h2 align=left>Login</h2>
	<div class="login-form">
	<form action="Accesso" method="post" onsubmit="event.preventDefault(); validateLogIn(this)">
		<fieldset>
			Email: <br><input type="email" name="email" required placeholder="mariorossi@example.com" id="email"><br>
			<span id="errEmail"></span><br>
			Password: <br><input type="password" name="password" required placeholder="Password" id="pw"><br>
			<span id="errPw"></span>
		</fieldset>
		<input type="submit" class="button" value="Login">
	</form>
	</div>
	<%if(session.getAttribute("incorrect") != null){ 
		session.removeAttribute("incorrect"); %>
			<p class="incorrect" align="center">I dati inseriti non sono corretti!</p>
		
	<% } %>
	<p id="register-p">Non sei ancora registrato?<br><a class="button" href="SignUp.jsp">Registrati ora!</a></p>
	<%@include file="Footer.jsp" %>
	<script src="./jquery.js"></script>
	<script src="./script.js"></script>
</body>
</html>