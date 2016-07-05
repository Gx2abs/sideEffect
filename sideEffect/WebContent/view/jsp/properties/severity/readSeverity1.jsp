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
 	<form name="severityCreateForm1" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="readSeverity"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="20%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="5">공통 코드 관리 - 위해심각도 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead">Level</th>
					<td class="tdBody">${article.depthLevel }</td>
				</tr>
				<c:if test="${article.depthLevel eq 2 }">
					<tr class="itemProperty">
					<th class="tdHead">상위코드</th>
					<td class="tdBody">${parent.propertyValue }</td>
				</tr>
				</c:if>
				<tr class="itemProperty">
					<th class="tdHead">점수</th>
					<td class="tdBody">${article.value }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">분류</th>
					<td class="tdBody">${article.propertyValue }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">위해심각도(Severity) 설명</th>
					<td class="tdBody">${article.severityName }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">적용시작일</th>
					<td class="tdBody"><fmt:formatDate pattern="yyyy-MM-dd" value="${article.startDate }"/></td>
				</tr>
				<tr class="itemProperty" >
					<th class="tdHead">이력 사항</th>
					<td class="tdBody" >
						<table>
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
							<c:when test="${!empty article.severityHistory }">
								<c:forEach var="severityHistory" items="${article.severityHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.severityHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${severityHistory.lastModified }"/></li>
									</c:when>
									<c:otherwise>
									<li>
									No record
									</li>
									</c:otherwise>
								</c:choose>
								</ul>
							</td> --%>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.severityHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${severityHistory.activeFrom }"/></li>
									</c:when>
									<c:otherwise>
									<li>
									No record
									</li>
									</c:otherwise>
								</c:choose>
								</ul>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${severityHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.severityHistory }">
										<li>${severityHistory.manager }</li>
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
									<c:when test="${!empty severityHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${severityHistory.historyDescription }</textarea>
									</c:when>
								</c:choose>
							</td>
						</tr>
								</c:forEach>
							</c:when>
						</c:choose>
						</table>
					</td>
				<tr class="itemProperty">
					<th class="tdHead">사용여부</th>
					<td class="tdBody">
						${article.useStatus.propertyValue }
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<%if(privilegeId>1){ %>
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateSeverity(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
		 	<%} %>
		 	<img src="view/style/images/bt_list.jpg" name="list" alt="list" class="buttonised" onclick="toList(1,1,1);"/>
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
 		
 			
 		}
 	</script>
 <script type="text/javascript">
  	function updateSeverity(articleId){
  		
  		location.href="properties.do?action=updateSeverityPage&menu=10&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("삭제하시겠습니까?")){
  			location.href="properties.do?action=deleteSeverity&menu=10&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="properties.do?action=listSeverity&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 