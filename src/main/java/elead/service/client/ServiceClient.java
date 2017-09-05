package elead.service.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				ts.setStaffCardNo(pushToHw.getId_no());
				ts.setStaffName(pushToHw.getName());
				ts.setSwipeTime(DateUtil.formatDate(2, pushToHw.getOn_duty_time()));
				listTS.add(ts);
			}
			if (pushToHw.getOff_duty_time() != null) {
				Timesheet ts = new Timesheet();
				ts.setStaffCardNo(pushToHw.getId_no());
				ts.setStaffName(pushToHw.getName());
				ts.setSwipeTime(DateUtil.formatDate(2, pushToHw.getOff_duty_time()));
				listTS.add(ts);
			}
		}
		sts.promoteTS(listTS, password);
		
	}
	
}