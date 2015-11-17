package com.sdzee.dao;

import java.util.List;

import com.sdzee.beans.ChartFraisAnnee;
import com.sdzee.beans.PrestationsSante;

public interface PrestationsSanteDAO {
	List<PrestationsSante> trouverParNumBeneficiaire(int numBeneficiaire) throws DAOException;
	List<PrestationsSante> trouverParNumBeneficiaireSinistre(int numBeneficiaireSinistre) throws DAOException;
	
	
	PrestationsSante trouver() throws DAOException;
}
