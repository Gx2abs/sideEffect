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
<%@page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%
DecimalFormat df = new DecimalFormat("00");
Calendar currentCalendar = Calendar.getInstance();

//현재 날짜
String strYear = Integer.toString(currentCalendar.get(Calendar.YEAR));
String strMonth = df.format(currentCalendar.get(Calendar.MONTH) + 1);
String strDay = df.format(currentCalendar.get(Calendar.DATE));
String strDate = strYear+ "." + strMonth + "." + strDay;

%>

</head>
<body>
	<div style="width: 600px;">
		<%@include file="/view/jsp/common/simplePageTitle1.jsp" %><br>
	<div style="float: right; margin-bottom: 5px;">(<%=strDate %>일 기준)</div>
		<table class="bordered fitToParent view_table report_table" style="margin-bottom: 30px;">
			<tr>
				<th></th>
				<th>${requestScope.year_5}</th>
				<th>${requestScope.year_4}</th>
				<th>${requestScope.year_3}</th>
				<th>${requestScope.year_2}</th>
				<th>${requestScope.year_1}</th>
			</tr>
			<tr>
				<th>부작용보고건수</th>
				<td>${requestScope.sicount_5 }</td>
				<td>${requestScope.sicount_4 }</td>
				<td>${requestScope.sicount_3 }</td>
				<td>${requestScope.sicount_2 }</td>
				<td>${requestScope.sicount_1 }</td>
			</tr>
			<tr>
				<th>안전성보고건수</th>
				<td>${requestScope.sacount_5 }</td>
				<td>${requestScope.sacount_4 }</td>
				<td>${requestScope.sacount_3 }</td>
				<td>${requestScope.sacount_2 }</td>
				<td>${requestScope.sacount_1 }</td>
			</tr>
		</table>
		<!-- 
	    <ul class="sideEffectStats">
	       <li><a href="javascript:popM('popReporterType')"><img src="view/style/images/icon.jpg" alt="." />보고자 유형별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popMeaCompany')"><img src="view/style/images/icon.jpg" alt="." />대상 업체명별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popCountry')"><img src="view/style/images/icon.jpg" alt="." />제조국별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popMeaClassNo')"><img src="view/style/images/icon.jpg" alt="." />품목별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popItemGrade')"><img src="view/style/images/icon.jpg" alt="." />등급별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popCausality')"><img src="view/style/images/icon.jpg" alt="." />인과관계별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popPatient')"><img src="view/style/images/icon.jpg" alt="." />환자문제별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popMedical')"><img src="view/style/images/icon.jpg" alt="." />의료기기문제별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popComponent')"><img src="view/style/images/icon.jpg" alt="." />구성요소문제별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popCauseOfSideeffect')"><img src="view/style/images/icon.jpg" alt="." />부작용 원인분류별 건수 / 수록기간 2012년</a></li>
	       <li><a href="javascript:popM('popSideeffectResult')"><img src="view/style/images/icon.jpg" alt="." />결과 및 위해성정도별 건수 / 수록기간 2012년</a></li>
	    </ul> -->
	</div>
</body>
</html>