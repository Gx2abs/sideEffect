<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>

 <%
 	int pg = Function.nullChk(request.getParameter("pg"),0);
 	String sv = Function.nullChk(request.getParameter("sv"),"");
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

//function submit(articleId, name){
	//alert(articleId, name);
	     //    alert(opener.document.safetyCreateForm1);
//	opener.document.safetyCreateForm1.meb_item_manuf_id.value = articleId;
//	opener.document.safetyCreateForm1.meb_item_manuf_name.value = name;
//	window.close();
//}


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


<div><font color="red">업소명</font> > 품목명 > 형명</div>

<div>
	<form name="manufImportEntpNameListForm1" method="post" action="meb_item.do">
		<input type="hidden" name="action" value="manufImportEntpNameSearch1" />
		업소명 : <input type="text" name="sv" value="<%=sv %>" onkeypress="EnterDown()" />
			   <input type="button" value="검색" onclick="search()" />
    </form>
</div>

<table class="bordered" style="width: 500px; ">

 		<thead>
 			<tr style="height:36px; padding: 0;">
 				
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					No
 				</th>
 				
 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					업허가번호
 				</th>

 				<th style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					업소명
 				</th>
 				
 			</tr>
 		</thead>
 		
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="meb_item" items="${requestScope.list }">	
			 		<tr>
		 				<td></td>
		 				<!-- 
		 				<td>${meb_item.meddev_item_seq }</td>
		 				 -->
		 				<td>${meb_item.meddev_entp_seq}</td>  
		 				<td>${meb_item.company.entp_name }</td>
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