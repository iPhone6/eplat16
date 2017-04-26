package com.cn.eplat.datasource;

/**
 * @ClassName: DataSourceType 
 * @Description:数据库类型常量类
 * @author: libiao
 * @date: 2015-12-14 下午5:57:01
 */
public class DataSourceType {
	private String environment = EVIRONMENT_LOCAL;	// 初始运行环境设置为本地（local），其它运行环境有：开发（dev）、生产（prod）等，目前只考虑本地和生产两种环境
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public static final String EVIRONMENT_LOCAL = "local";
	public static final String EVIRONMENT_PROD = "prod";
	
	public static final String DB_ATTEN = "atten";	// 考勤系统数据库类型
	public static final String DB_MS = "ms";	// 微服务SSO系统数据库类型

	// 192.168.10.72:3307/100msh_admin
	public static final String SOURCE_ADMIN = "ds_admin";
	// 192.168.10.72:3308/100msh_partner
	public static final String SOURCE_PARTNER = "ds_partner";
	// 192.168.10.72:3309/100msh_mop
	public static final String SOURCE_MOP = "ds_mop";
	// 微服务数据库(eplat-ms) ds_ms
	public static final String SOURCE_MS = "ds_ms";
	// Access数据源：
	// D:/TempDataDpan/19.test_mdb/att2000.mdb
	public static final String SOURCE_ACCESS = "ds_access";
	
	public static final String getProdDsType(String environment, String db_type) {
		if(EVIRONMENT_LOCAL.equals(environment)) {
			if(DB_ATTEN.equals(db_type)) {
				return SOURCE_ADMIN;
			} else if(DB_MS.equals(db_type)) {
				return SOURCE_MS;
			} else {
				// 
				System.err.println("不支持的数据库类型：" + db_type);
			}
		} else if(EVIRONMENT_PROD.equals(environment)) {
			if(DB_ATTEN.equals(db_type)) {
				return SOURCE_MOP;
			} else if(DB_MS.equals(db_type)) {
				return SOURCE_MS;
			} else {
				// 
				System.err.println("不支持的数据库类型：" + db_type);
			}
		} else {
			// 
			System.err.println("暂不支持除local、prod之外的环境类型");
		}
		return null;
	}
	
}
