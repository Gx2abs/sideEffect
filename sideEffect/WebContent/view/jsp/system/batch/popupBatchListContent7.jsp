<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
<table class="bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">문서번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고 지역</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">식약처보고일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고구분1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고구분2</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고구분3</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고구분4</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고구분5</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고일자1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고일자2</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고일자3</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고일자4</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고일자5</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">부작용결과1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">부작용결과2</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">부작용결과3</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">부작용결과4</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">부작용결과5</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">부작용결과6</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">부작용결과7</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">환자문제코드1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">환자문제코드2</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">환자문제코드3</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">의료기기문제코드1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">의료기기문제코드2</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">의료기기문제코드3</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구성요소코드1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구성요소코드2</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구성요소코드3</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">제조국</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="report" items="${requestScope.list }">
				 	<tr  >
				 		<td colspan="2"><!-- 문서번호 -->
				 			<fmt:formatNumber maxFractionDigits="0">${report.document_number }</fmt:formatNumber>
				 		</td>
				 		<td class="subject" colspan="2"><!-- 보고 지역 -->
				 			${report.gubun.propertyValue }
				 		</td>
				 		<td colspan="2"><!--  식약처보고일자 -->
				 			${report.mreport_date }
				 		</td>
				 		<td colspan="2"><!--  보고구분1 -->
				 			${report.reportType1.propertyValue }
				 		</td>
				 		<td colspan="2"><!-- 보고구분2 -->
				 			${report.reportType2.propertyValue }
				 		</td>
				 		<td colspan="2"><!-- 보고구분3 -->
				 			${report.reportType3.propertyValue }
						</td>
				 		<td colspan="2"><!-- 보고구분4 -->
				 			${report.reportType4.propertyValue }
				 		</td>
				 		<td colspan="2"><!-- 보고구분5 -->
				 			${report.reportType5.propertyValue }
				 		</td>
				 		<td colspan="2">
				 			${report.reportDate1}
				 		</td>
				 		<td colspan="2">
				 			${report.reportDate2}
				 		</td>
				 		<td colspan="2">
				 			${report.reportDate3}
				 		</td>
				 		<td colspan="2">
				 			${report.reportDate4}
				 		</td>
				 		<td colspan="2">
				 			${report.reportDate5}
				 		</td>
				 		<td colspan="2">${report.sideeffectResult1.property_value }
				 		</td>
				 		<td colspan="2">${report.sideeffectResult2.property_value }
				 		</td>
				 		<td colspan="2">${report.sideeffectResult3.property_value }
				 		</td>
				 		<td colspan="2">${report.sideeffectResult4.property_value }
				 		</td>
				 		<td colspan="2">${report.sideeffectResult5.property_value }
				 		</td>
				 		<td colspan="2">${report.sideeffectResult6.property_value }
				 		</td>
				 		<td colspan="2">${report.sideeffectResult7.property_value }
				 		</td>
				 		<td colspan="2">${report.patientCondition1.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.patientCondition2.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.patientCondition3.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.deviceCode1.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.deviceCode2.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.deviceCode3.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.componentCode1.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.componentCode2.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.componentCode3.fdaSourcePtKor }
				 		</td>
				 		<td colspan="2">${report.country_manufactured.propertyValue }
				 		</td>
				 		<td colspan="2">
				 		${report.meb_item.mea_item.class_kor_name }
				 		</td>
				 	</tr>
				 </c:forEach>
		 	</c:when>
		 	<c:otherwise>
		 		<tr>
		 			<td>no data</td>
				</tr>
			</c:otherwise>
		 </c:choose>
 	</table>
 	<div>
 		${count }
 	</div>
 	<div class="paging">
 		${requestScope.pageString }
 	</div>