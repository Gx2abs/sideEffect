<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="updateMemberForm1" method="POST" action="members.do">
 		<input type="hidden" name="action" value="updateMember"/>
 		<input type="hidden" name="articleId" value="${article.id }"/>
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
						<input type="hidden" value="${article.accountName }" name="accountName">
					</td >
					<th class="tdHead">담당자</th>
					<td class="tdBody"   >
					<input type="text" name="manager" value="${article.manager  }"/>
					</td>
				</tr>
				 <tr class="itemProperty">
					<th class="tdHead">소속</th>
					<td class="tdBody" >
					<input type="text" name="organisation" value="${ article.organisation}"/>
					</td>
					<th class="tdHead">등급</th>
					<td class="tdBody"   >
						<select   name="privilege" >
							<c:choose>
								<c:when test="${article.privilegeId eq 1}">
									<option value="1" selected="selected">일반사용자</option>
									<option value="2">관리자</option>
								</c:when>
								<c:otherwise>
									<option value="1">일반사용자</option>
									<option value="2" selected="selected">관리자</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">연락처</th>
					<td class="tdBody" >
					<input type="text" name="telephoneNumber" value="${article.phoneNumber }"/>
					</td>
					<th class="tdHead">이메일</th>
					<td class="tdBody"   >
					<input type="text" name="emailUserName" value="${article.emailUserName }"/>@<input type="text" name="emailDomain" value="${article.emailDomain}"/>
						
					</td>
				</tr>
				<tr class="itemProperty">
					<th class="tdHead">비밀번호</th>
					<td class="tdBody" >
						<input type="password" name="password" id="password" value="${article.memberPassword }"/>
					</td>
					<th class="tdHead">비밀번호 반복</th>
					<td class="tdBody"   >
						<input type="password" name="passwordRepeat" id="passwordRepeat" value="${article.memberPassword }"/>
					</td>
				</tr>
			</tbody>
 	</table>
	 	
	 	</form>
	 	
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" class="buttonised" onclick="updateMember();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" class="buttonised" onclick="cancel();"/>
	 	</div>
	 	
 	</div>
 	
 	<script>
 	 
 		function updateMember(){
 			$("form[name=updateMemberForm1]").submit();			
 		}
 		
 		function cancel(){
 			history.back();
 		}
 		
 		
 		
 	</script>
 