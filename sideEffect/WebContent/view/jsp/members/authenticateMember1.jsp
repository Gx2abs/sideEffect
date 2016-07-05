<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div class="loginPage" style="background-image:url('view/style/images/login/login_bg_01.jpg'); text-align: center;">
 	<%-- <img src="${titleImg }" /> --%>
 	<form name="countryCreateForm1"  method="POST" action="authenticator.do">
 		<input type="hidden" name="action" value="authenticateMember"/>
	 	<table class="loginTable" >
	 		<colgroup>
				<col width="25%" />
				<col width="45%" />
				<col width="30%" />
			</colgroup>
			<tbody>
				<tr>
					<td class="tdHead"><img src="view/style/images/login/tit_mail.jpg"></td>
					<td class="tdBody">
						<input type="text" name="accountName" />
					</td>
					<td rowspan="2">
						<img src="view/style/images/login/bt_login.jpg" id="submit1" alt="확인" onclick="create();"/>
					</td>
					 
				</tr>
				<tr>
					<td class="tdHead"><img src="view/style/images/login/tit_pw.jpg"></td>
					<td class="tdBody">
						<input type="password" name="password" id="password"/>
					</td>
					 
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		
	 		<!-- <img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();"/> -->
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function create(){
 		
 			if($("input[name=accountName]").val()==""){
 				alert("아이디를 입력해 주세요.");	
 				return false;
 			}
 			
 			if($("#password").val()==""){
 				alert("비밀번호를 입력해 주세요.");	
 				return false;
 			}
 			
 				$("form[name=countryCreateForm1]").submit();
 		 
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			bind13();
 		})
 		
 		function bind13(){
 			$("input[name=password]").bind("keydown", function(e){
 				if(e.keyCode == 13){
 					create();	
 				}
 			});
 		}
 		 
 	 
 	 
 	</script>
 