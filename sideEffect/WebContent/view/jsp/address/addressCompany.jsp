<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/view/config/config.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <%@ include file="/view/config/styleSheetsAndScripts.jsp" %> 
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/item.css">
 <link rel="stylesheet" type="text/css" href="view/style/css/properties/common.css">

<%
	String gubun = Function.nullChk(request.getParameter("gubun"),""); 
 	String key = Function.nullChk(request.getParameter("key"),"");
 %>
 
<script type="text/javascript">
function more(pg){
 	var addr = document.frm.addr2.value;
 	frm.action="address.do?searchCompany=1&pg=" + pg;
	frm.submit();
}


	function search(){
		var frm = document.searchForm;
		var key = frm.key.value;
		if(key == ""){
			alert("주소를 입력해 주세요.");
			return;
		}
		document.getElementById("pg").value = "1";
		frm.submit();
		
	}
	
	function winClose(address){
		//alert("address" + address);
		opener.document.companyCreateForm1.entp_zip_no.value = address;
		//opener.document.safetyCreateForm1.report_address.value = address;
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
<div style="overflow-x:hidden; overflow-y:auto; width:100%; height:100%;">
<form name="searchForm" method="post" action="address.do">
	<input type="hidden" name="action" value="searchCompany" />
	<input type="hidden" value="${param.pg }" name="pg" id="pg" />
	
	<table class=" bbsListType" style="margin: 20px 0px 0px 50px;;" >
			<tr>
				<td width="1px;">
					<select name="gubun">
						<option value="1">서울특별시</option>
						<option value="2">광주광역시</option>
						<option value="3">대구광역시</option>
						<option value="4">대전광역시</option>
						<option value="5">인천광역시</option>
						<option value="6">부산광역시</option>
						<option value="7">울산광역시</option>
						<option value="8">강원도</option>
						<option value="9">경기도</option>
						<option value="10">경상남도</option>
						<option value="11">경상북도</option>
						<option value="12">전라남도</option>
						<option value="13">전라북도</option>
						<option value="14">충청남도</option>
						<option value="15">충청북도</option>
						<option value="16">세종특별자치시</option>
						<option value="17">제주도특별자치도</option>
					</select>
				</td>
				<td align="left">
					<input type="text" name="key"  value="<%=key %>"onkeypress="EnterDown()" />
					<img src="view/style/images/bt_search.jpg" class="hover1" alt="검색" onclick="search()">
				</td>
			</tr>
		</table>
		<br> * 도로명으로 검색가능합니다.
</form>



	<table class=" bbsListType" style="margin: 10px 0px 10px 8px;">
		<tr style="height:36px; padding: 0;">
			<th width="1" style="background:url('view/style/images/bar_left.jpg') no-repeat;"></th>
 			<th width="80" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">우편번호</th>
 			<th width="1" style="vertical-align: middle; padding:0;"><img src="view/style/images/bar_division.jpg"  border="0"/></th>
 			<th width="180" style="background:url('view/style/images/bar_bg.jpg') repeat-x;">주소</th>
 			<th width="10" style="background:url('view/style/images/bar_right.jpg') no-repeat;"></th>
 		</tr>
		<c:choose>
		 	<c:when test="${!empty requestScope.list }">
				<c:forEach var="addr" items="${requestScope.list }" varStatus="i">
					<tr>
						<td colspan="2">${addr.zipcode }</td>
						<td colspan="2" style="text-align: left;"><a href="javascript:winClose('${addr.zipcode}')">${addr.gu} ${addr.doro_nm} ${addr.bldg_nm} ${addr.bldg1}</a></td>
					</tr>		
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<div>
		<c:choose>
			<c:when test="${!empty requestScope.list }">
				${count }
			</c:when>
		</c:choose> 
 	</div>
 	<br>
 	<div class="paging" style="min-width: 0px;">
 		${requestScope.pageString }
 	</div>
 	<br>
</div>
</body>
</html>
