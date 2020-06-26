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
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
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
		UserBean user = new UserBean();
		user.setNome(request.getParameter("nome"));
		user.setCognome(request.getParameter("cognome"));
		user.setEmail(request.getParameter("email"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setDataNascita(request.getParameter("dataNascita"));
		user.setAdmin(false);
		boolean success = u.doSave(user);
		if(!success) {
			
			HttpSession session = request.getSession();
			session.setAttribute("failed", true);
			RequestDispatcher view = request.getRequestDispatcher("SignUp.jsp");
			view.forward(request, response);
			return;
			
		}
		RequestDispatcher view = request.getRequestDispatcher("LogIn.jsp");
		view.forward(request, response);
		
	}
	
	private static UserDAO u;

}
