package brodo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CheckoutDAO {

	static {
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("dbrodo");
			
		}catch(NamingException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public static int getLastId() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query="SELECT idOrdine FROM ordine ORDER BY idOrdine DESC";
		int output = 0;
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				output = rs.getInt("idOrdine");
				
			} else {
				
				output = 0;
				
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
		
		return output;
		
	}
	
	public boolean doSave(Carrello c, int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		int lastId = getLastId() + 1;
		String query="INSERT INTO ordine (idOrdine, idUtente, idProdotto, quantitaFisico, quantitaDigitale, dataOra, prezzo, consegnato, iva) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			for(ItemCarrello i : c.getProdotti()) {
				
				ps = conn.prepareStatement(query);
				ps.setInt(1, lastId);
				ps.setInt(2, id);
				ps.setInt(3, i.getProdotto().getId());
				ps.setInt(4, i.getQtaFis());
				ps.setInt(5, i.getQtaDig());
				GregorianCalendar gc = new GregorianCalendar();
				int yy = gc.get(GregorianCalendar.YEAR);
				int mm = gc.get(GregorianCalendar.MONTH)+1; 
				int dd = gc.get(GregorianCalendar.DAY_OF_MONTH);
				ps.setString(6, yy+"-"+mm+"-"+dd);
				ps.setDouble(7, i.getProdotto().getPrezzoFisSenzaIva() * i.getQtaFis() + i.getProdotto().getPrezzoDigSenzaIva() * i.getQtaDig());
				ps.setBoolean(8, false);
				ps.setDouble(9, i.getProdotto().getIva());
				ps.executeUpdate();
				
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			return false;
			
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
		
		return true;
		
	}
	
	private static DataSource ds;
	
}
