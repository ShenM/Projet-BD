package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
	}

}