package com.sdzee.servlets;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sdzee.beans.Beneficiaire;


public class Demande extends HttpServlet {
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String BENEFICIAIRE = "benef";
       

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			

			HttpSession session = request.getSession();
			Beneficiaire benef = new Beneficiaire();		
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
			request.setAttribute(BENEFICIAIRE, benef);

			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Demande.jsp" ).forward( request, response );
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
	}
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			
			String error = "Votre demande a été effectué !";
			String errorColor = "green";
			
			HttpSession session = request.getSession();
			
			if(request.getParameter("benef").toString().compareTo("") == 0 ||
					request.getParameter("acteId").toString().trim().compareTo("") == 0 ||
					request.getParameter("acteDesign").toString().trim().compareTo("") == 0 ||
					request.getParameter("acteLib").toString().trim().compareTo("") == 0 ||
					request.getParameter("acteDateDebutSoins").toString().trim().compareTo("") == 0 ||
					request.getParameter("fraisDatePaiement").toString().trim().compareTo("") == 0 ||
					request.getParameter("fraisReels").toString().trim().compareTo("") == 0 || 					
					request.getParameter("fraisFile").toString().trim().compareTo("") == 0
					)
			{
				error = "Remplissez tout les champs !";
				errorColor = "red";
			}else if(request.getParameter("fraisReels").toString().trim().compareTo("0") == 0 ){
				error = "Les frais ne peuvent pas être égale à 0 !";
				errorColor = "red";
			}else{
	               	DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(5000);
					factory.setRepository(new File("/tmp"));
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setSizeMax(500000);
	

			}
			
			
			request.setAttribute("error", error);
			request.setAttribute("errorColor", errorColor);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Demande.jsp" ).forward( request, response );
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
	}

}