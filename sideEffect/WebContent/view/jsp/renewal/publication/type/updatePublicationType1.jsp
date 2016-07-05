<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
	 	<form method="POST" name="publicationTypeFrom1" action="publication.do" >
			<input type="hidden" value="createPublication" name="action"/>
			<input type="hidden" value="${article.id }" name="articleId"/>
	 		<table class="bordered fitToParent view_table" >
			<thead>
				<tr>
					<th colspan="5">구분 수정</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"  colspan="3">
						<input type="text" name="prop1" value="${article.propertyValue }">
					</td>
				</tr>
			</tbody>
 		</table> 	
 		<img src="view/style/images/bt_write.jpg" id="createButton1" class="buttonised" onclick="createPublicationType();"/>
 		<img src="view/style/images/bt_cancel.jpg"  class="buttonised" onclick="Popup.hide('modal');"/>
	 	</form>
 	</div>

 
 <script type="text/javascript" >
   
 	function createPublicationType(){
 		
 		var prop1 = $("input[name=prop1]").val();
 		var articleId = $("input[name=articleId]").val();
 		var xhr = new XMLHttpRequest();
 		var method = "get";
 		var url = "publication.do?action=updatePublicationType&prop1="+encodeURIComponent(prop1)+"&articleId="+articleId;
 		var isAsync = true;
 		
 		xhr.open(method, url, isAsync);
 		xhr.send();
 		
 		xhr.onreadystatechange = function(){
 			
 			if(xhr.readyState == 4){
 				if(xhr.status == 200){
 					if(xhr.responseText==1){
 						alert("작업성공");
 						Popup.hide("modal");
 						location.reload();
 					}else{
 						alert("작업실패");
 					}
 				}
 			}
 			
 		};
 	
 		
 	}
 </script>
 
 