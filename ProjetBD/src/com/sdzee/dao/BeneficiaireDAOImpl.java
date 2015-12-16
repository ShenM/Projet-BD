package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.sdzee.beans.Beneficiaire;

public class BeneficiaireDAOImpl implements BeneficiaireDAO {
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_ID = "SELECT NUM, SEXE, REGIME_SOCIAL, DATE_NAISSANCE_BENEFICIAIRE, NOM, PRENOM, EMAIL, NUM_TELEPHONE FROM BENEFICIAIRE WHERE NUM = ?";

	private static final String SQL_UPDATE_PAR_ID = "UPDATE BENEFICIAIRE SET EMAIL=?, NUM_TELEPHONE=? WHERE NUM=?";

	private static final String SQL_SELECT_NUM_CONTRAT_PAR_ID = "SELECT DISTINCT num_adhesion_normalise FROM ADHESION_DETAIL WHERE num_beneficiaire_unique=? AND exercice_paiement=?";
	
	private  Properties exceptionProp;
	

	public BeneficiaireDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierExceptions = classLoader.getResourceAsStream( DAOException.FICHIER_EXCEPTION );
        try {
			exceptionProp.load(fichierExceptions);
		} catch (IOException e) {
			 throw new DAOConfigurationException( "Impossible de charger le fichier properties ");
		}
	}
	
	
	@Override
	public Beneficiaire trouver(int id) throws DAOException{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        Beneficiaire benef = new Beneficiaire();

        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donn�es retourn�e dans le ResultSet */
            if ( resultSet.next() ) {
            	benef = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + daoFactory.getUrl());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
		return(benef);
	}
	
	
	
	public int getNumContrat(int numBeneficiareUnique, int annee) throws Exception{
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int numContrat=0;
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_NUM_CONTRAT_PAR_ID, false, numBeneficiareUnique, annee);
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){
            	numContrat = resultSet.getInt( "NUM_ADHESION_NORMALISE" );
            }else {
            	throw new Exception(exceptionProp.getProperty("contratNotFound"));
            }
            if(resultSet.next()){
            	throw new Exception(exceptionProp.getProperty("mutlipleNumContrat"));
            }
       
        return numContrat;
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + daoFactory.getUrl());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
	}
	
	public void modifier(Beneficiaire benef){
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
          
        try{
        	connexion = daoFactory.getConnection();
        	preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_PAR_ID, false, benef.getEmail(),benef.getNumTelephone(),benef.getNum());        	
        	preparedStatement.execute();
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + daoFactory.getUrl());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
	}
	
	
	
    private static Beneficiaire map( ResultSet resultSet ) throws SQLException {
        Beneficiaire benef = new Beneficiaire();
        
        benef.setNum( resultSet.getInt( "NUM" ) );
        benef.setSexe( resultSet.getString( "SEXE" ) );
        benef.setRegimeSocial( resultSet.getString( "REGIME_SOCIAL" ) );
        benef.setDateNaissanceBenficiaire( resultSet.getDate( "DATE_NAISSANCE_BENEFICIAIRE" ) );
        benef.setNom(resultSet.getString("NOM"));
        benef.setPrenom(resultSet.getString("PRENOM"));
        benef.setEmail(resultSet.getString("EMAIL"));
        benef.setNumTelephone(resultSet.getString("NUM_TELEPHONE"));

        return benef;
    }
    
    
}
