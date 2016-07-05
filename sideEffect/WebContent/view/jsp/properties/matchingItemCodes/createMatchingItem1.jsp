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
 	<form name="createMatchForm1"  method="post" action="properties.do">
 		<input type="hidden" value=<%=privilegeId %> name="privilegeId" />
 		<input type="hidden" name="action" value="createMatchingItem"/>
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
					<td class="tdBody">
						<input type="text" name="masterCode" readonly="readonly"/>
						<input type="text" name="masterName" readonly="readonly">
						<input type="hidden" name="masterGrade" readonly="readonly">
						<input type="hidden" name="masterId" readonly="readonly">
						<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="" onclick="fnc_openMasterCodeList();" />
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="2" colspan="2" >품목(신코드)</th>
					<td class="tdBody">품목코드(신코드) 품목명(신코드)<img src="view/style/images/btn_tab02.jpg" class="hover1" alt="추가" onclick="fnc_addSlaveCode();" />
					</td>
				</tr>
				<tr>
					<td class="tdBody">
					<ul id="slaveUl">
					<li class="slaveCodeLi"><input type="text" name=slaveCode readonly="readonly"><input type="text" name=slaveName readonly="readonly"><input type="hidden" name=slaveGrade readonly="readonly"><input type="hidden" name=slaveCategoryId readonly="readonly"><img src="view/style/images/bt_search.jpg" alt="검색" class="hover1" onclick="fnc_openSlavesCodeList2()"></li>
					</ul>
					</td>				
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 사항</th>
					<!-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified"  name="lastModified"/></td> -->
					<th class="tdHead" >작성일자</th>
					<td class="tdBody" ><input type="text" id="activeFrom" name="activeFrom"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody">
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
					<td class="tdBody"><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" >사유</th>
					<td class="tdBody"><textarea name="historyDescription"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">사용여부</th>
					<td class="tdBody">
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
 			$("form[name=createMatchForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 		
 			$("#lastModified").datepicker();
 			$("#lastModified").datepicker("option","dateFormat","yy-mm-dd");
 			$("#activeFrom").datepicker();
 			$("#activeFrom").datepicker("option","dateFormat", "yy-mm-dd");
 			$("#activeFrom").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));

 		}
 		
		function fnc_openMasterCodeList(){
 			
 			var url="properties.do?action=listMasterCodes";
 			var name="_blank";
 			var layout = "height=700,width=500";
 			window.open(url,name, layout);
 			
 		}
		
		function fnc_openSlavesCodeList2(){
			var index = 0;
			var url = "properties.do?action=listSlaveCodes&openerIndex="+index;
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
 			console.log("index : "+index);
 			
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
		
 	</script>
 