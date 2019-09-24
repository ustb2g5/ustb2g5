package com.ust.model;

import java.util.Date;

public class PrescDetail {
	private int detailId;
	private int prescId;
	private int medId;
	private String medFreq;
	private int medDays;
	private String prescDate;
	private String pharmacyStatus;
	public PrescDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PrescDetail(int detailId, int prescId, int medId,
			String medFreq, int medDays, String prescDate, String pharmacyStatus) {
		super();
		this.detailId = detailId;
		this.prescId = prescId;
		this.medId = medId;
		this.medFreq = medFreq;
		this.medDays = medDays;
		this.prescDate = prescDate;
		this.pharmacyStatus = pharmacyStatus;
	}
	public PrescDetail(int prescId, int medId, String medFreq, int medDays,
			String prescDate, String pharmacyStatus) {
		super();
		this.prescId = prescId;
		this.medId = medId;
		this.medFreq = medFreq;
		this.medDays = medDays;
		this.prescDate = prescDate;
		this.pharmacyStatus = pharmacyStatus;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public int getPrescId() {
		return prescId;
	}
	public void setPrescId(int prescId) {
		this.prescId = prescId;
	}
	public int getMedId() {
		return medId;
	}
	public void setMedId(int medId) {
		this.medId = medId;
	}
	public String getMedFreq() {
		return medFreq;
	}
	public void setMedFreq(String medFreq) {
		this.medFreq = medFreq;
	}
	public int getMedDays() {
		return medDays;
	}
	public void setMedDays(int medDays) {
		this.medDays = medDays;
	}
	public String getPrescDate() {
		return prescDate;
	}
	public void setPrescDate(String prescDate) {
		this.prescDate = prescDate;
	}
	public String getPharmacyStatus() {
		return pharmacyStatus;
	}
	public void setPharmacyStatus(String pharmacyStatus) {
		this.pharmacyStatus = pharmacyStatus;
	}
	
	
	
	

	
	
	

}
