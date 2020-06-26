package brodo.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brodo.model.WishlistDAO;

/**
 * Servlet implementation class WishlistServlet
 */
@WebServlet("/Wishlist")
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    static {
    	
    	w = new WishlistDAO();
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getSession().getAttribute("userId") != null) {	//si controlla se l'utente è autenticato
			
			if(Integer.parseInt(request.getParameter("idUtente")) != (Integer) request.getSession().getAttribute("userId")) {		//per evitare che utenti esterni possano accedere
				
				response.sendRedirect("./Catalogo");
				return;
				
			}
				
			if(request.getParameter("action") != null) {
							
				if(request.getParameter("action").equals("add")) {		//aggiunta di un prodotto
					
					w.doAdd(Integer.parseInt(request.getParameter("idUtente")), Integer.parseInt(request.getParameter("idProdotto")));
					
				} else if(request.getParameter("action").equals("del")) {	//eliminazione di un prodotto
	
					w.doRemove(Integer.parseInt(request.getParameter("idUtente")), Integer.parseInt(request.getParameter("idProdotto")));
					
				}
				
			}
			
			int key = Integer.parseInt(request.getParameter("idUtente"));
			request.setAttribute("prodotti", w.doRetrieveByUser(key));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Wishlist.jsp");
			dispatcher.forward(request, response);
			
		} else {
			
			response.sendRedirect("./LogIn.jsp");
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static WishlistDAO w;

}
