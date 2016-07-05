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
			XPlatformAXCtrl.xadl =  "/sideEffect/xPlatForm/sideeffect.xadl";
			//XPlatformAXCtrl.xadl = "C:\\xPlatform\\sideeffect_팝업값전송\\sideeffect\\sideeffect.xadl";
			XPlatformAXCtrl.autosize = true;
			XPlatformAXCtrl.key = "sideeffect";
			XPlatformAXCtrl.run();		
	}
				
	
</SCRIPT>	

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  
	XPlatformAXCtrl.CallScript("gfn_callscript('sideEffectPop::popMedDM.xfdl')");      // 마이플랫폼의 InitUrl설정방식이 바뀜
</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	//alert("nNotifyID:"+nNotifyID+"\nstrMsg:"+strMsg);
	
	//opener.document.safetyCreateForm1.patient_code_test.value = strMsg;
	
	if(nNotifyID == 1){
		var idArr = strMsg.split(",");
		
	//	for(var i=0; i<idArr.length; i++){
			//alert("idArr[]  " + idArr[i]);
		//	opener.document.safetyCreateForm1.patient_code_id.value = idArr[i];	
			opener.document.safetyCreateForm1.medical_code_id.value = strMsg;
	//	}
		
	}
	
	if(nNotifyID == 4){
		var indexNum = strMsg.lastIndexOf(",");
		var view = strMsg.substring(0, indexNum);
		
		opener.document.safetyCreateForm1.medical_code_user_view.value = view;		
	}
	
	window.close();
	
</SCRIPT>


</head>

<BODY onload="fnLoad()">

<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width="800" height="700"></OBJECT>


</BODY>
</html>


