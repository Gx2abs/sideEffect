<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 
  <%
 	String patient_extra_info = (String) request.getAttribute("patient_extra_info");
  	String followUp = (String) request.getAttribute("followup");
 	String extra_info = (String) request.getAttribute("extra_info");
 	
 	String side_result_from_reporter = (String) request.getAttribute("side_result_from_reporter");
 	String side_cause_from_reporter = (String) request.getAttribute("side_cause_from_reporter");
 	String patient_condition_from_reporter = (String) request.getAttribute("patient_condition_from_reporter");
 	String meddev_code_from_reporter = (String) request.getAttribute("meddev_code_from_reporter");
 	String omponent_code_from_reporter = (String) request.getAttribute("omponent_code_from_reporter");
 	String causality_from_reporter = (String) request.getAttribute("causality_from_reporter");
 	
 	String hyhtest = (String) request.getAttribute("hyhtest");
 	
 	
 	patient_extra_info = Function.fnTagOn(patient_extra_info);
 	followUp = Function.fnTagOn(followUp);
    extra_info = Function.fnTagOn(extra_info);
    
    side_result_from_reporter = Function.fnTagOn(side_result_from_reporter);
    side_cause_from_reporter = Function.fnTagOn(side_cause_from_reporter);
    patient_condition_from_reporter = Function.fnTagOn(patient_condition_from_reporter);
    meddev_code_from_reporter = Function.fnTagOn(meddev_code_from_reporter);
    omponent_code_from_reporter = Function.fnTagOn(omponent_code_from_reporter);
    causality_from_reporter = Function.fnTagOn(causality_from_reporter);
    
    hyhtest = Function.fnTagOn(hyhtest);
    
 	//System.out.println("followUp : " + followUp);
 %>
 
<body style="overflow-X:hidden">

 <form name="sideEffectReportViewForm1" method="post" action="sideEffectReport.do">
 <input type="hidden" name="action" value="delete"/>
 <input type="hidden" name="articleId" value="${article.id }"/>
 
 	<div style="width: 970; margin: 20 20 20 20;">
 	<img src="${titleImg }" />
 	<table class="bordered fitToParent view_table" >
		<colgroup>
				<col width="20%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="4" class="textCenter">보고의 종류</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="tdHead">구분</th>
					<td colspan="3">	
						<c:choose>
			 				<c:when test="${!empty requestScope.countryList}">
			 					<c:forEach var="country" items="${countryList }">
			 						<c:choose>
			 							<c:when test="${country.id eq article.colCountryReportedIn  }">
			 								${country.propertyValue }
			 							</c:when>
			 						</c:choose>
				 				</c:forEach>
				 			</c:when>
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
									<c:forEach var="varReportDate" items="${article.sideEffectReportTypeDate }">
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
					<td class="tdBody">${fn:substring(article.mreport_date,0 ,10) }</td>
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
				<col width="20%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
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
					<td class="tdBody">${article.representatives }</td>
				</tr>
				<tr>
					<th class="tdHead">담당자명</th>
					<td class="tdBody">${article.manager }</td>
					<th class="tdHead"> 전화</th>
					<td class="tdBody">${article.report_tel }</td>
				</tr>
				<tr>
					<th class="tdHead">FAX</th>
					<td class="tdBody">${article.fax }</td>
					<th class="tdHead"> EMAIL</th>
					<td class="tdBody">${article.email }</td>
				</tr>
				<tr>
					<th class="tdHead">주소</th>
					<td colspan="3">${article.report_address }</td>
				</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
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
					<td class="tdBody">${article.meb_item.meddev_entp_seq }</td>
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
							<c:when test="${article.meb_item.mea_item.mea_class_no ne '' and article.meb_item.mea_item.grade == 0}">
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
		 					<c:when test="${article.meb_item.meddev_item_no ne ''}">${article.meb_item.meddev_item_no }호</c:when>
		 				</c:choose>
					</td>
					<th class="tdHead">제조국/제조원</th>
					<td class="tdBody">
						<c:choose>
							<c:when test="${article.country_manufactured.propertyValue ne '' and article.manufacturer ne ''}">
								${article.country_manufactured.propertyValue } / ${article.manufacturer }
							</c:when> 
							<c:when test="${article.country_manufactured.propertyValue ne '' and article.manufacturer eq ''}">
								${article.country_manufactured.propertyValue } 
							</c:when> 
							<c:when test="${article.country_manufactured.propertyValue eq '' and article.manufacturer ne ''}">
								${article.manufacturer }
							</c:when>
							<c:when test="${article.country_manufactured.propertyValue eq '' and article.manufacturer eq ''}">
								
							</c:when>  
						</c:choose>
					</td>
				</tr>
				<tr>
					<th rowspan="2">형명</th>
					
					<th class="tdHead"> 형명</th>
					<td class="tdBody">
						<c:forEach var="mebType" items="${article.sideEffectReportMebTypeInfo}">
							${mebType.type_name }<br>
						</c:forEach>
					</td>
				</tr> 
				<tr>
					<th class="tdHead">제조번호</th>  
					<td colspan="3">
						${article.serial_number}
					</td>
				</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	<table class="bordered fitToParent view_table" >
		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="5" class="textCenter">환자정보</th>
				</tr>
			</thead>
			<tbody>
			
				<tr>
					<th rowspan="2">환자정보</th>
					<th class="tdHead">성명</th>
					<td>${article.patient_name }</td>
					<th class="tdDead">성별/나이</th>
					<td>
						<c:choose>
							<c:when test="${article.gender.propertyValue ne '' and article.patient_age > 0}">
								${article.gender.propertyValue } / ${article.patient_age }세	
							</c:when>
							<c:when test="${article.gender.propertyValue ne '' and article.patient_age == 0}">
								${article.gender.propertyValue }		
							</c:when>
							<c:when test="${article.gender.propertyValue eq '' and article.patient_age > 0}">
								${article.patient_age }세		
							</c:when>
							<c:when test="${article.gender.propertyValue eq '' and article.patient_age == 0}">
								
							</c:when>
						</c:choose>
					</td>
				</tr>
			
			<tr>
				<th class="tdHead">기타 특이사항</th>
					<td colspan="3">
					<!-- 
						<c:out value="${article.patient_extra_info }" escapeXml="false"></c:out>
					 -->
					 	<c:out value="<%=patient_extra_info %>" escapeXml="false"></c:out>
					</td>
					
			</tr>
			</tbody>
 	</table>
 	
 	<br/>
 	
 	
 	<table class="bordered fitToParent view_table" >
		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="30%" />
				<col width="20%" />
				<col width="30%" />
		</colgroup>
			<thead>
				<tr>
					<th colspan="5" class="textCenter">부작용 정보</th>
				</tr>
			</thead>
			<tbody>
			
				<tr>
					<th rowspan="3">부작용발생기간</th>
					<th class="tdHead">최초인지일자</th>
					<td colspan="3">${fn:substring(article.side_effect_first_date, 0, 10) }</td>
				</tr>
				<tr>
					<th class="tdHead">부작용 발생일자</th>
					<td colspan="3">${fn:substring(article.side_effect_generation_date,0,10) }</td>
				</tr>
				<tr>
					<th class="tdHead">종료일자</th>
					<td colspan="3">${fn:substring(article.side_effect_last_date,0,10) }</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">진행상황</th>
					<td colspan="3">${article.reportStatus.propertyValue }</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">부작용 결과 및 위해정도</th>
					<td>
						<c:forEach var="result" items="${article.sideeffectResult }"  varStatus="i">
							<c:choose>
								<c:when test="${result.propertyValue eq '기타'}">
									${result.propertyValue } (${article.result_etc})<br>
								</c:when>
								<c:otherwise>
									${result.propertyValue }<br>
								</c:otherwise>
							</c:choose>		
								
								
						</c:forEach>
					</td>
					<th class="tdHead">부작용 결과 및 위해정도<br> &nbsp;&nbsp;(업체보고)</th>
					<td colspan="3"><c:out value="<%=side_result_from_reporter%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">부작용 원인 분류</th>
					<td>
						<c:forEach var="cause" items="${article.sideeffectCause }"  varStatus="i">
							<c:choose>
								<c:when test="${cause.property_value eq '기타'}">
									${cause.property_value } (${article.cause_etc })<br>	
								</c:when>
								<c:otherwise>
									${cause.property_value } <br>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</td>
					<th class="tdHead">부작용 원인 분류 (업체보고)</th>
					<td colspan="3"><c:out value="<%=side_cause_from_reporter%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th rowspan="3">부작용 문제<br>&nbsp;코드</th>
					<th class="tdHead">환자문제<br>&nbsp;코드</th>
					<td>
					<c:forEach var="patientCode" items="${article.patientCodeCondition }">
						${patientCode.fdaCode }(${patientCode.fdaSourcePtKor })
					</c:forEach>
					</td>
					<th class="tdHead">환자문제코드 (업체보고)</th>
					<td><c:out value="<%=patient_condition_from_reporter%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th class="tdHead">의료기기문제<br>&nbsp;코드</th>
					<td>
					<c:forEach var="medicalCode" items="${article.medicalCode }">
						${medicalCode.fdaCode }(${medicalCode.fdaSourcePtKor })
					</c:forEach>
					</td>
					<th class="tdHead">의료기기문제코드 (업체보고)</th>
					<td><c:out value="<%=meddev_code_from_reporter%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th class="tdHead">구성요소<br>&nbsp;코드</th>
					<td>
					<c:forEach var="componentCode" items="${article.juncComponentCode }">
						${componentCode.fdaCode }(${componentCode.fdaSourcePtKor })
					</c:forEach>
					</td>
					<th class="tdHead">구성요소코드 (업체보고)</th>
					<td><c:out value="<%=omponent_code_from_reporter%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">인과관계</th>
					<td>${article.causality.propertyValue }</td>
					<th class="tdHead">인과관계 (업체보고)</th>
					<td><c:out value="<%=causality_from_reporter%>" escapeXml="false"></c:out></td>
				</tr>
				
				<tr>
					<th class="tdHead" colspan="2">부작용 원인 및 내용</th>
					<td colspan="3"><c:out value="<%=extra_info%>" escapeXml="false"></c:out></td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">경과 및 후속조치</th>
					<td colspan="3">
					<!-- 
						<c:out value="${article.followup }"  escapeXml="false"></c:out>
					 -->
					 	<c:out value="<%=followUp %>"  escapeXml="false"></c:out>
					</td>
					
				</tr>
				<tr>
					<th class="tdHead" colspan="2">사후조치 결과</th>
					<td colspan="3">
						${article.obj_follow_up_action.property_value } (${article.followUpActionEtc})
					</td>
				</tr>
				<tr>
					<th rowspan="2">부작용발생시설</th>
					<th class="tdHead">기관명</th>
					<td>${article.organization }</td>
					<th class="tdHead">연락처</th>
					<td>${article.organization_tel }</td>
				</tr>
				<tr>
					<th class="tdHead">주소</th>
					<td colspan="3">${article.organization_address}</td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">식약처 후속조치</th>
					<td colspan="3">
						<c:out value="${article.kfda_followup}" escapeXml="false"></c:out>
					 </td>
				</tr>
				<tr>
					<th class="tdHead" colspan="2">기타사항</th>
					<td colspan="3">
						<c:out value="${article.extra_info2}" escapeXml="false"></c:out>
					 </td>
				</tr>
				
				 <!-- 
				<tr>
					<th class="tdHead" colspan="2">hyhtest</th>
					<td colspan="3">
						<c:out value="<//%=hyhtest%>"  escapeXml="false"></c:out>
					 </td>
				</tr>
				 -->
				 
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
			<tbody>
				<tr>
					<th>No.</th>
					<th>파일명</th>
					<th>파일설명  
					      <a href="${pageContext.request.contextPath}/attachment.do?action=fileDownloadAll&articleId=${article.id}&type=1" >
					               모두 저장</a>
			     	</th>
				</tr>
				<c:forEach var="file" items="${requestScope.fileList }" varStatus="i">
					<tr>
						<td class="tdHead">${i.count }
						<input type="hidden" name="fileId" value="${file.id }" />
						</td>
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
 
 	<br/>
 	<br/>
 	
 	
 	
 	<div style="float: right; padding: 10px 0 20px 0;">
 		<c:choose>
 			<c:when test="${sessionScope.user != null && sessionScope.user.privilegeId == 2}">
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
             	
 	</form>
 
 <script type="text/javascript">
  	function update1(articleId){
  		location.href="sideEffectReport.do?action=update&articleId="+articleId;
  	}
  	
  	function del(articleId){
  		var frm = document.sideEffectReportViewForm1;
  		
  		if(confirm("정말 삭제하시겠습니까?.")){
  			frm.submit();
  		}else{
  			return;
  		}
  			
  		//location.href="sideEffectReport.do?action=delete&articleId="+articleId;
  	}
  	
  	function cancel(){
  		location.href="sideEffectReport.do?action=list";
  	}
  	
  	function fileDownloadAll(){
  		var xmlHttpRequest = new XMLHttpRequest();
			
  		 var method = "POST";
  		 var url = "properties.do?action=fileDownloadAll";
  		 var async = true;
  				
  		 xmlHttpRequest.open(method, url, async);
  		 xmlHttpRequest.send();
  				
  		 xmlHttpRequest.onreadystatechange = function(){
  					
  					if ( xmlHttpRequest.status ==200 && xmlHttpRequest.readyState ==4){
  						//alert(xmlHttpRequest.responseText);
  						
  						if(xmlHttpRequest.responseText > 0){
  							//alert("통신 성공");
  						}else{
  							//alert("통신 실패");
  						}
  						
  					} 
  				};
  	}
 </script>
 
 
 </body>