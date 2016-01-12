package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.AdhesionDetail;
import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.ChartFraisByBenef;
import com.sdzee.beans.ChartFraisByDate;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.AdhesionDetailDAOImpl;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;

/**
 * Servlet implementation class StatistiquesUser
 */
public class StatistiquesUser extends HttpServlet {
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String BENEFICIAIRE = "benef";
	public static final String LISTE_FRAIS = "frais";
	public static final String LISTE_FRAIS_DATE = "frais_date";
       
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			

			HttpSession session = request.getSession();
			Beneficiaire benef = new Beneficiaire();		
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
			request.setAttribute(BENEFICIAIRE, benef);
		
			PrestationsSanteDAOImpl prestaDaoImpl = new PrestationsSanteDAOImpl(DAOFactory.getInstance());
			HashMap<String,ChartFraisByBenef> fraisBenef = null;
			
			try {
				fraisBenef = prestaDaoImpl.trouverFraisParNumBeneficiaire(benef.getNum());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<ChartFraisByDate> fraisDate = null;
			try {
				fraisDate = prestaDaoImpl.trouverFraisParNumBeneficiaireDate(benef.getNum());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.setAttribute(LISTE_FRAIS, fraisBenef);
			request.setAttribute(LISTE_FRAIS_DATE, fraisDate);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/StatistiquesUser.jsp" ).forward( request, response );
			
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
	}
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
	}

}