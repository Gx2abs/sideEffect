<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="/view/config/config.jsp" %>
<% String statisticsGB = request.getAttribute("statisticsGB").toString(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<SCRIPT LANGUAGE="javascript">
function fnLoad() 
{	
	// Chrome
	if(navigator.userAgent.indexOf("Chrome") != -1)
	{	
 		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="1500"  load="npXPlatformPlugin_load">';
       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "<%=url%>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();	
	}
	else if(navigator.userAgent.indexOf("Safari") != -1){
		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="1500"  load="npXPlatformPlugin_load">';
       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "<%=url %>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();	
	}
	// IE
	else{
		var ResultItem = '<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width=100% height="1500" style="border-style: hidden; "></OBJECT>';
   		document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();
	}
}
	
function npXPlatformPlugin_load(strURL)
	{
	XPlatformAXCtrl.callscript("gfn_statistics_callscript('statisticsItem::statisticsItem.xfdl','<%=statisticsGB%>')");  
	}
	
</SCRIPT>	


<SCRIPT LANGUAGE="JavaScript" FOR="XPlatformAXCtrl" EVENT="load(strURL)">
	XPlatformAXCtrl.callscript("gfn_statistics_callscript('statisticsItem::statisticsItem.xfdl','<%=statisticsGB%>')"); 
</SCRIPT>

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  

</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	
	var properties ="width=1150,height=700,scrollbars=0";
	
	if (nNotifyID == 1) {
		document.from1.popName.value = strMsg;
	} else if (nNotifyID == 2) {
		document.from1.fmDate.value = strMsg;
	} else if (nNotifyID == 3) {
		document.from1.toDate.value = strMsg;
	} else if (nNotifyID == 4) {
		document.from1.reportType.value = strMsg;
	} else if (nNotifyID == 5) {
		document.from1.codeArr.value = strMsg
	} else if (nNotifyID == 6) {
		document.from1.codeName.value = strMsg;
	} else if (nNotifyID == 7) {
		document.from1.itemSeq.value = strMsg;
	} else {
		var url = strMsg;
		window.open("","paraPop",properties);
		document.from1.action = url;
		document.from1.target="paraPop";
		document.from1.method="post";
		document.from1.submit();

	} 
</SCRIPT>


</head>

<BODY onload="fnLoad()">  
<form name="from1" method="post" action="">
	<input type="hidden" name="popName" value="">
	<input type="hidden" name="fmDate" value="">
	<input type="hidden" name="toDate" value="">
	<input type="hidden" name="reportType" value="">
	<input type="hidden" name="codeArr" value="">
	<input type="hidden" name="codeName" value="">
	<input type="hidden" name="itemSeq" value="">
	<input type="hidden" name="filePath" value="">
	<input type="hidden" name="reporterTypeArr" value="">
</form>

<div style="width: 600px;">
	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %><br>
</div>
<div id="BrowserCHK"></div>

 	
 	
</BODY>
</html>


