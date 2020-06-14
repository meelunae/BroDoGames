package brodo.model;

public class Ordine {
	
	public Ordine(int idOrdine, int idUtente, int idProdotto, String dataOra, double prezzoFis, double prezzoDig, boolean consegnato,
			double iva, int qtaDigitale, int qtaFisico) {
		super();
		this.idOrdine = idOrdine;
		this.idUtente = idUtente;
		this.idProdotto = idProdotto;
		this.dataOra = dataOra;
		this.prezzoFis = prezzoFis;
		this.prezzoDig = prezzoDig;
		this.consegnato = consegnato;
		this.iva = iva;
		this.qtaDigitale = qtaDigitale;
		this.qtaFisico = qtaFisico;
	}
	public Ordine() {}
	public int getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
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
	public String getDataOra() {
		return dataOra;
	}
	public void setDataOra(String dataOra) {
		this.dataOra = dataOra;
	}
	
	public double getPrezzoFis() {
		return prezzoFis;
	}
	public void setPrezzoFis(double prezzoFis) {
		this.prezzoFis = prezzoFis;
	}
	public double getPrezzoDig() {
		return prezzoDig;
	}
	public void setPrezzoDig(double prezzoDig) {
		this.prezzoDig = prezzoDig;
	}
	public boolean isConsegnato() {
		return consegnato;
	}
	public void setConsegnato(boolean consegnato) {
		this.consegnato = consegnato;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public int getQtaDigitale() {
		return qtaDigitale;
	}
	public void setQtaDigitale(int qtaDigitale) {
		this.qtaDigitale = qtaDigitale;
	}
	public int getQtaFisico() {
		return qtaFisico;
	}
	public void setQtaFisico(int qtaFisico) {
		this.qtaFisico = qtaFisico;
	}
	
	@Override
	public String toString() {
		return "Ordine [idOrdine=" + idOrdine + ", idUtente=" + idUtente + ", idProdotto=" + idProdotto + ", dataOra="
				+ dataOra + ", prezzoFis=" + prezzoFis + ", prezzoDig=" + prezzoDig + ", consegnato=" + consegnato
				+ ", iva=" + iva + ", qtaDigitale=" + qtaDigitale + ", qtaFisico=" + qtaFisico + "]\n";
	}

	private int idOrdine;
	private int idUtente;
	private int idProdotto;
	private String dataOra;
	private double prezzoFis;
	private double prezzoDig;
	private boolean consegnato;
	private double iva;
	private int qtaDigitale;
	private int qtaFisico;
	
}
