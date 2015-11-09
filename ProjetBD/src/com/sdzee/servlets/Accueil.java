package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;

public class Accueil extends HttpServlet {
    public static final String LISTE_PRESTA = "lpresta";
    
    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{		
		PrestationsSanteDAOImpl prestaDAOImpl = new PrestationsSanteDAOImpl(DAOFactory.getInstance());
		
		ArrayList<PrestationsSante> prestaListe = prestaDAOImpl.trouverParNumBeneficiaireSinistre(2484);
		request.setAttribute(LISTE_PRESTA, prestaListe);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
	}
}