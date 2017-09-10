<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>重新筛选考勤数据</title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	function start_refilter(btn){
		btn.disabled=true;
		btn.value='请稍等片刻...';
		if($("#stop_filter_btn").prop("disabled")==true){
			$("#stop_filter_btn").attr("disabled",false);
			$("#stop_filter_btn").attr("value","停止筛选");
		}
		document.getElementById('label_okrf').style='display:block';
		document.form_okrf.submit();
	}
	function stop_filter() {
		$.ajax({
			type : "GET",
			url : "epDataController.do?stopFilter",
			data : {
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				$("#stop_filter_btn").attr("disabled",true);
				$("#stop_filter_btn").attr("value","已停止筛选！");
				alert(data.ret_message);
			}
		});
	}
</script>
</head>
<body>
	<%-- 
		<h3>一键重新筛选并重新推送最近一周的考勤数据（不包括今天，即今天之前7天内的所有考勤数据）</h3>
	--%>
	<h3>一键重新筛选并重新推送最近一周的考勤数据（不包括今天，即今天之前7天内的所有考勤数据；若指定了开始日期和结束日期，则按指定日期范围重新筛选）</h3>
	<form name="form_okrf" action="epDataController.do?reFilterPush2HwAttens" method="post" target=_blank >
		<%-- 暂时屏蔽根据用户自定义日期范围重新筛选的功能 
			<h3>一键重新筛选最近一周的考勤数据（不包括今天，即今天之前7天内的所有考勤数据；若指定了开始日期和结束日期，则按指定日期范围重新筛选）</h3>
		--%>
			<label>开始日期：</label> <input type="text" id="start_btn" name="start" > <label>（格式：yyyy-MM-dd）</label>
			<p/>
			<label>结束日期：</label> <input type="text" id="end_btn" name="end" > <label>（格式：yyyy-MM-dd）</label>
			<p/>
		
		<label>点击此按钮一键重新筛选：</label>
		<input type="submit" value="重新筛选" onclick="start_refilter(this);" />
		<p/>
		<label id="label_okrf" style="display:none" > <font color="red">请稍等片刻，待筛选操作完成后，再刷新本页面~~~</font> </label>
		<p/>
		<input id="stop_filter_btn" type="button" value="停止筛选" onclick="stop_filter();" />
	</form>
	<hr>

</body>
</html>
