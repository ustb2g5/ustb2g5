package com.ust.model;

public class Consultation {
	private int regId;
	private String pFName;
	private String dob;
	private String pPhNo;
	private String sName;
	private String dSpec;
	private double consultFee;
	private double admFee;
	
	
	public Consultation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Consultation(int regId, String pFName, String dob, String pPhNo,
			String sName, String dSpec, double consultFee, double admFee) {
		super();
		this.regId = regId;
		this.pFName = pFName;
		this.dob = dob;
		this.pPhNo = pPhNo;
		this.sName = sName;
		this.dSpec = dSpec;
		this.consultFee = consultFee;
		this.admFee = admFee;
	}


	public int getRegId() {
		return regId;
	}


	public void setRegId(int regId) {
		this.regId = regId;
	}


	public String getpFName() {
		return pFName;
	}


	public void setpFName(String pFName) {
		this.pFName = pFName;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getpPhNo() {
		return pPhNo;
	}


	public void setpPhNo(String pPhNo) {
		this.pPhNo = pPhNo;
	}


	public String getsName() {
		return sName;
	}


	public void setsName(String sName) {
		this.sName = sName;
	}


	public String getdSpec() {
		return dSpec;
	}


	public void setdSpec(String dSpec) {
		this.dSpec = dSpec;
	}


	public double getConsultFee() {
		return consultFee;
	}


	public void setConsultFee(double consultFee) {
		this.consultFee = consultFee;
	}


	public double getAdmFee() {
		return admFee;
	}


	public void setAdmFee(double admFee) {
		this.admFee = admFee;
	}
	
	
	
	
	

}
