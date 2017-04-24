<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>重新筛选考勤数据</title>
</head>
<body>
	<h3>一键重新筛选最近一周的考勤数据（不包括今天，即今天之前7天内的所有考勤数据）</h3>
	<form name="form_okrf" action="epDataController.do?reFilterPush2HwAttens" method="post" target=_blank >
		<%-- 暂时屏蔽根据用户自定义日期范围重新筛选的功能
			<h3>一键重新筛选最近一周的考勤数据（不包括今天，即今天之前7天内的所有考勤数据；若指定了开始日期和结束日期，则按指定日期范围重新筛选）</h3>
			<label>开始日期：</label> <input type="text" id="start_btn" name="start" > <label>（格式：yyyy-MM-dd）</label>
			<p/>
			<label>结束日期：</label> <input type="text" id="end_btn" name="end" > <label>（格式：yyyy-MM-dd）</label>
			<p/>
		 --%>
		<label>点击此按钮一键重新筛选：</label>
		<input type="submit" value="重新筛选" onclick="javascript:{this.disabled=true; this.value='请稍等片刻...'; document.getElementById('label_okrf').style='display:block'; document.form_okrf.submit();}" />
		<p/>
		<label id="label_okrf" style="display:none" > <font color="red">请稍等片刻，待筛选操作完成后，再刷新本页面~~~</font> </label>
		<p/>
	</form>
	<hr>

</body>
</html>
