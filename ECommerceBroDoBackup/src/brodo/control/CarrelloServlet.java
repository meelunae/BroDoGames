package brodo.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brodo.model.Carrello;
import brodo.model.CheckoutDAO;
import brodo.model.ProdottoBean;
import brodo.model.ProdottoDAO;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static {
		
		cd = new CheckoutDAO();
		pd = new ProdottoDAO();
		
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean flag = false;
		c = (Carrello) request.getSession().getAttribute("carrello");
		if(c == null) {
			
			c = new Carrello();
			request.getSession().setAttribute("carrello", c);
				
		}
		
		if(request.getParameter("action") != null && !request.getParameter("action").equals("")) {
			
			if(request.getParameter("action").equals("updateQta")) {	//aggiornamento di una quantità nel carrello
				
				for(int i = 0; i < c.getProdotti().size(); i++) {	//si scorre il carrello

					if(c.getProdotti().get(i).getProdotto().getId() == Integer.parseInt(request.getParameter("id"))) {	//appena si trova il prodotto cercato si cambia la quantità ordinata
						
						String esito = c.setQtaOrdinati(c.getProdotti().get(i).getProdotto(), Integer.parseInt(request.getParameter("newQtaFis")), Integer.parseInt(request.getParameter("newQtaDig")));
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(esito);	//si restituisce un JSON che rappresenta l'esito dell'aggiunta al carrello
						return;
						
					}
					
				}
				
			} else if(request.getParameter("action").equals("checkout")) {
				
				if(request.getSession().getAttribute("userId") == null) {	//si controlla se l'utente abbia effettuato il login, in caso contrario lo si manda alla pagina di login
					
					RequestDispatcher view = request.getRequestDispatcher("./LogIn.jsp");
					view.forward(request, response);
					return;
					
				}
				
				if(pd.doFixCart(c).equals("")) {	//in ogni caso prima di procedere al checkout è necessario verificare la disponibilità dei prodotti nel database
					
					request.setAttribute("cart", c);
					if(cd.doSave(c, (Integer) request.getSession().getAttribute("userId"))) {

						for(int i = 0; i < c.getProdotti().size(); i++) {
							
							pd.doSumQtaFisico(c.getProdotti().get(i).getProdotto().getId(), - c.getProdotti().get(i).getQtaFis());
							pd.doSumQtaDigitale(c.getProdotti().get(i).getProdotto().getId(), - c.getProdotti().get(i).getQtaDig());							
							
						}
						request.setAttribute("cart", c);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Checkout.jsp");
						dispatcher.forward(request, response);
						c.getProdotti().clear();	//si svuota il carrello dopo il checkout!
						return;
						
					}
					
				} else {	//se qualche prodotto ha subito variazioni a livello di prezzo o la quantità ordinata eccede la quantità disponibile si torna al carrello
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
					dispatcher.forward(request, response);
					return;
					
				}
								
			}
			
		}
		pd.doFixCart(c);	//Ogni volta che viene caricato il carrello lo si mostra in uno stato consistente e quindi si fa l'accesso alla base di dati
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
		dispatcher.forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Carrello c;
	private static CheckoutDAO cd;
	private static ProdottoDAO pd;

}
