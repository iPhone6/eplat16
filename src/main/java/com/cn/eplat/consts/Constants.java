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
	
	public static final int FILTER_PART_DATE_NUM = 1;	// 分批筛选考勤数据的每个批次的日期数量（天数）
	public static final int FILTER_PART_EPU_NUM = 20;	// 分批筛选考勤数据的每个批次的人员数量（人数）

	public static final int MACH_CHKIO_COPY_BATCH_NUM = 100;	// 分批插入打卡机服务器上的mach_chkio_copy表的每个批次数据量条数
	public static final int PUSH_TO_ALIYUN_MCIOS_NUM = 50;	// 分批推送到阿里云服务器的每个批次数据量条数
	
	private static boolean busy_finding_missing_not_uploaded_punch_card_datas = false;

	public static boolean isBusy_finding_missing_not_uploaded_punch_card_datas() {
		return busy_finding_missing_not_uploaded_punch_card_datas;
	}

	public static void setBusy_finding_missing_not_uploaded_punch_card_datas(boolean busy_finding_missing_not_uploaded_punch_card_datas) {
		Constants.busy_finding_missing_not_uploaded_punch_card_datas = busy_finding_missing_not_uploaded_punch_card_datas;
	}
	
}
