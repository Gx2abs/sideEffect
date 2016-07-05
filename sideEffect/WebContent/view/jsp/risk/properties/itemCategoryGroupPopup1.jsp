<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
 <%@ include file="/view/config/config.jsp" %>
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %>
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">
 	<%
 	member.Member objMember ;
	long privilegeId = -1;
 	if(session.getAttribute("user") != null){
		objMember = (member.Member) session.getAttribute("user");
		privilegeId = objMember.getPrivilegeId();
	} %>
 	<div style="padding: 50px 0px 0px 10px;" >
 	
 	<form method="get" name="itemListForm1" action="risk.category.do">
 			<input type="hidden" value="${param.action }" name="action"/>
 			<input type="hidden" value="${param.pg }" name="pg" />
 			<input type="hidden" value="${param.menu }" name="menu"/>
 			<input type="hidden" value="${param.articleId }" name="articleId"/>
 			
 		<div class="searchBar1">
 			<div class="searchBar2">
 			<select name="searchColumn">
 				<option value="setClass_kor_name">품목명</option>
 				<c:choose>
 					<c:when test="${param.searchColumn eq 'setMea_class_no' }">
 						<option value="setMea_class_no" selected="selected">품목코드</option>
 					</c:when>
 					<c:otherwise>
 						<option value="setMea_class_no">품목코드</option>
 					</c:otherwise>
 				</c:choose>
 			</select>
 			<input type="text" name="searchKeyword" value="${param.searchKeyword }">
 			<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" id="getSearchButton1">
 			</div>
 		</div><br><br><br>
 	<table class=" bbsListType">
 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">구분</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목코드</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="140" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">품목명</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등급</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="60" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">등록</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="item" items="${requestScope.list }">
				 	<tr class="listover" >
						<td colspan="2">
				 			${item.meaClassNoLevel.propertyValue }
				 		</td>
				 		<td class="subject" colspan="2">
				 			${item.mea_class_no }
				 		</td>
				 		<td colspan="2">
				 			${item.class_kor_name }
				 		</td>
				 		<td colspan="2">${item.categoryGrade.propertyValue }</td>
				 		<td colspan="2">
				 			<c:choose>
				 				<c:when test="${!empty item.parentId }">
				 					<c:choose>
				 						<c:when test="${item.parentId eq param.articleId }">
				 							<img src="view/style/images/btn_tab03.jpg" alt="삭제" id="${item.id }" onclick="deleteCategory(this)">
				 						</c:when>
				 						<c:otherwise>
				 							${item.udi_code } 그룹
				 						</c:otherwise>
				 					</c:choose>
				 				</c:when>
				 				<c:otherwise>
				 					<img src="view/style/images/btn_tab02.jpg" alt="추가" id="${item.id }" onclick="insertCategory(this)">
				 				</c:otherwise>
				 			</c:choose>
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
 	<div class="paging" style="min-width: 0px;">
 		${requestScope.pageString }
 	</div>
 	<div class="btnType3">
 		<img src="view/style/images/btn_ok.jpg" id="cancel1" alt="확인" class="buttonised" onclick="cancel();" style="padding-right: 20px;"/>
 	</div>
 	</form>
 	</div>

 <script type="text/javascript" >

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

 	function insertCategory(obj){
 		var groupId = ${param.articleId};
 		$.ajax({
 			url : "risk.category.do?action=ItemCategoryGroupPopupC",
 			dataType : "html",
 			type : "post" ,
 			data : {"articleId":obj.id,"groupId":groupId,"act":"I","privilegeId":<%=privilegeId%>},
 			success : function(result){
 	 				if(result = true){
 	 					alert("추가 되었습니다.");
 	 					$("#"+obj.id).attr("src","view/style/images/btn_tab03.jpg");
 	 					$("#"+obj.id).attr("onclick","deleteCategory(this)");
 	 					$("."+obj.id).val("D");
 	 	 			}else alert("추가 중 실패했습니다.");
 			},
 			error : function(error){
 				alert("추가에 실패했습니다.");
 			},
 			complete:function(){
 			}
 		});
 	}

 	function deleteCategory(obj){
 		var groupId = ${param.articleId};
 		$.ajax({
 			url : "risk.category.do?action=ItemCategoryGroupPopupC",
 			dataType : "html",
 			type : "post" ,
 			data : {"articleId":obj.id,"groupId":groupId,"act":"D","privilegeId":<%=privilegeId%>},
 			success : function(result){
 	 				if(result = true){
 	 					alert("삭제 되었습니다.");
 	 					$("#"+obj.id).attr("src","view/style/images/btn_tab02.jpg");
 	 					$("#"+obj.id).attr("onclick","insertCategory(this)");
 	 					$("."+obj.id).val("I");
 	 	 			}else alert("삭제 중 실패했습니다.");			
 			},
 			error : function(error){
 				alert("삭제에 실패했습니다.");
 			},
 			complete:function(){
 			}
 		});
 	}

 	function cancel(){
 		window.close();
 		opener.location.reload();
 	}
 	
 </script>
 
 