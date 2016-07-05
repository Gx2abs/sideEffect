<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/view/config/config.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
<title>Insert title here</title>

<SCRIPT LANGUAGE="javascript">

// 월별 보기
function popM(popName){
	var url ="statistics.do?action=popSideeffectM&popName="+popName;
	var properties ="width=1020,height=720";
	
	window.open(url, '', properties, 'scrollbars=yes');
}

// 연도별 보기
function popY(popName){
	var url ="statistics.do?action=popSideeffectY&popName="+popName;
	var properties ="width=1020,height=720";
	
	window.open(url, '', properties, 'scrollbars=yes');
}
	
</SCRIPT>	

<SCRIPT LANGUAGE="javascript">
function fnLoad() 
{	
	// Chrome
	if(navigator.userAgent.indexOf("Chrome") != -1)
	{	
 		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="1000"  load="npXPlatformPlugin_load">';
       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "<%=url %>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();	
	}
	else if(navigator.userAgent.indexOf("Safari") != -1){
		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="1000"  load="npXPlatformPlugin_load">';
       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "<%=url %>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();	
	}
	// IE
	else{
		var ResultItem = '<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width=100% height="1000" style="border-style: hidden; "></OBJECT>';
   		document.getElementById("BrowserCHK").innerHTML = ResultItem;
		//경로 수정하세요.
		//XPlatformAXCtrl.xadl = "D:\\WEB\\Xplatfrom\\sideeffect\\sideeffect.xadl";
		XPlatformAXCtrl.xadl = "/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();
	}
}
	
	
		

 	function npXPlatformPlugin_addlog(strMsg)
 	{
 	}

 	function npXPlatformPlugin_beforeexit()
 	{
 	}

 	function npXPlatformPlugin_communication(bStart)
 	{
 	}

 	function npXPlatformPlugin_error(nError, strErrMsg)
 	{
 	}

 	function npXPlatformPlugin_exit()
 	{
 	}

 	function npXPlatformPlugin_load(strURL)
 	{
 	//	XPlatformAXCtrl.callscript("af_setEmbededBorder()");
 		XPlatformAXCtrl.callscript("gfn_statistics_callscript('statistics::statistics_sideEffect.xfdl','')");  
 	}

 	function npXPlatformPlugin_loadingglobalvariables(strURL)
 	{
 	}

 	function npXPlatformPlugin_loadtypedefinition(strURL)
 	{
 	}

 	function npXPlatformPlugin_usernotify(nNotifyID, strMsg)
 	{
 	}
</SCRIPT>	

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">
	XPlatformAXCtrl.callscript("gfn_statistics_callscript('statistics::statistics_sideEffect.xfdl','')");      // 마이플랫폼의 InitUrl설정방식이 바뀜	
</SCRIPT>





<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">


	var url ="sideEffectReport.do?action=poplist";
	var properties ="width=1150,height=700,scrollbars=0";

	if (nNotifyID == 1) {
		document.from1.popName.value = strMsg;
	} else if (nNotifyID == 2) {
		document.from1.fmDate.value = strMsg;
	} else if (nNotifyID == 3) {
		document.from1.toDate.value = strMsg;
	} else if (nNotifyID == 4) {
		document.from1.curStep.value = strMsg;
	} else if (nNotifyID == 5) {
		document.from1.reportType.value = strMsg;
	} else if (nNotifyID == 6) {
		document.from1.codeArr.value = strMsg
	} else if (nNotifyID == 7) {
		document.from1.codeName.value = strMsg;
	} else if(nNotifyID == 8) {
		document.from1.reporterTypeArr.value = strMsg;
		
		window.open("","paraPop",properties);
		document.from1.action = url;
		document.from1.target="paraPop";
		document.from1.method="post";
		document.from1.submit(); 

		
	} // 차트
	else if (nNotifyID == 10) {
		
		var filePath = strMsg;
		filePath = filePath.replace(/\\/gi,"\/");
		
		// 차트 종류 구분
		var chartgubun = filePath.substring(0,2);
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

<body onload="fnLoad()"> 
<div style="width: 600px;">
	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %><br>

</div>
<form name="from1" method="post" action="">
<input type="hidden" name="popName" value="">
<input type="hidden" name="fmDate" value="">
<input type="hidden" name="toDate" value="">
<input type="hidden" name="curStep" value="">
<input type="hidden" name="reportType" value="">
<input type="hidden" name="codeArr" value="">
<input type="hidden" name="codeName" value="">
<input type="hidden" name="reporterTypeArr" value="">
<input type="hidden" name="filePath" value="">
</form>
<!-- <embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="700"  load="npXPlatformPlugin_load"> -->
<!-- <OBJECT ID="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="1188" height="617" load="npXPlatformPlugin_load"></OBJECT> -->
<!-- <OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width=100% height="700" style="border-style: hidden; "></OBJECT> -->
<div id="BrowserCHK"></div>

	
</body>
</html>