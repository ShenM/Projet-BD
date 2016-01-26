package com.sdzee.dao;

public interface AdministrateurDAO {
	
	/**
	 * Récupère le MDP hashé en base
	 * @param id
	 * @return
	 */
	String recupererMotDePasse(String id);
}
