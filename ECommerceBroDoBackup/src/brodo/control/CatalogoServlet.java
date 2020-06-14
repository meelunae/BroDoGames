package brodo.control;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brodo.model.Carrello;
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
			
			if(action.equalsIgnoreCase("addC")) {
				
				if(request.getParameter("tipo").equals("fisico")) {
					c.aggiungiProdotto(p.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))), true);
				} else {
					c.aggiungiProdotto(p.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))), false);
				}
				request.getSession().setAttribute("carrello", c);
				
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
