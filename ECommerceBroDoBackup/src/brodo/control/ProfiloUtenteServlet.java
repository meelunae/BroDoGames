package brodo.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brodo.model.OrdineDAO;
import brodo.model.UserBean;
import brodo.model.UserDAO;

/**
 * Servlet implementation class ProfiloUtenteServlet
 */
@WebServlet("/ProfiloUtente")
public class ProfiloUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   static {
	   
	   u = new UserDAO();
	   o = new OrdineDAO();
	   
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int key = Integer.parseInt(request.getParameter("id"));
		UserBean user = u.doRetrieveByKey(key);
		request.setAttribute("utente", user);
		request.setAttribute("ordini", o.doRetrieveByUser(key));
		RequestDispatcher view = request.getRequestDispatcher("/User.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private static UserDAO u;
	private static OrdineDAO o;
	
}
