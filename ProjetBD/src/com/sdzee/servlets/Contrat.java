package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.ValueSuffix;

import com.sdzee.beans.AdhesionDetail;
import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.AdhesionDetailDAOImpl;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;

public class Contrat extends HttpServlet {
   
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String BENEFICIAIRE = "benef";
	public static final String DETAILS_CONTRAT = "contrat";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//On vérifie qu'un utilisateur est connecté, sinon on le redirigie
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			

			HttpSession session = request.getSession();
			Beneficiaire benef = new Beneficiaire();		
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
			request.setAttribute(BENEFICIAIRE, benef);
			
			AdhesionDetailDAOImpl adhDetailDaoImpl = new AdhesionDetailDAOImpl(DAOFactory.getInstance());
			
			HashMap<Beneficiaire,AdhesionDetail> contrats = null;
			try {
				contrats = adhDetailDaoImpl.trouverAllContratsParNumBeneficiares(benef.getNum());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			request.setAttribute(DETAILS_CONTRAT, contrats);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Contrat.jsp" ).forward( request, response );
			
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
	}
	
	
	
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
	}

}