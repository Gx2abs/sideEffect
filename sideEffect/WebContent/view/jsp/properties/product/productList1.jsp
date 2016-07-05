<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 	
 	<form method="get" name="productListForm1" action="properties.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 	
 	<div class="searchBar1">
		<select name="searchColumn">
 			<option value="setEntp_name">업체명</option>
			<option value="setClass_kor_name">품목명</option>
			<option value="setMea_class_no">품목분류번호</option>
			<option value="setMeddev_item_no">품목허가번호</option>
		</select>
		<input type="text" name="searchKeyword" value="${param.searchKeyword }">
		<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 	</div><br>
 	
 	<table class="bordered bbsListType">
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
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">적용일자</th>
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
							${item.company.entp_name }
						</td><!-- 업체명 -->
				 		<td colspan="2">${item.mea_item.mea_class_no  }</td><!-- 품목분류번호 -->
				 		<td colspan="2">${item.mea_item.class_kor_name }</td><!-- 품목명 -->
				 		<td colspan="2">${item.mea_item.grade }</td><!-- 등급 -->
				 		<td colspan="2">
							<c:choose>
					 		<c:when test="${item.cob_flag_code eq '1'}">수허</c:when>
					 		<c:when test="${item.cob_flag_code eq '2'}">제허</c:when>
					 		<c:when test="${item.cob_flag_code eq '3'}">서울수신</c:when>
					 		<c:when test="${item.cob_flag_code eq '4'}">서울제신</c:when>
					 		<c:when test="${item.cob_flag_code eq '5'}">서울제신</c:when>
					 		<c:when test="${item.cob_flag_code eq '6'}">경인제신</c:when>
					 		<c:when test="${item.cob_flag_code eq '7'}">대전제신</c:when>
					 		<c:when test="${item.cob_flag_code eq '8'}">부산수신</c:when>
					 		<c:when test="${item.cob_flag_code eq '9'}">부산제신</c:when>
					 		<c:otherwise>없음</c:otherwise>
				 		</c:choose>
							 ${item.meddev_item_no }호</td><!-- 품목허가번호 -->
				 		<td colspan="2">
							<c:choose>
								<c:when test="${!empty item.productHistory }">
									<c:forEach var="productHistory" items="${item.productHistory }" varStatus="i" >
										<c:choose>
										<c:when test="${i.last == true}">
										<fmt:formatDate pattern="yyyy-MM-dd" value="${productHistory.lastModified }"/>
										</c:when> 
										</c:choose>
									 </c:forEach> 
								</c:when>
							</c:choose>
						</td><!-- 적용일자 -->
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
 	function read(id){
 		try{
 			//alert(id);
 			location.href = "properties.do?action=readProduct&menu=6&articleId="+id;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	function create(){
 		location.href="properties.do?action=createProductPage&menu=6";
 		
 	}

 	$(document).ready(function(){
 		
 		initCountryList1Jsp();
 	});
 	
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
 </script>
 
 