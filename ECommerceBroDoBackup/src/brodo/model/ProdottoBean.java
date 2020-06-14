package brodo.model;

public class ProdottoBean {
	
	public ProdottoBean(int id, String titolo, int nVenduti, double prezzoFis, double prezzoDig, String console, String descrizione, int qtaFis, int qtaDig, String casaSviluppatrice, boolean inVendita, int pegi, String data) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.titolo = titolo;
		this.data = data;
		this.pegi = pegi;
		this.inVendita = inVendita;
		this.console = console;
		this.casaSviluppatrice = casaSviluppatrice;
		this.qtaFis = qtaFis;
		this.qtaDig = qtaDig;
		this.nVenduti = nVenduti;
		this.prezzoFis = prezzoFis;
		this.prezzoDig = prezzoDig;
	}
	public ProdottoBean() {
		
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getPegi() {
		return pegi;
	}
	public void setPegi(int pegi) {
		this.pegi = pegi;
	}
	public boolean isInVendita() {
		return inVendita;
	}
	public void setInVendita(boolean inVendita) {
		this.inVendita = inVendita;
	}
	public String getCasaSviluppatrice() {
		return casaSviluppatrice;
	}
	public void setCasaSviluppatrice(String casaSviluppatrice) {
		this.casaSviluppatrice = casaSviluppatrice;
	}
	public int getQtaFis() {
		return qtaFis;
	}
	public void setQtaFis(int qtaFis) {
		this.qtaFis = qtaFis;
	}
	public int getQtaDig() {
		return qtaDig;
	}
	public void setQtaDig(int qtaDig) {
		this.qtaDig = qtaDig;
	}
	public int getnVenduti() {
		return nVenduti;
	}
	public void setnVenduti(int nVenduti) {
		this.nVenduti = nVenduti;
	}
	public double getPrezzoFis() {
		return (prezzoFis + prezzoFis * iva);
	}
	public void setPrezzoFis(double prezzoFis) {
		this.prezzoFis = prezzoFis;
	}
	public double getPrezzoDig() {
		return prezzoDig + prezzoDig * iva;
	}
	public void setPrezzoDig(double prezzoDig) {
		this.prezzoDig = prezzoDig;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String toString() {
		return "ProdottoBean [id=" + id + ", descrizione=" + descrizione + ", titolo=" + titolo + ", data=" + data
				+ ", pegi=" + pegi + ", inVendita=" + inVendita + ", casaSviluppatrice=" + casaSviluppatrice
				+ ", qtaFis=" + qtaFis + ", qtaDig=" + qtaDig + ", nVenduti=" + nVenduti + ", prezzoFis=" + prezzoFis
				+ ", prezzoDig=" + prezzoDig + "]";
	}
	public double getIva() {
		
		return iva;
		
	}
	public double getPrezzoFisSenzaIva() {
		
		return prezzoFis;
		
	}
	
	public double getPrezzoDigSenzaIva() {
		
		return prezzoDig;
		
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}

	private int id;
	private String descrizione;
	private String titolo;
	private String data;
	private int pegi;
	private boolean inVendita;
	private String casaSviluppatrice;
	private int qtaFis;
	private int qtaDig;
	private int nVenduti;
	private double prezzoFis;
	private double prezzoDig;
	private String console;
	private static final double iva = 0.22;

}
