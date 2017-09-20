package elead.service.client;

import java.util.ArrayList;
import java.util.List;

import com.cn.eplat.model.PushToHw;
import com.cn.eplat.utils.DateUtil;

import elead.service.ts.ServiceTimesheet;
import elead.service.ts.ServiceTimesheetService;
import elead.service.ts.Timesheet;
public class ServiceClient {

	public static void invokeElead(List<PushToHw> allNeedsDatas) {
		
		List<Timesheet> listTS = new ArrayList<Timesheet>();
		String password = "EleadTimesheet20170905";
		ServiceTimesheet sts = new ServiceTimesheetService().getServiceTimesheetPort();
		for (PushToHw pushToHw : allNeedsDatas) {
			if (pushToHw.getOn_duty_time() != null) {
				Timesheet ts = new Timesheet();
				ts.setStaffIdNo(pushToHw.getId_no());	//身份证号
				ts.setStaffName(pushToHw.getName());	//姓名
				ts.setSwipeTime(DateUtil.formatDate(2, pushToHw.getOn_duty_time()));	//打卡时间
				ts.setSource(pushToHw.getOn_duty_source());	//打卡来源
				listTS.add(ts);
			}
			if (pushToHw.getOff_duty_time() != null) {
				Timesheet ts = new Timesheet();
				ts.setStaffIdNo(pushToHw.getId_no());
				ts.setStaffName(pushToHw.getName());
				ts.setSwipeTime(DateUtil.formatDate(2, pushToHw.getOff_duty_time()));
				ts.setSource(pushToHw.getOff_duty_source());
				listTS.add(ts);
			}
		}
		sts.promoteTS(listTS, password);
		
	}
	
}