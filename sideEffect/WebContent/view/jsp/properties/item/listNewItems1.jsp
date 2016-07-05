<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 		<div style="margin: 30px 0 0 5px;">
 		<img src="${titleImg }" />
 	<form method="get" name="itemListForm1" action="properties.do" style="width: 500px;">
 			<input type="hidden" value="${param.action }" name="action"/><br/>
 			<input type="hidden" value="${param.pg }" name="pg" /><br/>
 			<input type="hidden" value="${param.menu }" name="menu"/><br/>
 			<input type="hidden" name="openerIndex" value="${param.index }"/><br/>
 		
 		<div>
 			<select name="searchColumn">
 				<option value="setClass_kor_name">품목명</option>
 				<option value="setMea_class_no">품목코드</option>
 			</select>
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 		</div><br>
 	
 	<div style="float:left;">
 	</div>
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">분류</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목코드</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="180" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="70" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">코드구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="70" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onMouseOver="this.style.backgroundColor='#F6F6F6'" onMouseOut="this.style.backgroundColor='#FFFFFF'" onclick="send('${item.compositeKey.mea_class_no}', '${item.class_kor_name }', ${item.grade });" >
				 		<td colspan="2">
				 			${item.meaClassNoLevel.propertyValue }
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
 		${requestScope.pageString }
 	</div>
 	
 	</form>
 	</div>
 
 <div class="btnType3">
	 		<img src="view/style/images/bt_cancel.jpg" class="hover1" id="cancel1" alt="취소" onclick="cancel();"/>
</div>
 
 <script type="text/javascript" >
 	function cancel(){
		window.close();
	}
 	function send(mea_class_no, slaveName, grade){
 		try{
 			var openerIndex = $("input[name=openerIndex]").val();
 			
 			var slaveCodeList = $("input[name=slaveCode]", window.opener.document);
 			var slaveCodeNode = $(slaveCodeList).get(openerIndex);
 			$(slaveCodeNode).val(mea_class_no);
 			
 			var slaveNameList = $("input[name=slaveName]", window.opener.document);
 			var slaveNameNode =$(slaveNameList).get(openerIndex); 
 			$(slaveNameNode).val(slaveName);
 			
 			var slaveGradeList = $("input[name=slaveGrade]", window.opener.document);
 			var slaveGradeNode =$(slaveGradeList).get(openerIndex); 
 			$(slaveGradeNode).val(grade);
 			
 			window.close();
 					 			
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	function create(level){
 		location.href="properties.do?action=createItemPage&level="+level;
 		
 	}
 
 	$(document).ready(function(){
 		initItemList1Jsp();
 	});
 	
 	function initItemList1Jsp(){
 		
 		$("#getSearchButton1").bind("click", function(){
 			itemListForm1Submit();
 		});
 		
 	}
 	
 	function itemListForm1Submit(){
 		
 		$("form[name=itemListForm1]").submit();
 		
 	}
 	
 	
 	
 </script>
 
 