package com.cn.eplat.timedtask;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class FlightTrainTask {
//	@Value("#{constants['solrURL']}")
	private String solrURL;
	
	public String getSolrURL() {
		return solrURL;
	}
	public void setSolrURL(String solrURL) {
		this.solrURL = solrURL;
	}
	
//	@Value("#{constants[solrURL]}")
	public void setSurl(Properties prop){
		this.solrURL=prop.getProperty("solrURL");
	}
	
//	@Scheduled(cron = "0/5 * * * * ? ")	// 间隔5秒执行
	public void taskCycle() {
		System.out.println("使用SpringMVC框架配置定时任务, solrURL="+solrURL);
	}
}
