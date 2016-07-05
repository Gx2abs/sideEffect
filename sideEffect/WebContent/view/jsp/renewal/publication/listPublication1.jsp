<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	
 	<form method="get" name="publicationListForm1" action="publication.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 	
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="72" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No.</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="102" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="122" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">제목</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="70" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">첨부</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">작성자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="92" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등록일</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">조회수</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="read(${item.id});" >
				 		<td colspan="2">${ item.id}</td>
				 		<td colspan="2">${item.publicationType.propertyValue }</td>
				 		<td class="subject" colspan="2">${item.title}</td>
				 		<td colspan="2"></td>
				 		<td colspan="2">${item.userId}</td>
				 		<td colspan="2"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.firstRegistered }"/></td>
				 		<td colspan="2">${item.hits}</td>
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
 	<div style="float: right;">
 		<img src="view/style/images/bt_write.jpg" alt="create" class="buttonised" onclick="create();"/>
	</div>
	
	<div class="searchBar1">
		<select name="publicationType">
			<option value="0">전체</option>
			<c:forEach var="publicationType" items="${requestScope.publicationType }">
				<c:choose>
					<c:when test="${publicationType.id eq 0 || param.publicationType eq publicationType.id }">
						<option selected="selected" value="${publicationType.id }">${publicationType.propertyValue }</option>
					</c:when>
					<c:otherwise>
						<option value="${publicationType.id }">${publicationType.propertyValue }</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		
		<select name="searchColumn">
			<option value="all">전체</option>
			<c:choose>
				<c:when test="${param.searchColumn eq 'setTitle' }">
					<option selected="selected" value="setTitle">제목</option>
				</c:when>
				<c:otherwise><option value="setTitle">제목</option></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.searchColumn eq 'setBody' }">
					<option selected="selected" value="setBody">내용</option>
				</c:when>
				<c:otherwise><option value="setBody">내용</option></c:otherwise>
			</c:choose>	
		</select>
		
		<input type="text" name="searchKeyword" value="${param.searchKeyword }">
		<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
	</div> 
 	</form>
 	</div>

 
 <script type="text/javascript" >
 	function read(id){
 		try{
 			//alert(id);
 			location.href = "publication.do?action=readPublication&articleId="+id;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 	}
 	
 	function create(){
 		location.href="publication.do?action=createPublicationPage";
 	}
 	
 	$(document).ready(function(){
 		initPublicationList1Jsp();
 	});
 	
	function initPublicationList1Jsp(){
 		
 		$("#getSearchButton1").bind("click", function(){
 			publicationListForm1Submit();
 		});
 		
 	}
	
	function publicationListForm1Submit(){
 		
 		$("form[name=publicationListForm1]").submit();
 		
 	}

 </script>
 
 