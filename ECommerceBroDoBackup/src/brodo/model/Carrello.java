package brodo.model;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Carrello {

	public Carrello() {
		
		prodotti = new ArrayList<ItemCarrello>();
		
	}
	
	public synchronized String aggiungiProdotto(ProdottoBean e, boolean fisico) {
		
		for(ItemCarrello val : prodotti) {	//si scorre il carrello per verificare se il prodotto sia stato già aggiunto in precedenza 
			
			if(e.getId() == val.getProdotto().getId()) {
				
				if(fisico) {
					
					val.incrementaQtaFis();
					if(e.getQtaFis() < val.getQtaFis()) {	//se la disponibilità nel carrello è minore della disponibiltà ordinata, si decrementa e si impedisce l'inserimento
						
						val.setQtaFis(val.getQtaFis()-1);
						return new Gson().toJson(new Esito(false, 0, 0, getNumProdotti()));
						
					}
					
					return new Gson().toJson(new Esito(true, 0, 0, getNumProdotti()));
					
				} else {
					
					val.incrementaQtaDig();
					if(e.getQtaDig() < val.getQtaDig()) {
						
						val.setQtaDig((val.getQtaDig()-1));
						return new Gson().toJson(new Esito(false, 0, 0, getNumProdotti()));
						
					}
					
					return new Gson().toJson(new Esito(true, 0, 0, getNumProdotti()));
					
				}
				
			}
			
		}
		
		/*se il prodotto non era stato precedentemente aggiunto al carrello e non vi sono prodotti disponibili non 
		 aggiunge, altrimenti aggiunge*/
		
		if(fisico) {
			
			if(e.getQtaFis() <= 0) {	
					
				return new Gson().toJson(new Esito(false, 0, 0, getNumProdotti()));
				
			} 
			
			prodotti.add(new ItemCarrello(e, fisico));
			return new Gson().toJson(new Esito(true, 0, 0, getNumProdotti()));
			
		} else {
			
			if(e.getQtaDig() <= 0) {
				
				return new Gson().toJson(new Esito(false, 0, 0, getNumProdotti()));
				
			}
			
			prodotti.add(new ItemCarrello(e, fisico));
			return new Gson().toJson(new Esito(true, 0, 0, getNumProdotti()));
			
		}
		
		
	}
	
	public synchronized String setQtaOrdinati(ProdottoBean e, int qtaFis, int qtaDig) {
		
		boolean ok = true;	//vale false se la nuova quantità eccede la quantità disponibile
		int nuovaFis = qtaFis;
		int nuovaDig = qtaDig;
		
		for(int i = 0; i < prodotti.size(); i++) {
			
			if((prodotti.get(i).getProdotto().getId()) == (e.getId())) {	//trovato!!
				
				if(qtaFis <= 0 && qtaDig <=0) {
						
					prodotti.remove(i);
					break;
						
				} 
				
				if(qtaFis <= prodotti.get(i).getProdotto().getQtaFis()){
						
					prodotti.get(i).setQtaFis(qtaFis);
						
				} else {	//se la nuova quantità eccede quella disponibile...
						
					prodotti.get(i).setQtaFis(prodotti.get(i).getProdotto().getQtaFis());
					nuovaFis = prodotti.get(i).getProdotto().getQtaFis();	//...si setta la nuova quantità fisica al max disponibile
					ok = false;

				}
				
				if(qtaDig <= prodotti.get(i).getProdotto().getQtaDig()){
					
					prodotti.get(i).setQtaDig(qtaDig);
						
				} else {
						
					prodotti.get(i).setQtaDig(prodotti.get(i).getProdotto().getQtaDig());
					nuovaDig = prodotti.get(i).getProdotto().getQtaDig();
					ok = false;

				}
								
			}
			
		}
				
		Esito es = new Esito(ok, nuovaFis, nuovaDig, getNumProdotti());
		String c = new Gson().toJson(es);
		return c;
	}
	
	public ArrayList<ItemCarrello> getProdotti(){
		
		return prodotti;
		
	}	
	
	public int getNumProdotti() {
		return this.prodotti.size();
	}
	
	private ArrayList<ItemCarrello> prodotti;
	
}
