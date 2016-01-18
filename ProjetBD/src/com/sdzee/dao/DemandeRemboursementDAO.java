package com.sdzee.dao;

import java.util.Date;
import java.util.List;

import com.sdzee.beans.DemandeRemboursement;
import com.sdzee.beans.DemandeRemboursementFlagEtat;

public interface DemandeRemboursementDAO {
	public void insert(DemandeRemboursement bean) throws DAOException;
	public void updateFlagTraite(int benefId, java.util.Date dateCreation, DemandeRemboursementFlagEtat flag) throws DAOException;
	public List<DemandeRemboursement> getDemandesNonTraite() throws DAOException;
	public DemandeRemboursement get(int benefId, java.util.Date dateCreation) throws DAOException;
	public void updateFlagTraiteRejet(int benefId, Date dateCreation, DemandeRemboursementFlagEtat flag, String motifRejet)
			throws DAOException;
}
