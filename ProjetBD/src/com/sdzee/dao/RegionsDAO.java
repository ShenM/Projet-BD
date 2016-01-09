package com.sdzee.dao;

import java.util.Map;

import com.sdzee.beans.Regions;

public interface RegionsDAO {
	public Map<Long, Regions> getTopThree() throws DAOException;
	
	
}
