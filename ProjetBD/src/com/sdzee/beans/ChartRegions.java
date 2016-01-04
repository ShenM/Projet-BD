package com.sdzee.beans;

import java.util.Map;

public class ChartRegions {
	private String label1;
	private long nb1;
	
	private String label2;
	private long nb2;
	
	private String label3;
	private long nb3;
	
	
	public ChartRegions() {
		super();

	}
	
	public ChartRegions(Map<Long, Regions> map) {
		int i = 0;
		
		for(Map.Entry<Long, Regions> entry : map.entrySet()) {
			if(i==0){
				label1 = entry.getValue().getLibeRegion();
				nb1 = entry.getKey();
			}else if(i==1){
				label2 = entry.getValue().getLibeRegion().replace('\'', ' ');
				nb2 = entry.getKey();
			}else if(i==2){
				label3 = entry.getValue().getLibeRegion();
				nb3 = entry.getKey();
			}
			i++;
		    
		}

	}
	
	public ChartRegions(String label1, long nb1, String label2, long nb2, String label3, long nb3) {
		super();
		this.label1 = label1;
		this.nb1 = nb1;
		this.label2 = label2;
		this.nb2 = nb2;
		this.label3 = label3;
		this.nb3 = nb3;
	}
	public String getLabel1() {
		return label1;
	}
	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	public long getNb1() {
		return nb1;
	}
	public void setNb1(long nb1) {
		this.nb1 = nb1;
	}
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public long getNb2() {
		return nb2;
	}
	public void setNb2(long nb2) {
		this.nb2 = nb2;
	}
	public String getLabel3() {
		return label3;
	}
	public void setLabel3(String label3) {
		this.label3 = label3;
	}
	public long getNb3() {
		return nb3;
	}
	public void setNb3(long nb3) {
		this.nb3 = nb3;
	}
		
}
