<%@page import="brodo.model.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   	
<% ArrayList<ProdottoBean> catalogo = (ArrayList<ProdottoBean>) request.getAttribute("catalogo"); 
	if(catalogo == null){
		
		response.sendRedirect("./Catalogo");
		return;
		
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogo</title>
</head>
<body>
	<%@include file="Header.jsp" %>
	
	<form action="" method="get" id="ordinaPer">
		<label>Ordina per: </label>
		
		<div class="custom-select">
		<select id="sort" name="sort">
  			<option value="id">Default</option>
  			<option value="titolo">Titolo</option>
  			<option value="prezzoFisico">Prezzo</option>
		</select>
		</div>
		
		<input type="submit" value="Ordina" class="button" id="sort-submit">
	</form>
	
	<div class="custom-table">
	<table border=1>
		
		<% if(catalogo != null && catalogo.size() != 0){

			for(ProdottoBean p : catalogo){
				
		%>	
		<tr class="data">
			
			<td class="immagine"> <img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'"></td>
			
			
			<td class="titoloProd"> <%=p.getTitolo()%><br><span style="font-size: 12px"><%="(" + p.getConsole() + ")"%></span> </td>
			
			<td class="prezzoProd"> 
			<%double fis = p.getPrezzoFis(); %>
			<%=String.format("%.2f", fis) + " EUR" %>

			</td>
			<td class="bottoni"> <a class="button" onclick="event.preventDefault(); aggiungiCarrello(<%="'fis'"%>, <%=p.getId() %>)">Aggiungi al carrello (Fisico)</a><br>			
			<a class="button" onclick="event.preventDefault(); aggiungiCarrello(<%="'dig'"%>, <%=p.getId()%>)">Aggiungi al carrello (Digitale)</a><br>
			<a class="button" href="Catalogo?action=details&id=<%=p.getId() %>">Dettagli</a>
			<div id=<%=p.getId() %>></div>		<!-- Serve per comunicare l'esito dell'aggiunta al carrello -->
			</td>			
			
		</tr>
				
		<%	}
			
		} else { %>
			
			<tr>
				<td colspan=5>Nessun prodotto disponibile</td>
			</tr>
			
		
		<% } %>
	
	</table>
	</div>
<%@include file="Footer.jsp" %>
<script src="jquery.js"></script>
<script src="script.js"></script>
</body>
</html>