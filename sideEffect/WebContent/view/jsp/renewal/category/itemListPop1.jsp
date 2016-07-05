<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="margin-top: 30px;">
 	<img src="${titleImg }" />
 	
 	<form method="get" name="itemListForm1" action="properties.do" style="width: 500px;">
 			<input type="hidden" value="${param.action }" name="action"/><br/>
 			<input type="hidden" value="${param.pg }" name="pg" /><br/>
 			<input type="hidden" value="${param.menu }" name="menu"/><br/>
 		
 		<div>
 			<select name="searchColumn">
 				<option value="setMea_class_no">품목코드</option>
 				<option value="setClass_kor_name">품목명</option>
 			</select>
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
 			<c:forEach var="codeAge" items="${itemCodeTypes }" begin="0" end="2">
 				<c:choose>
 					<c:when test="${codeAge.id eq 0 || param.codeAge eq codeAge.id }">
 						<input name="codeAge" type="radio" value="${codeAge.id }" checked="checked">${codeAge.propertyValue }
 					</c:when>
 					<c:otherwise>
 						<input name="codeAge" type="radio" value="${codeAge.id }">${codeAge.propertyValue }
 					</c:otherwise>
 				</c:choose>
 			</c:forEach>
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 		</div><br>
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="82" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목코드</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">코드구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
		 		<tr class="listover" onMouseOver="this.style.backgroundColor='#F6F6F6'" onMouseOut="this.style.backgroundColor='#FFFFFF'" onclick="winClose('${item.compositeKey.mea_class_no}', '${item.gradeObj.id }');" >
				 		<td colspan="2">
				 			${item.class_level }
				 		</td>
				 		<td class="subject" colspan="2">
				 			${item.compositeKey.mea_class_no }
				 		</td>
				 		<td colspan="2">
				 			${item.class_kor_name }
				 		</td>
				 		<td colspan="2">${item.code_age.propertyValue }</td>
				 		<td colspan="2">${item.gradeObj.propertyValue }</td>
				 	</tr>
				 </c:forEach>
		 	</c:when>
		 	<c:otherwise>
		 		<tr>
		 			<td>no data</td>
				</tr>
			</c:otherwise>
		 </c:choose>
 	</table>
 	
 	<div class="paging" style="min-width: 0">
 		${requestScope.pageString}
 	</div>
 	</form> 	
 	</div>
 
<div style="float: right;">
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();"/>
</div>
 
 <script type="text/javascript">
 
 	$(document).ready(function() {
   		 $('tr').click(function(e) {
       		 e.preventDefault();
        		$('tr').each(function() {
         	 	  $(this).removeClass('selected');
        		});
        		$(this).addClass('selected');
    		});
    		$(document).mousedown(function(){ 
        		$('.selected').removeClass('selected'); 
    		}); 
		});	
 
 	function winClose(id, grade){
 		opener.document.productCreateForm1.item_id.value = id;
 		opener.document.productCreateForm1.item_grade.value = grade;
 		window.close();
 	}
 	
	function cancel(){
 		window.close();
	}
 </script>
 