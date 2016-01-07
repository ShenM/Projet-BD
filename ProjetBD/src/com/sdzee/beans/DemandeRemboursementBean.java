package com.sdzee.beans;

import java.io.File;
import java.util.Date;

public class DemandeRemboursementBean {
	private int idBenef;
	private String acteId;
	private String acteDesignation;
	private String acteLibelle;
	private java.util.Date acteDateDebutSoins;
	private java.util.Date fraisDatePaiement;
	private float fraisReels;
	private String fileName;
	public DemandeRemboursementBean(int idBenef, String acteId, String acteDesignation, String acteLibelle,
			Date acteDateDebutSoins, Date fraisDatePaiement, float fraisReels, String fileName) {
		super();
		this.idBenef = idBenef;
		this.acteId = acteId;
		this.acteDesignation = acteDesignation;
		this.acteLibelle = acteLibelle;
		this.acteDateDebutSoins = acteDateDebutSoins;
		this.fraisDatePaiement = fraisDatePaiement;
		this.fraisReels = fraisReels;
		this.fileName = fileName;
	}
	public int getIdBenef() {
		return idBenef;
	}
	public void setIdBenef(int idBenef) {
		this.idBenef = idBenef;
	}
	public String getActeId() {
		return acteId;
	}
	public void setActeId(String acteId) {
		this.acteId = acteId;
	}
	public String getActeDesignation() {
		return acteDesignation;
	}
	public void setActeDesignation(String acteDesignation) {
		this.acteDesignation = acteDesignation;
	}
	public String getActeLibelle() {
		return acteLibelle;
	}
	public void setActeLibelle(String acteLibelle) {
		this.acteLibelle = acteLibelle;
	}
	public java.util.Date getActeDateDebutSoins() {
		return acteDateDebutSoins;
	}
	public void setActeDateDebutSoins(java.util.Date acteDateDebutSoins) {
		this.acteDateDebutSoins = acteDateDebutSoins;
	}
	public java.util.Date getFraisDatePaiement() {
		return fraisDatePaiement;
	}
	public void setFraisDatePaiement(java.util.Date fraisDatePaiement) {
		this.fraisDatePaiement = fraisDatePaiement;
	}
	public float getFraisReels() {
		return fraisReels;
	}
	public void setFraisReels(float fraisReels) {
		this.fraisReels = fraisReels;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
