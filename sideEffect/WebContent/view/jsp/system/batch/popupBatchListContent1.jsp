<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No.</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">기준 코드(구코드)</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">성공</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">실패</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">작업결과</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">서비스분야</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr  >
				 		<td colspan="2">
				 			${item.id }
				 		</td>
				 		<td class="subject" colspan="2">
				 		</td>
				 		<td colspan="2">
				 		</td>
				 		<td colspan="2"> </td>
				 		<td colspan="2">
				 		</td>
				 		<td colspan="2">
				 			<input type="hidden" value="${item.batchGroupId }" name="batchGroupId"/>
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
 	<div>
 		${count }
 	</div>
 	<div class="paging">
 		${requestScope.pageString }
 	</div>