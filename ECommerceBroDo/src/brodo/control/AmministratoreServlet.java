package brodo.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brodo.model.ProdottoBean;
import brodo.model.ProdottoDAO;

/**
 * Servlet implementation class AmministratoreServlet
 */
@WebServlet("/Amministratore")
public class AmministratoreServlet extends HttpServlet {
	
	static {
		
		p = new ProdottoDAO();
		
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmministratoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("lol");
		if(request.getParameter("action") != null) {

			if(request.getParameter("action").equalsIgnoreCase("addProdotto")) {
				ProdottoBean prod = new ProdottoBean();
				prod.setTitolo(request.getParameter("titolo"));
				prod.setnVenduti(0);
				prod.setPrezzoFis(Double.parseDouble(request.getParameter("prezzoFisico")));
				prod.setPrezzoDig(Double.parseDouble(request.getParameter("prezzoDigitale")));
				prod.setDescrizione(request.getParameter("descrizione"));
				prod.setQtaFis(Integer.parseInt(request.getParameter("qtaFisico")));
				prod.setQtaDig(Integer.parseInt(request.getParameter("qtaDigitale")));
				prod.setCasaSviluppatrice(request.getParameter("casaSviluppatrice"));
				//prod.setInVendita(Boolean.parseBoolean(request.getParameter("inVendita")));
				prod.setInVendita(true);
				prod.setPegi(Integer.parseInt(request.getParameter("pegi")));
				prod.setData(request.getParameter("dataUscita"));
				p.doSave(prod);
				
			} else if(request.getParameter("action").equalsIgnoreCase("deleteProdotto")) {
				
				//p.doDelete(Integer.parseInt(request.getParameter("id")));
				p.doNotOnSale(Integer.parseInt(request.getParameter("id")));
				
			} else if(request.getParameter("action").equalsIgnoreCase("edit")) {
				
				p.doUpdateDescrizione(request.getParameter("descrizione"), Integer.parseInt(request.getParameter("id")));
				p.doUpdatePrezzoDig(Double.parseDouble(request.getParameter("prezzoDigitale")), Integer.parseInt(request.getParameter("id")));
				p.doUpdatePrezzoFis(Double.parseDouble(request.getParameter("prezzoFisico")), Integer.parseInt(request.getParameter("id")));
				p.doUpdateQtaDigitale(Integer.parseInt(request.getParameter("qtaDigitale")), Integer.parseInt(request.getParameter("id")));
				p.doUpdateQtaFisico(Integer.parseInt(request.getParameter("qtaFisico")), Integer.parseInt(request.getParameter("id")));
				p.doUpdateTitolo(request.getParameter("titolo"), Integer.parseInt(request.getParameter("id")));
				
			} else if(request.getParameter("action").equalsIgnoreCase("details")) {
				
				request.removeAttribute("prodottoScelto");
				request.setAttribute("prodottoScelto", p.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SchedaProdotto.jsp");
				dispatcher.forward(request, response);
				return;
				
			} else if(request.getParameter("action").equalsIgnoreCase("scegliModifica")) {
				
				//request.removeAttribute("prodDaMod");
				request.setAttribute("prodDaMod", (ProdottoBean) p.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))));
				
			}

			
		}
		
		String ordinamento = request.getParameter("sort");
		request.removeAttribute("catalogo");
		request.setAttribute("catalogo", p.doRetrieveAll(ordinamento));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CatalogoAdmin.jsp");
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
