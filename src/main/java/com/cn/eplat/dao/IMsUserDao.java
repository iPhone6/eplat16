package com.cn.eplat.dao;

import java.util.List;

import com.cn.eplat.model.MsUser;
import com.cn.eplat.model.MsUserSSO;

public interface IMsUserDao {
	
	/**
	 * 获得所有微服务系统用户信息
	 * @return
	 */
	List<MsUser> getAllMsUsers();
	
	/**
	 * 获得所有微服务系统用户信息（联合 ms_sso表）
	 * @return
	 */
	List<MsUserSSO> getAllMsUsersWithMsSSO();
	
	/**
	 * 获得所有微服务系统用户信息2（ms_user表左连接 ms_sso表）
	 * @return
	 */
	List<MsUserSSO> getAllMsUsersByLeftJoinMsSSO();
	
	/**
	 * 只更新MsUser中的部分字段的值，Null值也接受
	 * @param msu
	 * @return
	 */
	int updateSomeFieldsOfMsUser(MsUser msu);
	
	/**
	 * 往ms_user表中添加一条数据
	 * @param msu
	 * @return
	 */
	int insertMsUser(MsUser msu);
	
	/**
	 * 往ms_sso表中添加一条数据
	 * @param mus
	 * @return
	 */
	int insertMsSSO(MsUserSSO mus);
	
}
