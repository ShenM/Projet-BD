package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sdzee.beans.Beneficiaire;

public class BeneficiaireDAOImpl implements BeneficiaireDAO {
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_ID = "SELECT NUM, SEXE, REGIME_SOCIAL, DATE_NAISSANCE_BENEFICIAIRE, NOM, PRENOM, EMAIL, NUM_TELEPHONE FROM BENEFICIAIRE WHERE NUM = ?";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE BENEFICIAIRE SET EMAIL=?, NUM_TELEPHONE=? WHERE NUM=?";
	public BeneficiaireDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
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
