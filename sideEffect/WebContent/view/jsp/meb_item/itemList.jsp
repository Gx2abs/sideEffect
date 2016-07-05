<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>

 <%
 	int pg = Function.nullChk(request.getParameter("pg"),1);
	int seq = Function.nullChk(request.getParameter("seq"),1);
 	String sv = Function.nullChk(request.getParameter("sv"),"");
 	String sv2 = Function.nullChk(request.getParameter("sv2"),"");
 	String entp_name = Function.nullChk(request.getParameter("entp_name"),"");
 	
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css" >
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css" >
<title>Insert title here</title>

<script type="text/javascript">

function itemSearch(){
		//alert(seq + "  " + entp_name);
		var frm = document.itemListForm1;
		
		frm.submit();
	}
	

function search(itemSeq, item_name){
	//alert("itemSeq  " + itemSeq + "  item_name  " +item_name);
	var frm = document.itemListForm1;
		frm.itemSeq.value=itemSeq;
		frm.item_name.value=item_name;
		frm.action.value="meb_type";
		frm.submit();
}
	

function EnterDown(){
	if(event.keyCode == 13) {
		itemSearch();
	}
}

</script>

</head>
<body>
<div style="margin: 30px 0 0 5px;">
	<div style="margin-bottom: 30px;"><%@include file="/view/jsp/common/simplePageTitle1.jsp" %></div>
<div>업소명 > <font color="red">품목명</font> > 형명</div><br>
	
<div>
	<form name="itemListForm1" method="post" action="meb_item.do">              
		<input type="hidden" name="action" value="item_search" readonly />
		업소명 : <%=entp_name %> <br><input type="hidden" name="entp_name" value="<%=entp_name %>" readonly />
		품목명 : <input type="text" name="sv2" value="<%=sv2 %>" onkeypress="EnterDown()" />
			   <img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="itemSearch()" />
			   
			   <input type="hidden" name="seq" value="<%=seq %>" readonly />
			   
			   <input type="hidden" name="item_name" value=""  />
			   <input type="hidden" name="itemSeq" value=""  />
			  <img src="view/style/images/btn_pre.jpg" class="hover1" alt="이전" onclick="history.back()" />
    </form>
</div><br>

<table class="bordered bbsListType" style="width: 500px; ">

 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="40" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					No
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="90" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목코드
 				</th>
				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목명
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="130" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목허가번호
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="40" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					등급
 				</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="meb_item" items="${requestScope.list }" varStatus="i">	
			 		<tr class="listover" onMouseOver="this.style.backgroundColor='#F6F6F6'" onMouseOut="this.style.backgroundColor='#FFFFFF'" onclick="search(${meb_item.id}, '${meb_item.mea_item.class_kor_name }')">
		 				<td colspan="2">${top - i.index }</td>
		 				<td colspan="2">${meb_item.mea_item.mea_class_no }</td>
		 				<td colspan="2">${meb_item.mea_item.class_kor_name }</td>
		 				<td colspan="2">${meb_item.cobFlagType.propertyValue }
		 					<c:choose>
		 						<c:when test="${meb_item.cobFlagType.id ne 0 }">
		 							${meb_item.meddev_item_no }호
		 						</c:when>
		 						<c:otherwise>
		 						</c:otherwise>
		 					</c:choose>
		 				</td>
		 				<td colspan="2">${meb_item.mea_item.grade }</td>
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




 		 <div class="paging" style="min-width: 0">
 			${requestScope.pageString }
 	     </div>
 	</div>
 		
	
	
	
	

</body>
</html>