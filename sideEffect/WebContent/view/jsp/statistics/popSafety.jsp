<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.ArrayList"%>

<%@ include file="/view/config/config.jsp" %>
<% String popName = request.getAttribute("popName").toString(); %>
<% String type = request.getAttribute("type").toString(); %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<SCRIPT LANGUAGE="javascript">

	function fnLoad() 
	{		
		//경로 수정하세요.
		//XPlatformAXCtrl.xadl = "D:\\WEB\\Xplatfrom\\sideeffect\\sideeffect.xadl";
		XPlatformAXCtrl.xadl = "/sideEffect/xPlatForm/sideeffect.xadl"; 
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();		
	}
	
</SCRIPT>	


<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  

	XPlatformAXCtrl.CallScript("gfn_statistics_callscript('statistics::statistics_safety.xfdl','<%=popName%>')");      // 마이플랫폼의 InitUrl설정방식이 바뀜

</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">

	var url ="safetyReport.do?action=poplist";
	var properties ="width=1150,height=700,scrollbars=0";
	
	if (nNotifyID == 1) {
		document.from1.popName.value = strMsg;
	} else if (nNotifyID == 2) {
		document.from1.fmYY.value = strMsg;
	} else if (nNotifyID == 3) {
		document.from1.fmMM.value = strMsg;
	} else if (nNotifyID == 4) {
		document.from1.toYY.value = strMsg;
	} else if (nNotifyID == 5) {
		document.from1.toMM.value = strMsg;
	} else if (nNotifyID == 6) {
		document.from1.reportType.value = strMsg;
	} else if (nNotifyID == 6) {
		document.from1.reportType.value = strMsg;
	} else if (nNotifyID == 7) {
		document.from1.codeArr.value = strMsg
	} else if (nNotifyID == 8) {
		document.from1.codeName.value = strMsg;
		
		window.open("","paraPop",properties);
		document.from1.action = url;
		document.from1.target="paraPop";
		document.from1.method="post";
		document.from1.submit();
		
	} // 차트
	else if (nNotifyID == 9) {
		
		var filePath = strMsg;
		filePath = filePath.replace(/\\/gi,"\/");
		var charUrl="http://192.168.0.21:8080/RexServer30/statisticsChart.jsp";
		//var charUrl="statistics.do?action=chart";
		var chartProperties = "width=1100,height=300,scrollbars=0";
		document.from1.filePath.value = filePath;

		window.open("","charPop",properties);
		document.from1.action = charUrl;
		document.from1.target="charPop";
		document.from1.method="post";
		document.from1.submit();

	}

</SCRIPT>
<SCRIPT LANGUAGE="JavaScript" FOR="XPlatformAXCtrl" EVENT="communication(bStart)"> 

</SCRIPT>



</head>

<BODY onload="fnLoad()">
<form name="from1" method="post" action="">
	<input type="hidden" name="popName" value="">
	<input type="hidden" name="fmYY" value="">
	<input type="hidden" name="fmMM" value="">
	<input type="hidden" name="toYY" value="">
	<input type="hidden" name="toMM" value="">
	<input type="hidden" name="reportType" value="">
	<input type="hidden" name="codeArr" value="">
	<input type="hidden" name="codeName" value="">
	<input type="hidden" name="filePath" value="">
</form>

<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width="1000" height="700"></OBJECT>


</BODY>
</html>


