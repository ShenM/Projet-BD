package com.sdzee.beans;

public class Departements {
	private String	numDepartement;
	private	int		numRegion;
	private String	libDepartement;
	
	public Departements() {
		super();
	}

	public Departements(String numDepartement, int numRegion,
			String libDepartement) {
		super();
		this.numDepartement = numDepartement;
		this.numRegion = numRegion;
		this.libDepartement = libDepartement;
	}

	public String getNumDepartement() {
		return numDepartement;
	}

	public void setNumDepartement(String numDepartement) {
		this.numDepartement = numDepartement;
	}

	public int getNumRegion() {
		return numRegion;
	}

	public void setNumRegion(int numRegion) {
		this.numRegion = numRegion;
	}

	public String getLibDepartement() {
		return libDepartement;
	}

	public void setLibDepartement(String libDepartement) {
		this.libDepartement = libDepartement;
	}
	
	
}
