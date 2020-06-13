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
<link rel="stylesheet" href="css/catalogo.css">
</head>
<body>
	<%@include file="Header.jsp" %>
	<h1>Catalogo</h1>
	
	<form action="" method="get">
		<label>Ordina per: </label>
		
		<div class="custom-select">
		<select id="sort" name="sort">
  			<option value="id">Codice</option>
  			<option value="titolo">Titolo</option>
  			<option value="prezzoFisico">Prezzo</option>
		</select>
		</div>
		
		<input type="submit" value="Ordina" class="button" id="sort-submit">
	</form>
	
	<div class="table">
	<table border=1>
		<!-- 
		<tr>
		
			<th>Copertina</th>
			<th><a href="Catalogo?sort=id">Codice</a></th>
			<th><a href="Catalogo?sort=titolo">Titolo</a></th>
			<th><a href="Catalogo?sort=prezzoFisico">Prezzo</a></th>
			<th>Azione</th>
		
		</tr>
		 -->
		
		<% if(catalogo != null && catalogo.size() != 0){
			
			for(ProdottoBean p : catalogo){
				
		%>	
		<tr id="data">
			
			<td> <img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'" style="width:100px"></td>
			
			<td> <%=p.getId()  %> </td>
			
			<td> <%=p.getTitolo()  %> </td>
			
			<td> <%=p.getPrezzoFis()  %> </td>	
			
			<td> <a class="button" href="Catalogo?action=addC&tipo=fisico&id=<%=p.getId() %>">Aggiungi al carrello (Fisico)</a><br>
			<a class="button" href="Catalogo?action=addC&tipo=digitale&id=<%=p.getId() %>">Aggiungi al carrello (Digitale)</a><br>
			<a class="button" href="Catalogo?action=details&id=<%=p.getId() %>">Dettagli</a></td>			
			
		</tr>
				
		<%	}
			
		} else { %>
			
			<tr>
				<td colspan=5>Nessun prodotto disponibile</td>
			</tr>
			
		
		<% } %>
	
	</table>
	</div>
	<a href="CarrelloServlet">Vai al carrello</a>
<%@include file="Footer.jsp" %>
</body>
</html>