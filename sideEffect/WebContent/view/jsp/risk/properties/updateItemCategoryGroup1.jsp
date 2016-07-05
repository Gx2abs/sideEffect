<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/jquery.mtz.monthpicker.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/spectrum/spectrum.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 <link rel="stylesheet" type="text/css" href="view/style/css/spectrum.css">
 	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="itemCategoryGroupCreateForm1" method="POST" action="risk.category.do">
 		<input type="hidden" name="action" value="updateItemCategoryGroup"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
 		<input type="hidden" name="backupId" value="${backup.id }"/>
 		<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="5%" />
				<col width="5%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="5">공통 코드 관리 - 발생가능성 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody" >${article.typeStatus.propertyValue }</td>	
					<th class="tdHead">그룹명</th>
					<td class="tdBody" ><input type="text" name="propertyValue" value="${article.propertyValue }"/></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">개정버전 명칭</th>
					<td class="tdBody" ><input type="text" name="revisionName" value="${backup.revisionName }" /></td>	
					<th class="tdHead">시작일</th>
					<td class="tdBody" ><input type="text" id="startDate" name="startDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${backup.startDate }"/>"/></td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">품목별<br>&nbsp;발생가능성 정보</th>
					<td class="tdBody" colspan="3">
						<table>
							<colgroup>
								<col width="10%" />
								<col width="5%" />
								<col width="25%" />
								<col width="25%" />
								<col width="35%" />
							</colgroup>
							<tr>
								<th class="tdHead" rowspan="60">분류</th>
								<th class="tdHead">점수</th>
								<th class="tdHead">발생가능성 분류</th>
								<th class="tdHead">발생가능성(Likelihood)</th>
								<td><img src="view/style/images/btn_tab02.jpg" class="hover1" alt="추가" id="addItemType" onclick="addItemP()" /></td>
							</tr>
							<c:choose>
									<c:when test="${!empty backup.possibility }">
										<c:forEach var="possibility" items="${backup.possibility }" varStatus="vs">
										<tr class="old_p${possibility.id }" >
											<td class="tdBody"><input type="text" name="old_pvalue[]" value="${possibility.value }" size="4"/></td>
											<td class="tdBody"><input type="text" name="old_pclassName[]" value="${possibility.className }" size="25" /></td>
											<td class="tdBody">
												<input type="text" name="old_likelihoodFm[]" class="likelihoodFm" value="${possibility.likelihoodFm }" size="4" />이상  
												<input type="text" name="old_likelihoodTo[]" class="likelihoodTo" value="${possibility.likelihoodTo }" size="4" />미만 %
											</td>
											<td class="tdBody">
												<img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" onclick="deleteTr('p','${possibility.id }')" />
												<input type="hidden" name="old_possibilityId[]" value="${possibility.id }" />
												<input type="hidden" name="old_pvalueDeleteChk[]" id="p${possibility.id }" />
											</td>
										</tr>
										</c:forEach>
									</c:when>
							</c:choose>
							<tr class="itemP">
								<td><ul id="itemP"><li></li></ul></td>
								<td><ul id="itemP2"><li></li></ul></td>
								<td><ul id="itemP3"><li></li></ul></td>
								<td><ul id="itemP4"><li></li></ul></td>
							</tr>
						</table>
					</td>	
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">품목별<br>&nbsp;위험도 점수범위<br>&nbsp;/수준</th>
					<td class="tdBody" colspan="3">
						<table>
							<colgroup>
								<col width="10%" />
								<col width="5%" />
								<col width="15%" />
								<col width="18%" />
								<col width="35%" />
								<col width="7%" />
								<col width="10%" />
							</colgroup>
							<tr>
								<th rowspan="60">위험성<br>&nbsp;계산 결과</th>
								<th>순서</th>
								<th>위험도 점수</th>
								<th>위험도 수준</th>
								<th>조치사항</th>
								<th>색상</th>
								<td><img src="view/style/images/btn_tab02.jpg" class="hover1" alt="추가" onclick="addItemR()" /></td>
							</tr>
							<c:choose>
								<c:when test="${!empty backup.risk }">
									<c:forEach var="risk" items="${backup.risk }">
									<tr class="old_r${risk.id }">
										<td class="tdBody"><input type="text" size="4" name="old_rvalue[]" value="${risk.value }" size="9" /></td>
										<td class="tdBody">
											<input type="text" size="4" name="old_riskGradeFm[]" class="riskGradeFm" value="${risk.riskGradeFm }" />이상  
											<input type="text" size="4" name="old_riskGradeTo[]" class="riskGradeTo" value="${risk.riskGradeTo }" />이하
										</td>
										<td class="tdBody"><input type="text" name="old_rclassName[]" value="${risk.className }" /></td>
										<td class="tdBody"><textarea name="old_correctiveMeasure[]">${risk.correctiveMeasure }</textarea></td>
										<td class="tdBody"><input type="text" name="old_color[]" class="selectColor" value="${risk.color }" /></td>
										<td class="tdBody">
											<img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" onclick="deleteTr('r','${risk.id }')" />
											<input type="hidden" name="old_riskId[]" value="${risk.id }" />
											<input type="hidden" name="old_rvalueDeleteChk[]" id="r${risk.id }" />
										</td>
									</tr>
									</c:forEach>
								</c:when>
							</c:choose>
							<tr class="itemR">
								<td><ul id="itemR"><li></li></ul></td>
								<td><ul id="itemR2"><li></li></ul></td>
								<td><ul id="itemR3"><li></li></ul></td>
								<td><ul id="itemR4"><li></li></ul></td>
								<td><ul id="itemR6"><li></li></ul></td>
								<td><ul id="itemR5"><li></li></ul></td>
							</tr>
						</table>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력<br>&nbsp;현황</th>
					<%-- <th class="tdHead" >작성일자</th>
					<td class="tdBody" ><input type="text" id="lastModified" name="lastModified" value="${article.lastModified }"/></td --%>
					<th class="tdHead" >작성<br>&nbsp;일자</th>
					<td class="tdBody" colspan="3"><input type="text" id="activeFrom" name="activeFrom" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3">
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${hType.propertyValue eq '개정'}">
												<option selected="selected" value="${hType.id }">${hType.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${hType.id }">${hType.propertyValue }</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="-1">no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>	
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >담당자</th>
					<td class="tdBody" colspan="3">
						<input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}
					</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3"><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" colspan="3">
						<table>
						<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
						<col width="40%" />
						<col width="10%" />
						</colgroup>
						<tr class="itemProprty">
							<!-- <th>변경일자</th> -->
							<th>작성일자</th>
							<th>구분</th>
							<th>담당자</th>
							<th colspan="2">사유</th>
						</tr>
						<c:choose>
							<c:when test="${!empty article.itemCategoryGroupHistory }">
								<c:forEach var="itemCategoryGroupHistory" items="${article.itemCategoryGroupHistory }">
						<tr class="itemProperty">
							<%-- <td><fmt:formatDate pattern="yyyy-MM-dd" value="${itemCategoryGroupHistory.lastModified }"/></td> --%>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${itemCategoryGroupHistory.activeFrom }"/></td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${itemCategoryGroupHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td><c:choose>
								<c:when test="${!empty article.itemCategoryGroupHistory }">
										${itemCategoryGroupHistory.manager }
								</c:when>
							</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${!empty itemCategoryGroupHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${itemCategoryGroupHistory.historyDescription }</textarea>
									</c:when>
								</c:choose>
							</td>
							<td id="history${itemCategoryGroupHistory.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="이력삭제" onclick="deleteHistory('${itemCategoryGroupHistory.id}','<%=privilegeId %>');"/></td>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody" colspan="3">
						<select name="isInUse" >
							<c:choose>
								<c:when test="${!empty requestScope.isInUse  }">
									<c:forEach var="useStat" items="${isInUse }">
										<c:choose>
											<c:when test="${useStat.id eq article.isInUse }">
												<option selected="selected" value="${useStat.id }">${useStat.propertyValue }</option>
											</c:when>
											<c:otherwise>
												<option value="${useStat.id }">${useStat.propertyValue }</option>
											</c:otherwise>
										</c:choose>
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
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="fromToCheck();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script type="text/javascript">
 	var risk = 0; 	 
 		function createItemCategoryGroup(){
 			$("form[name=itemCategoryGroupCreateForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 	 		$(".itemP").hide();
 	 		$(".itemR").hide();
 			$("#activeFrom").datepicker();
 			$("#startDate").monthpicker();	
 			selectColor(".selectColor");
 		}
 		
 		function deleteHistory(articleId,privilegeId){
 			if(privilegeId>1){
 			var method = "get";
 			var url = "risk.category.do?action=deleteItemCategoryGroupHistory&articleId="+articleId+"&privilegeId="+privilegeId;
 			var xhr = new XMLHttpRequest();
 			xhr.open(method,url, true);
 			xhr.send();
 			
 			xhr.onreadystatechange = function(){
 				
 				if(xhr.readyState==4 && xhr.status==200){
 					//alert(xhr.responseText );
 					if( xhr.responseText =="1" ||  xhr.responseText ==1){
 						alert("삭제성공");
 						
 						$("#history"+articleId).remove();
 					}else alert("권한이 없습니다.");
 				}
 				
 			};
 			}
 		}

		function addItemP(){

			$(".itemP").show();
			var li1 = document.createElement("li");
			var li2 = document.createElement("li");
			var li3 = document.createElement("li");
			var li4 = document.createElement("li");
			var textBox1 = document.createElement("input");
			var textBox2 = document.createElement("input");
			var textBox3 = document.createElement("input");
			var textBox4 = document.createElement("input");
			var deleteButton = document.createElement("img");
			
			$(textBox1).attr("type", "text");
			$(textBox1).attr("name", "pvalue[]");
			$(textBox1).attr("size", 4);
			$(textBox1).css("margin-top", 4);

			$(textBox2).attr("type", "text");
			$(textBox2).attr("name", "pclassName[]");
			$(textBox2).attr("size", 25);
			$(textBox2).css("margin-top", 4);

			$(textBox3).attr("type", "text");
			$(textBox3).attr("name", "likelihoodFm[]");
			$(textBox3).attr("class", "likelihoodFm");
			$(textBox3).attr("size", 4);

			$(textBox4).attr("type", "text");
			$(textBox4).attr("name", "likelihoodTo[]");
			$(textBox4).attr("class", "likelihoodTo");
			$(textBox4).attr("size", 4);
			
			$(deleteButton).attr("src", "view/style/images/btn_tab03.jpg");
			$(deleteButton).attr("class", "hover1");
			$(deleteButton).attr("alt", "삭제");
			
			$(li1).append(textBox1);
			$(li2).append(textBox2);
			$(li3).append(textBox3);
			$(li3).css("margin-top", 4);
			$(li3).append("이상 ");
			$(li3).append(textBox4);
			$(li3).append("미만 %");
			$(li4).append(deleteButton);
					
			$("#itemP").append(li1);
			$("#itemP2").append(li2);
			$("#itemP3").append(li3);
			$("#itemP4").append(li4);
			
			$(deleteButton).bind("click", function(){
				$(li1).remove();
				$(li2).remove();
				$(li3).remove();
				$(li4).remove();
			});
			
		}

		function addItemR(){

			$(".itemR").show();
			var li1 = document.createElement("li");
			var li2 = document.createElement("li");
			var li3 = document.createElement("li");
			var li4 = document.createElement("li");
			var li5 = document.createElement("li");
			var li6 = document.createElement("li");
			var textBox1 = document.createElement("input");
			var textBox2 = document.createElement("input");
			var textBox3 = document.createElement("input");
			var textBox4 = document.createElement("input");
			var textBox5 = document.createElement("textarea");
			var textBox6 = document.createElement("input");
			var deleteButton = document.createElement("img");
			
			$(textBox1).attr("type", "text");
			$(textBox1).attr("name", "rvalue[]");
			$(textBox1).attr("size", 4);
			$(textBox1).css("margin-top", 8);

			$(textBox2).attr("type", "text");
			$(textBox2).attr("name", "riskGradeFm[]");
			$(textBox2).attr("class", "riskGradeFm");
			$(textBox2).attr("size", 4);

			$(textBox3).attr("type", "text");
			$(textBox3).attr("name", "riskGradeTo[]");
			$(textBox3).attr("class", "riskGradeTo");
			$(textBox3).attr("size", 4);

			$(textBox4).attr("type", "text");
			$(textBox4).attr("name", "rclassName[]");
			$(textBox4).css("margin-top", 8);

			$(textBox5).attr("type", "text");
			$(textBox5).attr("name", "correctiveMeasure[]");

			$(textBox6).attr("type", "text");
			$(textBox6).attr("name", "color[]");
			$(textBox6).attr("value", "#ffffff");
			$(textBox6).attr("class", "selectColor"+risk);
			
			$(deleteButton).attr("src", "view/style/images/btn_tab03.jpg");
			$(deleteButton).attr("class", "hover1");
			$(deleteButton).attr("alt", "삭제");
			$(deleteButton).css("margin-top", 4);
			
			$(li1).append(textBox1);
			$(li2).append(textBox2);
			$(li2).css("margin-top", 8);
			$(li2).append("이상 ");
			$(li2).append(textBox3);
			$(li2).append("이하");
			$(li3).append(textBox4);
			$(li4).append(textBox5);
			$(li5).append(deleteButton);
			$(li6).append(textBox6);
					
			$("#itemR").append(li1);
			$("#itemR2").append(li2);
			$("#itemR3").append(li3);
			$("#itemR4").append(li4);
			$("#itemR5").append(li5);
			$("#itemR6").append(li6);
			selectColor(".selectColor"+risk);
			
			$(deleteButton).bind("click", function(){
				$(li1).remove();
				$(li2).remove();
				$(li3).remove();
				$(li4).remove();
				$(li5).remove();
				$(li6).remove();
			});
			
		}

	function deleteTr(c,id){
		$(".old_"+c+id).hide();
		$("#"+c+id).val("1");
	}

	function startDateCheck(){
		$.ajax({
			url : "risk.category.do?action=startDateCheck",
			dataType : "html",
			type : "post",
			data : {
				"articleId" : $("input[name=backupId]").val(), 
				"startDate" : $("input[name=startDate]").val()+"-01",
				"groupId" : $("input[name=articleId]").val()
			},
			dataType : "json",
			success : function(result) {
				if(result)createItemCategoryGroup();
				else alert("해당 시작일자가 존재합니다.");
			},
			error : function(request, status, error) {
				alert("error");
			},
			complete : function() {
			}
		});
	}

	function fromToCheck(){
		var $Fm = new Array();
		var $To = new Array();
		var check = true;

		$(".likelihoodFm").each(function(){
			if($(this).val()== ''){
				alert("발생가능성의 범위는 필수 값입니다.");
				check = false;
			}
			$Fm.push($(this).val());
		});
		if(check){
			$(".likelihoodTo").each(function(){
				if($(this).val()== ''){
					alert("발생가능성의 범위는 필수 값입니다.");
					check = false;
				}
				$To.push($(this).val());
			}); 
		}
		
		for(var i=0;i<$To.length;i++){
			if(parseFloat($Fm[i]) > parseFloat($To[i])){
				alert("발생가능성의 범위 지정이 잘못되었습니다.");
				return;
			}
		}
		
		$Fm = [];
		$To = [];

		if(check){
			$(".riskGradeFm").each(function(){
				if($(this).val()== ''){
					alert("위험도 점수 범위는 필수 값입니다.");
					check = false;
				}
				$Fm.push($(this).val());
			});
		}
		if(check){
			$(".riskGradeTo").each(function(){
				if($(this).val()== ''){
					alert("위험도 점수 범위는 필수 값입니다.");
					check = false;
				}
				$To.push($(this).val());
			}); 
		}
		
		for(var i=0;i<$To.length;i++){
			if(parseFloat($Fm[i]) > parseFloat($To[i])){
				alert("위험도 점수 범위 지정이 잘못되었습니다.");
				return;
			}
		}
		if(check){
			startDateCheck();
		}	
	}
 	</script>
 