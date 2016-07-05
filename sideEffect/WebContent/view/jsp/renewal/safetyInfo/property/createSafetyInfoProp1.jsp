<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
	 	<form method="POST" name="safetyInfoProp1From1" action="safetyInfo.do" >
			<input type="hidden" value="createSafetyInfoProp1" name="action"/>
			<input type="hidden" value="${param.propLevel }" name="propLevel"/><br/>
			<input type="hidden" value="${param.regionId }" name="regionId"/><br/>
	 		<table class="bordered fitToParent view_table" >
			<thead>
				<tr>
					<th colspan="5">구분 ${param.propLevel } 등록</th>
				</tr>
			</thead>
			<tbody>
				
				<c:choose>
					<c:when test="${param.propLevel eq 2 }">
						<tr class="itemProperty">
							<th class="tdHead" colspan="2">구분 Level 1</th>
							<td class="tdBody"  colspan="3">
								<select name="parentId">
									<c:forEach var="prop" items="${requestScope.propList }">
										<option value="${prop.id }">${prop.propertyValue}</option>
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
						<input type="text" name="prop1">
					</td>
				</tr>				
			</tbody>
 		</table> 
 		<c:if test="${!empty sessionScope.user }">
 			<img src="view/style/images/bt_write.jpg" id="createButton1" class="buttonised" onclick="createSafetyInfoProp1();"/>
 			<img src="view/style/images/bt_cancel.jpg"  class="buttonised" />
 		</c:if>	
 		
	 	</form>
 	</div>

 
 <script type="text/javascript" >
   
 	function createSafetyInfoProp1(){
 		
 		var regionId = $("input[name=regionId]").val();
 		var prop1 = $("input[name=prop1]").val();
 		var propLevel = $("input[name=propLevel]").val();
 		var propParentId = $("select[name=parentId] option:selected").val();
 		
 		var xhr = new XMLHttpRequest();
 		var method = "get";
 		var url = "safetyInfo.do?action=createSafetyInfoProp1&prop1="+prop1;
 		url += "&propLevel="+propLevel+"&propParentId="+propParentId;
 		url += "&regionId="+regionId;
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
 
 