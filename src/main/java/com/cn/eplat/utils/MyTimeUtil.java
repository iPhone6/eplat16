package com.cn.eplat.utils;

import java.util.Date;

/**
 * 时间工具类
 * @author Administrator
 *
 */
public class MyTimeUtil {
	/**
	 * 判断给定的时间是否正处于合适的时间范围（按照一定的预先约定好的时间范围规则来判断）
	 * @param time
	 * @return
	 */
	public static boolean isTimeRight(Date time){
		if(time==null){
			return false;
		}
		// 预设的2个不推送打卡数据的时间范围（分别对应一个开始时间，一个结束时间。
		// 注意：这几个时间里面的年月日是没什么用的，在转换成要比较的时间字符串时会被忽略掉，所以不用关心这几个时间的年月日信息）
		Date start_time1 = DateUtil.parse2date(2, "2017-01-01 08:00:00");
		Date end_time1 = DateUtil.parse2date(2, "2017-01-01 08:45:00");
		Date start_time2 = DateUtil.parse2date(2, "2017-01-01 17:45:00");
		Date end_time2 = DateUtil.parse2date(2, "2017-01-01 18:30:00");
		String start_time_str1 = DateUtil.formatDate(5, start_time1);
		String end_time_str1 = DateUtil.formatDate(5, end_time1);
		String start_time_str2 = DateUtil.formatDate(5, start_time2);
		String end_time_str2 = DateUtil.formatDate(5, end_time2);
		String the_time_str = DateUtil.formatDate(5, time);
		if(the_time_str.compareTo(start_time_str1)<0 || the_time_str.compareTo(end_time_str2)>0 || 
				(the_time_str.compareTo(end_time_str1)>0 && the_time_str.compareTo(start_time_str2)<0)){
			return true;
		}
		return false;
	}
}
