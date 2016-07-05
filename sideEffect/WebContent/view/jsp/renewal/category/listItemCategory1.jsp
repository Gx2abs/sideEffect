<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	
 	<form method="get" name="itemListForm1" action="properties.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 		
 		<div class="searchBar1">
 			<select name="searchColumn">
 				<option value="setMea_class_no">품목코드</option>
 				<option value="setClass_kor_name">품목명</option>
 			</select>
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
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
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 		</div><br>
 	
 	<div class="levelBtn1">
 	<c:choose>
 		<c:when test="${!empty itemLevelList }">
 			<c:forEach var="itemLevel"items="${itemLevelList}">
	 			<img src="view/style/images/btn_lev${itemLevel.id }.jpg" id="submit1" alt="level${itemLevel.id }등록" onclick="create(${itemLevel.id});"/>
	 		</c:forEach>
 		</c:when>
 		<c:otherwise>
 			No level data
 		</c:otherwise>
 	</c:choose>
 		
 	</div>
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목코드</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="140" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">코드구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">사용여부</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">변경일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">적용일자</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="read(${item.id});" >
				 		<td colspan="2">
				 			<%-- ${item.class_level } --%>
				 			<%-- ${item.itemLevelList.propertyValue } --%>
				 			${itemLevelList.propertyValue }
				 		</td>
				 		<td class="subject" colspan="2">
				 			<%-- ${item.mea_class_no } //  --%>
				 		</td>
				 		<td colspan="2">
				 			${item.class_kor_name }
				 		</td>
				 		<td colspan="2">${item.code_age.propertyValue }</td>
				 		<td colspan="2">${item.gradeObj.propertyValue }</td>
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
				 		<td colspan="2">
				 			<c:choose>
								<c:when test="${!empty item.history }">
									<c:forEach var="history" items="${item.history }" varStatus="i" >
										<c:choose>
										<c:when test="${i.last == true}">
										<fmt:formatDate pattern="yyyy-MM-dd" value="${history.activeFrom }"/>
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
 			alert(id);
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
 
 