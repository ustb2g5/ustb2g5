package com.ust.model;

public class Prescription {
	private int prescId;
	private long dId;
	private int regId;
	
	public Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Prescription(int prescId, long dId, int regId) {
		super();
		this.prescId = prescId;
		this.dId = dId;
		this.regId = regId;
	}

	public Prescription(long dId, int regId) {
		super();
		this.dId = dId;
		this.regId = regId;
	}

	public int getPrescId() {
		return prescId;
	}

	public void setPrescId(int prescId) {
		this.prescId = prescId;
	}

	public long getdId() {
		return dId;
	}

	public void setdId(long dId) {
		this.dId = dId;
	}

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}
	
	

}
