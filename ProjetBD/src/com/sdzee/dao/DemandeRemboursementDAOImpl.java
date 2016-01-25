package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sdzee.beans.DemandeRemboursement;
import com.sdzee.beans.DemandeRemboursementFlagEtat;

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
	
	private static final String SQL_SELECT_FLAG_ETAT_ZERO = "SELECT benef_id, presta_acte, presta_designation_acte, presta_libelle_bareme, presta_date_debut_soins, presta_date_paiement, presta_frais_reel, facture, date_creation " 
															+"FROM DEMANDE_REMBOURSEMENT "
															+"WHERE FLAG_ETAT = '0' "
															+"ORDER BY date_creation ASC";										
	
	private static final String SQL_UPDATE_FLAG_ETAT = "UPDATE DEMANDE_REMBOURSEMENT SET FLAG_ETAT = ? WHERE BENEF_ID = ? AND TO_CHAR(DATE_CREATION , 'DD/MM/YYYY HH24:MI:SS') = ?";
	private static final String SQL_UPDATE_FLAG_ETAT_REJET = "UPDATE DEMANDE_REMBOURSEMENT SET FLAG_ETAT = ?, MOTIF_REJET = ? WHERE BENEF_ID = ? AND TO_CHAR(DATE_CREATION , 'DD/MM/YYYY HH24:MI:SS') = ?";

	private static final String SQL_SELECT = "SELECT "
											+"benef_id, "
											+"presta_acte, "
											+"presta_designation_acte, "
											+"presta_libelle_bareme, "
											+"presta_date_debut_soins, "
											+"presta_date_paiement, "
											+"presta_frais_reel, "
											+"facture, "
											+"date_creation "
											+ "FROM DEMANDE_REMBOURSEMENT "
											+ "WHERE BENEF_ID = ? AND TO_CHAR(DATE_CREATION , 'DD/MM/YYYY HH24:MI:SS') = ?";
	
    public static final SimpleDateFormat formatterForm = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");

	public DemandeRemboursementDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		
	}
	
	/*=====================================================================================*/
	@Override
	public void insert(DemandeRemboursement bean) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
                
        try {
            connexion = daoFactory.getConnection();

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

	/*=====================================================================================*/
	@Override
	public void updateFlagTraite(int benefId, Date dateCreation, DemandeRemboursementFlagEtat flag) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
                
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_FLAG_ETAT, false,
            		flag.getVal(),
            		benefId,
            		formatterForm.format(dateCreation.getTime()));
            
           preparedStatement.executeUpdate();
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        } 
		
	}
	
	/*=====================================================================================*/
	@Override
	public void updateFlagTraiteRejet(int benefId, Date dateCreation, DemandeRemboursementFlagEtat flag, String motifRejet) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
                
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_FLAG_ETAT_REJET, false,
            		flag.getVal(),
            		motifRejet,
            		benefId,
            		formatterForm.format(dateCreation.getTime()));
            
           preparedStatement.executeUpdate();
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        } 
		
	}

	/*=====================================================================================*/
	@Override
	public List<DemandeRemboursement> getDemandesNonTraite() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        List<DemandeRemboursement> list = new ArrayList<DemandeRemboursement>();
        
        try {
            connexion = daoFactory.getConnection();	
            
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_FLAG_ETAT_ZERO, false);
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            while ( resultSet.next() != false) {            	
            	list.add(map(resultSet));
            }
            
            return list;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }  
	}

	/*=====================================================================================*/
	private DemandeRemboursement map(ResultSet resultSet) throws SQLException {
		DemandeRemboursement bean = new DemandeRemboursement();
		
		bean.setIdBenef(resultSet.getInt("benef_id"));
		bean.setActeId(resultSet.getString("presta_acte"));
		bean.setActeDesignation(resultSet.getString("presta_designation_acte"));
		bean.setActeLibelle(resultSet.getString("presta_libelle_bareme"));
		bean.setActeDateDebutSoins(resultSet.getDate("presta_date_debut_soins"));
		bean.setFraisDatePaiement(resultSet.getDate("presta_date_paiement"));
		bean.setFraisReels(resultSet.getFloat("presta_frais_reel"));
		bean.setFileName(resultSet.getString("facture"));
		bean.setDate_creation(resultSet.getTimestamp("date_creation"));
		
		return bean;
	}

	/*=====================================================================================*/
	private DemandeRemboursement mapRejet(ResultSet resultSet) throws SQLException {
		DemandeRemboursement bean = new DemandeRemboursement();
		
		bean.setIdBenef(resultSet.getInt("benef_id"));
		bean.setActeId(resultSet.getString("presta_acte"));
		bean.setActeDesignation(resultSet.getString("presta_designation_acte"));
		bean.setActeLibelle(resultSet.getString("presta_libelle_bareme"));
		bean.setActeDateDebutSoins(resultSet.getDate("presta_date_debut_soins"));
		bean.setFraisDatePaiement(resultSet.getDate("presta_date_paiement"));
		bean.setFraisReels(resultSet.getFloat("presta_frais_reel"));
		bean.setFileName(resultSet.getString("facture"));
		bean.setDate_creation(resultSet.getTimestamp("date_creation"));
		bean.setMotifRejet(resultSet.getString("motif_rejet"));
		
		return bean;
	}
	
	/*=====================================================================================*/
	
	@Override
	public DemandeRemboursement get(int benefId, Date dateCreation) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        DemandeRemboursement bean = new DemandeRemboursement();
        
        try{
        	connexion = daoFactory.getConnection();	

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false, benefId, formatterForm.format(dateCreation));
            
            resultSet = preparedStatement.executeQuery();
            
            if( resultSet.next() != false) {            	
            	bean= map(resultSet);
            }
            
    		return bean;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion );
        }  
	}
	
}
