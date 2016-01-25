package com.sdzee.dao;

import java.util.Map;

import com.sdzee.beans.Regions;

public interface RegionsDAO {
	/**
	 * Renvoi le top 3 des régions
	 * @return
	 * @throws DAOException
	 */
	public Map<Long, Regions> getTopThree() throws DAOException;
	
	
}
