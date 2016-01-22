package com.sdzee.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.RepaintManager;

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

    public static final SimpleDateFormat formatterForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");

    
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
				if(request.getParameter("action").equals("Valider")){
					String benefId = request.getParameter("id");
					String dateCreation = request.getParameter("dateC");
					String email = request.getParameter("email");
					
					DemandeRemboursementDAO ddeRembDAO = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());

					ddeRembDAO.updateFlagTraite(Integer.parseInt(benefId), formatterForm.parse(dateCreation), DemandeRemboursementFlagEtat.VALIDE);
					
					error = "La demande a été validée !";
					errorColor = "green";
					envoiEmail(true, dateCreation,null,email);
					
				}else if(request.getParameter("action").equals("Rejeter")){
					String benefId = request.getParameter("id");
					String dateCreation = request.getParameter("dateC");
					String email = request.getParameter("email");
					String motifRejet = "";
					motifRejet = request.getParameter("motifRejet");
					DemandeRemboursementDAO ddeRembDAO = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());
					System.out.println(dateCreation);
					ddeRembDAO.updateFlagTraiteRejet(Integer.parseInt(benefId), formatterForm.parse(dateCreation), DemandeRemboursementFlagEtat.REFUS, motifRejet);
					
					error = "La demande a été rejetée !";
					errorColor = "red";
					envoiEmail(false, dateCreation,motifRejet,email);
								
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
    
    public void envoiEmail(boolean accepte, String datc, String motif, String email){
    	
    	//Identifiant gmail
		final String username = "poly.mutuelle@gmail.com";
		final String password = "#Tototata69";
		
		// Utilisation du smtp de google
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		//Authentification
		Session httpSession = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			String rep = "refusée";
			if (accepte){
	    		rep="acceptée";
	    	}
			//définition du message, destinataire
			Message message = new MimeMessage(httpSession);
			message.setFrom(new InternetAddress("poly.mutuelle@gmail.com"));
			System.out.println(email);
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject(rep.toUpperCase()+" : Demande de remboursement");
			if(motif==null){
			message.setText("Bonjour,"
				+ "\n\nVotre demande de remboursement du "+datc+" a été "+rep+".\n\n"
				+ "Cordialement,\n\nL'équipe Poly Mutuelle.");
			//Envoi
			}else{
				message.setText("Bonjour,"
						+ "\n\nVotre demande de remboursement du "+datc+" a été "+rep+".\n"
						+ motif.toUpperCase()+".\n\n"
						+ "Cordialement,\n\nL'équipe Poly Mutuelle.");
			}
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }

	
}

