package com.sdzee.dao;

/**
 * Classe permettant l'éventuel gestion des Exception lié aux DAO
 */
public class DAOException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public static final String FICHIER_EXCEPTION = "/com/sdzee/dao/exception.properties";
	public DAOException( String message ) {
        super( message );
    }


    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }


    public DAOException( Throwable cause ) {
        super( cause );
    }
}
