<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/jquery.mtz.monthpicker.js"></script>
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
 	<form name="importOutputCreateForm1" method="POST" action="importoutput.do">
 	<input type="hidden" name="action" value="createImport"/>
 	<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">공통 코드 관리 - 생산·수입 실적 데이터</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody" >
						<input type="radio" name="importoutput" value="수입량" checked="checked" />수입량 
						<input type="radio" name="importoutput" value="생산량" />생산량
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">제품정보</th>
					<td class="tdBody" >
						<input type="text" name="mea_class_no" readonly="readonly" />
						<input type="text" name="item_class_kor_name" readonly="readonly" />
						<input type="hidden" name="item_id" id="item_id" />
						<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="pop1();" />
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" id="importOutput">수입량</th>
					<td class="tdBody" ><input type="text" name="itemImport" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >형명</th>
					<td class="tdBody" ><input type="text" name="typeName" style="width: 80%;"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">날짜</th>
					<td class="tdBody" ><input type="text" name="importDate" id="importDate" /></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 사항</th>
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
					<td class="tdBody" ><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody" ><textarea name="historyDescription"></textarea></td>
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
 	 		if($("input[name=importoutput]:checked").val() == '생산량'){
				$("input[name=action]").val("createOutput");
 	 	 	}
 	 	 	if(validation()){
 	 	 		$("form[name=importOutputCreateForm1]").submit();
 	 	 	}
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			$("input[name=importoutput]").bind("click", function(){
 				$("#importOutput").text($(this).val());
 			});
 			
 		});
 		
 		function initPage(){
 			$("#activeFrom").datepicker();
 			$("#activeFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#activeFrom").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
 			$("#importDate").monthpicker();
 		}

 		function pop1(){
 			var url ="safetyItem.do?action=listItemPop";
 	 		var properties ="width=770,height=600";
 	 		window.open(url, '', properties, 'scrollbars=yes');
 	 	}

 	 	function validation(){
 	 	 	var validation = false;
 	 	 	
 	 	 	if($("#importDate").val() == ""){
 	 	 	 	alert("날짜를 입력해주세요.");
 	 	 	}else{
				validation = true;
 	 	 	}

 	 	 	return validation;
 	 	 }
 	</script>
 