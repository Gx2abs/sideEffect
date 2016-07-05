<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form method="get" name="itemListForm1" action="system.do">
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
 	<table class="bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="202" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">ID</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="230" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">환경변수</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="230" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">변수값</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr onclick="read(${item.id});" >
				 		<td colspan="2">${item.id }</td>
				 		<td colspan="2">${item.propertyName}</td>
				 		<td colspan="2">${item.propertyValue }</td>
				 		<td></td>
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
 	function read(articleId){
 		try{
 			location.href = "system.do?action=readSystemProperty&articleId="+articleId;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	function create(level){
 		location.href="properties.do?action=createItemPage&level="+level;
 		
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
 
 