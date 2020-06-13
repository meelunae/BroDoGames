<%@page import="brodo.model.Ordine"%>
<%@page import="java.util.ArrayList"%>
<%@page import="brodo.model.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<% if(session.getAttribute("userId") == null){
	
		response.sendRedirect("Catalogo");
	
	}

	UserBean user = (UserBean) request.getAttribute("utente");
	if(user.getId() != (int) session.getAttribute("userId")){
		
		response.sendRedirect("ProfiloUtente?id=" + session.getAttribute("userId"));
		
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
	<h1>I miei ordini</h1>
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