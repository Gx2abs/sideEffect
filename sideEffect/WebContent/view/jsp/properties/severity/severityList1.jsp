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
 		<form method="get" name="severityListForm1" action="properties.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 		
 		<div class="searchBar1">
 			
 			<select name="searchColumn">
 			<option value="name">분류</option>
 			</select><input type="text" name="searchKeyword" value="${param.searchKeyword }"><img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 			
 		</div><br>
 	
 	<table class=" bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No.</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">Level</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">점수</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="298" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">분류</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="130" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">수정일자</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="read(${item.id});" >
				 		<td colspan="2">
				 			${localCount}
				 			<c:set var="localCount" value="${localCount-1 }"/>
				 		</td>
				 		<td colspan="2">
				 			${item.depthLevel}
				 		</td>
				 		<td colspan="2">
				 			${item.value }
				 		</td>
				 		<td colspan="2">
				 			${item.propertyValue }
				 		</td>
				 		<td colspan="2">
				 			<c:choose>
								<c:when test="${!empty item.severityHistory }">
									<c:forEach var="severityHistory" items="${item.severityHistory }" varStatus="i" >
										<c:choose>
										<c:when test="${i.last == true}">
										<fmt:formatDate pattern="yyyy-MM-dd" value="${severityHistory.lastModified }"/>
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
 	<div>
 		${count }
 	</div>
 	<div class="paging">
 		${requestScope.pageString }
 	</div>
 	
 	<div class="btnType3">
 	<%if(privilegeId>1){ %>
 		<img src="view/style/images/bt_write.jpg" alt="create" class="buttonised" onclick="create();"/>
 	<%} %>
	</div>
 	</form>	
 	</div>

 
 <script type="text/javascript" >
 	function read(id){
 		try{
 			//alert(id);
 			location.href = "properties.do?action=readSeverity&menu=10&articleId="+id;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	function create(){
 		location.href="properties.do?action=createSeverityPage&menu=10";
 		
 	}
 	
 	$(document).ready(function(){
 		
 		initSeverityList1Jsp();
 	});
 	
 	function initSeverityList1Jsp(){
 		
 		var requestURL = window.location.href;
 		$("input[name=requestURL]").val(requestURL);
 		
 		$("#getSearchButton1").bind("click", function(){
 			doGetSearch();
 		});
 		
 		
 	}
 	
 	function doGetSearch(){
 		$("form[name=severityListForm1]").submit();
 	}
 	
 </script>
 
 