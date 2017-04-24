package com.cn.eplat.model;

import java.util.Date;

/**
 * 考勤打卡记录实体类
 * 
 * @author Administrator
 *
 */
public class EpAtten {
	private Long id;	// 主键id（由于是记录每个员工的考勤打卡的流水记录，所以编号可能会累加得很快，故采用bigint大整形数据类型）
	private Integer ep_uid;	// 公司员工用户id
	private String type;	// 打卡方式（主要包括GPS定位经纬度方式和连接的WiFi MAC地址方式，以及新加入的打卡机方式，打卡机打卡又分为IC卡和指纹两种方式。）
	private Double latitude;	// 纬度值（精确到小数点后15位）
	private Double longtitude;	// 经度值（精确到小数点后15位）
	private Double gps_distance;	// 用来存放计算得到的离所有中心点坐标最近的一个计算距离值（精确到小数点后5位，单位：米）
	private String gps_addr;	// GPS打卡方式下，经纬度对应的地理位置信息
	private String wifi_mac;	// 所连接的路由器的WiFi MAC地址
	private String wifi_name;	// 打卡时WiFi的名字
	private Boolean is_valid;	// 用于指示打卡是否有效（如果是GPS定位方式，则表示经纬度是否在有效范围内(以公司所在地点为中心的半径为200米的圆形区域内为有效范围)；如果是WiFi打卡方式，则表示所连接的WiFi MAC地址是否正确）
	private Date time;	// 打卡时间（精确到秒）
	private int count;	// 表示当天第几次打卡
	private String platform;	// 表示打卡时所使用的平台（目前主要是iOS和Android两大平台）
	private String mach_sn;	// 打卡机序列号
	private Date fetch_time;	// 接收远程推送过来的打卡机打卡数据的时间
	private String proc_result;	// 处理结果：主要用来标记是否已经经过筛选处理，即每天凌晨1点要对0点前的所有打卡数据进行筛选的操作。其值包括：filter_success, filter_failed，分别表示筛选成功和筛选失败，如果值为NULL则表示尚未筛选。
	private Integer proc_count;	// 处理次数计数：用于记录进行筛选处理的次数
	private Date proc_time;		// 最近一次的处理时间：用于记录最后一次进行筛选处理的时间信息
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getEp_uid() {
		return ep_uid;
	}
	public void setEp_uid(Integer ep_uid) {
		this.ep_uid = ep_uid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}
	public Double getGps_distance() {
		return gps_distance;
	}
	public void setGps_distance(Double gps_distance) {
		this.gps_distance = gps_distance;
	}
	public String getGps_addr() {
		return gps_addr;
	}
	public void setGps_addr(String gps_addr) {
		this.gps_addr = gps_addr;
	}
	public String getWifi_mac() {
		return wifi_mac;
	}
	public void setWifi_mac(String wifi_mac) {
		this.wifi_mac = wifi_mac;
	}
	public String getWifi_name() {
		return wifi_name;
	}
	public void setWifi_name(String wifi_name) {
		this.wifi_name = wifi_name;
	}
	public Boolean getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(Boolean is_valid) {
		this.is_valid = is_valid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getMach_sn() {
		return mach_sn;
	}
	public void setMach_sn(String mach_sn) {
		this.mach_sn = mach_sn;
	}
	public Date getFetch_time() {
		return fetch_time;
	}
	public void setFetch_time(Date fetch_time) {
		this.fetch_time = fetch_time;
	}
	public String getProc_result() {
		return proc_result;
	}
	public void setProc_result(String proc_result) {
		this.proc_result = proc_result;
	}
	public Integer getProc_count() {
		return proc_count;
	}
	public void setProc_count(Integer proc_count) {
		this.proc_count = proc_count;
	}
	public Date getProc_time() {
		return proc_time;
	}
	public void setProc_time(Date proc_time) {
		this.proc_time = proc_time;
	}
	
	@Override
	public String toString() {
		return "EpAtten [id=" + id + ", ep_uid=" + ep_uid + ", type=" + type + ", latitude=" + latitude + ", longtitude=" + longtitude + ", gps_distance=" + gps_distance
				+ ", gps_addr=" + gps_addr + ", wifi_mac=" + wifi_mac + ", wifi_name=" + wifi_name + ", is_valid=" + is_valid + ", time=" + time + ", count=" + count
				+ ", platform=" + platform + ", mach_sn=" + mach_sn + ", fetch_time=" + fetch_time + ", proc_result=" + proc_result + ", proc_count=" + proc_count + ", proc_time="
				+ proc_time + "]";
	}
	
}
