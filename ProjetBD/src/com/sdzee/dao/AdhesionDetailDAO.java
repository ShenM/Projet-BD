package com.sdzee.dao;

import java.util.List;

import com.sdzee.beans.AdhesionDetail;

public interface AdhesionDetailDAO {
	/*ici une liste ou un objet, car quand on recherche une adhesion par son idAdhesion ou idBenef ou 
	le couple des deux, on retrouve toute les adhesion r�aliser dans le passer 
	(c'est annuel) donc selon l'ann�e de l'exercice de paiement*/
	List<AdhesionDetail> trouverParNumAdhesion(int numAdhesionNormalise) throws DAOException;
	List<AdhesionDetail> trouverParNumBeneficiaire(int numBeneficiaireUnique) throws DAOException;
	
	AdhesionDetail trouverDernierParNumAdhesion(int numAdhesionNormalise) throws DAOException;
	AdhesionDetail trouverDernierParNumBeneficiaire(int numBeneficiaireUnique) throws DAOException;
	
	AdhesionDetail trouver(int numAdhesionNormalise, int numBeneficiaireUnique, int exercicePaiement) throws DAOException;
	
	AdhesionDetail trouverLastContratParNumBenef(int numBeneficiareUnique) throws DAOException;

}
