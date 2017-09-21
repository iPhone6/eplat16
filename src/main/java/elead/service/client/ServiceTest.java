package elead.service.client;

import java.util.ArrayList;
import java.util.List;

import elead.service.ts.ServiceTimesheet;
import elead.service.ts.ServiceTimesheetService;
import elead.service.ts.Timesheet;
class ServiceTest {
	public static void main(String[] args) {
		String password = "EleadTimesheet20170905";
		ServiceTimesheet sts = new ServiceTimesheetService().getServiceTimesheetPort();
		Timesheet ts = new Timesheet();
		ts.setStaffIdNo("123456");//身份证号
		ts.setStaffName("zhangsan8888");//姓名
		ts.setSource("OA 移动");//打卡来源
		List<Timesheet> listTS = new ArrayList<Timesheet>();
		listTS.add(ts);
 		System.out.println(sts.promoteTS(listTS, password));
	}

}