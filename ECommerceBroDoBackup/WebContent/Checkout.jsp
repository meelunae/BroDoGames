<%@page import="java.util.Locale"%>
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
		<script>document.getElementById("qtaCart").innerHTML = "Carrello (0)"</script>
		<h2>Checkout eseguito con successo!</h2>
		<h3>Riepilogo:</h3>
		<div class="custom-table" id="custom-table-orders" id="custom-table-checkout">
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
	
				<tr class="daRidurre">
					<td><%=i.getProdotto().getId() %></td>
					<td><%=i.getProdotto().getTitolo() %></td>
					<td>Fisico: <%=i.getQtaFis() %><br>Digitale: <%=i.getQtaDig() %></td>
					<td><% String costo = "" + (i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig()) + "0"; %>
					<%=costo.substring(0, costo.indexOf(".")+3) + " EUR"%></td>
					<%tot+=i.getProdotto().getPrezzoFis() * i.getQtaFis() + i.getProdotto().getPrezzoDig() * i.getQtaDig();%>
				</tr>
						
			<% } %>
			
			<tr><td colspan=5 align=right><%=String.format(Locale.CANADA, "%.2f", tot) + " EUR" %></td></tr>
			
			<%} else { %>
			
			<tr>
				<td colspan=10 align=center>Checkout non disponibile</td>
			</tr>
			
			<%} %>
					
		</table>
	</div>
	<%@include file="Footer.jsp" %>
</body>
</html>