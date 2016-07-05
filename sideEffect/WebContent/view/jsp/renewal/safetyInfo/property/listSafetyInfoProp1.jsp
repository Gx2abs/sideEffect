<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 
 <script type="text/javascript" src="${pageContext.request.contextPath }/view/js/popup.js"></script>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 	<div>
 	<%@include file="/view/jsp/common/simplePageTitle1.jsp" %>
 	
 	<form method="post" name="componentListForm1" action="safetyInfo.do">
 			<input type="hidden" value="listSafetyInfoProp1" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.regionId }" name="regionId" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 		
 		<div class="searchBar1">
 			<input type="text" name="searchColumn" value="setPropertyValue">
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 		</div><br>
 		
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
 		<img src="view/style/images/btn_lev1.jpg" class="buttonised" onclick="openLayerSafetyInfoProp1(1);"/>
 		<img src="view/style/images/btn_lev2.jpg" class="buttonised" onclick="openLayerSafetyInfoProp1(2);"/>
 	</div>
 	
 	<table class="bordered bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="72" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="92" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">FDA코드</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="72" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">action</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		<c:set var="localCount" value="${top }"/>
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr >
				 		
				 		<td class="subject" colspan="2">${item.propertyLevel }</td>
				 		<td colspan="2">
				 			<c:choose>
				 				<c:when test="${item.propertyLevel eq 1 }">
				 					<b>${item.propertyValue }</b>
				 				</c:when>
				 				<c:otherwise>
				 					${item.parent.propertyValue }->${item.propertyValue }
				 				</c:otherwise>
				 			</c:choose>
				 		</td>
				 		<td colspan="2">
				 		
				 			<c:if test="${!empty sessionScope.user }">
				 				<input type="button" value="수정" onclick="updateSafetyInfoProp1('${item.id}');"/>
				 				<input type="button" value="삭제" onclick="deleteSafetyInfoProp1('${item.id}');"/>
				 			</c:if>
				 			
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
 	</form>
 	</div>

 <div id="modal" style="border:3px solid black; background-color:white;  padding:25px; font-size:150%; text-align:center; display:none;">
	This is a modal popup!<br><br>
	<input type="button" value="OK" onClick="Popup.hide('modal')">
</div>
 <script type="text/javascript" >
  
 	function create(level){
 		location.href="properties.do?action=createComponentPage&level="+level;
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
	
	function updateSafetyInfoProp1(articleId){
		
		if(confirm("기존데이터도 수정 됩니다. 계속 하시겠습니까 ?")){
			var xhr = new XMLHttpRequest();
			var method = "get";
			var url = "safetyInfo.do?action=updateSafetyInfoProp1Page&articleId="+articleId;
			var isAsync = true;
			
			xhr.open(method, url, isAsync);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						
						$("#modal").html(xhr.responseText);
						Popup.showModal('modal',null,null,{'screenColor':'blue','screenOpacity':0.2,'offsetTop':-250,'offsetLeft':-200});
					}else{
						alert('내부에러');
					}
				}
				
			};		
		}else{
			return;
		}
	}
	
	function deleteSafetyInfoProp1(articleId){
		
		if(confirm("삭제 하시겠습니까?")){
			var xhr = new XMLHttpRequest();
			var method = "get";
			var url = "safetyInfo.do?action=deleteSafetyInfoProp1&articleId="+articleId;
			var isAsync = true;
			xhr.open(method, url, isAsync);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						if(xhr.responseText==1){
							alert("삭제성공");
							location.reload();
						}else{
							alert("삭제실패");
						}
					}
				}
				
			};
		}else{
			return;
		}
		
		
	}
	
	function openLayerSafetyInfoProp1(level){

		var regionId = $("input[name=regionId]").val();
		var xhr = new XMLHttpRequest();
		var method = "get";
		var url = "safetyInfo.do?action=createSafetyInfoProp1Page&propLevel="+level+"&regionId="+regionId;
		var isAsync = true;
		
		xhr.open(method, url, isAsync);
		xhr.send();
		
		xhr.onreadystatechange = function(){
			
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					
					$("#modal").html(xhr.responseText);
					Popup.showModal('modal',null,null,{'screenColor':'blue','screenOpacity':0.2,'offsetTop':-250,'offsetLeft':-200});
				}
			}
			
		};		
		
		return;
	}

 </script>
 
 