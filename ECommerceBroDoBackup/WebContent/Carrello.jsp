<%@page import="brodo.model.ItemCarrello"%>
<%@page import="java.util.ArrayList"%>
<%@page import="brodo.model.Carrello"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% double tot=0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>
<%@include file="Header.jsp" %>
	<% Carrello c = (Carrello) session.getAttribute("carrello"); 
	   
	%>
	
	<h1>Carrello</h1>
	
	<table border=1>
		<tr>
			
			<th>Copertina</th>
			<th>Codice</th>
			<th>Titolo</th>
			<th>Prezzo</th>
			<th>Quantita'</th>	
			<th>Totale</th>							
		
		</tr>
		
		<% 
		if(c != null && c.getProdotti().size() != 0){
			ArrayList<ItemCarrello> it = c.getProdotti();
			int count = 0;
			for(ItemCarrello i : it){ %>
			
			<tr>
				<td><img src="GetPicture?id=<%=i.getProdotto().getId() %>" onerror="this.src='./imgs/nophoto.png'" style="width:100px"></td>
				<td><%=i.getProdotto().getId() %></td>
				<td><%=i.getProdotto().getTitolo() %></td>
				<td>Fisico: <%=i.getProdotto().getPrezzoFis() %><br>
					Digitale: <%=i.getProdotto().getPrezzoDig() %></td>
				<td>
				<form onsubmit="event.preventDefault(); setQta(this, <%=count %>);">
					<input type=number value="<%=i.getQtaFis() %>" name=newQtaFis id="fis<%=count %>">
					<input type=number value="<%=i.getQtaDig() %>" name=newQtaDig id="dig<%=count %>">
					<input type="hidden" name="action" value="updateQta">
					<input type="hidden" name="id" value=<%=i.getProdotto().getId() %> id="id<%=count%>">
					<input type="submit">
				</form>
				</td>
				<td><%=i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig() %></td>
				<%tot+=i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig();%>
			</tr>
					
		<% count = count + 1; } %>
		
		<tr><td colspan=5 align=right><%=tot %></td></tr>
		
		<%} else { %>
		
		<tr>
			<td colspan=10 align=center>Nessun prodotto nel carrello</td>
		</tr>
		
		<%} %>
		
	</table>
	
	<% if(c != null && c.getProdotti().size() != 0){ %>
	
		<form action="CarrelloServlet" method=post>
			<input type="hidden" name="action" value="checkout">
			<input type="submit" value="Procedi al checkout">
		</form>
	
	<%} %>
	<a href="Catalogo">Torna al catalogo</a>
<%@include file="Footer.jsp" %>
<script src="jquery.js"></script>
<script src="script.js"></script>
</body>
</html>