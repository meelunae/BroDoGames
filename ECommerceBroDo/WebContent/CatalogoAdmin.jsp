<%@page import="brodo.model.Ordine"%>
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
	ArrayList<Ordine> ordini = (ArrayList<Ordine>) request.getAttribute("ordini");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pannello di amministrazione</title>
<!-- <link href="css/catalogo.css" rel="stylesheet">-->
</head>
<body>	
<%@include file="Header.jsp" %>
	<h1>Catalogo</h1>
	<table border=1>
	
		<tr>
			
			<th>Copertina</th>
			<th>Codice<a href="Amministratore?sort=id">Ordina</a></th>
			<th>Titolo<a href="Amministratore?sort=titolo">Ordina</a></th>
			<th>Prezzo fisico senza IVA</th>
			<th>Prezzo fisico con IVA<a href="Amministratore?sort=prezzoFisico">Ordina</a></th>
			<th>Prezzo digitale senza IVA</th>
			<th>Prezzo digitale con IVA<a href="Amministratore?sort=prezzoFisico">Ordina</a></th>
			<th>Azione</th>
		
		</tr>
		
		<% if(catalogo != null && catalogo.size() != 0){
			
			for(ProdottoBean p : catalogo){
				
		%>	
		
		<tr>
		
			<td><img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'" style="width:100px"></td>
			<td> <%=p.getId()  %> </td>
			<td> <%=p.getTitolo()  %> </td>
			<td> <%=p.getPrezzoFisSenzaIva() %></td>
			<td> <%=p.getPrezzoFis()  %> </td>	
			<td> <%=p.getPrezzoDigSenzaIva() %></td>
			<td> <%=p.getPrezzoDig() %></td>
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
		<input type="text" name="titolo" placeholder="titolo" required>
		<input type="number" name="prezzoFisico" placeholder="prezzo fisico" step=0.01 min=0 required>
		<input type="number" name="prezzoDigitale" placeholder="prezzo digitale" step=0.01 min=0 required>
		<input type="text" name="descrizione" placeholder="descrizione" required>
		<input type="number" name="qtaFisico" placeholder="quantita fisico" step=1 min=0 required>
		<input type="number" name="qtaDigitale" placeholder="quantita digitale" step=1 min=0 required>
		<input type="text" name="casaSviluppatrice" placeholder="casa sviluppatrice" required>
		<input type="number" name="pegi" placeholder="pegi" step=1 min=0 required>
		<input type="date" name="dataUscita" placeholder="data di uscita" required>
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
		<input type="text" name="titolo" placeholder="titolo" value="<%=mod.getTitolo() %>" required>
		<input type="number" name="prezzoFisico" placeholder="prezzo fisico" value=<%=mod.getPrezzoFisSenzaIva() %> min=0 step=0.01 required>
		<input type="number" name="prezzoDigitale" placeholder="prezzo digitale" value=<%=mod.getPrezzoDigSenzaIva() %> min=0 step=0.01 required>
		<input type="text" name="descrizione" placeholder="descrizione" value="<%= mod.getDescrizione() %>" required>
		<input type="number" name="qtaFisico" placeholder="quantita fisico" value=<%= mod.getQtaFis() %> step=1 min=0 required>
		<input type="number" name="qtaDigitale" placeholder="quantita digitale" value=<%= mod.getQtaDig() %> step=1 min=0 required>
		<input type="text" name="casaSviluppatrice" placeholder="casa sviluppatrice" value="<%= mod.getCasaSviluppatrice() %>" required>
		<input type="number" name="pegi" placeholder="pegi" value=<%= mod.getPegi() %> step=1 min=3 required>
		<input type="date" name="dataUscita" placeholder="data di uscita" value=<%= mod.getData() %> required>
		<input type="text" name="console" placeholder="console" value="<%=mod.getConsole() %>" required>
		<input type="submit">
			
	</form>
	
	<% } %>
	
	<h2>Ordini</h2>
	<form action="Amministratore">
		<input type="radio" name="scelta" value="data">
		Prima data: <input type="date" name="data1">
		Seconda data: <input type="date" name="data2"><br>
		<input type="radio" name="scelta" value="id">
		<input type="number" name="id" placeholder="id utente"><br>
		<input type="radio" name="scelta" value="niente" checked>Visualizza tutto
		<input type="submit" value="Cerca">
	</form>
	<table border=1>
	
		<tr>
			<th>Id Ordine</th>
			<th>Id Utente</th>
			<th>Id Prodotto</th>
			<th>Data</th>
			<th>Consegnato</th>		
			<th>Prezzo Fisico Unitario</th>
			<th>Prezzo Digitale Unitario</th>
			<th>Iva</th>
			<th>Quantita Fisica</th>
			<th>Quantita Digitale</th>
			<th>Totale Parziale</th>
			<th>Totale Ordine</th>
												
		</tr>
		
		<tr>
		
		<%int idOrdine; %>
		
		<% if(ordini != null && ordini.size() != 0){
			
			double parzialeIVA = 0;
			double parzialeDaAgg = 0;
			int id = ordini.get(0).getIdOrdine();
			
		for(Ordine o : ordini){
				
		%>	
		
			<%if(o.getIdOrdine() != id){ %> 
				
				<td><%=parzialeDaAgg %></td>
				<%parzialeIVA=0;
				parzialeDaAgg=0;
				id=o.getIdOrdine();%>
				
			
			<% } %>
			</tr>
		
		<tr>
		
			<td> <%=o.getIdOrdine()  %> </td>
			<td> <%=o.getIdUtente()  %> </td>
			<td> <%=o.getIdProdotto()  %> </td>	
			<td> <%=o.getDataOra()%></td>
			<td> <%=o.isConsegnato() %></td>
			<%if(o.getQtaFisico() == 0){ %>
				<td>0</td>
				<%System.out.println("Qta: " + o.getQtaFisico()); %>
			<%}else{ %>
				<%System.out.println(o.getQtaFisico()); %>
			<td> <%=o.getPrezzoFis() %></td>
			<%} %>
			<%if(o.getQtaDigitale() == 0){ %>
				<td>0</td>
			<%}else{ %>
			<td> <%=o.getPrezzoDig() %></td>
			<%} %>
			<td> <%=o.getIva() %></td>
			<td> <%=o.getQtaFisico() %></td>
			<td> <%=o.getQtaDigitale() %></td>
			<% double parziale = (o.getQtaDigitale() * o.getPrezzoDig())+(o.getQtaFisico()*o.getPrezzoFis()); 
			   parzialeIVA = parziale + parziale * o.getIva();
			   parzialeDaAgg += parzialeIVA; %>
			   
				<td> <%=parzialeIVA%></td>
			 <% if(ordini.get(ordini.size()-1) == o){ %>
				   
				   <td><%=parzialeDaAgg %></td>
				   
			<%  } %>
				
		<%	}
			
		} else { %>
		
			<tr>
				<td colspan=4>Nessun ordine trovato</td>
			</tr>
		
		<% } %>
			
		
		</tr>
	
	</table>
	
<%@include file="Footer.jsp" %>
</body>
</html>