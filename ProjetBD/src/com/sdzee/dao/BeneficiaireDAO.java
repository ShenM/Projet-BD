package com.sdzee.dao;

import com.sdzee.beans.Beneficiaire;

public interface BeneficiaireDAO {
	/**
	 * Renvoi un bénéficiaire à partir de son ID
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	Beneficiaire trouver( int id ) throws DAOException;
}
