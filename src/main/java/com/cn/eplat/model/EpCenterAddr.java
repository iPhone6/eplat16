package com.cn.eplat.model;

public class EpCenterAddr {
	private int id;
	private int center_id;
	private String name;
	private int order_num;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCenter_id() {
		return center_id;
	}
	public void setCenter_id(int center_id) {
		this.center_id = center_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	
	@Override
	public String toString() {
		return "EpCenterAddr [id=" + id + ", center_id=" + center_id + ", name=" + name + ", order_num=" + order_num + "]";
	}
	
}
