package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Administrateur;
import com.sdzee.beans.AuthentificationBean;

public class AuthAdmin extends HttpServlet {
	public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    public static final String ATT_ID = "id";
    public static final String ATT_ERREUR = "erreur"; 
	
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( "/WEB-INF/AuthentificationAdmin.jsp" ).forward( request, response );
    }
    
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AuthentificationBean auth = new AuthentificationBean();

        Administrateur admin = auth.authentifierAdministrateur( request );
        
        HttpSession session = request.getSession();

        if ( auth.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_ADMIN, admin );
            response.sendRedirect("/ProjetBD/StatistiquesAdmin");

        } else {
            session.setAttribute( ATT_SESSION_ADMIN, null );
            request.setAttribute(ATT_ERREUR, auth.getErreurs());
    		this.getServletContext().getRequestDispatcher( "/WEB-INF/AuthentificationAdmin.jsp" ).forward( request, response );
        }

        request.setAttribute( ATT_ID, request.getParameter( "id" ));
        request.setAttribute( ATT_FORM, auth );

	}
}
