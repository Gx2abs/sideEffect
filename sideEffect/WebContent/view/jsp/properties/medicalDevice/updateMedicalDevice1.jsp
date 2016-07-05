<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
   	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="medicalDeviceCreateForm1"  method="POST" action="properties.do">
 		<input type="hidden" name="action" value="updateMedicalDevice"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
 		<input type="hidden" name="check" value="1" />
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
					<th colspan="5">공통 코드 관리 - 의료기기문제 코드</th>
				</tr>
			</thead>
			<tbody>
				<!-- <tr class="itemProperty">
					<th class="tdHead" colspan="2">Level 구분</th>
					<td class="tdBody"  colspan="3"></td>
				</tr> -->
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"  colspan="3">Level ${article.depthLevel }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">Level 경로</th>
					<td class="tdBody"  colspan="3"></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">MFDS Code</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaCode" value="${article.fdaCode }" onkeypress="keypress()"/>
					<input type="button" value="중복검사" onclick="duplicate()"/>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">MFDS Source PT(Korean)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaSourcePtKor" value="${article.fdaSourcePtKor }" size="70" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA Source PT(English)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="name" value="${article.name }"  size="70"/></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">MFDS Source Definition(Korean)</th>
					<%-- <td class="tdBody"  colspan="3"><input type="text" name="fdaSourceDefinitionKor" value=${article.fdaSourceDefinitionKor } /></td> --%>
					<td class="tdBody"  colspan="3"><textarea name="fdaSourceDefinitionKor" />${article.fdaSourceDefinitionKor }</textarea></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">FDA Source Definition(English)</th>
					<%-- <td class="tdBody"  colspan="3"><input type="text" name="fdaSourceDefinition" value=${article.fdaSourceDefinition } /></td> --%>
					<td class="tdBody"  colspan="3"><textarea name="fdaSourceDefinition" />${article.fdaSourceDefinition }</textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCI Code</th>
					<td class="tdBody"  colspan="3"><input type="text" name="nciCode" value="${article.nciCode }" /></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">NCIt Definition (Korean)</th>
					<%-- <td class="tdBody"  colspan="3"><input type="text" name="ncitDefinitionKor" value=${article.ncitDefinitionKor } /></td> --%>
					<td class="tdBody"  colspan="3"><textarea name="ncitDefinitionKor"/>${article.ncitDefinitionKor }</textarea></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">NCIt Definition</th>
					<%-- <td class="tdBody"  colspan="3"><input type="text" name="ncitDefinition" value=${article.ncitDefinition } /></td> --%>
					<td class="tdBody"  colspan="3"><textarea name="ncitDefinition" />${article.ncitDefinition }</textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 사항</th>
					<!-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified" name="lastModified"/></td> -->
					<th class="tdHead" >작성일자</th>
					<td class="tdBody" ><input type="text" id="activeFrom" name="activeFrom"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3">
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${hType.propertyValue eq '개정'}">
												<option  value="${hType.id }" selected="selected">${hType.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option  value="${hType.id }">${hType.propertyValue }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >담당자</th>
					<td class="tdBody" colspan="3"><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3"><textarea name="historyDescription"></textarea></td>
				</tr>
				
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody"  colspan="3">
						<table class="his_table">
						<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
						<col width="40%" />
						<col width="10%" />
						</colgroup>
						<tr class="itemProprty">
							<!-- <th>변경일자</th> -->
							<th>작성일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.medicalDeviceMalfunctionHistory }">
								<c:forEach var="medicalDeviceMalfunctionHistory" items="${article.medicalDeviceMalfunctionHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${medicalDeviceMalfunctionHistory.lastModified }"/>
							</td> --%>
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${medicalDeviceMalfunctionHistory.activeFrom }"/>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${medicalDeviceMalfunctionHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td>
							<c:choose>
								<c:when test="${!empty article.medicalDeviceMalfunctionHistory }">
										${medicalDeviceMalfunctionHistory.manager }
								</c:when>
							</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${!empty medicalDeviceMalfunctionHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${medicalDeviceMalfunctionHistory.historyDescription }</textarea>
									</c:when>
								<c:otherwise>
									<textarea disabled="disabled" name="historyDescription"/>no record</textarea>
								</c:otherwise>
								</c:choose>
							</td>
							<td id="history${medicalDeviceMalfunctionHistory.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="이력삭제" onclick="deleteHistory(${medicalDeviceMalfunctionHistory.id});"/></td>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
				</tr>
				
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						<%-- <select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<option value="${useStat.id }">${useStat.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select> --%>
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<c:choose>
											<c:when test="${useStat.id eq article.isInUse }">
												<option selected="selected" value="${useStat.id }">${useStat.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${useStat.id }">${useStat.propertyValue }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="createMedicalDevice();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function createMedicalDevice(){
 			if(document.medicalDeviceCreateForm1.fdaCode.value != '${article.fdaCode }'){
	 	 		if(document.medicalDeviceCreateForm1.check.value==0){
	 	 			alert("MFDS Code 중복검사를 해주세요.");
	 	 			return;
 	 			}
 			}
 			$("form[name=medicalDeviceCreateForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 		
 			$("#lastModified").datepicker();
 			$("#activeFrom").datepicker();
 			$("#lastModified").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#activeFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 		}
 		
		function deleteHistory(historyId){
 			
 			var xhr = new XMLHttpRequest();
 			var method="POST";
 			var url="properties.do?action=deleteMedicalDeviceHistory&articleId="+historyId;
 			var isAsync=true;
 			
 			xhr.open(method, url, isAsync);
 			xhr.send();
 			
 			xhr.onreadystatechange = function(){
 				
 				if( xhr.readyState== 4  && xhr.status == 200 ){
 					var responseText = xhr.responseText;
 					if("1"==responseText || 1==responseText){
 						alert("삭제성공");
 	 					var id = "#history"+historyId;
 	 					$(id).remove();	
 					}else{
 						//alert("삭제실패");
 					}
 					
 				} 
 			};
 			
 		}
 	</script>
 	
 	<script type="text/javascript">
 function duplicate(){
		var frm = document.medicalDeviceCreateForm1;
		var fdaCode = frm.fdaCode.value;
		
		var xmlHttpRequest = new XMLHttpRequest();
		
		 var method = "POST";
		 var url = "properties.do?action=fdaCodeDuplicate&fdaCode="+fdaCode+"&table=MEDICAL_DEVICE_MALFUNCTION_CODE";
		 var async = true;
		 
		 xmlHttpRequest.open(method, url, async);
		 xmlHttpRequest.send();
		 xmlHttpRequest.onreadystatechange = function(){
					
					if ( xmlHttpRequest.status ==200 && xmlHttpRequest.readyState ==4){
						//alert(xmlHttpRequest.responseText);
						
						if(xmlHttpRequest.responseText > 0){
							frm.check.value=0;
							alert("이미 사용중인 코드 입니다.");
							return;
						}else{
							alert("사용가능한 코드 입니다.");
							frm.check.value=1;
							return;
						}
						
					} 
				};
		
	}
 
 
 function keypress(){
	 document.medicalDeviceCreateForm1.check.value=0;
 }
 </script>
 
 