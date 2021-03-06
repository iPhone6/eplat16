package com.cn.eplat.model;

import java.util.Date;
import java.util.UUID;

/**
 * 结合MsUser和MsSSO实体之后的对象
 * @author Administrator
 *
 */
public class MsUserSSO extends MsUser {
	
	private String sso_id;	// ms_sso表的主键id
	private String userid;
	private String password;
	private String origin_pwd;	// 原始密码
	private String ver_code;	// 验证码
	private Date vercode_time;	// 验证码的生成时间
	
	public MsUserSSO() {
		sso_id = UUID.randomUUID().toString().replace("-", "");
	}
	
	public MsUserSSO(MsUser mu) {
		super();
		if(mu != null) {
			this.setId(mu.getId());
			this.setName(mu.getName());
			this.setOrderInDepts(mu.getOrderInDepts());
			this.setPosition(mu.getPosition());
			this.setMobile(mu.getMobile());
			this.setEm_uid(mu.getEm_uid());
			this.setTel(mu.getTel());
			this.setWorkPlace(mu.getWorkPlace());
			this.setRemark(mu.getRemark());
			this.setEmail(mu.getEmail());
			this.setJobnumber(mu.getJobnumber());
			this.setIsHide(mu.getIsHide());
			this.setIsSenior(mu.getIsSenior());
			this.setExtattr(mu.getExtattr());
			this.setEnabled(mu.getEnabled());
			this.setCreate_time(mu.getCreate_time());
			this.setCreate_people(mu.getCreate_people());
			this.setUpdate_time(mu.getUpdate_time());
			this.setUpdate_people(mu.getUpdate_people());
			this.setOrgid(mu.getOrgid());
			this.setLevel(mu.getLevel());
			this.setCompany_code(mu.getCompany_code());
		}
	}
	
	public String getSso_id() {
		return sso_id;
	}
	public void setSso_id(String sso_id) {
		this.sso_id = sso_id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrigin_pwd() {
		return origin_pwd;
	}
	public void setOrigin_pwd(String origin_pwd) {
		this.origin_pwd = origin_pwd;
	}
	public String getVer_code() {
		return ver_code;
	}
	public void setVer_code(String ver_code) {
		this.ver_code = ver_code;
	}
	public Date getVercode_time() {
		return vercode_time;
	}
	public void setVercode_time(Date vercode_time) {
		this.vercode_time = vercode_time;
	}
	
	@Override
	public String toString() {
		return  super.toString() + ", MsSSO [sso_id=" + sso_id + ", userid=" + userid + ", password=" + password + ", origin_pwd=" + origin_pwd + ", ver_code=" + ver_code + ", vercode_time="
				+ vercode_time + "]";
	}
	
}
