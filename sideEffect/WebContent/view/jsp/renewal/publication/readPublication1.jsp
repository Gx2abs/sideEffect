<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="publicationCreateForm1" method="POST" enctype="multipart/form-data">
 		<input type="text" name="action" value="readPublication"/>
 		<input type="text" name="articleId" value="${article.id }"/>
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
					<th colspan="5">센터발간물</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"  colspan="3">
					<c:choose>
						 	<c:when test="${!empty requestScope.publicationType  }">
						 		<c:forEach var="Type" items="${requestScope.publicationType }">
						 			<c:choose>
						 				<c:when test="${Type.id eq article.publicationType.id }">
						 					${Type.propertyValue }
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
					<th class="tdHead" colspan="2">제목</th>
					<td class="tdBody"  colspan="3">${article.title}</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">내용</th>
					<td class="tdBody"  colspan="3">${article.body }
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">등록일</th>
					<td class="tdBody"  colspan="3"><fmt:formatDate pattern="yyyy-MM-dd" value="${article.firstRegistered }"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">첨부파일</th>
					<td class="tdBody"  colspan="3"></td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" onclick="updatePublication(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" onclick="del(${article.id});"/>
		 	<img src="view/style/images/bt_cancel.jpg" name="cancel" alt="cancel" onclick="toList(1,1,1,);"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function create(){
 			$("form[name=publicationCreateForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 		
 			
 		}
 	</script>
 <script type="text/javascript">
  	function updatePublication(articleId){
  		
  		location.href="publication.do?action=updatePublicationPage&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("Really ?!")){
  			location.href="publication.do?action=deletePublication&articleId="+articleId;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="publication.do?action=listPublication&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 