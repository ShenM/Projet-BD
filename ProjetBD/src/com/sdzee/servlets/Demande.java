package com.sdzee.servlets;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;







import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.DemandeRemboursement;
import com.sdzee.dao.AdhesionDetailDAO;
import com.sdzee.dao.AdhesionDetailDAOImpl;
import com.sdzee.dao.BeneficiaireDAO;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DemandeRemboursementDAO;
import com.sdzee.dao.DemandeRemboursementDAOImpl;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



/**
 * Servlet permettant la gestion des demande de remboursements
 *
 */
public class Demande extends HttpServlet {
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String BENEFICIAIRE = "benef";
    public static final SimpleDateFormat formatterFile = new SimpleDateFormat("ddMMyyyyHHmmss");
    public static final SimpleDateFormat formatterForm = new SimpleDateFormat("dd-MM-yyyy");
    public static final String DIR_REMBOURSEMENT = "C:\\ProjetBD_FichiersRemboursements\\";
    public static final String ATT_LIST_BENEF = "lBenef";
    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		//On vérifie qu'un utilisateur est connecté, sinon on le redirige
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			
			HttpSession session = request.getSession();
			Beneficiaire benef = new Beneficiaire();		
			
			//On récupères les information du bénéficiaire à partir de la session et les renvoies à la JSP
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
			
			AdhesionDetailDAO adhDAO = new AdhesionDetailDAOImpl(DAOFactory.getInstance());
			
			List<Beneficiaire> lBenef = adhDAO.trouverBenefsMemeContrat(benef.getNum());
			
			
			request.setAttribute(ATT_LIST_BENEF, lBenef);
			request.setAttribute(BENEFICIAIRE, benef);

			
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Demande.jsp" ).forward( request, response );
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
	}
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			
			HttpSession session = request.getSession();
			Beneficiaire benef = new Beneficiaire();		
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
			request.setAttribute(BENEFICIAIRE, benef);
			
			//On initialise nos variables de gestions d'erreur
			String error = "Votre demande a été effectuée !";
			String errorColor = "green";
			
			//On initialise nos variables de gestions de formulaire Encrypté
		    HashMap<String, String> params = new HashMap<String, String>();  
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
			boolean flagFile = false; 
			FileItem itemFinal = null;
			File uploadedFile = null;
			
			try{  
				if (isMultipart){  
					
					DiskFileItemFactory factory = new DiskFileItemFactory();
					//factory.setSizeThreshold(5000);
					//factory.setRepository(new File(getServletContext().getRealPath("/WebContent/Remboursements-Files")));
					ServletFileUpload upload = new ServletFileUpload(factory);
					//upload.setSizeMax(500000);
				    List<FileItem> items = null;

				    //On parse notre requete en liste d'objet
					items = upload.parseRequest(request);

					/*Pour chaque occurence de cette liste, on vérifie si c'est un champs ou un fichier, selon le cas soit on l'ajoute
					* à notre HashMap, soit on l'ajoute à notre HashMap ET on stock l'item contenant le file
					*/
					Iterator<FileItem> iter = items.iterator();  
				    while (iter.hasNext()) {  
			    		FileItem item = (FileItem) iter.next();  
		    			if (item.isFormField()) {  		    	           
			    			 params.put(item.getFieldName(), item.getString());  

		    		 	} else {   				           
			    			params.put(item.getFieldName(), item.getName());  

		    	        	itemFinal = item;
			    		}  
				    }
			    }  
					

				//On vérifie que tout les champs sont remplis 
			 	if(params.get("benef").trim().compareTo("") == 0||
					params.get("acteId").trim().compareTo("") == 0 ||
					params.get("acteDesign").trim().compareTo("") == 0 ||
					params.get("acteLib").trim().compareTo("") == 0 ||
					params.get("acteDateDebutSoins").trim().compareTo("") == 0 ||
					params.get("fraisDatePaiement").trim().compareTo("") == 0 ||
					params.get("fraisReels").trim().compareTo("") == 0)
				{
			 		error = "Merci de remplir tous les champs !";
					errorColor = "red";
				}else if(params.get("fraisFile").trim().compareTo("") == 0){
					error = "Merci de joindre un fichier !";
					errorColor = "red";
				}else if(params.get("fraisReels").trim().compareTo("0") == 0){
					error = "Les frais ne peuvent pas être égaux à 0 !";
					errorColor = "red";
				}else{		  
					String name = params.get("fraisFile");
					String[] tmpName = name.split("\\.");
					
					//On vérifie le format du fichier
					if(!(tmpName[tmpName.length-1].toLowerCase().compareTo("png")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("jpg")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("jpeg")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("bmp")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("pdf")==0)){
						error = "Merci d'utiliser un format autorisé : pdf, png, jpg, jpeg, bmp";
						errorColor = "red";
					}else{
						java.util.Date dateSys = new java.util.Date();
						
						String fileName = params.get("benef")+formatterFile.format(dateSys)+"."+tmpName[tmpName.length-1];
						
						//On vérifie si le dossier de stockage d'image sur le serveur existe, sinon on le crée. 
						File directory = new File("C:\\ProjetBD_FichiersRemboursements\\");
						if(!directory.exists()){
							directory.mkdirs();
						}
						
						//On écrit notre fichier sur le serveur avec comme non une association des clés primaires composées : ID et DateCreation
						uploadedFile = new File("C:\\ProjetBD_FichiersRemboursements"+File.separator+fileName);  
						itemFinal.write(uploadedFile);
						
						//On crée le bean à insérer en base
						DemandeRemboursement remboursement =  new DemandeRemboursement(
								Integer.parseInt(params.get("benef").trim()), 
								params.get("acteId").trim(), params.get("acteDesign").trim(), 
								params.get("acteLib").trim(), 
								formatterForm.parse(params.get("acteDateDebutSoins").trim()), 
								formatterForm.parse(params.get("fraisDatePaiement").trim()), 
								Float.parseFloat(params.get("fraisReels").trim()), 
								fileName,
								dateSys);
						
						DemandeRemboursementDAO rembDAO = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());	
						
						//On insert la demande en base
						rembDAO.insert(remboursement);
						
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
							//définition du message, destinataire
							Message message = new MimeMessage(httpSession);
							message.setFrom(new InternetAddress("poly.mutuelle@gmail.com"));
							message.setRecipients(Message.RecipientType.TO,
								InternetAddress.parse(benef.getEmail()));
							message.setSubject("Demande de remboursement");
							message.setText(benef.getPrenom()+" " +benef.getNom()+","
								+ "\n\nVotre demande de remboursement a bien été effectuée.\n"
								+remboursement.getActeLibelle() + " : "+remboursement.getFraisReels()+ "€ \n"
								+ "Nous traitons actuellement votre demande, vous serez notifié de notre décision sous peu.\n\n"
								+ "Cordialement,\n\nL'équipe Poly Mutuelle.");
							//Envvoi
							Transport.send(message);

							System.out.println("Done");

						} catch (MessagingException e) {
							throw new RuntimeException(e);
						}
					}
					

				}
		 	
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			request.setAttribute("error", error);
			request.setAttribute("errorColor", errorColor);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/Demande.jsp" ).forward( request, response );
			
		}else {
			response.sendRedirect("/ProjetBD/Authentification");
		}
	}

}