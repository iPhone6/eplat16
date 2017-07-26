package org.zsl.testmybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.eplat.dao.IEpUserDao;
import com.cn.eplat.dao.IMachCheckInOutDao;
import com.cn.eplat.dao.IPushFilterLogDao;
import com.cn.eplat.dao.IPushToHwDao;
import com.cn.eplat.datasource.DataSourceContextHolder;
import com.cn.eplat.datasource.DataSourceType;
import com.cn.eplat.model.PushToHw;
import com.cn.eplat.utils.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestPushToHwDao {
	

	private static Logger logger = Logger.getLogger(TestPushToHwDao.class);
	
	@Resource
	private IEpUserDao epUserDao;
	
	@Resource
	private IMachCheckInOutDao machCheckInOutDao;
	
	@Resource
	private IPushFilterLogDao pushFilterLogDao;
	
	@Resource
	private IPushToHwDao pushToHwDao;
	
	
	
	
	
	
	
	@Test
	public void testSqlServerDb() {
		
		List<PushToHw> res = null;
		try {
			DataSourceContextHolder.setDbType(DataSourceType.SOURCE_SQLSERVER);
			res = pushToHwDao.queryQcKqDatas();
		} catch (Exception e) {
			logger.error("查询全程OA系统考勤数据出现异常：error_info = " + e.getLocalizedMessage());
			return;
		}
		
		if(res != null && res.size() > 0) {
			logger.info("查询全程OA系统考勤数据成功~");
//			System.out.println(res);
			for(int i=0;i<10&&i<res.size();i++){
				System.out.println(res.get(i));
			}
		} else {
			logger.error("查询失败");
		}
		
	}
	
	
	@Test
	public void testfindNotPushedDatasByActualConditioin() {
		
		List<PushToHw> res = null;
		try {
			res = pushToHwDao.findNotPushedDatasByActualConditioin();
		} catch (Exception e) {
			logger.error("调用存储过程查询出现异常：error_info = " + e.getLocalizedMessage());
			return;
		}
		
		if(res != null && res.size() > 0) {
			logger.info("调用存储过程查询成功~");
			System.out.println(res);
		} else {
			logger.error("查询失败");
		}
		
	}
	
	
	
	
	
	
	@Test
	public void testBatchInsertPushToHws() {
		List<PushToHw> pthws = new ArrayList<PushToHw>();
		
		Date now = new Date();
		
		for(int i=0; i<5; i++) {
			PushToHw pthw = new PushToHw();
			pthw.setDayof_week("qqqqqqqqqq-" + i + "-ppppppp");
			pthw.setDayof_date(DateUtil.calcXDaysAfterADate(i, now));
			pthws.add(pthw);
		}
		
		Integer ret = pushToHwDao.batchInsertPushToHws(pthws);
		
		if(ret == null || ret <= 0) {
			logger.error("批量插入数据失败, ret = " + ret);
			return;
		}
		
		logger.info("批量插入数据成功, ret = " + ret);
		
	}
	
	
}
