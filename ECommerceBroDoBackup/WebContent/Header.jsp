<%@page import="brodo.model.Carrello"%>
<%@page import="brodo.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet href="css/style.css" type="text/css">
</head>
<header>

	
	<div class="topnav">
			<a href="Catalogo"><image id="logo" src="https://cdn.discordapp.com/attachments/690652460439830601/721367239181336576/Senza_titolo1.png"></a>
            <a href="Catalogo">Catalogo</a>
            <% if (session.getAttribute("carrello") != null) { %>
            <a href="CarrelloServlet">Carrello (<%= ( (Carrello) session.getAttribute("carrello")).getNumProdotti() %>)</a>
            <% } else { %>
            <a href="CarrelloServlet">Carrello (0)</a>
            <% } %>
            <% if(session.getAttribute("admin") != null){ %>	
            <a href="Amministratore">Pannello Amministratore</a>
            <% } %>
            <% if(session.getAttribute("userLogged") != null){ %>
            <% System.out.println(session.getAttribute("userId")); %>
            <a href="ProfiloUtente?id=<%= session.getAttribute("userId") %>" id="utente">Bentornato, <%= session.getAttribute("username") %></a>
            <a href="LogOut" id="utente">Effettua il logout</a>
            <% } else { %>
            <a href="LogIn.jsp" id="utente">Effettua il login</a>
            <% } %>
    <div class="search-container">
        <form action="/action_page.php">
        <input type="text" placeholder="Search.." name="search">
        <button type="submit">Go!</button>
        </form>
    </div>
    	 
</div>
	
</header>
</html>