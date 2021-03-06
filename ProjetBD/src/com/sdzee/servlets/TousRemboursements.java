package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;


public class TousRemboursements extends HttpServlet{
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String LISTE_PRESTA = "lpresta";
    public static final String BENEFICIAIRE = "benef";
    

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//On vérifie qu'un utilisateur est connecté, sinon on le redirige
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			
			HttpSession session = request.getSession();
			
			PrestationsSanteDAOImpl prestaDAOImpl = new PrestationsSanteDAOImpl(DAOFactory.getInstance());

			Beneficiaire benef = new Beneficiaire();		
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
			ArrayList<PrestationsSante> prestaListe = prestaDAOImpl.trouverParNumAdhesion(benef.getNum());
			
			request.setAttribute(LISTE_PRESTA, prestaListe);
			request.setAttribute(BENEFICIAIRE, benef);
			

			this.getServletContext().getRequestDispatcher( "/WEB-INF/TousRemboursements.jsp" ).forward( request, response );

			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
		
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
	}

	
}
