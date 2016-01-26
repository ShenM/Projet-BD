package com.sdzee.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdzee.beans.PrestationsSante;

public interface PrestationsSanteDAO {
	/**
	 * Retourne une liste des prestations d'un bénéficiaire
	 * @param numBeneficiaire
	 * @return
	 * @throws DAOException
	 */
	List<PrestationsSante> trouverParNumBeneficiaire(int numBeneficiaire) throws DAOException;
	
	/**
	 * Retourne une liste des prestations d'un bénéficiaire
	 * @param numBeneficiaireSinistre
	 * @return
	 * @throws DAOException
	 */
	List<PrestationsSante> trouverParNumBeneficiaireSinistre(int numBeneficiaireSinistre) throws DAOException;
	
	/**
	 * Retourne une liste des prestations d'un bénéficiaire
	 * @param numBeneficiaireSinistre
	 * @return
	 * @throws DAOException
	 */
	List<PrestationsSante> trouverParNumAdhesion(int numBeneficiaireSinistre) throws DAOException;
	
	/**
	 * Retourne une liste des prestations d'un bénéficiaire
	 * @param numBeneficiaireSinistre
	 * @return
	 * @throws DAOException
	 */
	List<PrestationsSante> trouverParNumAdhesionLimite(int numBeneficiaireSinistre) throws DAOException;
	
	/**
	 * (Stats) retourne un map servant à afficher la moyenne par sexe
	 * @return
	 * @throws DAOException
	 */
	Map<String, Float> moyenneParSexe() throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 */
	PrestationsSante trouver() throws DAOException;
	
	/**
	 * Retourne un HashMap qui trie par le code de profession les prestations
	 * @return
	 * @throws DAOException
	 */
	HashMap<String, PrestationsSante> getMapTrierParCodeProf() throws DAOException;
}
