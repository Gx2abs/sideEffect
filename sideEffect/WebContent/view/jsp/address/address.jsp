<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/view/config/config.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%
	String gubun = Function.nullChk(request.getParameter("gubun"),""); 	
	String key = Function.nullChk(request.getParameter("key"),"");
	String inputName = Function.nullChk(request.getParameter("inputName"),"");
	
	//out.print("inputName  " + inputName);
%>
 
<script type="text/javascript">
function more(pg){
 	var addr = document.frm.addr2.value;
 	frm.action="address.do?search=1&pg=" + pg;
	frm.submit();
}


	function search(){
		var frm = document.searchForm;
		var key = frm.key.value;
		if(key == ""){
			alert("주소를 입력해 주세요.");
			return;
		}
		
		//frm.action="address.do?action=search&key="+key;
		frm.submit();
		
	}
	
	function winClose(address){
		//opener.document.companyCreateForm1.entp_zip_no.value = zipcode;
		
		var inputName1 = document.searchForm.inputName.value;
		
		if(inputName1 == "organization_address"){
			opener.document.safetyCreateForm1.organization_address.value = address;
		}else if(inputName1 == "report_address"){
			opener.document.safetyCreateForm1.report_address.value = address;
		} 
		

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
	<input type="hidden" name="action" value="search" />
	<input type="hidden" name="inputName" value="<%=inputName %>" />
	
	<table border="0" >
		<tr>
			<td>
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
			<td><input type="text" name="key"  value="<%=key %>"onkeypress="EnterDown()" /></td>
			<td><input type="button" value="검색" onclick="search()" /></td>
		</tr>
	</table>
	<br>* 도로명으로 검색가능합니다.
</form>



	<table border="1">
			
				<c:forEach var="addr" items="${requestScope.list }" varStatus="i">
					<tr>
						<td>${addr.zipcode }</td>
						<td><a href="javascript:winClose('${addr.zipcode}')">${addr.gu} ${addr.doro_nm} ${addr.bldg_nm} ${addr.bldg1}</a></td>
					</tr>		
				</c:forEach>
			
		
	</table>
</div>
</body>
</html>
