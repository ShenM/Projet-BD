package com.sdzee.beans;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.UtilisateurDAOImpl;

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

    public Beneficiaire authentifierBeneficiaire( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String id = getValeurChamp( request, CHAMP_ID );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String motDePasseBD = null;
        
        Beneficiaire beneficiaire = new Beneficiaire();

        try {
        	validationIdentifiant( id );
        } catch ( Exception e ) {
            setErreur( CHAMP_ID, e.getMessage() );
            resultat = "Échec de la connexion.";
            return beneficiaire;
        }
        
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            resultat = "Échec de la connexion.";
            return beneficiaire;
        }
        
    	UtilisateurDAOImpl utilisateur = new UtilisateurDAOImpl(DAOFactory.getInstance());
    	
        try {
        	motDePasseBD= utilisateur.recupererMotDePasse(Integer.parseInt(id));
        } catch ( Exception e ){
        	setErreur( CHAMP_ID, "Identifiant incorrect." );
        	resultat = "Échec de la connexion.";
            return beneficiaire;
        }
        
        if(motDePasseBD=="" || motDePasseBD==null){
        	setErreur( CHAMP_ID, "Identifiant incorrect." );
        	resultat = "Échec de la connexion.";
            return beneficiaire;
        }
        
        try{
        	validationPairMdp(motDePasse, motDePasseBD);
        } catch (Exception e){
        	setErreur( CHAMP_PASS, e.getMessage() );
        	resultat = "Échec de la connexion.";
            return beneficiaire;
        }
        
        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }
        
        //TODO récupérer un benef/facultatif
        
        return beneficiaire;
    }
    
    private void validationPairMdp( String motDePasse, String motDePasseBD) throws Exception {
    	if ( motDePasse.compareTo(motDePasseBD) != 0 ){
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
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
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
