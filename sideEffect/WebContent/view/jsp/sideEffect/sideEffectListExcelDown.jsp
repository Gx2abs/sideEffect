<%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="application/vnd.ms-excel;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="common.*" %>


<script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-1.9.1.js"></script>

<style type+"text/css">
.bordered{
	
	border: 1px solid grey;
	
}
  
.fitToParent{
	
	width: inherit;
	
}

.buttonised{
	cursor: pointer;
	
}

.hidden{
	display: none;
}
</style>


<%
   
 //response.setHeader("Content-Type", "application/vnd.ms-xls");

 //response.setHeader("Content-Type", "application/vnd.ms-excel");

 //response.setHeader("Content-Disposition", "inline; filename=myfile.xls");
 response.setHeader("Content-Disposition","attachment;filename=myfile.xls");
 //response.setHeader("Content-Disposition","JSP Generated Data");

%>

<body style="text-align: left;">
<table class="bordered" style="width: 3000px;" border >

 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">첨부</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">문서번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">식약처보고일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">보고자유형</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">대상업체명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업허가번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">분류번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
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
 					성명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					성별
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					나이
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					최초인지일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					부작용발생일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					종료일자
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					부작용결과 및 위해정도
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					부작용 원인 분류
 				</th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					환자문제코드
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					의료기기문제코드
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					구성요소코드
 				</th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					인과관계
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					진행상황
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					기관명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					상호명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					대표자 성명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					담당자명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					전화
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					FAX
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					EMAIL
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"></th>
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					주소
 				</th>
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list1 }">
		 		<c:forEach var="item" items="${requestScope.list1 }">
				 	<tr>
				 		<td >
				 		
				 		</td>
				 		
				 		<td>
				 		
				 		</td>
				 		      
				 		<td>
				 			<c:forEach items="${item.attachments}" var="attachment">
				 				${attachment.name}
				 			</c:forEach>
				 		</td>
				 		<td >
			 				
				 		</td>
				 		<td style="mso-number-format:'_-* \#\\#\#0\">
				 			${item.document_number }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.gubun.propertyValue }
				 		</td>
				 		
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="varReportType" items="${item.reportType }">
								${varReportType.propertyValue }
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td style="text-align: left;">
				 			<c:forEach var="varReportDate" items="${item.sideEffectReportTypeDate }">
					 			<c:choose>
									<c:when test="${varReportDate.report_date  ne null }">
										${fn:substring(varReportDate.report_date,0,10) }
									</c:when>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${fn:substring(item.mreport_date,0 ,10) } 			
				 		</td>
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="reporterType" items="${item.reporterTypes }"  varStatus="i">
								<c:choose>
									<c:when test="${reporterType.propertyValue eq '기타' }">
										${reporterType.propertyValue } (${item.reporter_etc })
									</c:when>
									<c:otherwise>
										${reporterType.propertyValue }
									</c:otherwise>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
							${item.meb_item.company.entp_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_entp_seq }
				 		</td>
				 		<td >
				 	   	
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.mea_class_no }
						</td>
				 		<td >
				 			
				 		</td>
				 		<td >
							${item.meb_item.mea_item.categoryGrade.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.class_kor_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 		<c:choose>
		 					<c:when test="${item.meb_item.cobFlagType.propertyValue ne '없음' }">
		 						${item.meb_item.cobFlagType.propertyValue }
		 					</c:when>
		 				</c:choose>
		 				<c:choose>
		 					<c:when test="${item.meb_item.meddev_item_no ne ''}">${item.meb_item.meddev_item_no }호</c:when>
		 				</c:choose>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.country_manufactured.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			 ${item.manufacturer }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.serial_number}
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.gender.propertyValue } 
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_age }세
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_first_date, 0, 10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_generation_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_last_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 		<c:forEach var="result" items="${item.sideeffectResult }"  varStatus="i">
							<c:choose>
								<c:when test="${result.property_value eq '기타'}">
									${result.property_value } (${item.result_etc})
								</c:when>
								<c:otherwise>
									${result.property_value }
								</c:otherwise>
							</c:choose>		
								
								
						</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
			 			<c:forEach var="cause" items="${item.sideeffectCause }"  varStatus="i">
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									${cause.property_value } (${item.cause_etc })	
								</c:when>
								<c:otherwise>
									${cause.property_value }
								</c:otherwise>
							</c:choose>
						</c:forEach>
				 		</td>
				 		<td>
				 			<c:forEach var="patientCode" items="${item.patientCodeCondition }">
								${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			<c:forEach var="medicalCode" items="${item.medicalCode }">
								${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			<c:forEach var="componentCode" items="${item.juncComponentCode }">
								${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			${item.causality.propertyValue }
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			${item.reportStatus.propertyValue }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.organization }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.company_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.representatives }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.manager }
				 		</td>
						<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_tel }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.fax }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.email }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_address }
				 		</td>
				 		
				 		
				 		
				 		
				 	</tr>
				 </c:forEach>
				 
				 
				 
				 
				 
				 <c:forEach var="item" items="${requestScope.list1 }" end="0">
				 	<tr>
				 		<td >
				 		
				 		</td>
				 		
				 		<td>
				 		
				 		</td>
				 		      
				 		<td>
				 			<c:forEach items="${item.attachments}" var="attachment">
				 				${attachment.name}
				 			</c:forEach>
				 		</td>
				 		<td >
			 				
				 		</td>
				 		<td style="mso-number-format:'_-* \#\\#\#0\">
				 			${item.document_number }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.gubun.propertyValue }
				 		</td>
				 		
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="varReportType" items="${item.reportType }">
								${varReportType.propertyValue }
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td style="text-align: left;">
				 			<c:forEach var="varReportDate" items="${item.sideEffectReportTypeDate }">
					 			<c:choose>
									<c:when test="${varReportDate.report_date  ne null }">
										${fn:substring(varReportDate.report_date,0,10) }
									</c:when>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${fn:substring(item.mreport_date,0 ,10) } 			
				 		</td>
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="reporterType" items="${item.reporterTypes }"  varStatus="i">
								<c:choose>
									<c:when test="${reporterType.propertyValue eq '기타' }">
										${reporterType.propertyValue } (${item.reporter_etc })
									</c:when>
									<c:otherwise>
										${reporterType.propertyValue }
									</c:otherwise>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
							${item.meb_item.company.entp_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_entp_seq }
				 		</td>
				 		<td >
				 	   	
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.mea_class_no }
						</td>
				 		<td >
				 			
				 		</td>
				 		<td >
							${item.meb_item.mea_item.categoryGrade.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.class_kor_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 		<c:choose>
		 					<c:when test="${item.meb_item.cobFlagType.propertyValue ne '없음' }">
		 						${item.meb_item.cobFlagType.propertyValue }
		 					</c:when>
		 				</c:choose>
		 				<c:choose>
		 					<c:when test="${item.meb_item.meddev_item_no ne ''}">${item.meb_item.meddev_item_no }호</c:when>
		 				</c:choose>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.country_manufactured.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			 ${item.manufacturer }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.serial_number}
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.gender.propertyValue } 
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_age }세
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_first_date, 0, 10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_generation_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_last_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 		<c:forEach var="result" items="${item.sideeffectResult }"  varStatus="i">
							<c:choose>
								<c:when test="${result.property_value eq '기타'}">
									${result.property_value } (${item.result_etc})
								</c:when>
								<c:otherwise>
									${result.property_value }
								</c:otherwise>
							</c:choose>		
								
								
						</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
			 			<c:forEach var="cause" items="${item.sideeffectCause }"  varStatus="i">
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									${cause.property_value } (${item.cause_etc })	
								</c:when>
								<c:otherwise>
									${cause.property_value }
								</c:otherwise>
							</c:choose>
						</c:forEach>
				 		</td>
				 		<td>
				 			<c:forEach var="patientCode" items="${item.patientCodeCondition }">
								${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			<c:forEach var="medicalCode" items="${item.medicalCode }">
								${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			<c:forEach var="componentCode" items="${item.juncComponentCode }">
								${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			${item.causality.propertyValue }
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			${item.reportStatus.propertyValue }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.organization }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.company_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.representatives }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.manager }
				 		</td>
						<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_tel }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.fax }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.email }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_address }
				 		</td>
				 		
				 		
				 		
				 		
				 	</tr>
				 </c:forEach>
				 
				 
				 
				 
				 
				 <c:forEach var="item" items="${requestScope.list1 }" end="0">
				 	<tr>
				 		<td >
				 		
				 		</td>
				 		
				 		<td>
				 		
				 		</td>
				 		      
				 		<td>
				 			<c:forEach items="${item.attachments}" var="attachment">
				 				${attachment.name}
				 			</c:forEach>
				 		</td>
				 		<td >
			 				
				 		</td>
				 		<td style="mso-number-format:'_-* \#\\#\#0\">
				 			${item.document_number }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.gubun.propertyValue }
				 		</td>
				 		
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="varReportType" items="${item.reportType }">
								${varReportType.propertyValue }
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td style="text-align: left;">
				 			<c:forEach var="varReportDate" items="${item.sideEffectReportTypeDate }">
					 			<c:choose>
									<c:when test="${varReportDate.report_date  ne null }">
										${fn:substring(varReportDate.report_date,0,10) }
									</c:when>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${fn:substring(item.mreport_date,0 ,10) } 			
				 		</td>
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="reporterType" items="${item.reporterTypes }"  varStatus="i">
								<c:choose>
									<c:when test="${reporterType.propertyValue eq '기타' }">
										${reporterType.propertyValue } (${item.reporter_etc })
									</c:when>
									<c:otherwise>
										${reporterType.propertyValue }
									</c:otherwise>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
							${item.meb_item.company.entp_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_entp_seq }
				 		</td>
				 		<td >
				 	   	
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.mea_class_no }
						</td>
				 		<td >
				 			
				 		</td>
				 		<td >
							${item.meb_item.mea_item.categoryGrade.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.class_kor_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 		<c:choose>
		 					<c:when test="${item.meb_item.cobFlagType.propertyValue ne '없음' }">
		 						${item.meb_item.cobFlagType.propertyValue }
		 					</c:when>
		 				</c:choose>
		 				<c:choose>
		 					<c:when test="${item.meb_item.meddev_item_no ne ''}">${item.meb_item.meddev_item_no }호</c:when>
		 				</c:choose>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.country_manufactured.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			 ${item.manufacturer }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.serial_number}
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.gender.propertyValue } 
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_age }세
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_first_date, 0, 10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_generation_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_last_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 		<c:forEach var="result" items="${item.sideeffectResult }"  varStatus="i">
							<c:choose>
								<c:when test="${result.property_value eq '기타'}">
									${result.property_value } (${item.result_etc})
								</c:when>
								<c:otherwise>
									${result.property_value }
								</c:otherwise>
							</c:choose>		
								
								
						</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
			 			<c:forEach var="cause" items="${item.sideeffectCause }"  varStatus="i">
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									${cause.property_value } (${item.cause_etc })	
								</c:when>
								<c:otherwise>
									${cause.property_value }
								</c:otherwise>
							</c:choose>
						</c:forEach>
				 		</td>
				 		<td>
				 			<c:forEach var="patientCode" items="${item.patientCodeCondition }">
								${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			<c:forEach var="medicalCode" items="${item.medicalCode }">
								${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			<c:forEach var="componentCode" items="${item.juncComponentCode }">
								${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			${item.causality.propertyValue }
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			${item.reportStatus.propertyValue }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.organization }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.company_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.representatives }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.manager }
				 		</td>
						<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_tel }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.fax }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.email }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_address }
				 		</td>
				 		
				 		
				 		
				 		
				 	</tr>
				 </c:forEach>
				 
				 
				 
				 
				 <c:forEach var="item" items="${requestScope.list1 }" end="0">
				 	<tr>
				 		<td >
				 		
				 		</td>
				 		
				 		<td>
				 		
				 		</td>
				 		      
				 		<td>
				 			<c:forEach items="${item.attachments}" var="attachment">
				 				${attachment.name}
				 			</c:forEach>
				 		</td>
				 		<td >
			 				
				 		</td>
				 		<td style="mso-number-format:'_-* \#\\#\#0\">
				 			${item.document_number }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.gubun.propertyValue }
				 		</td>
				 		
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="varReportType" items="${item.reportType }">
								${varReportType.propertyValue }
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td style="text-align: left;">
				 			<c:forEach var="varReportDate" items="${item.sideEffectReportTypeDate }">
					 			<c:choose>
									<c:when test="${varReportDate.report_date  ne null }">
										${fn:substring(varReportDate.report_date,0,10) }
									</c:when>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${fn:substring(item.mreport_date,0 ,10) } 			
				 		</td>
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="reporterType" items="${item.reporterTypes }"  varStatus="i">
								<c:choose>
									<c:when test="${reporterType.propertyValue eq '기타' }">
										${reporterType.propertyValue } (${item.reporter_etc })
									</c:when>
									<c:otherwise>
										${reporterType.propertyValue }
									</c:otherwise>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
							${item.meb_item.company.entp_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_entp_seq }
				 		</td>
				 		<td >
				 	   	
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.mea_class_no }
						</td>
				 		<td >
				 			
				 		</td>
				 		<td >
							${item.meb_item.mea_item.categoryGrade.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.class_kor_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 		<c:choose>
		 					<c:when test="${item.meb_item.cobFlagType.propertyValue ne '없음' }">
		 						${item.meb_item.cobFlagType.propertyValue }
		 					</c:when>
		 				</c:choose>
		 				<c:choose>
		 					<c:when test="${item.meb_item.meddev_item_no ne ''}">${item.meb_item.meddev_item_no }호</c:when>
		 				</c:choose>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.country_manufactured.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			 ${item.manufacturer }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.serial_number}
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.gender.propertyValue } 
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_age }세
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_first_date, 0, 10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_generation_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_last_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 		<c:forEach var="result" items="${item.sideeffectResult }"  varStatus="i">
							<c:choose>
								<c:when test="${result.property_value eq '기타'}">
									${result.property_value } (${item.result_etc})
								</c:when>
								<c:otherwise>
									${result.property_value }
								</c:otherwise>
							</c:choose>		
								
								
						</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
			 			<c:forEach var="cause" items="${item.sideeffectCause }"  varStatus="i">
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									${cause.property_value } (${item.cause_etc })	
								</c:when>
								<c:otherwise>
									${cause.property_value }
								</c:otherwise>
							</c:choose>
						</c:forEach>
				 		</td>
				 		<td>
				 			<c:forEach var="patientCode" items="${item.patientCodeCondition }">
								${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			<c:forEach var="medicalCode" items="${item.medicalCode }">
								${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			<c:forEach var="componentCode" items="${item.juncComponentCode }">
								${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			${item.causality.propertyValue }
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			${item.reportStatus.propertyValue }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.organization }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.company_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.representatives }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.manager }
				 		</td>
						<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_tel }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.fax }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.email }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_address }
				 		</td>
				 		
				 		
				 		
				 		
				 	</tr>
				 </c:forEach>
				 
				 
				 
				 
				 <c:forEach var="item" items="${requestScope.list1 }" end="0">
				 	<tr>
				 		<td >
				 		
				 		</td>
				 		
				 		<td>
				 		
				 		</td>
				 		      
				 		<td>
				 			<c:forEach items="${item.attachments}" var="attachment">
				 				${attachment.name}
				 			</c:forEach>
				 		</td>
				 		<td >
			 				
				 		</td>
				 		<td style="mso-number-format:'_-* \#\\#\#0\">
				 			${item.document_number }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.gubun.propertyValue }
				 		</td>
				 		
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="varReportType" items="${item.reportType }">
								${varReportType.propertyValue }
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td style="text-align: left;">
				 			<c:forEach var="varReportDate" items="${item.sideEffectReportTypeDate }">
					 			<c:choose>
									<c:when test="${varReportDate.report_date  ne null }">
										${fn:substring(varReportDate.report_date,0,10) }
									</c:when>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${fn:substring(item.mreport_date,0 ,10) } 			
				 		</td>
				 		<td >
						
				 		</td>
				 		<td >
				 			<c:forEach var="reporterType" items="${item.reporterTypes }"  varStatus="i">
								<c:choose>
									<c:when test="${reporterType.propertyValue eq '기타' }">
										${reporterType.propertyValue } (${item.reporter_etc })
									</c:when>
									<c:otherwise>
										${reporterType.propertyValue }
									</c:otherwise>
								</c:choose>
							</c:forEach>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
							${item.meb_item.company.entp_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.meb_item.meddev_entp_seq }
				 		</td>
				 		<td >
				 	   	
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.mea_class_no }
						</td>
				 		<td >
				 			
				 		</td>
				 		<td >
							${item.meb_item.mea_item.categoryGrade.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			${item.meb_item.mea_item.class_kor_name }
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 		<c:choose>
		 					<c:when test="${item.meb_item.cobFlagType.propertyValue ne '없음' }">
		 						${item.meb_item.cobFlagType.propertyValue }
		 					</c:when>
		 				</c:choose>
		 				<c:choose>
		 					<c:when test="${item.meb_item.meddev_item_no ne ''}">${item.meb_item.meddev_item_no }호</c:when>
		 				</c:choose>
				 		</td>
				 		<td >
				 		
				 		</td>
				 		<td >
				 			${item.country_manufactured.propertyValue }
				 		</td>
				 		<td >
				 			
				 		</td>
				 		<td >
				 			 ${item.manufacturer }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.serial_number}
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.gender.propertyValue } 
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.patient_age }세
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_first_date, 0, 10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_generation_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${fn:substring(item.side_effect_last_date,0,10) }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 		<c:forEach var="result" items="${item.sideeffectResult }"  varStatus="i">
							<c:choose>
								<c:when test="${result.property_value eq '기타'}">
									${result.property_value } (${item.result_etc})
								</c:when>
								<c:otherwise>
									${result.property_value }
								</c:otherwise>
							</c:choose>		
								
								
						</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
			 			<c:forEach var="cause" items="${item.sideeffectCause }"  varStatus="i">
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									${cause.property_value } (${item.cause_etc })	
								</c:when>
								<c:otherwise>
									${cause.property_value }
								</c:otherwise>
							</c:choose>
						</c:forEach>
				 		</td>
				 		<td>
				 			<c:forEach var="patientCode" items="${item.patientCodeCondition }">
								${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			<c:forEach var="medicalCode" items="${item.medicalCode }">
								${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			<c:forEach var="componentCode" items="${item.juncComponentCode }">
								${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
							</c:forEach>
				 		</td>
				 		<td>
				 			${item.causality.propertyValue }
				 		</td>
				 		<td>
				 			
				 		</td>
				 		<td>
				 			${item.reportStatus.propertyValue }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.organization }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.company_name }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.representatives }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.manager }
				 		</td>
						<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_tel }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.fax }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.email }
				 		</td>
				 		<td>
				 		
				 		</td>
				 		<td>
				 			${item.report_address }
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
 </body>