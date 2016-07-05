<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>

 <%
 	int pg = Function.nullChk(request.getParameter("pg"),0);
 	String sv = Function.nullChk(request.getParameter("sv"),"");
 	String sv2 = Function.nullChk(request.getParameter("sv2"),"");
 	String sv3 = Function.nullChk(request.getParameter("sv3"),"");
 	String sv4 = Function.nullChk(request.getParameter("sv4"),"");
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function submit(articleId, name){
	//alert(articleId, name);
	     //    alert(opener.document.safetyCreateForm1);
	opener.document.safetyCreateForm1.meb_item_manuf_id.value = articleId;
	opener.document.safetyCreateForm1.meb_item_manuf_name.value = name;
	window.close();
}


function search(){
		var frm = document.manufImportEntpNameListForm1;
		frm.submit();
		
	}
	
	function EnterDown(){
		if(event.keyCode == 13) {
			search();
		}
	}
	
</script>

</head>
<body>


<div>업소명 > 품목명 > <font color="red">형명</font></div>

<div>
	<form name="manufImportEntpNameListForm1" method="post" action="meb_item.do">
		<input type="hidden" name="action" value="manufImportEntpNameSearch3" />
		업소명 : <input type="text" name="sv" value="<%=sv %>" readonly /> <br>
		품목명 : <input type="text" name="sv2" value="<%=sv2 %>" readonly /> <br>
		   형명 : <input type="text" name="sv3" value="<%=sv3 %>" readonly />
			   
    </form>
</div>

<table class="bordered" style="width: 500px; ">

 		<thead>
 			<tr style="height:36px; padding: 0;">
 				
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					No
 				</th>
 				
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목명
 				</th>

 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					형명
 				</th>
 				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="meb_item" items="${requestScope.list }">	
			 		<tr onclick="submit(${meb_item.meddev_item_seq}, '${meb_item.item_name }');">
		 				<td></td>
		 					<td>${meb_item.item_name }</td>
		 					<td>${meb_item.manuf_req_name }</td>
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
	
	
	

</body>
</html>