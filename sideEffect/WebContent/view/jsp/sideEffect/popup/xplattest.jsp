<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<SCRIPT LANGUAGE="javascript">

	function fnLoad() 
	{		
			//경로 수정하세요.
			XPlatformAXCtrl.xadl = "C:\\Documents and Settings\\hyh\\바탕 화면\\xplatform_IE_test\\test_20100623.xadl";
			XPlatformAXCtrl.autosize = false;
			XPlatformAXCtrl.key = "TEST";
			XPlatformAXCtrl.run();		
	}
				
	function page_call(arg)
	{
			if(arg==1){
				XPlatformAXCtrl.CallScript("gfn_callscript('Base::Test.xfdl')");	
	  	} else if(arg==2) {
				XPlatformAXCtrl.CallScript("gfn_callscript('Base::Test1.xfdl')");	
			} else if(arg==3) {				
				XPlatformAXCtrl.CallScript("gfn_callscript('Base::Test2.xfdl')");	
			}	
				
	}		
</SCRIPT>	

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  
alert("strurl  " + strurl);
	XPlatformAXCtrl.CallScript("gfn_callscript('Base::Test.xfdl')");      // 마이플랫폼의 InitUrl설정방식이 바뀜
</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	alert("TEST  nNotifyID:"+nNotifyID+"\nstrMsg:"+strMsg);
	
	opener.document.safetyCreateForm1.patient_code_test.value = strMsg;
	
</SCRIPT>



</head>

<BODY onload="fnLoad()">

<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width="600" height="400"></OBJECT>
<input type="button" name="btnTest1" value="화면1" onclick="javascript:page_call(1)">
<input type="button" name="btnTest2" value="화면2" onclick="javascript:page_call(2)">
<input type="button" name="btnTest3" value="화면3" onclick="javascript:page_call(3)">

</BODY>
</html>