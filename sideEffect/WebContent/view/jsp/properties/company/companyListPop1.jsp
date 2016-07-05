<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div style="margin: 30px 0 0 5px;">
 	
 	 	<img src="${titleImg }" />
 	
 	<form method="get" name="companyListForm1" action="safetyItem.do">
 			<input type="hidden" value="${param.action }" name="action"/><br/>
 			<input type="hidden" value="${param.pg }" name="pg" /><br/>
 			<input type="hidden" value="${param.menu }" name="menu"/><br/>
 	
 	<div>
		<select name="searchColumn">
			<c:choose>
				<c:when test="${param.searchColumn eq 'setEntp_name' }">
					<option selected="selected" value="setEntp_name">업체명</option>
				</c:when>
				<c:otherwise><option value="setEntp_name">업체명</option></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.searchColumn eq 'setCob_flag_code' }">
					<option selected="selected" value="setCob_flag_code">업구분</option>
				</c:when>
				<c:otherwise><option value="setCob_flag_code">업구분</option></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.searchColumn eq 'setMeddev_entp_no' }">
					<option selected="selected" value="setMeddev_entp_no">업허가번호</option>
				</c:when>
				<c:otherwise><option value="setMeddev_entp_no">업허가번호</option></c:otherwise>
			</c:choose>
			<!-- <option value="setEntp_name">업체명</option>
			<option value="setCob_flag_code">업구분</option>
			<option value="setMeddev_entp_no">업허가번호</option> -->
		</select>
		<c:choose>
			<c:when test="${param.searchColumn eq 'setCob_flag_code' and param.searchKeyword eq '1'}">
				<input type="text" name="searchKeyword" value="제조업">	
			</c:when>
			<c:when test="${param.searchColumn eq 'setCob_flag_code' and param.searchKeyword eq '2'}">
				<input type="text" name="searchKeyword" value="수입업">	
			</c:when>
			<c:otherwise>
				<input type="text" name="searchKeyword" value="${param.searchKeyword }">
			</c:otherwise>
		</c:choose>
		
		<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 	</div><br>
 
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="72" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="110" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업허가번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="180" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업체명</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
		 		
		 		
		 		
				 	<tr class="listover" onMouseOver="this.style.backgroundColor='#F6F6F6'" onMouseOut="this.style.backgroundColor='#FFFFFF'" onclick="winClose(${item.id},'${item.meddev_entp_no }','${item.entp_name}')" >
				 		<td colspan="2">
				 			${localCount}
				 			<c:set var="localCount" value="${localCount-1 }"/>
				 		</td>
				 		<td colspan="2">
				 		<c:choose>
				 		<c:when test="${item.cob_flag_code eq '1'}">제조업</c:when>
				 		<c:when test="${item.cob_flag_code eq '2'}">수입업</c:when>
				 		<c:otherwise></c:otherwise>
				 		</c:choose></td><!-- 업구분 -->
				 		<td colspan="2">제${item.meddev_entp_no }호</td><!-- 업허가번호 -->
				 		<td colspan="2">${item.entp_name }</td><!-- 업체명 -->
				 	</tr>
				
				 <c:if test="${item.default_in_use_id eq 2}">
				 </c:if>
				 </c:forEach>
		 	</c:when>
		 	<c:otherwise>
		 		<tr>
		 			<td>no data</td>
				</tr>
			</c:otherwise>
		 </c:choose>
  	</table>
 	<div>
 		${count }
 	</div>
 	 <div class="paging" style="min-width: 0">
 		${requestScope.pageString }
 	</div>
</form>
 </div>

 <div style="float: right;">
	 		<img src="view/style/images/bt_cancel.jpg" class="hover1" id="cancel1" alt="취소" onclick="cancel();"/>
</div>

 <script type="text/javascript" >
 	
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

 	function winClose(id,num,name){
		opener.document.productCreateForm1.company_id.value = id;
		opener.document.productCreateForm1.company_num.value = num+" 호";
		opener.document.productCreateForm1.company_name.value = name;
		window.close();
 	}
 	
 	function cancel(){
 		window.close();
	}
 	
 	$(document).ready(function(){
 		initCompanyList1Jsp();
 	});
 	
 	function initCompanyList1Jsp(){
 		
 		$("#getSearchButton1").bind("click", function(){
 			companyListForm1Submit();
 		});
 		
 	}
 	
 	function companyListForm1Submit(){
 		$("form[name=companyListForm1]").submit();
 		
 	}
 </script>
 
 