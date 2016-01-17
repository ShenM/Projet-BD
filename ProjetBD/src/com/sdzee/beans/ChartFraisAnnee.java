package com.sdzee.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Classe utiliser pour des chart frais annee
 */
public class ChartFraisAnnee {
	float fraisTotal;
	float fraisPayer;
	float remboursementMutuelle;
	float remboursementSecu;
	
	/**
	 * Constructeur permettant de construire un ChartFraisAnnee à partir d'une liste de prestatation de sante
	 * @param lpresta
	 */
	public ChartFraisAnnee(ArrayList<PrestationsSante> lpresta) {
		this.fraisTotal = 0F;
		this.fraisPayer = 0F;
		this.remboursementMutuelle = 0F;
		this.remboursementSecu = 0F;
		
		BigDecimal fraisTotalBD = new BigDecimal(0F);
		BigDecimal fraisPayerBD = new BigDecimal(0F);
		BigDecimal remboursementMutuelleBD = new BigDecimal(0F);
		BigDecimal remboursementSecuBD = new BigDecimal(0F);
		
		
		
		for(PrestationsSante presta : lpresta)
		{
			fraisTotalBD = fraisTotalBD.add(new BigDecimal(presta.getFraisReelAssure()));
			remboursementMutuelleBD = remboursementMutuelleBD.add(new BigDecimal(presta.getMontantRembourse()));
			remboursementSecuBD = remboursementSecuBD.add(new BigDecimal(presta.getMontantSecu()));
			
//			this.fraisTotal += (float)presta.getFraisReelAssure();
//			this.remboursementMutuelle += (float)presta.getMontantRembourse();
//			this.remboursementSecu += (float)presta.getMontantSecu();
		}
		
		
		fraisPayerBD = fraisTotalBD.setScale(2, RoundingMode.DOWN).subtract(remboursementMutuelleBD.add(remboursementSecuBD).setScale(2, RoundingMode.DOWN));
		
		fraisPayer = fraisPayerBD.floatValue();
		fraisTotal = fraisTotalBD.floatValue();
		remboursementMutuelle = remboursementMutuelleBD.floatValue();
		remboursementSecu = remboursementSecuBD.floatValue();
		
//		this.fraisPayer = this.fraisTotal - this.remboursementMutuelle - this.remboursementSecu;
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
		this.fraisTotal = 0F;
		this.fraisPayer = 0F;
		this.remboursementMutuelle = 0F;
		this.remboursementSecu = 0F;
	}

	
	public double getFraisPayer() {
		return fraisPayer;
	}


	public void setFraisPayer(float fraisPayer ) {
		this.fraisPayer = fraisPayer;
	}


	public float getFraisTotal() {
		return fraisTotal;
	}

	public void setFraisTotal(float frais) {
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
