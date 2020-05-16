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

public class UserDAO {
	
	static {
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("dbrodo");
			
		} catch(NamingException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public synchronized void doSave(UserBean user) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		//String pw = user.getPassword();
		//String encPw = "SELECT PASSWORD('"+ pw +"')";
		//String encPw ="SELECT PASSWORD(?)";
		String query = "INSERT INTO utente (nome, cognome, dataNascita, username, password, email, citta, via, numCivico, cap, admin) VALUES (?, ?, ?, ?, SHA(?), ?, ?, ?, ?, ?, ?)";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getCognome());
			ps.setString(3, user.getDataNascita());
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getEmail());
			ps.setString(7, user.getCitta());
			ps.setString(8, user.getVia());
			ps.setInt(9, user.getNumCivico());
			ps.setInt(10, user.getCap());
			ps.setBoolean(11, user.isAdmin());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			
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
	
	public synchronized UserBean doRetrieveByKey(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		UserBean result = new UserBean();
		String query = "SELECT * FROM utente WHERE id = ?";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result.setId(rs.getInt("id"));
			result.setNome(rs.getString("nome"));
			result.setCognome(rs.getString("cognome"));
			result.setDataNascita(rs.getString("dataNascita"));
			result.setUsername(rs.getString("username"));
			result.setPassword(rs.getString("password"));
			result.setEmail(rs.getString("email"));
			result.setCitta(rs.getString("citta"));
			result.setVia(rs.getString("via"));
			result.setNumCivico(rs.getInt("numCivico"));
			result.setCap(rs.getInt("cap"));
			result.setAdmin(rs.getBoolean("admin"));
			
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
	
	public synchronized ArrayList<UserBean> doRetrieveAll() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<UserBean> result = new ArrayList<UserBean>();
		String query = "SELECT * FROM utente";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				UserBean utente = new UserBean();
				utente.setId(rs.getInt("id"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setDataNascita(rs.getString("dataNascita"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setEmail(rs.getString("email"));
				utente.setCitta(rs.getString("citta"));
				utente.setVia(rs.getString("via"));
				utente.setNumCivico(rs.getInt("numCivico"));
				utente.setCap(rs.getInt("cap"));
				utente.setAdmin(rs.getBoolean("admin"));
				result.add(utente);
				
			}
			
			
		}catch(SQLException e) {
			
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
	
	public synchronized void doDelete(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "DELETE FROM utente WHERE id = ?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			
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
	
	public synchronized UserBean checkLogin(String email, String password) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		UserBean result = new UserBean();
		String query = "SELECT * FROM utente WHERE password = SHA(?) AND email = ?";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, password);
			ps.setString(2, email);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				
				System.out.println("ciao");
				return null;
				
			}
			result.setId(rs.getInt("id"));
			result.setNome(rs.getString("nome"));
			result.setCognome(rs.getString("cognome"));
			result.setDataNascita(rs.getString("dataNascita"));
			result.setUsername(rs.getString("username"));
			result.setPassword(rs.getString("password"));
			result.setEmail(rs.getString("email"));
			result.setCitta(rs.getString("citta"));
			result.setVia(rs.getString("via"));
			result.setNumCivico(rs.getInt("numCivico"));
			result.setCap(rs.getInt("cap"));
			result.setAdmin(rs.getBoolean("admin"));			
			
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
