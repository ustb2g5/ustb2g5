package com.ust.model;

public class Activity {

	private int logId;
	private int regId;
	private int dId;
	private String logType;
	private String description;
	private int activityId;
	private String createdDate;

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Activity(int logId, int regId, int dId, String logType,
			String description, int activityId, String createdDate) {
		super();
		this.logId = logId;
		this.regId = regId;
		this.dId = dId;
		this.logType = logType;
		this.description = description;
		this.activityId = activityId;
		this.createdDate = createdDate;
	}

	public Activity(int regId, int dId, String logType, String description,
			int activityId, String createdDate) {
		super();
		this.regId = regId;
		this.dId = dId;
		this.logType = logType;
		this.description = description;
		this.activityId = activityId;
		this.createdDate = createdDate;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getRegId() {
		return regId;
	}

	public void setRegId(int regId) {
		this.regId = regId;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
