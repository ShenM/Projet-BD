package com.sdzee.beans;

import java.util.Map;

public class ChartPrestaParSexe {
	public float moyH;
	public float moyF;
	
	public ChartPrestaParSexe() {
		super();
	}
	
	public ChartPrestaParSexe(float moyH, float moyF) {
		super();
		this.moyH = moyH;
		this.moyF = moyF;
	}
	
	public ChartPrestaParSexe(Map<String, Float> map){
		this.moyH = (float)map.get("M");
		this.moyF = (float)map.get("F");
	}
	
	public float getMoyH() {
		return moyH;
	}
	public void setMoyH(float moyH) {
		this.moyH = moyH;
	}
	public float getMoyF() {
		return moyF;
	}
	public void setMoyF(float moyF) {
		this.moyF = moyF;
	}
	
	
}
