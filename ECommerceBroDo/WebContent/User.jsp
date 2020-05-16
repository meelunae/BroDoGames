<%@page import="brodo.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% UserBean user = (UserBean) request.getAttribute("utente");%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo di <%=user.getUsername()%></title>
</head>
<body>
	<%@include file="Header.jsp" %>
	<p>Nome utente: <%=user.getUsername()%></p>
	<p>Nome: <%=user.getNome()%></p>
	<p>Cognome: <%=user.getCognome()%></p>
	<p>Data di nascita: <%=user.getDataNascita()%></p>
	<p>Email: <%=user.getEmail()%></p>
	<%if(user.getCitta() != null && !user.getCitta().equals("")){ %>
	<p>Citta: <%=user.getCitta() %></p>
	<%} %>
	<%if(user.getVia() != null && !user.getVia().equals("")){ %>
	<p>Via: <%=user.getVia() %></p>
	<%} %>
	<%if(user.getNumCivico() != 0){ %>
	<p>Numero Civico: <%=user.getNumCivico() %></p>
	<%} %>
	<%if(user.getCap() != 0){ %>
	<p>CAP: <%=user.getCap() %></p>
	<%} %>
	<%@include file="Footer.jsp" %>
	
</body>
</html>