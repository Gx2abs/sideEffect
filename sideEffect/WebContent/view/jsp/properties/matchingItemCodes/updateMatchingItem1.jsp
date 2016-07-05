<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 	<form name="codeMatcingUpdateForm1" method="post" action="properties.do">
 		<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
 		<input type="hidden" name="action" value="updateMatch"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="10%" />
				<col width="80%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="3">공통 코드 관리 - 신·구 품목 매칭 관리</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >기준코드(구코드)</th>
					<td class="tdBody" >
						<input type="text" readonly="readonly" name="masterCode" value="${article.masterCode }" />
						<input type="text" readonly="readonly" name="masterName" value="${article.masterName }" />
						<input type="hidden" readonly="readonly" name="masterGrade" value="${article.masterGrade }" />
						<input type="hidden" readonly="readonly" name="masterId" value="${article.id }" />
					<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="fnc_openMasterCodeList();" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="2" colspan="2" >품목(신코드)</th>
					
				</tr>
				
				<tr>
					<td class="tdBody" >
						<ul id="slaveUl">
							<li>
								<table>
									<tr>
										<th>품목코드</th>
										<th>품목명</th>
										<th colspan="2">등급</th>
									</tr>
								<c:choose>
								<c:when test="${!empty article.matchSlaves }">
									<c:forEach var="matchSlave" items="${article.matchSlaves }" varStatus="i">
										<li class="slaveCodeLi">							
											<tr>
												<td>${matchSlave.slaveCode }</td>
												<td>${matchSlave.slaveObject.class_kor_name }</td>
												<td>${matchSlave.slaveGrade }
													<input type="hidden" name="slaveId" value="${matchSlave.id }" readonly="readonly">
													<input type="hidden" name="slaveName" value="${matchSlave.slaveCode }" readonly="readonly"> 
													<input type="hidden" name="slaveCode" value="${matchSlave.slaveCode }" readonly="readonly">
													<input type="hidden" name="slaveGrade"  value="${matchSlave.slaveGrade }" readonly="readonly">
													<input type="hidden" name="slaveCategoryId"  value="${matchSlave.slave_category_id }" readonly="readonly">
												</td>
												<td id="slaveCode_${matchSlave.id}"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="삭제" name="del" onclick="deleteSlave('${matchSlave.id}','<%=privilegeId %>')">
												</td>			
													
												
											</tr>
										</li>
									</c:forEach>
								</c:when>	
						</c:choose>
								</table><br/><br/>
								품목코드(신코드) 품목명(신코드)
								<img src="view/style/images/btn_tab02.jpg" class="hover1" alt="추가" id="addSlaveCode" onclick="fnc_addSlaveCode();" /><!-- <input type="button" value="삭제" id="" onclick="" /> -->
							</li>
						</ul>
					</td>				
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
					<td class="tdBody" ><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody" ><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">현재 이력</th>
					<td class="tdBody" >
						<table class="his_table">
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
							<c:when test="${!empty article.matchHistory }">
								<c:forEach var="matchHistory" items="${article.matchHistory }">
						<tr class="itemProperty">
							<%-- <td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${matchHistory.lastModified }"/>
							</td> --%>
							<td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${matchHistory.activeFrom }"/>
							</td>
							<td class="tdBody">
							<c:choose>
								<c:when test="${!empty requestScope.historyType }">
									<c:forEach var="hType" items="${historyType }">
										<c:choose>
											<c:when test="${matchHistory.historyType eq hType.id }">
												${hType.propertyValue }
											</c:when>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
							</td>
							<td>
							<c:choose>
								<c:when test="${!empty article.matchHistory }">
										${matchHistory.manager }
								</c:when>
							</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${!empty matchHistory.historyDescription }">
									<textarea disabled="disabled" name="historyDescription"/>${matchHistory.historyDescription }</textarea>
									</c:when>
								<c:otherwise>
									<textarea disabled="disabled" name="historyDescription"/>no record</textarea>
								</c:otherwise>
								</c:choose>
							</td>
							<td id="history${matchHistory.id }"><img src="view/style/images/btn_tab03.jpg" class="hover1" alt="이력삭제" onclick="deleteHistory('${matchHistory.id}','<%=privilegeId %>');"/></td>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
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
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick=""/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	
 		function create(){
 			$("form[name=codeMatcingUpdateForm1]").submit();			
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
 			
 			$("#lastModified").datepicker("option","dateFormat","yy-mm-dd");
 			$("#activeFrom").datepicker("option","dateFormat","yy-mm-dd");
 			
 		}

		function fnc_openMasterCodeList(){
		
			var url="properties.do?action=listMasterCodes";
			var name="_blank";
			var layout = "height=700,width=500";
			window.open(url,name, layout);
		
		}
		
		function fnc_addSlaveCode(){
 			var li = document.createElement("li");
 			var textBox1 = document.createElement("input");
 			var textBox2 = document.createElement("input");
 			var textBox3 = document.createElement("input");
 			var textBox4 = document.createElement("input");
 			var searchButton1 = document.createElement("img");
 			var deleteButton = document.createElement("img");
 			
 			$(li).attr("class", "slaveCodeLi");
 			
 			$(textBox1).attr("type", "text");
 			$(textBox1).attr("name", "slaveCode");
 			$(textBox1).attr("readonly", "readonly");
 			
 			$(textBox2).attr("type", "text");
 			$(textBox2).attr("name", "slaveName");
 			$(textBox2).attr("readonly", "readonly");
 			
 			$(textBox3).attr("type", "hidden");
 			$(textBox3).attr("name", "slaveGrade");
 			$(textBox3).attr("readonly", "readonly");

 			$(textBox4).attr("type", "hidden");
 			$(textBox4).attr("name", "slaveCategoryId");
 			$(textBox4).attr("readonly", "readonly");
 			
 			$(searchButton1).attr("src", "view/style/images/bt_search.jpg");
 			$(searchButton1).attr("class", "hover1");
 			$(searchButton1).attr("alt", "검색");

 			$(deleteButton).attr("src", "view/style/images/btn_tab03.jpg");
 			$(deleteButton).attr("class", "hover1");
 			$(deleteButton).attr("alt", "삭제");
 			
 			
 			$(li).append(textBox1);
 			$(li).append(textBox2);
 			$(li).append(textBox3);
 			$(li).append(textBox4);
 			$(li).append(searchButton1);
 			$(li).append(deleteButton);
 			
 			
 			$("#slaveUl").append(li);
 			
 			var slaveCodeLiList = $(".slaveCodeLi");
 			
 			var index = $(slaveCodeLiList).index(li);
 			
 			$(searchButton1).bind("click", function(){
 				var url = "properties.do?action=listSlaveCodes&openerIndex="+index;
 				var name = "_blank";
 				var layout = "height=700,width=500";
 				window.open(url, name, layout);
 			});
 			
 			$(deleteButton).bind("click", function(){
 				$(li).remove();
 			});
 			
 		}
		
		/**
			Author : Adam Hun/현한영
			Description :  품목(신코드) 레코드 하나 삭제하는 메소드.
		**/
		function deleteSlave(articleId,privilegeId){
		if(privilegeId>1){
			try{
				
				var xhr = new XMLHttpRequest();
				var url = "properties.do?action=deleteSlave&articleId="+articleId+"&privilegeId="+<%=privilegeId %>;
				var method = "get";
				var isAsync = true;
				xhr.open(method, url, isAsync);
				xhr.send();
				
				xhr.onreadystatechange = function(){
					
					//if the response from the server is available
					if(xhr.readyState ==4 ){
					
						//check if the status was alright
						if(xhr.status == 200){
							
							var XHRResponseText = xhr.responseText;
							
							if(XHRResponseText==1){
								
								var name = "#slaveCode_"+articleId;
								
								$(name).remove();
								
								alert("삭제성공");
							}else{
								alert("삭제실패");	
							}
							
						}else if(xhr.status == 500 || xhr.status == 404){
							alert("삭제실패");
						}
						
												
					}
				
				};
				
			}catch(Exception ){
				alert(Exception.message);
			}
			}else alert("권한이 없습니다.");
		}
		
		function deleteHistory(articleId,privilegeId){
		if(privilegeId>1){
			var method = "get";
			var url = "properties.do?action=deleteHistory&articleId="+articleId+"&privilegeId="+<%=privilegeId %>;
			var isAsynch = true;
			var xhr = new XMLHttpRequest();
			xhr.open(method, url, isAsynch);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				//if response ready
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						var XHRResponseText = xhr.responseText;
						
						if(XHRResponseText==1){
							var id = "#history"+articleId;
							$(id).remove();
							
							alert("삭제성공");
						}else{
							alert("삭제실패");	
						}
					}else{
						alert("삭제실패");
					}
				}
				
			};
		}else alert("권한이 없습니다.");
		}
 	</script>
 