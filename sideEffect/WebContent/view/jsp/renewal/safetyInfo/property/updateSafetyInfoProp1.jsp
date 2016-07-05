<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
	 	<form method="POST" name="safetyInfoProp1From1" action="safetyInfo.do" >
			<input type="hidden" value="createSafetyInfoProp1" name="action"/>
			<input type="hidden" value="${article.id }" name="articleId"/>
			<input type="hidden" value="${article.propertyLevel }" name="propLevel"/>
	 		<table class="bordered fitToParent view_table" >
			<thead>
				<tr>
					<th colspan="5">구분 ${param.level } 수정</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${article.propertyLevel eq 2 }">
						<tr class="itemProperty">
							<th class="tdHead" colspan="2">구분 Level 1</th>
							<td class="tdBody"  colspan="3">
								<select name="parentId">
									<c:forEach var="prop" items="${requestScope.propList }">
										<c:choose>
											<c:when test="${prop.id eq article.parentId }">
												<option value="${prop.id }" selected="selected">${prop.propertyValue}</option>
											</c:when>
											<c:otherwise>
												<option value="${prop.id }">${prop.propertyValue}</option>
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</select>
							</td>
						</tr>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"  colspan="3">
						<input type="text" name="prop1" value="${article.propertyValue }">
					</td>
				</tr>
			</tbody>
 		</table> 	
 		<c:if test="${!empty sessionScope.user }">
 			<img src="view/style/images/bt_write.jpg" id="createButton1" class="buttonised" onclick="createSafetyInfoProp1();"/>
 			<img src="view/style/images/bt_cancel.jpg"  class="buttonised" onclick="Popup.hide('modal');"/>
 		</c:if>
	 	</form>
 	</div>

 
 <script type="text/javascript" >
   
 	function createSafetyInfoProp1(){
 		
 		var prop1 = $("input[name=prop1]").val();
 		var articleId = $("input[name=articleId]").val();
 		var xhr = new XMLHttpRequest();
 		var method = "get";
 		var url = "safetyInfo.do?action=updateSafetyInfoProp1&prop1="+prop1+"&articleId="+articleId;
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
 
 