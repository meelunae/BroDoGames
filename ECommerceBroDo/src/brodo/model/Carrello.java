package brodo.model;

import java.util.ArrayList;

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
	
	public synchronized void setQtaOrdinati(ProdottoBean e, int qtaFis, int qtaDig) {
		
		for(int i = 0; i < prodotti.size(); i++) {
			
			if((prodotti.get(i).getProdotto().getId()) == (e.getId())) {
				
				if(qtaFis <= 0 && qtaDig <=0) {
						
					prodotti.remove(i);
					break;
						
				} 
				
				if(qtaFis <= prodotti.get(i).getProdotto().getQtaFis()){
						
					prodotti.get(i).setQtaFis(qtaFis);
						
				} else {
						
					prodotti.get(i).setQtaFis(prodotti.get(i).getProdotto().getQtaFis());
						
				}
				
				if(qtaDig <= prodotti.get(i).getProdotto().getQtaDig()){
					
					prodotti.get(i).setQtaDig(qtaDig);
						
				} else {
						
					prodotti.get(i).setQtaDig(prodotti.get(i).getProdotto().getQtaDig());
						
				}
								
			}
			
		}
		
//		prodotti.add(new ItemCarrello(e, fisico));
		
	}
	
	public ArrayList<ItemCarrello> getProdotti(){
		
		return prodotti;
		
	}	
	
	private ArrayList<ItemCarrello> prodotti;
	
}
