package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.sdzee.beans.AuthentificationBean;
import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.DemandeRemboursement;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DemandeRemboursementDAOImpl;
import com.sdzee.dao.PrestationsSanteDAOImpl;


public class TousRemboursementsAdmin extends HttpServlet{
    public static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    public static final String LISTE_REMB = "lremb";

    

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){
			
			HttpSession session = request.getSession();
			

			DemandeRemboursementDAOImpl demandeRemboursmentImpl = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());
			List<DemandeRemboursement> demandeRemb = demandeRemboursmentImpl.getDemandesNonTraite();
			
			request.setAttribute(LISTE_REMB, demandeRemb);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/TousRemboursementsAdmin.jsp" ).forward( request, response );

			
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
		
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( "/WEB-INF/TousRemboursementsAdmin.jsp" ).forward( request, response );

	}

	
}
