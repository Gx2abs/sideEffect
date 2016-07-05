<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%; overflow-y: scroll;">
 	<img src="${titleImg }" />
 	<form name="sideEffectCreateForm1" enctype="multipart/form-data">
 		<input type="hidden" name="action" value="createSideEffect"/>
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
					<th colspan="5">공통 코드 관리 - 부작용 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >코드 구분</th>
					<td class="tdBody" >
						<select name="" >
							
							<option></option>
						</select></td>
					<th class="tdHead" >구분</th>
					<td class="tdBody" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >Level 1</th>
					<td class="tdBody" colspan="3" >
						<select name="" >
						
							<option></option>
						</select>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >FDA Code</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >FDA Source PT(Korean)</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >FDA Source PT(English)</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >FDA Source Definition(Korean)</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >FDA Source Definition(English)</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >NCI CODE</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >NCIt Definition (Korean) Final</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >NCIt Definition</th>
					<td class="tdBody" colspan="3" ><input type="text" name="" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 사항</th>
					<th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified"/></td>
					<th class="tdHead" >적용일자</th>
					<td class="tdBody" ><input type="text" id="activeFrom"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3">
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<option>${hType.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >담당자</th>
					<td class="tdBody" colspan="3"><input type="text" name="history_manager" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3"><textarea name="history_description"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<option value="${useStat.id }">${useStat.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" class="buttonised" alt="확인" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" class="buttonised" alt="취소" onclick=""/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 			$("form[name=sideEffectCreateForm1]").submit();			
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
 