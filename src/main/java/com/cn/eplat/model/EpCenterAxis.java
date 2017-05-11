package com.cn.eplat.model;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class EpCenterAxis {
	
	private static int center_num = 0;
	private static List<EpAxis> center_axis;
	private static List<String[]> center_addr;
	
	public static int getCenter_num() {
		return center_num;
	}
//	public static void setCenter_num(int center_num) {
//		EpCenterAxis.center_num = center_num;
//	}
	public static List<EpAxis> getCenter_axis() {
		return center_axis;
	}
	public static void setCenter_axis(List<EpAxis> center_axis) {
		if(center_axis != null && center_axis.size() > 0) {
			EpCenterAxis.center_axis = center_axis;
			EpCenterAxis.center_num = center_axis.size();
		} else {
			System.err.println("初始化中心点坐标为null异常");
		}
	}
	public static List<String[]> getCenter_addr() {
		return center_addr;
	}
	public static void setCenter_addr(List<String[]> center_addr) {
		EpCenterAxis.center_addr = center_addr;
	}
	
}
