package com.sdzee.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.dao.BeneficiaireDAO;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOFactory;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.PrestationsSanteDAO;
import com.sdzee.dao.PrestationsSanteDAOImpl;

/**
 * Servlet implementation class TestBd
 */
public class TestBd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String ATT_BENEFICIAIRE = "beneficiaire";
    public static final String LISTE_PRESTA = "lpresta";

    	
    public TestBd() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = null;
		Beneficiaire benef = new Beneficiaire();
		BeneficiaireDAOImpl benefDAOImpl = new BeneficiaireDAOImpl(DAOFactory.getInstance());
		benef = benefDAOImpl.trouver(2484);
		request.setAttribute(ATT_BENEFICIAIRE, benef);
		
		PrestationsSanteDAOImpl prestaDAOImpl = new PrestationsSanteDAOImpl(DAOFactory.getInstance());
		
		ArrayList<PrestationsSante> prestaListe = prestaDAOImpl.trouverParNumBeneficiaireSinistre(2484);
		request.setAttribute(LISTE_PRESTA, prestaListe);
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/TestBd.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
