<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%; overflow-y: scroll;">
 	<img src="${titleImg }" />
 	<form name="itemCreateForm1" method="post" action="properties.do">
 		<input type="hidden" readonly="readonly" name="action" value="updateItemPage"/>
 		<input type="hidden" readonly="readonly" name="articleId" value="${article.id }"/>
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
						${article.class_level }	
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">코드 경로</th>
					<td class="tdBody">
						${article.higher_class_no }->${parent.higher_class_no }					
					</td>
					<th class="tdHead">코드 구분</th>
					<td class="tdBody">
						<select disabled="disabled" name="itemCodeType">
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
					<td class="tdBody">${article.mea_class_no}</td>
					<th class="tdHead">등급</th>
					<td class="tdBody">
						<input type="hidden" readonly="readonly" value="${article.grade }" name="grade"/>
						<select disabled="disabled" name="itemGrade" >
							<c:choose>
								<c:when test="${!empty requestScope.itemGrades  }">
									<c:forEach var="itemGrade" items="${itemGrades }">
										<c:choose>
											<c:when test="${itemGrade.id eq 1 }">
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
					<td class="tdBody" colspan="3">${article.class_kor_name }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">품목영문명</th>
					<td class="tdBody" colspan="3">${article.class_eng_name}</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">품목 설명</th>
					<td class="tdBody" colspan="3">${article.class_cont }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">추적관리대상여부</th>
					<td class="tdBody" colspan="3">
						<select disabled="disabled" name="traceability" >
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
				<tr class="itemProperty hidden">
					<th class="tdHead" rowspan="4">이력 현황</th>
					<th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" readonly="readonly" id="lastModified" value="" readOnly /></td>
					<th class="tdHead" >적용일자</th>
					<td class="tdBody" ><input type="text" readonly="readonly" id="activeFrom" value="" readOnly /></td>
				</tr>
				<tr class="itemProperty  hidden">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3">
						<select disabled="disabled" name="historyType" >
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
				<tr class="itemProperty  hidden">
					<th class="tdHead" >담당자</th>
					<td class="tdBody" colspan="3"><input type="text" readonly="readonly" name="history_manager" readOnly /></td>
				</tr>
				<tr class="itemProperty hidden">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3"><textarea readonly="readonly" name="historyDescription" readOnly ></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						<select disabled="disabled" name="isInUse" >
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
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" onclick="updateThisArticle();"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" onclick="del('${article.compositeKey.mea_class_no}',${article.compositeKey.grade });"/>
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
  	
function del(mcn, grade){
  		if(confirm("Are you sure ?!")){
  			location.href="properties.do?action=deleteItem&mea_class_no="+mcn+"&grade="+grade	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="properties.do?action=listItem&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 