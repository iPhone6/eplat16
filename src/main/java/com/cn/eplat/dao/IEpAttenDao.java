package com.cn.eplat.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.cn.eplat.model.EpAtten;
import com.cn.eplat.model.EpAttenExport;
import com.cn.eplat.model.EpUser;
import com.cn.eplat.model.PushToHw;

public interface IEpAttenDao {
	// 添加一条打卡记录
	public int insertEpAtten(EpAtten epa);
	// 根据用户id查询该用户当天打卡记录中最后一次打卡记录是第几次打卡
	public Integer queryLastPunchCardCountToday(EpAtten epa);
	// 根据用户id和时间范围（给定天数）查询该用户在这个时间范围内的全部打卡记录信息
	public List<EpAtten> queryEpAttenByUidAndDayRange(@Param("uid") int uid, @Param("day_range") int day_range);
	// 根据打卡记录id查询打卡记录
	public EpAtten queryEpAttenById(Long id);
	// 根据打卡记录id集合查询打卡记录
	public List<EpAtten> queryEpAttenListByIds(@Param("ids") Set ids);
	
	//导出指定员工,指定日期范围内的考勤数据
	public List<EpAttenExport> queryAllEpAttenExportDatas(@Param("datas") List<String> emails,@Param("startDate") Date startDdate,@Param("endDate") Date endDate);
	
	// 根据给定的日期范围（开始日期和结束日期）查询打卡记录信息
	public List<EpAtten> queryEpAttenByStartDateAndEndDate(@Param("start_date") Date start_date, @Param("end_date") Date end_date);
	// 根据用户id和给定的日期范围（开始日期和结束日期）查询打卡记录信息
	public List<EpAtten> queryEpAttenByEpUidAndStartDateAndEndDate(@Param("ep_uid") int ep_uid, @Param("start_date") Date start_date, @Param("end_date") Date end_date);
	// 根据用户id和给定的日期范围（开始日期和结束日期）查询全部有效的打卡记录信息（注：“有效的”即表示is_valid字段的值为true，且打卡时间time字段的值不为NULL）
	public List<EpAtten> queryValidEpAttenByEpUidAndStartDateAndEndDate(@Param("ep_uid") int ep_uid, @Param("start_date") Date start_date, @Param("end_date") Date end_date);
	
	// 批量插入打卡机打卡数据
	public int batchInsertEpAttens(List<EpAtten> epas);
	
	// 根据给定日期列表和用户id列表查询出每个用户在这些日期中的最早一次和最晚一次有效/成功打卡的打卡时间（只查今天0点整之前的打卡数据）
	public List<HashMap<String, Object>> queryFirstAndLastPunchTimeValidByDatesAndEpUidsBeforeToday(@Param("dates") List<Date> dates, @Param("epus") List<EpUser> epus);
	
	// 根据给定日期列表和用户id列表查询出每个用户在这些日期中的最早一次和最晚一次有效/成功打卡的打卡时间（只查今天0点整之前的打卡数据）
	/**
	 * 根据给定日期列表和用户id列表查询出每个用户在这些日期中的最早一次和最晚一次有效/成功打卡的打卡时间（只查今天0点整之前的打卡数据）
	 * @param dates
	 * @param epuids
	 * @return
	 */
	public List<HashMap<String, Object>> getFirstAndLastPunchTimeValidByDatesAndEpUidsBeforeToday(@Param("dates") List<Date> dates, @Param("epuids") List<Integer> epuids);
	
	// 在所有打卡数据中，查询最早的一次和最晚的一次打卡数据的日期（只查今天0点之前的）
	public HashMap<String, Object> queryFirstAndLastPunchTimeValid();
	
	// 查出所有GPS打卡数据
	public List<EpAtten> queryAllGPSEpAttens();
	
	// 查出所有距离所有中心点的最近距离字段(gps_distance)的值为NULL的GPS打卡数据
	public List<EpAtten> queryAllGPSEpAttensWithNullGPSDistance();
	
	// 根据id修改打卡数据信息
	public int updateEpAttenById(EpAtten epa);
	
	// 修改指定日期的所有打卡数据的处理结果字段（proc_result）的值
	/**
	 * 修改指定日期的所有打卡数据的处理结果字段（proc_result）的值
	 * @param dates
	 * @return
	 */
	int updateEpAttenProcResultOfGivenDates(@Param("dates") List<Date> dates);
	
	/**
	 * 修改指定日期、指定用户的所有打卡数据的处理结果字段（proc_result）的值为指定值（由调用方法时的参数procResult设置）
	 * @param dates
	 * @return
	 */
	int updateEpAttenProcResultOfGivenDatesAndEpuids(@Param("dates") List<Date> dates, @Param("epuids") List<Long> epuids, @Param("procResult") String procResult);
	
	/**
	 * 修改指定日期、指定用户的所有打卡数据的处理结果字段（proc_result）的值为"filter_success"
	 * @param dates
	 * @return
	 */
	int updateEpAttenProcResultOfGivenDatesAndEpuids2FilterSuccess(@Param("dates") List<Date> dates, @Param("epuids") List<Long> epuids);
	
	/**
	 * 修改指定日期、指定用户的所有打卡数据的处理结果字段（proc_result）的值为"filter_error"
	 * @param dates
	 * @return
	 */
	int updateEpAttenProcResultOfGivenDatesAndEpuids2FilterError(@Param("dates") List<Date> dates, @Param("epuids") List<Long> epuids);
	
	/**
	 * 获取尚未做筛选处理的打卡数据数量
	 * @return
	 */
	int getNotProcessedEpAttenCount();
	
	/**
	 * 获取尚未做筛选处理的打卡数据的日期
	 * @return
	 */
	List<Date> getNotProcessedEpAttenDates();
	
	/**
	 * 获取尚未做筛选处理的打卡数据的用户id
	 * @return
	 */
	List<Integer> getNotProcessedEpAttenEpUids();
	
	/**
	 * 对剩余未做标记的考勤数据进行补充标记（以免在下次筛选操作时重复筛选这部分的考勤数据）
	 * @param dates
	 * @return
	 */
	int markRemainEpAttensByDates(@Param("dates") List<Date> dates);
	
	/**
	 * 根据筛选出来的考勤数据反向回溯找出数据来源
	 * @param pth 筛选出来的考勤数据
	 * @param on_off 要找的是上班卡或下班卡数据来源的区分变量（上班卡：1,下班卡：2）
	 * @return
	 */
	List<EpAtten> findSourceAttenByPthData(@Param("pth") PushToHw pth, @Param("on_off") int on_off);
	
}
