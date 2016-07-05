<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	
 	<form method="get" name="itemListForm1" action="itemCategory.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 		
 		<div class="searchBar1">
 			<div class="searchBar2">
 			<select name="searchColumn">
 				<option value="setClass_kor_name">품목명</option>
 				<c:choose>
 					<c:when test="${param.searchColumn eq 'setMea_class_no' }">
 						<option value="setMea_class_no" selected="selected">품목코드</option>
 					</c:when>
 					<c:otherwise>
 						<option value="setMea_class_no">품목코드</option>
 					</c:otherwise>
 				</c:choose>
 			</select>
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 			</div>
 		<div class="searchRadio1">
 			<c:forEach var="meaClassNoLv" items="${meaClassNoLevel }">
 				<c:choose>
 				<c:when test="${meaClassNoLv.id eq 0 || param.meaClassNoLevel eq meaClassNoLv.id }">
 					<input type="radio" name="meaClassNoLevel" value="${meaClassNoLv.id }" checked="checked"/>${meaClassNoLv.propertyValue}
 				</c:when>
 				<c:otherwise>
 					<input type="radio" name="meaClassNoLevel" value="${meaClassNoLv.id }"/>${meaClassNoLv.propertyValue}
 				</c:otherwise>
 				</c:choose>
 			</c:forEach><br>
 			<c:forEach var="codeAge" items="${itemCodeTypes }" begin="0" end="2">
 				<c:choose>
 					<c:when test="${codeAge.id eq 0 || param.codeAge eq codeAge.id }">
 						<input name="codeAge" type="radio" value="${codeAge.id }" checked="checked">${codeAge.propertyValue }
 					</c:when>
 					<c:otherwise>
 						<input name="codeAge" type="radio" value="${codeAge.id }">${codeAge.propertyValue }
 					</c:otherwise>
 				</c:choose>
 			</c:forEach>
 			</div>
 		</div><br><br><br>
 	
 	<div class="levelBtn1">
 	<%if(privilegeId>1){ %>
 	<c:choose>
 		<c:when test="${!empty meaClassNoLevel }">
 			<c:forEach var="meaClassNoLevel" items="${meaClassNoLevel}" begin="1" end="3">
	 			<img src="view/style/images/btn_class${meaClassNoLevel.id }.jpg" id="submit1" class="listover" alt="level${meaClassNoLevel.id }등록" onclick="create(${meaClassNoLevel.id});"/>
	 		</c:forEach>
 		</c:when>
 		<c:otherwise>
 			No level data
 		</c:otherwise>
 	</c:choose>
 	<%} %>
 	</div>
 	<table class=" bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목코드</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="200" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">코드구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">사용여부</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">수정일자</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">

				 	<%-- <tr class="listover" onclick="read('${item.compositeKey.mea_class_no}', ${item.compositeKey.grade });" > --%>
				 	<tr class="listover" onclick="read(${item.id});" >
						<td colspan="2">
				 			<%-- ${item.class_level } --%>
				 			<%-- ${item.itemLevelList.propertyValue } --%>
				 			<%-- ${itemLevelList.id } --%>
				 			${item.meaClassNoLevel.propertyValue }
				 		</td>
				 		<td class="subject" colspan="2">
				 			${item.mea_class_no }
				 		</td>
				 		<td colspan="2">
				 			${item.class_kor_name }
				 		</td>
				 		<td colspan="2">${item.code_age.propertyValue }</td>
				 		<%-- <td colspan="2">${item.gradeObj.propertyValue }</td> --%>
				 		<td colspan="2">${item.categoryGrade.propertyValue }</td>
				 		<td colspan="2">
				 			${item.isInUse.propertyValue }
				 		</td>
				 		<td colspan="2">
				 			<c:choose>
								<c:when test="${!empty item.history }">
									<c:forEach var="history" items="${item.history }" varStatus="i" >
										<c:choose>
										<c:when test="${i.last == true}">
										<fmt:formatDate pattern="yyyy-MM-dd" value="${history.lastModified }"/>
										</c:when> 
										</c:choose>
									 </c:forEach> 
								</c:when>
								
							</c:choose>
				 		</td>
				 	</tr>
				 </c:forEach>
		 	</c:when>
		 	<c:otherwise>
		 		<tr>
		 			<td>no data</td>
				</tr>
			</c:otherwise>
		 </c:choose>
 	</table>
 	
 	<div class="paging">
 		${requestScope.pageString }
 	</div>
 	
 	<!--  
 	<div style="float: right;">
 		<img src="view/style/images/bt_write.jpg" alt="create" class="buttonised" onclick="create();"/>
	</div>
	-->
 	</form>
 	</div>

 
 <script type="text/javascript" >

 	function read(id){

 		try{
 			location.href = "itemCategory.do?action=readItemCategory&articleId="+id;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	
 	function create(level){
 		location.href="itemCategory.do?action=createItemCategoryPage&level="+level;
 		
 	}
 
 	$(document).ready(function(){
 		initItemList1Jsp();
 	});
 	
 	function initItemList1Jsp(){
 		
 		$("#getSearchButton1").bind("click", function(){
 			itemListForm1Submit();
 		});
 		
 	}
 	
 	function itemListForm1Submit(){
 		
 		$("form[name=itemListForm1]").submit();
 		
 	}
 	
 	
 	
 </script>
 
 