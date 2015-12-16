package com.sdzee.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.AuthentificationBean;
import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.PrestationsSante;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PrestationsSanteDAOImpl;
import com.sdzee.dao.UtilisateurDAOImpl;
import com.sdzee.utilitaires.Md5;


public class EditerProfile extends HttpServlet { 
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String ATT_ID = "id";

	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( "/WEB-INF/InformationsBeneficiare.jsp" ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    	
		HttpSession session = request.getSession();

    	Beneficiaire benef = new Beneficiaire();		
		benef = (Beneficiaire) session.getAttribute(ATT_SESSION_USER);
	
		BeneficiaireDAOImpl benedao = new BeneficiaireDAOImpl(DAOFactory.getInstance());
		UtilisateurDAOImpl utilisdao =  new UtilisateurDAOImpl(DAOFactory.getInstance());
		
		String valeur = request.getParameter("num");
		String success = "";
        if(valeur != null && valeur.trim().length() <11 && valeur.trim().length()>0 ) {
    		benef.setNumTelephone(valeur);
        }
        else{
        	success+=" Problème d'édition du numéro de téléphone";
        }
        
        
        valeur = request.getParameter("email");
        if(!(valeur == null) || !(valeur.trim().length() == 0 )) {
    		benef.setEmail(valeur);
        }
        else{
        	success+=" Problème d'édition de l'adresse Email";
        }
        
        
        valeur = request.getParameter("password1");
        Md5 pass = new Md5();
        if(valeur != null && (valeur.trim().length() >= 8)) {
        		utilisdao.modifier(pass.encode(valeur), benef.getNum());
        }
        else{
        	if(valeur.trim().length()!=0)
        	success+=" Mot de passe vide ou trop court !";
        }
        
        if(success == ""){
        	success = "Modifications réussies !";
        }
		benedao.modifier(benef);
		request.setAttribute("success", success);
		request.setAttribute("benef", benef);

		

		this.getServletContext().getRequestDispatcher( "/WEB-INF/MesInformations.jsp" ).forward( request, response );


	}

}