package com.sdzee.dao;

/**
 * Classe permettant l'éventuel gestion des Exceptions concernant la configuration DAO
 */
public class DAOConfigurationException extends RuntimeException{
	private static final long serialVersionUID = 1L;


	public DAOConfigurationException( String message ) {
        super( message );
    }


    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }


    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
