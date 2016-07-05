<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="itemCreateForm1" method="post" action=itemCategory.do>
 		<input type="hidden" name="level" value="${param.level }"/>
 		<input type="text" name="action" value="createItemCategory"/>
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
					<th colspan="5">공통 코드 관리 - 품목코드</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2" >Level </th>
					<td colspan="3">
							<c:choose>
								<c:when test="${!empty requestScope.level1List  }">
									<select name="parentItemId" id="parentItem">
									<c:forEach var="lv1Item" items="${level1List }">
										<option value="${lv1Item.id }">${lv1Item.class_kor_name }</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						
							<div  id="childItemTd" ></div>
						
						</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">구분</th>
					<td class="tdBody"> Level ${param.level }</td>
					<th class="tdHead">코드 구분</th>
					<td class="tdBody">
						<select name="itemCodeType">
							<c:choose>
								<c:when test="${!empty requestScope.itemCodeTypes  }">
									<c:forEach var="itemCodeType" items="${itemCodeTypes }">
										<option value="${itemCodeType.id }">${itemCodeType.propertyValue }</option>
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
					<th class="tdHead" colspan="2">품목코드</th>
					<td class="tdBody"><input type="text" name="mea_class_no_code" maxlength="9" /></td>
					<th class="tdHead">등급</th>
					<td class="tdBody">
						<select name="itemGrade" >
							<c:choose>
								<c:when test="${!empty requestScope.itemGrades  }">
									<c:forEach var="itemGrade" items="${itemGrades }">
										<option value="${itemGrade.id }">${itemGrade.propertyValue }</option>
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
					<th class="tdHead" colspan="2">품목한글명</th>
					<td class="tdBody" colspan="3"><input type="text" name="itemNameKr" maxlength="20"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">품목영문명</th>
					<td class="tdBody" colspan="3"><input type="text" name="itemNameEn"  maxlength="20"/></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">품목 설명</th>
					<td class="tdBody" colspan="3"><textarea name="itemDesc" maxlength="200"></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">추적관리대상여부</th>
					<td class="tdBody" colspan="3">
						<select name="traceability" >
							<c:choose>
								<c:when test="${!empty requestScope.traceabilityList  }">
									<c:forEach var="traceability" items="${traceabilityList }">
										<option value="${traceability.id }">${traceability.propertyValue }</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option>no data</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">UDI CODE</th>
					<td class="tdBody" colspan="3"><input type="text" name="udiCode" maxlength="9"></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" rowspan="4">이력 현황</th>
					<!-- <th class="tdHead" >변경일자</th>
					<td class="tdBody" ><input type="text" id="lastModified" name="lastModified"/></td> -->
					<th class="tdHead" >적용일자</th>
					<td class="tdBody" colspan="3"><input type="text" id="activeFrom" name="activeFrom"/></td>
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
					<td class="tdBody" colspan="3">${sessionScope.user.accountName }<input type="hidden" name="historyManager" value="${sessionScope.user.accountName }" readonly="readonly"/></td>
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
 	/* 
 		function create(){
 			
 			var mea_class_no_code = $("input[name=mea_class_no_code]").val();
 			var grade = $("select[name=itemGrade] option:selected").val();
 			
 			if(isUnique(mea_class_no_code, grade)){
 				alert("code unique");
 				$("form[name=itemCreateForm1]").submit();	
 			}else{
 				
 			}
 						
 		}
 		 */
 		 
 		function create(){
  			
  			//var mea_class_no_code = $("input[name=mea_class_no_code]").val();
  			//var grade = $("select[name=itemGrade] option:selected").val();
  			
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
 		}
 		
 		function setParentItemGrade(){
 			var node = $("select[name=parentItem]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var targetNode = $("select[name=parentItemGrade]");
			$(targetNode).prop("selectedIndex", selectedIndex);

			var mea_class_no = $("#parentItem option:selected").val();
			var grade = $("#parentItemGrade option:selected").val();
			
			var level = $("input[name=level]").val();
			
			if(level=="3"){
				getChildList(mea_class_no, grade);	
			}
 			
 		}
 		
 		function setParentItemGrade2(){
 			var node = $("select[name=parentItem2]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var targetNode = $("select[name=parentItemGrade2]");
			$(targetNode).prop("selectedIndex", selectedIndex);

 			
 		}
 		
 		function getChildList(mcn, grade){
 			
 			var xhr = new XMLHttpRequest();
 			
 			var method = "GET";
 			var url = "properties.do?action=getChildMCN&parentItem="+mcn+"&parentItemGrade="+grade;
 			var isAsync = true;
 			xhr.open(method, url, isAsync);
 			xhr.send();
 			
 			xhr.onreadystatechange = function(){
 				
 				if(xhr.readyState==4 && xhr.status==200){
 					//alert(xhr.responseText);
 					var target = $("#childItemTd");
 					target.html(xhr.responseText);
 				}
 				
 			};
 			
 			
 		}
 		/* 
 		function isUnique(itemCode, itemGrade){
 			
 			var xhr = new XMLHttpRequest();
 			var returnValue = false;
 			var method = "get";
 			var url = "properties.do?action=isUniqueItemCode&itemCode="+itemCode+"&itemGrade="+itemGrade;
 			var isAsync = true;
 			
 			xhr.open(method, url, isAsync);
 			xhr.send();
 			
 			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == 4 ){
					//response ready
					
					if(xhr.status ==200){
						//HTTP status 200 OK
						var responseText = xhr.responseText;
						//alert("responseText : " + responseText);
						if(responseText==1){
							$("form[name=itemCreateForm1]").submit();
							returnValue = true;
						}else{
							alert("Specified item code exists.");
							return false;
						}
					}else{
						alert("Server returned error.");
						return false;
					}
					
				}else{
					
				}
 				
 			};
 			 return returnValue;
 		}
 		 */
 	</script>
 