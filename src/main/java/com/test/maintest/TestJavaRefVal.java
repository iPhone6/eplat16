package com.test.maintest;

public class TestJavaRefVal {
	
	public void genStr(String strin){
		if(strin==null){
			strin="test2017";
		}
	}
	
	public static void main(String[] args) {
		TestJavaRefVal tnt=new TestJavaRefVal();
		String str=null;
		tnt.genStr(str);
		System.out.println("str_gen = "+str);
		
	}
	
	
}
