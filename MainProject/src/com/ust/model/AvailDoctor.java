package com.ust.model;

public class AvailDoctor {
	private int dId;
	private String sName;
	private String dSpec;
	public AvailDoctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AvailDoctor(int dId, String sName, String dSpec) {
		super();
		this.dId = dId;
		this.sName = sName;
		this.dSpec = dSpec;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
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
	
	

}
