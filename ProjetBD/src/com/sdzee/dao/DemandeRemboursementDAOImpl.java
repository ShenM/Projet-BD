package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import com.sdzee.beans.DemandeRemboursementBean;
import com.sdzee.beans.Regions;

public class DemandeRemboursementDAOImpl implements DemandeRemboursementDAO{
	private DAOFactory daoFactory;
	private static final String SQL_INSERT = "INSERT INTO DEMANDE_REMBOURSEMENT ("
											+"benef_id, "
											+"presta_acte, "
											+"presta_designation_acte, "
											+"presta_libelle_bareme, "
											+"presta_date_debut_soins, "
											+"presta_date_paiement, "
											+"presta_frais_reel, "
											+"facture, "
											+"date_creation) "
											+"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public DemandeRemboursementDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		
}

	@Override
	public void insert(DemandeRemboursementBean bean) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
                
        try {
            connexion = daoFactory.getConnection();

            System.out.println(SQL_INSERT);
            System.out.println(bean.getActeDateDebutSoins());
            System.out.println(new java.sql.Timestamp(bean.getActeDateDebutSoins().getTime()));
            
            System.out.println(bean.getDate_creation());
            System.out.println(new java.sql.Timestamp(bean.getDate_creation().getTime()));


            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, false,
            		bean.getIdBenef(),
            		bean.getActeId(),
            		bean.getActeDesignation(),
            		bean.getActeLibelle(),
            		new java.sql.Timestamp(bean.getActeDateDebutSoins().getTime()),
            		new java.sql.Timestamp(bean.getFraisDatePaiement().getTime()),
            		bean.getFraisReels(),
            		bean.getFileName(),
            		new java.sql.Timestamp(bean.getDate_creation().getTime()));
            
           preparedStatement.executeUpdate();
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        } 
	}
	
}
