package com.sdzee.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.DemandeRemboursement;
import com.sdzee.beans.AdhesionDetail;
import com.sdzee.dao.AdhesionDetailDAO;
import com.sdzee.dao.AdhesionDetailDAOImpl;
import com.sdzee.dao.BeneficiaireDAO;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DemandeRemboursementDAO;
import com.sdzee.dao.DemandeRemboursementDAOImpl;

public class TraitementRemboursementAdmin extends HttpServlet{
    private static final String ATT_SESSION_ADMIN = "sessionAdministrateur";
    private static final String ATT_BENEF = "benef";
    private static final String ATT_ADHESION = "adhesion";
    private static final String ATT_DDE_REMB = "demande";

    public static final SimpleDateFormat formatterForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){	
			
			String benefId = request.getParameter("id");
			String dateCreation = request.getParameter("dateC");
			
			DemandeRemboursementDAO ddeRembDAO = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());
			BeneficiaireDAO benefDAO = new BeneficiaireDAOImpl(DAOFactory.getInstance());
			AdhesionDetailDAO adhDAO = new AdhesionDetailDAOImpl(DAOFactory.getInstance());
			DemandeRemboursement ddRembBean = new DemandeRemboursement();
			Beneficiaire benefBean = new Beneficiaire();
			AdhesionDetail adhBean = new AdhesionDetail();
			
			try {
				ddRembBean = ddeRembDAO.get(Integer.parseInt(benefId), formatterForm.parse(dateCreation));
			} catch (NumberFormatException | DAOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			benefBean = benefDAO.trouver(Integer.parseInt(benefId));
			adhBean = adhDAO.trouverLastContratParNumBenef(Integer.parseInt(benefId));
			
			request.setAttribute(ATT_BENEF, benefBean);
			request.setAttribute(ATT_ADHESION, adhBean);
			request.setAttribute(ATT_DDE_REMB, ddRembBean);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/TraitementRemboursementAdmin.jsp" ).forward( request, response );

			
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
		
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){
			
			
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
	}

	
}
