<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="itemCreateForm1" method="post" action="itemCategory.do">
 		<input type="text" readonly="readonly" name="action" value="updateItemCategoryPage"/>
 		<input type="text" readonly="readonly" name="articleId" value="${article.id }"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="20%" />
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
					<th class="tdHead" >Level </th>
					<td colspan="3">
						${article.class_level }	
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >코드 경로</th>
					<td class="tdBody">
						${article.mea_class_no }->${parent.mea_class_no }					
					</td>
					<th class="tdHead">코드 구분</th>
					<td class="tdBody">
						<c:choose>
						 	<c:when test="${!empty requestScope.itemCodeTypes  }">
						 		<c:forEach var="ict" items="${requestScope.itemCodeTypes }">
						 			<c:choose>
						 				<c:when test="${ict.id eq article.code_age.id }">
						 					${ict.propertyValue }
						 				</c:when>
						 				<c:otherwise>
						 				</c:otherwise>
						 			</c:choose>
						 		</c:forEach>
						 	</c:when>
						 	<c:otherwise>
						 		no data
						 	</c:otherwise>
						 </c:choose>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >품목코드</th>
					<td class="tdBody">${article.mea_class_no}</td>
					<th class="tdHead">등급</th>
					<td class="tdBody">
						<input type="hidden" readonly="readonly" value="${article.grade }" name="grade"/>
						<input type="hidden" readonly="readonly" value="${article.mea_class_no }" name="mea_class_no"/>
							<c:choose>
								<c:when test="${!empty requestScope.itemGrades  }">
									<c:forEach var="itemGrade" items="${itemGrades }">
										<c:choose>
											<c:when test="${itemGrade.id eq 1 }">
												${itemGrade.propertyValue }
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									no data
								</c:otherwise>
							</c:choose>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >품목한글명</th>
					<td class="tdBody" colspan="3">${article.class_kor_name }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >품목영문명</th>
					<td class="tdBody" colspan="3">${article.class_eng_name}</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >품목 설명</th>
					<td class="tdBody" colspan="3">${article.class_cont }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >추적관리대상여부</th>
					<td class="tdBody" colspan="3">
							<c:choose>
								<c:when test="${!empty requestScope.traceabilityList  }">
									<c:forEach var="traceability" items="${traceabilityList }">
										<c:choose>
											<c:when test="${article.traceability.id eq traceability.id }">
												${traceability.propertyValue }
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									no data 
								</c:otherwise>
							</c:choose>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >UDI CODE</th>
					<td class="tdBody" colspan="3">${article.udi_code }</td>
				<tr class="itemProperty" >
					<th class="tdHead" >이력 사항</th>
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
							<th>변경일자</th>
							<th>적용일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.history }">
								<c:forEach var="history" items="${article.history }">
						<tr class="itemProperty">
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.history }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${history.lastModified }"/></li>
									</c:when>
								</c:choose>
								</ul>
							</td>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.history }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${history.activeFrom }"/></li>
									</c:when>
								</c:choose>
								</ul>
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
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
							</td>
							<td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.history }">
										<li>${history.manager }</li>
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
									<c:when test="${!empty history.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${history.historyDescription }</textarea>
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
					<td class="tdBody" colspan="3">
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<c:choose>
											<c:when test="${article.isInUse.id eq useStat.id }">
												${useStat.propertyValue }
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									no data>
								</c:otherwise>
							</c:choose>
					</td>
				</tr>
				<%-- <tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" colspan="3">
						<table>
							<c:choose>
								<c:when test="${!empty article.history }">
									<c:forEach var="h" items="${article.history }">
										<tr>
											<td>${h.manager }</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</table>
					</td>
				</tr> --%>
			</tbody>
 	</table>
	 	</form>
	 	<div style="float: right;">
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" onclick="updateThisArticle();"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" onclick="del(${article.id});"/>
		 	<img src="view/style/images/bt_cancel.jpg" name="cancel" alt="cancel" onclick="toList(1,1,1);"/>
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
 			
 		}
 	</script>
 	<script type="text/javascript">
  	function updateThisArticle(){
  		$("form[name=itemCreateForm1]").submit();
  	}
  	
function del(id){
  		if(confirm("Are you sure ?!")){
  			location.href="itemCategory.do?action=deleteItemCategory&articleId="+id;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="itemCategory.do?action=listItemCategory&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 