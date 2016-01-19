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
import com.sdzee.beans.ChartFormules;
import com.sdzee.dao.AdhesionDetailDAOImpl;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;


public class StatistiquesAdmin extends HttpServlet{
    private static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    private static final String ADMIN = "admin";
    private static final String REMBOURSEMENT = "remb";
    private static final String BENEFICIAIRES = "benefs";
    private static final String FORMULES = "formules";
    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//On vérifie qu'un admin est connecté, sinon on le redirige
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){	
			
			HttpSession session = request.getSession();

			request.setAttribute(ADMIN, session.getAttribute(ATT_SESSION_ADMIN));
			
			PrestationsSanteDAOImpl prestaDaoImpl = new PrestationsSanteDAOImpl(DAOFactory.getInstance());
			AdhesionDetailDAOImpl adhDaiImpl = new AdhesionDetailDAOImpl(DAOFactory.getInstance());
			
			ArrayList<AdminChartRemboursement> remb = null;
			ArrayList<ChartAdminBenef> benefs = null;
			ArrayList<ChartFormules> formules = null;
			
			try {
				remb = prestaDaoImpl.trouverRemboursementsAdmin();
				benefs = prestaDaoImpl.trouverAdminChartBenef();
				formules = adhDaiImpl.trouverAdminChartFormules();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute(REMBOURSEMENT, remb);
			request.setAttribute(BENEFICIAIRES, benefs);
			request.setAttribute(FORMULES, formules);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/StatistiquesAdmin.jsp" ).forward( request, response );	
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
	}
}
