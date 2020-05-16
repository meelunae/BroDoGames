<%@page import="brodo.model.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% if(session.getAttribute("admin") == null){
	
		response.sendRedirect("Catalogo");
// 		RequestDispatcher view = request.getRequestDispatcher("/Catalogo");
// 		view.forward(request, response);
	
	}

	ArrayList<ProdottoBean> catalogo = (ArrayList<ProdottoBean>) request.getAttribute("catalogo"); 
	if(catalogo == null){
		
		response.sendRedirect("./Amministratore");
		return;
		
	}
	
	ProdottoBean mod = (ProdottoBean) request.getAttribute("prodDaMod");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pannello di amministrazione</title>
</head>
<body>	
<%@include file="Header.jsp" %>
	<h1>Catalogo</h1>
	<table border=1>
	
		<tr>
			
			<th>Copertina</th>
			<th>Codice<a href="Amministratore?sort=id">Ordina</a></th>
			<th>Titolo<a href="Amministratore?sort=titolo">Ordina</a></th>
			<th>Prezzo con IVA<a href="Amministratore?sort=prezzoFisico">Ordina</a></th>
			<th>Prezzo senza IVA</th>
			<th>Azione</th>
		
		</tr>
		
		<% if(catalogo != null && catalogo.size() != 0){
			
			for(ProdottoBean p : catalogo){
				
		%>	
		
		<tr>
		
			<td><img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'" style="width:100px"></td>
			<td> <%=p.getId()  %> </td>
			<td> <%=p.getTitolo()  %> </td>
			<td> <%=p.getPrezzoFis()  %> </td>	
			<td> <%=p.getPrezzoFisSenzaIva() %></td>
			<td> <a href="Amministratore?action=deleteProdotto&id=<%=p.getId() %>">Elimina</a><br>
			<a href="Amministratore?action=details&id=<%=p.getId() %>">Dettagli</a><br>
			<a href="Amministratore?action=scegliModifica&id=<%=p.getId() %>">Modifica</a></td>			
		
		</tr>
				
		<%	}
			
		} else { %>
		
			<tr>
				<td colspan=4>Nessun prodotto disponibile</td>
			</tr>
		
		<% } %>
	
	</table>
	
	<h2>Aggiungi gioco</h2>
	<form action="Amministratore" method="get">
	
		<input type="hidden" name="action" value="addProdotto">
		<input type="text" name="titolo" placeholder="titolo">
		<input type="number" name="prezzoFisico" placeholder="prezzo fisico" step=0.01>
		<input type="number" name="prezzoDigitale" placeholder="prezzo digitale" step=0.01>
		<input type="text" name="descrizione" placeholder="descrizione">
		<input type="number" name="qtaFisico" placeholder="quantita fisico" step=1>
		<input type="number" name="qtaDigitale" placeholder="quantita digitale" step=1>
		<input type="text" name="casaSviluppatrice" placeholder="casa sviluppatrice">
		<input type="number" name="pegi" placeholder="pegi" step=1>
		<input type="date" name="dataUscita" placeholder="data di uscita">
		<input type="submit" value="Conferma">
			
	</form>	
	
	<h2>Aggiungi immagine</h2>
	<form action="UploadPhoto" enctype=multipart/form-data method="post">
		
		<select name=id>
			
			<% for(ProdottoBean e : catalogo){ %>
				
				<option value= <%=e.getId() %>><%=e.getId() %></option>
					
				
			<% } %>
			
		</select>
		<input class="file" type="file" name="cover" value="" maxlength="255">
		<input type="submit">
	
	</form>
	
	
	<%if(mod != null){ %>
	
	<h2>Modifica</h2>
	<form action="Amministratore" method="get">
	
		<input type="hidden" name="action" value="edit" >
		<input type="hidden" name="id" value=<%=mod.getId() %>>
		<input type="text" name="titolo" placeholder="titolo" value="<%=mod.getTitolo() %>">
		<input type="number" name="prezzoFisico" placeholder="prezzo fisico" value=<%=mod.getPrezzoFis() %> step=0.01>
		<input type="number" name="prezzoDigitale" placeholder="prezzo digitale" value=<%=mod.getPrezzoDig() %> step=0.01>
		<input type="text" name="descrizione" placeholder="descrizione" value="<%= mod.getDescrizione() %>">
		<input type="number" name="qtaFisico" placeholder="quantita fisico" value=<%= mod.getQtaFis() %> step=1>
		<input type="number" name="qtaDigitale" placeholder="quantita digitale" value=<%= mod.getQtaDig() %> step=1>
		<input type="text" name="casaSviluppatrice" placeholder="casa sviluppatrice" value="<%= mod.getCasaSviluppatrice() %>">
		<input type="number" name="pegi" placeholder="pegi" value=<%= mod.getPegi() %> step=1>
		<input type="date" name="dataUscita" placeholder="data di uscita" value=<%= mod.getData() %>>
		<input type="submit">
			
	</form>
	
	<% } %>
	
<%@include file="Footer.jsp" %>
</body>
</html>