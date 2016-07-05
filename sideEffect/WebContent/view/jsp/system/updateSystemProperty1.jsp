<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="width: 100%; height: 99%;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form name="updateSystemPropertyForm1" method="post" action="system.do">
 		<input type="text" name="action" value="updateSystemProperty"/>
 		<input type="text" name="articleId" value="${article.id }"/>
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
					<th colspan="5">시스템 관리 - 시스템 환경 설정</th>
				</tr>
			</thead>
			<tbody>
				<tr class="itemProperty">
					<th class="tdHead" colspan="2">property name</th>
					<td class="tdBody">
						<input type="text" value="${article.propertyName }" name="propertyName"/>
					</td>
					<th class="tdHead">property value</th>
					<td class="tdBody">
						<input type="text" value="${article.propertyValue }" name="propertyValue"/>MB
					</td>
				</tr>
			</tbody>
 	</table>
	 	</form>
	 	<div style="float: right;">
	 		<img src="view/style/images/btn_ok.jpg" id="submit1" alt="확인" onclick="update();"/>
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();"/>
	 	</div>
 	</div>
 	<script>
  
 		function update(){
 			$("form[name=updateSystemPropertyForm1]").submit();
 		}
 	
 		function cancel(){
 			history.back();
 		}
 	</script>
 