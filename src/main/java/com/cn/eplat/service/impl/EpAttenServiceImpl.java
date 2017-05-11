package com.cn.eplat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zsl.testmybatis.TestEpCenterAxisDao;

import com.cn.eplat.dao.IEpAttenDao;
import com.cn.eplat.dao.IEpCenterAxisDao;
import com.cn.eplat.dao.IEpUserDao;
import com.cn.eplat.model.EpAtten;
import com.cn.eplat.model.EpAttenExport;
import com.cn.eplat.model.EpAxis;
import com.cn.eplat.model.EpCenterAddr;
import com.cn.eplat.model.EpCenterAxis;
import com.cn.eplat.model.EpUser;
import com.cn.eplat.service.IEpAttenService;
import com.cn.eplat.utils.db2excel.DbToExcelUtil;

@Service("epAttenService")
@Transactional
public class EpAttenServiceImpl implements IEpAttenService {
	
	private static Logger logger = Logger.getLogger(EpAttenServiceImpl.class);
	
	@Resource
	private IEpUserDao epUserDao;
	@Resource
	private IEpAttenDao epAttenDao;
	@Resource
	private IEpCenterAxisDao epCenterAxisDao;
	
	@Override
	public int addEpAtten(EpAtten epa) {
		return epAttenDao.insertEpAtten(epa);
	}

	@Override
	public int getLastPunchCardCountByEpUid(EpAtten epa) {
		Integer ret = epAttenDao.queryLastPunchCardCountToday(epa);
		
		if(ret == null) {	// 如果查询当天没有打卡记录，或打卡记录的次数字段（count）都为null时，则返回0
			return 0;
		}
		
		return ret;
	}

	@Override
	public List<EpAtten> getEpAttenByUidAndDayRange(int uid, int day_range) {
		return epAttenDao.queryEpAttenByUidAndDayRange(uid, day_range);
	}

	@Override
	public EpAtten getEpAttenById(Long id) {
		return epAttenDao.queryEpAttenById(id);
	}

	@Override
	public List<EpAtten> getEpAttenByStartDateAndEndDate(Date start_date, Date end_date) {
		if(start_date == null || end_date == null) {	// 如果开始日期或结束日期为null，则直接返回null
			return null;
		}
		
		if(start_date.after(end_date)) {	// 如果开始日期晚于结束日期，则自动交换开始日期和结束日期
			Date tmp_date = start_date;
			end_date = tmp_date;
			start_date = end_date;
		}
		
		return epAttenDao.queryEpAttenByStartDateAndEndDate(start_date, end_date);
	}

	@Override
	public List<EpAtten> getEpAttenByEpUidAndStartDateAndEndDate(int ep_uid, Date start_date, Date end_date) {
		if(start_date == null || end_date == null) {	// 如果开始日期或结束日期为null，则直接返回null
			return null;
		}
		
		if(start_date.after(end_date)) {	// 如果开始日期晚于结束日期，则自动交换开始日期和结束日期
			Date tmp_date = start_date;
			end_date = tmp_date;
			start_date = end_date;
		}
		
		return epAttenDao.queryEpAttenByEpUidAndStartDateAndEndDate(ep_uid, start_date, end_date);
	}
	
	@Override
	public List<EpAtten> getValidEpAttenByEpUidAndStartDateAndEndDate(int ep_uid, Date start_date, Date end_date) {
		if(start_date == null || end_date == null) {	// 如果开始日期或结束日期为null，则直接返回null
			return null;
		}
		
		if(start_date.after(end_date)) {	// 如果开始日期晚于结束日期，则自动交换开始日期和结束日期
			Date tmp_date = start_date;
			start_date = end_date;
			end_date = tmp_date;
		}
		
		return epAttenDao.queryValidEpAttenByEpUidAndStartDateAndEndDate(ep_uid, start_date, end_date);
	}

	@Override
	public String getAllEpAttenExportDatas(List<String> emails,Date startDdate,Date endDate) {
		List<EpAttenExport> attenDatas = epAttenDao.queryAllEpAttenExportDatas(emails,startDdate,endDate);
		return DbToExcelUtil.exportAttendDatasToExcel(attenDatas);
	}

	@Override
	public int batchAddEpAttens(List<EpAtten> epas) {
		return epAttenDao.batchInsertEpAttens(epas);
	}

	@Override
	public List<HashMap<String, Object>> getFirstAndLastPunchTimeValidByDatesAndEpUidsBeforeToday(List<Date> dates, List<EpUser> epus) {
		return epAttenDao.queryFirstAndLastPunchTimeValidByDatesAndEpUidsBeforeToday(dates, epus);
	}

	@Override
	public HashMap<String, Object> getFirstAndLastPunchTimeValid() {
		return epAttenDao.queryFirstAndLastPunchTimeValid();
	}

	@Override
	public boolean isEpCenterAxisInitialized() {
		
		return EpCenterAxis.getCenter_num()>0?true:false;
	}

	@Override
	public void initializeCenterAxis() {
		if(isEpCenterAxisInitialized()) {
			logger.info("EpCenterAxis已经初始化了");
		} else {
			logger.info("开始初始化EpCenterAxis");
			List<EpAxis> allEpAxises = epCenterAxisDao.getAllEpAxises();
			if(allEpAxises != null && allEpAxises.size()>0){
				EpCenterAxis.setCenter_axis(allEpAxises);
				EpCenterAxis.setCenter_addr(new ArrayList<String[]>());
				for(EpAxis ea:allEpAxises) {
					List<String> center_addrs = new ArrayList<String>();
					List<EpCenterAddr> centerAxisAddrs = epCenterAxisDao.getCenterAxisAddrsByCenterId(ea.getId());
					for(EpCenterAddr eca:centerAxisAddrs){
						center_addrs.add(eca.getName());
					}
					EpCenterAxis.getCenter_addr().add(center_addrs.toArray(new String[]{}));
				}
				logger.info("初始化EpCenterAxis完成");
			}else{
				logger.error("中心点坐标个数为0");
			}
		}
	}
	
}
