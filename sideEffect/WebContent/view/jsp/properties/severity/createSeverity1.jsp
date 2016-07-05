<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="severityCreateForm1"  method="POST" action="properties.do">
 	<input type="hidden" name="action" value="createSeverity"/>
 	<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">공통 코드 관리 - 위해심각도 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">Level</th>
					<td class="tdBody" >
						<input type="radio" name="depthLevel" value="1" checked="checked" onclick="level1()"/>Level1
						<input type="radio" name="depthLevel" value="2" onclick="level2()"/>Level2
					</td>	
				</tr>
				<tr class="itemProperty level1">
					<th class="tdHead" colspan="2">상위코드</th>
					<td class="tdBody" >
			 			<select name="parentId">
			 				<c:choose>
								<c:when test="${!empty requestScope.list }">
			 						<c:forEach var="item" items="${requestScope.list }">
			 							<option value="${item.id }">${item.propertyValue }</option>
			 						</c:forEach>
			 					</c:when>
			 					<c:otherwise>
			 						no data
			 					</c:otherwise>
		 					</c:choose>
		 				</select>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">점수</th>
					<td class="tdBody" ><input type="text" name="value" onkeypress="isNum()" style='ime-mode:disabled'/></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">분류</th>
					<td class="tdBody" ><input type="text" name="propertyValue" /></td>	
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">위해심각도(Severity) 설명</th>
					<td class="tdBody" ><textarea name="severityName"></textarea></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">적용시작일</th>
					<td class="tdBody" ><input type="text" id="startDate" name="startDate" /></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 사항</th>
					<!-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified" name="lastModified"/></td> -->
					<th class="tdHead" >작성일자</th>
					<td class="tdBody" ><input type="text" id="activeFrom" name="activeFrom"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" >
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<option value="${hType.id }">${hType.propertyValue }</option>
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
					<td class="tdBody" ><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}"/>${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody" ><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" >
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
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 			$("form[name=severityCreateForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			$(".level1").hide();	
 			initPage();
 			
 		});
 		
 		function initPage(){
 			$("#startDate").datepicker();
 			$("#activeFrom").datepicker();
 			$("#startDate").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#activeFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#startDate").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
 			$("#activeFrom").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
 		}

 		function isNum(){
 			var key = event.keyCode;
 			if(!(key==8||key==13||(key>=48&&key<=57))){ 
	 			alert('숫자만 입력 가능합니다.'); 
	 			if(event.preventDefault)event.preventDefault();
	 			else event.returnValue = false; 
 			} 
 	 	}

 	 	function level1(){
 	 		$(".level1").hide();
 	 	}

 	 	function level2(){
			$(".level1").show();
 	 	}
 	</script>
 