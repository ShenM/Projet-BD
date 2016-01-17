package com.sdzee.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet permettant la gestion des telechargement de fichiers par un admin
 *
 */
public class Telechargement extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String DIR_REMBOURSEMENT = "C:\\ProjetBD_FichiersRemboursements\\";
    private static final String ATT_SESSION_ADMIN = "sessionAdministrateur";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On vérifie qu'un admin est connecté, sinon on le redirige
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){
			String fileName = request.getParameter("filename");
			
			//Si le nom de fichier est null ou vide on lance une erreur
		    if(fileName == null || fileName.equals(""))
		    {
		        throw new ServletException("File Name can't be null or empty");
		    }
			
		    String filePath = DIR_REMBOURSEMENT + fileName;
		    
		    File file = new File(filePath);
		    //On vérifie si le fichier existe
		    if(!file.exists())
		    {
		        throw new ServletException("File doesn't exists on server.");
		    }
		    
		    response.setContentType("APPLICATION/OCTET-STREAM");
		    response.setHeader("Content-Disposition","attachment; filename=\"" + fileName + "\""); 

		    java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filePath);
		    
		    //On envoie le fichier
		    int i; 
		    while ((i=fileInputStream.read()) != -1) 
		    {
		         response.getWriter().write(i); 
		    } 
		    fileInputStream.close();
		    
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
