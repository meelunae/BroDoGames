package brodo.model;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ProdottoDAO {
	
	static {
		
		try {
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("dbrodo");
			
		}catch(NamingException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public synchronized void doSave(ProdottoBean prod) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO prodotto (titolo, numVenduti, prezzoFisico, prezzoDigitale, descrizione, qtaFisico, qtaDigitale, casaSviluppatrice, inVendita, pegi, dataUscita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, prod.getTitolo());
			ps.setInt(2, prod.getnVenduti());
			ps.setDouble(3, prod.getPrezzoFis());
			ps.setDouble(4, prod.getPrezzoDig());
			ps.setString(5, prod.getDescrizione());
			ps.setInt(6, prod.getQtaFis());
			ps.setInt(7, prod.getQtaDig());
			ps.setString(8, prod.getCasaSviluppatrice());
			ps.setBoolean(9, prod.isInVendita());
			ps.setInt(10, prod.getPegi());
			ps.setString(11, prod.getData());
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
	
	public synchronized ProdottoBean doRetrieveByKey(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ProdottoBean result = new ProdottoBean();
		String query = "SELECT * FROM prodotto WHERE id = ?";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result.setId(rs.getInt("id"));
			result.setTitolo(rs.getString("titolo"));
			result.setnVenduti(rs.getInt("numVenduti"));
			result.setPrezzoFis(rs.getInt("prezzoFisico"));
			result.setPrezzoDig(rs.getInt("prezzoDigitale"));
			result.setDescrizione(rs.getString("descrizione"));
			result.setQtaFis(rs.getInt("qtaFisico"));
			result.setQtaDig(rs.getInt("qtaDigitale"));
			result.setCasaSviluppatrice(rs.getString("casaSviluppatrice"));
			result.setInVendita(rs.getBoolean("inVendita"));
			result.setPegi(rs.getInt("pegi"));
			result.setData(rs.getString("dataUscita"));

			
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
	
	public synchronized boolean doDelete(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		boolean esito = false;
		String query = "DELETE FROM prodotto WHERE id = ?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			if(ps.executeUpdate() != 0) {
				
				esito = true;
				
			} else {
				
				esito = false;
				
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
		
		return esito;
		
	}
	
	public synchronized ArrayList<ProdottoBean> doRetrieveAll(String order){

		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<ProdottoBean> output = new ArrayList<ProdottoBean>();
		String query = "SELECT * FROM prodotto ORDER BY ";
		
		try {
			
			conn = ds.getConnection();
			if(order != null && !order.equals("")) {

				query += order;
				
			} else {
				
				query += "id";
				
			}
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				if(rs.getBoolean("inVendita")) {
					ProdottoBean result = new ProdottoBean();
					result.setId(rs.getInt("id"));
					result.setTitolo(rs.getString("titolo"));
					result.setnVenduti(rs.getInt("numVenduti"));
					result.setPrezzoFis(rs.getInt("prezzoFisico"));
					result.setPrezzoDig(rs.getInt("prezzoDigitale"));
					result.setDescrizione(rs.getString("descrizione"));
					result.setQtaFis(rs.getInt("qtaFisico"));
					result.setQtaDig(rs.getInt("qtaDigitale"));
					result.setCasaSviluppatrice(rs.getString("casaSviluppatrice"));
					result.setInVendita(rs.getBoolean("inVendita"));
					result.setPegi(rs.getInt("pegi"));
					result.setData(rs.getString("dataUscita"));
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
	
	public synchronized void doUpdatePrezzoFis(double newPrezzo, int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "UPDATE prodotto SET prezzoFisico = ? WHERE id = ?";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, newPrezzo);
			ps.setInt(2, id);
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
	
	public synchronized void doNotOnSale(int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query="UPDATE prodotto SET inVendita=false WHERE id = ?";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
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
	
	public synchronized void doUpdatePrezzoDig(double newPrezzo, int id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "UPDATE prodotto SET prezzoDigitale = ? WHERE id = ?";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, newPrezzo);
			ps.setInt(2, id);
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
	
	public synchronized void doUpdateQtaFisico(int newQta, int id){
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE prodotto SET qtaFisico = ? WHERE id = ?";
        try{
        	System.out.println("Id:" + id);
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, newQta);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch(SQLException e){
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
	
public synchronized void doSumQtaFisico(int id, int qta) {
		
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE prodotto SET qtaFisico = ? WHERE id = ?";
        String query2 = "SELECT qtaFisico FROM prodotto WHERE id = ?";
        try {
        
        	conn = ds.getConnection();
        	ps = conn.prepareStatement(query2);
        	ps.setInt(1, id);
        	ResultSet rs = ps.executeQuery();
        	rs.next();
        	int qtaVecchia = rs.getInt("qtaFisico");
        	ps = conn.prepareStatement(query);
        	ps.setInt(1, qtaVecchia + qta);
        	ps.setInt(2, id);
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
	
	public synchronized void doSumQtaDigitale(int id, int qta) {
		
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE prodotto SET qtaDigitale = ? WHERE id = ?";
        String query2 = "SELECT qtaDigitale FROM prodotto WHERE id = ?";
        try {
        
        	conn = ds.getConnection();
        	ps = conn.prepareStatement(query2);
        	ps.setInt(1, id);
        	ResultSet rs = ps.executeQuery();
        	rs.next();
        	int qtaVecchia = rs.getInt("qtaDigitale");
        	ps = conn.prepareStatement(query);
        	ps.setInt(1, qtaVecchia + qta);
        	ps.setInt(2, id);
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

    public synchronized void doUpdateQtaDigitale(int newQta, int id){
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE prodotto SET qtaDigitale = ? WHERE id = ?";
        try{
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, newQta);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch(SQLException e){
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

    public synchronized void doUpdateTitolo(String newTitolo, int idProdotto) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query =  " UPDATE prodotto SET titolo = ? WHERE id = ?";
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newTitolo);
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


    public synchronized void doUpdateDescrizione(String newDescrizione, int idProdotto) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query =  " UPDATE prodotto SET descrizione = ? WHERE id = ?";
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, newDescrizione);
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
    
    public synchronized static byte[] load(String id) {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		byte[] bt = null;

		try {
			connection = ds.getConnection();
			String sql = "SELECT cover FROM prodotto WHERE id = " + id;
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("cover");
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
		
		try {
			
			connection.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		try {
			
			stmt.close();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		return bt;
	}

	public synchronized static void updatePhoto(String idA, String photo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ds.getConnection();

			stmt = con.prepareStatement("UPDATE prodotto SET cover = ? WHERE id = ?");
			
			File file = new File(photo);
			FileInputStream fis = new FileInputStream(file);
			stmt.setBinaryStream(1, fis, fis.available());
			stmt.setString(2, idA);
				
			stmt.executeUpdate();
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}
			
			try {
				
				con.close();
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
			try {
				
				stmt.close();
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
			
	}
	
	public String doFixCart(Carrello c) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String output = "";
		String query = "SELECT prezzoFisico, prezzoDigitale, qtaFisico, qtaDigitale, inVendita FROM prodotto WHERE id = ?";
		try {
			
			conn = ds.getConnection();
			ps = conn.prepareStatement(query);
			for(int i = 0; i < c.getProdotti().size(); i++) {
				
				ItemCarrello p = c.getProdotti().get(i);
				ps = conn.prepareStatement(query);
				ps.setInt(1, p.getProdotto().getId());
				ResultSet rs = ps.executeQuery();
				rs.next();
				if(rs.getDouble("prezzoFisico") != p.getProdotto().getPrezzoFisSenzaIva()) {
					
					output += "Il prezzo fisico del prodotto" + p.getProdotto().getTitolo() + " ha subito una variazione ";
					p.getProdotto().setPrezzoFis(rs.getDouble("prezzoFisico"));
					
				}
				
				if(rs.getDouble("prezzoDigitale") != p.getProdotto().getPrezzoDigSenzaIva()) {
					
					output += "Il prezzo digitale del prodotto" + p.getProdotto().getTitolo() + " ha subito una variazione ";
					p.getProdotto().setPrezzoDig(rs.getDouble("prezzoDigitale"));
					
				}
				
				if(rs.getInt("qtaFisico") < p.getQtaFis()) {
					
					output += "La quantita' fisica del prodotto" + p.getProdotto().getTitolo() + " ha subito una variazione ";
					p.setQtaFis(rs.getInt("qtaFisico"));
					
				}
				
				p.getProdotto().setQtaFis(rs.getInt("qtaFisico"));
				
				if(rs.getInt("qtaDigitale") < p.getQtaDig()) {
					output += "La quantita' digitale del prodotto" + p.getProdotto().getTitolo() + " ha subito una variazione ";
					p.setQtaDig(rs.getInt("qtaDigitale"));
					
				}
				
				p.getProdotto().setQtaDig(rs.getInt("qtaDigitale"));
				
				if(!rs.getBoolean("invendita")) {
					
					output += "Il prodotto" + p.getProdotto().getTitolo() + " non è più in vendita\n";
					c.getProdotti().remove(i);
					i--;
					continue;
					
				}
				
				if(p.getQtaFis() <= 0 && p.getQtaDig() <= 0) {
					
					c.getProdotti().remove(i);
					i--;
					
				}
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
		
		return output;
		
	}
		
	private static DataSource ds;

}
