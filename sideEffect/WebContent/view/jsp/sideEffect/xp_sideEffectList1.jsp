<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.ArrayList"%>

<%@ include file="/view/config/config.jsp" %>

<%

	member.Member objMember ;
	long privilegeId = -1;
	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	}
	 
	String popYN = Function.nullChk(request.getAttribute("popYN").toString(),"N");
	String popName = "N";
	String fmDate = "N";
	String toDate = "N";
	String reportType = "N";
	String codeArr = "N";
	String codeName = "N";
	String itemSeq = "0";
	String reporterTypeArr = "N";
	
	// 통계에서 넘겨주는 parameter값
	if(popYN.equals("Y")) {
		
		popName = Function.nullChk(request.getAttribute("popName").toString(),"");
		fmDate = Function.nullChk(request.getAttribute("fmDate").toString(),"");
		toDate = Function.nullChk(request.getAttribute("toDate").toString(),"");
		reportType = Function.nullChk(request.getAttribute("reportType").toString(),"");
		codeArr = Function.nullChk(request.getAttribute("codeArr").toString(),"");
		codeName = Function.nullChk(request.getAttribute("codeName").toString(),"");
		reporterTypeArr = Function.nullChk(request.getAttribute("reporterTypeArr").toString(),"");
		// 0보다 크면 홍목 조합통계에서 조회(품목ID)
		itemSeq = Function.nullChk(request.getAttribute("itemSeq").toString(),"0");

	}
	
	//select box에서 선택된값
	String sc_0 = Function.nullChk( request.getParameter("sc_0"), "0");
	//검색어
	String sv_0 = Function.nullChk( request.getParameter("sv_0"), "0");
	//and 인지 or 검색인지 구분
	String logicalOperator_0 = Function.nullChk( request.getParameter("logicalOperator_0"), "0");
	
%>


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
	 		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="700"  load="npXPlatformPlugin_load">';
	       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
			//경로 수정하세요.
			//XPlatformAXCtrl.xadl = "D:\\WEB\\Xplatfrom\\sideeffect\\sideeffect.xadl";
			XPlatformAXCtrl.xadl = "<%=url %>/sideEffect/xPlatForm/sideeffect.xadl";
			XPlatformAXCtrl.autosize = false;
			XPlatformAXCtrl.key = "sideeffect";
			XPlatformAXCtrl.run();	
	}
	else if(navigator.userAgent.indexOf("Safari") != -1){
		var ResultItem = '<embed id="XPlatformAXCtrl" type="application/xplatform9.2-plugin" width="100%" height="700"  load="npXPlatformPlugin_load">';
       	document.getElementById("BrowserCHK").innerHTML = ResultItem;
		//경로 수정하세요.
		//XPlatformAXCtrl.xadl = "D:\\WEB\\Xplatfrom\\sideeffect\\sideeffect.xadl";
		XPlatformAXCtrl.xadl = "<%=url %>/sideEffect/xPlatForm/sideeffect.xadl";
		XPlatformAXCtrl.autosize = false;
		XPlatformAXCtrl.key = "sideeffect";
		XPlatformAXCtrl.run();	
	}
	// IE
	//else if(navigator.appVersion.indexOf('MSIE')>=0){
	else {
			var ResultItem = '<OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width=100% height="700" style="border-style: hidden; "></OBJECT>';
	   		document.getElementById("BrowserCHK").innerHTML = ResultItem;
			//경로 수정하세요.
			//XPlatformAXCtrl.xadl = "D:\\WEB\\Xplatfrom\\sideeffect\\sideeffect.xadl";
			XPlatformAXCtrl.xadl = "/sideEffect/xPlatForm/sideeffect.xadl";
			XPlatformAXCtrl.autosize = false;
			XPlatformAXCtrl.key = "sideeffect";
			XPlatformAXCtrl.run();
	}
		
}

function npXPlatformPlugin_load(strURL)
	{
		XPlatformAXCtrl.callscript("gfn_list_callscript('sideEffectList::sideEffectList.xfdl','<%=popYN%>')");    
	}
		
		
	function fn_details_search(){
	var frm1 = document.xp_details_search_form1;
	
	frm1.submit();
	
	}	
	
</SCRIPT>	


<SCRIPT LANGUAGE="JavaScript" FOR="XPlatformAXCtrl" EVENT="load(strURL)">
	XPlatformAXCtrl.callscript("gfn_list_callscript('sideEffectList::sideEffectList.xfdl','<%=popYN%>')"); 
</SCRIPT>

<SCRIPT LANGUAGE=javascript FOR=XPlatformAXCtrl EVENT="loadingglobalvariables(strurl)">  
	XPlatformAXCtrl.setvariablevalue("gVpopName", '<%=popName%>');
	XPlatformAXCtrl.setvariablevalue("gVfmDate", '<%=fmDate%>');
	XPlatformAXCtrl.setvariablevalue("gVtoDate", '<%=toDate%>');
	XPlatformAXCtrl.setvariablevalue("gVReportType", '<%=reportType%>');
	XPlatformAXCtrl.setvariablevalue("gVcodeArr", '<%=codeArr%>');
	XPlatformAXCtrl.setvariablevalue("gVcodeName", '<%=codeName%>');
	XPlatformAXCtrl.setvariablevalue("gVReporterTypes", '<%=reporterTypeArr%>');
	XPlatformAXCtrl.setvariablevalue("gVitemSeq", '<%=itemSeq%>');
	
<%-- 	XPlatformAXCtrl.setvariablevalue("gvSc_0", '<%=sc_0%>');
	XPlatformAXCtrl.setvariablevalue("gvSv_0", '<%=sv_0%>');
	XPlatformAXCtrl.setvariablevalue("gvLogicalOperator_0", '<%=logicalOperator_0%>'); --%>
	XPlatformAXCtrl.setvariablevalue("gvPrivilegeId", <%=privilegeId%>);
</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	//alert("nNotifyID:"+nNotifyID+"\nstrMsg:"+strMsg);
	if(nNotifyID == 1){
		//location.href=strMsg;
		
		var url =strMsg;
 		
 		window.open(url, "", "width=1030, height=700, scrollbars=yes, resizable=yes");
	}else{
		//alert(strMsg);
		location.href=strMsg;
	}
	
</SCRIPT>


</head>

<BODY onload="fnLoad()">  

<img alt="" src="view/style/images/title/sub01_01.jpg">
 	<div id="BrowserCHK"></div>
 	<!-- <OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width=100% height="700" style="border-style: hidden; "></OBJECT> -->
 	
<form name="xp_details_search_form1" method="post" action="sideEffectReport.do">
	<input type="hidden" name="action" value="list" />
	<input type="hidden" name="sc_0" value="0" />
	<input type="hidden" name="sv_0" value="0" />
	<input type="hidden" name="logicalOperator_0" value="0" />
</form>	
 	
 	
</BODY>
</html>


