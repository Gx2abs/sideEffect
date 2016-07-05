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
 	<form name="reportTypeCreateForm1" method="POST" action="properties.do">
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="20%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">공통 코드 관리 - 보고종류 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<%-- <th class="tdHead" colspan="2">보고종류 코드</th>
					<td class="tdBody"><input type="text" name="id" value="${article.id }" readOnly /></td> --%>
					<th class="tdHead" >보고종류명</th>
					<td class="tdBody" >${article.propertyValue }</td>
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
							<c:when test="${!empty article.reportTypeHistory }">
								<c:forEach var="reportTypeHistory" items="${article.reportTypeHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.reportTypeHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${reportTypeHistory.lastModified }"/></li>
									</c:when>
								</c:choose>
								</ul>
							</td> --%>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.reportTypeHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${reportTypeHistory.activeFrom }"/></li>
									</c:when>
								</c:choose>
								</ul>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${reportTypeHistory.historyType eq hType.id }">
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
									<c:when test="${!empty article.reportTypeHistory }">
										<li>${reportTypeHistory.manager }</li>
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
									<c:when test="${!empty reportTypeHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${reportTypeHistory.historyDescription }</textarea>
									<%-- ${reportTypeHistory.historyDescription } --%>
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
							<td class="tdBody">
								${article.useStatus.propertyValue }
							</td>
						</tr>	
					
					<%-- <input type="text" value="${article.lastModified }" readOnly/></td>
					<th class="tdHead" >적용일자</th>
					<td class="tdBody" ><input type="text" value="${article.activeFrom }" readOnly /></td>
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
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >담당자</th>
					<td class="tdBody" colspan="3">
					<ul>
						<c:choose>
							<c:when test="${!empty article.reportTypeHistory }">
								<c:forEach var="reportTypeHistory" items="${article.reportTypeHistory }">
									<li>${reportTypeHistory.manager }</li>
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
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3">
					<ul>
						<c:choose>
							<c:when test="${!empty article.reportTypeHistory }">
								<c:forEach var="reportTypeHistory" items="${article.reportTypeHistory }">
									<li>${reportTypeHistory.historyDescription }</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li>
									No record
								</li>
							</c:otherwise>
						</c:choose>
						</ul>				
					<!-- <textarea name="historyDescription" readOnly ></textarea></td> -->
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<option value="${useStat.id }">${useStat.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr> --%>
				<%-- <tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" colspan="3">
						<ul>
						<c:choose>
							<c:when test="${!empty article.countryHistory }">
								<c:forEach var="countryHistory" items="${article.countryHistory }">
									<li>${countryHistory.historyDescription }</li>
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
				</tr> --%>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<%if(privilegeId>1){ %>
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateReportType(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
		 	<%} %>
		 	<img src="view/style/images/bt_list.jpg" name="list" alt="list" class="buttonised" onclick="toList(1,1,1);"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function create(){
 			$("form[name=reportTypeCreateForm1]").submit();			
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
  	function updateReportType(articleId){
  		
  		location.href="properties.do?action=updateReportTypePage&menu=8&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("삭제하시겠습니까?")){
  			location.href="properties.do?action=deleteReportType&menu=8&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="properties.do?action=listReportType&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 