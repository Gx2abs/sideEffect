<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 
 <script type="text/javascript">
 
 	function duplicate(){
 		var frm = document.medicalDeviceCreateForm1;
 		var fdaCode = frm.fdaCode.value;
 		if(fdaCode=="" || fdaCode.length==0){
 			alert("MFDS Code를 입력해주세요.");
 			return;
 		}
 		var xmlHttpRequest = new XMLHttpRequest();
 		
 		 var method = "POST";
 		 var url = "properties.do?action=fdaCodeDuplicate&fdaCode="+fdaCode+"&table=MEDICAL_DEVICE_MALFUNCTION_CODE";
 		 var async = true;
 		 
 		 xmlHttpRequest.open(method, url, async);
 		 xmlHttpRequest.send();
 		 xmlHttpRequest.onreadystatechange = function(){
 					
 					if ( xmlHttpRequest.status ==200 && xmlHttpRequest.readyState ==4){
 						//alert(xmlHttpRequest.responseText);
 						
 						if(xmlHttpRequest.responseText > 0){
 							frm.check.value=0;
 							alert("이미 사용중인 코드 입니다.");
 							return;
 						}else{
 							alert("사용가능한 코드 입니다.");
 							frm.check.value=1;
 							return;
 						}
 						
 					} 
 				};
 		
 	}
 	
 </script>
 
   	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="medicalDeviceCreateForm1"  method="POST" action="properties.do">
 		<input type="hidden" name="level" value="${param.level }"/>
 		<input type="hidden" name="action" value="createMedicalDevice"/>
 		<input type="hidden" name="check" value="0" />
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
					<th colspan="5">공통 코드 관리 - 의료기기문제 코드</th>
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
						<br><b>* 등록된 하위 Level이 있는 경우에만 상위 Level 리스트가 보여집니다.</b><br><br>
							<c:choose>
								<c:when test="${!empty requestScope.level1List  }">
									<select name="parentItem" id="parentItem">
									<option value=""> - LEVEL 1 선택 - </option>
									<c:forEach var="lv1Item" items="${level1List }">
										<%-- <option value="${lv1Item.id }">${lv1Item.id } ${lv1Item.fdaSourcePtKor }</option> --%>
										<option value="${lv1Item.id }">[${lv1Item.fdaCode}] ${lv1Item.fdaSourcePtKor }</option>
									</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							
							<!-- <div  id="childItemTd" ></div> -->
							
							<c:choose>
								<c:when test="${param.level eq 3  }">
									<div  id="childItemTd2" ></div>
								</c:when>
								<c:when test="${param.level eq 4  }">
									<div  id="childItemTd2" ></div>
									<div  id="childItemTd3" ></div>
								</c:when>
								<c:when test="${param.level eq 5  }">
									<div  id="childItemTd2" ></div>
									<div  id="childItemTd3" ></div>
									<div  id="childItemTd4" ></div>
								</c:when>
								<c:when test="${param.level eq 6  }">
									<div  id="childItemTd2" ></div>
									<div  id="childItemTd3" ></div>
									<div  id="childItemTd4" ></div>
									<div  id="childItemTd5" ></div>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							
						</td>
				</tr>
				
				<tr class="itemProperty">
					<!-- <th class="tdHead" colspan="2">FDA Code</th> -->
					<th class="tdHead" colspan="2">MFDS Code</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaCode" />
					<input type="button" value="중복검사" onclick="duplicate()"/>
					</td>
				</tr>
				<tr class="itemProperty">
					<!-- <th class="tdHead" colspan="2">FDA Source PT(Korean)</th> -->
					<th class="tdHead" colspan="2">MFDS Source PT(Korean)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="fdaSourcePtKor"  size="70"/></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">FDA Source PT(English)</th>
					<td class="tdBody"  colspan="3"><input type="text" name="name"  size="70"/></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<!-- <th class="tdHead" colspan="2">FDA  Source Definition(Korean)</th> -->
					<th class="tdHead" colspan="2">MFDS  Source Definition(Korean)</th>
					<!-- <td class="tdBody"  colspan="3"><input type="text" name="fdaSourceDefinitionKor" /></td> -->
					<td class="tdBody"  colspan="3"><textarea name="fdaSourceDefinitionKor"/></textarea></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">FDA  Source Definition(English)</th>
					<!-- <td class="tdBody"  colspan="3"><input type="text" name="fdaSourceDefinition" /></td> -->
					<td class="tdBody"  colspan="3"><textarea name="fdaSourceDefinition"/></textarea></td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">NCI Code</th>
					<td class="tdBody"  colspan="3"><input type="text" name="nciCode" /></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">NCIt Definition (Korean)</th>
					<!-- <td class="tdBody"  colspan="3"><input type="text" name="ncitDefinitionKor" /></td> -->
					<td class="tdBody"  colspan="3"><textarea name="ncitDefinitionKor"/></textarea></td>
				</tr>
				<tr class="itemProperty createTextarea">
					<th class="tdHead" colspan="2">NCIt Definition</th>
					<!-- <td class="tdBody"  colspan="3"><input type="text" name="ncitDefinition" /></td> -->
					<td class="tdBody"  colspan="3"><textarea name="ncitDefinition" size="70"/></textarea></td>
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
					<td class="tdBody" colspan="3"><input type="hidden" name="historyManager" value="${sessionScope.user.accountName}" />${sessionScope.user.accountName}</td>
				</tr>
				<tr class="itemProperty createTextarea">
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
	 	
	 	<div class="btnType3">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 			if(document.medicalDeviceCreateForm1.check.value==0){
 				alert("MFDS Code 중복검사를 해주세요.");
 				return;
 			}
 			$("form[name=medicalDeviceCreateForm1]").submit();			
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
 			$("#activeFrom").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
 			
 			bindParentItemGrade();
 		}
 		
 		function bindParentItemGrade(){
 			//$("select[name=parentItem]").bind("mouseup", function(){
 			$("select[name=parentItem]").bind("change", function(){
 				
 				setParentItemGrade();
 			});
 			
 			//$("select[name=parentItem2]").bind("mouseup", function(){
 			//	setParentItemGrade2();
 			//});
 			
 			/* $("select[name=parentItem3]").bind("mouseup", function(){
 				setParentItemGrade3();
 			}); */
 		}
 		
 		function setParentItemGrade(){
 			var node = $("select[name=parentItem]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var id = $("#parentItem option:selected").val();
			var level = $("input[name=level]").val();
			var step = 2;
			
			getChildList2(id, level, step);
		}
 		
 		function setParentItemGrade2(){
 			var node = $("select[name=parentItem2]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var id = $("#parentItem2 option:selected").val();
			var level = $("input[name=level]").val();
			var step = 3;
			
			getChildList3(id, level, step);	
		}
 		
 		function setParentItemGrade3(){
 			var node = $("select[name=parentItem3]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var id = $("#parentItem3 option:selected").val();
 			var level = $("input[name=level]").val();
			var step = 4;
			
			getChildList4(id, level, step);	
		}
 		
 		function setParentItemGrade4(){
 			var node = $("select[name=parentItem4]");
 			var selectedIndex = $(node).prop("selectedIndex");
			var id = $("#parentItem4 option:selected").val();
 			var level = $("input[name=level]").val();
 			var step = 5;
 			
 			getChildList5(id, level, step);	
		}
 		
 		function getChildList2(id, level, step){
 			var xhr = new XMLHttpRequest();
 			var method = "GET";
 			var url = "properties.do?action=getChildMedicalDevice&parentItem="+id+"&level="+level+"&step="+step;
 			var isAsync = true;
 			xhr.open(method, url, isAsync);
 			xhr.send();
 			xhr.onreadystatechange = function(){
 				
 				if(xhr.readyState==4 && xhr.status==200){
 					//alert(xhr.responseText);
 					var target = $("#childItemTd2");
 					target.html(xhr.responseText);
 					
 					//after dom generation
 					$("select[name=parentItem2]").bind("change", function(){
 		 				setParentItemGrade2();
 		 			});
 				}
 			};
 		}
 		
		function getChildList3(id, level, step){
	 			var xhr = new XMLHttpRequest();
	 			var method = "GET";
	 			var url = "properties.do?action=getChildMedicalDevice&parentItem2="+id+"&level="+level+"&step="+step;
	 			var isAsync = true;
	 			xhr.open(method, url, isAsync);
	 			xhr.send();
	 			xhr.onreadystatechange = function(){
	 				
	 				if(xhr.readyState==4 && xhr.status==200){
	 					//alert(xhr.responseText);
	 					var target = $("#childItemTd3");
	 					target.html(xhr.responseText);
	 					
	 					//after dom generation
	 					$("select[name=parentItem3]").bind("change", function(){
	 		 				setParentItemGrade3();
	 		 			});
	 				}
	 			};
	 		}
	
		function getChildList4(id, level, step){
				var xhr = new XMLHttpRequest();
				var method = "GET";
				var url = "properties.do?action=getChildMedicalDevice&parentItem3="+id+"&level="+level+"&step="+step;
				var isAsync = true;
				xhr.open(method, url, isAsync);
				xhr.send();
				xhr.onreadystatechange = function(){
					
					if(xhr.readyState==4 && xhr.status==200){
						//alert(xhr.responseText);
						var target = $("#childItemTd4");
						target.html(xhr.responseText);
						
						//after dom generation
						$("select[name=parentItem4]").bind("change", function(){
	 		 				setParentItemGrade4();
	 		 			});
					}
				};
			}
	
		function getChildList5(id, level, step){
			var xhr = new XMLHttpRequest();
			var method = "GET";
			var url = "properties.do?action=getChildMedicalDevice&parentItem4="+id+"&level="+level+"&step="+step;
			var isAsync = true;
			xhr.open(method, url, isAsync);
			xhr.send();
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState==4 && xhr.status==200){
					//alert(xhr.responseText);
					var target = $("#childItemTd5");
					target.html(xhr.responseText);
				}
			};
		}
 	</script>
 