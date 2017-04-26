package org.zsl.testmybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.eplat.dao.IEpAttenDao;
import com.cn.eplat.dao.IEpUserDao;
import com.cn.eplat.dao.IMsUserDao;
import com.cn.eplat.datasource.DataSourceContextHolder;
import com.cn.eplat.datasource.DataSourceType;
import com.cn.eplat.model.MsUser;
import com.cn.eplat.model.MsUserSSO;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMsUserDao {
	
	private static Logger logger = Logger.getLogger(TestMsUserDao.class);
	
	@Resource
	private IEpUserDao epUserDao;
	@Resource
	private IEpAttenDao epAttenDao;
	@Resource
	private IMsUserDao msUserDao;
	
	
	
	
	
	
	
	@Test
	public void testinsertMsSSO() {
		DataSourceContextHolder.setDbType(DataSourceType.SOURCE_MS);
		
		MsUserSSO mus = new MsUserSSO();
		mus.setCreate_people("jjjj");
		mus.setVer_code("oooppp看看");
		mus.setPassword("ppp33jfu");
		mus.setOrigin_pwd("8887744");
		
		try {
			msUserDao.insertMsSSO(mus);
		} catch (Exception e) {
			logger.error("插入数据失败, error_info = " + e.getMessage());
			return;
		}
		logger.info("插入数据成功！");
		
	}
	
	
	
	
	@Test
	public void testinsertMsUser() {
		DataSourceContextHolder.setDbType(DataSourceType.SOURCE_MS);
		
		MsUser mu = new MsUser();
//		mu.setId("dddd");
		mu.setMobile("1548788956");
		mu.setCompany_code("pppp");
		mu.setName("张三");
		
		msUserDao.insertMsUser(mu);
		
		logger.info("新增一条微服务SSO系统的用户信息成功");
		
	}
	
	
	
	
	
	@Test
	public void testgetAllMsUsersByLeftJoinMsSSO() {
		DataSourceContextHolder.setDbType(DataSourceType.SOURCE_MS);
		
		List<MsUserSSO> msus = msUserDao.getAllMsUsersByLeftJoinMsSSO();
		
		if(msus != null && msus.size() > 0) {
			System.out.println("查询成功");
			for(MsUserSSO mu:msus) {
				System.out.println(mu);
			}
			System.out.println("msus.size() = " + msus.size());
		} else {
			System.out.println("查询结果为空");
		}
	}
	
	@Test
	public void testgetAllMsUsersWithMsSSO() {
		
		DataSourceContextHolder.setDbType(DataSourceType.SOURCE_MS);
		
		List<MsUserSSO> msus = msUserDao.getAllMsUsersWithMsSSO();
		
		if(msus != null && msus.size() > 0) {
			System.out.println("查询成功");
			for(MsUserSSO mu:msus) {
//				System.out.println(mu.getId() + ", ");
				System.out.println(mu);
			}
			System.out.println("msus.size() = " + msus.size());
		} else {
			System.out.println("查询结果为空");
		}
		
	}
	
	
	@Test
	public void testgetAllMsUsers() {
		
		DataSourceContextHolder.setDbType(DataSourceType.SOURCE_MS);
		
		List<MsUser> msus = msUserDao.getAllMsUsers();
		
		if(msus != null && msus.size() > 0) {
			System.out.println("查询成功");
			for(MsUser mu:msus) {
				System.out.println(mu.getId() + ", ");
			}
			System.out.println("msus.size() = " + msus.size());
		} else {
			System.out.println("查询结果为空");
		}
		
	}
	
	
	
	
	
}
