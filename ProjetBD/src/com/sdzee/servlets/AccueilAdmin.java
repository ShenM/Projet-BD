package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.ChartPrestaParSexe;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAO;
import com.sdzee.dao.PrestationsSanteDAOImpl;

public class AccueilAdmin extends HttpServlet{
    public static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    public static final String ADMIN = "admin";
    public static final String CHARTSEXE = "chartSexe";
    
    public PrestationsSanteDAO prestaDAO = new PrestationsSanteDAOImpl(DAOFactory.getInstance());

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){	
			
			HttpSession session = request.getSession();
			request.setAttribute(ADMIN, session.getAttribute(ATT_SESSION_ADMIN));
			
			ChartPrestaParSexe chartSexe = new ChartPrestaParSexe(prestaDAO.moyenneParSexe());
			
			request.setAttribute(CHARTSEXE, chartSexe);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/AccueilAdmin.jsp" ).forward( request, response );	
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
		
		
	}
}
