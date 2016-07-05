<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.List"%>
<%@ page import="properties.ComponentCode" %>
<%@ page import="common.Function"%>


<%@ include file="/view/config/config.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<SCRIPT LANGUAGE="javascript">

	function fnLoad() 
	{		
			//경로 수정하세요.
			XPlatformAXCtrl.xadl =  "D:\\WEB\\Xplatfrom\\sideeffect\\sideeffect.xadl";//"C:\\xPlatform\\sideeffect_부작용통계완료\\sideeffect\\sideeffect.xadl"; //D:\\WEB\\Xplatfrom\\sideeffect
			XPlatformAXCtrl.autosize = false;
			XPlatformAXCtrl.key = "sideeffect";
			XPlatformAXCtrl.run();		
	}
				
	
</SCRIPT>	

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  
	XPlatformAXCtrl.CallScript("gfn_callscript('sideEffectPop::popComC.xfdl')");      // 마이플랫폼의 InitUrl설정방식이 바뀜
</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	alert("nNotifyID:"+nNotifyID+"\nstrMsg:"+strMsg);
	
	opener.document.safetyCreateForm1.patient_code_test.value = strMsg;
	
	//window.close();
	
</SCRIPT>



</head>

<BODY onload="fnLoad()">

<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width="600" height="400"></OBJECT>


</BODY>
</html>