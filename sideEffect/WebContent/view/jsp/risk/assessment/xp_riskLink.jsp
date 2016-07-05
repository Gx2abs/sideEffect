<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="/view/config/config.jsp" %>
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
 		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="800"  load="npXPlatformPlugin_load">';
       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "<%=url%>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();	
	}
	else if(navigator.userAgent.indexOf("Safari") != -1){
		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="800"  load="npXPlatformPlugin_load">';
       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "<%=url %>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();	
	}
	// IE
	else{
		var ResultItem = '<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width=100% height="800" style="border-style: hidden; "></OBJECT>';
   		document.getElementById("BrowserCHK").innerHTML = ResultItem;
		XPlatformAXCtrl.xadl = "<%=url %>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();
	}
}
	
function npXPlatformPlugin_load(strURL)
{
	XPlatformAXCtrl.callscript("gfn_statistics_callscript('risk::riskAssessment.xfdl','sideEffect')");  
}
	
</SCRIPT>	


<SCRIPT LANGUAGE="JavaScript" FOR="XPlatformAXCtrl" EVENT="load(strURL)">
	XPlatformAXCtrl.callscript("gfn_statistics_callscript('risk::riskAssessment.xfdl','sideEffect')"); 
</SCRIPT>

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  

</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	

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


