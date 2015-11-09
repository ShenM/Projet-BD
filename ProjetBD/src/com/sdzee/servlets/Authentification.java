package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.PrestationsSante;
import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;

public class Authentification extends HttpServlet { 
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Authentification.jsp" ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Authentification form = new Authentification();

        Utilisateur utilisateur = form.authentifierUtilisateur( request );

        HttpSession session = request.getSession();

        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

		this.getServletContext().getRequestDispatcher( "/WEB-INF/Authentification.jsp" ).forward( request, response );
	}

	private String getErreurs() {
		// TODO Auto-generated method stub
		return null;
	}

	private Utilisateur authentifierUtilisateur(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}