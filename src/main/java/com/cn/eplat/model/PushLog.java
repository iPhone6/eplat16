package com.cn.eplat.model;

import java.util.Date;

/**
 *将考勤数据推送到华为端的日志实体类（记录推送成功或者失败） 
 */
public class PushLog {
	private Long id;	// 
	private String work_no;//员工工号
	private String name;//员工姓名
	private boolean result;//推送结果（成功/失败）
	private Date time;//推送时间
	private Long pth_id;//对应push_to_hw的id
	private Boolean real_push;	// 表示是否是真推送（1表示是真推送，0表示是假推送）
	
	public PushLog(){
		
	}
	
	public PushLog(String work_no, String name, boolean result, Date time,
			Long pth_id,Boolean real_push) {
		super();
		this.work_no = work_no;
		this.name = name;
		this.result = result;
		this.time = time;
		this.pth_id = pth_id;
		this.real_push=real_push;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWork_no() {
		return work_no;
	}
	public void setWork_no(String work_no) {
		this.work_no = work_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Long getPth_id() {
		return pth_id;
	}
	public void setPth_id(Long pth_id) {
		this.pth_id = pth_id;
	}
	public Boolean getReal_push() {
		return real_push;
	}
	public void setReal_push(Boolean real_push) {
		this.real_push = real_push;
	}
	
	@Override
	public String toString() {
		return "PushLog [id=" + id + ", work_no=" + work_no + ", name=" + name + ", result=" + result + ", time=" + time + ", pth_id=" + pth_id + ", real_push=" + real_push + "]";
	}
	
	
}
