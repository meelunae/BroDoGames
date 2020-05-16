<%@page import="brodo.model.ItemCarrello"%>
<%@page import="java.util.ArrayList"%>
<%@page import="brodo.model.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% Carrello c = (Carrello) request.getAttribute("cart"); %>
<%double tot = 0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
</head>
<body>
	<%@include file="Header.jsp" %>

	<% if(c != null && c.getProdotti().size() != 0){	%>
	
		<h2>Checkout eseguito con successo!</h2>
		<h3>Riepilogo:</h3>
		<table border=1>
		<tr>
			
			<th>Codice</th>
			<th>Titolo</th>
			<th>Quantità</th>	
			<th>Totale</th>							
		
		</tr>
		
		<% 
			ArrayList<ItemCarrello> it = c.getProdotti();
			for(ItemCarrello i : it){ %>

			<tr>
				<td><%=i.getProdotto().getId() %></td>
				<td><%=i.getProdotto().getTitolo() %></td>
				<%--<td><%=i.getProdotto().getPrezzoFis() + i.getProdotto().getPrezzoDig() %></td> --%>
				<td>Fisico: <%=i.getQtaFis() %><br>Digitale: <%=i.getQtaDig() %></td>
				<td><%=i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig() %></td>
				<%tot+=i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig();%>
			</tr>
					
		<% } %>
		
		<tr><td colspan=5 align=right><%=String.format("%.2f", tot) %></td></tr>
		
		<%} else { %>
		
		<tr>
			<td colspan=10 align=center>Checkout non disponibile</td>
		</tr>
		
		<%} %>
		
		<a href="Catalogo">Torna al catalogo</a>
		
	</table>
	<%@include file="Footer.jsp" %>
</body>
</html>