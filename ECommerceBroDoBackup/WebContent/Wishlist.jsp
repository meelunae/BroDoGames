<%@page import="java.util.Locale"%>
<%@page import="brodo.model.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getAttribute("prodotti"); 
	if(prodotti == null){
		
		response.sendRedirect("./Wishlist");
		return;
		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La mia Wishlist</title>
</head>
<body>
	<%@include file="Header.jsp" %>
	<h2 style="margin-left: 1%">La mia wishlist</h2>
	<div class="custom-table">
	<table border=1 id="tabella">
	
	<%if(prodotti != null && prodotti.size() != 0){ 
		int count = 0;
		for(ProdottoBean i : prodotti){%>
			<tr id=<%=count %> class="data">
				<td class="immagineCart"><img src="GetPicture?id=<%=i.getId() %>" onerror="this.src='./imgs/nophoto.png'" style="width:100px"></td>
				<td class="titoloProd"><%=i.getTitolo() %></td>
				<td class="prezzoProdCart">Fisico:
				<% double fis = i.getPrezzoFis(); %>
				<%=(String.format(Locale.CANADA, "%.2f", fis)) + " EUR"%><br>
				Digitale:
				<% double dig = i.getPrezzoDig(); %>
				<%=(String.format(Locale.CANADA, "%.2f", dig)) + " EUR"%>
				</td>
				<td class="bottoni">
				<form action="Wishlist" method="get">
					<input type="hidden" value=<%=i.getId() %> name="idProdotto">
					<input type="hidden" value=<%=session.getAttribute("userId") %> name="idUtente">
					<input type="hidden" value="del" name="action">
					<input type="submit" class="button" value="Elimina">
				</form>
				</td>
			</tr>
				
	<%count++;} %>
			
	
	<%} else {%>
	
		<tr><td>Nessun prodotto nella Wishlist</td></tr>
	
	<%} %>
	
	</table>
	</div>
	
<%@include file="Footer.jsp" %>
<script src="jquery.js"></script>
<script src="script.js"></script>
</body>
</html>