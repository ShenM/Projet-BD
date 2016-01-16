package com.sdzee.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.DemandeRemboursement;
import com.sdzee.beans.DemandeRemboursementFlagEtat;
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
    private static final String ATT_ERR = "error";
    private static final String ATT_ERR_COL = "errorColor";
    private static final String ADMIN = "admin";

    public static final SimpleDateFormat formatterForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_ADMIN)!=null){	
			HttpSession session = request.getSession();
			
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
			
			request.setAttribute(ADMIN, session.getAttribute(ATT_SESSION_ADMIN));
			request.setAttribute(ATT_ERR, "");
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
			String error = "Erreur";
			String errorColor = "red";
			try {
				if(request.getParameter("action").equals("TraitementRemboursementValide")){
					String benefId = request.getParameter("id");
					String dateCreation = request.getParameter("dateC");
					
					DemandeRemboursementDAO ddeRembDAO = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());

					ddeRembDAO.updateFlagTraite(Integer.parseInt(benefId), formatterForm.parse(dateCreation), DemandeRemboursementFlagEtat.VALIDE);
					
					error = "La demande a été validé !";
					errorColor = "green";
					
				}else if(request.getParameter("action").equals("TraitementRemboursementRejete")){
					String benefId = request.getParameter("id");
					String dateCreation = request.getParameter("dateC");
					String motifRejet = "";
					motifRejet = request.getParameter("motifRejet");
					DemandeRemboursementDAO ddeRembDAO = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());

					ddeRembDAO.updateFlagTraiteRejet(Integer.parseInt(benefId), formatterForm.parse(dateCreation), DemandeRemboursementFlagEtat.REFUS, motifRejet);
					
					error = "La demande a été rejeté !";
					errorColor = "red";
								
				}
			} catch (NumberFormatException | DAOException | ParseException e) {
				System.out.println(e.getMessage());
				error = "Le traitement a échoué";
				errorColor = "red";
			}
			request.setAttribute(ATT_ERR, error);
			request.setAttribute(ATT_ERR_COL, errorColor);
			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/TraitementRemboursementAdmin.jsp" ).forward( request, response );
			
		}else {
			response.sendRedirect("/ProjetBD/AuthentificationAdmin");
		}
	}

	
}
