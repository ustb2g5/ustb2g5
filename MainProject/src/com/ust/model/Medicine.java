package com.ust.model;

import java.sql.Date;

public class Medicine {
	
	private int medId;
	private String medName;
	private double medPrice;
	private int isActiveM;
	private String manufacturer;
	private Date createdDateM;
	
	public int getMedId() {
		return medId;
	}
	public void setMedId(int medId) {
		this.medId = medId;
	}
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	
	public double getMedPrice() {
		return medPrice;
	}
	public void setMedPrice(double medPrice) {
		this.medPrice = medPrice;
	}
	public int getIsActiveM() {
		return isActiveM;
	}
	public void setIsActiveM(int isActiveM) {
		this.isActiveM = isActiveM;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Date getCreatedDateM() {
		return createdDateM;
	}
	public void setCreatedDateM(Date createdDateM) {
		this.createdDateM = createdDateM;
	}
	
	
	

}
