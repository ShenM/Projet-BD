package com.sdzee.beans;

public class AdhesionDetail {
	private int 	numAdhesionNormalise;
	private int 	numBeneficiaireUnique; //remplacé par un objet Beneficiaire?
	private String 	codeProfession;
	private String 	codeProduit;
	private String 	codeFractionnement;
	private String	codeGarantie;
	private String 	formule;
	private int		exercicePaiement;
	private int		numBeneficiaire;
	private	String	typeBeneficiaire;
	private float	primesAcquises;
	private	int		codeAgent;
	private	int		codeRegion;
	private String	primeGarantie;
	private String	codePostal;
	
	public AdhesionDetail() {
		super();
	}
	
	public AdhesionDetail(int numAdhesionNormalise, int numBeneficiaireUnique,
			String codeProfession, String codeProduit,
			String codeFractionnement, String codeGarantie, String formule,
			int exercicePaiement, int numBeneficiaire, String typeBeneficiaire,
			float primesAcquises, int codeAgent, int codeRegion,
			String primeGarantie, String codePostal) {
		super();
		this.numAdhesionNormalise = numAdhesionNormalise;
		this.numBeneficiaireUnique = numBeneficiaireUnique;
		this.codeProfession = codeProfession;
		this.codeProduit = codeProduit;
		this.codeFractionnement = codeFractionnement;
		this.codeGarantie = codeGarantie;
		this.formule = formule;
		this.exercicePaiement = exercicePaiement;
		this.numBeneficiaire = numBeneficiaire;
		this.typeBeneficiaire = typeBeneficiaire;
		this.primesAcquises = primesAcquises;
		this.codeAgent = codeAgent;
		this.codeRegion = codeRegion;
		this.primeGarantie = primeGarantie;
		this.codePostal = codePostal;
	}


	public int getNumAdhesionNormalise() {
		return numAdhesionNormalise;
	}


	public void setNumAdhesionNormalise(int numAdhesionNormalise) {
		this.numAdhesionNormalise = numAdhesionNormalise;
	}


	public int getNumBeneficiaireUnique() {
		return numBeneficiaireUnique;
	}


	public void setNumBeneficiaireUnique(int numBeneficiaireUnique) {
		this.numBeneficiaireUnique = numBeneficiaireUnique;
	}


	public String getCodeProfession() {
		return codeProfession;
	}


	public void setCodeProfession(String codeProfession) {
		this.codeProfession = codeProfession;
	}


	public String getCodeProduit() {
		return codeProduit;
	}


	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}


	public String getCodeFractionnement() {
		return codeFractionnement;
	}


	public void setCodeFractionnement(String codeFractionnement) {
		this.codeFractionnement = codeFractionnement;
	}


	public String getCodeGarantie() {
		return codeGarantie;
	}


	public void setCodeGarantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}


	public String getFormule() {
		return formule;
	}


	public void setFormule(String formule) {
		this.formule = formule;
	}


	public int getExercicePaiement() {
		return exercicePaiement;
	}


	public void setExercicePaiement(int exercicePaiement) {
		this.exercicePaiement = exercicePaiement;
	}


	public int getNumBeneficiaire() {
		return numBeneficiaire;
	}


	public void setNumBeneficiaire(int numBeneficiaire) {
		this.numBeneficiaire = numBeneficiaire;
	}


	public String getTypeBeneficiaire() {
		return typeBeneficiaire;
	}


	public void setTypeBeneficiaire(String typeBeneficiaire) {
		this.typeBeneficiaire = typeBeneficiaire;
	}


	public float getPrimesAcquises() {
		return primesAcquises;
	}


	public void setPrimesAcquises(float primesAcquises) {
		this.primesAcquises = primesAcquises;
	}


	public int getCodeAgent() {
		return codeAgent;
	}


	public void setCodeAgent(int codeAgent) {
		this.codeAgent = codeAgent;
	}


	public int getCodeRegion() {
		return codeRegion;
	}


	public void setCodeRegion(int codeRegion) {
		this.codeRegion = codeRegion;
	}


	public String getPrimeGarantie() {
		return primeGarantie;
	}


	public void setPrimeGarantie(String primeGarantie) {
		this.primeGarantie = primeGarantie;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	
	
}
