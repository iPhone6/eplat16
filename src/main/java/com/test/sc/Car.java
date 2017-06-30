package com.test.sc;

public class Car {
	private String vendor;	// 制造商
	private String modelName;	// 型号名称
	private Integer modelYear=-1;	//	型号年份（默认值为-1）
	private String plateNum="";	// 车牌号（默认值为""）
	
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public String getVendor() {
		return vendor;
	}
	public String getModelName() {
		return modelName;
	}
	public Integer getModelYear() {
		return modelYear;
	}
	
//	private Car(){
//		
//	}
	
	public Car(String vendor,String modelName){
//		this();
		this.vendor=vendor;
		this.modelName=modelName;
	}
	
	public Car(String vendor,String modelName,Integer modelYear,String plateNum){
		this.vendor=vendor;
		this.modelName=modelName;
		if(modelYear==null)modelYear=-1;
		if(plateNum==null)plateNum="";
	}
	
	@Override
	public String toString() {
		return "Car [vendor=" + vendor + ", modelName=" + modelName + ", modelYear=" + modelYear + ", plateNum=" + plateNum + "]";
	}
	
	public static void main(String[] args) {
		Car c=new Car("Hmmm","Yuuu");
		c=new Car("Hmmm","Yuuu",null,null);
		System.out.println("c = "+c);
		
	}
	
}
