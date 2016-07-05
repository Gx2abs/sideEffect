<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="itemCreateForm1" method="post" action="properties.do">
 		<input type="text" name="action" value="updateItem"/>
 		<input type="text" name="articleId" value="${article.id }"/>
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
					<th colspan="5">공통 코드 관리 - 품목코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">Level </th>
					<td colspan="3">
						</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"></td>
					<th class="tdHead">코드 구분</th>
					<td class="tdBody">
						<select name="itemCodeType">
							 <c:choose>
							 	<c:when test="${!empty requestScope.itemCodeTypes  }">
							 		<c:forEach var="ict" items="${requestScope.itemCodeTypes }">
							 			<c:choose>
							 				<c:when test="${ict.id eq article.code_age.id }">
							 					<option  value="${ict.id }" selected="selected">${ict.propertyValue }</option>
							 				</c:when>
							 				<c:otherwise>
							 					<option  value="${ict.id }">${ict.propertyValue }</option>
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
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">품목코드</th>
					<td class="tdBody"><input type="text" name="mea_class_no" value="${article.mea_class_no }" readonly="readonly"/></td>
					<th class="tdHead">등급</th>
					<td class="tdBody">
						<input type="hidden" name="grade" value="${article.grade }"/>
						<select name="gradeDisp"   disabled="disabled">
							<c:choose>
								<c:when test="${!empty requestScope.itemGrades  }">
									<c:forEach var="itemGrade" items="${itemGrades }">
										<c:choose>
											<c:when test="${itemGrade.id eq article.grade }">
												<option value="${itemGrade.id }" selected="selected">${itemGrade.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${itemGrade.id }">${itemGrade.propertyValue }</option>
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
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">품목한글명</th>
					<td class="tdBody" colspan="3"><input type="text" name="class_kor_name" value="${article.class_kor_name }" size="60%" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">품목영문명</th>
					<td class="tdBody" colspan="3"><input type="text" name="class_eng_name" value="${article.class_eng_name}" size="60%"/></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">품목 설명</th>
					<td class="tdBody" colspan="3"><textarea name="class_cont">${article.class_cont }</textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">추적관리대상여부</th>
					<td class="tdBody" colspan="3">
						<select name="traceability" >
							<c:choose>
								<c:when test="${!empty requestScope.traceabilityList  }">
									<c:forEach var="traceability" items="${traceabilityList }">
										<c:choose>
											<c:when test="${article.traceability.id eq traceability.id }">
												<option selected="selected" value="${traceability.id }">${traceability.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${traceability.id }">${traceability.propertyValue }</option>
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
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">UDI CODE</th>
					<td class="tdBody" colspan="3"><input type="text" name="udiCode" value="${article.udi_code }"></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 현황</th>
					<!-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified" name="lastModified" /></td> -->
					<th class="tdHead" >적용일자</th>
					<td class="tdBody" colspan="3"><input type="text" id="activeFrom" name="activeFrom" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3">
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<option value="${hType.id }">${hType.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >담당자</th>
					<td class="tdBody" colspan="3"><input type="hidden" name="historyManager" value="${sessionScope.user.accountName }" />${sessionScope.user.accountName }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3"><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<c:choose>
											<c:when test="${article.isInUse.id eq useStat.id }">
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
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" colspan="3">
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
							<th>적용일자</th>
							<th>변경일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.history }">
								<c:forEach var="history" items="${article.history }">
						<tr class="itemProperty">
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${history.activeFrom }"/>
							</td>
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${history.lastModified }"/>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${history.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td>
							<c:choose>
								<c:when test="${!empty article.history }">
										${history.manager }
								</c:when>
							</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${!empty history.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${history.historyDescription }</textarea>
									</c:when>
								<c:otherwise>
									<textarea disabled="disabled" name="historyDescription"/>no record</textarea>
								</c:otherwise>
								</c:choose>
							</td>
							<td id="history${history.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="이력삭제" onclick="deleteHistory(${history.id});"/></td>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function create(){
 			$("form[name=itemCreateForm1]").submit();			
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
 			var url="properties.do?action=deleteItemHistory&articleId="+historyId;
 			var isAsync=true;
 			
 			xhr.open(method, url, isAsync);
 			xhr.send();
 			
 			xhr.onreadystatechange = function(){
 				
 				if( xhr.readyState== 4  && xhr.status == 200 ){
 					var responseText = xhr.responseText.trim();
 					if("1"==responseText){
 						alert("삭제성공");
 	 					var id = "#history"+historyId;
 	 					$(id).remove();	
 					}else{
 						alert("삭제실패");
 					}
 					
 				} 
 			};
 			
 		}
 	</script>
 