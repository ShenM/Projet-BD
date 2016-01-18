package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.sdzee.beans.AdhesionDetail;
import com.sdzee.beans.Beneficiaire;
import com.sdzee.beans.ChartAdminBenef;
import com.sdzee.beans.ChartFormules;

public class AdhesionDetailDAOImpl implements AdhesionDetailDAO {	
	private static final String SQL_SELECT_ADHESION_PAR_NUM_CONTRAT = "SELECT DISTINCT * FROM ADHESION_DETAIL WHERE num_adhesion_normalise=? AND exercice_paiement=?";
	private static final String SQL_SELECT_ALL_ADHESION_FROM_BENF = "  SELECT a.num_adhesion_normalise, a.num_beneficiaire_unique, a.code_profession, a.code_produit, a.code_fractionnement, a.code_garantie, a.formule, a.exercice_paiement, a.num_beneficiaire, a.type_beneficiaire, a.primes_acquises, a.code_agent, a.code_region, a.prime_garantie, a.code_postal " 
																	  +"FROM ADHESION_DETAIL a "
																	  +"INNER JOIN( "
																	    +"SELECT num_adhesion_normalise, MAX(exercice_paiement) AS maxAnnee "
																	    +"FROM ADHESION_DETAIL "
																	    +"WHERE num_adhesion_normalise = ( SELECT num_adhesion_normalise "
																	                                     +"FROM ADHESION_DETAIL "
																	                                     +"WHERE num_beneficiaire_unique=? AND Rownum <2) "
																	    +"GROUP BY num_adhesion_normalise "
																	  +") temp ON a.num_adhesion_normalise = temp.num_adhesion_normalise "
																	  +"WHERE a.exercice_paiement = temp.maxAnnee";
	
	private static final String SQL_SELECT_LAST_ADHESION_PAR_NUM_BENEF = "SELECT a.num_adhesion_normalise, a.num_beneficiaire_unique, a.code_profession, a.code_produit, a.code_fractionnement, a.code_garantie, a.formule, a.exercice_paiement, a.num_beneficiaire, a.type_beneficiaire, a.primes_acquises, a.code_agent, a.code_region, a.prime_garantie, a.code_postal "
																	+ " FROM ADHESION_DETAIL a "
																	+ " WHERE a.num_beneficiaire_unique=? AND a.exercice_paiement = "
																		+ "(SELECT MAX(exercice_paiement)"
																		+ "FROM ADHESION_DETAIL "
																		+ "WHERE num_beneficiaire_unique=? AND Rownum <2 )";
	
	private static final String SQL_SELECT_ALL_FORMULES = "select formule, count(*) as nb, exercice_paiement as annee "
			+ "from adhesion_detail group by formule, exercice_paiement order by exercice_paiement, formule";
	
	private DAOFactory daoFactory;
	private  Properties exceptionProp;
	
	public AdhesionDetailDAOImpl(DAOFactory daoFactory){
		this.daoFactory=daoFactory;
		exceptionProp = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierExceptions = classLoader.getResourceAsStream( DAOException.FICHIER_EXCEPTION );
        try {
			exceptionProp.load(fichierExceptions);
		} catch (IOException e) {
			 throw new DAOConfigurationException( "Impossible de charger le fichier properties ");
		}
	}
	
	public List<AdhesionDetail> trouverParNumAdhesion(int numAdhesionNormalise) throws DAOException{return null;}
	
	public List<AdhesionDetail> trouverParNumBeneficiaire(int numBeneficiaireUnique) throws DAOException{return null;}
	
	public ArrayList<ChartFormules> trouverAdminChartFormules() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        ArrayList<ChartFormules> formules = new ArrayList<ChartFormules>();
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL_FORMULES, false);
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            while ( resultSet.next() ) {
            	ChartFormules bean = new ChartFormules();
            	
            	bean.setAnnee(resultSet.getInt("ANNEE"));
            	bean.setFormule(resultSet.getString("FORMULE"));
            	bean.setNb(resultSet.getInt("NB"));
            	formules.add(bean);
            }
            
            
    		return formules;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   
	}
	
	// =======================================================================================================
	
	
	
	/* version test (seul difference ici c'est que je fais tout en une requete (pas obligé de faire ca) et je recupere l'annee max car on sait pas 
	 * vraiment l'année pour laquelle il veulent le contrat, donc je prend la max associé à ce contrat*/
	public HashMap<Beneficiaire,AdhesionDetail> trouverAllContratsParNumBeneficiares(int numBeneficiareUnique) throws Exception{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Beneficiaire,AdhesionDetail> contrats = new HashMap<Beneficiaire,AdhesionDetail>();
        BeneficiaireDAOImpl benefDAO = new BeneficiaireDAOImpl(daoFactory);

        try {
        	
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL_ADHESION_FROM_BENF, false, numBeneficiareUnique);

            resultSet = preparedStatement.executeQuery();
			//System.out.println("Num bénéf : " + map(resultSet).getNumBeneficiaire() ); 
	
            
            while(resultSet.next()){
            	contrats.put(benefDAO.trouver(resultSet.getInt("num_beneficiaire_unique")),map(resultSet));
            }
            
            return contrats;
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
	}
	
	
	// =======================================================================================================
	
	
	// A TESTER 
	public HashMap<Beneficiaire,AdhesionDetail> trouverParNumBeneficiaresContrat(int numBeneficiareUnique, int annee) throws Exception{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<Beneficiaire,AdhesionDetail> contrats = new HashMap<Beneficiaire,AdhesionDetail>();
        BeneficiaireDAOImpl benefDAO = new BeneficiaireDAOImpl(daoFactory);

        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ADHESION_PAR_NUM_CONTRAT, false,benefDAO.getNumContrat(numBeneficiareUnique,annee), annee);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
            	contrats.put(benefDAO.trouver(resultSet.getInt("num_beneficiaire_unique")),map(resultSet));
            }
            
            return contrats;
            
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + daoFactory.getUrl());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
	}
	
	public AdhesionDetail trouverLastContratParNumBenef(int numBeneficiareUnique) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AdhesionDetail contrat = new AdhesionDetail();

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_LAST_ADHESION_PAR_NUM_BENEF, false, numBeneficiareUnique, numBeneficiareUnique);

            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
            	contrat = map(resultSet);
            }
            
            return contrat;
            
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
	}
	
	private static AdhesionDetail map(ResultSet resultSet) throws SQLException {
		AdhesionDetail adhesion = new AdhesionDetail();
        
		adhesion.setCodeAgent(resultSet.getInt("code_agent"));
		adhesion.setCodeFractionnement(resultSet.getString("code_fractionnement"));
		adhesion.setCodeGarantie(resultSet.getString("code_garantie"));
		adhesion.setCodePostal(resultSet.getString("code_postal"));
		adhesion.setCodeProduit(resultSet.getString("code_produit"));
		adhesion.setCodeProfession(resultSet.getString("code_profession"));
		adhesion.setCodeRegion(resultSet.getInt("code_region"));
		adhesion.setExercicePaiement(resultSet.getInt("exercice_paiement"));
		adhesion.setFormule(resultSet.getString("formule"));
		adhesion.setNumAdhesionNormalise(resultSet.getInt("num_adhesion_normalise"));
		adhesion.setNumBeneficiaire(resultSet.getInt("num_beneficiaire"));
		adhesion.setNumBeneficiaireUnique(resultSet.getInt("num_beneficiaire_unique"));
		adhesion.setPrimeGarantie(resultSet.getString("prime_garantie"));
		adhesion.setPrimesAcquises(resultSet.getFloat("primes_acquises"));
		adhesion.setTypeBeneficiaire(resultSet.getString("type_beneficiaire"));

        return adhesion;
        
    }
	
	public AdhesionDetail trouverDernierParNumAdhesion(int numAdhesionNormalise) throws DAOException{return null;}
	public AdhesionDetail trouverDernierParNumBeneficiaire(int numBeneficiaireUnique) throws DAOException{return null;}
	
	public AdhesionDetail trouver(int numAdhesionNormalise, int numBeneficiaireUnique, int exercicePaiement) throws DAOException{return null;}
}
