<%@page import="brodo.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
</head>
<body>

	<h3><a href="Catalogo">Catalogo</a>
	<a href="CarrelloServlet">Carrello</a>
	<% if(session.getAttribute("admin") != null){ %>	
	<a href="Amministratore">Pannello Amministratore</a></h3>
	<% } %>
	<% if(session.getAttribute("userLogged") != null){ %>
	<%System.out.println(session.getAttribute("userId")); %>
	Bentornato, <a href="ProfiloUtente?id=<%= session.getAttribute("userId") %>"><%= session.getAttribute("username") %></a>
	<a href="LogOut">Log Out</a>
	<% } else { %>
	<a href="Accesso">Log In</a>
	<a href="SignUp.jsp">Registrati</a>
	<% } %>
	
</body>
</html>