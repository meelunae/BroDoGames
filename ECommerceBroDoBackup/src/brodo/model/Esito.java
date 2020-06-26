package brodo.model;

public class Esito {
	
	public Esito(boolean esito, int nuovoFisico, int nuovoDigitale, int sizeCart) {
		super();
		this.esito = esito;
		this.nuovoFisico = nuovoFisico;
		this.nuovoDigitale = nuovoDigitale;
		this.sizeCart = sizeCart;
	}
	public boolean getEsito() {
		return esito;
	}
	public void setEsito(boolean esito) {
		this.esito = esito;
	}
	public int getNuovoFisico() {
		return nuovoFisico;
	}
	public void setNuovoFisico(int nuovoFisico) {
		this.nuovoFisico = nuovoFisico;
	}
	public int getNuovoDigitale() {
		return nuovoDigitale;
	}
	public void setNuovoDigitale(int nuovoDigitale) {
		this.nuovoDigitale = nuovoDigitale;
	}
	public int getSizeCart() {
		return sizeCart;
	}
	public void setSizeCart(int sizeCart) {
		this.sizeCart = sizeCart;
	}
	@Override
	public String toString() {
		return "Esito [esito=" + esito + ", nuovoFisico=" + nuovoFisico + ", nuovoDigitale=" + nuovoDigitale + "]";
	}

	private boolean esito;
	private int nuovoFisico;
	private int nuovoDigitale;
	private int sizeCart;
	
}
