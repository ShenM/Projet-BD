package com.sdzee.beans;

import java.util.ArrayList;

public class ChartFraisAnnee {
	int fraisTotal;
	int fraisPayer;
	int remboursementMutuelle;
	int remboursementSecu;
	
	public ChartFraisAnnee(ArrayList<PrestationsSante> lpresta) {
		this.fraisTotal = 0;
		this.fraisPayer = 0;
		this.remboursementMutuelle = 0;
		this.remboursementSecu = 0;
		
		for(PrestationsSante presta : lpresta)
		{
			this.fraisTotal += (int)presta.getFraisReelAssure();
			this.remboursementMutuelle += (int)presta.getMontantRembourse();
			this.remboursementSecu += (int)presta.getMontantSecu();
		}
		
		
		
		this.fraisPayer = this.fraisTotal - this.remboursementMutuelle - this.remboursementSecu;
	}


	public ChartFraisAnnee(int fraisTotal, int fraisPayer,
			int remboursementMutuelle, int remboursementSecu) {
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


	public void setFraisPayer(int fraisPayer ) {
		this.fraisPayer = fraisPayer;
	}


	public int getFraisTotal() {
		return fraisTotal;
	}

	public void setFraisTotal(int frais) {
		this.fraisTotal = frais;
	}

	public int getRemboursementMutuelle() {
		return remboursementMutuelle;
	}

	public void setRemboursementMutuelle(int remboursementMutuelle) {
		this.remboursementMutuelle = remboursementMutuelle;
	}

	public int getRemboursementSecu() {
		return remboursementSecu;
	}

	public void setRemboursementSecu(int remboursementSecu) {
		this.remboursementSecu = remboursementSecu;
	}
	
	
}
