<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 
 <%
	String safety_cause_content = (String) request.getAttribute("safety_cause_content");
	String followup = (String) request.getAttribute("followup");
	String extra_info = (String) request.getAttribute("extra_info");
	
	safety_cause_content = Function.fnTagOn(safety_cause_content);
	followup = Function.fnTagOn(followup);
    extra_info = Function.fnTagOn(extra_info);

 %>
 
 <body style="overflow-X:hidden">
 
<form name="safetyReportViewForm1" method="post" action="safetyReport.do">
 <input type="hidden" name="action" value="delete"/>
 <input type="hidden" name="articleId" value="${article.id }"/>
 
 
 	<div style="width: 970; margin: 20 20 20 20;">
 	<img src="${titleImg }" />
 	<table class="bordered fitToParent view_table" >
		<colgroup>
				<col width="21%" />
				<col width="30%" />
				<col width="20%" />
				<col width="29%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="4" class="textCenter">보고의종류</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead">구분</th>
					<td colspan="3">
					<c:choose>
						<c:when test="${article.countryReportedIn.propertyValue ne '정보없음'}">${article.countryReportedIn.propertyValue }</c:when>
					</c:choose>
					</td>
				</tr>
				<tr>
					<th class="tdHead" style="height: 100%; min-height: 24px;">보고구분/보고일자</th>
					<td class="tdBody">
						<table>
							<tr>
								<td style="width: 60px; padding: 5px 0 5px 0; border-style: none;">
									<c:forEach var="varReportType" items="${article.reportType }">
										<div style="padding-top: 3px;">${varReportType.propertyValue }</div>
									</c:forEach>
								</td>
								<td style="padding: 5px 0 5px 0; border-style: none;">
									<c:forEach var="varReportDate" items="${article.safetyReportTypeDate }">
										<c:choose>
											<c:when test="${varReportDate.report_date  ne null}">
												<div style="padding-top: 3px;">${fn:substring(varReportDate.report_date,0,10) }</div>
											</c:when>
										</c:choose>
									</c:forEach>
								</td>
							</tr>
						</table>	
					</td>
					<th class="tdHead"> 식약처 보고일자</th>
					<td class="tdBody">${fn:substring(article.mreport_date,0,10)}</td>
				</tr>
				<tr>
					<th class="tdHead">보고자 유형</th>
					<td colspan="3">
						<c:forEach var="reporterType" items="${article.reporterTypes }"  varStatus="i">
								<c:choose>
									<c:when test="${reporterType.propertyValue eq '기타' }">
								${reporterType.propertyValue } (${article.reporter_etc })<br>
									</c:when>
									<c:otherwise>
								${reporterType.propertyValue }<br>
									</c:otherwise>
								</c:choose>
						</c:forEach>
					</td>
				</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
 		<colgroup>
				<col width="21%" />
				<col width="30%" />
				<col width="20%" />
				<col width="29%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="4" class="textCenter">보고자 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead">상호명</th>
					<td class="tdBody">${article.company_name }</td>
					<th class="tdHead">대표자 성명</th>
					<td class="tdBody">${article.representative }</td>
				</tr>
				<tr>
					<th class="tdHead">담당자명</th>
					<td class="tdBody">${article.manager }</td>
					<th class="tdHead"> 전화</th>
					<td class="tdBody">${article.telephone }</td>
				</tr>
				<tr>
					<th class="tdHead">FAX</th>
					<td class="tdBody">${article.fax }</td>
					<th class="tdHead"> EMAIL</th>
					<td class="tdBody">${article.email }</td>
				</tr>
				<tr>
					<th class="tdHead">주소</th>
					<td colspan="3">${article.address }</td>
				</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
 		<colgroup>
				<col width="11%" />
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="29%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="5" class="textCenter">의료기기 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead" colspan="2">대상업체명</th>
					<td class="tdBody">${article.meb_item.company.entp_name }</td>
					<th class="tdHead"> 업허가번호</th>
					<td class="tdBody">${article.meb_item.company.meddev_entp_no }</td>
				</tr>
				<tr>
					<th rowspan="2">제품명</th>
					<th class="tdHead">품목명</th>
					<td class="tdBody">${article.meb_item.mea_item.class_kor_name }</td>
					<th class="tdHead"> 분류번호/등급</th>
					<td class="tdBody">
						<c:choose>
							<c:when test="${article.meb_item.mea_item.mea_class_no ne '' and article.meb_item.mea_item.grade > 0}">
								${article.meb_item.mea_item.mea_class_no } / ${article.meb_item.mea_item.categoryGrade.propertyValue }
							</c:when> 
							<c:when test="${article.meb_item.mea_class_no ne '' and article.meb_item.mea_item.grade == 0}">
								${article.meb_item.mea_item.mea_class_no } 
							</c:when> 
							<c:when test="${article.meb_item.mea_item.mea_class_no eq '' and article.meb_item.mea_item.grade > 0}">
								${article.meb_item.categoryGrade.propertyValue }
							</c:when>
							<c:when test="${article.meb_item.mea_item.mea_class_no eq '' and article.meb_item.mea_item.grade == 0}">
								
							</c:when>  
						</c:choose>
					</td>      
				</tr>
				<tr>
					<th class="tdHead">품목허가번호</th>
					<td class="tdBody">
						<c:choose>
		 					<c:when test="${article.meb_item.cobFlagType.propertyValue ne '없음' }">
		 						${article.meb_item.cobFlagType.propertyValue }
		 					</c:when>
		 				</c:choose>
		 				<c:choose>
		 					<c:when test="${article.meb_item.id ne ''}">${article.meb_item.meddev_item_no }</c:when>
		 				</c:choose>
					</td>
					<th class="tdHead"> 제조국/제조원</th>
					<td class="tdBody">
						<c:choose>
							<c:when test="${article.country_manufactured.propertyValue ne '정보없음' and article.manufacturer ne ''}">
								${article.country_manufactured.propertyValue } / ${article.manufacturer }
							</c:when> 
							<c:when test="${article.country_manufactured.propertyValue ne '정보없음' and article.manufacturer eq ''}">
								${article.country_manufactured.propertyValue } 
							</c:when> 
							<c:when test="${article.country_manufactured.propertyValue eq '정보없음' and article.manufacturer ne ''}">
								${article.manufacturer }
							</c:when>
							<c:when test="${article.country_manufactured.propertyValue eq '정보없음' and article.manufacturer eq ''}">
								
							</c:when>  
						</c:choose>
					</td>
				</tr>
				<tr>
					<th rowspan="2" >형명</th>
					
					<th class="tdHead"> 형명</th>  
					<td class="tdBody">
						<c:forEach var="mebType" items="${article.safetyReportMebTypeInfo}">
							${mebType.type_name }<br>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th class="tdHead">제조번호</th>
					<td colspan="3">${article.serial_number }</td>
				</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
 		<colgroup>
				<col width="11%" />
				<col width="10%" />
				<col width="79%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="3" class="textCenter">안전성 정보</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead" colspan="2">안전성 정보 원인<br>&nbsp;및 내용</th>
					<td><c:out value="<%=safety_cause_content%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th rowspan="3">안전성 문제<br>&nbsp;코드</th>
					<th class="tdHead">환자문제<br>&nbsp;코드</th>
					<td><c:forEach var="patientCode" items="${article.patientCodeCondition }">
						${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
					</c:forEach>
					</td>
				</tr>
				<tr>
					<th class="tdHead">의료기기문제<br>&nbsp;코드</th>
					<td>
					<c:forEach var="medicalCode" items="${article.medicalCode }">
						${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
					</c:forEach>
					</td>
				</tr>
				<tr>
					<th class="tdHead">구성요소<br>&nbsp;코드</th>
					<td>
					<c:forEach var="componentCode" items="${article.juncComponentCode }">
						${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
					</c:forEach>
					</td>
					
				</tr>
				<tr>
					<th class="tdHead" colspan="2">업소의 후속조치</th>
					<td><c:out value="<%=followup%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">사후조치 결과</th>
					<td colspan="3">
						${article.obj_follow_up_action.property_value } (${article.followUpActionEtc })
					</td>
				</tr>
				<tr>	
					<th colspan="2">안전성정보보고 유형</th>
					<td>
						<c:forEach var="safetyReportType" items="${article.report_type }" varStatus="num">
							<c:choose>
								<c:when test="${safetyReportType.propertyValue eq '기타'}">
									${safetyReportType.propertyValue } (${article.safety_report_type_etc })<br>
								</c:when>
								<c:otherwise>
									${safetyReportType.propertyValue }<br>	
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
				<tr>
					<th class="tdHead" colspan="2">진행상황</th>
					<td>${article.reportStatus.propertyValue }</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">식약처 후속조치</th>
					<td colspan="3">
						<c:out value="${article.kfda_followup}" escapeXml="false"></c:out>
					 </td>
				</tr>
				 <tr>
						<th class="tdHead" colspan="2">기타사항</th>
						<td>
							<c:out value="<%=extra_info%>" escapeXml="false"></c:out>
						</td>
					</tr>
					<tr>
					<th class="tdHead" colspan="2">등록일</th>
					<td colspan="3">
						${fn:substring(article.first_modified,0,10) }
					</td>
				</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
			<colgroup>
				<col width="20%" />
				<col width="30%" />
				<col width="50%" />
			</colgroup>
			<thead>
				<tr>	
					<th colspan="3" class="textCenter">첨부자료</th>
				</tr>
			</thead>
				<tr>
					<th>No.</th>	
					<th>첨부파일</th>
					<th>파일설명
					   <a href="${pageContext.request.contextPath}/attachment.do?action=fileDownloadAll&articleId=${article.id}&type=2" >
					               모두 저장</a>
					</th>
				</tr>
			<tbody>
			
				<c:forEach var="file" items="${requestScope.fileList }" varStatus="i">
					<tr>
						
						<td class="tdHead">${i.count }
							<input type="hidden" name="fileId" value="${file.id }" /></td>
						<td>
							<a href="${pageContext.request.contextPath}/attachment.do?action=fileDown&filepath=${file.fullURL }&filename=${file.name}" >
								${file.name }
							</a>
						</td>
						<td>${file.fileName }</td>
					</tr>
				</c:forEach>
				
			</tbody>
 	</table>
 	
 	<br>
 	<br>
 	
 	<div style="float: right; padding: 10px 0 20px 0;">
 		<c:choose>
 			<c:when test="${sessionScope.user != nul && sessionScope.user.privilegeId == 2}">
		 		<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="hover1" onclick="update1(${article.id})"/>
		 		<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="hover1" onclick="del(${article.id})"/>
 			</c:when>
			<c:otherwise>
			</c:otherwise>
 		</c:choose>
 		<!-- 
 		<img src="view/style/images/bt_list.jpg" name="list" alt="list" class="buttonised" onclick="cancel()"/>
 		 -->
 	</div>
 	
 	</div>
             
 
 <script type="text/javascript">
  	function update1(articleId){
  		location.href="safetyReport.do?action=update&articleId="+articleId;
  	}
  	
  	function del(articleId){
  		var frm = document.safetyReportViewForm1;
  		
  		if(confirm("정말 삭제하시겠습니까?.")){
  			frm.submit();
  		}else{
  			return;
  		}
			
  		//location.href="safetyReport.do?action=delete&articleId="+articleId;
  	}
  	
  	function cancel(){
  		location.href="safetyReport.do?action=list";
  	}
 </script>
 
 
 </body>
 