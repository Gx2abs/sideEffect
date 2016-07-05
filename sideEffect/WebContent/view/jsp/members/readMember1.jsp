<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="memberReadForm1"  method="POST" action="members.do">
 		<input type="hidden" name="action" value="updateMemberPage"/>
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
						${article.accountName }
					</td>
					<th class="tdHead">담당자</th>
					<td class="tdBody"   >
						${article.manager }
					</td>
				</tr>
				 <tr class="itemProperty">
					<th class="tdHead">소속</th>
					<td class="tdBody" >
						${article.organisation}
					</td>
					<th class="tdHead">등급</th>
					<td class="tdBody"   >
						<!-- <select   name="privilege" disabled="disabled">
							<option value="1">일반사용자</option>
							<option value="2">관리자</option>
						</select> -->
						<c:choose>
							<c:when test="${article.privilegeId eq 1}">일반사용자</c:when>
							<c:otherwise>관리자</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">연락처</th>
					<td class="tdBody" >
						${article.phoneNumber }
					</td>
					<th class="tdHead">이메일</th>
					<td class="tdBody"   >
						${article.wholeEmail }
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_edit.jpg" name="update" alt="update" class="buttonised" onclick="updateMember(${article.id});"/>
		 	<img src="view/style/images/btn_del.jpg" name="delete" alt="delete" class="buttonised" onclick="del(${article.id});"/>
	 		<img src="view/style/images/bt_list.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 		function update(){
 			$("form[name=memberReadForm1]").submit();			
 		}
 		
 		function updateMember(articleId){
 	  		
 	  		location.href="members.do?action=updateMemberPage&articleId="+articleId;
 	  		
 	  	}
 	  	
 	function del(articleId){
 	  		if(confirm("삭제하시겠습니까?")){
 	  			location.href="members.do?action=deleteMember&articleId="+articleId;	
 	  		}
 	  		
 	  	}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		$(document).ready(function(){
 			
 			initPage();
 			
 		});
 		
 		function initPage(){
 		
 		}
 	</script>
 