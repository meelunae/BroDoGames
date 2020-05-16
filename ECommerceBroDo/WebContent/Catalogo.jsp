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
	<h1>Catalogo</h1>
	<table border=1>
	
		<tr>
		
			<th>Copertina</th>
			<th>Codice<a href="Catalogo?sort=id">Ordina</a></th>
			<th>Titolo<a href="Catalogo?sort=titolo">Ordina</a></th>
			<th>Prezzo<a href="Catalogo?sort=prezzoFisico">Ordina</a></th>
			<th>Azione</th>
		
		</tr>
		
		<% if(catalogo != null && catalogo.size() != 0){
			
			for(ProdottoBean p : catalogo){
				
		%>	
		
		<tr>
		
			<td> <img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'" style="width:100px"></td>
			<td> <%=p.getId()  %> </td>
			<td> <%=p.getTitolo()  %> </td>
			<td> <%=p.getPrezzoFis()  %> </td>	
			<td> <a href="Catalogo?action=addC&tipo=fisico&id=<%=p.getId() %>">Aggiungi al carrello (Fisico)</a><br>
			<a href="Catalogo?action=addC&tipo=digitale&id=<%=p.getId() %>">Aggiungi al carrello (Digitale)</a><br>
			<a href="Catalogo?action=details&id=<%=p.getId() %>">Dettagli</a></td>			
		
		</tr>
				
		<%	}
			
		} else { %>
		
			<tr>
				<td colspan=5>Nessun prodotto disponibile</td>
			</tr>
		
		<% } %>
	
	</table>
	
	<a href="CarrelloServlet">Vai al carrello</a>
<%@include file="Footer.jsp" %>
</body>
</html>