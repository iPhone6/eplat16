package com.cn.eplat.dao;

import java.util.List;

import com.cn.eplat.model.EpAxis;
import com.cn.eplat.model.EpCenterAddr;

public interface IEpCenterAxisDao {
	
	List<EpAxis> getAllEpAxises();
	
	List<EpCenterAddr> getCenterAxisAddrsByCenterId(int centerId);
	
}
