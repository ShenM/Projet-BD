package com.sdzee.beans;

import java.util.ArrayList;

public class ChartFraisAnnee {
	double fraisTotal;
	double fraisPayer;
	float remboursementMutuelle;
	float remboursementSecu;
	
	public ChartFraisAnnee(ArrayList<PrestationsSante> lpresta) {
		this.fraisTotal = 0;
		this.fraisPayer = 0;
		this.remboursementMutuelle = 0;
		this.remboursementSecu = 0;
		
		for(PrestationsSante presta : lpresta)
		{
			System.out.println(presta.getFraisReelAssure());
			this.fraisTotal += (double)presta.getFraisReelAssure();
			System.out.println(this.fraisTotal);
			this.remboursementMutuelle += presta.getMontantRembourse();
			this.remboursementSecu += presta.getMontantSecu();
		}
		
		
		
		this.fraisPayer = this.fraisTotal - this.remboursementMutuelle - this.remboursementSecu;
	}


	public ChartFraisAnnee(float fraisTotal, float fraisPayer,
			float remboursementMutuelle, float remboursementSecu) {
		super();
		this.fraisTotal = fraisTotal;
		this.fraisPayer = fraisPayer;
		this.remboursementMutuelle = remboursementMutuelle;
		this.remboursementSecu = remboursementSecu;
	}


	public ChartFraisAnnee() {
		this.fraisTotal = 0;
		this.fraisPayer = 0;
		this.remboursementMutuelle = 0;
		this.remboursementSecu = 0;
	}

	
	public double getFraisPayer() {
		return fraisPayer;
	}


	public void setFraisPayer(double fraisPayer) {
		this.fraisPayer = fraisPayer;
	}


	public double getFraisTotal() {
		return fraisTotal;
	}

	public void setFraisTotal(double frais) {
		this.fraisTotal = frais;
	}

	public float getRemboursementMutuelle() {
		return remboursementMutuelle;
	}

	public void setRemboursementMutuelle(float remboursementMutuelle) {
		this.remboursementMutuelle = remboursementMutuelle;
	}

	public float getRemboursementSecu() {
		return remboursementSecu;
	}

	public void setRemboursementSecu(float remboursementSecu) {
		this.remboursementSecu = remboursementSecu;
	}
	
	
}
