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
 	<form name="componentCreateForm1" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="readcomponent"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
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
					<th colspan="5">공통 코드 관리 - 구성요소 코드</th>
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
					<td class="tdBody"  colspan="3">
						<c:choose>
							<c:when test="${article.depthLevel eq 1 }">
								${article.fdaSourcePtKor }
							</c:when>
							<c:when test="${article.depthLevel eq 2 }">
								${parent2.fdaSourcePtKor } > ${article.fdaSourcePtKor }
							</c:when>
							<c:when test="${article.depthLevel eq 3 }">
								${parent3.fdaSourcePtKor } > ${parent2.fdaSourcePtKor } > ${article.fdaSourcePtKor }
							</c:when>
							<c:when test="${article.depthLevel eq 4 }">
								${parent4.fdaSourcePtKor } > ${parent3.fdaSourcePtKor } > ${parent2.fdaSourcePtKor } > ${article.fdaSourcePtKor }
							</c:when>
							<c:when test="${article.depthLevel eq 5 }">
								${parent5.fdaSourcePtKor } > ${parent4.fdaSourcePtKor } > ${parent3.fdaSourcePtKor } > ${parent2.fdaSourcePtKor } > ${article.fdaSourcePtKor }
							</c:when>
							<c:when test="${article.depthLevel eq 6 }">
								${parent6.fdaSourcePtKor } > ${parent5.fdaSourcePtKor } > ${parent4.fdaSourcePtKor } > ${parent3.fdaSourcePtKor } > ${parent2.fdaSourcePtKor } > ${article.fdaSourcePtKor }
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">MFDS Code</th>
					<td class="tdBody"  colspan="3">${article.fdaCode }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">MFDS Source PT(Korean)</th>
					<td class="tdBody"  colspan="3">${article.fdaSourcePtKor }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA Source PT(English)</th>
					<td class="tdBody"  colspan="3">${article.name }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">MFDS Source Definition(Korean)</th>
					<td class="tdBody"  colspan="3">${article.fdaSourceDefinitionKor }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA Source Definition(English)</th>
					<td class="tdBody"  colspan="3">${article.fdaSourceDefinition }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCI Code</th>
					<td class="tdBody"  colspan="3">${article.nciCode }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCIt Definition (Korean)</th>
					<td class="tdBody"  colspan="3">${article.ncitDefinitionKor }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCIt Definition</th>
					<td class="tdBody"  colspan="3">${article.ncitDefinition }</td>
				</tr>
				
				<tr class="itemProperty" >
					<th class="tdHead" colspan="2">이력 사항</th>
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
						<tr class="itemProperty">
							<!-- <th>변경일자</th> -->
							<th>작성일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.componentHistory }">
								<c:forEach var="componentHistory" items="${article.componentHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.componentHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${componentHistory.lastModified }"/></li>
									</c:when>
								</c:choose>
								</ul>
							</td> --%>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.componentHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${componentHistory.activeFrom }"/></li>
									</c:when>
								</c:choose>
								</ul>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${componentHistory.historyType eq hType.id }">
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
									<c:when test="${!empty article.componentHistory }">
										<li>${componentHistory.manager }</li>
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
									<c:when test="${!empty componentHistory.historyDescription }">
										<%-- <textarea disabled="disabled" name="historyDescription"/>${componentHistory.historyDescription }</textarea> --%>
										<textarea readonly="readonly" name="historyDescription"/>${componentHistory.historyDescription }</textarea>
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
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						${article.useStatus.propertyValue }
					</td>
				</tr>
				
				<%-- <tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" colspan="3">
						<ul>
						<c:choose>
							<c:when test="${!empty article.componentHistory }">
								<c:forEach var="componentHistory" items="${article.componentHistory }">
									<li>${componentHistory.historyDescription }</li>
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
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateComponent(${article.id});"/>
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
  	function updateComponent(articleId){
  		
  		location.href="properties.do?action=updateComponentPage&menu=4&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("삭제하시겠습니까?")){
  			location.href="properties.do?action=deleteComponent&menu=4&articleId="+articleId;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="properties.do?action=listComponent&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 