package com.sdzee.dao;

public interface UtilisateurDAO{
	/**
	 * Récupère le mdp en md5 stocké en base
	 * @param id
	 * @return
	 */
	String recupererMotDePasse(int id);
}
