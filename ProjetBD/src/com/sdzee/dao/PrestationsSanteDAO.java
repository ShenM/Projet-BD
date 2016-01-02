package com.sdzee.dao;

import java.util.List;
import java.util.Map;

import com.sdzee.beans.PrestationsSante;

public interface PrestationsSanteDAO {
	List<PrestationsSante> trouverParNumBeneficiaire(int numBeneficiaire) throws DAOException;
	List<PrestationsSante> trouverParNumBeneficiaireSinistre(int numBeneficiaireSinistre) throws DAOException;
	List<PrestationsSante> trouverParNumAdhesion(int numBeneficiaireSinistre) throws DAOException;
	List<PrestationsSante> trouverParNumAdhesionLimite(int numBeneficiaireSinistre) throws DAOException;
	Map<String, Float> moyenneParSexe() throws DAOException;
	
	PrestationsSante trouver() throws DAOException;
}
