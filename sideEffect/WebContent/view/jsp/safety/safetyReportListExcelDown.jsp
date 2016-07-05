<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>

<%

 response.setHeader("Content-Type", "application/vnd.ms-xls");

 response.setHeader("Content-Disposition", "inline; filename=myfile.xls");

%>

<table class="bordered" style="width: 1900px; ">

 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					첨부
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					문서번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					구분
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					보고구분
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					보고일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					식약처보고일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					보고자유형
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					대상업체명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					업허가번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					분류번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					등급
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목허가번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					제조원
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					형명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					제조번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					환자분제코드
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					의료기기문제코드
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					구성요소코드
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					진행상황
 				</th>
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr onclick="read(${item.id});" >
				 		<td >
				 			${item.id }
				 		</td>
				 		<td >
				 			${item.representative }
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_item_seq }
				 		</td>
				 		<td >
				 			${item.countryReportedIn.propertyValue }
				 		</td>
				 		<td >
				 			${item.reportType.propertyValue }
				 		</td>
				 		<td >
				 			${item.reportDate }
				 		</td>
				 		<td >
				 			${item.kfdaReportDate }
				 		</td>
				 		<td >
				 			<c:choose>
				 				<c:when test="${!empty item.reporterTypes }">
				 					
				 					<c:forEach var="reporterType" items="${item.reporterTypes }">
				 						${reporterType.propertyValue }			
									</c:forEach>
				 				
				 				</c:when>
				 				<c:otherwise>
				 					no data
				 				</c:otherwise>
				 			</c:choose>
				 		</td>
				 		<td >
				 			${item.meb_item.manuf_name }
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_entp_seq }
				 		</td>
				 		<td >
				 			${item.meb_item.mea_class_no }
				 		</td>
				 		<td >
				 			${item.meb_item.grade }
				 		</td>
				 		<td >
				 			${item.meb_item.item_name }
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_item_seq }
				 		</td>
				 		<td >
				 			${item.meb_item.manuf_name }
				 		</td>
				 		<td >
				 			${item.meb_item.shape }
				 		</td>
				 		<td >
				 			${item.id }
				 		</td>
				 		<td >
				 			${item.patientCondition.id }
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_item_seq }
				 		</td>
				 		<td >
				 			${item.componentCode.id }
				 		</td>
				 		<td >
				 			${item.reportStatus.propertyValue }
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