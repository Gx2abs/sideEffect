<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%; overflow-y: scroll;">
 	<img src="${titleImg }" />
 	<form name="sideEffectCreateForm1" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="readSideEffect"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="40%" />
				<col width="60%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="5">공통 코드 관리 - 부작용 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" >Level 구분</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr>
					<th class="tdHead" >Level 경로</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >FDA Code</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >FDA Source PT(Korean)</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >FDA Source PT(English)</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >FDA Source PT Definition(Korean)</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >FDA Source PT definition(English)</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >NCI Code</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >NCIt Definition (Korean) Final</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >NCIt Definition</th>
					<td class="tdBody" ><input type="text" name="" readOnly /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >현재 이력</th>
					<td class="tdBody" >
						<ul>
						<c:choose>
							<c:when test="${!empty article.itemHistory }">
								<c:forEach var="itemHistory" items="${article.itemHistory }">
									<li>${itemHistory.historyDescription }</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li>
									No record
								</li>
							</c:otherwise>
						</c:choose>
						</ul>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >사용여부</th>
					<td class="tdBody" >
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<option value="${useStat.id }">${useStat.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
		 	<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="update(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
		 	<img src="view/style/images/bt_list.jpg" name="list" alt="list" class="buttonised" onclick="toList(1,1,1);"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function create(){
 			$("form[name=itemCreateForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 		
 			$("#lastModified").datepicker();
 			$("#activeFrom").datepicker();
 			
 		}
 	</script>
 <script type="text/javascript">
  	function update(articleId){
  		
  		location.href="properties.do?action=updateSideEffectPage&articleId="+articleId;
  		
  	}
  	
function del(articleId){
  		if(confirm("Really ?!")){
  			location.href="properties.do?action=deleteSideEffect&articleId="+articleId;	
  		}
  		
  	}
  	
  	function toList(page, searchKeyword, searchColumn){
  		location.href="properties.do?action=listSideEffect&page=&searchColumn=&searchKeyword=";
  	}
 </script>
 
 