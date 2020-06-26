<%@page import="java.util.Locale"%>
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
	<div class="details">
		<img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'">
			<div class="details-p">
			<p><%=p.getTitolo() %></p>
			<p><%=p.getCasaSviluppatrice() %></p>
			<p>PEGI <%=p.getPegi() %>
			<p>Descrizione: <%=p.getDescrizione() %></p>
			<%if(p.isInVendita()){ %>
			<%double dig = p.getPrezzoDig(); %>
			<p>Prezzo Digitale: <%=String.format(Locale.CANADA, "%.2f", dig) + " EUR"%></p>
			<%double fis = p.getPrezzoFis(); %>
			<p>Prezzo Fisico: <%=String.format(Locale.CANADA, "%.2f", fis) + " EUR"%></p>
			<a class="button" onclick="event.preventDefault(); aggiungiCarrello(<%="'fis'"%>, <%=p.getId() %>)">Aggiungi al carrello (Fisico)</a><br>			
			<a class="button" onclick="event.preventDefault(); aggiungiCarrello(<%="'dig'"%>, <%=p.getId()%>)">Aggiungi al carrello (Digitale)</a><br>
			<form action=Wishlist>
					<input type="hidden" value="add" name="action">
			<input type=hidden value=<%=p.getId() %> name="idProdotto">
			<input type=hidden value=<%=session.getAttribute("userId")%> name="idUtente">
			<div id=<%=p.getId() %>></div>
			<button class="button">Aggiungi alla Wishlist</button>
			<%} else{ %>
			
			<div>Prodotto non più in vendita :(</div>
			
			<%} %>
				
		</form>
		
		</div>
		
	</div>
	
<%@include file="Footer.jsp" %>
<script src="jquery.js"></script>
<script src="script.js"></script>
</body>
</html>