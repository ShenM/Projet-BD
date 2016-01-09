package com.sdzee.beans;

public enum DemandeRemboursementFlagEtat {
	EN_ATTENTE("0"),
	VALIDE("1"),
	REFUS("2");
	
	private String val;
	
	DemandeRemboursementFlagEtat(String val){
		this.val = val;
	}
	
	public String getVal(){
		return this.val;
	}
}
