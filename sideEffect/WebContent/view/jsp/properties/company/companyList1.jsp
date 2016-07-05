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
 	
 	<form method="get" name="companyListForm1" action="safetyCompany.do" accept-charset="utf-8">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 	
 	<div class="searchBar1">
		<select name="searchColumn">
			<option value="setEntp_name">업체명</option>
			<option value="setCob_flag_code">업구분</option>
			<option value="setMeddev_entp_no">업허가번호</option>
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
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업허가번호</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업체명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">업허가일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">수정일자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">상태</th>
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
				 		<c:choose>
				 		<c:when test="${item.cob_flag_code eq '1'}">제조업</c:when>
				 		<c:when test="${item.cob_flag_code eq '2'}">수입업</c:when>
				 		<c:otherwise></c:otherwise>
				 		</c:choose></td><!-- 업구분 -->
				 		<td colspan="2">
				 		<c:choose>
				 			<c:when test="${!empty item.meddev_entp_no }">제${item.meddev_entp_no }호</c:when>
				 		</c:choose> 
				 		</td><!-- 업허가번호 -->
				 		<td colspan="2">${item.entp_name }</td><!-- 업체명 -->
				 		<td colspan="2"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.permit_date }"/></td><!-- 업허가일자 -->
				 		<td colspan="2">
						<c:choose>
								<c:when test="${!empty item.companyHistory }">
									<c:forEach var="companyHistory" items="${item.companyHistory }" varStatus="i" >
										<c:choose>
										<c:when test="${i.last == true}">
										<fmt:formatDate pattern="yyyy-MM-dd" value="${companyHistory.lastModified }"/>
										</c:when> 
										</c:choose>
									 </c:forEach> 
								</c:when>
								
							</c:choose>
						</td><!-- 적용일자 -->
				 		<td colspan="2">
				 		<c:choose>
				 		<c:when test="${item.shutdown_close_reopen_code eq '1'}">정상</c:when>
				 		<c:when test="${item.shutdown_close_reopen_code eq '2'}">양도</c:when>
				 		<c:when test="${item.shutdown_close_reopen_code eq '3'}">취하</c:when>
				 		<c:when test="${item.shutdown_close_reopen_code eq '4'}">휴업</c:when>
				 		<c:when test="${item.shutdown_close_reopen_code eq '5'}">폐업</c:when>
				 		<c:when test="${item.shutdown_close_reopen_code eq '6'}">취소</c:when>
				 		<c:otherwise>없음</c:otherwise>
				 		</c:choose></td><!-- 상태 -->
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
 			location.href = "safetyCompany.do?action=readCompany&menu=5&articleId="+id;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 		
 	}
 	
 	function create(){
 		location.href="safetyCompany.do?action=createCompanyPage&menu=5";
 		
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
 		$("form[name=companyListForm1]").submit();
 	}
 </script>
 
 