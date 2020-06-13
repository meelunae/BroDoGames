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
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static {
		
		cd = new CheckoutDAO();
		pd = new ProdottoDAO();
		
	}
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean flag = false;
		c = (Carrello) request.getSession().getAttribute("carrello");
		if(c == null) {
			
			c = new Carrello();
			request.getSession().setAttribute("carrello", c);
				
		}
		
		if(request.getParameter("action") != null && !request.getParameter("action").equals("")) {
			
			if(request.getParameter("action").equals("updateQta")) {
				
				for(int i = 0; i < c.getProdotti().size(); i++) {
					
					if(c.getProdotti().get(i).getProdotto().getId() == Integer.parseInt(request.getParameter("id"))) {
						
						c.setQtaOrdinati(c.getProdotti().get(i).getProdotto(), Integer.parseInt(request.getParameter("newQtaFis")), Integer.parseInt(request.getParameter("newQtaDig")));
						break;
						
					}
					
				}
				
			} else if(request.getParameter("action").equals("checkout")) {
				
				if(request.getSession().getAttribute("userId") == null) {
					
					RequestDispatcher view = request.getRequestDispatcher("/Accesso");
					view.forward(request, response);
					return;
					
				}
				
				if(pd.doFixCart(c).equals("")) {
					
					request.setAttribute("cart", c);
					if(cd.doSave(c, (Integer) request.getSession().getAttribute("userId"))) {

						for(int i = 0; i < c.getProdotti().size(); i++) {
							
							pd.doSumQtaFisico(c.getProdotti().get(i).getProdotto().getId(), - c.getProdotti().get(i).getQtaFis());
							pd.doSumQtaDigitale(c.getProdotti().get(i).getProdotto().getId(), - c.getProdotti().get(i).getQtaDig());							
							
						}
						request.setAttribute("cart", c);
						//TODO: aggiungere controllo sulle cose nel carrello -> accesso al db e se non ok impedire ordine
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Checkout.jsp");
						dispatcher.forward(request, response);
						c.getProdotti().clear();
						return;
						
					}
					
				}
								
			}
			
		}
		System.out.println("Messaggio: " + pd.doFixCart(c));
		//TODO: aggiungere controllo sulle cose nel carrello -> accesso al db
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
