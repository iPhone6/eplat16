package com.cn.eplat.model;

public class EpAxis {
	private int id;
	private double longitude;
	private double latitude;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "EpAxis [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
}
