package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.AuthentificationBean;
import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;


public class Authentification extends HttpServlet { 
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_ID = "id";

	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Authentification.jsp" ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AuthentificationBean auth = new AuthentificationBean();

        Beneficiaire benef = auth.authentifierBeneficiaire( request );

        HttpSession session = request.getSession();
        if ( auth.getErreurs().isEmpty() ) {
        	
            session.setAttribute( ATT_SESSION_USER, benef );
            response.sendRedirect("/ProjetBD/Accueil");

        } else {
            session.setAttribute( ATT_SESSION_USER, null );
    		this.getServletContext().getRequestDispatcher( "/WEB-INF/Authentification.jsp" ).forward( request, response );
    		//this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
        }

        request.setAttribute( ATT_ID, request.getParameter( "id" ));
        request.setAttribute( ATT_FORM, auth );

	}

}