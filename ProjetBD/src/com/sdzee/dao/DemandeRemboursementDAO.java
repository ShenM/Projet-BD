package com.sdzee.dao;

import java.util.Date;
import java.util.List;

import com.sdzee.beans.DemandeRemboursement;
import com.sdzee.beans.DemandeRemboursementFlagEtat;

public interface DemandeRemboursementDAO {
	/**
	 * Insertion d'une demande de remboursement en base
	 * @param bean
	 * @throws DAOException
	 */
	public void insert(DemandeRemboursement bean) throws DAOException;
	

	/**
	 * Update du flag de remboursement à "traité"
	 * @param benefId
	 * @param dateCreation
	 * @param flag
	 * @throws DAOException
	 */
	public void updateFlagTraite(int benefId, java.util.Date dateCreation, DemandeRemboursementFlagEtat flag) throws DAOException;
	
	/**
	 * Retourne toutes les demandes non traitées
	 * @return
	 * @throws DAOException
	 */
	public List<DemandeRemboursement> getDemandesNonTraite() throws DAOException;
	
	/**
	 * Retoure une demande de remboursement à partir un id benef et une date de création
	 * @param benefId
	 * @param dateCreation
	 * @return
	 * @throws DAOException
	 */
	public DemandeRemboursement get(int benefId, java.util.Date dateCreation) throws DAOException;
	
	/**
	 * Update du flag de remboursement à "rejet"
	 * @param benefId
	 * @param dateCreation
	 * @param flag
	 * @param motifRejet
	 * @throws DAOException
	 */
	public void updateFlagTraiteRejet(int benefId, Date dateCreation, DemandeRemboursementFlagEtat flag, String motifRejet)
			throws DAOException;
}
