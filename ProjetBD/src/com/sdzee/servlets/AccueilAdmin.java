package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccueilAdmin extends HttpServlet{
    public static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    public static final String ADMIN = "admin";

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){	
			HttpSession session = request.getSession();
			request.setAttribute(ADMIN, session.getAttribute(ATT_SESSION_ADMIN));
			this.getServletContext().getRequestDispatcher( "/WEB-INF/AccueilAdmin.jsp" ).forward( request, response );	
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
		
		
	}
}
