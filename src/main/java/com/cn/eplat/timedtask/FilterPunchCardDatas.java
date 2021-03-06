package com.cn.eplat.timedtask;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cn.eplat.consts.Constants;
import com.cn.eplat.controller.EpDataController;
import com.cn.eplat.model.EpUser;
import com.cn.eplat.service.IEpAttenService;
import com.cn.eplat.service.IEpUserService;
import com.cn.eplat.service.IMachCheckInOutService;
import com.cn.eplat.service.IPushFilterLogService;
import com.cn.eplat.service.IPushToHwService;
import com.cn.eplat.utils.DateUtil;
import com.cn.eplat.utils.MyListUtil;

/**
 * 每天定时筛选打卡数据
 * 
 * @author Administrator
 *
 */
@Component
@PropertySource("classpath:schedule/tasks.props")	// 此处注解用于指定定时任务的定时参数的配置文件路径
public class FilterPunchCardDatas {
	
	private static Logger logger = Logger.getLogger(FilterPunchCardDatas.class);
	
	@Resource
	private IEpUserService epUserService;
	@Resource
	private IEpAttenService epAttenService;
	@Resource
	private IMachCheckInOutService machCheckInOutService;
	@Resource
	private IPushToHwService pushToHwService;
	@Resource
	private IPushFilterLogService pushFilterLogService;
	@Resource
	private EpDataController epDataController;
	
	private static List<EpUser> epus_valid = new ArrayList<EpUser>();
	
	private static TreeMap<Integer, EpUser> qc_users = null;
	
	private static int filter_times = 0;	// 筛选次数
	
//	@Value("#{testdata}")
	public String testdata;
	
	public static void addFilterTimes() {	// 筛选次数加1
		filter_times ++;
	}
	
	public static int getFilter_times() {
		return filter_times;
	}
	public static void setFilter_times(int filter_times) {
		FilterPunchCardDatas.filter_times = filter_times;
	}
	
	public static List<EpUser> getEpus_valid() {
		return epus_valid;
	}
	public static void setEpus_valid(List<EpUser> epus_valid) {
		FilterPunchCardDatas.epus_valid = epus_valid;
	}
	public static TreeMap<Integer, EpUser> getQc_users() {
		return qc_users;
	}
	public static void setQc_users(TreeMap<Integer, EpUser> qc_users) {
		FilterPunchCardDatas.qc_users = qc_users;
	}
	
	//	@Scheduled(cron = "0 0 1 * * ? ")	// "0 0 1 * * ?"  （每天凌晨1点整开始执行）(正式上线时用的定时设置)
	@Scheduled(cron = "${filter_punch_card_datas.schedule}")	// 通过读取配置文件中的参数设置定时任务
//	@Scheduled(cron = "0/5 * * * * ? ")	// （快速测试用定时设置。。。）
	public void filter() {
		System.out.println("执行定时筛选任务。。。, testdata = "+testdata);
		long start_time = System.currentTimeMillis();	// 记录筛选开始时间毫秒数
		Date now_time = new Date();
		FilterPunchCardDatas.addFilterTimes();
		logger.info("当前是第（" + FilterPunchCardDatas.getFilter_times() + "）次筛选考勤数据，开始时间：" + DateUtil.formatDate(2, now_time));
		
		if(Constants.STOP_FILTER_FLAG){	// 在定时筛选操作开始前，如果是否停止筛选操作的标志值为true，
			Constants.STOP_FILTER_FLAG=false;	// 则首先将该标志变量的值设为false，以防后续定时筛选过程提前终止。
		}
		
		// 从全程OA系统中获取最新用户信息，并硬更新到本地MySQL数据库中
		List<EpUser> epus_valid = refreshQcoaUsers(true);
		if(epus_valid!=null&& epus_valid.size()>0){
		}else{
			logger.error("全程OA系统用户信息条数为0");
			return;
		}
		
		// // 1. 正常情况下筛选打卡数据（正常情况下，只筛选前一天的打卡数据）
		Date earliest_pfl_time = pushFilterLogService.getEarliestPushFilterLogTime();
		
		if(earliest_pfl_time == null) {
			HashMap<String, Object> first_last_punch_time = epAttenService.getFirstAndLastPunchTimeValid();
			
			if(first_last_punch_time == null) {
				// 历史有效打卡数据为空
				logger.error("历史有效打卡数据为空。。。");
			} else {
				Object first_punch_time_obj = first_last_punch_time.get("first_punch_time");
				Object last_punch_time_obj = first_last_punch_time.get("last_punch_time");
				
				if(first_punch_time_obj instanceof Timestamp && last_punch_time_obj instanceof Timestamp) {
					Date first_punch_time = (Date) first_punch_time_obj;
					Date last_punch_time = (Date) last_punch_time_obj;
					
					int hist_filter_ret = epDataController.filterPush2HwAttenOperation(first_punch_time, last_punch_time);
					
					if(hist_filter_ret > 0) {
						logger.info("筛选准备推送华为的考勤数据（历史数据）成功, hist_filter_ret = " + hist_filter_ret);
					} else {
						logger.error("筛选准备推送华为的考勤数据（历史数据）失败, hist_filter_ret = " + hist_filter_ret);
					}
					
				} else {
					logger.error("今天0点之前的历史打卡数据中，最早和最晚一次有效打卡时间出现null异常。。。");
				}
			}
		} else {
			Date yesterday = DateUtil.calcXDaysAfterADate(-1, now_time);
			
			int new_filter_ret = epDataController.filterPush2HwAttenOperation(yesterday, yesterday);
			
			if(new_filter_ret > 0) {
				logger.info("筛选准备推送华为的考勤数据（新数据）成功, new_filter_ret = " + new_filter_ret);
			} else if(new_filter_ret == -6 || new_filter_ret == -8) {
				logger.info("没有更多新的考勤打卡数据需要筛选了, new_filter_ret = " + new_filter_ret);
			} else {
				logger.error("筛选准备推送华为的考勤数据（新数据）失败, new_filter_ret = " + new_filter_ret);
			}
		}
		
		// // 2. 探测是否有异常情况（有打卡数据的proc_result字段的值为NULL时），如有异常情况，则进行重新筛选，并推送华为考勤系统
		int p2hw_abnormal = epDataController.filterPush2HwAttenOperationAbnormal();
		
		if(p2hw_abnormal == 0) {
			logger.info("没有异常情况的打卡数据需要筛选");
		} else {
			logger.info("已处理异常情况下的打卡数据，p2hw_abnormal = " + p2hw_abnormal);
		}
		
		if(Constants.STOP_FILTER_FLAG){	// 在定时筛选操作结束后，如果是否停止筛选操作的标志值为true，
			Constants.STOP_FILTER_FLAG=false;	// 则首再次将该标志变量的值设为false，以防后续筛选过程提前终止。
		}
		
		long end_time = System.currentTimeMillis();
		long use_time = (end_time - start_time);
		
		System.out.println("第（" + FilterPunchCardDatas.getFilter_times() + "）次筛选考勤数据，结束时间：" + DateUtil.formatDate(2, new Date()) + 
				"，本次筛选耗时：" + DateUtil.timeMills2ReadableStr(use_time) + " (over)");
		
		
	}
	
	
	/**
	 * 刷新全程OA系统用户信息
	 * @param hardRefresh 表示是否需要硬刷新（即是否需要把从SQL Server数据库中得到的最新的用户信息写入本地MySQL数据中）
	 * @return
	 */
	public List<EpUser> refreshQcoaUsers(boolean hardRefresh){
		List<EpUser> epus_valid = FilterPunchCardDatas.getEpus_valid();
		boolean softRefresh = epus_valid==null||epus_valid.size()==0;	// 表示是否需要软更新用户信息的标志
		if(hardRefresh||softRefresh){
			TreeMap<Integer, EpUser> qc_users = epDataController.getEpusValidQCOA(epus_valid);
			FilterPunchCardDatas.setEpus_valid(epus_valid);
			FilterPunchCardDatas.setQc_users(qc_users);
		}
		if(hardRefresh){
			hardRefreshQcoaUsers2LocalMySqlDb(epus_valid);
		}else{
		}
		return epus_valid;
	}
	
	/**
	 * 将全程OA用户信息硬更新写入本地MySQL数据库（写入前先清空本地MySQL数据库中的用户信息）
	 */
	private void hardRefreshQcoaUsers2LocalMySqlDb(List<EpUser> epus_valid){
		// 从全程OA系统中获取最新用户信息，并更新到本地MySQL数据库中
		int del_count = epUserService.deleteAllEpUsers();	// 清空本地MySQL数据库中的全部用户信息
		logger.info("已删除"+del_count+"条用户信息");
		int part_num = Constants.QCOA_PART_EPU_NUM;
		MyListUtil<EpUser> epu_mlu = new MyListUtil<EpUser>(epus_valid);
		List<EpUser> part_epus=null;
		epu_mlu.setCurrentIndex(0);
		
		int insert_count=0;
		boolean flag=false;	// 标志是否还有需要写入本地MySQL数据库中的用户信息数据
		do {
			part_epus = epu_mlu.getNextNElements(part_num);
			flag=part_epus != null && part_epus.size() > 0;
			if(flag){
				try {
					insert_count += epUserService.batchInsertEpUsersQCOA(part_epus);	// 把查出的最新用户信息写入本地MySQL数据库
				} catch (Exception e) {
					System.err.println("error_part_epus = "+JSON.toJSONString(part_epus) );
					logger.error("将用户信息写入MySQL数据库时出现异常，error_info = "+e.getMessage());
				}
			}
		}while (flag);
		
		logger.info("======== qc_users.size() = "+qc_users.size()+", del_count = "+del_count+", insert_count = "+insert_count+" ========");
		
	}
	
}
