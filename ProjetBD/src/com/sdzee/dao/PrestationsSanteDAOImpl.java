package com.sdzee.dao;

import static com.sdzee.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sdzee.beans.AdminChartRemboursement;
import com.sdzee.beans.ChartAdminBenef;
import com.sdzee.beans.ChartFraisByBenef;
import com.sdzee.beans.ChartFraisByDate;
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
	
	private static final String SQL_SELECT_FRAI_BY_NUMBENEF = "select prenom,num_beneficiaire_sinistre, sum(frais_reel_assure), sum(montant_rembourse), sum(montant_secu) "
			+ "from prestations_sante p, beneficiaire b "
			+ "where num_adhesion in "
			+ "(SELECT num_adhesion FROM prestations_sante WHERE num_beneficiaire_sinistre = ?) "
			+ "and p.num_beneficiaire_sinistre = b.num GROUP BY prenom,num_beneficiaire_sinistre";
	
	private static final String SQL_SELECT_FRAIs_BY_NUMBENEF_DATE = "select annee_paiement, mois_paiement, sum(frais_reel_assure), sum(montant_rembourse), sum(montant_secu) "
			+ "from prestations_sante where num_adhesion in "
			+ "(SELECT num_adhesion FROM prestations_sante WHERE num_beneficiaire_sinistre = ?) "
			+ "GROUP BY annee_paiement, mois_paiement ORDER BY annee_paiement, mois_paiement";
	
	
	private static final String SQL_SELECT_REMB_ADMIN = "select annee_paiement, mois_paiement, sum(montant_rembourse), avg(montant_rembourse), count(*) "
			+ "from prestations_sante GROUP BY annee_paiement, mois_paiement ORDER BY annee_paiement, mois_paiement";
	
	private static final String SQL_SELECT_SUM_BENE_BY_RANGE_AGE_SEXE = "SELECT sexe,TO_CHAR(FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10) * 10) || ' - ' || TO_CHAR(FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10) * 10 + 10 - 1) AS range, COUNT(*) AS frequency "
			+ "FROM beneficiaire "
			+ "GROUP BY FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10), sexe "
			+ "ORDER BY FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10)";
	
	private static final String SQL_SELECT_SUM_BENE_BY_RANGE_AGE = "SELECT TO_CHAR(FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10) * 10) || ' - ' || TO_CHAR(FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10) * 10 + 10 - 1) AS range, COUNT(*) AS frequency "
			+ "FROM beneficiaire "
			+ "GROUP BY FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10) "
			+ "ORDER BY FLOOR(floor(months_between(sysdate, date_naissance_beneficiaire) /12)/10)";
	
	public PrestationsSanteDAOImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public ArrayList<PrestationsSante> trouverParNumBeneficiaire(int numBeneficiaire) throws DAOException{
		ArrayList<PrestationsSante> prestaListe = new ArrayList<PrestationsSante>();
		
		return prestaListe;
	}
	
	public HashMap<String, ChartFraisByBenef> trouverFraisParNumBeneficiaire(int numBeneficiaire) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        HashMap<String, ChartFraisByBenef> fraisListe = new HashMap<String, ChartFraisByBenef>();
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_FRAI_BY_NUMBENEF, false, numBeneficiaire );
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            while ( resultSet.next() ) {
            	ChartFraisByBenef bean = new ChartFraisByBenef();
            	
            	bean.setPrenom(resultSet.getString("PRENOM"));
            	bean.setNumBenef(resultSet.getInt("NUM_BENEFICIAIRE_SINISTRE"));
            	bean.setFraisReel(resultSet.getFloat("SUM(FRAIS_REEL_ASSURE)"));
            	bean.setRembMut(resultSet.getFloat("SUM(MONTANT_REMBOURSE)"));
            	bean.setRembSecu(resultSet.getFloat("SUM(MONTANT_SECU)"));
            	bean.setaCharge(bean.getaCharge());
            	
            	fraisListe.put(resultSet.getString("PRENOM"), bean);
            }
            
            
    		return fraisListe;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   
	}
	
//	=================================================================================================================================
	
	public ArrayList<ChartFraisByDate> trouverFraisParNumBeneficiaireDate(int numBeneficiaire) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        ArrayList<ChartFraisByDate> fraisListe = new ArrayList<ChartFraisByDate>();
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_FRAIs_BY_NUMBENEF_DATE, false, numBeneficiaire );
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            while ( resultSet.next() ) {
            	ChartFraisByDate bean = new ChartFraisByDate();
            	
            	bean.setDate(resultSet.getString("ANNEE_PAIEMENT") + "-" + resultSet.getString("MOIS_PAIEMENT"));
            	bean.setFraisReel(resultSet.getFloat("SUM(FRAIS_REEL_ASSURE)"));
            	bean.setRembMut(resultSet.getFloat("SUM(MONTANT_REMBOURSE)"));
            	bean.setRembSecu(resultSet.getFloat("SUM(MONTANT_SECU)"));
            	bean.setaCharge(bean.getaCharge());
            	
            	fraisListe.add(bean);
            }
            
            
    		return fraisListe;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   
	}
	
	
	public ArrayList<ChartAdminBenef> trouverAdminChartBenef() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        ArrayList<ChartAdminBenef> benefAgeListe = new ArrayList<ChartAdminBenef>();
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_SUM_BENE_BY_RANGE_AGE, false);
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            while ( resultSet.next() ) {
            	ChartAdminBenef bean = new ChartAdminBenef();
            	
            	bean.setRange(resultSet.getString("RANGE"));
            	bean.setFrequence(resultSet.getInt("FREQUENCY"));
            	
            	benefAgeListe.add(bean);
            }
            
            
    		return benefAgeListe;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   
	}
	
	
	public ArrayList<AdminChartRemboursement> trouverRemboursementsAdmin() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        ArrayList<AdminChartRemboursement> rembList = new ArrayList<AdminChartRemboursement>();
        
        try {
            connexion = daoFactory.getConnection();

            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_REMB_ADMIN, false);
            
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnees retournee dans le ResultSet */
            while ( resultSet.next() ) {
            	AdminChartRemboursement bean = new AdminChartRemboursement();
            	
            	bean.setDate(resultSet.getString("ANNEE_PAIEMENT") + "-" + resultSet.getString("MOIS_PAIEMENT"));
            	bean.setRemboursements_somme(resultSet.getFloat("SUM(MONTANT_REMBOURSE)"));
            	bean.setRemboursements_moyenne(resultSet.getFloat("AVG(MONTANT_REMBOURSE)"));
            	bean.setBenef_somme(resultSet.getFloat("COUNT(*)"));
            	System.out.println(bean.getDate() + " ----- " + bean.getRemboursements_somme() + " ----- " + bean.getRemboursements_moyenne() + " ----- " + bean.getBenef_somme() + " -----\n ");
            	rembList.add(bean);
            }
            
            
    		return rembList;

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }   
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
            while ( resultSet.next() != false) {
            	mapMoySexee.put(resultSet.getString("SEXE"), Float.parseFloat(resultSet.getString("MOYENNE").replace(',','.')));
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
