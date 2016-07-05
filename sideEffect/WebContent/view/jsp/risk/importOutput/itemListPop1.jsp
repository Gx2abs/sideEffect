<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="margin: 30px 10px 0px 10px;">
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	
 	<form method="get" name="productListForm1" action="safetyItem.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 	
 	<div class="searchBar1">
		<select name="searchColumn">
 			<option value="setEntp_name">업체명</option>
 			<c:choose>
				<c:when test="${param.searchColumn eq 'setClass_kor_name' }">
					<option selected="selected" value="setClass_kor_name">품목명</option>
				</c:when>
				<c:otherwise><option value="setClass_kor_name">품목명</option></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.searchColumn eq 'setMea_class_no' }">
					<option selected="selected" value="setMea_class_no">품목분류번호</option>
				</c:when>
				<c:otherwise><option value="setMea_class_no">품목분류번호</option></c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.searchColumn eq 'setMeddev_item_no' }">
					<option selected="selected" value="setMeddev_item_no">품목허가번호</option>
				</c:when>
				<c:otherwise><option value="setMeddev_item_no">품목허가번호</option></c:otherwise>
			</c:choose>
		</select>
		<input type="text" name="searchKeyword" value="${param.searchKeyword }">
		<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 	</div><br>
 	
 	<table class=" bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="82" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업체명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목분류번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목허가번호</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="winClose('${item.id }','${item.mea_item.class_kor_name }','${item.mea_item.mea_class_no }');" >
				 		<td colspan="2">
				 			${localCount}
				 			<c:set var="localCount" value="${localCount-1 }"/>
				 		</td>
				 		<td colspan="2">
							${item.company.entp_name }
						</td><!-- 업체명 -->
				 		<td colspan="2">${item.mea_item.mea_class_no  }</td><!-- 품목분류번호 -->
				 		<td colspan="2">${item.mea_item.class_kor_name }</td><!-- 품목명 -->
				 		<td colspan="2">${item.mea_item.categoryGrade.propertyValue }</td><!-- 등급 -->
				 		<td colspan="2">
				 		<c:choose>
							<c:when test="${item.cobFlagType.propertyValue eq '없음'}"></c:when>
							<c:otherwise>${item.cobFlagType.propertyValue }</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${item.meddev_item_no ne ''}">${item.meddev_item_no }호</c:when>
						</c:choose>
						</td><!-- 품목허가번호 -->
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
 	
 	<div class="paging" align="center">
 		${requestScope.pageString }
 	</div>
 	<div style="float: right;">
	 		<img src="view/style/images/bt_cancel.jpg" id="cancel1" alt="취소" onclick="cancel();" style="cursor: pointer;"/>
	</div>
 	</form>
 	</div>

 
 <script type="text/javascript" >
 	function winClose(id, name, code){
	 	$(opener.document).find("input[name=item_id]").val(id);
	 	$(opener.document).find("input[name=item_class_kor_name]").val(name);
	 	$(opener.document).find("input[name=mea_class_no]").val(code);
		window.close();
	}
 	
 	function initCountryList1Jsp(){
 		
 		var requestURL = window.location.href;
 		$("input[name=requestURL]").val(requestURL);
 		
 		$("#getSearchButton1").bind("click", function(){
 			doGetSearch();
 		});
 		
 	}
 	
 	function doGetSearch(){
 		$("form[name=productListForm1]").submit();
 	}

 	function cancel(){
 		window.close();
	}
 </script>
 
 