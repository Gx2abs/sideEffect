<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 	<form name="productCreateForm1" method="post" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="readItemPage"/>
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
					<th colspan="5">공통 코드 관리 - 제품 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="5" >업체정보</th>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >업체명</th>
					<td class="tdBody" >
						${article.company.entp_name }
					</td>
					<th class="tdHead" >업허가번호</th>
					<td class="tdBody" >
						<c:choose>
							<c:when test="${!empty article.company.meddev_entp_no }">${article.company.meddev_entp_no }호</c:when>
						</c:choose> 					
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >업구분</th>
					<td class="tdBody" >
						<c:choose>
							<c:when test="${article.company.cob_flag_code eq '1'}">제조업</c:when>
							<c:when test="${article.company.cob_flag_code eq '2'}">수입업</c:when>
						</c:choose>	
					</td>
					<th class="tdHead" >상태</th>
					<td class="tdBody" >
						<c:choose>
					 		<c:when test="${article.company.shutdown_close_reopen_code eq '1'}">정상</c:when>
					 		<c:when test="${article.company.shutdown_close_reopen_code eq '2'}">양도</c:when>
					 		<c:when test="${article.company.shutdown_close_reopen_code eq '3'}">취하</c:when>
					 		<c:when test="${article.company.shutdown_close_reopen_code eq '4'}">휴업</c:when>
					 		<c:when test="${article.company.shutdown_close_reopen_code eq '5'}">폐업</c:when>
					 		<c:when test="${article.company.shutdown_close_reopen_code eq '6'}">취소</c:when>
					 		<c:otherwise>없음</c:otherwise>
				 		</c:choose></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >업허가일자</th> 
					<td class="tdBody" colspan="3" id="permit_date"><fmt:formatDate pattern="yyyy-MM-dd" value="${article.company.permit_date}"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >주소</th>
					<td class="tdBody" colspan="3" >
					${article.company.entp_zip_no }<br>
					${article.company.entp_addr1 } ${article.company.entp_addr2 }
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="5" >제품정보</th>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >품목분류번호</th>
					<td class="tdBody" >${article.mea_item.mea_class_no }</td>
					<th class="tdHead" >구분</th>
					<td class="tdBody" >${article.mea_item.code_age.propertyValue}</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >품목명</th>
					<td class="tdBody" >${article.mea_item.class_kor_name }</td>
					<th class="tdHead" >등급</th>
					<td class="tdBody" >${article.mea_item.grade }</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >품목허가번호</th>
					<td class="tdBody" colspan="3" >
					<%-- <c:choose>
					 		<c:when test="${article.cob_flag_code eq '1'}">수허</c:when>
					 		<c:when test="${article.cob_flag_code eq '2'}">제허</c:when>
					 		<c:otherwise>없음</c:otherwise>
				 		</c:choose> --%>
				 		<c:choose>
							<c:when test="${article.cobFlagType.propertyValue eq '없음' or empty article.cobFlagType.propertyValue}"></c:when>
							<c:otherwise>${article.cobFlagType.propertyValue }</c:otherwise>
						</c:choose>
				 		${article.meddev_item_no }호</td>
				</tr>		
 				<tr class="itemProperty">
					<th class="tdHead" colspan="2">형명</th>
					<td class="tdBody" colspan="3">
						<ul>
						<c:choose>
							<c:when test="${!empty article.item_type }">
								<c:forEach var="item_type" items="${article.item_type }">
										<li>${item_type.type_name}</li>
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
					<th class="tdHead" colspan="2" >제조원(수입의 경우)</th>
					<td class="tdBody" colspan="3" >${article.manuf_import_name }</td>
				</tr>
				 <%-- <tr class="itemProperty">
					<th class="tdHead" colspan="2">제조번호(Lot번호)</th>
					<td class="tdBody" colspan="3">
						<ul>
						<c:choose>
							<c:when test="${!empty article.item_lot }">
								<c:forEach var="item_lot" items="${article.item_lot }">
										<li>${item_lot.lot_no}</li>
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
				<tr class="itemProperty hidden" >
					<th class="tdHead" rowspan="4">이력 사항</th>
					<!-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified" value="" readonly="readonly"/></td> -->
					<th class="tdHead" >작성일자</th>
					<td class="tdBody" ><input type="text" id="activeFrom" value="" readonly="readonly"/></td>
				</tr>
				<tr class="itemProperty hidden">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3" >
						<select name="historyType" disabled="disabled">
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
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">이력 사항</th>
					<td class="tdHead"  colspan="3">
						<table class="his_table">
						<colgroup>
						<col width="20%" />
						<col width="20%" />
						<col width="10%" />
						<col width="10%" />
						<col width="40%" />
						</colgroup>
						<tr class="itemProperty">
							<!-- <th>변경일자</th> -->
							<th>작성일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.itemHistory }">
								<c:forEach var="itemHistory" items="${article.itemHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<ul>
								<c:choose>
									<c:when test="${!empty article.itemHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${itemHistory.lastModified }"/></li>
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
									<c:when test="${!empty article.itemHistory }">
										<li><fmt:formatDate pattern="yyyy-MM-dd" value="${itemHistory.activeFrom }"/></li>
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
											<c:when test="${itemHistory.historyType eq hType.id }">
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
									<c:when test="${!empty article.itemHistory }">
										<li>${itemHistory.manager }</li>
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
									<c:when test="${!empty itemHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${itemHistory.historyDescription }</textarea>
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
				<tr class="itemProperty hidden">
					<th class="tdHead" >담당자</th>
					<td class="tdBody" colspan="3"><input type="text" readonly="readonly" name="history_manager" /></td>
				</tr>
				<tr class="itemProperty hidden">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3"><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">				
						${article.useStatus.propertyValue }
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 	<%if(privilegeId>1){ %>
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateProduct(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
		 <%} %>
		 	<img src="view/style/images/bt_list.jpg" name="cancel" alt="cancel" class="buttonised" onclick="toList(1,1,1);"/>
	 	</div>
	 	
 	</div>
 	
 	 <script type="text/javascript">
 	 
 		function create(){
 			$("form[name=productCreateForm1]").submit();			
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
 			$("#permit_date").datepicker();
 			$("#lastModified").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#activeFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#permit_date").datepicker("option", "dateFormat", "yy-mm-dd");
 		}
 	</script>
 	
 <script type="text/javascript">
  	function updateProduct(articleId){
  		
  		location.href="safetyItem.do?action=updateItemPage&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;
  		
  	}
  	
function del(articleId){
  		if(confirm("삭제하시겠습니까?")){
  			location.href="safetyItem.do?action=deleteItem&articleId="+articleId+"&privilegeId="+<%=privilegeId%>;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="safetyItem.do?action=listItem&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 