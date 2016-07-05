<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/spectrum/spectrum.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 <link rel="stylesheet" type="text/css" href="view/style/css/spectrum.css">
 	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="itemCategoryGroupCreateForm1" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="readItemCategoryGroup"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="4">공통 코드 관리 - 발생가능성 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead">구분</th>
					<td class="tdBody" >${article.typeStatus.propertyValue }</td>	
					<th class="tdHead">그룹명</th>
					<td class="tdBody" >${article.propertyValue }</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">개정버전 명칭</th>
					<td class="tdBody" >${backup.revisionName }</td>	
					<th class="tdHead">시작일</th>
					<td class="tdBody" ><fmt:formatDate pattern="yyyy-MM-dd" value="${backup.startDate }"/></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">품목별<br>&nbsp;발생가능성 정보</th>
					<td class="tdBody" colspan="3" >
						<table>
							<colgroup>
								<col width="10%" />
								<col width="5%" />
								<col width="25%" />
								<col width="25%" />
								<col width="35%" />
							</colgroup>
							<tr>
								<th class="tdHead" rowspan="60">분류</th>
								<th class="tdHead">점수</th>
								<th class="tdHead">발생가능성 분류</th>
								<th class="tdHead">발생가능성(Likelihood)</th>
								<td align="right"></td>
							</tr>
								<c:choose>
									<c:when test="${!empty backup.possibility }">
										<c:forEach var="possibility" items="${backup.possibility }">
										<tr>
											<td class="tdBody">${possibility.value }</td>
											<td class="tdBody">${possibility.className }</td>
											<td class="tdBody" colspan="2">
												${possibility.likelihoodFm } 이상 ${possibility.likelihoodTo } 미만 %
											</td>
										</tr>
										</c:forEach>
									</c:when>
								</c:choose>
						</table>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">품목별<br>&nbsp;위험도 점수범위<br>&nbsp;/수준</th>
					<td class="tdBody" colspan="3" >
						<table>
							<colgroup>
								<col width="10%" />
								<col width="5%" />
								<col width="15%" />
								<col width="18%" />
								<col width="35%" />
								<col width="7%" />
								<col width="10%" />
							</colgroup>
							<tr>
								<th rowspan="60">위험성<br>&nbsp;계산 결과</th>
								<th class="tdHead">순서</th>
								<th class="tdHead">위험도 점수</th>
								<th class="tdHead">위험도 수준</th>
								<th class="tdHead">조치사항</th>
								<th class="tdHead">색상</th>
								<td class="tdHead"></td>
							</tr>
								<c:choose>
									<c:when test="${!empty backup.risk }">
										<c:forEach var="risk" items="${backup.risk }">
										<tr>
											<td class="tdBody">${risk.value }</td>
											<td class="tdBody">${risk.riskGradeFm } 이상 ${risk.riskGradeTo } 이하</td>
											<td class="tdBody">${risk.className }</td>
											<td class="tdBody"><textarea disabled="disabled" >${risk.correctiveMeasure }</textarea></td>
											<td class="tdBody"><input type="text" class="selectColor" value="${risk.color }" /></td>
											<td class="tdBody"></td>
										</tr>
										</c:forEach>
									</c:when>
								</c:choose>
						</table>
					</td>	
				</tr>
				<c:if test="${article.type ne 1 }">
					<tr class="itemProperty">
						<th class="tdHead">그룹</th>
						<td class="tdBody" colspan="3" >
							<c:choose>
								<c:when test="${!empty article.itemCategoryGroup }">
									<table>
										<colgroup>
											<col width="10%" />
											<col width="20%" />
											<col width="10%" />
											<col width="20%" />
											<col width="10%" />
											<col width="20%" />
										</colgroup>
									<c:forEach var="itemCategory" items="${article.itemCategoryGroup }">
										<tr>
											<th class="tdHead">품목코드</th>
											<td>${itemCategory.mea_class_no }</td>
											<th class="tdHead">등급</th>
											<td>${itemCategory.categoryGrade.propertyValue }</td>
											<th class="tdHead">품목명</th>
											<td>${itemCategory.class_kor_name }</td>
										</tr>
									</c:forEach>
									</table> 
								</c:when>
								<c:otherwise>
									등록된 품목이 없습니다.
								</c:otherwise>
							</c:choose>
						</td>	
					</tr>
				</c:if>
				<tr class="itemProperty" >
					<th class="tdHead">이력 사항</th>
					<td class="tdBody" colspan="3">
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
							<c:when test="${!empty article.itemCategoryGroupHistory }">
								<c:forEach var="itemCategoryGroupHistory" items="${article.itemCategoryGroupHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.itemCategoryGroupHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${itemCategoryGroupHistory.lastModified }"/></li>
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
									<c:when test="${!empty article.itemCategoryGroupHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${itemCategoryGroupHistory.activeFrom }"/></li>
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
											<c:when test="${itemCategoryGroupHistory.historyType eq hType.id }">
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
									<c:when test="${!empty article.itemCategoryGroupHistory }">
										<li>${itemCategoryGroupHistory.manager }</li>
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
									<c:when test="${!empty itemCategoryGroupHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${itemCategoryGroupHistory.historyDescription }</textarea>
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
					<td class="tdBody" colspan="3">
						${article.useStatus.propertyValue }
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<%if(privilegeId>1){ %>
	 		<c:choose>
	 			<c:when test="${article.type ne 1 }">
	 				<img src="view/style/images/btn_insertCategory.gif" alt="품목추가" class="buttonised" onclick="insertCategory(${article.id})" />
	 			</c:when>
	 		</c:choose>
	 		<img src="view/style/images/btn_revision.gif" alt="개정" class="buttonised" onclick="revision(${article.id})" />
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateItemCategoryGroup(${article.id});"/>
		 	<c:if test="${article.type ne 1 }">
		 		<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
		 	</c:if>
		 	<%} %>
		 	<img src="view/style/images/bt_list.jpg" name="list" alt="list" class="buttonised" onclick="toList(1,1,1);"/>
	 	</div>
 	<!-- </div> -->
 	<br>
 	
 		<table class="bordered fitToParent view_table">
 			<colgroup>
				<col width="30%" />
				<col width="10%" />
				<col width="60%" />
			</colgroup>
 			<c:choose>
				<c:when test="${!empty requestScope.backupList }">
					<thead>
						<tr>
							<th colspan="3">개정 이력</th>
						</tr>
					</thead>
					<tr class="itemProperty">
		 					<th class="tdHead">개정버전 명칭</th>
		 					<th class="tdHead" colspan="2">시작일자</th>
		 			</tr>
					<c:forEach var="backupList" items="${backupList }">
						<c:if test="${backupList.id ne backup.id }">
							<tr class="itemProperty">
					 			<td class="tdBody">${backupList.revisionName }</td>
					 			<td class="tdBody">
					 				<fmt:formatDate pattern="yyyy-MM-dd" value="${backupList.startDate }"/>
					 			</td>
					 			<td class="tdBody">
					 				<img src="view/style/images/bt_detailView.gif" alt="상세보기" class="buttonised" onclick="readBackup(${backupList.id})"/>
					 			</td>
				 			</tr>
						</c:if>
					</c:forEach>
				</c:when>
			</c:choose>
 		</table>
 	</div>

 <script type="text/javascript">
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
		$(".selectColor").spectrum({
		    disabled: true
		});
	}
	
	function updateItemCategoryGroup(articleId){
		
		location.href="risk.category.do?action=updateItemCategoryGroupPage&menu=11&articleId="+articleId;
		
	}
	
	function del(articleId){
		if(confirm("삭제하시겠습니까?")){
			location.href="risk.category.do?action=deleteItemCategoryGroup&menu=11&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;	
		}
		
	}
	
	function toList(page, searchKeyword, searchColumn){
		location.href="risk.category.do?action=listItemCategoryGroup&page=&searchColumn=&searchKeyword=";
	}
	
	function insertCategory(articleId){
		var url ="risk.category.do?action=ItemCategoryGroupPopup&articleId="+articleId;
			var properties ="width=800,height=560";
			window.open(url, '', properties, 'scrollbars=yes'); 	  	
	};

	function revision(articleId){
		location.href = "risk.category.do?action=revisionItemCategoryGroupPop&menu=11&articleId="+articleId;		
	} 

	function readBackup(id){
		var url ="risk.category.do?action=readItemCategoryGroupBack&menu=11&articleId="+id;
		var properties ="width=1100,height=670";
		window.open(url, '', properties, 'scrollbars=yes'); 	
	}
	
 </script>
 
 