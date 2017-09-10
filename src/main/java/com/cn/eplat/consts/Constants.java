package com.cn.eplat.consts;

import com.cn.eplat.datasource.DataSourceType;

public class Constants {
	public static final String ANDROID_APKS_DIR = "/ROOT/app";//andorid apk上传到服务器上的目录路径(当前项目下)
	public static final String ANDROID_APKS_URL_ALUYUN = "https://hr.e-lead.cn:8443/app/";//andorid apk的下载路径(aliyun正是环境)
	public static final String ANDROID_APKS_URL_TEST = "http://192.168.1.225:8080/app/";//andorid apk的下载路径(测试环境)
	public static final String ANDROID_APKS_URL_LOCAL = "http://192.168.1.180:8080/app/";//andorid apk的下载路径(本地环境)
//	public static final String ANDROID_APKS_URL = ANDROID_APKS_URL_LOCAL;
//	public static final String ANDROID_APKS_URL = ANDROID_APKS_URL_TEST;
	public static final String ANDROID_APKS_URL = ANDROID_APKS_URL_ALUYUN;
	
//	public static final String RUN_ENVIRONMENT = DataSourceType.EVIRONMENT_LOCAL;	// 本地运行环境类型
	public static final String RUN_ENVIRONMENT = DataSourceType.EVIRONMENT_PROD;	// 生产运行环境类型
	
	public static final String REAL_PUSH = "1";	// 表示是否开启真推送HW考勤数据（1表示开启真推送，0表示开启假推送）
	
	public static final int FILTER_PART_DATE_NUM = 1;	// 分批筛选考勤数据的每个批次的日期数量（天数）
	public static final int FILTER_PART_EPU_NUM = 100;	// 分批筛选考勤数据的每个批次的人员数量（人数）
	public static final int QCOA_PART_EPU_NUM = 100;	// 分批处理全程OA系统的每个批次的人员数量（人数）

	public static final int MACH_CHKIO_COPY_BATCH_NUM = 100;	// 分批插入打卡机服务器上的mach_chkio_copy表的每个批次数据量条数
	public static final int PUSH_TO_ALIYUN_MCIOS_NUM = 50;	// 分批推送到阿里云服务器的每个批次数据量条数
	
	public static final long TOKEN_VALID_TIME = 30*60*1000l;	// 设置token有效时长为30分钟（实际有效期是1小时，设置一个小于1小时的有效期主要是为了防止token在未来不确定的异常情况下提前失效）
	
	public static final int RETRY_PUSH_TO_HW_TIMES = 2;	// 重试推送到HW考勤系统次数
	
	public static boolean STOP_FILTER_FLAG = false;	// 停止筛选操作的标志（初值为false，表示不停止筛选操作，若其值被设为true，则立即停止筛选操作）
	
	private static boolean busy_finding_missing_not_uploaded_punch_card_datas = false;

	public static boolean isBusy_finding_missing_not_uploaded_punch_card_datas() {
		return busy_finding_missing_not_uploaded_punch_card_datas;
	}

	public static void setBusy_finding_missing_not_uploaded_punch_card_datas(boolean busy_finding_missing_not_uploaded_punch_card_datas) {
		Constants.busy_finding_missing_not_uploaded_punch_card_datas = busy_finding_missing_not_uploaded_punch_card_datas;
	}
	
}
