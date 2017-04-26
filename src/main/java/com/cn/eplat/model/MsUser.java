package com.cn.eplat.model;

import java.util.Date;
import java.util.UUID;

public class MsUser {
	
	private String id;	// 主键
	private String name;	// 成员名称。长度为1~64个字符
	private String orderInDepts;	// 在对应的部门中的排序, Map结构的json字符串, key是部门的Id, value是人员在这个部门的排序值
	private String position;	// 职位信息。长度为0~64个字符
	private String mobile;	// 手机号码。企业内必须唯一
	private Integer em_uid;	// 环信用户对应id
	private String tel;	// 分机号，长度为0~50个字符
	private String workPlace;	// 办公地点，长度为0~50个字符
	private String remark;	// 备注，长度为0~1000个字符
	private String email;	// 邮箱。长度为0~64个字符。企业内必须唯一
	private String jobnumber;	// 员工工号。对应显示到OA后台和客户端个人资料的工号栏目。长度为0~64个字符
	private Boolean isHide;	// 是否号码隐藏, true表示隐藏, false表示不隐藏。隐藏手机号后，手机号在个人资料页隐藏，但仍可对其发DING、发起钉钉免费商务电话。
	private Boolean isSenior;	// 是否高管模式，true表示是，false表示不是。开启后，手机号码对所有员工隐藏。普通员工无法对其发DING、发起钉钉免费商务电话。高管之间不受影响。
	private String extattr;	// 扩展属性，可以设置多种属性(但手机上最多只能显示10个扩展属性，具体显示哪些属性，请到OA管理后台->设置->通讯录信息设置和OA管理后台->设置->手机端显示信息设置)
	private Boolean enabled;	// 主键
	private Date create_time;	// 创建时间
	private String create_people;	// 创建人
	private Date update_time;	// 更新时间
	private String update_people;	// 更新人
	private String orgid;	// 关联ms_organization表的id
	private String level;	// 表示用户级别（按此顺序级别依次从低到高：1B、1A、2B、2A、3B、3A、……、8B、8A、9B、9A、……、13B、13A、14B、14A、……）
	private String company_code;	// 公司编码(华为那边对应的)
	private String dept_name;	// 部门名（暂时将部门名放在用户信息表中）
	private String project_name;	// 项目名（暂时将项目名放在用户信息表中）
	private String identity_no;	// 身份证号码
	private String type;	// 类型，不同的员工分属不同的类型。1-无小周末，2-有小周末
	private Boolean push2hw_atten;	// 表示是否要将当前用户的考勤数据推送给HW考勤系统
//	private String userid;	
	
	public MsUser() {
		id = UUID.randomUUID().toString().replace("-", "");
		create_time = new Date();
		update_time = new Date();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderInDepts() {
		return orderInDepts;
	}
	public void setOrderInDepts(String orderInDepts) {
		this.orderInDepts = orderInDepts;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getEm_uid() {
		return em_uid;
	}
	public void setEm_uid(Integer em_uid) {
		this.em_uid = em_uid;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobnumber() {
		return jobnumber;
	}
	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}
	public Boolean getIsHide() {
		return isHide;
	}
	public void setIsHide(Boolean isHide) {
		this.isHide = isHide;
	}
	public Boolean getIsSenior() {
		return isSenior;
	}
	public void setIsSenior(Boolean isSenior) {
		this.isSenior = isSenior;
	}
	public String getExtattr() {
		return extattr;
	}
	public void setExtattr(String extattr) {
		this.extattr = extattr;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getCreate_people() {
		return create_people;
	}
	public void setCreate_people(String create_people) {
		this.create_people = create_people;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getUpdate_people() {
		return update_people;
	}
	public void setUpdate_people(String update_people) {
		this.update_people = update_people;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getIdentity_no() {
		return identity_no;
	}
	public void setIdentity_no(String identity_no) {
		this.identity_no = identity_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getPush2hw_atten() {
		return push2hw_atten;
	}
	public void setPush2hw_atten(Boolean push2hw_atten) {
		this.push2hw_atten = push2hw_atten;
	}
	
	@Override
	public String toString() {
		return "MsUser [id=" + id + ", name=" + name + ", orderInDepts=" + orderInDepts + ", position=" + position + ", mobile=" + mobile + ", em_uid=" + em_uid + ", tel=" + tel
				+ ", workPlace=" + workPlace + ", remark=" + remark + ", email=" + email + ", jobnumber=" + jobnumber + ", isHide=" + isHide + ", isSenior=" + isSenior
				+ ", extattr=" + extattr + ", enabled=" + enabled + ", create_time=" + create_time + ", create_people=" + create_people + ", update_time=" + update_time
				+ ", update_people=" + update_people + ", orgid=" + orgid + ", level=" + level + ", company_code=" + company_code + ", dept_name=" + dept_name + ", project_name="
				+ project_name + ", identity_no=" + identity_no + ", type=" + type + ", push2hw_atten=" + push2hw_atten + "]";
	}
	
}
