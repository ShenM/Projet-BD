package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.ChartFraisAnnee;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.AdhesionDetailDAO;
import com.sdzee.dao.AdhesionDetailDAOImpl;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;

public class AdhesionDetail extends HttpServlet {
    public static final String LISTE_PRESTA = "lpresta";
    public static final String LISTE_BENEF = "lpresta";
    public static final String BENEFICIAIRE = "benef";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String CHART = "chart";

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			
			HttpSession session = request.getSession();			
			Beneficiaire benef = new Beneficiaire();		
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
			
			AdhesionDetailDAOImpl adhesionDetailDAO = new AdhesionDetailDAOImpl(DAOFactory.getInstance());
			
			
			//A modifier le try catch & a compléter
			try {
				HashMap<Beneficiaire,com.sdzee.beans.AdhesionDetail> listeBenef = adhesionDetailDAO.trouverParNumBeneficiaresContrat(benef.getNum(),2015);
				request.setAttribute(LISTE_BENEF, listeBenef);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute(BENEFICIAIRE, benef);


			this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
			
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
		
		
	}
}