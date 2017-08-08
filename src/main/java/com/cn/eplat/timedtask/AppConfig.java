package com.cn.eplat.timedtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//@Configuration
//@PropertySource("classpath:config/statics.properties")
public class AppConfig {
//	@Autowired
	Environment env;
//	@Autowired
	FlightTrainTask ftt;
	
//	@Bean
	public FlightTrainTask testBean(){
		ftt.setSolrURL(env.getProperty("solrURL"));
		return ftt;
	}
	
}
