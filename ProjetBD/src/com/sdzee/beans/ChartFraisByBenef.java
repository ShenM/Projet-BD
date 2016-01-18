package com.sdzee.beans;

public class ChartFraisByBenef {

	private String prenom;
	private int numBenef;
	private float fraisReel;
	private float rembSecu;
	private float rembMut;
	private float aCharge;
	
	public float getRembMut() {
		return rembMut;
	}
	public void setRembMut(float rembMut) {
		this.rembMut = rembMut;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getNumBenef() {
		return numBenef;
	}
	public void setNumBenef(int numBenef) {
		this.numBenef = numBenef;
	}
	public float getFraisReel() {
		return fraisReel;
	}
	public void setFraisReel(float fraisReel) {
		this.fraisReel = fraisReel;
	}
	public float getRembSecu() {
		return rembSecu;
	}
	public void setRembSecu(float rembSecu) {
		this.rembSecu = rembSecu;
	}
	public float getaCharge() {
		float pow = (float) Math.pow(10, 2);
		return (float) (Math.floor((fraisReel - rembMut - rembSecu) * pow)) / pow;
//		return (float) Math.rint(fraisReel - rembMut - rembSecu);
	}
	public void setaCharge(float aCharge) {
		this.aCharge = aCharge;
	}
}
