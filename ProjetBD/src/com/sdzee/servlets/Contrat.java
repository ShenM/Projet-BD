package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.ChartFraisAnnee;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;

public class Contrat extends HttpServlet {
   
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			

			
			
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Contrat.jsp" ).forward( request, response );
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
		
		
	}
}