package com.cn.eplat.utils;

import java.util.Arrays;
import java.util.List;

public class MyListUtil<T> {
	
	private List<T> elements;
	private int currentIndex = -1;

	public List<T> getElements() {
		return elements;
	}
	public void setElements(List<T> elements) {
		this.elements = elements;
		if(elements != null ) {
			currentIndex = 0;
		} else {
			// 
		}
	}
	
	public MyListUtil() {
		
	}
	
	public MyListUtil(List<T> elements) {
		super();
		setElements(elements);
	}
	
	public List<T> getNextNElements(int n) {
		if(n < 0) {
			return null;
		}
		
		if(elements == null) {
			return null;
		}
		
		int total_num = elements.size();
		
		if(currentIndex >= total_num) {
			return null;
		}
		
		if(currentIndex + n <= total_num) {
			List<T> ret_list = elements.subList(currentIndex, currentIndex+n);
			currentIndex += n;
			return ret_list;
		}
		
		List<T> ret_list = elements.subList(currentIndex, total_num);
		currentIndex = total_num-1;
		return ret_list;
	}
	
	
	public static void main(String[] args) {
		Integer[] nums = {1,2,3,4,5,6,7,8,9,0,10,11,12,13,14,15,16,17,18,19,20};
		
		List<Integer> num_list = Arrays.asList(nums);
		
		MyListUtil<Integer> mlu = new MyListUtil<Integer>(num_list);
		
		List<Integer> res_list;
		
		int i=0, n = 22;
		do {
			i++;
			res_list = mlu.getNextNElements(n);
			System.out.println("第（" + i + "）次获取，res_list = " + res_list);
		} while (res_list != null && res_list.size() >= n);
		
	}
	
	
}
