package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.AdminChartRemboursement;
import com.sdzee.beans.ChartAdminBenef;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;


public class StatistiquesAdmin extends HttpServlet{
    private static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    private static final String ADMIN = "admin";
    private static final String REMBOURSEMENT = "remb";
    private static final String BENEFICIAIRES = "benefs";

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){	
			
			HttpSession session = request.getSession();

			request.setAttribute(ADMIN, session.getAttribute(ATT_SESSION_ADMIN));
			
			PrestationsSanteDAOImpl prestaDaoImpl = new PrestationsSanteDAOImpl(DAOFactory.getInstance());
			
			ArrayList<AdminChartRemboursement> remb = null;
			ArrayList<ChartAdminBenef> benefs = null;
			try {
				remb = prestaDaoImpl.trouverRemboursementsAdmin();
				benefs = prestaDaoImpl.trouverAdminChartBenef();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.setAttribute(REMBOURSEMENT, remb);
			request.setAttribute(BENEFICIAIRES, benefs);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/StatistiquesAdmin.jsp" ).forward( request, response );	
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
		
		
	}
}
