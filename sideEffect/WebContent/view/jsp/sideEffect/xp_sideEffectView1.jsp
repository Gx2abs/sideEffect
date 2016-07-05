<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.ArrayList"%>

<%@ include file="/view/config/config.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<SCRIPT LANGUAGE="javascript">

	function fnLoad() 
	{		
		
			//경로 수정하세요.
			XPlatformAXCtrl.xadl =  "D:\\WEB\\Xplatfrom\\sideeffect\\sideeffect.xadl";
			XPlatformAXCtrl.autosize = true;
			XPlatformAXCtrl.key = "sideeffect";
			//XPlatformAXCtrl.run();		
	}
				
	
</SCRIPT>	

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  
	XPlatformAXCtrl.CallScript("gfn_callscript('sideEffectList::sideEffectList.xfdl')");      // 마이플랫폼의 InitUrl설정방식이 바뀜
</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	alert("nNotifyID:"+nNotifyID+"\nstrMsg:"+strMsg);
	
</SCRIPT>


</head>

<BODY onload="fnLoad()">




</BODY>
</html>


