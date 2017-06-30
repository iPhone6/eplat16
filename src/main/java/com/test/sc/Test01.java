package com.test.sc;

import java.util.Arrays;
import java.util.List;

public class Test01 {
	
	public static void main(String[] args) {
		String[] strs=null;
		strs="".split(";");
		List<String> strs_list = Arrays.asList(strs);
		if(strs_list!=null){
			System.out.println("strs_list不为空,strs_list="+strs_list);
			System.out.println("strs_list.size="+strs_list.size());
			System.out.println("空字符串.equals(strs_list.get(0))="+"".equals(strs_list.get(0)));
			System.out.println("strs不为空,strs="+strs);
			System.out.println("strs.length="+strs.length);
		}else{
			System.out.println("strs_list=null");
		}
	}
	
}
