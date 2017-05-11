package org.zsl.testmybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.eplat.dao.IEpAttenDao;
import com.cn.eplat.dao.IEpCenterAxisDao;
import com.cn.eplat.dao.IEpUserDao;
import com.cn.eplat.model.EpAxis;
import com.cn.eplat.model.EpCenterAddr;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestEpCenterAxisDao {
	private static Logger logger = Logger.getLogger(TestEpCenterAxisDao.class);
	
	@Resource
	private IEpUserDao epUserDao;
	@Resource
	private IEpAttenDao epAttenDao;
	
	@Resource
	private IEpCenterAxisDao epCenterAxisDao;
	
	
	
	
	
	
	@Test
	public void testgetCenterAxisAddrsByCenterId() {
		int centerId = 1;
		List<EpCenterAddr> axisAddrs = epCenterAxisDao.getCenterAxisAddrsByCenterId(centerId);
		if(axisAddrs != null && axisAddrs.size() > 0) {
			logger.info("查询成功！");
			for(EpCenterAddr eca:axisAddrs) {
				System.out.println("axisAddr = " + eca);
			}
		} else {
			logger.error("查询失败！");
		}
	}
	
	
	
	
	@Test
	public void testgetAllEpAxises() {
		List<EpAxis> allEpAxises = epCenterAxisDao.getAllEpAxises();
		if(allEpAxises != null && allEpAxises.size() > 0) {
			logger.info("查询成功!");
			for(EpAxis ea:allEpAxises) {
				System.out.println("ea = " + ea);
			}
		} else {
			logger.info("查询失败!");
		}
	}
	
	
	
	
	@Test
	public void testgetNotProcessedEpAttenEpUids() {
		
		List<Integer> epuids = null;
		try {
			epuids = epAttenDao.getNotProcessedEpAttenEpUids();
		} catch (Exception e) {
			logger.error("查询出现异常，error_info = " + e.getMessage());
		}
		
		if(epuids != null && epuids.size() > 0) {
			logger.info("查询成功！");
			for(Integer uid:epuids) {
				System.out.print(uid + ", ");
			}
		} else {
			logger.error("查询失败！");
		}
		
	}
}
