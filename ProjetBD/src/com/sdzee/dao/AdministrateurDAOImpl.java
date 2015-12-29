package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministrateurDAOImpl implements AdministrateurDAO{
	private DAOFactory daoFactory;
	private static final String SQL_SELECT_PAR_ID = "SELECT MOT_DE_PASSE FROM ADMINISTRATEUR WHERE ID = ?";
	
	public AdministrateurDAOImpl( DAOFactory daoFactory) {
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
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
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

}
