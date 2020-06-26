package brodo.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import brodo.model.ProdottoDAO;

@WebServlet("/UploadPhoto")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String SAVE_DIR ="uploadTemp";
       
    public UploadPhotoServlet() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		out.write("Errore: serve una post");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		//Costruiamo il path completo della cartella in cui salvare temporaneamente l'immagine
	    String appPath = request.getServletContext().getRealPath("");
	    String savePath = appPath + File.separator + SAVE_DIR;
	         
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {	//Se la cartella non esiste la si crea
			fileSaveDir.mkdir();
		}

		for (Part part : request.getParts()) {		//Si prende l'immagine dalla richiesta
			
			String fileName = extractFileName(part);	//Si prende il path dell'immagine
			
			if((File.separator).equals("/")) {
				
				int index = fileName.lastIndexOf("/");
				fileName = fileName.substring(index + 1);
				
			} else if((File.separator).equals("\\")) {
				
				int index = fileName.lastIndexOf("\\");
				fileName = fileName.substring(index + 1);

			}
			
			if (fileName != null && !fileName.equals("")) {
				
				part.write(savePath + File.separator + fileName);	//si inserisce la foto nella cartella temporanea
				
				try {
					
					ProdottoDAO.updatePhoto(id, savePath + File.separator + fileName);		//si carica la foto nel database
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				
				}
				
			}
			
		}
		RequestDispatcher view = request.getRequestDispatcher("/CatalogoAdmin.jsp");
		view.forward(request, response);
	}
	
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        
        for (String s : items) {
        	
            if (s.trim().startsWith("filename")) {
            	
                return s.substring(s.indexOf("=") + 2, s.length()-1);
                
            }
        }
        
        return "";
    }		

}
