package com.cn.zpxt.test.demo;

import java.util.Set;
import java.util.TreeSet;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cn.eplat.model.EpAtten;

public class TestPattern {
	
	// 判断一个字符串是不是非负整数
    private static boolean isNumber(String str) {
    	return str.matches("^[1-9]\\d*|0$");	// 匹配非负整数的正则表达式
    }

	public static void main(String[] args) {
		String str = "1.099";
		System.out.println(isNumber(str));
		
		Set<String> test_set=new TreeSet<>();
//		test_set.add(null);	// Set中不能存放null，会报空指针异常
		test_set.add("");
		test_set.add("adb");
		System.out.println(test_set);
		
		EpAtten epa=new EpAtten();
		epa.setId(999111l);
		epa.setEp_uid(909);
		epa.setGps_addr("hhhh米宓模9090");
		epa.setTime(new Date());
		System.out.println(JSON.toJSONString(epa, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat));
		
		
		
	}

}
