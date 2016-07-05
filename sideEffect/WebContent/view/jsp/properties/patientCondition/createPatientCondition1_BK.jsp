<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%; overflow-y: scroll;">
 	<form name="patientConditionCreateForm1"  method="POST" action="properties.do">
 		<input type="hidden" name="level" value="${param.level }"/>
 		<input type="hidden" name="action" value="createPatientCondition"/>
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
					<th colspan="5">공통 코드 관리 - 환자문제 코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"  colspan="3">Level ${param.level }</td>
				</tr>
			
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >Level </th>
					<td colspan="3">
							<c:choose>
								<c:when test="${!empty requestScope.level1List  }">
									<select name="parentItem" id="parentItem">
									<c:forEach var="lv1Item" items="${level1List }">
										<%-- <option value="${lv1Item.mea_class_no }">${lv1Item.class_kor_name }</option> --%>
										<option value="${lv1Item.id }">${lv1Item.fdaSourcePtKor }</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							
							<div  id="childItemTd" ></div>
							
							<%-- <c:choose>
								<c:when test="${!empty requestScope.level2List  }">
									<select name="parentItem2" >
									<c:forEach var="lv2Item" items="${level2List }">
										<option value="${lv2Item.id }">${lv2Item.depthLevel }</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${!empty requestScope.level3List  }">
									<select name="parentItem3" >
									<c:forEach var="lv3Item" items="${level3List }">
										<option value="${lv3Item.id }">${lv3Item.depthLevel }</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${!empty requestScope.level4List  }">
									<select name="parentItem4" >
									<c:forEach var="lv4Item" items="${level4List }">
										<option value="${lv4Item.id }">${lv4Item.depthLevel }</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${!empty requestScope.level5List  }">
									<select name="parentItem5" >
									<c:forEach var="lv5Item" items="${level5List }">
										<option value="${lv5Item.id }">${lv5Item.depthLevel }</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose> --%>
						</td>
				</tr>
				
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA Code</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaCode" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA Source PT(Korean)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaSourcePtKor" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA Source PT(English)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="name" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA  Source Definition(Korean)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaSourceDefinitionKor" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA  Source Definition(English)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaSourceDefinition" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCI Code</th>
					<td class="tdBody"  colspan="3"><input type="text" name="nciCode" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCIt Definition (Korean) Final</th>
					<td class="tdBody"  colspan="3"><input type="text" name="ncitDefinitionKor" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCIt Definition</th>
					<td class="tdBody"  colspan="3"><input type="text" name="ncitDefinition" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 사항</th>
					<th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified" name="lastModified"/></td>
					<th class="tdHead" >적용일자</th>
					<td class="tdBody" ><input type="text" id="activeFrom" name="activeFrom"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >구분</th>
					<td class="tdBody" colspan="3">
						<select name="historyType" >
							<c:choose>
								<c:when test="${!empty requestScope.historyType  }">
									<c:forEach var="hType" items="${historyType }">
										<option  value="${hType.id }">${hType.propertyValue }</option>
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
					<td class="tdBody" colspan="3"><input type="text" name="historyManager" /></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" >사유</th>
					<td class="tdBody" colspan="3"><textarea name="historyDescription"></textarea></td>
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
	 	
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 			$("form[name=patientConditionCreateForm1]").submit();			
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
 			$("#lastModified").datepicker("option", "dateFormat", "yy-mm-dd");
 			$("#activeFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 			
 			bindParentItemGrade();
 		}
 		
 		function bindParentItemGrade(){
 			$("select[name=parentItem]").bind("mouseup", function(){
 				setParentItemGrade();
 			});
 			
 			$("select[name=parentItem2]").bind("mouseup", function(){
 				setParentItemGrade2();
 			});
 			
 			/* $("select[name=parentItem3]").bind("mouseup", function(){
 				setParentItemGrade3();
 			}); */
 		}
 		
 		function setParentItemGrade(){
 			var node = $("select[name=parentItem]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var id = $("#parentItem option:selected").val();
			var level = $("input[name=level]").val();
			
			if(level=="3"){
				getChildList(id, level);	
			}
			if(level=="4"){
				getChildList(id, level);	
			}
 		}
 		
 		function setParentItemGrade2(){
 			var node = $("select[name=parentItem2]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var id = $("#parentItem2 option:selected").val();
			var level = $("input[name=level]").val();
			
			//alert(id);
			if(level=="3"){
				getChildList(id, level);	
			}
			if(level=="4"){
				getChildList(id, level);	
			}
 		}
 		
 		function setParentItemGrade3(){
 			var node = $("select[name=parentItem2]");
 			var selectedIndex = $(node).prop("selectedIndex");
			//var targetNode = $("select[name=parentItemGrade2]");
			//$(targetNode).prop("selectedIndex", selectedIndex);
 			var id = $("#parentItem3 option:selected").val();
 			var level = $("input[name=level]").val();
 			/* if(level=="3"){
				getChildList(id);	
			} */
 		}
 		
 		function getChildList(id, level){
 			
 			var xhr = new XMLHttpRequest();
 			
 			var method = "GET";
 			var url = "properties.do?action=getChildPatientCondition&parentItem="+id+"&level="+level;
 			var isAsync = true;
 			xhr.open(method, url, isAsync);
 			xhr.send();
 			
 			xhr.onreadystatechange = function(){
 				
 				if(xhr.readyState==4 && xhr.status==200){
 					//alert(xhr.responseText);
 					var target = $("#childItemTd");
 					target.html(xhr.responseText);
 					//after dom generation
 					$("select[name=parentItem2]").bind("mouseup", function(){
 		 				setParentItemGrade2();
 		 			});
 				}
 				
 			};
 			
 			
 		}
 	</script>
 