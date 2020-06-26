<%@page import="java.util.Locale"%>
<%@page import="brodo.model.Ordine"%>
<%@page import="brodo.model.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% if(session.getAttribute("admin") == null){
	
		response.sendRedirect("Catalogo");
		return;
	
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

</head>
<body>	
<%@include file="Header.jsp" %>
	
	<form action="" id="ordinaPer" method="get">
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

	<div class="custom-table" id="custom-table-admin">
		<table border=1>
		
			<tr class="headings">
				
				<th>Copertina</th>
				<th>Codice</th>
				<th>Titolo</th>
				<th>Prezzo fisico senza IVA</th>
				<th>Prezzo fisico con IVA</th>
				<th>Prezzo digitale senza IVA</th>
				<th>Prezzo digitale con IVA</th>
				<th>Azione</th>
			
			</tr>
			
			<% if(catalogo != null && catalogo.size() != 0){
				
				for(ProdottoBean p : catalogo){
					
			%>	
			
			<tr>
			
				<td><img src="GetPicture?id=<%=p.getId() %>" onerror="this.src='./imgs/nophoto.png'"></td>
				<td class="idAdm"> <%=p.getId()  %> </td>
				<td class="titoloAdm"> <%=p.getTitolo()  %> </td>
				<td class="noivaFAdm"> <%=String.format(Locale.CANADA, "%.2f", p.getPrezzoFisSenzaIva()) + " EUR"%></td>
				<td class="ivaFAdm"> <%=String.format(Locale.CANADA, "%.2f", p.getPrezzoFis())  + " EUR"%> </td>	
				<td class="noivaDAdm"> <%=String.format(Locale.CANADA, "%.2f", p.getPrezzoDigSenzaIva()) + " EUR"%></td>
				<td class="ivaDAdm"> <%=String.format(Locale.CANADA, "%.2f", p.getPrezzoDig()) + " EUR"%></td>
				<td> <a class="button" href="Amministratore?action=deleteProdotto&id=<%=p.getId() %>">Elimina</a><br>
				<a class="button" href="Amministratore?action=details&id=<%=p.getId() %>">Dettagli</a><br>
				<a class="button" href="Amministratore?action=scegliModifica&id=<%=p.getId() %>">Modifica</a></td>			
			
			</tr>
					
			<%	}
				
			} else { %>
			
				<tr>
					<td colspan=4>Nessun prodotto disponibile</td>
				</tr>
			
			<% } %>
		
		</table>
	</div>
	
	<h2 align="center">Aggiungi gioco</h2>
	<div class="login-form">
		<form action="Amministratore" method="get">
		
			<fieldset>
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
			<input type="text" name="console" placeholder="console" required>
			</fieldset>
			<input class="button" type="submit" value="Aggiungi">
				
		</form>	
	</div>
	
	<h2 align="center">Aggiungi immagine</h2>
	<div class="login-form">
			<form action="UploadPhoto" enctype=multipart/form-data method="post">
			<fieldset>
	
				<select name=id>
					
					<% for(ProdottoBean e : catalogo){ %>
						
						<option value= <%=e.getId() %>><%=e.getId() %></option>
							
						
					<% } %>
					
				</select>
				<input class="file" type="file" name="cover" value="" maxlength="255">
			</fieldset>
			<input class="button" type="submit">
		
		</form>
	</div>
	
	
	<%if(mod != null){ %>
	
	<h2>Modifica</h2>
		
	<div class="login-form">
		<form action="Amministratore" method="get">
			<fieldset>
				<input type="hidden" name="action" value="edit" >
				<input type="hidden" name="id" value=<%=mod.getId() %>>
				<input type="text" name="titolo" placeholder="titolo" value="<%=mod.getTitolo() %>" required>
				<input type="number" name="prezzoFisico" placeholder="prezzo fisico" value=<%=mod.getPrezzoFisSenzaIva() %> min=0 step=0.01 required>
				<input type="number" name="prezzoDigitale" placeholder="prezzo digitale" value=<%=mod.getPrezzoDigSenzaIva() %> min=0 step=0.01 required>
				<input type="text" name="descrizione" placeholder="descrizione" value="<%= mod.getDescrizione() %>" required>
				<input type="number" name="qtaFisico" placeholder="quantita fisico" value=<%= mod.getQtaFis() %> step=1 min=0 required>
				<input type="number" name="qtaDigitale" placeholder="quantita digitale" value=<%= mod.getQtaDig() %> step=1 min=0 required>
				<input type="text" name="casaSviluppatrice" placeholder="casa sviluppatrice" value="<%= mod.getCasaSviluppatrice() %>" required>
				<input type="text" name="console" placeholder="console" value="<%=mod.getConsole() %>" required>
				<input type="submit">
			</fieldset>
		</form>
	</div>
	
	<% } %>
	
	<h2>Ordini</h2>
	<form action="Amministratore">
		<h3>Cerca per data:</h3>
		<input type="radio" name="scelta" value="data">
		Prima data: <input type="date" name="data1">
		Seconda data: <input type="date" name="data2"><br>
		<h3>Cerca per id utente:</h3>
		<input type="radio" name="scelta" value="id">
		<input type="number" name="id" placeholder="id utente"><br><br>
		<input type="radio" name="scelta" value="niente" checked>Visualizza tutto<br>
		<input class="button" type="submit" value="Cerca">
	</form>
	
	<div class="custom-table" id="custom-table-orders">
	<table border=1>
	
		<tr>
			<th>Id Ord</th>
			<th>Id Utente</th>
			<th>Id Prod</th>
			<th>Data</th>
			<th>Cons.</th>		
			<th>P.zzo Fis Un</th>
			<th>P.zzo Dig Un</th>
			<th>Iva</th>
			<th>Qta Fis</th>
			<th>Qta Dig</th>
			<th>Tot Parz</th>
			<th>Tot Ord</th>
												
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
				
				<td><%=String.format(Locale.CANADA, "%.2f", parzialeDaAgg) + " EUR"%></td>
				<%parzialeIVA=0;
				parzialeDaAgg=0;
				id=o.getIdOrdine();%>
				
			
			<% } %>
			</tr>
		
		<tr class="daRidurre">
		
			<td> <%=o.getIdOrdine()  %> </td>
			<td> <%=o.getIdUtente()  %> </td>
			<td> <a class="prodL" href="Catalogo?action=details&id=<%=o.getIdProdotto()  %>"><%=o.getIdProdotto()%></a> </td>	
			<td> <%=o.getDataOra()%></td>
			<td> <%=o.isConsegnato() %></td>
			<%if(o.getQtaFisico() == 0){ %>
				<td>0</td>
			<%}else{ %>
			<td> <%=String.format(Locale.CANADA, "%.2f", o.getPrezzoFis()) + " EUR"%></td>
			<%} %>
			<%if(o.getQtaDigitale() == 0){ %>
				<td>0</td>
			<%}else{ %>
			<td> <%=String.format(Locale.CANADA, "%.2f", o.getPrezzoDig()) + " EUR"%></td>
			<%} %>
			<td> <%=o.getIva() %></td>
			<td> <%=o.getQtaFisico() %></td>
			<td> <%=o.getQtaDigitale() %></td>
			<% double parziale = (o.getQtaDigitale() * o.getPrezzoDig())+(o.getQtaFisico()*o.getPrezzoFis()); 
			   parzialeIVA = parziale + parziale * o.getIva();
			   parzialeDaAgg += parzialeIVA; %>
			   
				<td> <%=String.format(Locale.CANADA, "%.2f", parzialeIVA) + " EUR"%></td>
			 <% if(ordini.get(ordini.size()-1) == o){ %>
				   
				   <td><%=String.format(Locale.CANADA, "%.2f", parzialeDaAgg) + " EUR"%></td>
				   
			<%  } %>
				
		<%	}
			
		} else { %>
		
			<tr>
				<td colspan=12>Nessun ordine trovato</td>
			</tr>
		
		<% } %>
	
	</table>
	</div>
	<script src="jquery.js"></script>
	<script src="script.js"></script>
<%@include file="Footer.jsp" %>
</body>
</html>