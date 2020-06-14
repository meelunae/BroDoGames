<%@page import="brodo.model.ProdottoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% ProdottoBean p = (ProdottoBean) request.getAttribute("prodottoScelto"); 
	if(p == null){
	
		response.sendRedirect("./Catalogo");
		return;

	}
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><%=p.getTitolo() %></title>
</head>
<body>
<%@include file="Header.jsp" %>
	<img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'" style="width:100px">
	<p><%=p.getTitolo() %></p>
	<p><%=p.getCasaSviluppatrice() %></p>
	<p><%=p.getPegi() %>
	<p><%=p.getDescrizione() %></p>
	<p>Prezzo Digitale:<%=p.getPrezzoDig() %></p>
	<p>Prezzo Fisico:<%=p.getPrezzoFis() %></p>
	<p>Quantita' Fisica:<%=p.getQtaFis() %></p>
	<p>Quantita' Digitale:<%=p.getQtaDig() %></p>
	<a href="Catalogo">Torna al catalogo</a>
<%@include file="Footer.jsp" %>
</body>
</html>