package brodo.model;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Carrello {

	public Carrello() {
		
		prodotti = new ArrayList<ItemCarrello>();
		
	}
	
	public synchronized void aggiungiProdotto(ProdottoBean e, boolean fisico) {
		
		for(ItemCarrello val : prodotti) {
			
			if(e.getId() == val.getProdotto().getId()) {
				
				if(fisico) {
					
					val.incrementaQtaFis();
					return;
					
				} else {
					
					val.incrementaQtaDig();
					return;
					
				}
				
			}
			
		}
		
		prodotti.add(new ItemCarrello(e, fisico));
		
	}
	
	public synchronized String setQtaOrdinati(ProdottoBean e, int qtaFis, int qtaDig) {
		
		boolean ok = true;
		int nuovaFis = qtaFis;
		int nuovaDig = qtaDig;
		
		for(int i = 0; i < prodotti.size(); i++) {
			
			if((prodotti.get(i).getProdotto().getId()) == (e.getId())) {
				
				if(qtaFis <= 0 && qtaDig <=0) {
						
					prodotti.remove(i);
					break;
						
				} 
				
				if(qtaFis <= prodotti.get(i).getProdotto().getQtaFis()){
						
					prodotti.get(i).setQtaFis(qtaFis);
					nuovaFis = prodotti.get(i).getProdotto().getQtaFis();
					ok = false;
						
				} else {
						
					prodotti.get(i).setQtaFis(prodotti.get(i).getProdotto().getQtaFis());
						
				}
				
				if(qtaDig <= prodotti.get(i).getProdotto().getQtaDig()){
					
					prodotti.get(i).setQtaDig(qtaDig);
					nuovaDig = prodotti.get(i).getProdotto().getQtaDig();
					ok = false;
						
				} else {
						
					prodotti.get(i).setQtaDig(prodotti.get(i).getProdotto().getQtaDig());
						
				}
								
			}
			
		}
		
//		prodotti.add(new ItemCarrello(e, fisico));
		
		return new Gson().toJson("{Esito: " + ok + "NuovaF: " + nuovaFis + " NuovaD: " + nuovaDig + "}");
		
	}
	
	public ArrayList<ItemCarrello> getProdotti(){
		
		return prodotti;
		
	}	
	
	public int getNumProdotti() {
		return this.prodotti.size();
	}
	
	private ArrayList<ItemCarrello> prodotti;
	
}
