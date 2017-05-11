package com.cn.eplat.conf;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cn.eplat.service.IEpAttenService;
import com.cn.eplat.utils.LocationUtil;

@Component("BeanDefineConfigue")
public class BeanDefineConfigue implements ApplicationListener<ContextRefreshedEvent> {
	
	private static Logger logger = Logger.getLogger(BeanDefineConfigue.class);
	
	@Autowired
	private IEpAttenService epAttenService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("进入BeanDefineConfigue的事件监听器");
		if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
			if(!epAttenService.isEpCenterAxisInitialized()){
				logger.info("尚未初始化EpCenterAxis");
				epAttenService.initializeCenterAxis();
				LocationUtil.initializeCenterInfos();
				logger.info("初始化EpCenterAxis完成");
			} else {
				logger.info("已初始化EpCenterAxis");
			}
		}else{
			logger.info("非容器初始化完成事件, applicationContext.displayName = "+event.getApplicationContext().getDisplayName());
		}
	}

}
