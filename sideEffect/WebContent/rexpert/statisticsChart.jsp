<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<% String filePath = request.getParameter("filePath").toString();
	String chartgubun = filePath.substring(0,2);
	filePath = filePath.substring(2);
	filePath = filePath + "chart_temp.csv";
	System.out.print(filePath+"   filePath");
	
	%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chart Page</title>
<script type="text/javascript" src="./rexscript/getscript.jsp?f=rexpert.min"></script>
<script type="text/javascript" src="./rexscript/getscript.jsp?f=rexpert.properties"></script>
<script type="text/javascript">

window.onload = function fnOpen(){
	
	// 필수 - 레포트 생성 객체
	var oReport = GetfnParamSet();
	
	//필수 - 레포트 파일명
	oReport.rptname = "Chart_<%=chartgubun%>";
	
	//옵션 - 레포트 파라메터
	oReport.param("param1").value = "param1";
	
	//-------------------------------------
	// 2. csv file을 통해 데이터를 가져오는 방법
	//-------------------------------------
	
	oReport.type = "file";
	oReport.datatype = "csv";
	//oReport.path = "http://" + location.host + "/RexServer30/rebfiles/samples/oracle_emp.csv";
	//oReport.path = "http://" + location.host + "/RexServer30/rebfiles/samples/oracle_emp.jsp?datatype=csv";
	//oReport.path = "D:\\rexpert30\\RexServer30\\rebfiles\\samples\\oracle_emp.csv";
	oReport.path = "<%=filePath%>";
	
	//-----------------------------------------------------------------------------------------------
	// event handler 정의(인자는 3개)
	//        oRexCtl : 렉스퍼트 객체
	//        sEvent : 이벤트명 
	//                     "init" - 초기화, "finishdocument" - 레포트팅 완료, "finishprint" - 인쇄 완료, "finishexport" - 저장 완료,
	//			"hyperlinkclicked" - 하이퍼링크클릭시
	//        oArgs : 이벤트 파라메터(finishexport - filename, hyperlinkclicked - Path, finishprintresult - resultcode)
	//-----------------------------------------------------------------------------------------------
	/*
	oReport.event.init = fnReportEvent;
	
	oReport.event.buttonprintclickbefore = fnReportEvent;
	oReport.event.buttonprintclickafter = fnReportEvent;
	
	oReport.event.buttonexportclickbefore = fnReportEvent;
	oReport.event.buttonexportclickafter = fnReportEvent;
	
	oReport.event.buttonrefreshclickbefore = fnReportEvent;
	oReport.event.buttonrefreshclickafter = fnReportEvent;	
	
	oReport.event.buttonexportxlsclickbefore = fnReportEvent;
	oReport.event.buttonexportxlsclickafter = fnReportEvent;		
	
	oReport.event.buttonexportpdfclickbefore = fnReportEvent;
	oReport.event.buttonexportpdfclickafter = fnReportEvent;	
	
	oReport.event.buttonexporthwpclickbefore = fnReportEvent;
	oReport.event.buttonexporthwpclickafter = fnReportEvent;
	
	oReport.event.cancelprint = fnReportEvent;
	
	oReport.event.buttonclosewindowclickbefore = fnReportEvent;
	oReport.event.buttonclosewindowclickafter = fnReportEvent;
	
	oReport.event.printpage = fnReportEvent;
	oReport.event.cancelexport = fnReportEvent;	
	oReport.event.finishprintresult = fnReportEvent;
	
	oReport.event.errorevent = fnReportEvent;
	*/
	
	//필수 - 레포트 실행
	oReport.iframe(ifrmRexPreview1);
}	

//event handler 
function fnReportEvent(oRexCtl, sEvent, oArgs) {
	//alert(sEvent);

	if (sEvent == "init") {
		oRexCtl.SetCSS("appearance.canvas.offsetx=0");
		oRexCtl.SetCSS("appearance.canvas.offsety=0");
		oRexCtl.SetCSS("appearance.pagemargin.visible=0");

		oRexCtl.SetCSS("appearance.toolbar.button.open.visible=0");
		oRexCtl.SetCSS("appearance.toolbar.button.export.visible=0");
		oRexCtl.SetCSS("appearance.toolbar.button.refresh.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.movefirst.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.moveprev.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.pagenumber.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.pagecount.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.movenext.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.movelast.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.zoom.visible=1");
		oRexCtl.SetCSS("appearance.toolbar.button.exportxls.visible=0");
		oRexCtl.SetCSS("appearance.toolbar.button.exportpdf.visible=0");
		oRexCtl.SetCSS("appearance.toolbar.button.exporthwp.visible=0");
		oRexCtl.SetCSS("appearance.toolbar.button.about.visible=0");

		//oRexCtl.SetCSS("appearance.pane.toc.visible=0");

		//oRexCtl.SetCSS("event.printfinishresult.enable=1");
		//<- 이 속성을 1로 설정해야 PrintFinishResult 이벤트가 발생합니다

		oRexCtl.UpdateCSS();
	} else if (sEvent == "finishdocument") {
		oRexCtl.Zoom("wholepage");
		//oRexCtl.SetCSS("appearance.toolbar.button.exportxls.option.sheetoption=2");
		//oRexCtl.UpdateCSS();
	} else if (sEvent == "finishprint") {
		
	} else if (sEvent == "finishexport") {
		//alert(oArgs.filename);

	} else if (sEvent == "finishprintresult") {
		//alert(oArgs.resultcode);
		/*
			0      : 성공
			1002 : 인쇄 작업이 일지정지
			1003 : 인쇄 작업중 오류 발생
			1004 : 인쇄 작업이 삭제중
			1005 : 프린터 오프라인상태
			1006 : 프린터 용지 부족
			1007 : 인쇄 작업이 삭제됨
			9999 : 알수 없는 오류 발생
		*/
	} else if (sEvent == "hyperlinkclicked") {
		//alert(oArgs.Path);
	} else if (sEvent == "buttonprintclickbefore") { 
	} else if (sEvent == "buttonprintclickafter") {  
	} else if (sEvent == "buttonexportclickbefore") { 
	} else if (sEvent == "buttonexportclickafter") { 
	} else if (sEvent == "buttonrefreshclickbefore") {
	} else if (sEvent == "buttonrefreshclickafter") { 
	} else if (sEvent == "buttonexportxlsclickbefore") {  
	} else if (sEvent == "buttonexportxlsclickafter") { 
	} else if (sEvent == "buttonexportpdfclickbefore") {
	} else if (sEvent == "buttonexportpdfclickafter") { 
	} else if (sEvent == "buttonexporthwpclickbefore") {  
	} else if (sEvent == "buttonexporthwpclickafter") {  
	} else if (sEvent == "cancelprint") { 
	} else if (sEvent == "buttonclosewindowclickbefore") { 
	} else if (sEvent == "buttonclosewindowclickafter") { 
	} else if (sEvent == "printpage") { 
		//alert("call:"  + "oArgs.totalpage:" + oArgs.totalpage + "   oArgs.page:" + oArgs.page);
	} else if (sEvent == "cancelexport") {  
	} else if (sEvent == "finishprintresult") {  
		//alert("call:"  + "finishprintresult" + "oArgs.resultcode:" + oArgs.resultcode);
	} else if (sEvent == "errorevent") {  
		//alert("call:"  + "errorevent" + "oArgs.errorxml:" + oArgs.errorxml);
	}
	//window.close();
}

//----------------------------------------------
//커넥션별 데이터를 서로 다르게 각각 가져오는 예
//디자인시 만든 커넥션에 따라 설정
//----------------------------------------------
function fnOpenEach() {
	// 필수 - 레포트 생성 객체
	var oReport = GetfnParamSet();
	
	// 필수 - 레포트 파일명
	oReport.rptname = "rebfiles/Chart_<%=chartgubun%>";
	
	
	//---------------------------------------------------------
	// 2. 커넥션별 서로 다르게 데이터를 가져오는 방법(csv file, http xml)
	//---------------------------------------------------------
	
	oReport.con("ADO1").type = "file";
	oReport.con("ADO1").path = "http://" + location.host + "/RexServer30/rebfiles/samples/sqlserver_customers.csv";
	//oReport.con("ADO1").namespace = "ADO1";	// 생략하면, sub id를 사용

	//System.out.print("sss "+  location.host);
	
	oReport.con("ADO2").type = "http";
	oReport.con("ADO2").datatype = "xml";
	oReport.con("ADO2").connectname = "Sql1";
	//oReport.con("ADO2").namespace = "ADO2";	// 생략하면, sub id를 사용
	
	
	//필수 - 레포트 실행
	oReport.iframe(ifrmRexPreview1);
	
}



function fnGetPrinterInfo() {
	var printerCombo = document.all.printerCombo;
 	for(i = printerCombo.length-1 ; i >= 0 ; i--) {
 		printerCombo.options[i] = null;
 	}
 		
 	var sPrinterList;
	var listPrinter;	
	try {
		//******************************************************
		// 프린터 정보를 얻어 옵니다.
		//******************************************************
		sPrinterList = RexCtl.GetPrinterList("@");
		listPrinter = sPrinterList.split("@");
	}
	catch(e) {
		alert(e.message);
		return;
	}
	 		
 	for(i = 0; i < listPrinter.length; i++) {
 	    printerCombo.options[i] = new Option(listPrinter[i], listPrinter[i]);
 	}
}

function fnChangePrinter() {
	var paperbinCombo = document.all.paperbinCombo;
 	for(i = paperbinCombo.length-1 ; i >= 0 ; i--)
 		paperbinCombo.options[i] = null;
	
	var sPrinterName = printerCombo.value;
	var sPaperBin;
	var listPaperBin;

  	//alert(sPrinterName);
	try {
		//******************************************************
		// 프린터 트레이 정보를 얻어 옵니다.
		//******************************************************
		sPaperBin = RexCtl.GetPrinterBinInfoList(sPrinterName, "@");
		//alert(sPaperBin);
		listPaperBin = sPaperBin.split("@");
	}
	catch(e) {
		alert(e.message);
		return;
	}

	//******************************************************
	// 동일한 배열안에 트레이번호와 이름이 같이 포함됨
	// [번호1][이름1][번호2][이름2].....
	//******************************************************	
 	for(i = 0; i < listPaperBin.length; i=i+2) {
 		var sBinCode = listPaperBin[i];
 		var sBinName = listPaperBin[i+1];
 	    paperbinCombo.options[i/2] = new Option(sBinCode + " - " + sBinName ,sBinCode);	
 	}
}




</script>
</head>
<body>
	<br/>
	<br/>
	
	
	<!-- 
	&nbsp;<button onclick="fnOpen();">레포트 보기(Single)</button>
	&nbsp;<button onclick="fnOpenEach();">레포트 보기(Each)</button>
	&nbsp;<button onclick="fnOpenMulti();">레포트 보기(Multi)</button>
	&nbsp;<button onclick="fnOpenMulti_MainSub();">레포트 보기(Multi, main-sub)</button><br>
	
	<hr>
	<INPUT type="button" value="설치된 프린터목록" onClick="fnGetPrinterInfo()">
	<SELECT name="printerCombo" ID="printerCombo" OnChange="fnChangePrinter()">
  		<OPTION>========== 프린터 선택 ========== </OPTION>
  	</SELECT>
	<SELECT name="paperbinCombo" ID="paperbinCombo">
		<OPTION>========== 프린터 트레이 목록 ========== </OPTION>
	</SELECT>
  	<hr>
	<textarea id=txtData rows=3 cols=100></textarea><br>
	 -->
	<iframe name="ifrmRexPreview1" id="ifrmRexPreview1" src='rexpreview.jsp' width="100%" height="80%"></iframe>
	
	<!--script type="text/javascript">
		rex_writeRexCtl("RexCtl", "0", "0");
	</script-->
	
	<!--  plugin
	<SCRIPT language=JavaScript SRC="/RexServer30/plugin/markany/js/markany.js"></SCRIPT>
	<SCRIPT language=JavaScript SRC="/RexServer30/plugin/bcqre/js/bcqre.js"></SCRIPT>
	-->
	<br/>
	<br/>
</body>
</html>