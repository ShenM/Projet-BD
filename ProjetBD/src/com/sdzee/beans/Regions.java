package com.sdzee.beans;

public class Regions {
	private int		numRegion;
	private	String	libeRegion;
	
	public Regions() {
		razBean();
	}
	
	public void razBean(){
		this.numRegion=0;
		this.libeRegion="";
	}

	public Regions(int numRegion, String libeRegion) {
		super();
		this.numRegion = numRegion;
		this.libeRegion = libeRegion;
	}

	public int getNumRegion() {
		return numRegion;
	}

	public void setNumRegion(int numRegion) {
		this.numRegion = numRegion;
	}

	public String getLibeRegion() {
		return libeRegion;
	}

	public void setLibeRegion(String libeRegion) {
		this.libeRegion = libeRegion;
	}
	
}
