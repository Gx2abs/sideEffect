<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form method="post" name="codeMatchingListForm1" action="system.do">
 		<input type="hidden" value="${param.action }" name="action"/>
		<input type="hidden" value="${param.pg }" name="pg" />
 		<input type="hidden" value="${param.menu }" name="menu"/>
 		<input type="hidden" value="${param.tableTypeId }" name="tableTypeId"/>
 		<input type="hidden" value="${param.batchJobGroupId }" name="batchJobGroupId"/>
 		<input type="hidden" value="${requestScope.batchJobGroup.moveJobStatus  }" name="moveStatus"/>
 		<div class="searchBar1">	
 			<select name="jobStatus" class="hidden" style="display: none;">
 				<c:forEach var="jobStat" items="${requestScope.jobStats }">
 					<option value="${jobStat.id }">${jobStat.propertyValue }</option>
 				</c:forEach>
 			</select>
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }"><img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">	
 		</div>
 		
 		<div>
 		<c:choose>
 			<c:when test="${requestScope.batchJobGroup.moveJobStatus eq 5}">
 				<button  onclick="apply();">실제 테이블에 적용시키기</button>
 			</c:when>
 			<c:otherwise>
 				${requestScope.batchJobGroup.moveJobStatusObject.propertyValue}
 			</c:otherwise>
 		</c:choose>
 		
 		업로드 내용을 실제 DB에 적용시키려면  “실제 테이블에 적용시키기” 버튼을 눌러 주십시오.
 		
 		</div>
 		
 		<br>
 	 
 	 	<c:import url="${requestScope.popupBatchListContent }"/>
 		
 	</form>
 	</div>
 
 <script type="text/javascript" >
 
 function apply(){
	 var batchGroupId = $("input[name=batchJobGroupId]").val();
	 var method = "get";
	 var url = "system.do?action=applyBatchJob&batchGroupId="+batchGroupId;
	 var isAsync = true;
	 var xhr = new XMLHttpRequest();
	 
	 xhr.open(method, url, isAsync);
	 xhr.send();
	 window.close();
	 
 }
 
 	
	$(document).ready(function(){
 		
 		initReportTypeList1Jsp();
 	});
 	
 	function initReportTypeList1Jsp(){
 		
 		var requestURL = window.location.href;
 		$("input[name=requestURL]").val(requestURL);
 		
 		$("#getSearchButton1").bind("click", function(){
 			doGetSearch();
 		});
 			
 	}
 	
 	function doGetSearch(){
 		$("form[name=codeMatchingListForm1]").submit();
 	}
 	
 	 
 </script>
 
 