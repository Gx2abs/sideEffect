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
 	<div style="width: 98%; height: 99%; padding: 40px 10px 0px 10px; overflow: auto;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="itemCategoryGroupBackCreateForm1" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="readItemCategoryGroupBack"/>
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
					<th class="tdHead">시작일자</th>
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
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3" align="right" style="margin: 30px 0px 60px 0px;">
	 		<%if(privilegeId>1){ %>
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateItemCategoryGroupBack(${backup.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${backup.id});"/>
		 	<%} %>
	 	</div>
 	</div>
 	
 <script type="text/javascript">
 	$(document).ready(function(){	
		initPage();	
	});
	
	function initPage(){
		$(".selectColor").spectrum({
		    disabled: true
		});
	}
		
	function updateItemCategoryGroupBack(articleId){
		
		location.href="risk.category.do?action=updateItemCategoryGroupBackPage&menu=11&articleId="+articleId;
		
	}
	
	function del(articleId){
		if(confirm("삭제하시겠습니까?")){
			location.href="risk.category.do?action=deleteItemCategoryGroupBack&menu=11&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;	
		}
		
	}

 </script>
 
 