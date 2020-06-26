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

	<div class="topnav" id="myTopnav">
			<div class="logo"><a href="Catalogo"><img height="100px" src="imgs/BroDoLogo.png"></a></div>
			<div class="links">
		  		<a href="Catalogo">Catalogo</a>
	            <% if (session.getAttribute("carrello") != null) { %>
	            <a href="Carrello" id="qtaCart">Carrello (<%= ( (Carrello) session.getAttribute("carrello")).getNumProdotti() %>)</a>
	            <% } else { %>
	            <a href="CarrelloServlet">Carrello (0)</a>
	            <% } %>
				<a href="Wishlist?idUtente=<%= session.getAttribute("userId") %>">Wishlist</a>
	            <% if(session.getAttribute("admin") != null){ %>	
	            <a href="Amministratore">Pannello Amministratore</a>
	            <% } %>
	            <% if(session.getAttribute("userLogged") != null){ %>
	            <a href="ProfiloUtente?id=<%= session.getAttribute("userId") %>" id="utente">Bentornato, <%= session.getAttribute("username") %></a>
	            <a href="LogOut" id="utente">Logout</a>
	            <% } else { %>
	            <a href="LogIn.jsp" id="utente">Login</a>
	            <% } %>
            </div>
	  <a href="javascript:void(0);" class="icon" onclick="myFunction()">
	    <img src="imgs/hamburger.png" width="42px">
	  </a>
	</div>
	
	<script>
	function myFunction() {
	  var x = document.getElementById("myTopnav");
	  if (x.className === "topnav") {
	    x.className += " responsive";
	  } else {
	    x.className = "topnav";
	  }
	}
	</script>

</header>
</html>