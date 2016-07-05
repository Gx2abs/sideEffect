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
 	<form name="codeMatchingCreateForm1" method="POST" action="properties.do">
 		<input type="hidden" name="action" value="readCodeMatching"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="20%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">공통 코드 관리 - 신·구 품목 매칭 관리</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" >기준코드</th>
					<td class="tdBody" >${article.masterCode }</td>
				</tr>
				<tr>
					<th class="tdHead" >품목명</th>
					<td class="tdBody" >${article.masterName }</td>
				</tr>
				<tr>
					<th class="tdHead" >등급</th>
					<td class="tdBody" >${article.categoryGrade.propertyValue }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >매칭 코드(신코드)</th>
					<td class="tdBody" >
						<table>
							<tr>
								<th>품목코드</th>
								<th>품목명</th>
								<th>등급</th>
								</tr>
										
						<c:choose>
							<c:when test="${!empty article.matchSlaves }">
								<c:forEach var="matchSlave" items="${article.matchSlaves }" varStatus="i">
										<tr>
											<td>${matchSlave.slaveCode }</td>
											<td>${matchSlave.slaveObject.class_kor_name }</td>
											<td>${matchSlave.slaveGrade }</td>
										</tr>		
								</c:forEach>
							</c:when>
						</c:choose>
						</table>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">이력 사항</th>
					<td class="tdHead" >
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
							<c:when test="${!empty article.matchHistory }">
								<c:forEach var="matchHistory" items="${article.matchHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.matchHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${matchHistory.lastModified }"/></li>
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
									<c:when test="${!empty article.matchHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${matchHistory.activeFrom}"/></li>
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
											<c:when test="${matchHistory.historyType eq hType.id }">
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
									<c:when test="${!empty article.matchHistory }">
										<li>${matchHistory.manager }</li>
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
									<c:when test="${!empty matchHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${matchHistory.historyDescription }</textarea>
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
				<tr class="itemProperty">
					<th class="tdHead" >사용여부</th>
					<td class="tdBody" >
						${article.useStatus.propertyValue }
					</td>
				</tr>
				<%-- <tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" colspan="3">
						<ul>
						<c:choose>
							<c:when test="${!empty article.matchHistory }">
								<c:forEach var="matchHistory" items="${article.matchHistory }">
									<li>${matchHistory.historyDescription }</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li>
									No record
								</li>
							</c:otherwise>
						</c:choose>
						</ul>
					</td>
				</tr>  --%>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<%if(privilegeId>1){ %>
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateMatchingCode(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
		 	<%} %>
		 	<img src="view/style/images/bt_list.jpg" name="list" alt="list" class="buttonised" onclick="toList(1,1,1);"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function create(){
 			$("form[name=codeMatchingCreateForm1]").submit();			
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
  	function updateMatchingCode(articleId){
  		
  		location.href="properties.do?action=updateMatchingItemPage&menu=9&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("삭제하시겠습니까?")){
  			location.href="properties.do?action=deleteMatch&menu=9&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="properties.do?action=listMatchingItems&menu=9&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 