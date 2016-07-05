<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %> 
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 		<form method="get" name="importOutputListForm1" action="importoutput.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 		
 	<div class="searchBar1">
		<select name="searchColumn">
			<option value="mea_item.class_kor_name" selected="selected">품목명</option>
			<c:choose>
 				<c:when test="${param.searchColumn eq 'company.entp_name' }">
 					<option value="company.entp_name" selected="selected">업체명</option>
 				</c:when>
 				<c:otherwise>
 					<option value="company.entp_name">업체명</option>
 				</c:otherwise>
 			</c:choose>
 			<c:choose>
 				<c:when test="${param.searchColumn eq 'item.meddev_item_no' }">
 					<option value="item.meddev_item_no" selected="selected">품목허가번호</option>
 				</c:when>
 				<c:otherwise>
 					<option value="item.meddev_item_no">품목허가번호</option>
 				</c:otherwise>
 			</c:choose>
		</select>
		<input type="text" name="searchKeyword" value="${param.searchKeyword }">
		<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 	</div><br>
 	<div class="levelBtn1">
 		<c:if test="${param.action eq 'listOutput' }">
 				<a href="importoutput.do?action=listOutput&menu=12" ><img src="view/style/images/btn_class_output.gif" alt="생산실적관리" /></a>
	 			<a href="importoutput.do?action=listImport&menu=12" ><img src="view/style/images/btn_class_import_off.gif" alt="생산실적관리" /></a>
 		</c:if>
 		<c:if test="${param.action eq 'listImport' }">
 				<a href="importoutput.do?action=listOutput&menu=12" ><img src="view/style/images/btn_class_output_off.gif" alt="생산실적관리" /></a>
	 			<a href="importoutput.do?action=listImport&menu=12" ><img src="view/style/images/btn_class_import.gif" alt="생산실적관리" /></a>
 		</c:if>
 	</div>
 	<table class=" bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No.</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="170" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="170" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업체명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목허가번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">수입량</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">날짜</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="read(${item.id});" >
				 		<td colspan="2">
				 			${localCount}
				 			<c:set var="localCount" value="${localCount-1 }"/>
				 		</td>
				 		<td colspan="2">
				 			${item.item.mea_item.class_kor_name }
				 		</td>
				 		<td colspan="2">
				 			${item.item.company.entp_name }
				 		</td>
				 		<td colspan="2" >
							<c:if test="${item.item.cobFlagType.propertyValue ne '없음' or !empty item.item.cobFlagType.propertyValue}">
								${item.item.cobFlagType.propertyValue} ${item.item.meddev_item_no }호
							</c:if>
						</td>
				 		<td colspan="2">
				 			<fmt:parseNumber value="${item.itemImport }" />
				 		</td>
				 		<td colspan="2">
				 			<c:if test="${!empty item.importYear && !empty item.importMonth }">
				 				${item.importYear }-${item.importMonth }
				 			</c:if>
				 		</td>
				 	</tr>
				 </c:forEach>
		 	</c:when>
		 	<c:otherwise>
		 		<tr><td colspan="12" align="cneter">no data</td></tr>
			</c:otherwise>
		 </c:choose>
 	</table>
 	<div>
 		${count }
 	</div>
 	<div class="paging">
 		${requestScope.pageString }
 	</div>
 	
 	<div class="btnType3">
 	<%if(privilegeId>1){ %>
 		<img src="view/style/images/bt_write.jpg" alt="create" class="buttonised" onclick="create();"/>
 	<%} %>
	</div>
 	</form>	
 	</div>

 
 <script type="text/javascript" >

 	$(document).ready(function(){
		initItemList1Jsp();
	});
	
	function initItemList1Jsp(){
		
		$("#getSearchButton1").bind("click", function(){
			$("form[name=importOutputListForm1]").submit();
		});
		
	}
 	
 	function read(id){
 		try{
 			//alert(id);
 			location.href = "importoutput.do?action=readImport&menu=12&articleId="+id;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	function create(){
 		location.href="importoutput.do?action=createImportOutputPage&menu=12";
 		
 	}
 	
 </script>
 
 