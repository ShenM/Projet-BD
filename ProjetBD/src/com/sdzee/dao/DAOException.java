package com.sdzee.dao;

public class DAOException extends RuntimeException{
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
