package brodo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDAO {
	
	static {
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("dbrodo");
			
		}catch(NamingException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public synchronized ArrayList<Ordine> doRetrieveAll() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM ordine";
		ArrayList<Ordine> result = new ArrayList<Ordine>();
		
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Ordine ord = new Ordine();
				ord.setConsegnato(rs.getBoolean("consegnato"));
				ord.setDataOra(rs.getString("dataOra"));
				ord.setIdOrdine(rs.getInt("idOrdine"));
				ord.setIdProdotto(rs.getInt("idProdotto"));
				ord.setPrezzoFis(rs.getDouble("prezzoFis"));
				ord.setPrezzoDig(rs.getDouble("prezzoDig"));
				ord.setIva(rs.getDouble("iva"));
				ord.setQtaDigitale(rs.getInt("quantitaDigitale"));
				ord.setQtaFisico(rs.getInt("quantitaFisico"));
				ord.setIdUtente(rs.getInt("idUtente"));
				result.add(ord);
				
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			conn.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			ps.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	public synchronized ArrayList<Ordine> doRetrieveByUser(int id){
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM ordine WHERE idUtente = ?";
		ArrayList<Ordine> result = new ArrayList<Ordine>();
		
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Ordine ord = new Ordine();
				ord.setConsegnato(rs.getBoolean("consegnato"));
				ord.setDataOra(rs.getString("dataOra"));
				ord.setIdOrdine(rs.getInt("idOrdine"));
				ord.setIdProdotto(rs.getInt("idProdotto"));
				ord.setPrezzoFis(rs.getDouble("prezzoFis"));
				ord.setPrezzoDig(rs.getDouble("prezzoDig"));
				ord.setIva(rs.getDouble("iva"));
				ord.setQtaDigitale(rs.getInt("quantitaDigitale"));
				ord.setQtaFisico(rs.getInt("quantitaFisico"));
				ord.setIdUtente(rs.getInt("idUtente"));
				result.add(ord);
				
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			conn.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			ps.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	public synchronized ArrayList<Ordine> doRetrieveByDate(String data1, String data2){
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM ordine WHERE dataOra > ? AND dataOra < ?";
		ArrayList<Ordine> result = new ArrayList<Ordine>();
		
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, data1);
			ps.setString(2, data2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Ordine ord = new Ordine();
				ord.setConsegnato(rs.getBoolean("consegnato"));
				ord.setDataOra(rs.getString("dataOra"));
				ord.setIdOrdine(rs.getInt("idOrdine"));
				ord.setIdProdotto(rs.getInt("idProdotto"));
				ord.setPrezzoFis(rs.getDouble("prezzoFis"));
				ord.setPrezzoDig(rs.getDouble("prezzoDig"));
				ord.setIva(rs.getDouble("iva"));
				ord.setQtaDigitale(rs.getInt("quantitaDigitale"));
				ord.setQtaFisico(rs.getInt("quantitaFisico"));
				ord.setIdUtente(rs.getInt("idUtente"));
				result.add(ord);
				
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			conn.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			ps.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return result;
		
	}
	
	private static DataSource ds;

}
