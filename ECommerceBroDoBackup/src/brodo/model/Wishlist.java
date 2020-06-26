package brodo.model;

public class Wishlist {
	
	public Wishlist(int idUtente, int idProdotto) {
		this.idUtente = idUtente;
		this.idProdotto = idProdotto;
	}
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	public int getIdProdotto() {
		return idProdotto;
	}
	
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	private int idUtente;
	private int idProdotto;
}
