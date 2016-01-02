package com.sdzee.dao;

import java.util.Map;

import com.sdzee.beans.Regions;

public interface RegionsDAO {
	public Map<Regions, Long> getTopThree();
	
	
}
