package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import com.sdzee.beans.Regions;

public class RegionsDAOImpl implements RegionsDAO{

	private DAOFactory daoFactory;
	private static final String SQL_SELECT_TOP_3 = "SELECT orderedList.num NUM ,orderedList.lib LIB, orderedList.cnt CNT "
														+"FROM (SELECT r.NUM_REGION num, r.LIB_REGION lib, COUNT(1) cnt "
														        +"FROM PRESTATIONS_SANTE p, ADHESION_DETAIL a, REGIONS r, DEPARTEMENTS d "
														        +"WHERE p.NUM_ADHESION = a.NUM_ADHESION_NORMALISE "
														          +"AND d.NUM_DEPARTEMENT = SUBSTR(a.CODE_POSTAL, 1,2) "
														          +"AND d.NUM_REGION = r.NUM_REGION "
														        +"GROUP BY r.LIB_REGION, r.NUM_REGION "
														        +"ORDER BY cnt DESC) orderedList "
														+"WHERE ROWNUM <=3";
	
	public RegionsDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/*=====================================================================================*/
	
	public Map<Long, Regions> getTopThree() throws DAOException{		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        Map<Long, Regions> mapTopThree = new TreeMap<Long, Regions>();
        
        try {
            connexion = daoFactory.getConnection();

            
            OracleCallableStatement cs = (OracleCallableStatement)connexion.prepareCall("{call REGIONTOPREMB(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            
            cs.execute();
            resultSet = cs.getCursor(1);
//            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_TOP_3, false);
//            
//            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            while ( resultSet.next() != false) {
            	Regions bean = new Regions();
            	bean = map(resultSet);
            	
            	mapTopThree.put(resultSet.getLong("CNT"), bean);
            }
            
    		return mapTopThree;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   		
	}
	
	/*=====================================================================================*/
	
	private static Regions map(ResultSet resultSet) throws SQLException {
		Regions bean = new Regions();
		
		bean.setNumRegion(resultSet.getInt("NUM"));
		bean.setLibeRegion(resultSet.getString("LIB"));
		
		return bean;	
	}
	


}
