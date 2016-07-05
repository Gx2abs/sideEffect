<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	
 	<form method="get" name="componentListForm1" action="safetyInfo.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 			<input type="hidden" value="${param.regionId }" name="regionId"/>
 		
 		
 	<div class="levelBtn1">
 	<c:choose>
 		<c:when test="${!empty componentLevel }">
 			<c:forEach var="componentLevel"  items="${componentLevel}">
	 			<img src="view/style/images/btn_lev${componentLevel.id}.jpg" id="submit1" alt="level${componentLevel.id}등록" onclick="create(${componentLevel.id});"/>
	 		</c:forEach>
 		</c:when>
 		<c:otherwise>
 			No level data
 		</c:otherwise>
 	</c:choose>
 		
 	</div>
 	
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="72" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분1</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="72" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분2</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="92" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">제목</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="160" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">첨부</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="160" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">작성자</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="92" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등록일</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">조회수</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" onclick="read(${item.id});" >
				 		<!--${ item.prop1List[0].propertyValue}  / ${ item.prop1List[1].propertyValue}-->
				 		<td colspan="2">${ item.prop1List[0].propertyValue}</td>
				 		<td colspan="2">${ item.prop1List[1].propertyValue}</td>
				 		<td class="subject" colspan="2">${item.title}</td>
				 		<td colspan="2">${item.title}</td>
				 		<td colspan="2">${item.userId}</td>
				 		<td colspan="2">${item.firstRegistered }</td>
				 		<td colspan="2">${item.hits}</td>
				 	</tr>
				 	<c:set var="localCount" value="${localCount-1 }"/>
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
 		<c:if test="${!empty sessionScope.user }">
 			<img src="view/style/images/bt_write.jpg" alt="create" class="buttonised" onclick="create('${param.regionId}');"/>
 		</c:if>
	</div>
	
	<div class="searchBar1">
		parent
		<select name="propLv1">
			<option value="0">전체</option>
			<c:forEach var="propLv1" items="${requestScope.propsLv1 }">
				<option value="${propLv1.id }">${propLv1.id }/${propLv1.propertyValue }</option>
			</c:forEach>
		</select>
		child
		<select name="propLv2">
			<option value="0">전체</option>
			<c:forEach var="propLv2" items="${requestScope.propsLv2 }">
				<option value="${propLv2.id }">${propLv2.id }/${propLv2.propertyValue }</option>
			</c:forEach>
		</select>
		
		<select name="searchColumn">
			<option value="all">전체</option>
			<option value="setTitle">제목</option>
			<option value="setBody">내용</option>
		</select>
		
			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
			
	</div>
 	</form>
 	</div>

 
 <script type="text/javascript" >
 	function read(id){
 		try{
 			//alert(id);
 			var regionId = $("input[name=regionId]").val();
 			location.href = "safetyInfo.do?action=readSafetyInfo&articleId="+id+"&regionId="+regionId;
 		}catch(Exception){
 			alert(Exception.message);
 		}
 	}
 	
 	function create(regionId){
 		location.href="safetyInfo.do?action=createSafetyInfoPage&regionId="+regionId;
 	}
 	
 	$(document).ready(function(){
 		initcomponentList1Jsp();
 	});
 	
	function initcomponentList1Jsp(){
 		
 		$("#getSearchButton1").bind("click", function(){
 			componentListForm1Submit();
 		});
 		
 	}
	
	function componentListForm1Submit(){
 		
 		$("form[name=componentListForm1]").submit();
 		
 	}

 </script>
 
 