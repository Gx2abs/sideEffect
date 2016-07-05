<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="countryCreateForm1"  method="POST" action="members.do">
 		<input type="hidden" name="action" value="createMember"/>
 		<input type="hidden" name="isIdUnique" value="-1"/>
	 	<table class="bordered fitToParent view_table" >
	 		<colgroup>
				<col width="10%" />
				<col width="40%" />
				<col width="10%" />
				<col width="40%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="4">회원관리 - 사용자 관리</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead">아이디</th>
					<td class="tdBody"  >
						<input type="text" name="accountName" /><img src="view/style/images/bt_search_chk.jpg" id="checkUniqueId" alt="아이디체크" class="buttonised" />
					</td>
					<th class="tdHead">담당자</th>
					<td class="tdBody"   >
						<input type="text" name="manager" />
					</td>
				</tr>
				 <tr class="itemProperty">
					<th class="tdHead">소속</th>
					<td class="tdBody" >
						<input type="text" name="organisation" />
					</td>
					<th class="tdHead">등급</th>
					<td class="tdBody"   >
						<select   name="privilege" >
							<option value="1">일반사용자</option>
							<option value="2">관리자</option>
						</select>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">연락처</th>
					<td class="tdBody" >
						<input type="text" name="telephoneNumber" />
					</td>
					<th class="tdHead">이메일</th>
					<td class="tdBody"   >
						<input type="text" name="emailUserName" />@<input type="text" name="emailDomain" />
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">비밀번호</th>
					<td class="tdBody" >
						<input type="password" name="password" id="password"/>
					</td>
					<th class="tdHead">비밀번호 반복</th>
					<td class="tdBody"   >
						<input type="password" name="passwordRepeat" id="passwordRepeat"/>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="create();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 			var console = window.console || {log:function(){}};
 			
 			if(checkPasswordMatch()){
 				
 			}else{
 				alert("두개의 비밀번호가 서로 일치하지 않거나 비밀번호를 입력하지 않았습니다.");
 				return;
 			}
			try{
 				
 				var stringId = $("input[name=accountName]").val();
 				var xhr = new XMLHttpRequest();
 				var method = "get";
 				var url = "members.do?action=checkUniqueId&idToCheck="+stringId;
 				var isAsync = true;
 				
 				xhr.open(method, url, isAsync);
 				xhr.send();
 				xhr.onreadystatechange = function(){
 					if(xhr.readyState==4 && xhr.status==200){
 						if(xhr.responseText==0){
 							console.log("xhr.responseText=\"0\"");
 							$("input[name=isIdUnique]").val(1);
 							$("form[name=countryCreateForm1]").submit();
 							return 1;
 						}else{
 							alert("동일한 아이디가 존재 합니다.");
 							$("input[name=isIdUnique]").val(-1);
 							console.log("xhr.responseText!=\"0\"");
 							return -1;
 						}
 					}
 				};
 				
 			}catch(Exception ){
 				alert(Exception.message);
			}
 			
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
 			bindCheckUniqueId();
 		}
 		
 		function checkPasswordMatch(){
 			
 			var password = $("#password").val();
 			var passwordRepeat = $("#passwordRepeat").val();
 			 
 			if((password==passwordRepeat) 
 					&& (password!="" && passwordRepeat!="")
 					&&(password!="undefined" && passwordRepeat!="undefined")		
 			){
 				return true;
 			}else{
 				return false;
 			}
 		}
 		
 		function bindCheckUniqueId(){
 			
 			$("#checkUniqueId").bind("click", function(){
 				manualCheckUniqueId();
 				
 			});
 			
 		}
 		
 		function manualCheckUniqueId(){
 			
 			var stringId = $("input[name=accountName]").val();
 			
 			
 			if(""==stringId){
 				alert("아이디값을 입력해주세요.");
 				return;
 			}
 			checkUniqueId();
 			
 			/* if(checkUniqueId==0){
 				alert("동일한 ID가 존재 합니다.");
 			}else{
 				alert("이 아이디는 사용하셔도 좋습니다.");
 			} */
 			
 		}
 		function checkUniqueId(){
 			var console = window.console || {log:function(){}};
 			try{
 				
 				var stringId = $("input[name=accountName]").val();
 				var xhr = new XMLHttpRequest();
 				var method = "get";
 				var url = "members.do?action=checkUniqueId&idToCheck="+stringId;
 				var isAsync = true;
 				
 				xhr.open(method, url, isAsync);
 				xhr.send();
 				xhr.onreadystatechange = function(){
 					if(xhr.readyState==4 && xhr.status==200){
 						if(xhr.responseText==0){
 							console.log("xhr.responseText=\"0\"");
 							alert("이 아이디는 사용하셔도 좋습니다.");	
 							$("input[name=isIdUnique]").val(1);
 							return 1;
 							
 						}else{
 							alert("동일한 아이디가 존재 합니다.");
 							$("input[name=isIdUnique]").val(-1);
 							console.log("xhr.responseText!=\"0\"");
 							return -1;
 						}
 					}
 				};
 				
 			}catch(Exception ){
 				alert(Exception.message);
			}
 			
 		}
 	</script>
 