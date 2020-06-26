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

public class WishlistDAO {

	static {
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("dbrodo");
			
		}catch(NamingException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public synchronized ArrayList<ProdottoBean> doRetrieveByUser(int id){
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "SELECT idProdotto, titolo, numVenduti, prezzoFisico, prezzoDigitale, descrizione, qtaFisico, qtaDigitale, casaSviluppatrice, inVendita, pegi, dataUscita, console FROM wishlist JOIN prodotto ON wishlist.idProdotto=prodotto.id WHERE idUtente = ?";
		ArrayList<ProdottoBean> output = new ArrayList<ProdottoBean>();
		
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				if(rs.getBoolean("inVendita")) {
					ProdottoBean result = new ProdottoBean();
					result.setId(rs.getInt("idProdotto"));
					result.setTitolo(rs.getString("titolo"));
					result.setnVenduti(rs.getInt("numVenduti"));
					result.setPrezzoFis(rs.getDouble("prezzoFisico"));
					result.setPrezzoDig(rs.getDouble("prezzoDigitale"));
					result.setDescrizione(rs.getString("descrizione"));
					result.setQtaFis(rs.getInt("qtaFisico"));
					result.setQtaDig(rs.getInt("qtaDigitale"));
					result.setCasaSviluppatrice(rs.getString("casaSviluppatrice"));
					result.setInVendita(rs.getBoolean("inVendita"));
					result.setPegi(rs.getInt("pegi"));
					result.setData(rs.getString("dataUscita"));
					result.setConsole(rs.getString("console"));
					output.add(result);
				}
				
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
	
	public void doRemove(int idUtente, int idProdotto) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "DELETE FROM wishlist WHERE idUtente = ? AND idProdotto = ?";

		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idUtente);
			ps.setInt(2, idProdotto);
			ps.executeUpdate();
			
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
		
	}
	
	
	public void doAdd(int idUtente, int idProdotto) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO wishlist VALUES(?, ?)";

		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idUtente);
			ps.setInt(2, idProdotto);
			ps.executeUpdate();
			
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
		
	}
	
	private static DataSource ds;
	
}
