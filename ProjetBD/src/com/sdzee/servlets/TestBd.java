package com.sdzee.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.dao.BeneficiaireDAO;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOFactory;

/**
 * Servlet implementation class TestBd
 */
public class TestBd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String ATT_BENEFICIAIRE = "beneficiaire";
    	
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
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/TestBd.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
