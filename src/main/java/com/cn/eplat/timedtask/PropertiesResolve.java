package com.cn.eplat.timedtask;

//import org.slf4j.Logger;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//REF: http://ask.csdn.net/questions/230400
//运用注解读取配置文件
@Component
@ComponentScan
@Configuration
@PropertySource({ "classpath:info.properties" })
public class PropertiesResolve {
	private static Logger logger = Logger.getLogger(PropertiesResolve.class.getName());
	// 把配置文件的属性值直接注解到类的属性里面
	@Value("${mongodb.url}")
	// url
	static String mongodbUrl;
	@Value("${mongodb.name}")
	// name
	static String mongodbName;
	@Value("${mongodb.password}")
	// password
	static String mongodbPassword;
	
//	@Value("#{constants.solrURL}")
	private String solrURL;
	
	public void setSolrURL(String solrURL){
		this.solrURL=solrURL;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

//	@Scheduled(cron = "${time1}")
	public void test1() {
		logger.info("1111111111111111111111111");
		System.out.println("mongodbUrl的值: " + mongodbUrl);
		System.out.println("mongodbName的值: " + mongodbName);
		System.out.println("mongodbPassword的值: " + mongodbPassword);
		
		System.out.println("solrURL = "+solrURL);
	}

	// *************************************************************************************
	@Autowired
	private static Environment env;

//	@Scheduled(cron = "${time2}")
	public static void test2() {
		logger.info("2222222222222222222222");
		System.out.println(env.getProperty("mongodb.url"));
		System.out.println(env.getProperty("mongodb.name"));
		System.out.println(env.getProperty("mongodb.password"));
	}
}
