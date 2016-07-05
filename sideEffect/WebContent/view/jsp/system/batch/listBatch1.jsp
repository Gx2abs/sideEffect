<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/jquery-ui-1.10.3/js/jquery-ui-1.10.3.min.js"></script>
 <link rel="stylesheet" type="text/css" href="view/js/jquery-ui-1.10.3/css/ui-lightness/jquery-ui-1.10.3.custom.min.css"/>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	<form method="get" name="codeMatchingListForm1" action="properties.do">
 		<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 		<div class="searchBar1 hidden">	
 			<select name="searchColumn">
 			<option value="name"></option>
 			</select>
 			<input type="text" name="dateFrom" id="dateFrom" readonly="readonly" value="클릭하세요"> ~
 			<input type="text" name="dateTo" id="dateTo" readonly="readonly" value="클릭하세요"> 
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">	
 		</div><br>
 	<table class="bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">No.</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">기준 코드(구코드)</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">성공</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">실패</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">작업결과</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">작업결과</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="135" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">서비스분야</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="read(${item.id}, ${item.tableTypeId });" >
				 		<td colspan="2">
				 			${item.id }
				 		</td>
				 		<td class="subject" colspan="2">
				 		</td>
				 		<td colspan="2">
				 		${item.numberOfSuccess }
				 		</td>
				 		<%-- <td colspan="2">${item.id }</td> --%>
				 		<td colspan="2">${item.numberOfFailure }</td>
				 		<td colspan="2">
				 			${item.jobStatusObject.propertyValue } 
				 		</td>
				 		<td colspan="2">
				 			${item.moveJobStatusObject.propertyValue } 
				 		</td>
				 		<td colspan="2">
				 			 ${item.tableType.propertyValue }
				 		</td>
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
 	<div>
 		${count }
 	</div>
 	<div class="paging">
 		${requestScope.pageString }
 	</div>
 	
 	<div style="float: right;">
 		<img src="view/style/images/bt_write.jpg" alt="create" class="buttonised" onclick="create();"/>
	</div>
 	</form>
 	</div>

 
 <script type="text/javascript" >
 	function read(id, tableType){
 		try{
 			
 			var name = "_BLANK";
 			var url = "system.do?action=openBatchJobListPopup&batchJobGroupId="+id+"&tableTypeId="+tableType;
 			var specs = "width=900,height=300,resizable=yes";
 			window.open(url, name, specs);
 			
 		}catch(Exception){
 			alert(Exception.message);
 		};
 		
 	}
 	
 	function create(){
 		location.href="system.do?action=createBatchJobPage";
 		
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
 	
 	function bindCalendars(){
 		
 		$("#dateFrom").datepicker();
 		$("#dateFrom").datepicker("option", "dateFormat", "yy-mm-dd");
 		
 		$("#dateTo").datepicker();
 		$("#dateTo").datepicker("option", "dateFormat", "yy-mm-dd");
 		
 	}
 	
 	$(document).ready(function(){
 		bindCalendars();
 	});
 </script>
 
 