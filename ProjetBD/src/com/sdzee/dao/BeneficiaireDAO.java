package com.sdzee.dao;

import com.sdzee.beans.Beneficiaire;

public interface BeneficiaireDAO {
	Beneficiaire trouver( int id ) throws DAOException;
}
