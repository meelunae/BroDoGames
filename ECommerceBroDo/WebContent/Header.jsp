<%@page import="brodo.model.Carrello"%>
<%@page import="brodo.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title> Header </title>
<style>


body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  top: 0;
  width: 100%;
  overflow: hidden;
  background-color: #0F4C75;
}

.topnav a {
  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #008CBA;
  color: white;
}

.topnav .search-container {
	float: none;
}

.topnav input[type=text] {
  padding: 6px;
  margin-top: 8px;
  font-size: 17px;
  border: none;
}

.topnav .search-container button {
  padding: 6px 10px;
  margin-top: 8px;
  margin-right: 18px;
  margin-left: 4px;
  background: #ddd;
  font-size: 17px;
  border: none;
  cursor: pointer;
}

.topnav .search-container button:hover {
  background: #ccc;
}

 .topnav #utente {
  	float: right; !important
 }

@media screen and (max-width: 600px) {
  .topnav .search-container {
    float: none;
  }
  .topnav a, .topnav input[type=text], .topnav .search-container button {
    float: none;
    display: block;
    text-align: left;
    width: 100%;
    margin: 0;
    padding: 14px;
  }
  
  .topnav input[type=text] {
    border: 1px solid #ccc;  
  }
  
}
</style>
</head>
<body>

	
	<div class="topnav">
            <a href="Catalogo">Catalogo</a>
            <a href="CarrelloServlet">Carrello (0)</a>
            <% if(session.getAttribute("admin") != null){ %>	
            <a href="Amministratore">Pannello Amministratore</a></h3>
            <% } %>
            <% if(session.getAttribute("userLogged") != null){ %>
            <% System.out.println(session.getAttribute("userId")); %>
            <a href="ProfiloUtente?id=<%= session.getAttribute("userId") %>" id="utente">Bentornato, <%= session.getAttribute("username") %></a>
            <a href="LogOut" id="utente">Effettua il logout</a>
            <% } else { %>
            <a href="Accesso" id="utente">Effettua il login</a>
            <% } %>
    <div class="search-container">
        <form action="/action_page.php">
        <input type="text" placeholder="Search.." name="search">
        <button type="submit">Go!</button>
        </form>
    </div>
    	 
</div>
	
</body>
</html>