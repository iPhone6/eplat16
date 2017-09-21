package com.cn.eplat.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.eplat.model.MachCheckInOut;

public interface IMachCheckInOutDao {
	
	// // 对本地Access数据库进行操作	// // 
	// 
	public List<MachCheckInOut> queryMachCheckInOutById(int id);
	
	public List<MachCheckInOut> queryMachCheckInOutByCheckTime(Date ct);
	// 根据打卡时间往后查询前100条打卡数据
	public List<MachCheckInOut> queryMachCheckInOutByCheckTimeTop100(Date ct);
	// 关联打卡数据表(CHECKINOUT)和用户信息表(USERINFO)，根据打卡时间往后查询前100条打卡数据
	public List<MachCheckInOut> queryMachCheckInOutAndUserInfoByCheckTimeTop100(Date ct);
	// 关联打卡数据表(CHECKINOUT)和用户信息表(USERINFO)，根据打卡时间往后查询前100条打卡数据，并排除掉部分userid的打卡数据
	public List<MachCheckInOut> queryMachCheckInOutAndUserInfoByCheckTimeExcludeSomeUseridsTop100(@Param("ct") Date ct, @Param("uids") List<Integer> userids);
	// 根据打卡机序列号，关联打卡数据表(CHECKINOUT)和用户信息表(USERINFO)，根据打卡时间往后查询前100条打卡数据，并排除掉部分userid的打卡数据
	public List<MachCheckInOut> queryMachCheckInOutAndUserInfoByCheckTimeExcludeSomeUseridsTop100ByMachSn(@Param("ct") Date ct, @Param("uids") List<Integer> userids, @Param("sn") String sn);
	// 根据打卡机序列号和打卡时间往后查询前100条打卡数据，并排除掉部分userid的打卡数据（只查打卡数据表(CHECKINOUT)，查询结果没有用户工号信息）
	public List<MachCheckInOut> queryMachCheckInOutByCheckTimeExcludeSomeUseridsTop100ByMachSn(@Param("ct") Date ct, @Param("uids") List<Integer> userids, @Param("sn") String sn);
	// 关联打卡数据表(CHECKINOUT)和用户信息表(USERINFO)，查询前100条打卡数据
	public List<MachCheckInOut> queryMachCheckInOutAndUserInfoTop100();
	// 根据打卡机序列号，关联打卡数据表(CHECKINOUT)和用户信息表(USERINFO)，查询前100条打卡数据
	public List<MachCheckInOut> queryMachCheckInOutAndUserInfoTop100ByMachSn(String sn);
	
	public List<MachCheckInOut> queryAllMachCheckInOut();
	
	/**
	 * 查询所有打卡机打卡数据的总条数
	 * @return
	 */
	int getAllMachCheckInOutCount();
	
	// 
	public List<MachCheckInOut> queryMachCheckInOutBySomeCriteria();
	
	/**
	 * 查出最早和最晚的打卡机打卡时间
	 * @return
	 */
	HashMap<String, Object> getEarliestAndLatestMachCheckTime();
	
	
	/**
	 * 根据给定的起始时间查出前100条打卡机打卡数据
	 * @param start_time
	 * @return
	 */
	List<MachCheckInOut> getTop100MachCheckInOutsByStartTime(@Param("start_time") Date start_time);
	
	/**
	 * 根据给定的起始时间和userid查出前100条打卡机打卡数据
	 * @param start_time
	 * @return
	 */
	List<MachCheckInOut> getTop100MachCheckInOutsByStartTimeAndUserid(@Param("start_time") Date start_time, @Param("userid") Integer userid);
	
	/**
	 * 根据给定的起始时间和userid查出前100条打卡机打卡数据2（修复Bug：已排除可能出现查出重复的打卡数据的情况）
	 * @param start_time
	 * @return
	 */
	List<MachCheckInOut> getTop100MachCheckInOutsByStartTimeAndUserid2(@Param("start_time") Date start_time, @Param("userid") Integer userid);
	
	/**
	 * 根据给定的起始时间和userid查出前100条打卡机打卡数据3（修复Bug：已排除可能出现查出重复的打卡数据的情况，并补充用户工号信息）
	 * @param start_time
	 * @return
	 */
	List<MachCheckInOut> getTop100MachCheckInOutsByStartTimeAndUserid3(@Param("start_time") Date start_time, @Param("userid") Integer userid);
	
	
	
	
	// // 对本地MySQL数据库进行操作	// // TODO: 对本地MySQL数据库操作虽说保证了服务器端的数据库用户名和密码不会泄露，但是要求每台打卡机的主机上要安装一个MySQL数据库。。。
	// 批量添加打卡机打卡数据
	public int batchInsertMachCheckInOut(List<MachCheckInOut> mcios);
	// 批量添加打卡机打卡数据（打卡时间有误的）
	public int batchInsertMachCheckInOutWithErrorCheckTime(List<MachCheckInOut> mcios);
	
	// 根据条件查询打卡机打卡数据
	public List<MachCheckInOut> queryMachCheckInOutByCriteria(MachCheckInOut mcio);
	
	// 根据普通条件和特殊条件查询打卡机打卡数据
	public List<MachCheckInOut> queryMachCheckInOutByNormalAndSpecialCriteria(MachCheckInOut mcio);
	
	// 根据id批量修改打卡机打卡数据
	public int batchUpdateMachCheckInOutById(List<MachCheckInOut> macios);
	
	// 查询id最大的打卡机打卡数据（也即是最后一条打卡机打卡数据）
	public MachCheckInOut queryMachCheckInOutWithMaxId();
	
	// 查询打卡时间最晚的打卡机打卡数据
	public List<MachCheckInOut> queryMachCheckInOutWithMaxCheckTime();
	
	// 根据打卡机序列号查询打卡时间最晚的打卡机打卡数据
	public List<MachCheckInOut> queryMachCheckInOutWithMaxCheckTimeByMachSn(String sn);
	
	// 在打卡记录推送记录表中查出最晚的一次打卡的打卡时间
	public Date queryLatestCheckTime();
	
	// 在传入的打卡数据列表中找出在本地MySQL数据库中没有的打卡数据
	public List<MachCheckInOut> queryMissedMachCheckInOutsWithGivenDatas(List<String> datas);
	
	// 从Access打卡数据拷贝表中找出在本地MySQL打卡数据推送日志表中没有的打卡数据
	public List<MachCheckInOut> queryMissedMachCheckInOutsByCompareAccessAndMySQLDatas();
	
	// 从Access打卡数据拷贝表中找出在本地MySQL打卡数据推送日志表中没有的打卡数据
	/**
	 * 从Access打卡数据拷贝表中找出在本地MySQL打卡数据推送日志表中没有的打卡数据（使用not exists）
	 * @return
	 */
	public List<MachCheckInOut> findMissedMachCheckInOutsByCompareAccessAndMySQLDatas();
	
	// 把全部Access的打卡数据批量插入本地MySQL的打卡数据拷贝表中
	public int batchInsertAllAccessCheckinoutsToMySQLMachCheckInOutCopy(List<MachCheckInOut> all_datas);
	
	// 删除全部打卡机Access打卡数据在MySQL数据库中的拷贝（即清空mach_chkio_copy表中的数据）
	public int deleteAllAccessCheckInOutsCopyInMySQL();
	
	/**
	 * 将最近一个月内推送时出现工号异常的打卡数据的推送状态末尾添加"_failed"标记（用于及时自动补救性重新推送这部分异常打卡数据）
	 * @return
	 */
	// 将最近一个月内推送时出现工号异常的打卡数据的推送状态末尾添加"_failed"标记（用于及时自动补救性重新推送这部分异常打卡数据）
	int appendFailedLabelToRecentMonthInvalidMachCheckInOuts();
	
	
	// // 对远程MySQL数据库进行操作	// // TODO: 配置文件中会暴露服务器上的数据库用户名和密码配置参数。。。
	// 批量添加打卡机打卡数据
	
	
	
	
}
