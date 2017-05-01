package com.cn.eplat.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cn.eplat.model.PushToHw;

public interface IPushToHwDao {
	
	// 批量插入准备推送华为考勤系统的数据
	public Integer batchInsertPushToHws(List<PushToHw> pthws);
	
	//根据日期获取当天的所有要导入到华为的考勤数据
	public List<PushToHw> getPushToHwsByDate(@Param("startdate") Date startdate,@Param("enddate") Date enddate);
	
	//根据ids查找数据
	public List<PushToHw> getPushToHwsByIds(List<Long> ids);
	
	
	// 按名字查找某个人的所有推送数据
	List<PushToHw> findPushToHwsByName(String name);
	
	// 按序号筛选推送数据（从开始到结束序号）
	List<PushToHw> getPushToHwsByIdSeq(@Param("idSeqStart") Long idSeqStart, @Param("idSeqEnd") Long idSeqEnd);
	
	/**
	 * 查出未推送HW考勤系统的数据
	 * @return
	 */
	List<PushToHw> findNotPushedDatas();
	
	/**
	 * 查出未推送HW考勤系统的数据（不包括昨天的，也就是昨天之前的部分）
	 * @return
	 */
	List<PushToHw> findNotPushedDatasBeforeYesterday();
	
	/**
	 * 查出未推送HW考勤系统的数据（根据具体情况灵活判断是否包含昨天的筛选出来的未推送考勤数据：当前时间如果已经过了当天01:30，则返回结果要包含昨天的数据；否则不包含昨天的数据。）
	 * @return
	 */
	List<PushToHw> findNotPushedDatasByActualConditioin();
	
}
