<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/view/config/config.jsp" %>
<%@ include file="/view/config/styleSheetsAndScripts.jsp" %>

 <%
 	int pg = Function.nullChk(request.getParameter("pg"),1);
	int seq = Function.nullChk(request.getParameter("seq"),1);
	int itemSeq = Function.nullChk(request.getParameter("itemSeq"),1);
 	String sv = Function.nullChk(request.getParameter("sv"),"");
 	String sv2 = Function.nullChk(request.getParameter("sv2"),"");
 	String sv3 = Function.nullChk(request.getParameter("sv3"),"");
 	String entp_name = Function.nullChk(request.getParameter("entp_name"),"");
 	String item_name = Function.nullChk(request.getParameter("item_name"),"");
 	//out.print("item_name  "+item_name);
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css" >
<link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css" >
<title>Insert title here</title>

<script type="text/javascript">

function search(){
	//alert(itemSeq);
	var frm = document.itemListForm1;
		
	frm.submit();
}

function select1(articleId, name){
	var frm1 = document.itemListForm1;
	
	var isEmpty = "${fn:length(requestScope.list)}";
	
	if(isEmpty > 0){
		var arr = new Array();
		
		var mebTypeSelectLength = frm1.mebTypeSelect.length;
		for(var i=0; i<mebTypeSelectLength; i++){
			if(frm1.mebTypeSelect[i].checked){
				arr[i] = frm1.mebTypeInfoId[i].value;
			}else{
				arr[i] = -1;
			}
		}
		
		if(arr.length > 0){
			frm1.arr.value=arr;
			opener.document.safetyCreateForm1.mebTypeInfoSelect.value = arr;
		}
		
		if(mebTypeSelectLength == undefined){
			if(frm1.mebTypeSelect.checked){
				frm1.arr.value=frm1.mebTypeInfoId.value;
				opener.document.safetyCreateForm1.mebTypeInfoSelect.value = frm1.mebTypeInfoId.value;
			}
		}
	}
		
		
		//alert("articleId  "+articleId+"  name  "+name);
	    //alert(opener.document.safetyCreateForm1);
		opener.document.safetyCreateForm1.meb_item_manuf_id.value = articleId;
		opener.document.safetyCreateForm1.meb_item_manuf_name.value = name;
		window.close();
	
}

function EnterDown(){
	if(event.keyCode == 13) {
		search();
	}
}
	

</script>

</head>

<body>
<div style="margin: 30px 0 0 5px;">
	<div style="margin-bottom: 30px;"><%@include file="/view/jsp/common/simplePageTitle1.jsp" %></div>
<div>업소명 > 품목명 > <font color="red">형명</font></div><br>


<div>
	<form name="itemListForm1" method="post" action="meb_item.do">	
		<input type="hidden" name="action" value="meb_type" />
			업소명 : <%=entp_name %> <br><input type="hidden" name="entp_name" value="<%=entp_name %>" readonly />
			<div style="padding-top: 3px">품목명 : <%=item_name %> <br><input type="hidden" name="item_name" value="<%=item_name %>" readonly /></div>
		   	형 &nbsp; 명 : <input type="text" name="sv3" value="<%=sv3 %>"  onkeypress="EnterDown()" />
			  <img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="search()" />
			   
			   <input type="hidden" name="seq" value="<%=seq %>"  />
			   
			   <input type="hidden" name="itemSeq" value="<%=itemSeq %>"  />
			   
			   <input type="hidden" name="arr" value="0"  />
			   
			   <img src="view/style/images/btn_pre.jpg" class="hover1" alt="뒤로가기" onclick="history.back()" />
   	
</div><br>

<table class="bordered bbsListType" style="width: 100%">

 		<thead>
 			<tr style="height:36px; padding: 0;">
 				<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 				<th width="70" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					No
 				</th>
 				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="100" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					품목명
 				</th>
				<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 				<th width="270" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">
 					형명
 				</th>
 				<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 			</tr>
 		</thead>
 		 
 		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
		 		<c:forEach var="mebTypeInfo" items="${requestScope.mebTypeInfoList }">	
		 		<input type="hidden" name="mebTypeInfoId" value="${mebTypeInfo.id }" />
		 		
			 		<tr>
		 					<td colspan="2">${top - i.index }</td>
		 					<td colspan="2">
		 						<%=item_name %>
		 					</td>
		 					<td colspan="2">
			 					<input type="checkbox" name="mebTypeSelect" />${mebTypeInfo.type_name }
		 					</td>
		 			</tr>
		 		</c:forEach>
		 	</c:when>
		 	<c:otherwise>
			 		<tr class="listover" onMouseOver="this.style.backgroundColor='#F6F6F6'" onMouseOut="this.style.backgroundColor='#FFFFFF'" onclick="select1(<%=itemSeq %>,'<%=item_name %>');">
		 					<td colspan="2">1</td>
		 					<td colspan="2">
		 						<%=item_name %>
		 					</td>
		 					<td colspan="2">
			 		
		 					</td>
		 			</tr>
			</c:otherwise>
		 </c:choose>
 	</table>
 
 	<div style="float: right; padding: 20px 10px 0px 0px;">
		<img src="view/style/images/btn_ok.jpg" class="hover1" onclick="select1(<%=itemSeq %>,'<%=item_name %>');" />
	</div>


 		 <div class="paging" style="min-width: 0">
 			${requestScope.pageString }
 	     </div>
 	</div>
 		
	 </form>	
	

	

</body>


</html>