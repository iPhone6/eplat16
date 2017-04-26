package com.cn.eplat.utils;

/**
 * 对象比较工具类
 * @author Administrator
 *
 */
public class ObjCmpUtil {
	
	public static boolean compare(Object obj1, Object obj2) {
		if(obj1 == null || obj2 == null) {
			return false;
		}
		
		if(obj1 == obj2) {	// 如果两个对象的引用相同，则认为这两个对象是同一个类的实例
			return true;
		}
		
		if(obj1.getClass() == obj2.getClass()) {
			return obj1.equals(obj2);
		}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		String str1 = "123";
		String str2 = "123";
		
		System.out.println(ObjCmpUtil.compare(str1, str2));
		
	}
	
	
}
