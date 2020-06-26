package brodo.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import brodo.model.UserBean;
import brodo.model.UserDAO;

/**
 * Servlet implementation class AccessoServlet
 */
@WebServlet("/Accesso")
public class AccessoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static {
		
		u = new UserDAO();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		UserBean result = u.checkLogin(email, password);
		if(result == null){

			session.removeAttribute("admin");
			session.removeAttribute("userLogged");
			session.removeAttribute("userId");
			session.setAttribute("incorrect", true);
			RequestDispatcher view = request.getRequestDispatcher("/LogIn.jsp");
			view.forward(request, response);
			return;
			
		} else if(result.isAdmin()) {

			session.removeAttribute("incorrect");
			session.setAttribute("admin", true);
			session.setAttribute("userLogged", true);
			session.setAttribute("userId", result.getId());
			session.setAttribute("username", result.getUsername());
			RequestDispatcher view = request.getRequestDispatcher("CatalogoAdmin.jsp");
			view.forward(request, response);
			return;
			
		} else {

			session.removeAttribute("admin");
			session.removeAttribute("incorrect");
			session.setAttribute("userLogged", true);
			session.setAttribute("userId", result.getId());
			session.setAttribute("username", result.getUsername());
			RequestDispatcher view = request.getRequestDispatcher("/Catalogo");
			view.forward(request, response);
			return;
			
		}
		
	}
	
	private static UserDAO u;

}
