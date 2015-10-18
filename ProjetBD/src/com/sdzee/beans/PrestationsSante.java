package com.sdzee.beans;

public class PrestationsSante {
	private int		numSinistre;
	private	int		numAdhesion; //remplacé par un objet adhesion ?
	private int		numBeneficiaireSinistre; //remplacé par un objet beneficiaire ?
	private	int		numBeneficiaire; //remplacé par un objet beneficiaire ?
	private String	acte;
	private String	designationActe;
	private String	libelleBareme;
	private String	jourDebutSoins; //remplacé par une date (avec moisDebutSoins et anneeDebutSoin ?
	private String	moisDebutSoins; 
	private int		anneeDebutSoins; 
	private String	jourPaiement; //même question
	private String	moisPaiement;
	private int		anneePaiement;
	private float	fraisReelAssure;
	private float 	montantSecu;
	private float	montantRembourse;
	
	public PrestationsSante() {
		super();
	}

	public PrestationsSante(int numSinistre, int numAdhesion,
			int numBeneficiaireSinistre, int numBeneficiaire, String acte,
			String designationActe, String libelleBareme,
			String jourDebutSoins, String moisDebutSoins, int anneeDebutSoins,
			String jourPaiement, String moisPaiement, int anneePaiement,
			float fraisReelAssure, float montantSecu, float montantRembourse) {
		super();
		this.numSinistre = numSinistre;
		this.numAdhesion = numAdhesion;
		this.numBeneficiaireSinistre = numBeneficiaireSinistre;
		this.numBeneficiaire = numBeneficiaire;
		this.acte = acte;
		this.designationActe = designationActe;
		this.libelleBareme = libelleBareme;
		this.jourDebutSoins = jourDebutSoins;
		this.moisDebutSoins = moisDebutSoins;
		this.anneeDebutSoins = anneeDebutSoins;
		this.jourPaiement = jourPaiement;
		this.moisPaiement = moisPaiement;
		this.anneePaiement = anneePaiement;
		this.fraisReelAssure = fraisReelAssure;
		this.montantSecu = montantSecu;
		this.montantRembourse = montantRembourse;
	}

	public int getNumSinistre() {
		return numSinistre;
	}

	public void setNumSinistre(int numSinistre) {
		this.numSinistre = numSinistre;
	}

	public int getNumAdhesion() {
		return numAdhesion;
	}

	public void setNumAdhesion(int numAdhesion) {
		this.numAdhesion = numAdhesion;
	}

	public int getNumBeneficiaireSinistre() {
		return numBeneficiaireSinistre;
	}

	public void setNumBeneficiaireSinistre(int numBeneficiaireSinistre) {
		this.numBeneficiaireSinistre = numBeneficiaireSinistre;
	}

	public int getNumBeneficiaire() {
		return numBeneficiaire;
	}

	public void setNumBeneficiaire(int numBeneficiaire) {
		this.numBeneficiaire = numBeneficiaire;
	}

	public String getActe() {
		return acte;
	}

	public void setActe(String acte) {
		this.acte = acte;
	}

	public String getDesignationActe() {
		return designationActe;
	}

	public void setDesignationActe(String designationActe) {
		this.designationActe = designationActe;
	}

	public String getLibelleBareme() {
		return libelleBareme;
	}

	public void setLibelleBareme(String libelleBareme) {
		this.libelleBareme = libelleBareme;
	}

	public String getJourDebutSoins() {
		return jourDebutSoins;
	}

	public void setJourDebutSoins(String jourDebutSoins) {
		this.jourDebutSoins = jourDebutSoins;
	}

	public String getMoisDebutSoins() {
		return moisDebutSoins;
	}

	public void setMoisDebutSoins(String moisDebutSoins) {
		this.moisDebutSoins = moisDebutSoins;
	}

	public int getAnneeDebutSoins() {
		return anneeDebutSoins;
	}

	public void setAnneeDebutSoins(int anneeDebutSoins) {
		this.anneeDebutSoins = anneeDebutSoins;
	}

	public String getJourPaiement() {
		return jourPaiement;
	}

	public void setJourPaiement(String jourPaiement) {
		this.jourPaiement = jourPaiement;
	}

	public String getMoisPaiement() {
		return moisPaiement;
	}

	public void setMoisPaiement(String moisPaiement) {
		this.moisPaiement = moisPaiement;
	}

	public int getAnneePaiement() {
		return anneePaiement;
	}

	public void setAnneePaiement(int anneePaiement) {
		this.anneePaiement = anneePaiement;
	}

	public float getFraisReelAssure() {
		return fraisReelAssure;
	}

	public void setFraisReelAssure(float fraisReelAssure) {
		this.fraisReelAssure = fraisReelAssure;
	}

	public float getMontantSecu() {
		return montantSecu;
	}

	public void setMontantSecu(float montantSecu) {
		this.montantSecu = montantSecu;
	}

	public float getMontantRembourse() {
		return montantRembourse;
	}

	public void setMontantRembourse(float montantRembourse) {
		this.montantRembourse = montantRembourse;
	}
	
		
}
