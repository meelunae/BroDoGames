package brodo.control;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import brodo.model.Carrello;
import brodo.model.Esito;
import brodo.model.ProdottoDAO;

/**
 * Servlet implementation class CatalogoServlet
 */
@WebServlet("/Catalogo")
public class CatalogoServlet extends HttpServlet {
	
	static {
		
		p = new ProdottoDAO();
		
	}
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Carrello c = (Carrello) request.getSession().getAttribute("carrello");
		if(c == null) {
			
			c = new Carrello();
			request.getSession().setAttribute("carrello", c);
				
		}
		
		String action = request.getParameter("action");
		
		if(action != null && !action.equals("")) {
			
			if(action.equalsIgnoreCase("addC")) {	//Se l'utente sceglie di aggiungere il prodotto al carrello
				
				String esito;	//serve per ottenere il JSON relativo all'esito dell'aggiunta al carrello
				
				if(request.getParameter("tipo").equals("fisico")) {
					
					esito = c.aggiungiProdotto(p.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))), true);
				
				} else {
					
					
					esito = c.aggiungiProdotto(p.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))), false);
				}
				
				request.getSession().setAttribute("carrello", c);
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(esito);
				return;
				
			} else if(action.equalsIgnoreCase("details")) {
				
				request.removeAttribute("prodottoScelto");
				request.setAttribute("prodottoScelto", p.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SchedaProdotto.jsp");
				dispatcher.forward(request, response);
				return;
				
			}
			
			
		}
		
		String ordinamento = request.getParameter("sort");
		request.removeAttribute("catalogo");
		request.setAttribute("catalogo", p.doRetrieveAll(ordinamento));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Catalogo.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static ProdottoDAO p;

}
