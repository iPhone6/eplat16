package com.cn.eplat.timedtask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.util.TextUtils;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.eplat.consts.Constants;
import com.cn.eplat.dao.IEpUserDao;
import com.cn.eplat.dao.IPushLogDao;
import com.cn.eplat.dao.IPushToHwDao;
import com.cn.eplat.model.PushLog;
import com.cn.eplat.model.PushToHw;
import com.cn.eplat.utils.DateUtil;
import com.cn.eplat.utils.elead2huawei.Constant;
import com.cn.eplat.utils.elead2huawei.ExportData2HWHelper;
import com.cn.eplat.utils.elead2huawei.GetTokenHelper;

import elead.service.client.ServiceClient;

/**
 * 每天定时推送打卡数据
 */
@RunWith(SpringJUnit4ClassRunner.class)// 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
@Component
@PropertySource("classpath:schedule/tasks.props")	// 此处注解用于指定定时任务的定时参数的配置文件路径
public class PushToHwTask {
	
	private static Logger logger = Logger.getLogger(PushToHwTask.class);
	
	@Resource
	private IPushToHwDao pushToHwDao;

	@Resource
	private IPushLogDao pushLogDao;

	@Resource
	private IEpUserDao epUserDao;
	
	private List<PushToHw> pths;
	
	public static String real_push=Constants.REAL_PUSH;	// 表示是否开启真推送HW考勤数据（1表示开启真推送，0表示开启假推送）
										// TODO: 后面会用properties配置文件的方式来设置这个参数
	
	//将epuid-work_no缓存起来   key-epuid  value-work_no  
	private static Map<String,String>  workNumMap = new HashMap<String, String>();
	
	private static int push_times = 0;	// 筛选次数
	
	public static void addPushTimes() {	// 筛选次数加1
		push_times ++;
	}
	
	public static int getPush_times() {
		return push_times;
	}
	public static void setPush_times(int push_times) {
		PushToHwTask.push_times = push_times;
	}
	
	public List<PushToHw> getPths() {
		return pths;
	}
	public void setPths(List<PushToHw> pths) {
		this.pths = pths;
	}
	
//	@Scheduled(cron = "0 30 1 * * ? ") // "0 30 1 * * ?"      // （每天凌晨1点30分开始执行）(正式上线时用的定时设置)
	@Scheduled(cron = "${push_to_hw_task.schedule}")	// 通过读取配置文件中的参数设置定时任务
//	@Scheduled(cron = "0/8 * * * * ? ")     // （快速测试用定时设置。。。）
	public void pushDatasToHw() { // 将从数据库里查出来的数据组装成推送需要的数据
		long start_time = System.currentTimeMillis();	// 记录推送开始时间毫秒数
		logger.info("本次推送到华为,开始时间   "+DateUtil.formatDate(2, new Date()));
		
		List<PushToHw> allNeedsDatas = getPths();
		if(allNeedsDatas == null || allNeedsDatas.size() == 0) {
			allNeedsDatas = getAllNeedsDatas();
		}
		
		boolean realPush=true;
		if("1".equals(real_push)){
			realPush=true;
		}else{
			realPush=false;
		}
		
		// 用于存放批量推送HW时失败的数据列表（该列表中的数据已拆分成多个单条数据的ArrayList，以便重推失败数据时可以一条条地重推）
		List<ArrayList<Map<String, String>>> fail_lists = new ArrayList<>();
		
		if (allNeedsDatas != null && !allNeedsDatas.isEmpty()) {
			// 首先推送考勤数据到TimeSheet系统
			logger.info("开始推送考勤数据到TimeSheet系统-Start-");
			try {
				long push_timesheet_start_time = System.currentTimeMillis();	// 记录推送TimeSheet开始时间毫秒数
				ServiceClient.invokeElead(allNeedsDatas);	// 推送至华为业务运营系统
				long push_timesheet_end_time = System.currentTimeMillis();	// 记录推送TimeSheet结束时间毫秒数
				logger.info("本次推送TimeSheet系统共花费："+DateUtil.timeMills2ReadableStr(push_timesheet_end_time-push_timesheet_start_time));
			} catch (Exception e) {
				logger.error("推送至华为业务运营系统出现异常：error_info="+e.getMessage());
			}
			logger.info("推送考勤数据到TimeSheet系统结束-End-");
			
			// 然后推送考勤数据到华为考勤系统
			ArrayList<Map<String, String>> lists = new ArrayList<>();
			for (PushToHw pushToHw : allNeedsDatas) {
				if(Constants.STOP_FILTER_FLAG){
					lists.clear();
					logger.info("用户停止筛选操作--User stopped filter operation");
					break;
				}
				if (pushToHw.getOn_duty_time() != null) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("staffIdNo", pushToHw.getId_no());
					map.put("staffName", pushToHw.getName());
					map.put("swipeDate",DateUtil.formatDate(1, pushToHw.getDayof_date()));
					map.put("swipeTime",DateUtil.formatDate(2, pushToHw.getOn_duty_time()));
					map.put("week", pushToHw.getDayof_week());
					map.put("pth_id", String.valueOf(pushToHw.getId()));
					map.put("ep_uid", String.valueOf(pushToHw.getEp_uid()));
					map.put("company_code", pushToHw.getCompany_code());
					lists.add(map);
				}

				if (lists.size() == Constant.COUNTS_PER_REQUEST) {// 满足推送条数就推送出去
					pushLists(lists,fail_lists,realPush,true);
				}
				
				if (pushToHw.getOff_duty_time() != null) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("staffIdNo", pushToHw.getId_no());
					map.put("staffName", pushToHw.getName());
					map.put("swipeDate",DateUtil.formatDate(1, pushToHw.getDayof_date()));
					map.put("swipeTime",DateUtil.formatDate(2, pushToHw.getOff_duty_time()));
					map.put("week", pushToHw.getDayof_week());
					map.put("pth_id", String.valueOf(pushToHw.getId()));
					map.put("ep_uid", String.valueOf(pushToHw.getEp_uid()));
					map.put("company_code", pushToHw.getCompany_code());
					lists.add(map);
				}

				if (lists.size() == Constant.COUNTS_PER_REQUEST) {// 满足推送条数就推送出去
					pushLists(lists,fail_lists,realPush,true);
				}
			}
			
			if (!lists.isEmpty()) {
				pushLists(lists,fail_lists,realPush,true);
			}
		}else{
			logger.info("没有更多数据需要推送");
		}
		
		// 对批量推送过程中失败的数据列表进行一次（每次只推送一条数据的）重推操作
		if(fail_lists.size()>0&&!Constants.STOP_FILTER_FLAG){
			logger.info("本次批量推送HW过程中推送失败的数据条数为：fail_lists.size() = "+fail_lists.size());
			if(Constant.COUNTS_PER_REQUEST>1){	// 如果每次推送的数据条数大于1，则需要对推送失败的数据进行一次重推操作（一条条地重推）
				for(ArrayList<Map<String, String>> lists:fail_lists){
					pushLists(lists,fail_lists,realPush,false);
				}
			}else{
				logger.info("推送失败的数据已经是一条条推送了，无须再次重新推送！");
			}
			fail_lists.clear();	// 一条条重推完成之后，即清空推送失败数据列表
		}else{
			logger.info("本次批量推送HW过程中未出现推送失败的数据！");
		}
		
		if(getPths() != null) {
			setPths(null);	// 推送完成后，将待推送HW考勤系统的临时成员变量的值清空
		}
		
		long end_time = System.currentTimeMillis();	// 记录推送结束时间毫秒数
		logger.info("本次推送到华为,结束时间   "+DateUtil.formatDate(2, new Date()));
		logger.info("本次推送共花费时间：    "+DateUtil.timeMills2ReadableStr(end_time-start_time)+"    毫秒");
	}
	
	/**
	 * 推送数据列表操作
	 * @param lists
	 * @param realPush
	 * @param addFail	是否需要将推送失败的数据添加到失败列表中去的标志
	 */
	private void pushLists(ArrayList<Map<String, String>> lists, List<ArrayList<Map<String, String>>> fail_lists, boolean realPush, boolean addFail){
		if(lists==null||lists.isEmpty()) return;
		addPushTimes();
		logger.info("这是第    "+getPush_times()+"    次推送到华为，共推送   "+lists.size()+"    条数据");
		boolean isSuccess = ExportData2HWHelper.getInstance().insert2HW(lists, GetTokenHelper.getToken(), realPush);
		if(isSuccess){
			dealResult(lists, isSuccess, realPush);
		}else{
			if(addFail){
				dealWithFailedLists(lists,fail_lists);
			}else{
				dealResult(lists, isSuccess, realPush);
			}
		}
		lists.clear();
	}
	
	/**
	 * 处理推送失败的数据列表
	 * @param lists
	 */
	private void dealWithFailedLists(ArrayList<Map<String, String>> lists,List<ArrayList<Map<String, String>>> fail_lists){
		if(lists==null||lists.isEmpty()){
			return;
		}
		for(Map<String, String> data:lists){
			ArrayList<Map<String, String>> data_list=new ArrayList<>();
			data_list.add(data);
			fail_lists.add(data_list);
		}
	}
	
	//获取所有需要推送的数据
	public List<PushToHw> getAllNeedsDatas() {
		List<PushToHw> result = new ArrayList<PushToHw>();
		Date time = pushLogDao.getLatestPushLogTime();
		if (time == null) {
			List<PushToHw> allDatas = pushToHwDao.getPushToHwsByDate(null,null);// 获取所有的数据
			if (allDatas != null) {
				return allDatas;
			}
		} else {
			// 1.先去查所有之前推送失败的数据
			List<Long> pthIds = pushLogDao.getAllFailPthIds();
			if (pthIds != null && !pthIds.isEmpty()) {
				pushLogDao.deletePushLogsByPthids(pthIds);//先把日志表（push_log）里失败(也许带一条成功)了的数据删除
				List<PushToHw> failDatas = pushToHwDao.getPushToHwsByIds(pthIds);
				if (failDatas != null && !failDatas.isEmpty()) {
					result.addAll(failDatas);
				}
			}
			// 2.获取待推送的数据(截至到昨天)
			Date now_date = new Date();
			String yesterday_start_str = DateUtil.formatDate(1, DateUtil.calcXDaysAfterADate(-1, now_date));
			String yesterday_end_str = yesterday_start_str + " 23:59:59.999";
			Date yesterday_start = DateUtil.parse2date(1, yesterday_start_str);
			Date yesterday_end = DateUtil.parse2date(4, yesterday_end_str);
			
			List<PushToHw> waitingDatas = pushToHwDao.getPushToHwsByDate(yesterday_start, yesterday_end);
			if (waitingDatas != null && !waitingDatas.isEmpty()) {
				result.addAll(waitingDatas);
			}
		}
		return result;
	}

	// 处理推送结果
	public void dealResult(List<Map<String, String>> lists, boolean isSuccess, boolean realPush) {
		if (lists == null || lists.isEmpty())  return;
		List<PushLog> result = new ArrayList<PushLog>();
		if(!isSuccess){
			logger.error("出现推送HW考勤系统失败的数据！push_hw_failed_lists = "+lists);
		}
		for (Map<String, String> map : lists) {
			PushLog log = new PushLog();
			log.setName(map.get("staffName"));
			log.setPth_id(Long.valueOf(map.get("pth_id")));
			log.setResult(isSuccess);
			log.setTime(new Date());
			log.setReal_push(realPush);
			String ep_uid = map.get("ep_uid");
			String workNum = "<UnKownWorkNo>";
			if(workNumMap.containsKey(ep_uid) && !TextUtils.isEmpty(workNumMap.get(ep_uid))){
				workNum = workNumMap.get(ep_uid);
			}else{
				workNum = epUserDao.queryWorkNumById(Integer.valueOf(ep_uid));
				workNumMap.put(ep_uid, workNum);
			}
			log.setWork_no(workNum);
			result.add(log);
		}
		pushLogDao.insert2PushLog(result);
	}
}
