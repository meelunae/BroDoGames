<%@page import="java.util.Locale"%>
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
	<h2 style="margin-left: 1%">Il mio carrello</h2>
	<div class="custom-table">
	<table border=1 id="tabella">
		
		<% 
		int count = 0;
		if(c != null && c.getProdotti().size() != 0){
			ArrayList<ItemCarrello> it = c.getProdotti();
			for(ItemCarrello i : it){ %>
			
			<tr class="data" id="row<%=count %>">
				<td class="immagineCart"><img src="GetPicture?id=<%=i.getProdotto().getId() %>" onerror="this.src='./imgs/nophoto.png'"></td>
				<td class="titoloProd"><%=i.getProdotto().getTitolo() %><br><span style="font-size: 12px"><%="(" + i.getProdotto().getConsole() + ")"%></span></td>
				<td class="prezzoProdCart">Fisico: <span id="priceF<%=count%>">
				<% double fis = i.getProdotto().getPrezzoFis(); %>
				<%=(String.format(Locale.CANADA, "%.2f", fis)) + " EUR"%></span><br>
				Digitale: <span id="priceD<%=count%>">
				<% double dig = i.getProdotto().getPrezzoDig(); %>
				<%=(String.format(Locale.CANADA, "%.2f", dig)) + " EUR"%>
				</span></td>
				<td class="qtaProd">
				<form onsubmit="event.preventDefault(); setQta(this, <%=count %>);">
					<input type=number value="<%=i.getQtaFis() %>" name=newQtaFis id="fis<%=count %>"><br>
					<span id="errFis<%=count %>"></span><br>
					<input type=number value="<%=i.getQtaDig() %>" name=newQtaDig id="dig<%=count %>"><br>
					<span id="errDig<%=count %>"></span><br>
					<input type="hidden" name="action" value="updateQta">
					<input type="hidden" name="id" value=<%=i.getProdotto().getId() %> id="id<%=count%>">
					<input type="submit" class="button" value="Aggiorna qta">
				</form>
				</td>
				<td class="delProd">
				<form class="deleteCart" onsubmit="event.preventDefault(); remove(this, <%=count %>);">
					<input type="submit" class="button" value="Elimina">
				</form>
				</td>
				<td class="totCart" id="totParziale<%=count %>">		
				<% double costo = (i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig()); %>
				<%=String.format(Locale.CANADA, "%.2f", costo) + " EUR"%>
				<%tot+=i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig();%>
			</tr>
					
		<% count = count + 1; } %>
		
		<tr id="total" align=right id="tot<%=count %>"><td colspan=6 align=right id="totd">
		Totale Ordine: <%=String.format(Locale.CANADA, "%.2f", tot) + " EUR"%>
		</td></tr>
		
		<%} else { %>
		
		<tr>
			<td colspan=10 align=center>Nessun prodotto nel carrello</td>
		</tr>
		
		<%} %>
		
	</table>
	</div>
	<p hidden=hidden id="counter"><%=count %></p>
	<% if(c != null && c.getProdotti().size() != 0){ %>
	
		<form action="Carrello" method=post>
			<input type="hidden" name="action" value="checkout">
			<div align="center"><input type="submit" value="Procedi al checkout" class="button" id="checkout-submit"></div>
		</form>
	
	<%} %>
	
	
<%@include file="Footer.jsp" %>
<script src="jquery.js"></script>
<script src="script.js"></script>
</body>
</html>