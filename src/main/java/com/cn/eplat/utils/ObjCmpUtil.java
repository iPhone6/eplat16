package com.cn.eplat.utils;

/**
 * 对象比较工具类
 * @author Administrator
 *
 */
public class ObjCmpUtil {
	
	/**
	 * 比较两个对象是否相同
	 * @param obj1	对象1
	 * @param obj2	对象2
	 * @return	(1) 如果两个对象都是null，则返回true；<br/>
	 * (2) 如果两个对象中有一个是null，另一个不是null，则返回false；<br/>
	 * (3) 如果两个对象都不是null，则返回调用对象1的equals()方法比较对象2的结果。<br/>
	 */
	public static boolean compare(Object obj1, Object obj2) {
		if(obj1 == null && obj2 == null) {
			return true;
		}
		
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
