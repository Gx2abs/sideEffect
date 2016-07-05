<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<%@ page import="com.tobesoft.xplatform.data.*" %>
<%@ page import="com.tobesoft.xplatform.tx.*" %>
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
	String reporterTypeArr = "N";
	String itemSeq = "0";
	
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
		
		System.out.println("popName===" + popName);
	}
	
	String sc = Function.nullChk(request.getParameter("sc"), "");
	String date1 = Function.nullChk(request.getParameter("date1"), "");
	String date2 = Function.nullChk(request.getParameter("date2"), "");
	//out.print("sc  " + sc + "  date1  " + date1 + "  date2  " + date2);
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
	XPlatformAXCtrl.callscript("gfn_list_callscript('safety::safetyList.xfdl','<%=popYN%>')");  
	}
		
				
	
</SCRIPT>	


<SCRIPT LANGUAGE="JavaScript" FOR="XPlatformAXCtrl" EVENT="load(strURL)">
	XPlatformAXCtrl.callscript("gfn_list_callscript('safety::safetyList.xfdl','<%=popYN%>')"); 
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

	XPlatformAXCtrl.setvariablevalue("gvPrivilegeId", <%=privilegeId%>);
</SCRIPT>

<SCRIPT LANGUAGE="javascript" FOR="XPlatformAXCtrl" EVENT="usernotify(nNotifyID,strMsg)">
	//alert("nNotifyID:"+nNotifyID+"\nstrMsg:"+strMsg);
	if(nNotifyID == 1){
	
		var url =strMsg;
 		
 		window.open(url, "", "width=1030, height=700, scrollbars=yes", "resizable=yes");
	}else{
		location.href=strMsg;
	}
</SCRIPT>    


<SCRIPT LANGUAGE="javascript">

function search(){
	document.xpSafetyListForm1.submit();
}

</SCRIPT>

</head>

<BODY onload="fnLoad()">
<!-- 
<form name="xpSafetyListForm1" method="post" action="safetyReport.do" >
	<input type="hidden" name="action" value="list" />
	<div>
		<select name="sc">
			<option value="report_date">보고일자</option>
			<option value="kfda_report_date">식약처보고일자</option>
		</select>
		
		<input type="text" name="date1" /> 년 ~ <input type="text" name="date2" /> 년	
		<input type="button" value="조회" onclick="search();" />	(1년 기간 이내로만 조회가 가능합니다. ex) 2012~2012, 2011~2011
	</div>
</form>
 -->
 <img alt="" src="view/style/images/title/sub01_02.jpg">
<!-- <OBJECT ID="XPlatformAXCtrl" CLASSID="CLSID:43C5FE00-DD32-4792-83DB-19AE4F88F2A6" width="100%" height="700" style="border-style: hidden; " ></OBJECT> -->
<div id="BrowserCHK"></div>

</BODY>
</html>


