package com.sdzee.beans;

import java.util.Date;

public class Beneficiaire {
	private int num;
	private String sexe;
	private String regimeSocial;
	private java.util.Date dateNaissanceBenficiaire;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getRegimeSocial() {
		return regimeSocial;
	}
	public void setRegimeSocial(String regimeSocial) {
		this.regimeSocial = regimeSocial;
	}
	public java.util.Date getDateNaissanceBenficiaire() {
		return dateNaissanceBenficiaire;
	}
	public void setDateNaissanceBenficiaire(java.util.Date dateNaissanceBenficiaire) {
		this.dateNaissanceBenficiaire = dateNaissanceBenficiaire;
	}
	
	
	
	
	public Beneficiaire(int num, String sexe, String regimeSocial,
			Date dateNaissanceBenficiaire) {
		super();
		this.num = num;
		this.sexe = sexe;
		this.regimeSocial = regimeSocial;
		this.dateNaissanceBenficiaire = dateNaissanceBenficiaire;
	}

	public Beneficiaire(){
	}
	
}
