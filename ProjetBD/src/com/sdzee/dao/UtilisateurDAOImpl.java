package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.sdzee.beans.Beneficiaire;


public class UtilisateurDAOImpl implements UtilisateurDAO{
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_ID = "SELECT MOT_DE_PASSE FROM UTILISATEUR WHERE NUM_BENEFICIAIRE = ?";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE UTILISATEUR SET MOT_DE_PASSE = ? WHERE NUM_BENEFICIAIRE = ?";
	
	public UtilisateurDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public String recupererMotDePasse(int id){
		String motDePasse = "";
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donn�es retourn�e dans le ResultSet */
            if ( resultSet.next() ) {
            	motDePasse = resultSet.getString("MOT_DE_PASSE");
            }
            
    		return motDePasse;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   		
	}
	
	public void modifier(String password, int benefid){
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
          
        try{
        	connexion = daoFactory.getConnection();
        	preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_PAR_ID, false, password,benefid);        	
        	preparedStatement.execute();
        } catch ( SQLException e ) {
            throw new DAOException( e + " TRUC :" + daoFactory.getUrl());
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        
	}
}
