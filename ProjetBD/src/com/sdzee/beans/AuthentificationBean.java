package com.sdzee.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.AdministrateurDAOImpl;
import com.sdzee.dao.BeneficiaireDAOImpl;
import com.sdzee.dao.UtilisateurDAOImpl;
import com.sdzee.utilitaires.Md5;


public class AuthentificationBean {
	private static final String CHAMP_ID  = "id";
    private static final String CHAMP_PASS   = "motdepasse";

    private String              resultat;
    private Map<String, String> erreurs      = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Administrateur authentifierAdministrateur( HttpServletRequest request ) {
    	String id = getValeurChamp( request, CHAMP_ID );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String motDePasseBD = null;
        
        AdministrateurDAOImpl adminDAO = new AdministrateurDAOImpl(DAOFactory.getInstance());
        Administrateur admin = new Administrateur();
        admin.setId(id);
        
        try {
        	validationIdentifiant( id );
        } catch ( Exception e ) {
            setErreur( CHAMP_ID, e.getMessage() );
            resultat = "Echec de la connexion.";
            return admin;
        }
        
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            resultat = "Echec de la connexion.";
            return admin;
        }
        
        try {
        	motDePasseBD= adminDAO.recupererMotDePasse(id);
        } catch ( Exception e ){
        	setErreur( CHAMP_ID, "Identifiant incorrect." );
        	resultat = "Echec de la connexion.";
            return admin;
        }
        
        if(motDePasseBD=="" || motDePasseBD==null){
        	setErreur( CHAMP_ID, "Identifiant incorrect." );
        	resultat = "Echec de la connexion.";
            return admin;
        }
        
        try{
        	validationPairMdp(motDePasse, motDePasseBD);
        } catch (Exception e){
        	setErreur( CHAMP_PASS, e.getMessage() );
        	resultat = "Echec de la connexion.";
            return admin;
        }
        
        /* Initialisation du resultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succes de la connexion.";
        } else {
            resultat = "Echec de la connexion.";
        }
        
    	return admin;
    }
    
    public Beneficiaire authentifierBeneficiaire( HttpServletRequest request ) {
        /* R�cup�ration des champs du formulaire */
        String id = getValeurChamp( request, CHAMP_ID );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String motDePasseBD = null;
        
        BeneficiaireDAOImpl beneDAO = new BeneficiaireDAOImpl(DAOFactory.getInstance());
        Beneficiaire beneficiaire = new Beneficiaire();

        try {
        	validationIdentifiant( id );
        } catch ( Exception e ) {
            setErreur( CHAMP_ID, e.getMessage() );
            resultat = "Echec de la connexion.";
            return beneficiaire;
        }
        
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            resultat = "Echec de la connexion.";
            return beneficiaire;
        }
        
    	UtilisateurDAOImpl utilisateur = new UtilisateurDAOImpl(DAOFactory.getInstance());
    	
        try {
        	motDePasseBD= utilisateur.recupererMotDePasse(Integer.parseInt(id));
        } catch ( Exception e ){
        	setErreur( CHAMP_ID, "Identifiant incorrect." );
        	resultat = "Echec de la connexion.";
            return beneficiaire;
        }
        
        if(motDePasseBD=="" || motDePasseBD==null){
        	setErreur( CHAMP_ID, "Identifiant incorrect." );
        	resultat = "Echec de la connexion.";
            return beneficiaire;
        }
        
        try{
        	validationPairMdp(motDePasse, motDePasseBD);
        } catch (Exception e){
        	setErreur( CHAMP_PASS, e.getMessage() );
        	resultat = "Echec de la connexion.";
            return beneficiaire;
        }
        
        /* Initialisation du resultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succes de la connexion.";
        } else {
            resultat = "Echec de la connexion.";
        }
                
        beneficiaire = beneDAO.trouver(Integer.parseInt(id));
        
        return beneficiaire;
    }
    
    private void validationPairMdp( String motDePasse, String motDePasseBD) throws Exception {
    	Md5 hash = new Md5();
    	String MdpHashed = hash.encode(motDePasse);
    	if ( MdpHashed.compareTo(motDePasseBD) != 0 ){
    		throw new Exception( "Mot de passe incorrect !" );
    	}
    }

    private void validationIdentifiant( String id ) throws Exception {
        if ( id == null ) {
            throw new Exception( "Merci de saisir votre identifiant." );
        }
    }
    
    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caract�res." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ specifiee la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {

        String valeur = request.getParameter( nomChamp );

        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
