package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sdzee.beans.PrestationsSante;

public class PrestationsSanteDAOImpl implements PrestationsSanteDAO{
	private DAOFactory daoFactory;

	private static final String SQL_SELECT_PAR_NUMBENEFSINISTRE = "SELECT NUM_SINISTRE, NUM_ADHESION, NUM_BENEFICIAIRE_SINISTRE, NUM_BENEFICIAIRE, ACTE, DESIGNATION_ACTE, LIBELLE_BAREME, JOUR_DEBUT_SOINS, MOIS_DEBUT_SOINS, ANNEE_DEBUT_SOINS, JOUR_PAIEMENT, MOIS_PAIEMENT, ANNEE_PAIEMENT, FRAIS_REEL_ASSURE, MONTANT_SECU, MONTANT_REMBOURSE FROM PRESTATIONS_SANTE WHERE NUM_BENEFICIAIRE_SINISTRE = ? ORDER BY NUM_SINISTRE DESC";
	private static final String SQL_SELECT_PAR_NUM_ADHESION = "SELECT NOM, PRENOM, NUM_SINISTRE, NUM_ADHESION, NUM_BENEFICIAIRE_SINISTRE, NUM_BENEFICIAIRE, ACTE, DESIGNATION_ACTE, LIBELLE_BAREME, JOUR_DEBUT_SOINS, MOIS_DEBUT_SOINS, ANNEE_DEBUT_SOINS, JOUR_PAIEMENT, MOIS_PAIEMENT, ANNEE_PAIEMENT, FRAIS_REEL_ASSURE, MONTANT_SECU, MONTANT_REMBOURSE FROM PRESTATIONS_SANTE, BENEFICIAIRE WHERE NUM_ADHESION = (select distinct NUM_ADHESION from PRESTATIONS_SANTE where num_beneficiaire_sinistre = ?) and prestations_sante.num_beneficiaire_sinistre = beneficiaire.num ORDER BY ANNEE_PAIEMENT DESC, MOIS_PAIEMENT DESC, JOUR_PAIEMENT DESC";
	private static final String SQL_SELECT_PAR_NUM_ADHESION_WITH_LIMITE = "SELECT NOM, PRENOM, NUM_SINISTRE, NUM_ADHESION, NUM_BENEFICIAIRE_SINISTRE, NUM_BENEFICIAIRE, ACTE, DESIGNATION_ACTE, LIBELLE_BAREME, JOUR_DEBUT_SOINS, MOIS_DEBUT_SOINS, ANNEE_DEBUT_SOINS, JOUR_PAIEMENT, MOIS_PAIEMENT, ANNEE_PAIEMENT, FRAIS_REEL_ASSURE, MONTANT_SECU, MONTANT_REMBOURSE FROM PRESTATIONS_SANTE, BENEFICIAIRE WHERE NUM_ADHESION = (select distinct NUM_ADHESION from PRESTATIONS_SANTE where num_beneficiaire_sinistre = ?) and prestations_sante.num_beneficiaire_sinistre = beneficiaire.num and ROWNUM <=5 ORDER BY ANNEE_PAIEMENT DESC, MOIS_PAIEMENT DESC, JOUR_PAIEMENT DESC";
	private static final String SQL_SELECT_MOYENNE_SEXE = "SELECT cnt.s as SEXE, SUBSTR(AVG(cnt.c), 1, 5) as MOYENNE "
															+"FROM (SELECT b.NUM n, b.SEXE s, COUNT(1) c "
															        +"FROM BENEFICIAIRE b "
															          +"JOIN PRESTATIONS_SANTE p "
															          +"ON b.NUM = p.NUM_BENEFICIAIRE_SINISTRE "
															        +"GROUP BY b.NUM, b.SEXE) cnt "
															+"GROUP BY cnt.s"; 
	private static final String SQL_SELECT_CODE_PRO_PRESTA = "SELECT a.CODE_PROFESSION, p.NUM_SINISTRE, p.NUM_ADHESION, p.NUM_BENEFICIAIRE_SINISTRE, p.NUM_BENEFICIAIRE, p.ACTE, p.DESIGNATION_ACTE, p.LIBELLE_BAREME, p.JOUR_DEBUT_SOINS, p.MOIS_DEBUT_SOINS, p.ANNEE_DEBUT_SOINS, p.JOUR_PAIEMENT, p.MOIS_PAIEMENT, p.ANNEE_PAIEMENT, p.FRAIS_REEL_ASSURE, p.MONTANT_SECU, p.MONTANT_REMBOURSE "
																+"FROM PRESTATIONS_SANTE p " 
																  +"JOIN ADHESION_DETAIL a  "
																  +"ON p.NUM_ADHESION = a.NUM_ADHESION_NORMALISE";
	
	public PrestationsSanteDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public ArrayList<PrestationsSante> trouverParNumBeneficiaire(int numBeneficiaire) throws DAOException{
		ArrayList<PrestationsSante> prestaListe = new ArrayList<PrestationsSante>();
		
		return prestaListe;
	}
	public ArrayList<PrestationsSante> trouverParNumBeneficiaireSinistre(int numBeneficiaireSinistre) throws DAOException{
		ArrayList<PrestationsSante> prestaListe = new ArrayList<PrestationsSante>();
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NUMBENEFSINISTRE, false, numBeneficiaireSinistre );
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donn�es retourn�e dans le ResultSet */
            while ( resultSet.next() != false) {
                PrestationsSante presta = new PrestationsSante();
            	presta = map( resultSet );
            	prestaListe.add(presta);
            }
            
    		return prestaListe;
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + e.getMessage());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }	
	}
	
	public ArrayList<PrestationsSante> trouverParNumAdhesion(int numBeneficiaireSinistre) throws DAOException{
		ArrayList<PrestationsSante> prestaListe = new ArrayList<PrestationsSante>();
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NUM_ADHESION, false, numBeneficiaireSinistre );
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donn�es retourn�e dans le ResultSet */
            while ( resultSet.next() != false) {
                PrestationsSante presta = new PrestationsSante();
            	presta = map( resultSet );
            	prestaListe.add(presta);
            }
            
    		return prestaListe;
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + e.getMessage());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }	
	}
	
	public ArrayList<PrestationsSante> trouverParNumAdhesionLimite(int numBeneficiaireSinistre) throws DAOException{
		ArrayList<PrestationsSante> prestaListe = new ArrayList<PrestationsSante>();
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NUM_ADHESION_WITH_LIMITE, false, numBeneficiaireSinistre );
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donn�es retourn�e dans le ResultSet */
            while ( resultSet.next() != false) {
                PrestationsSante presta = new PrestationsSante();
            	presta = map( resultSet );
            	prestaListe.add(presta);
            }
            
    		return prestaListe;
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + e.getMessage());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }	
	}
	
	public PrestationsSante trouver() throws DAOException{
		PrestationsSante presta = new PrestationsSante();
		
		return presta;
	}
	
    private static PrestationsSante map( ResultSet resultSet ) throws SQLException {
    	PrestationsSante presta = new PrestationsSante();
        
    	presta.setNumSinistre(resultSet.getInt("NUM_SINISTRE"));
    	presta.setNumAdhesion(resultSet.getInt("NUM_ADHESION"));
    	presta.setNumBeneficiaireSinistre(resultSet.getInt("NUM_BENEFICIAIRE_SINISTRE"));
    	presta.setNumBeneficiaire(resultSet.getInt("NUM_BENEFICIAIRE"));
    	presta.setActe(resultSet.getString("ACTE"));
    	presta.setDesignationActe(resultSet.getString("DESIGNATION_ACTE"));
    	presta.setLibelleBareme(resultSet.getString("LIBELLE_BAREME"));
    	presta.setJourDebutSoins(resultSet.getString("JOUR_DEBUT_SOINS"));
    	presta.setMoisDebutSoins(resultSet.getString("MOIS_DEBUT_SOINS"));
    	presta.setAnneeDebutSoins(resultSet.getInt("ANNEE_DEBUT_SOINS"));
    	presta.setJourPaiement(resultSet.getString("JOUR_PAIEMENT"));
    	presta.setMoisPaiement(resultSet.getString("MOIS_PAIEMENT"));
    	presta.setAnneePaiement(resultSet.getInt("ANNEE_PAIEMENT"));
    	presta.setFraisReelAssure(resultSet.getFloat("FRAIS_REEL_ASSURE"));
    	presta.setMontantSecu(resultSet.getFloat("MONTANT_SECU"));
    	presta.setMontantRembourse(resultSet.getFloat("MONTANT_REMBOURSE"));
    	presta.setNomBenef(resultSet.getString("NOM"));
    	presta.setPrenomBenef(resultSet.getString("PRENOM"));
    	
        return presta;
    }

    private static PrestationsSante mapSimple( ResultSet resultSet ) throws SQLException {
    	PrestationsSante presta = new PrestationsSante();
        
    	presta.setNumSinistre(resultSet.getInt("NUM_SINISTRE"));
    	presta.setNumAdhesion(resultSet.getInt("NUM_ADHESION"));
    	presta.setNumBeneficiaireSinistre(resultSet.getInt("NUM_BENEFICIAIRE_SINISTRE"));
    	presta.setNumBeneficiaire(resultSet.getInt("NUM_BENEFICIAIRE"));
    	presta.setActe(resultSet.getString("ACTE"));
    	presta.setDesignationActe(resultSet.getString("DESIGNATION_ACTE"));
    	presta.setLibelleBareme(resultSet.getString("LIBELLE_BAREME"));
    	presta.setJourDebutSoins(resultSet.getString("JOUR_DEBUT_SOINS"));
    	presta.setMoisDebutSoins(resultSet.getString("MOIS_DEBUT_SOINS"));
    	presta.setAnneeDebutSoins(resultSet.getInt("ANNEE_DEBUT_SOINS"));
    	presta.setJourPaiement(resultSet.getString("JOUR_PAIEMENT"));
    	presta.setMoisPaiement(resultSet.getString("MOIS_PAIEMENT"));
    	presta.setAnneePaiement(resultSet.getInt("ANNEE_PAIEMENT"));
    	presta.setFraisReelAssure(resultSet.getFloat("FRAIS_REEL_ASSURE"));
    	presta.setMontantSecu(resultSet.getFloat("MONTANT_SECU"));
    	presta.setMontantRembourse(resultSet.getFloat("MONTANT_REMBOURSE"));
    	
        return presta;
    }
    
	@Override
	public Map<String, Float> moyenneParSexe() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        Map<String, Float> mapMoySexee = new TreeMap<String, Float>();
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_MOYENNE_SEXE, false);
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            if ( resultSet.next() ) {
            	mapMoySexee.put(resultSet.getString("SEXE"), resultSet.getFloat("MOYENNE"));
            }
            
    		return mapMoySexee;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   
	}

	@Override
	public HashMap<String, PrestationsSante> getMapTrierParCodeProf() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        HashMap<String, PrestationsSante> mapCodeProfPresta = new HashMap<String, PrestationsSante>();
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_CODE_PRO_PRESTA, false);
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            if ( resultSet.next() ) {
            	PrestationsSante bean = new PrestationsSante();
            	
            	bean = mapSimple(resultSet);
            	
            	mapCodeProfPresta.put(resultSet.getString("CODE_PROFESSION"), bean);
            }
            
    		return mapCodeProfPresta;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   
	}

    
}
