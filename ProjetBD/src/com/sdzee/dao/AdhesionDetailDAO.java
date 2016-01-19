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
	
	public AdhesionDetail trouverDernierParNumAdhesion(int numAdhesionNormalise) throws DAOException;
	public AdhesionDetail trouverDernierParNumBeneficiaire(int numBeneficiaireUnique) throws DAOException;
	
	public AdhesionDetail trouver(int numAdhesionNormalise, int numBeneficiaireUnique, int exercicePaiement) throws DAOException;
	
	public AdhesionDetail trouverLastContratParNumBenef(int numBeneficiareUnique) throws DAOException;

	public List<Beneficiaire> trouverBenefsMemeContrat(int numBeneficiaireUnique) throws DAOException;

}
