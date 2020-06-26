<%@page import="java.util.Locale"%>
<%@page import="brodo.model.Ordine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="brodo.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% if(session.getAttribute("userId") == null){
	
		response.sendRedirect("Catalogo");
		return;
	
	}

	UserBean user = (UserBean) request.getAttribute("utente");
	if(user == null || user.getId() != (int) session.getAttribute("userId")){

		response.sendRedirect("ProfiloUtente?id=" + session.getAttribute("userId"));
		return;
		
	}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo di <%=user.getUsername()%></title>
</head>
<body>
	<%@include file="Header.jsp" %>
	<% ArrayList<Ordine> ordini = (ArrayList<Ordine>) request.getAttribute("ordini"); %>
	<div class="details" align="center">
		<div class="details-p" id="scheda">
			<p>Nome utente: <%=user.getUsername()%></p>
			<p>Nome: <%=user.getNome()%></p>
			<p>Cognome: <%=user.getCognome()%></p>
			<p>Data di nascita: <%=user.getDataNascita()%></p>
			<p>Email: <%=user.getEmail()%></p>
			<%if(user.getCitta() != null && !user.getCitta().equals("")){ %>
			<p>Citta: <%=user.getCitta() %></p>
			<%} %>
			<%if(user.getVia() != null && !user.getVia().equals("")){ %>
			<p>Via: <%=user.getVia() %></p>
			<%} %>
			<%if(user.getNumCivico() != 0){ %>
			<p>Numero Civico: <%=user.getNumCivico() %></p>
			<%} %>
			<%if(user.getCap() != 0){ %>
			<p>CAP: <%=user.getCap() %></p>
		<%} %>
		</div>
	</div>
	<h1>I miei ordini</h1>
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
					
					<td><%=String.format(Locale.CANADA, "%.2f", parzialeDaAgg) + " EUR" %></td>
					<%parzialeIVA=0;
					parzialeDaAgg=0;
					id=o.getIdOrdine();%>
					
				
				<% } %>
				</tr>
			
			<tr class="daRidurre">
			
				<td> <%=o.getIdOrdine()  %> </td>
				<td> <%=o.getIdUtente()  %> </td>
				<td> <a class="prodL" href="Catalogo?action=details&id=<%=o.getIdProdotto()  %>"><%=o.getIdProdotto()%></a>  </td>	
				<td> <%=o.getDataOra()%></td>
				<td> <%=o.isConsegnato() %></td>
				<%if(o.getQtaFisico() == 0){ %>
					<td>0 EUR</td>
				<%}else{ %>
				<td> <%=String.format(Locale.CANADA, "%.2f", o.getPrezzoFis()) + " EUR"%></td>
				<%} %>
				<%if(o.getQtaDigitale() == 0){ %>
					<td>0 EUR</td>
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
				
			
			</tr>
		
		</table>
	</div>

	<%@include file="Footer.jsp" %>
	
</body>
</html>