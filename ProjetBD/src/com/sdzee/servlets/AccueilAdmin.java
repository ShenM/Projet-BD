package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.ChartPrestaParSexe;
import com.sdzee.beans.ChartRegions;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAO;
import com.sdzee.dao.PrestationsSanteDAOImpl;
import com.sdzee.dao.RegionsDAO;
import com.sdzee.dao.RegionsDAOImpl;

public class AccueilAdmin extends HttpServlet{
    private static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    private static final String ADMIN = "admin";
    private static final String CHARTSEXE = "chartSexe";
    private static final String CHARTREGIONS = "chartRegions";

    private PrestationsSanteDAO prestaDAO = new PrestationsSanteDAOImpl(DAOFactory.getInstance());
    private RegionsDAO regDAO = new RegionsDAOImpl(DAOFactory.getInstance());
    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//On vérifie qu'un admin est connecté, sinon on le redirige
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){	
			
			HttpSession session = request.getSession();
			
			ChartPrestaParSexe chartSexe = new ChartPrestaParSexe(prestaDAO.moyenneParSexe());
			ChartRegions chartRegions = new ChartRegions(regDAO.getTopThree());
			
			
			request.setAttribute(ADMIN, session.getAttribute(ATT_SESSION_ADMIN));
			request.setAttribute(CHARTSEXE, chartSexe);
			request.setAttribute(CHARTREGIONS, chartRegions);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/AccueilAdmin.jsp" ).forward( request, response );	
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
		
		
	}
}
