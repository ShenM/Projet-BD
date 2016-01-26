package com.sdzee.dao;

import java.util.List;

import com.sdzee.beans.AdhesionDetail;
import com.sdzee.beans.Beneficiaire;

public interface AdhesionDetailDAO {
	/*ici une liste ou un objet, car quand on recherche une adhesion par son idAdhesion ou idBenef ou 
	le couple des deux, on retrouve toute les adhesion r�aliser dans le passer 
	(c'est annuel) donc selon l'ann�e de l'exercice de paiement*/
	public List<AdhesionDetail> trouverParNumAdhesion(int numAdhesionNormalise) throws DAOException;
	public List<AdhesionDetail> trouverParNumBeneficiaire(int numBeneficiaireUnique) throws DAOException;
	
	
	/**
	 * Récupération d'une adhséion à partir d'une numAdhesionNormalise
	 * @param numAdhesionNormalise
	 * @return
	 * @throws DAOException
	 * 
	 * Récupération d'une adhséion à partir d'une numAdhesionNormalise
	 */
	public AdhesionDetail trouverDernierParNumAdhesion(int numAdhesionNormalise) throws DAOException;
	
	/**
	 * Récupération d'une adhséion à partir du numBeneficiaire
	 * 
	 * @param numBeneficiaireUnique
	 * @return
	 * @throws DAOException
	 * 
	 */
	public AdhesionDetail trouverDernierParNumBeneficiaire(int numBeneficiaireUnique) throws DAOException;
	

	/**
	 * Récupération de l'adhésion à partir du numAdhsion normalisé, numBenef et exericePaiement
	 * @param numAdhesionNormalise
	 * @param numBeneficiaireUnique
	 * @param exercicePaiement
	 * @return
	 * @throws DAOException
	 */
	public AdhesionDetail trouver(int numAdhesionNormalise, int numBeneficiaireUnique, int exercicePaiement) throws DAOException;
	
	/**
	 * Récupération du dernier contrat d'un bénéficiare
	 * @param numBeneficiareUnique
	 * @return
	 * @throws DAOException
	 */
	public AdhesionDetail trouverLastContratParNumBenef(int numBeneficiareUnique) throws DAOException;

	
	/**
	 * Retourne la liste des bénéficiaires sur le même contrat qu'un bénéficiare donné
	 * @param numBeneficiaireUnique
	 * @return
	 * @throws DAOException
	 */
	public List<Beneficiaire> trouverBenefsMemeContrat(int numBeneficiaireUnique) throws DAOException;

}
