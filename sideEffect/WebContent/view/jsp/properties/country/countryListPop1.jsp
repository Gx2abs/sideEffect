<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%-- <%@ include file="/view/config/styleSheetsAndScripts.jsp" %> --%>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css" >
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="margin: 30px 0 0 5px;">
 		<div style="margin-bottom: 20px;"> 
 			<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 		</div>
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="82" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No.</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">국가명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">적용일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">변경일자</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onMouseOver="this.style.backgroundColor='#F6F6F6'" onMouseOut="this.style.backgroundColor='#FFFFFF'" onclick="winClose(${item.id}, '${item.propertyValue }');" >
				 		<td colspan="2">
				 			${localCount}
				 			<c:set var="localCount" value="${localCount-1 }"/>
				 		</td>
				 		<td colspan="2">
				 			${item.propertyValue }
				 		</td>
				 		<td colspan="2"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.activeFrom }"/></td>
				 		<td colspan="2"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.lastModified }"/></td>
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
 	<div>
 		${count }
 	</div>
 	<div class="paging" style="min-width: 0">
 		${requestScope.pageString }
 	</div>
 	

 	
 	</div>

 
 <script type="text/javascript" >
 	function winClose(id, propertyValue){
		opener.document.safetyCreateForm1.country_manufactured_id.value = id;
		opener.document.safetyCreateForm1.countryManufactured.value = propertyValue;
		
		window.close();
 		
 	}
 </script>
 
 