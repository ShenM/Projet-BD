package com.sdzee.servlets;

import java.io.File;
import java.io.IOException;
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
import com.sdzee.beans.DemandeRemboursementBean;
import com.sdzee.dao.DAOException;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.DemandeRemboursementDAO;
import com.sdzee.dao.DemandeRemboursementDAOImpl;


public class Demande extends HttpServlet {
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String BENEFICIAIRE = "benef";
    public static final SimpleDateFormat formatterFile = new SimpleDateFormat("ddMMyyyyHHmmss");
    public static final SimpleDateFormat formatterForm = new SimpleDateFormat("dd-MM-yyyy");

    
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if (request.getSession()!=null && request.getSession().getAttribute(ATT_SESSION_USER)!=null){
			
			HttpSession session = request.getSession();
			Beneficiaire benef = new Beneficiaire();		
			benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
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
			
			String error = "Votre demande a été effectué !";
			String errorColor = "green";
			
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

					items = upload.parseRequest(request);

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
					

			
			 	if(params.get("benef").trim().compareTo("") == 0||
					params.get("acteId").trim().compareTo("") == 0 ||
					params.get("acteDesign").trim().compareTo("") == 0 ||
					params.get("acteLib").trim().compareTo("") == 0 ||
					params.get("acteDateDebutSoins").trim().compareTo("") == 0 ||
					params.get("fraisDatePaiement").trim().compareTo("") == 0 ||
					params.get("fraisReels").trim().compareTo("") == 0)
				{
			 		error = "Merci de remplir tout les champs !";
					errorColor = "red";
				}else if(params.get("fraisFile").trim().compareTo("") == 0){
					error = "Merci de joindre un fichier !";
					errorColor = "red";
				}else if(params.get("fraisReels").trim().compareTo("0") == 0){
					error = "Les frais ne peuvent pas être égale à 0 !";
					errorColor = "red";
				}else{		  
					String name = params.get("fraisFile");
					String[] tmpName = name.split("\\.");
					
					if(!(tmpName[tmpName.length-1].toLowerCase().compareTo("png")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("jpg")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("jpeg")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("bmp")==0) && !(tmpName[tmpName.length-1].toLowerCase().compareTo("pdf")==0)){
						error = "Merci d'utiliser un format autorisé : pdf, png, jpg, jpeg, bmp";
						errorColor = "red";
					}else{
						java.util.Date dateSys = new java.util.Date();
						
						String fileName = params.get("benef")+formatterFile.format(dateSys)+"."+tmpName[tmpName.length-1];
						
						File directory = new File("C:\\ProjetBD_FichiersRemboursements\\");
						if(!directory.exists()){
							directory.mkdirs();
						}
						
						uploadedFile = new File("C:\\ProjetBD_FichiersRemboursements"+File.separator+fileName);  
						itemFinal.write(uploadedFile);
						
						DemandeRemboursementBean remboursement =  new DemandeRemboursementBean(
								Integer.parseInt(params.get("benef").trim()), 
								params.get("acteId").trim(), params.get("acteDesign").trim(), 
								params.get("acteLib").trim(), 
								formatterForm.parse(params.get("acteDateDebutSoins").trim()), 
								formatterForm.parse(params.get("fraisDatePaiement").trim()), 
								Float.parseFloat(params.get("fraisReels").trim()), 
								"C:\\ProjetBD_FichiersRemboursements"+File.separator+fileName,
								dateSys);
						
						DemandeRemboursementDAO rembDAO = new DemandeRemboursementDAOImpl(DAOFactory.getInstance());						
						
						rembDAO.insert(remboursement);
					
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