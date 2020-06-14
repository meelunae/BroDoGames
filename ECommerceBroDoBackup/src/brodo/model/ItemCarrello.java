package brodo.model;

public class ItemCarrello {
	
	public ItemCarrello(ProdottoBean prodotto, boolean fisico) {
		
		this.prodotto = prodotto;
		if(fisico) {
			
			this.qtaFis = 1;
			
		} else {
			
			this.qtaDig = 1;
			
		}
		
	}
	
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
	}
	public int getQtaFis() {
		return qtaFis;
	}
	public void setQtaFis(int qtaFis) {
		this.qtaFis = qtaFis;
	}
	public synchronized void incrementaQtaFis() {
		
		this.qtaFis++;
		
	}
	public int getQtaDig() {
		return qtaDig;
	}
	public void setQtaDig(int qtaDig) {
		this.qtaDig = qtaDig;
	}
	public synchronized void incrementaQtaDig() {
		
		this.qtaDig++;
		
	}
	
	private ProdottoBean prodotto;
	private int qtaFis;
	private int qtaDig;

}
