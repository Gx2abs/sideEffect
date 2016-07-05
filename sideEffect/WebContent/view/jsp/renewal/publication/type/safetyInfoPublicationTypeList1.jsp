<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/popup.js"></script>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	
 	<form method="post" name="PublicationTypeListForm1" action="publication.do">
 			<input type="hidden" value="listPublicationType" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 		
 		<div class="searchBar1">
 			구분 
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 			<div style="float: right; margin-right: 50px">
	 			<img src="view/style/images/bt_write.jpg" alt="create" class="buttonised" onclick="create();"/>
 		</div>
 		</div><br>
 		
 	
 	
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="550" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="150" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">수정/삭제</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover2">
				 		<td colspan="2">
				 			${item.propertyValue }
				 		</td>
				 		<td colspan="2">
				 			<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updatePublicationType('${item.id}');"/>
				 			<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="deletePublicationType('${item.id}');"/>
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
 	</form>
 	</div>

 <div id="modal" style="border:3px solid black; background-color:white;  padding:25px; font-size:150%; text-align:center; display:none;">
	This is a modal popup!<br><br>
	<input type="button" value="OK" onClick="Popup.hide('modal')">
</div>
 <script type="text/javascript" >
  
 	/* function create(){
 		location.href="publication.do?action=createPublicationType";
 	} */
 	
 	$(document).ready(function(){
 		initcomponentList1Jsp();
 	});
 	
	function initcomponentList1Jsp(){
 		
 		$("#getSearchButton1").bind("click", function(){
 			PublicationTypeListForm1Submit();
 		});
 		
 	}
	
	function PublicationTypeListForm1Submit(){
 		
 		$("form[name=PublicationTypeListForm1]").submit();
 		
 	}
	
	function updatePublicationType(articleId){
		
		if(confirm("기존데이터도 수정 됩니다. 계속 하시겠습니까 ?")){
			var xhr = new XMLHttpRequest();
			var method = "get";
			var url = "publication.do?action=updatePublicationTypePage&articleId="+articleId;
			var isAsync = true;
			
			xhr.open(method, url, isAsync);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						
						$("#modal").html(xhr.responseText);
						Popup.showModal('modal',null,null,{'screenColor':'blue','screenOpacity':0.2,'offsetTop':-250,'offsetLeft':-200});
					}else{
						alert('내부에러');
					}
				}
				
			};		
		}else{
			return;
		}
	}
	
	function deletePublicationType(articleId){
		
		if(confirm("삭제 하시겠습니까?")){
			var xhr = new XMLHttpRequest();
			var method = "get";
			var url = "publication.do?action=deletePublicationType&articleId="+articleId;
			var isAsync = true;
			xhr.open(method, url, isAsync);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						if(xhr.responseText==1){
							alert("삭제성공");
							location.reload();
						}else{
							alert("삭제실패");
						}
					}
				}
				
			};
		}else{
			return;
		}
		
		
	}
	
	function create(){

		var xhr = new XMLHttpRequest();
		var method = "get";
		var url = "publication.do?action=createPublicationTypePage";
		var isAsync = true;
		
		xhr.open(method, url, isAsync);
		xhr.send();
		
		xhr.onreadystatechange = function(){
			
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					
					$("#modal").html(xhr.responseText);
					Popup.showModal('modal',null,null,{'screenColor':'blue','screenOpacity':0.2,'offsetTop':-250,'offsetLeft':-200});
				}
			}
			
		};		
		
		return;
	}

 </script>
 
 