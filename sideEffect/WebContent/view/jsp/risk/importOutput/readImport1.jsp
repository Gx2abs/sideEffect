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
 	<form name="importOutputCreateForm1" method="POST" action="importoutput.do">
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="20%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">공통 코드 관리 - 생산·수입 실적 데이터</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" >품목명</th>
					<td class="tdBody" >${article.item.mea_item.class_kor_name }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >업체명</th>
					<td class="tdBody" >${article.item.company.entp_name }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >품목허가번호</th>
					<td class="tdBody" >
						<c:if test="${article.item.cobFlagType.propertyValue ne '없음' or !empty article.item.cobFlagType.propertyValue}">
							${article.item.cobFlagType.propertyValue} ${article.item.meddev_item_no }호
						</c:if>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >수입량</th>
					<td class="tdBody" ><fmt:parseNumber value="${article.itemImport }" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >형명</th>
					<td class="tdBody" >${article.typeName }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >날짜</th>
					<td class="tdBody" >
						<c:if test="${!empty article.importYear && !empty article.importMonth }">
							${article.importYear }-${article.importMonth }
						</c:if>
					</td>
				</tr>
				<tr class="itemProperty" >
					<th class="tdHead" >이력 사항</th>
					<td class="tdBody" >
						<table class="his_table">
						<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
						<col width="40%" />
						<col width="10%" />
						</colgroup>
						<tr class="itemProperty">
							<!-- <th>변경일자</th> -->
							<th>작성일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.itemImportHistory }">
								<c:forEach var="itemImportHistory" items="${article.itemImportHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.itemImportHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${itemImportHistory.lastModified }"/></li>
									</c:when>
								</c:choose>
								</ul>
							</td> --%>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.itemImportHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${itemImportHistory.activeFrom }"/></li>
									</c:when>
								</c:choose>
								</ul>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${itemImportHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
							</td>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.itemImportHistory }">
										<li>${itemImportHistory.manager }</li>
									</c:when>
									<c:otherwise>
									<li>
									No record
									</li>
									</c:otherwise>
								</c:choose>
								</ul>
							</td>
							<td colspan="2">
								<c:choose>
									<c:when test="${!empty itemImportHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${itemImportHistory.historyDescription }</textarea>
									</c:when>
								</c:choose>
							</td>
						</tr>
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
	 		<%if(privilegeId>1){ %>
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateImportOutput(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
		 	<%} %>
		 	<img src="view/style/images/bt_list.jpg" name="list" alt="list" class="buttonised" onclick="toList(1,1,1);"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function create(){
 			$("form[name=importOutputCreateForm1]").submit();			
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
 			
 		}
 	</script>
 <script type="text/javascript">
  	function updateImportOutput(articleId){
  		
  		location.href="importoutput.do?action=updateImportPage&menu=12&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("삭제하시겠습니까?")){
  			location.href="importoutput.do?action=deleteImport&menu=12&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="importoutput.do?action=listImport&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 