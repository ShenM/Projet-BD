package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;

public class Accueil extends HttpServlet {
    public static final String LISTE_PRESTA = "lpresta";
    public static final String BENEFICIAIRE = "benef";
    
    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{		
		PrestationsSanteDAOImpl prestaDAOImpl = new PrestationsSanteDAOImpl(DAOFactory.getInstance());
		BeneficiaireDAOImpl benefiDAOImpl = new BeneficiaireDAOImpl(DAOFactory.getInstance());
		Beneficiaire benef = new Beneficiaire();
		
		benef = benefiDAOImpl.trouver(2484);
		ArrayList<PrestationsSante> prestaListe = prestaDAOImpl.trouverParNumBeneficiaireSinistre(2484);
		request.setAttribute(LISTE_PRESTA, prestaListe);
		request.setAttribute(BENEFICIAIRE, benef);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
	}
}