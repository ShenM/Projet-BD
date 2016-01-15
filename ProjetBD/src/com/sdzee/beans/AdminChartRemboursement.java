package com.sdzee.beans;

public class AdminChartRemboursement {
	private String date;
	private float remboursements_somme;
	private float remboursements_moyenne;
	private float benef_somme;
//	select annee_paiement, mois_paiement, sum(montant_rembourse) from prestations_sante GROUP BY annee_paiement, mois_paiement ORDER BY annee_paiement, mois_paiement;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getRemboursements_somme() {
		return remboursements_somme;
	}
	public void setRemboursements_somme(float remboursements_somme) {
		this.remboursements_somme = remboursements_somme;
	}
	public float getRemboursements_moyenne() {
		return remboursements_moyenne;
	}
	public void setRemboursements_moyenne(float remboursements_moyenne) {
		this.remboursements_moyenne = remboursements_moyenne;
	}
	public float getBenef_somme() {
		return benef_somme;
	}
	public void setBenef_somme(float benef_somme) {
		this.benef_somme = benef_somme;
	}
	
}
