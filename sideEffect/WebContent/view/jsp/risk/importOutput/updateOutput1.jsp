<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/jquery.mtz.monthpicker.js"></script>
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
 	<form name="importOutputCreateForm1" method="POST" action="importoutput.do">
 	<input type="hidden" name="action" value="updateOutput"/>
 	<input type="hidden" name="articleId" value="${article.id }"/>
 	<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">공통 코드 관리 - 생산·수입 실적 데이터</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >품목명</th>
					<td class="tdBody" >${article.item.mea_item.class_kor_name }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >업체명</th>
					<td class="tdBody" >${article.item.company.entp_name }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >품목허가번호</th>
					<td class="tdBody" >
						<c:if test="${article.item.cobFlagType.propertyValue ne '없음' or !empty article.item.cobFlagType.propertyValue}">
							${article.item.cobFlagType.propertyValue} ${article.item.meddev_item_no }
						</c:if>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" id="importOutput">생산량</th>
					<td class="tdBody" ><input type="text" name="itemOutput" value="<fmt:parseNumber value='${article.itemOutput }' />" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >형명</th>
					<td class="tdBody" ><input type="text" name="typeName" value="${article.typeName }" style="width: 80%;"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">날짜</th>
					<td class="tdBody" ><input type="text" name="outputDate" id="outputDate" value="${article.outputYear }-${article.outputMonth}" /></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 사항</th>
					<th class="tdHead" >작성일자</th>
					<td class="tdBody" ><input type="text" id="activeFrom" name="activeFrom"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" >
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<option value="${hType.id }">${hType.propertyValue }</option>
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
					<td class="tdBody" ><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody" ><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody">
						<table>
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
							<c:when test="${!empty article.itemOutputHistory }">
								<c:forEach var="itemOutputHistory" items="${article.itemOutputHistory }">
						<tr class="itemProperty">
							<%-- <td><fmt:formatDate pattern="yyyy-MM-dd" value="${itemOutputHistory.lastModified }"/></td> --%>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${itemOutputHistory.activeFrom }"/></td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${itemOutputHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td><c:choose>
								<c:when test="${!empty article.itemOutputHistory }">
										${itemOutputHistory.manager }
								</c:when>
							</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${!empty itemOutputHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${itemOutputHistory.historyDescription }</textarea>
									</c:when>
								</c:choose>
							</td>
							<td id="history${itemOutputHistory.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="이력삭제" onclick="deleteHistory('${itemOutputHistory.id}','<%=privilegeId %>');"/></td>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 	 		if(validation()){
 	 			$("form[name=importOutputCreateForm1]").submit();
 	 	 	}			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			$("input[name=importoutput]").bind("click", function(){
 				$("#importOutput").text($(this).val());
 			});
 			
 		});
 		
 		function initPage(){
 			$("#activeFrom").datepicker();
 			$("#activeFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#outputDate").monthpicker();
 		}

 		function deleteHistory(articleId,privilegeId){
 			if(privilegeId>1){
 			var method = "get";
 			var url = "importoutput.do?action=deleteOutputHistory&articleId="+articleId+"&privilegeId="+privilegeId;
 			var xhr = new XMLHttpRequest();
 			xhr.open(method,url, true);
 			xhr.send();
 			
 			xhr.onreadystatechange = function(){
 				
 				if(xhr.readyState==4 && xhr.status==200){
 					//alert(xhr.responseText );
 					if( xhr.responseText =="1" ||  xhr.responseText ==1){
 						alert("삭제성공");
 						
 						$("#history"+articleId).remove();
 					}else alert("권한이 없습니다.");
 				}
 				
 			};
 			}
 		}

 		function validation(){
 	 	 	var validation = false;
 	 	 	
 	 	 	if($("#outputDate").val() == ""){
 	 	 	 	alert("날짜를 입력해주세요.");
 	 	 	}else{
				validation = true;
 	 	 	}

 	 	 	return validation;
 	 	 }
 	</script>
 